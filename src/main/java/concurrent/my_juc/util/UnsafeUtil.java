package concurrent.my_juc.util;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeUtil {

    public static Unsafe getUnsafe() {
        try {
            Field unsafeStaticField =
                    Unsafe.class.getDeclaredField("theUnsafe");
            unsafeStaticField.setAccessible(true);
            return (Unsafe) unsafeStaticField.get(Unsafe.class);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
