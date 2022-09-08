package ir.wy.wycore;

import ir.wy.wycore.exception.Exception;

import lombok.Getter;

public final class Versions {

    private static String serverVersion;

    @Getter
    private static Ver current;

    public enum Ver {
        ver1_6_LOWER(6),
        ver1_7(7),
        ver1_8(8),
        ver1_9(9),
        ver1_10(10),
        ver1_11(11),
        ver1_12(12),
        ver1_13(13),
        ver1_14(14),
        ver1_15(15),
        ver1_16(16),
        ver1_17(17),
        ver1_18(18),
        ver1_19(19);

        private final int minorVersionNumber;

        @Getter
        private final boolean tested;

        Ver(int version) {
            this(version, true);
        }

        Ver(int version, boolean tested) {
            this.minorVersionNumber = version;
            this.tested = tested;
        }

        protected static Ver parse(int number) {
            for (final Ver v : values())
                if (v.minorVersionNumber == number)
                    return v;

            throw new Exception("Invalid version number: " + number);
        }

        /**
         * @see java.lang.Enum#toString()
         */
        @Override
        public String toString() {
            return "1." + this.minorVersionNumber;
        }
    }

    private static int compareWith(Ver ver) {
        try {
            return getCurrent().minorVersionNumber - ver.minorVersionNumber;
        } catch (final Throwable t) {
            t.printStackTrace();
            return 0;
        }
    }

    public static boolean equals(Ver ver) {
        return compareWith(ver) == 0;
    }

    public static boolean newer(Ver ver) {
        return compareWith(ver) > 0;
    }

    public static boolean atLeast(Ver ver) {
        return equals(ver) || newer(ver);
    }

    public static String getServerVersion() {
        return serverVersion.equals("craftbukkit") ? "" : serverVersion;
    }

}
