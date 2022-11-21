/* Copyright (c) 2022 DeflatedPickle under the MIT license */

package com.deflatedpickle.closequartersconcoctions.mixin;

import com.deflatedpickle.closequartersconcoctions.CloseQuartersConcoctions;
import com.deflatedpickle.closequartersconcoctions.Kind;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ExperienceBottleItem;
import net.minecraft.item.GlassBottleItem;
import net.minecraft.item.HoneyBottleItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PotionItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;

@SuppressWarnings({"unused", "UnusedMixin"})
@Mixin({PotionItem.class, GlassBottleItem.class, HoneyBottleItem.class, ExperienceBottleItem.class})
abstract class MixinBottles extends Item {
  public MixinBottles(Settings settings) {
    super(settings);
  }

  @Override
  public ActionResult useOnEntity(
      ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
    return CloseQuartersConcoctions.INSTANCE.useOnEntity(user, entity, stack, Kind.GLASS);
  }
}
