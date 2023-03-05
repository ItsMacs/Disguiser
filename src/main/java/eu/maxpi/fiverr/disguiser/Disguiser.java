package eu.maxpi.fiverr.disguiser;

import eu.maxpi.fiverr.disguiser.commands.NickCMD;
import eu.maxpi.fiverr.disguiser.commands.SkinCMD;
import eu.maxpi.fiverr.disguiser.events.onChat;
import eu.maxpi.fiverr.disguiser.events.onPlayerJoin;
import eu.maxpi.fiverr.disguiser.events.onPlayerQuit;
import eu.maxpi.fiverr.disguiser.utils.PluginLoader;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Disguiser extends JavaPlugin {

    private static Disguiser instance = null;
    public static Disguiser getInstance() { return Disguiser.instance; }
    private static void setInstance(Disguiser in) { Disguiser.instance = in; }

    @Override
    public void onEnable() {
        setInstance(this);

        PluginLoader.load();

        loadCommands();
        loadEvents();
        loadTasks();

        Bukkit.getLogger().info("Disguiser by fiverr.com/macslolz was enabled successfully!");
    }

    private void loadEvents(){
        Bukkit.getPluginManager().registerEvents(new onChat(), this);
        Bukkit.getPluginManager().registerEvents(new onPlayerJoin(), this);
        Bukkit.getPluginManager().registerEvents(new onPlayerQuit(), this);
    }

    private void loadCommands(){
        Objects.requireNonNull(getCommand("nick")).setExecutor(new NickCMD());
        Objects.requireNonNull(getCommand("skin")).setExecutor(new SkinCMD());
    }

    private void loadTasks(){

    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("Disguiser by fiverr.com/macslolz was disabled successfully!");
    }
}
