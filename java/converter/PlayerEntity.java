package converter;

import com.mojang.authlib.*;
import net.minecraft.core.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.level.*;

public abstract class PlayerEntity extends Player {
    public PlayerEntity(Level p_36114_, BlockPos p_36115_, float p_36116_, GameProfile p_36117_) {
        super(p_36114_, p_36115_, p_36116_, p_36117_);
    }
}
