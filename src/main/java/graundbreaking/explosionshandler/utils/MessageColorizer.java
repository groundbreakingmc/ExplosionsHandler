package graundbreaking.explosionshandler.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.bukkit.ChatColor.translateAlternateColorCodes;

public final class MessageColorizer {

    private static final Pattern HEX_PATTERN = Pattern.compile("&#([a-fA-F\\d]{6})");
    private static final char COLOR_CHAR = '§';
    private final boolean IS_ABOVE_16;

    public MessageColorizer(final boolean isAbove16) {
        this.IS_ABOVE_16 = isAbove16;
    }

    public String colorize(String message) {
        if (IS_ABOVE_16) {
            Matcher matcher = HEX_PATTERN.matcher(message);
            StringBuilder builder = new StringBuilder(message.length() + 32);
            while (matcher.find()) {
                String group = matcher.group(1);
                matcher.appendReplacement(builder,
                        COLOR_CHAR + "x" +
                                COLOR_CHAR + group.charAt(0) +
                                COLOR_CHAR + group.charAt(1) +
                                COLOR_CHAR + group.charAt(2) +
                                COLOR_CHAR + group.charAt(3) +
                                COLOR_CHAR + group.charAt(4) +
                                COLOR_CHAR + group.charAt(5));
            }

            message = matcher.appendTail(builder).toString();
        }

        return translateAlternateColorCodes('&', message);
    }
}
