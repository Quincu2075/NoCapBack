package me.noCapBack;

import me.angeschossen.lands.api.LandsIntegration;
import me.angeschossen.lands.api.events.land.block.LandBlockPlaceEvent;
import me.angeschossen.lands.api.land.block.LandBlockType;
import me.angeschossen.lands.api.war.captureflag.CaptureFlag;
import me.angeschossen.lands.api.war.enums.WarTeam;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.Callable;

public final class NoCapBack extends JavaPlugin implements Listener {

    private LandsIntegration landsIntegration;

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getPluginManager().registerEvents(this, this);
        this.landsIntegration = LandsIntegration.of(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onLandBlockPlace(LandBlockPlaceEvent event){
        if (event.getLandBlock().getType() != LandBlockType.CAPTURE_FLAG) return;
        CaptureFlag captureFlag = (CaptureFlag) event.getLandBlock();
        captureFlag.setEvaluateCapturingTeam(new Callable<WarTeam>() {
            @Override
            public WarTeam call() throws Exception {
                return WarTeam.ATTACKER;
            }
        });

    }

}
