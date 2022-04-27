package dev._2lstudios.prismatrade.entities;

import com.dotphin.milkshakeorm.entity.Entity;
import com.dotphin.milkshakeorm.entity.ID;
import com.dotphin.milkshakeorm.entity.Prop;

public class Category extends Entity {
    @ID
    public String id;

    @Prop
    public String name;

    public String getName() {
        return name;
    }

    public Category setName(String name) {
        this.name = name;

        return this;
    }
}
