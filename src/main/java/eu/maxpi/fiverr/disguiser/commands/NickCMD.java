package eu.maxpi.fiverr.disguiser.commands;

import eu.maxpi.fiverr.disguiser.utils.ColorTranslator;
import eu.maxpi.fiverr.disguiser.utils.NMSHelper;
import eu.maxpi.fiverr.disguiser.utils.PluginLoader;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NickCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 0){
            sender.sendMessage(PluginLoader.lang.get("nick-usage"));
            return true;
        }

        try {
            NMSHelper.setName((Player)sender, ColorTranslator.translate(args[0]));
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
