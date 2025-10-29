package de.horiko.planescape.client;

import io.wispforest.owo.ui.base.BaseOwoScreen;
import io.wispforest.owo.ui.component.Components;
import io.wispforest.owo.ui.container.Containers;
import io.wispforest.owo.ui.container.FlowLayout;
import io.wispforest.owo.ui.core.*;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;


public class PlanescapeMenu extends BaseOwoScreen<FlowLayout> {

	public PlanescapeMenu() {

	}

	@Override
	protected @NotNull OwoUIAdapter<FlowLayout> createAdapter() {
		//TODO
		return OwoUIAdapter.create(this, Containers::verticalFlow);
	}

	@Override
	protected void build(FlowLayout rootComponent) {
		rootComponent.surface(Surface.VANILLA_TRANSLUCENT)
				.horizontalAlignment(HorizontalAlignment.CENTER)
				.verticalAlignment(VerticalAlignment.CENTER);


		rootComponent.child(
				Containers.verticalFlow(Sizing.content(), Sizing.content())
				          .child(Components.button(Component.literal("A Button"), button -> {

							  System.out.println("click");
						  }))
//						.child(Components.list())
						.padding(Insets.of(10))
						.surface(Surface.DARK_PANEL)
						.verticalAlignment(VerticalAlignment.CENTER)
						.horizontalAlignment(HorizontalAlignment.CENTER)
		                   );
	}
}
