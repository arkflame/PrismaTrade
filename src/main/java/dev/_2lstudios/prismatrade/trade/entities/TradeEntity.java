package dev._2lstudios.prismatrade.trade.entities;

import com.dotphin.milkshakeorm.entity.Entity;
import com.dotphin.milkshakeorm.entity.ID;
import com.dotphin.milkshakeorm.entity.Prop;

public class TradeEntity extends Entity {
    @ID
    public String id;

    @Prop
    public int amount;

    @Prop
    public String material;

    @Prop
    public int durability;

    @Prop
    public String ownerId;

    @Prop
    public double price;

    public TradeEntity() {}

    public TradeEntity(int amount, String material, int durability, String ownerId, double price) {
        this.amount = amount;
        this.material = material;
        this.durability = durability;
        this.ownerId = ownerId;
        this.price = price;
    }
}
