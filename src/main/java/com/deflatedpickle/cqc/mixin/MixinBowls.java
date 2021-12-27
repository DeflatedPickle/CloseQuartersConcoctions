package com.deflatedpickle.cqc.mixin;

import com.deflatedpickle.cqc.CloseQuartersConcoctions;
import com.deflatedpickle.cqc.Kind;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.StewItem;
import net.minecraft.item.SuspiciousStewItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;

@SuppressWarnings({"unused", "UnusedMixin"})
@Mixin({
        StewItem.class,
        SuspiciousStewItem.class
})
abstract class MixinBowls extends Item {
    public MixinBowls(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        return CloseQuartersConcoctions.INSTANCE.useOnEntity(user, entity, stack, Kind.WOOD);
    }
}
