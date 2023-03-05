package eu.maxpi.fiverr.disguiser.commands;

import eu.maxpi.fiverr.disguiser.utils.NMSHelper;
import eu.maxpi.fiverr.disguiser.utils.PluginLoader;
import eu.maxpi.fiverr.disguiser.utils.URLUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SkinCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 0){
            sender.sendMessage(PluginLoader.lang.get("skin-usage"));
            return true;
        }

        try {
            NMSHelper.setSkin((Player)sender, args[0]);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
