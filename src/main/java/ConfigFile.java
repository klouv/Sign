import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class ConfigFile extends YamlConfiguration {

	private final File configFile;

	public ConfigFile(JavaPlugin plugin, String path) {
		path = path.endsWith(".yml") ? path : path + ".yml";
		this.configFile = new File(plugin.getDataFolder(), path);

		if (!(this.configFile.exists())) {
			this.configFile.getParentFile().mkdirs();
			plugin.saveResource(path, false);
		}
		this.reload();
	}

	public void reload() {
		try {
			super.load(this.configFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void save() {
		try {
			super.save(this.configFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}