package com.deflatedpickle.cqc.mixin;

import com.deflatedpickle.cqc.CloseQuartersConcoctions;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PotionItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;

@SuppressWarnings({"unused", "UnusedMixin"})
@Mixin(PotionItem.class)
abstract class MixinPotionItem extends Item {
    public MixinPotionItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        return CloseQuartersConcoctions.INSTANCE.useOnEntity((PotionItem) (Object) this, user, entity, stack);
    }
}
