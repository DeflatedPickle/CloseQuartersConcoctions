/* Copyright (c) 2021 DeflatedPickle under the MIT license */

package com.deflatedpickle.cqc

import net.fabricmc.api.ModInitializer
import net.minecraft.block.Blocks
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.PotionItem
import net.minecraft.item.ThrowablePotionItem
import net.minecraft.potion.PotionUtil
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.util.ActionResult

@Suppress("UNUSED")
object CloseQuartersConcoctions : ModInitializer {
    private const val MOD_ID = "$[id]"
    private const val NAME = "$[name]"
    private const val GROUP = "$[group]"
    private const val AUTHOR = "$[author]"
    private const val VERSION = "$[version]"

    override fun onInitialize() {
        println(listOf(MOD_ID, NAME, GROUP, AUTHOR, VERSION))
    }

    fun useOnEntity(
        potion: PotionItem,
        user: PlayerEntity,
        entity: LivingEntity,
        stack: ItemStack
    ): ActionResult {
        if (potion is ThrowablePotionItem) return ActionResult.FAIL

        val world = user.world

        world.playSound(
            user, entity.blockPos,
            SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.BLOCKS,
            1.0f, world.getRandom().nextFloat() * 0.4f + 0.8f
        )

        world.addBlockBreakParticles(
            entity.blockPos, Blocks.GLASS.defaultState
        )

        if (!world.isClient) {
            if (!user.abilities.creativeMode) {
                stack.decrement(1)
            }

            entity.damage(DamageSource.player(user), 2f)
            entity.damageHelmet(DamageSource.player(user), 1f)

            for (i in PotionUtil.getPotionEffects(stack)) {
                if (i.effectType.isInstant) {
                    i.effectType.applyInstantEffect(
                        user,
                        user,
                        entity,
                        i.amplifier,
                        1.0
                    )
                } else {
                    entity.addStatusEffect(StatusEffectInstance(i))
                }
            }
        }

        return ActionResult.success(world.isClient)
    }
}
