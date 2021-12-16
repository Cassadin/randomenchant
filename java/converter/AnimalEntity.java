package converter;

import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.level.*;

public abstract class AnimalEntity extends Animal {

    protected AnimalEntity(EntityType<? extends Animal> p_27557_, Level p_27558_) {
        super(p_27557_, p_27558_);
    }
}
