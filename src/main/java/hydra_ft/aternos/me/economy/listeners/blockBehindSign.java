package hydra_ft.aternos.me.economy.listeners;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.material.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;


public class blockBehindSign implements Listener {
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            if (event.getClickedBlock().getState() instanceof Sign) {
                Sign sign = (Sign) event.getClickedBlock().getState();
                Block b = event.getClickedBlock();

                Block attachedBlock = b.getRelative(sign.getAttachedFace());
                player.sendMessage(ChatColor.YELLOW + String.valueOf(attachedBlock));
            }
        }
    }
}
