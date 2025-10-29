package de.horiko.planescape;

import net.fabricmc.api.ModInitializer;

public class Planescape implements ModInitializer {
	public static final String MOD_ID = "planescape";
	public static final PlanescapeAPI API = new PlanescapeAPI();

	public static final de.horiko.planescape.PlanescapeCommonConfig CONFIG = de.horiko.planescape.PlanescapeCommonConfig.createAndLoad();

	@Override
	public void onInitialize() {

	}
}
