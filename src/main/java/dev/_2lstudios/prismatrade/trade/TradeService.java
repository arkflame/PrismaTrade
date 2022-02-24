package dev._2lstudios.prismatrade.trade;

import java.util.Collections;
import java.util.UUID;

import com.dotphin.milkshakeorm.MilkshakeORM;
import com.dotphin.milkshakeorm.errors.NotIDAnnotationException;
import com.dotphin.milkshakeorm.providers.Provider;
import com.dotphin.milkshakeorm.repository.FindOption;
import com.dotphin.milkshakeorm.repository.Repository;
import com.dotphin.milkshakeorm.utils.MapFactory;

import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.Plugin;

public class TradeService {
    private Repository<CategoryEntity> categories;
    private Repository<DemandEntity> demands;
    private Repository<OfferEntity> offers;
    private Repository<VaultItemEntity> vault;

    public TradeService(Plugin plugin) {
        Configuration config = plugin.getConfig();
        Provider provider = MilkshakeORM.connect(config.getString("database.uri"));
        MilkshakeORM.addRepository(CategoryEntity.class, provider, "trade-categories");
        MilkshakeORM.addRepository(DemandEntity.class, provider, "trade-demands");
        MilkshakeORM.addRepository(OfferEntity.class, provider, "trade-offers");
        MilkshakeORM.addRepository(OfferEntity.class, provider, "trade-vault");
        categories = MilkshakeORM.getRepository(CategoryEntity.class);
        demands = MilkshakeORM.getRepository(DemandEntity.class);
        offers = MilkshakeORM.getRepository(OfferEntity.class);
        vault = MilkshakeORM.getRepository(VaultItemEntity.class);
    }

    public CategoryEntity getCategory(String name) {
        return categories.findOne(MapFactory.create("name", name));
    }

    public CategoryEntity[] getCategories() {
        return categories.findMany(Collections.EMPTY_MAP);
    }

    public void addCategory(CategoryEntity categoryEntity) {
        CategoryEntity category = getCategory(categoryEntity.name);

        try {
            if (category == null) {
                categories.save(category);
            } else {
                category.id = categoryEntity.id;
                categories.save(category);
            }
        } catch (NotIDAnnotationException e) {
            /* Ignored */
        }
    }

    public void addCategory(String categoryName) {
        CategoryEntity categoryEntity = new CategoryEntity(categoryName);

        addCategory(categoryEntity);
    }

    public DemandEntity[] getDemands(int limit, int skip) {
        DemandEntity[] demandEntities = demands.findMany(Collections.EMPTY_MAP,
                FindOption.create().sort("price").skip(limit).skip(skip));

        return demandEntities;
    }

    public DemandEntity[] getDemands(String material, int limit, int skip) {
        DemandEntity[] demandEntities = demands.findMany(MapFactory.create("material", material),
                FindOption.create().sort("price").skip(limit).skip(skip));

        return demandEntities;
    }

    public void clearCategories() {
        demands.deleteMany(Collections.EMPTY_MAP);
    }

    public void addDemand(DemandEntity demandEntity) {
        addCategory(demandEntity.material);

        try {
            demands.save(demandEntity);
        } catch (NotIDAnnotationException e) {
            /* Ignored */
        }
    }

    public OfferEntity[] getOffers(int limit, int skip) {
        OfferEntity[] offerEntities = offers.findMany(Collections.EMPTY_MAP,
                FindOption.create().sort("price").skip(limit).skip(skip));

        return offerEntities;
    }

    public OfferEntity[] getOffers(String material, int limit, int skip) {
        OfferEntity[] offerEntities = offers.findMany(MapFactory.create("material", material),
                FindOption.create().sort("price").skip(limit).skip(skip));

        return offerEntities;
    }

    public void addOffer(OfferEntity offerEntity) {
        addCategory(offerEntity.material);

        try {
            offers.save(offerEntity);
        } catch (NotIDAnnotationException e) {
            /* Ignored */
        }
    }

    public void addVaultItem(VaultItemEntity vaultItemEntity) {
        try {
            vault.save(vaultItemEntity);
        } catch (NotIDAnnotationException e) {
            /* Ignored */
        }
    }

    public VaultItemEntity[] getVaultItems(UUID ownerId) {
        return vault.findMany(MapFactory.create("ownerId", ownerId.toString()));
    }
}