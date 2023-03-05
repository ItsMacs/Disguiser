package eu.maxpi.fiverr.disguiser.events;

import com.mojang.authlib.GameProfile;
import eu.maxpi.fiverr.disguiser.Disguiser;
import eu.maxpi.fiverr.disguiser.utils.NMSHelper;
import net.minecraft.server.level.EntityPlayer;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.lang.reflect.Field;

public class onChat implements Listener {

    /*@EventHandler (priority = EventPriority.LOWEST, ignoreCancelled = false)
    public void playerChat(AsyncPlayerChatEvent event) throws NoSuchFieldException, IllegalAccessException {
        String currentName = event.getPlayer().getName();

        NMSHelper.setProfileName(event.getPlayer(), NMSHelper.realNames.getOrDefault(event.getPlayer(), event.getPlayer().getName()));
        System.out.println("CurrentName: " + currentName + ", RealName:" + NMSHelper.realNames.getOrDefault(event.getPlayer(), event.getPlayer().getName()));
        Bukkit.getScheduler().scheduleSyncDelayedTask(Disguiser.getInstance(), () -> {
            if(event.getPlayer() == null) return;
            try {
                NMSHelper.setProfileName(event.getPlayer(), currentName);
            } catch (IllegalAccessException | NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
        }, 3L);
    }*/
}
