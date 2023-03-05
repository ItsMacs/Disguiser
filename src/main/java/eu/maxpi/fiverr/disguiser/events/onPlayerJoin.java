package eu.maxpi.fiverr.disguiser.events;

import eu.maxpi.fiverr.disguiser.utils.NMSHelper;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class onPlayerJoin implements Listener {

    @EventHandler
    public void playerJoin(PlayerJoinEvent event){
        Bukkit.getOnlinePlayers().forEach(p -> {
            if(p == event.getPlayer()) return;

            try {
                NMSHelper.setName(p, NMSHelper.fakeNames.getOrDefault(p, p.getName()), event.getPlayer());
            } catch (IllegalAccessException | NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
