package eu.maxpi.fiverr.disguiser.events;

import eu.maxpi.fiverr.disguiser.utils.NMSHelper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class onPlayerQuit implements Listener {

    @EventHandler
    public void playerQuit(PlayerQuitEvent event) throws NoSuchFieldException, IllegalAccessException {
        //NMSHelper.setProfileName(event.getPlayer(), NMSHelper.realNames.getOrDefault(event.getPlayer(), event.getPlayer().getName()));
    }

}
