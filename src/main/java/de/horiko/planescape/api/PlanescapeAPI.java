package de.horiko.planescape.api;


import net.minecraft.server.WorldStem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

import static de.horiko.planescape.Planescape.MOD_ID;

public final class PlanescapeAPI {
    private static final Logger LOG = LoggerFactory.getLogger(MOD_ID + "/api");

    public record RequestServerState() {
    }

    public record ServerState(boolean initialized) {
    }

    public static class Server {
        private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(MOD_ID + "/server");
        private static boolean INITIALIZED = false;
        private static WorldStem worldStem;

        private static void initializeServer(WorldStem worldStem) {
            if (!INITIALIZED) {
                Server.worldStem = Objects.requireNonNull(worldStem);
                LOG.info("Initialized.");
                INITIALIZED = true;
            } else {
                LOG.warn("Already initialized. Did Something Go Wrong?");
            }
        }

        private static void finalizeServer() {
            if (INITIALIZED) {
                INITIALIZED = false;
                Server.worldStem = null;
                LOG.info("Closed.");
            } else {
                LOG.warn("Already closed. Did Something Go Wrong?");
            }
        }

        public static boolean isInitialized() {
            return INITIALIZED;
        }

        protected static WorldStem getWorldStem() {
            return worldStem;
        }
    }

}
