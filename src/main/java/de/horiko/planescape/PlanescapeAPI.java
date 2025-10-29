package de.horiko.planescape;

import net.minecraft.server.WorldStem;

import java.util.Objects;

public class PlanescapeAPI {
	private boolean INITIALIZED = false;
	private static WorldStem worldStem;

	private void initializeAPI(WorldStem worldStem) {
		if (!INITIALIZED) {
			PlanescapeAPI.worldStem = Objects.requireNonNull(worldStem);
			System.out.println("PlanescapeAPI initialized.");
			INITIALIZED = true;
		}
		else {}
	}

	private void finalizeAPI() {
		if (INITIALIZED) {
			INITIALIZED = false;
			PlanescapeAPI.worldStem = null;
			System.out.println("PlanescapeAPI closed.");
		}
		else {}
	}

	public PlanescapeAPI getInstance() {
		return null;
	}

}
