package de.horiko.planescape.client.api;

import io.wispforest.owo.network.ClientAccess;
import org.slf4j.Logger;

import static de.horiko.planescape.api.Networking.API_CHANNEL;

public final class ClientNetworking {
    private static final Logger LOG = org.slf4j.LoggerFactory.getLogger("Planescape/API/ClientNetworking");
    private static boolean SERVER_INITIALIZED = false;

    public static void init() {
        API_CHANNEL.registerClientbound(
                de.horiko.planescape.api.PlanescapeAPI.ServerState.class,
                ClientNetworking::handlerServerState_S2C
        );
    }

    private static void handlerServerState_S2C(de.horiko.planescape.api.PlanescapeAPI.ServerState pkg, ClientAccess access) {
        if (SERVER_INITIALIZED != pkg.initialized()) {
            LOG.info("Server State: {}", pkg.initialized());
        }
        SERVER_INITIALIZED = pkg.initialized();
    }

    public static void requestServerState() {
        API_CHANNEL.clientHandle().send(new de.horiko.planescape.api.PlanescapeAPI.RequestServerState());
    }

    public static boolean isServerInitialized() {
        return SERVER_INITIALIZED;
    }
}
