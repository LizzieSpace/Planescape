package de.horiko.planescape.client;

import com.mojang.blaze3d.platform.InputConstants;
import de.horiko.planescape.client.api.ClientNetworking;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import org.lwjgl.glfw.GLFW;

import static de.horiko.planescape.Planescape.MOD_ID;

public class PlanescapeClient implements ClientModInitializer {
	private static KeyMapping keyMapping;
	private static final KeyMapping.Category CATEGORY = KeyMapping.Category.register(ResourceLocation.fromNamespaceAndPath(MOD_ID, "keybinds"));

	@Override
	public void onInitializeClient() {
        ClientNetworking.init();

		keyMapping = KeyBindingHelper.registerKeyBinding(new KeyMapping(
				"key.planescape.openMenu",
				InputConstants.Type.KEYSYM,
				GLFW.GLFW_KEY_P,
				CATEGORY
		));

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (keyMapping.consumeClick()) {
                ClientNetworking.requestServerState();
				Minecraft.getInstance().setScreen(new PlanescapeMenu());
			}
		});
	}
}
