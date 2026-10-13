package com.example.autototem;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Items;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.SlotActionType;

public class AutoTotemClient implements ClientModInitializer {
private boolean replacing = false;

@Override
public void onInitializeClient() {
    ClientTickEvents.END_CLIENT_TICK.register(client -> {
        if (client.player == null || client.interactionManager == null) return;

        if (client.player.getOffHandStack().isEmpty()) {
            int slot = -1;

            for (int i = 0; i < client.player.getInventory().size(); i++) {
                if (client.player.getInventory().getStack(i).isOf(Items.TOTEM_OF_UNDYING)) {
                    slot = i;
                    break;
                }
            }

            if (slot != -1 && !replacing) {
                replacing = true;
                client.player.getInventory().selectedSlot = slot;
            }
        } else {
            replacing = false;
        }
    });
}

}
