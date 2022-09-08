package ir.wy.wycore;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.bukkit.util.Vector;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * Utils: Mathematical Class - Copywrite of some codes (MineAcademy)
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MathUtil {

    private final static DecimalFormat oneDigitFormat = new DecimalFormat("#.#");
    private final static DecimalFormat twoDigitsFormat = new DecimalFormat("#.##");
    private final static DecimalFormat threeDigitsFormat = new DecimalFormat("#.###");
    private final static DecimalFormat fiveDigitsFormat = new DecimalFormat("#.#####");
    private final static NavigableMap<Integer, String> romanNumbers = new TreeMap<>();

    // Load the roman numbers
    static {
        romanNumbers.put(1000, "M");
        romanNumbers.put(900, "CM");
        romanNumbers.put(500, "D");
        romanNumbers.put(400, "CD");
        romanNumbers.put(100, "C");
        romanNumbers.put(90, "XC");
        romanNumbers.put(50, "L");
        romanNumbers.put(40, "XL");
        romanNumbers.put(10, "X");
        romanNumbers.put(9, "IX");
        romanNumbers.put(5, "V");
        romanNumbers.put(4, "IV");
        romanNumbers.put(1, "I");
    }

    public static String toRoman(final int number) {
        if (number == 0)
            return "0"; // Actually, Romans did not know zero lol

        final int literal = romanNumbers.floorKey(number);

        if (number == literal)
            return romanNumbers.get(number);

        return romanNumbers.get(literal) + toRoman(number - literal);
    }

    public static int max(int... numbers) {
        return Arrays.stream(numbers).max().getAsInt();
    }

    public static long floor(final double d1) {
        final long i = (long) d1;

        return d1 >= i ? i : i - 1;
    }

    public static long ceiling(final double f1) {
        final long i = (long) f1;

        return f1 >= i ? i : i - 1;
    }

    public static double range(final double value, final double min, final double max) {
        return Math.min(Math.max(value, min), max);
    }

    public static int range(final int value, final int min, final int max) {
        return Math.min(Math.max(value, min), max);
    }

    public static double atLeast(final double value, final double min) {
        return value > min ? value : min;
    }

    public static int atLeast(final int value, final int min) {
        return value > min ? value : min;
    }

    public static int increase(final int number, final double percent) {
        final double myNumber = number;
        final double percentage = myNumber / 100 * percent;

        return (int) Math.round(myNumber + percentage);
    }

    public static double increase(final double number, final double percent) {
        final double percentage = number / 100 * percent;

        return number + percentage;
    }

    public static int percent(final double number, final double maximum) {
        return (int) (number / maximum * 100);
    }

    public static double average(final Collection<Double> values) {
        return average(values.toArray(new Double[values.size()]));
    }

    public static double average(final Double... values) {
        values.checkBoolean(values.length > 0, "No values given!");

        double sum = 0;

        for (final double val : values)
            sum += val;

        return formatTwoDigitsD(sum / values.length);
    }

    public static Vector rotateAroundAxisX(Vector vector, double angle) {
        angle = Math.toRadians(angle);

        final double cos = Math.cos(angle);
        final double sin = Math.sin(angle);
        final double y = vector.getY() * cos - vector.getZ() * sin;
        final double z = vector.getY() * sin + vector.getZ() * cos;

        return vector.setY(y).setZ(z);
    }

    public static Vector rotateAroundAxisY(Vector v, double angle) {
        angle = -angle;
        angle = Math.toRadians(angle);

        final double cos = Math.cos(angle);
        final double sin = Math.sin(angle);
        final double x = v.getX() * cos + v.getZ() * sin;
        final double z = v.getX() * -sin + v.getZ() * cos;

        return v.setX(x).setZ(z);
    }

    public static Vector rotateAroundAxisZ(Vector v, double angle) {
        angle = Math.toRadians(angle);

        final double cos = Math.cos(angle);
        final double sin = Math.sin(angle);
        final double x = v.getX() * cos - v.getY() * sin;
        final double y = v.getX() * sin + v.getY() * cos;

        return v.setX(x).setY(y);
    }

    public static String formatOneDigit(final double value) {
        return oneDigitFormat.format(value).replace(",", ".");
    }

    public static double formatOneDigitD(final double value) {
        Valid.checkBoolean(!Double.isNaN(value), "Value must not be NaN");

        return Double.parseDouble(oneDigitFormat.format(value).replace(",", "."));
    }

    /**
     * Formats the given number into two digits
     *
     * @param value
     * @return
     */
    public static String formatTwoDigits(final double value) {
        return twoDigitsFormat.format(value).replace(",", ".");
    }

    /**
     * Formats the given number into two digits
     *
     * @param value
     * @return
     */
    public static double formatTwoDigitsD(final double value) {
        Valid.checkBoolean(!Double.isNaN(value), "Value must not be NaN");

        return Double.parseDouble(twoDigitsFormat.format(value).replace(",", "."));
    }

    /**
     * Formats the given number into three digits
     *
     * @param value
     * @return
     */
    public static String formatThreeDigits(final double value) {
        return threeDigitsFormat.format(value).replace(",", ".");
    }

    /**
     * Formats the given number into three digits
     *
     * @param value
     * @return
     */
    public static double formatThreeDigitsD(final double value) {
        Valid.checkBoolean(!Double.isNaN(value), "Value must not be NaN");

        return Double.parseDouble(threeDigitsFormat.format(value).replace(",", "."));
    }

    /**
     * Formats the given number into five digits
     *
     * @param value
     * @return
     */
    public static String formatFiveDigits(final double value) {
        return fiveDigitsFormat.format(value).replace(",", ".");
    }

    /**
     * Formats the given number into five digits
     *
     * @param value
     * @return
     */
    public static double formatFiveDigitsD(final double value) {
        Valid.checkBoolean(!Double.isNaN(value), "Value must not be NaN");

        return Double.parseDouble(fiveDigitsFormat.format(value).replace(",", "."));
    }

    public static double calculate(final String expression) {
        class Parser {
            int pos = -1, c;

            void eatChar() {
                this.c = ++this.pos < expression.length() ? expression.charAt(this.pos) : -1;
            }

            void eatSpace() {
                while (Character.isWhitespace(this.c))
                    this.eatChar();
            }

            double parse() {
                this.eatChar();

                final double v = this.parseExpression();

                if (this.c != -1)
                    throw new CalculatorException("Unexpected: " + (char) this.c);

                return v;
            }


            double parseExpression() {
                double v = this.parseTerm();

                for (; ; ) {
                    this.eatSpace();

                    if (this.c == '+') {
                        this.eatChar();
                        v += this.parseTerm();
                    } else if (this.c == '-') {
                        this.eatChar();
                        v -= this.parseTerm();
                    } else
                        return v;

                }
            }

            double parseTerm() {
                double v = this.parseFactor();

                for (; ; ) {
                    this.eatSpace();

                    if (this.c == '/') {
                        this.eatChar();
                        v /= this.parseFactor();
                    } else if (this.c == '*' || this.c == '(') {
                        if (this.c == '*')
                            this.eatChar();
                        v *= this.parseFactor();
                    } else
                        return v;
                }
            }

            double parseFactor() {
                double v;
                boolean negate = false;

                this.eatSpace();

                if (this.c == '+' || this.c == '-') {
                    negate = this.c == '-';
                    this.eatChar();
                    this.eatSpace();
                }

                if (this.c == '(') {
                    this.eatChar();
                    v = this.parseExpression();
                    if (this.c == ')')
                        this.eatChar();
                } else {
                    final StringBuilder sb = new StringBuilder();

                    while (this.c >= '0' && this.c <= '9' || this.c == '.') {
                        sb.append((char) this.c);
                        this.eatChar();
                    }

                    if (sb.length() == 0)
                        throw new CalculatorException("Unexpected: " + (char) this.c);

                    v = Double.parseDouble(sb.toString());
                }
                this.eatSpace();
                if (this.c == '^') {
                    this.eatChar();
                    v = Math.pow(v, this.parseFactor());
                }
                if (negate)
                    v = -v;
                return v;
            }
        }
        return new Parser().parse();
    }

    public static final class CalculatorException extends RuntimeException {
        private static final long serialVersionUID = 1L;

        public CalculatorException(final String message) {
            super(message);
        }
    }
}
