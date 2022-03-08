package elias.vwa.eventHandler;

import elias.vwa.Bot;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EventHandler extends ListenerAdapter {

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        for (Guild guild : Bot.jda.getGuilds()) {
            guild.upsertCommand("kontoalter", "Zeigt an, wann ein Discord Konto erstellt wurde").
                    addOption(OptionType.USER, "konto", "Konto von dem das Erstellungsdatum angezeigt werden soll");
        }
    }

    @Override
    public void onGuildJoin(@NotNull GuildJoinEvent event) {
        event.getGuild().upsertCommand("kontoalter", "Zeigt an, wann ein Discord Konto erstellt wurde").
                addOption(OptionType.USER, "konto", "Konto von dem das Erstellungsdatum angezeigt werden soll");
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (!event.getChannel().getType().isGuild())
            return;
        if (event.getName().equalsIgnoreCase("kontoalter")) {
            User user;
            try {
                user = event.getOption("konto").getAsUser();
            } catch (NullPointerException exception) {
                user = event.getUser();
            }

            SimpleDateFormat format = new SimpleDateFormat("dd.MM. yyyy 'um' HH:mm:ss");
            event.reply("Das Konto von " + user.getAsMention() + " wurde am " + format.format(Date.from(user.getTimeCreated().toInstant())) + " erstellt!").queue();
        }
    }
}

