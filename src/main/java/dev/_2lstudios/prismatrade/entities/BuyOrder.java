package dev._2lstudios.prismatrade.entities;

import com.dotphin.milkshakeorm.entity.Entity;
import com.dotphin.milkshakeorm.entity.ID;
import com.dotphin.milkshakeorm.entity.Prop;

import org.bukkit.Material;

public class BuyOrder extends Entity {
    @ID
    public String id;

    @Prop
    public String material;

    @Prop
    public double price;

    @Prop
    public int amount;

    public String getId() {
        return id;
    }

    public Material getMaterial() {
        Material material = Material.getMaterial(this.material);

        if (material == null) {
            material = Material.AIR;
        }

        return material;
    }

    public BuyOrder setMaterial(Material material) {
        this.material = material.name();

        return this;
    }

    public double getPrice() {
        return price;
    }

    public BuyOrder setPrice(double price) {
        this.price = price;

        return this;
    }

    public int getAmount() {
        return amount;
    }

    public BuyOrder setAmount(int amount) {
        this.amount = amount;

        return this;
    }
}
