package hydra_ft.aternos.me.economy;

import hydra_ft.aternos.me.economy.commands.PayCommannd;
import hydra_ft.aternos.me.economy.commands.BalCommand;
import hydra_ft.aternos.me.economy.listeners.signClick;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginCommand("bal").setExecutor(new BalCommand(this));
        getServer().getPluginCommand("pay").setExecutor(new PayCommannd(this));
        getServer().getPluginManager().registerEvents(new signClick(),this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
