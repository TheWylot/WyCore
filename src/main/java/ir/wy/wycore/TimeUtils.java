package ir.wy.wycore;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utils: calculating time from ticks and back.
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TimeUtil {

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    private static final DateFormat DATE_FORMAT_SHORT = new SimpleDateFormat("dd.MM.yyyy HH:mm");

    private static final DateFormat DATE_FORMAT_MONTH = new SimpleDateFormat("dd.MM HH:mm");

    private static final Pattern TOKEN_PATTERN = Pattern.compile("(?:([0-9]+)\\s*y[a-z]*[,\\s]*)?"
                    // Months
                    + "(?:([0-9]+)\\s*mo[a-z]*[,\\s]*)?"

                    // Weeks
                    + "(?:([0-9]+)\\s*w[a-z]*[,\\s]*)?"

                    // Days
                    + "(?:([0-9]+)\\s*d[a-z]*[,\\s]*)?"

                    // Hours
                    + "(?:([0-9]+)\\s*h[a-z]*[,\\s]*)?"

                    // Minutes
                    + "(?:([0-9]+)\\s*m[a-z]*[,\\s]*)?"

                    // Seconds (the "s" may be left out)
                    + "(?:([0-9]+)\\s*(?:s[a-z]*)?)?",

            Pattern.CASE_INSENSITIVE);


    public static long currentTimeSeconds() {
        return System.currentTimeMillis() / 1000;
    }

    public static long currentTimeTicks() {
        return System.currentTimeMillis() / 50;
    }

    public static String getFormattedDate() {
        return getFormattedDate(System.currentTimeMillis());
    }

    public static String getFormattedDate(final long time) {
        return DATE_FORMAT.format(time);
    }

    public static String getFormattedDateShort() {
        return DATE_FORMAT_SHORT.format(System.currentTimeMillis());
    }

    public static String getFormattedDateShort(final long time) {
        return DATE_FORMAT_SHORT.format(time);
    }

    public static String getFormattedDateMonth(final long time) {
        return DATE_FORMAT_MONTH.format(time);
    }

    public static long toTicks(final String humanReadableTime) {
        Valid.checkNotNull(humanReadableTime, "Time is null");

        long seconds = 0L;

        final String[] split = humanReadableTime.split(" ");

        if (split.length < 2)
            throw new IllegalArgumentException("Expected human readable time like '1 second', got '" + humanReadableTime + "' instead");

        for (int i = 1; i < split.length; i++) {
            final String sub = split[i].toLowerCase();
            int multiplier = 0; // e.g 2 hours = 2
            long unit = 0; // e.g hours = 3600
            boolean isTicks = false;

            try {
                multiplier = Integer.parseInt(split[i - 1]);
            } catch (final NumberFormatException e) {
                continue;
            }

            // attempt to match the unit time
            if (sub.startsWith("tick"))
                isTicks = true;

            else if (sub.startsWith("second"))
                unit = 1;

            else if (sub.startsWith("minute"))
                unit = 60;

            else if (sub.startsWith("hour"))
                unit = 3600;

            else if (sub.startsWith("day"))
                unit = 86400;

            else if (sub.startsWith("week"))
                unit = 604800;

            else if (sub.startsWith("month"))
                unit = 2629743;

            else if (sub.startsWith("year"))
                unit = 31556926;

            else if (sub.startsWith("potato"))
                unit = 1337;

            else
                throw new IllegalArgumentException("Must define date type! Example: '1 second' (Got '" + sub + "')");

            seconds += multiplier * (isTicks ? 1 : unit * 20);
        }

        return seconds;
    }

    public static String formatTimeGeneric(final long seconds) {
        final long second = seconds % 60;
        long minute = seconds / 60;
        String hourMsg = "";

        if (minute >= 60) {
            final long hour = seconds / 60 / 60;
            minute %= 60;

            hourMsg = hour + (hour == 1 ? " hour" : " hours") + " ";
        }

        return hourMsg + (minute != 0 ? minute : "") + (minute > 0 ? (minute == 1 ? " minute" : " minutes") + " " : "") + Long.parseLong(String.valueOf(second)) + (Long.parseLong(String.valueOf(second)) == 1 ? " second" : " seconds");

    }

    public static String formatTimeDays(final long seconds) {
        final long minutes = seconds / 60;
        final long hours = minutes / 60;
        final long days = hours / 24;

        return days + " days " + hours % 24 + " hours " + minutes % 60 + " minutes " + seconds % 60 + " seconds";
    }

    public static String formatTimeShort(long seconds) {
        long minutes = seconds / 60;
        long hours = minutes / 60;
        final long days = hours / 24;

        hours = hours % 24;
        minutes = minutes % 60;
        seconds = seconds % 60;

        return (days > 0 ? days + "d " : "") + (hours > 0 ? hours + "h " : "") + (minutes > 0 ? minutes + "m " : "") + seconds + "s";
    }

    public static String formatTimeColon(long seconds) {
        long minutes = seconds / 60;
        long hours = minutes / 60;
        final long days = hours / 24;

        hours = hours % 24;
        minutes = minutes % 60;
        seconds = seconds % 60;

        return (days > 0 ? (days < 10 ? "0" : "") + days + ":" : "") +
                (hours > 0 ? (hours < 10 ? "0" : "") + hours + ":" : "") +
                (minutes > 0 ? (minutes < 10 ? "0" : "") + minutes + ":" : "00") +
                (seconds < 10 ? "0" : "") + seconds;
    }

    public static long parseToken(String text) {
        final Matcher matcher = TOKEN_PATTERN.matcher(text);

        long years = 0, months = 0, weeks = 0, days = 0, hours = 0, minutes = 0, seconds = 0;
        boolean found = false;

        while (matcher.find()) {

            if (matcher.group() == null || matcher.group().isEmpty())
                continue;

            for (int i = 0; i < matcher.groupCount(); i++)
                if (matcher.group(i) != null && !matcher.group(i).isEmpty()) {
                    found = true;

                    break;
                }

            if (found) {
                for (int i = 1; i < 8; i++)
                    if (matcher.group(i) != null && !matcher.group(i).isEmpty()) {
                        final long output = Long.parseLong(matcher.group(i));

                        if (i == 1) {
                            checkLimit("years", output, 10);

                            years = output;
                        } else if (i == 2) {
                            checkLimit("months", output, 12 * 100);

                            months = output;
                        } else if (i == 3) {
                            checkLimit("weeks", output, 4 * 100);

                            weeks = output;
                        } else if (i == 4) {
                            checkLimit("days", output, 31 * 100);

                            days = output;
                        } else if (i == 5) {
                            checkLimit("hours", output, 24 * 100);

                            hours = output;
                        } else if (i == 6) {
                            checkLimit("minutes", output, 60 * 100);

                            minutes = output;
                        } else if (i == 7) {
                            checkLimit("seconds", output, 60 * 100);

                            seconds = output;
                        }
                    }

                break;
            }
        }

        if (!found)
            throw new NumberFormatException("Date not found from: " + text);

        return (seconds + (minutes * 60) + (hours * 3600) + (days * 86400) + (weeks * 7 * 86400) + (months * 30 * 86400) + (years * 365 * 86400)) * 1000;
    }

    private static void checkLimit(String type, long value, int maxLimit) {
        if (value > maxLimit)
            throw new IllegalArgumentException("Value type " + type + " is out of bounds! Max limit: " + maxLimit + ", given: " + value);
    }

    public static String toSQLTimestamp() {
        return toSQLTimestamp(System.currentTimeMillis());
    }

    public static String toSQLTimestamp(long timestamp) {
        final java.util.Date date = new Date(timestamp);

        return new Timestamp(date.getTime()).toString();
    }

    public static long fromSQLTimestamp(String timestamp) {
        return Timestamp.valueOf(timestamp).getTime();
    }
}
