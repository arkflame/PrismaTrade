package dev._2lstudios.prismatrade;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;

import com.dotphin.milkshakeorm.MilkshakeORM;
import com.dotphin.milkshakeorm.providers.Provider;
import com.dotphin.milkshakeorm.repository.FindOption;
import com.dotphin.milkshakeorm.repository.Repository;
import com.dotphin.milkshakeorm.repository.SortOrder;
import com.dotphin.milkshakeorm.utils.MapFactory;

import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import dev._2lstudios.prismatrade.entities.BuyOrder;
import dev._2lstudios.prismatrade.entities.Category;
import dev._2lstudios.prismatrade.entities.SellOrder;

public class PrismaTradeAPI {
    private Server server;
    private Repository<Category> categoryRepository;
    private Repository<SellOrder> sellOrderRepository;
    private Repository<BuyOrder> buyOrderRepository;

    public PrismaTradeAPI(Server server, Configuration config) {
        Provider provider = MilkshakeORM.connect(config.getString("database.uri"));

        this.server = server;
        this.categoryRepository = MilkshakeORM.addRepository(Category.class, provider);
        this.sellOrderRepository = MilkshakeORM.addRepository(SellOrder.class, provider);
        this.buyOrderRepository = MilkshakeORM.addRepository(BuyOrder.class, provider);
    }

    public Category[] getCategories(int skip, int limit) {
        return categoryRepository.findMany(Collections.EMPTY_MAP,
                FindOption.create().skip(skip).limit(limit));
    }

    public SellOrder[] getSellOrders(Material material, int limit, int skip) {
        Map<String, Object> filter;

        if (material != null) {
            filter = MapFactory.create("material", material.name());
        } else {
            filter = Collections.EMPTY_MAP;
        }

        return sellOrderRepository.findMany(filter,
                FindOption.create().sort("id").sort("price", SortOrder.DESCENDANT).skip(skip).limit(limit));
    }

    public BuyOrder[] getBuyOrders(Material material, int limit, int skip) {
        Map<String, Object> filter;

        if (material != null) {
            filter = MapFactory.create("material", material.name());
        } else {
            filter = Collections.EMPTY_MAP;
        }

        return buyOrderRepository.findMany(filter,
                FindOption.create().sort("id").sort("price", SortOrder.ASCENDANT).skip(skip).limit(limit));
    }

    public double getUnitaryPrice(int amount, double price) {
        return price / amount;
    }

    public void addCategory(Material material) {
        String materialName = material.name();

        if (categoryRepository.findOne(MapFactory.create("name", materialName)) == null) {
            Category category = new Category();

            category.setName(materialName);
            category.save();
        }
    }

    public void buy(Player buyerPlayer, Material material, int buyAmount, double buyPrice) {
        Collection<ItemStack> itemsToGive = new HashSet<>();

        // Insta-Buy - Start
        double buyUnitaryPrice = getUnitaryPrice(buyAmount, buyPrice);

        for (SellOrder sellOrder : getSellOrders(material, 10, 0)) {
            int sellAmount = sellOrder.getAmount();
            double sellPrice = sellOrder.getPrice();
            double sellUnitaryPrice = getUnitaryPrice(sellAmount, sellPrice);

            if (sellUnitaryPrice <= buyUnitaryPrice) {
                int boughtAmount = Math.min(buyAmount, sellAmount);

                if (boughtAmount > 0) {
                    buyAmount -= boughtAmount;
                    sellAmount -= boughtAmount;

                    if (sellAmount > 0) {
                        sellOrder.setAmount(sellAmount);
                        sellOrder.setPrice(sellAmount * sellUnitaryPrice);
                        sellOrder.save();
                    } else {
                        sellOrder.delete();
                    }

                    ItemStack itemToGive = sellOrder.getItemStack().clone();

                    itemToGive.setAmount(boughtAmount);

                    itemsToGive.add(itemToGive);

                    // TODO: Give money to seller

                    if (buyAmount < 1) {
                        break;
                    }
                }
            }
        }
        // Insta-Buy - End

        if (itemsToGive.size() > 0) {
            Inventory inventory = buyerPlayer.getInventory();

            for (ItemStack itemToGive : itemsToGive) {
                inventory.addItem(itemToGive);
            }
        }

        if (buyAmount > 0) {
            addCategory(material);

            BuyOrder buyOrder = new BuyOrder();

            buyOrder.setOwnerName(buyerPlayer.getName());
            buyOrder.setOwnerUUID(buyerPlayer.getUniqueId());
            buyOrder.setMaterial(material);
            buyOrder.setAmount(buyAmount);
            buyOrder.setPrice(buyAmount * buyUnitaryPrice);
            buyOrder.save();
        }
    }

    public void sell(Player sellerPlayer, ItemStack sellItem, double sellPrice) {
        Material material = sellItem.getType();
        int sellAmount = sellItem.getAmount();
        double moneyToGive = 0;

        // Insta-Sell - Start
        double sellUnitaryPrice = getUnitaryPrice(sellAmount, sellPrice);

        for (BuyOrder buyOrder : getBuyOrders(material, 10, 0)) {
            int buyAmount = buyOrder.getAmount();
            double buyPrice = buyOrder.getPrice();
            double buyUnitaryPrice = getUnitaryPrice(buyAmount, buyPrice);

            if (buyUnitaryPrice >= sellUnitaryPrice) {
                int soldAmount = Math.min(buyAmount, sellAmount);

                if (soldAmount > 0) {
                    buyAmount -= soldAmount;
                    sellAmount -= soldAmount;

                    if (buyAmount < 1) {
                        buyOrder.delete();
                    } else {
                        buyOrder.setAmount(buyAmount);
                        buyOrder.setPrice(buyAmount * buyUnitaryPrice);
                        buyOrder.save();
                    }

                    ItemStack itemToGive = sellItem.clone();

                    itemToGive.setAmount(soldAmount);

                    Player player = server.getPlayer(buyOrder.getOwnerUUID());

                    player.getInventory().addItem(itemToGive);

                    moneyToGive += buyUnitaryPrice;

                    if (sellAmount < 1) {
                        break;
                    }
                }
            }
        }
        // Insta-Sell - End

        if (moneyToGive > 0) {
            // TODO: Give money to seller
        }

        if (sellAmount > 0) {
            addCategory(material);

            SellOrder sellOrder = new SellOrder();

            sellOrder.setOwnerName(sellerPlayer.getName());
            sellOrder.setOwnerUUID(sellerPlayer.getUniqueId());
            sellOrder.setItemStack(sellItem);
            sellOrder.setMaterial(material);
            sellOrder.setAmount(sellAmount);
            sellOrder.setPrice(sellUnitaryPrice * sellAmount);
            sellOrder.save();
        }
    }
}
