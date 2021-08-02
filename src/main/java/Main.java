import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class Main extends JavaPlugin {

    private ConfigFile commandConfig;
    private ConfigFile Lconfig;
    public Map<Location, Integer> Lmap = new HashMap<>();

    @Override
    public void onEnable() {
        Lconfig = new ConfigFile(this, "Lconfig");
        commandConfig = new ConfigFile(this, "commandConfig");

        Bukkit.getPluginManager().registerEvents(new PutEvent(this), this);
        //location çek

    }


    @Override
    public void onDisable() {
        //location kaydet

    }


}
