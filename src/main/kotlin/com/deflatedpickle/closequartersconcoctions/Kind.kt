/* Copyright (c) 2021-2022 DeflatedPickle under the MIT license */

package com.deflatedpickle.closequartersconcoctions

import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents

enum class Kind(
    val sound: SoundEvent,
    val block: Block,
) {
    GLASS(
        SoundEvents.BLOCK_GLASS_BREAK,
        Blocks.GLASS,
    ),
    WOOD(
        SoundEvents.BLOCK_WOOD_BREAK,
        Blocks.OAK_WOOD,
    ),
}
