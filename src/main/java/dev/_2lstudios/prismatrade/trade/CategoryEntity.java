package dev._2lstudios.prismatrade.trade;

import com.dotphin.milkshakeorm.entity.Entity;
import com.dotphin.milkshakeorm.entity.ID;
import com.dotphin.milkshakeorm.entity.Prop;

public class CategoryEntity extends Entity {
    @ID
    public String id;

    @Prop
    public String name;

    public CategoryEntity(String name) {
        this.name = name;
    }
}
