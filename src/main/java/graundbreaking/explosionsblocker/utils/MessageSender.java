package graundbreaking.explosionsblocker.utils;

import it.unimi.dsi.fastutil.chars.CharOpenHashSet;
import it.unimi.dsi.fastutil.chars.CharSet;
import org.bukkit.command.CommandSender;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class MessageSender {

    private static final Pattern HEX_PATTERN = Pattern.compile("&#([a-fA-F\\d]{6})");
    private static final char COLOR_CHAR = '§';
    private final boolean IS_ABOVE_16;

    public MessageSender(final boolean isAbove16) {
        this.IS_ABOVE_16 = isAbove16;
    }

    private static final CharSet CODES = new CharOpenHashSet(new char[]{
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f',
            'A', 'B', 'C', 'D', 'E', 'F',
            'k', 'l', 'm', 'n', 'o', 'r', 'x',
            'K', 'L', 'M', 'N', 'O', 'R', 'X'
    });

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

    public String translateAlternateColorCodes(char altColorChar, String textToTranslate) {
        char[] b = textToTranslate.toCharArray();

        for (int i = 0, length = b.length - 1; i < length; ++i) {
            if (b[i] == altColorChar && CODES.contains(b[i + 1])) {
                b[i++] = '§';
                b[i] = Character.toLowerCase(b[i]);
            }
        }

        return new String(b);
    }

    public void sendMessage(CommandSender sender, String message) {
        if (message.isEmpty() || message.isBlank()) {
            return;
        }

        sender.sendMessage(colorize(message));
    }
}
