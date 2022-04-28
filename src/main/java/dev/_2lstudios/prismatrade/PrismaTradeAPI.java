package dev._2lstudios.prismatrade;

import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;

import java.util.Collections;

import com.dotphin.milkshakeorm.MilkshakeORM;
import com.dotphin.milkshakeorm.providers.Provider;
import com.dotphin.milkshakeorm.repository.FindOption;
import com.dotphin.milkshakeorm.repository.Repository;
import com.dotphin.milkshakeorm.repository.SortOrder;

import dev._2lstudios.prismatrade.entities.BuyOrder;
import dev._2lstudios.prismatrade.entities.Category;
import dev._2lstudios.prismatrade.entities.SellOrder;

public class PrismaTradeAPI {
    private Repository<Category> categoryRepository;
    private Repository<SellOrder> sellOrderRepository;
    private Repository<BuyOrder> buyOrderRepository;

    public PrismaTradeAPI(Configuration config) {
        Provider provider = MilkshakeORM.connect(config.getString("database.uri"));

        this.categoryRepository = MilkshakeORM.addRepository(Category.class, provider);
        this.sellOrderRepository = MilkshakeORM.addRepository(SellOrder.class, provider);
        this.buyOrderRepository = MilkshakeORM.addRepository(BuyOrder.class, provider);
    }

    public Category[] getCategories(int limit, int skip) {
        return categoryRepository.findMany(Collections.EMPTY_MAP, FindOption.create().skip(skip).limit(limit));
    }

    public SellOrder[] getSellOrders(int limit, int skip) {
        return sellOrderRepository.findMany(Collections.EMPTY_MAP,
                FindOption.create().sort("id").sort("price", SortOrder.ASCENDANT).skip(skip).limit(limit));
    }

    public BuyOrder[] getBuyOrders(int limit, int skip) {
        return buyOrderRepository.findMany(Collections.EMPTY_MAP,
                FindOption.create().sort("id").sort("price", SortOrder.DESCENDANT).skip(skip).limit(limit));
    }

    public double getUnitaryPrice(int amount, double price) {
        return amount / price;
    }

    public void buy(Player player, Material material, int amount, double price) {
        // Insta-Buy - Start
        for (SellOrder sellOrder : getSellOrders(10, 0)) {
            final double unitaryPrice = getUnitaryPrice(amount, price);
            final double sellPrice = sellOrder.getPrice();
            int sellAmount = sellOrder.getAmount();
            final int initialSellAmount = sellAmount;
            final double sellUnitaryPrice = getUnitaryPrice(sellAmount, sellPrice);

            if (unitaryPrice >= sellUnitaryPrice) {
                if (amount >= sellAmount) {
                    amount -= initialSellAmount;
                    price = amount * unitaryPrice;

                    sellOrder.delete();
                } else {
                    sellAmount -= amount;
                    amount = 0;
                    price -= initialSellAmount * sellUnitaryPrice;
                    sellOrder.setPrice(sellUnitaryPrice * sellAmount);
                    sellOrder.setAmount(sellAmount);
                    sellOrder.save();
                }

                double toGive = initialSellAmount * unitaryPrice;

                if (toGive > 0) {
                    // TODO: Give money to seller
                }

                if (amount <= 0) {
                    break;
                }
            }
        }
        // Insta-Buy - End

        if (amount > 0) {
            BuyOrder buyOrder = new BuyOrder();

            buyOrder.setMaterial(material);
            buyOrder.setAmount(amount);
            buyOrder.setPrice(price);
            buyOrder.save();
        }
    }
}
