package dev._2lstudios.prismatrade.trade;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class TradeController {
    private TradeService tradeService;

    public void buy(Player player, Material material, int durability, int amount, double price) {
        String materialName = material.name();
        OfferEntity[] offers = tradeService.getOffers(materialName, 100, 0);
        String ownerId = player.getUniqueId().toString();

        for (OfferEntity offer : offers) {
            if (amount >= 0 && offer.price <= price) {
                int amountBought = Math.min(offer.amount, amount);
                double earnings = amountBought * offer.price;

                amount -= amountBought;
                offer.amount -= amountBought;

                if (offer.amount <= 0) {
                    offer.delete();
                } else {
                    offer.save();
                }

                tradeService.addVaultItem(new VaultItemEntity(materialName, durability, ownerId, amountBought));
                // TODO: GIVE EARNINGS TO SELLER
            } else {
                break;
            }
        }

        if (amount > 0) {
            tradeService.addDemand(new DemandEntity(amount, materialName, durability, ownerId, price));
        }
    }

    public void sell(Player player, Material material, int durability, int amount, double price) {
        String materialName = material.name();
        DemandEntity[] demands = tradeService.getDemands(materialName, 100, 0);
        double earnings = 0;

        for (DemandEntity demand : demands) {
            if (amount >= 0 && demand.price >= price) {
                int amountSold = Math.min(demand.amount, amount);

                earnings += amountSold * demand.price;
                amount -= amountSold;
                demand.amount -= amountSold;

                if (demand.amount <= 0) {
                    demand.delete();
                } else {
                    demand.save();
                }

                tradeService.addVaultItem(new VaultItemEntity(materialName, durability, demand.ownerId, amountSold));
            } else {
                break;
            }
        }

        if (earnings > 0) {
            // TODO: GIVE EARNINGS TO SELLER
        }

        if (amount > 0) {
            tradeService.addOffer(new OfferEntity(amount, materialName, durability, player.getUniqueId().toString(), price));
        }
    }

    public void sell(Player player, ItemStack item, double price) {
        Material material = item.getType();
        int amount = item.getAmount();
        int durability = item.getDurability();

        sell(player, material, durability, amount, price);

        item.setAmount(0);
    }
}
