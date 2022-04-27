package dev._2lstudios.prismatrade;

import org.bukkit.configuration.Configuration;

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
}
