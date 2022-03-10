package dev._2lstudios.prismatrade.trade.entities;

public class DemandEntity extends TradeEntity {
    public DemandEntity() {}

    public DemandEntity(int amount, String material, int durability, String ownerId, double price) {
        super(amount, material, durability,ownerId, price);
    }
}
