package dev._2lstudios.prismatrade.trade;

public class OfferEntity extends TradeEntity{
    OfferEntity(int amount, String materialName,  int durability, String ownerId, double price) {
        super(amount, materialName, durability, ownerId, price);
    }
}
