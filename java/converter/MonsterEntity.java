package converter;

import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.level.*;

public abstract class MonsterEntity extends Monster {
    protected MonsterEntity(EntityType<? extends Monster> p_33002_, Level p_33003_) {
        super(p_33002_, p_33003_);
    }
}
