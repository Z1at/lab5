package data;

public enum StandardOfLiving {
    VERY_HIGH,
    LOW,
    VERY_LOW,
    NIGHTMARE;

    public static boolean isPresent(String data) {
        try {
            StandardOfLiving.valueOf(data);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
