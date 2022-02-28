package data;

public enum Government {
    CORPORATOCRACY,
    MATRIARCHY,
    OLIGARCHY,
    TIMOCRACY;

    public static boolean isPresent(String data) {
        try {
            Government.valueOf(data);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
