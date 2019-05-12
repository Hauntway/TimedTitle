package me.azarex.timedtitle.user;

import me.azarex.timedtitle.configuration.Configuration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class UserSessionManager implements Listener {

    private final UserLoader userLoader;

    public UserSessionManager(Configuration configuration) {

        userLoader = new UserLoader(configuration);
    }

    @EventHandler
    private void onJoin(PlayerJoinEvent event) {
        userLoader.load(event.getPlayer());
        System.out.println("World tick: " + event.getPlayer().getWorld().getTime());
        System.out.println("World full-tick: " + event.getPlayer().getWorld().getFullTime());
    }

    @EventHandler
    private void onLeave(PlayerQuitEvent event) {
        userLoader.unload(event.getPlayer());
        System.out.println("World tick: " + event.getPlayer().getWorld().getTime());
        System.out.println("World full-tick: " + event.getPlayer().getWorld().getFullTime());
    }

}