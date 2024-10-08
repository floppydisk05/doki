package net.hypr.doki.commands.utils;

import com.freya02.botcommands.api.annotations.CommandMarker;
import com.freya02.botcommands.api.prefixed.CommandEvent;
import com.freya02.botcommands.api.prefixed.TextCommand;
import com.freya02.botcommands.api.prefixed.annotations.Category;
import com.freya02.botcommands.api.prefixed.annotations.Description;
import com.freya02.botcommands.api.prefixed.annotations.JDATextCommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.SelfUser;

import java.awt.*;

@CommandMarker
@Category("Utils")
@Description("Get about info")
public class About extends TextCommand {
    @JDATextCommand(name = "about", aliases = {"doki"})
    public void execute(CommandEvent event) {
        JDA jda = event.getJDA();
        SelfUser selfUser = jda.getSelfUser();
        String jdaVersion = "";
        try {
            Class<?> jdaClass = Class.forName("net.dv8tion.jda.api.JDA");
            Package jdaPackage = jdaClass.getPackage();
            if (jdaPackage != null) {
                // Attempt to get Implementation-Version from the manifest
                String version = jdaPackage.getImplementationVersion();
                jdaVersion = version != null ? version : "Unknown";
            }
        } catch (Exception ex) {
            jdaVersion = "UNKNOWN";
        }
        EmbedBuilder aboutEmbed = new EmbedBuilder()
                .setTitle("About " + selfUser.getName())
                .setThumbnail(selfUser.getAvatarUrl())
                .setDescription("Banned from every deli nationwide")
                .addField("Guilds", String.valueOf(jda.getGuildCache().stream().count()), true)
                .addField("JDA Version", jdaVersion, true)
                .addField("Owner", "fwoppydwisk", true)
                .setColor(new Color(210,138,39));
            event.reply(aboutEmbed.build()).queue();
    }
}
