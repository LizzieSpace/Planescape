package de.horiko.planescape.mixin;


import com.mojang.datafixers.DataFixer;
import de.horiko.planescape.PlanescapeAPI;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.Services;
import net.minecraft.server.WorldStem;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.server.level.progress.LevelLoadListener;
import net.minecraft.server.packs.repository.PackRepository;
import net.minecraft.world.level.storage.LevelStorageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.net.Proxy;

import static de.horiko.planescape.Planescape.API;

/**
 * <p>A Mixin class targeting the {@code MinecraftServer} to integrate the {@code PlanescapeAPI}
 * with Minecraft's server lifecycle events.</p>
 *
 * <p>This Mixin is responsible for:</p>
 * <ul>
 *   <li>Initializing the {@code PlanescapeAPI} during the Minecraft server's construction.</li>
 *   <li>Finalizing and cleaning up the {@code PlanescapeAPI} upon the server's shutdown.</li>
 * </ul>
 *
 * <p>The integration leverages Fabric's Mixin library, utilizing injection into the
 * {@code MinecraftServer} to hook into specific lifecycle methods and ensure the proper
 * execution of {@code PlanescapeAPI} logic.</p>
 *
 * <p>Details of key injection points:</p>
 * <ul>
 *   <li><b>Initializer:</b> Injects into the constructor of {@code MinecraftServer} at the end
 *   (<code>RETURN</code>) to initialize the {@code PlanescapeAPI} with the server's world stem.</li>
 *   <li><b>Finalizer:</b> Injects into {@code MinecraftServer}'s {@code onServerExit} method,
 *   after its execution (<code>RETURN</code>), to trigger the cleanup process of {@code PlanescapeAPI}.</li>
 * </ul>
 */
@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {

	@Inject(method = "<init>", at = @At(value = "RETURN"))
	private void InjectPlanescapeAPI_Initializer(Thread serverThread,
	                    LevelStorageSource.LevelStorageAccess storageSource,
	                    PackRepository packRepository,
	                    WorldStem worldStem,
	                    Proxy proxy,
	                    DataFixer fixerUpper,
	                    Services services,
	                    LevelLoadListener levelLoadListener,
	                    CallbackInfo ci
	                   ) {
		//noinspection ReferenceToMixin
		((PlanescapeAPIAccessor) API).callInitializeAPI(worldStem);
	}

	@Mixin(PlanescapeAPI.class)
	private interface PlanescapeAPIAccessor {
		@Invoker void callInitializeAPI(WorldStem worldStem);
		@Invoker void callFinalizeAPI();
	}

	@Mixin({MinecraftServer.class, DedicatedServer.class})
	private static class ServerShutdownListener {
		@Inject(method = "onServerExit()V", at = @At(value = "RETURN"))
		private void InjectPlanescapeAPI_Finalizer(CallbackInfo ci) {
			//noinspection ReferenceToMixin
			((PlanescapeAPIAccessor) API).callFinalizeAPI();
		}
	}
}
