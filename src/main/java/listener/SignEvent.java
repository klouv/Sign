package listener;

import KlouvSign.Main;
import file.ConfigFile;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashMap;
import java.util.Map;

public class SignEvent implements Listener {

    private Main main;
    private Map<String, String> command = new HashMap<>();
    private Map<Location, String> locations = new HashMap<>();

    public SignEvent(Main main) {
        this.main = main;
        this.locations = main.locations;
        this.command = main.command;
    }

    @EventHandler
    public void signPlaceEvent(SignChangeEvent event) {

        Material type = event.getBlock().getType();
        if (type != Material.SIGN_POST && type != Material.WALL_SIGN) return;

        String[] lines = event.getLines();
        Player player = event.getPlayer();

        event.getPlayer().sendMessage("test 0");
        if (!lines[0].equalsIgnoreCase("klouvSign")) return;
        event.getPlayer().sendMessage("test 1");;
        if (lines[1].isEmpty()) {
            player.sendMessage("lütfen komut id giriniz(2.satır)");
            event.getBlock().setType(Material.AIR);
            return;
        }
        if (lines[2].isEmpty()) {
            player.sendMessage("lütfen başlık giriniz(3.satır)");
            event.getBlock().setType(Material.AIR);
            return;
        }
        Location loc = event.getBlock().getLocation();
        locations.put(loc, lines[1]);

        String line = lines[2];
        event.setLine(0, line);
        event.setLine(1, "");
        event.setLine(2, "");
        player.sendMessage("başarıyla kayedildi");
    }

    @EventHandler
    public void onClickEvent(PlayerInteractEvent event) {
        Block type = event.getClickedBlock();
        if (type == null || type.getType() == Material.AIR) return;
        if (!locations.containsKey(event.getClickedBlock().getLocation())) return;
        String id = locations.get(event.getClickedBlock().getLocation());
        if (!command.containsKey(id)) {
            event.getPlayer().sendMessage("CommandConfig " + id +"içermiyor");
            return;
        }

        //commandConfig deki kodu burada çalıştır
        String komut = command.get(id);
        Player player = event.getPlayer();
        //consol sender naısl kullanılıyoır

        player.sendMessage("test 2");
    }

}

