package dev._2lstudios.prismatrade.trade;

import com.dotphin.milkshakeorm.entity.Entity;
import com.dotphin.milkshakeorm.entity.ID;
import com.dotphin.milkshakeorm.entity.Prop;

public class VaultItemEntity extends Entity {
    @ID
    public String id;

    @Prop
    public String material;

    @Prop
    public int durability;

    @Prop
    public String ownerId;

    @Prop
    public int amount;

    public VaultItemEntity(String material, int durability, String ownerId, int amount) {
        this.material = material;
        this.durability = durability;
        this.ownerId = ownerId;
        this.amount = amount;
    }
}
