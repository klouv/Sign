import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Sign;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.HashMap;
import java.util.Map;

public class PutEvent implements Listener {

    private final Main main;
    private Map<Location, Integer> Lmap = new HashMap<>();

    public PutEvent(Main main) {
        this.main = main;
        this.Lmap = main.Lmap;
    }

    public void onPutEvent(BlockPlaceEvent e) {
        if(!(e.getBlock().getType() == Material.SIGN)) {
            return;
        }
        Sign sign = (Sign) e.getBlock().getState();
        String contents1 = sign.getLine(0);
        String contents2 = sign.getLine(1);
        if(contents1.equals("KlouvSign")) {
            if (contents2.isEmpty()) {
                e.getPlayer().sendMessage("komut id giriniz");
                e.setCancelled(true);
            }
            else {
                //location ve komut id kaydet

            }
        }
    }

}
