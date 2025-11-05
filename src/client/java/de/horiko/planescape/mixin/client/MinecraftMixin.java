package de.horiko.planescape.mixin.client;

import de.horiko.planescape.client.api.ClientNetworking;
import net.minecraft.client.gui.screens.Screen;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Debug(export = true)
@Mixin(net.minecraft.client.Minecraft.class)
public class MinecraftMixin {

    @Inject(method = "disconnect", at = @At(value = "HEAD"))
    private static void injectPlanescapeClientApi_Finalizer(Screen nextScreen, boolean keepResourcePacks, CallbackInfo ci) {
        ClientNetworkingAccessor.getLOG().info("Connection Reset");
        ClientNetworkingAccessor.setSERVER_INITIALIZED(false);
    }

    @Mixin(value = ClientNetworking.class, remap = false)
    private interface ClientNetworkingAccessor {
        @Accessor
        static void setSERVER_INITIALIZED(boolean SERVER_INITIALIZED) {
            throw new UnsupportedOperationException();
        }

        @Accessor
        static Logger getLOG() {
            throw new UnsupportedOperationException();
        }
    }
}
