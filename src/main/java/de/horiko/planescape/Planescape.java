package de.horiko.planescape;

import de.horiko.planescape.api.Networking;
import net.fabricmc.api.ModInitializer;

public class Planescape implements ModInitializer {
	public static final String MOD_ID = "planescape";
	public static final de.horiko.planescape.PlanescapeCommonConfig CONFIG = de.horiko.planescape.PlanescapeCommonConfig.createAndLoad();

	@Override
	public void onInitialize() {
        Networking.init();

	}
}
