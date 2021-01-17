package juuxel.adorn.lib

import juuxel.adorn.Adorn
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.sound.SoundEvent
import net.minecraftforge.fml.RegistryObject
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries

object AdornSounds {
    val SOUNDS: DeferredRegister<SoundEvent> = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Adorn.NAMESPACE)

    val BLOCK_CHAIN_LINK_FENCE_BREAK = register("block.adorn.chain_link_fence.break")
    val BLOCK_CHAIN_LINK_FENCE_STEP = register("block.adorn.chain_link_fence.step")
    val BLOCK_CHAIN_LINK_FENCE_PLACE = register("block.adorn.chain_link_fence.place")
    val BLOCK_CHAIN_LINK_FENCE_HIT = register("block.adorn.chain_link_fence.hit")
    val BLOCK_CHAIN_LINK_FENCE_FALL = register("block.adorn.chain_link_fence.fall")

    val CHAIN_LINK_FENCE: BlockSoundGroup = LazyBlockSoundGroup(
        1.0F,
        1.5F,
        BLOCK_CHAIN_LINK_FENCE_BREAK,
        BLOCK_CHAIN_LINK_FENCE_STEP,
        BLOCK_CHAIN_LINK_FENCE_PLACE,
        BLOCK_CHAIN_LINK_FENCE_HIT,
        BLOCK_CHAIN_LINK_FENCE_FALL
    )

    private fun register(name: String): RegistryObject<SoundEvent> =
        SOUNDS.register(name) { SoundEvent(Adorn.id(name)) }

    private class LazyBlockSoundGroup(
        volume: Float, pitch: Float,
        private val breakSound: RegistryObject<SoundEvent>,
        private val stepSound: RegistryObject<SoundEvent>,
        private val placeSound: RegistryObject<SoundEvent>,
        private val hitSound: RegistryObject<SoundEvent>,
        private val fallSound: RegistryObject<SoundEvent>
    ) : BlockSoundGroup(volume, pitch, null, null, null, null, null) {
        override fun getBreakSound() = breakSound.get()
        override fun getStepSound() = stepSound.get()
        override fun getPlaceSound() = placeSound.get()
        override fun getHitSound() = hitSound.get()
        override fun getFallSound() = fallSound.get()
    }
}
