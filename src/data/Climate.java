package data;

public enum Climate {
    RAIN_FOREST,
    TROPICAL_SAVANNA,
    STEPPE,
    DESERT;

    public static boolean isPresent(String data) {
        try {
            Climate.valueOf(data);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
