package dev.toastmc.toastclient.api.events

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.hud.ClientBossBar
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.input.Input
import net.minecraft.client.network.AbstractClientPlayerEntity
import net.minecraft.client.render.Camera
import net.minecraft.client.render.LightmapTextureManager
import net.minecraft.client.util.Window
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.MovementType
import net.minecraft.entity.damage.DamageSource
import net.minecraft.network.packet.s2c.play.ChunkDataS2CPacket
import net.minecraft.text.Text
import net.minecraft.util.hit.EntityHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Box
import net.minecraft.util.math.Direction
import net.minecraft.util.math.Vec3d
import net.minecraft.world.BlockView
import net.minecraft.world.World
import net.minecraft.world.chunk.Chunk
import org.quantumclient.energy.Event
import java.util.function.Predicate

class AddCollisionBoxToListEvent(
    val block: Block,
    val state: BlockState,
    val world: World,
    val pos: BlockPos,
    val entityBox: Box,
    val collidingBoxes: MutableList<Box>,
    val entity: Entity,
    val isBool: Boolean
) : Event()

class ApplyFogEvent : Event()

class BindEvent(val key: Int, val scancode: Int, i: Int) : Event() {
    val pressed = i != 0
    val ingame = MinecraftClient.getInstance().currentScreen == null
}

class CameraHurtEvent(val tickDelta: Float) : Event()

class CanBeControlledEvent(val entity: Entity, var canBeSteered: Boolean?) : Event()

open class ChunkEvent private constructor(val chunk: Chunk?) : Event() {
    class Load(chunk: Chunk?, val packet: ChunkDataS2CPacket) : ChunkEvent(chunk)
    class Unload(chunk: Chunk?) : ChunkEvent(chunk)
}

class CloseScreenInPortalEvent(val screen: Screen?) : Event()

class DisplaySizeChangedEvent

open class EntityEvent(val entity: Entity) : Event() {
    class EntityCollision(entity: Entity, var x: Double, var y: Double, var z: Double) : EntityEvent(entity)
    class EntityDamage(entity: Entity, var source: DamageSource, var damage: Float) : EntityEvent(entity)
    class EntityDeath(entity: Entity, var source: DamageSource) : EntityEvent(entity)
}

class EntityJoinWorldEvent(val id: Int, val entity: Entity) : Event()

class EntityVelocityMultiplierEvent(val entity: Entity?, var multiplier: Float) : Event()

class InputUpdateEvent(val previousState: Input, var newState: Input) : Event()

class LivingEntityTickEvent(private val entity: LivingEntity) : Event() {
    fun getEntity(): Entity {
        return entity
    }
}

class MoveEntityFluidEvent(val entity: Entity, var movement: Vec3d) : Event()

class PlayerAttackBlockEvent(val position: BlockPos, val facing: Direction)

class PlayerAttackEntityEvent(val entity: Entity) : Event()

class PlayerMoveEvent(val type: MovementType, val vec: Vec3d) : Event()

open class RenderBossBarEvent : Event() {
    class GetIterator(var iterator: Iterator<ClientBossBar>) : RenderBossBarEvent()
    class GetText(val bossBar: ClientBossBar, var text: Text) : RenderBossBarEvent()
    class Spacing(var spacing: Int) : RenderBossBarEvent()
}

class RenderGuiEvent(val window: Window, val matrixStack: MatrixStack) : Event()

class RenderPlayerNametagEvent(val entity: AbstractClientPlayerEntity) : Event()

class RenderWeatherEvent(val manager: LightmapTextureManager, val f: Float, val d: Double, val e: Double, val g: Double) : Event()

open class ScreenEvent(var screen: Screen?) : Event() {
    class Displayed(screen: Screen?) : ScreenEvent(screen)
    class Closed(screen: Screen?) : ScreenEvent(screen)
}

class TargetEntityEvent(
    val entity: Entity,
    val vec3d: Vec3d,
    val vec3d2: Vec3d,
    val box: Box,
    val predicate: Predicate<Entity>,
    val d: Double,
    var trace: EntityHitResult?
) : Event()

class CameraUpdateEvent(
    val camera: Camera,
    val area: BlockView?,
    val focusedEntity: Entity?,
    val thirdPerson: Boolean,
    val inverseView: Boolean,
    val tickDelta: Float
) : Event()

class UpdateLookEvent(
    val deltaX: Double,
    val deltaY: Double
) : Event()

