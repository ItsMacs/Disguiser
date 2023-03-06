package eu.maxpi.fiverr.disguiser.commands;

import eu.maxpi.fiverr.disguiser.utils.NMSHelper;
import eu.maxpi.fiverr.disguiser.utils.PluginLoader;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SkinOtherCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.hasPermission("disguiser.skinother")){
            sender.sendMessage(PluginLoader.lang.get("no-permission"));
            return true;
        }

        if(args.length != 2){
            sender.sendMessage(PluginLoader.lang.get("skin-other-usage"));
            return true;
        }

        Player p = Bukkit.getPlayer(args[0]);
        if(p == null){
            sender.sendMessage(PluginLoader.lang.get("no-player"));
            return true;
        }

        try {
            NMSHelper.setSkin(p, args[1]);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
