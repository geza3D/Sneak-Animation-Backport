package me.geza3d.sab;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = SAB.MODID, version = SAB.VERSION)
public class SAB {

	public static final String MODID = "sab";
	public static final String VERSION = "1.0";
	
	@EventHandler
    public void init(FMLInitializationEvent event)
    {
		MinecraftForge.EVENT_BUS.register(new SneakOverride());
    }
}
