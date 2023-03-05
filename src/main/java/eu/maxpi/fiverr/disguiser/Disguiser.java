package eu.maxpi.fiverr.disguiser;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Disguiser extends JavaPlugin {

    private static Disguiser instance = null;
    public static Disguiser getInstance() { return Disguiser.instance; }
    private static void setInstance(Disguiser in) { Disguiser.instance = in; }

    @Override
    public void onEnable() {
        setInstance(this);

        loadCommands();
        loadEvents();
        loadTasks();

        Bukkit.getLogger().info("Disguiser by fiverr.com/macslolz was enabled successfully!");
    }

    private void loadEvents(){

    }

    private void loadCommands(){

    }

    private void loadTasks(){

    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("Disguiser by fiverr.com/macslolz was disabled successfully!");
    }
}
