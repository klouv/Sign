package KlouvSign;

import file.ConfigFile;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class Main extends JavaPlugin {

    public final Map<Location, String> locations = new HashMap<>();
    public final Map<String, String> command = new HashMap<>();
    private ConfigFile config;
    private ConfigFile commandConfig;

    @Override
    public void onEnable() {
        config = new ConfigFile(this, "config");
        commandConfig = new ConfigFile(this, "commandConfig");


        //---------------CommandConfig-------------------------------------------------------------
        for (String id : commandConfig.getKeys(false)) {
            if (id == null) {
                getLogger().warning("command id is null");
                continue;
            }
            if (commandConfig.getString(id) == null) {
                getLogger().warning("command is null, command id: " + id + "");
                continue;
            }
            command.put(id, commandConfig.getString(id));
        }
        //-----------------------------------------------------------------------------------------

        //------------------Config-----------------------------------------------------------------
        for (String location : config.getKeys(false)) {
            String[] split = location.split(":");
            String worldName = split[0];
            World world = getServer().getWorld(worldName);
            if (world == null) {
                getLogger().warning("World null! " + worldName);
                continue;
            }
            double x = Double.parseDouble(split[1]);
            double y = Double.parseDouble(split[2]);
            double z = Double.parseDouble(split[3]);

            locations.put(new Location(world, x, y, z), config.getString(location));
        }
        //-----------------------------------------------------------------------------------------

        getServer().getPluginManager().registerEvents(new listener.SignEvent(this), this);
    }


    @Override
    public void onDisable() {
        for (String key : config.getKeys(false)) config.set(key, null);

        for (Map.Entry<Location, String> entry : locations.entrySet()) {
            config.set(String.format("%s:%s:%s:%s", entry.getKey().getWorld().getName(), entry.getKey().getX(), entry.getKey().getY(), entry.getKey().getZ()), entry.getValue());
        }
        config.save();
    }


}