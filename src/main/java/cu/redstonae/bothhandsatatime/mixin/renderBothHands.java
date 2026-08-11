package cu.redstonae.bothhandsatatime.mixin;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static cu.redstonae.bothhandsatatime.config.config.returnConfig;

@Mixin (HeldItemRenderer.class)
public abstract class renderBothHands{

    @Shadow
    protected abstract void renderArmHoldingItem(MatrixStack matrices, OrderedRenderCommandQueue queue, int light, float equipProgress, float swingProgress, Arm arm);

    @Inject(method = "renderFirstPersonItem", at = @At("HEAD"))
    private void bothHands(AbstractClientPlayerEntity player, float tickProgress, float pitch, Hand hand, float swingProgress, ItemStack item, float equipProgress, MatrixStack matrices, OrderedRenderCommandQueue queue, int light, CallbackInfo ci){
        String renderBoth = returnConfig("bothHand");
        boolean mainHand = hand == Hand.MAIN_HAND;
        Item mainHandItem = player.getMainHandStack().getItem();
        String mainHandItemId = Registries.ITEM.getId(mainHandItem).toString();
        Arm offArm = mainHand ? player.getMainArm() : player.getMainArm().getOpposite();
        if (item.isEmpty() && renderBoth.equals("true") && (!mainHand && !player.isInvisible())) {
            this.renderArmHoldingItem(matrices, queue, light, equipProgress, swingProgress, offArm);
        }
    }
}
