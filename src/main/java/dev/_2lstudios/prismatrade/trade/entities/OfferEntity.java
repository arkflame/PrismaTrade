package dev._2lstudios.prismatrade.trade.entities;

public class OfferEntity extends TradeEntity {
    public OfferEntity() {}

    public OfferEntity(int amount, String materialName,  int durability, String ownerId, double price) {
        super(amount, materialName, durability, ownerId, price);
    }
}
