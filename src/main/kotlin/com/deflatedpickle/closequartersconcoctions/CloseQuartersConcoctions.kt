/* Copyright (c) 2021-2022 DeflatedPickle under the MIT license */

package com.deflatedpickle.closequartersconcoctions

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.ThrowablePotionItem
import net.minecraft.sound.SoundCategory
import net.minecraft.util.ActionResult
import org.quiltmc.loader.api.ModContainer
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer

@Suppress("UNUSED")
object CloseQuartersConcoctions : ModInitializer {
    private const val MOD_ID = "$[id]"
    private const val NAME = "$[name]"
    private const val GROUP = "$[group]"
    private const val AUTHOR = "$[author]"
    private const val VERSION = "$[version]"

    override fun onInitialize(mod: ModContainer) {
        println(listOf(MOD_ID, NAME, GROUP, AUTHOR, VERSION))
    }

    fun useOnEntity(
        user: PlayerEntity,
        entity: LivingEntity,
        stack: ItemStack,
        kind: Kind,
    ): ActionResult {
        if (stack.item is ThrowablePotionItem) return ActionResult.PASS

        val world = user.world

        world.playSound(
            user, entity.blockPos,
            kind.sound, SoundCategory.BLOCKS,
            1.0f, world.getRandom().nextFloat() * 0.4f + 0.8f
        )

        world.addBlockBreakParticles(
            entity.blockPos, kind.block.defaultState
        )

        if (!world.isClient) {
            if (!user.abilities.creativeMode) {
                stack.decrement(1)
            }

            entity.damage(DamageSource.player(user), 2f)
            entity.damageHelmet(DamageSource.player(user), 1f)

            stack.finishUsing(world, entity)
        }

        return ActionResult.success(world.isClient)
    }
}
