package me.geza3d.sab;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;

public class SneakOverride {

	float defHeight = 1.62F;
	float noSneakHeight = 1.70F;
	float sneakHeight = 1.35f;
	
	boolean sneakPressed = false;
	float eyeheightPrev = 0;
	float delta = 0;
	
	Minecraft mc = Minecraft.getMinecraft();
	
	@SubscribeEvent
	public void onPlayerUpdate(ClientTickEvent e) {
		if(mc.thePlayer != null && e.phase == Phase.END) {
			if(mc.gameSettings.keyBindSneak.isKeyDown()) {
				delta = (sneakHeight - mc.thePlayer.eyeHeight) * 0.5f;
			} else {
				delta = (defHeight - mc.thePlayer.eyeHeight) * 0.5f;
			}
			eyeheightPrev = mc.thePlayer.eyeHeight;
		}
	}
	
	@SubscribeEvent
	public void onPlayerUpdate(RenderHandEvent e) {
		if(mc.thePlayer != null) {
			if(mc.gameSettings.keyBindSneak.isKeyDown()) {
				mc.thePlayer.eyeHeight = eyeheightPrev + delta * e.partialTicks;
			} else {
				mc.thePlayer.eyeHeight = eyeheightPrev + delta * e.partialTicks;
			}
		}
	}
	
	@SubscribeEvent
	public void onInput(KeyInputEvent e) {
		if(mc.gameSettings.keyBindSneak.isPressed() && !sneakPressed) {
			mc.thePlayer.eyeHeight = noSneakHeight;
			eyeheightPrev = mc.thePlayer.eyeHeight;
			sneakPressed = true;
		}
		if(!mc.gameSettings.keyBindSneak.isKeyDown() && sneakPressed) {
			mc.thePlayer.eyeHeight = sneakHeight;
			eyeheightPrev = mc.thePlayer.eyeHeight;
			sneakPressed = false;
		}
	}
}
