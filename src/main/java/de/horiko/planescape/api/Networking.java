package de.horiko.planescape.api;

import io.wispforest.owo.network.OwoNetChannel;
import net.minecraft.resources.ResourceLocation;

import static de.horiko.planescape.Planescape.MOD_ID;

public final class Networking {

    public static final OwoNetChannel API_CHANNEL = OwoNetChannel.create(ResourceLocation.fromNamespaceAndPath(MOD_ID, "api"));

    public static void init() {
        API_CHANNEL.registerClientboundDeferred(PlanescapeAPI.ServerState.class);
        API_CHANNEL.registerServerbound(PlanescapeAPI.RequestServerState.class, (ctx, msg) -> {
            API_CHANNEL.serverHandle(msg.player()).send(new PlanescapeAPI.ServerState(PlanescapeAPI.Server.isInitialized()));
        });
    }

}
