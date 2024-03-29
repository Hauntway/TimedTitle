package me.azarex.timedtitle.scheduler;

import me.azarex.timedtitle.configuration.Configuration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.HashMap;
import java.util.Map;

/**
 * Registers {@link ScheduledTitle} for specific tick's during
 * in-game days
 */
public class TitleRegistry {

    private final Map<Short, ScheduledTitle> titles = new HashMap<>();

    public TitleRegistry(Configuration configuration) {
        final YamlConfiguration yaml = configuration.getYaml().get();

        yaml.getValues(false)
                .forEach((key, $) -> {
                    final String title = yaml.getString(key + ".title");
                    final String subtitle = yaml.getString(key + ".subtitle");

                    final int fadeIn = yaml.getInt(key + ".fadeIn") * 20;
                    final int stay = yaml.getInt(key + ".stay") * 20;
                    final int fadeOut = yaml.getInt(key + ".fadeOut") * 20;
                    final short time = (short) yaml.getInt(key + ".tick-count");

                    titles.put(time, new ScheduledTitle(title, subtitle, fadeIn, stay, fadeOut, key));
                });
    }

    public ScheduledTitle getTitlesForTick(short tick) {
        return titles.get(tick);
    }

}
