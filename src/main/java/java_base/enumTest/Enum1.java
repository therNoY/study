package java_base.enumTest;

public enum Enum1 {
    BLUE("blue"),GREEN("green");

    private final String key;

    Enum1(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public static Enum1 getByKey(String value) {
        for (Enum1 app : values()) {
            if (app.getKey().equals(value)) {
                return app;
            }
        }
        return null;
    }
}
