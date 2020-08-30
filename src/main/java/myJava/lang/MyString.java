package myJava.lang;

import java.io.Serializable;

/**
 * @Author mh32736
 * @Date 2020/8/27 11:19
 */
public class MyString implements Serializable, Comparable<java.lang.String>, CharSequence {

    private final char value[];

    /**
     * Cache the hash code for the string
     */
    private int hash; // Default to 0

    /**
     * use serialVersionUID from JDK 1.0.2 for interoperability
     */
    private static final long serialVersionUID = -6849794470754667710L;

    public MyString() {
        this.value = "".toCharArray();
    }

    public MyString(String string) {
        this.value = string.toCharArray();
    }

    @Override
    public int length() {
        return 0;
    }

    @Override
    public char charAt(int index) {
        return value[index];
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return null;
    }

    @Override
    public int compareTo(java.lang.String o) {
        return 0;
    }

    public int indexOf(String str, int fromIndex) {
        return indexOf(value, 0, value.length, str.toCharArray(), 0, str.length(), fromIndex);
    }

    public int indexOf(String str) {
        return indexOf(str, 0);
    }

    static int indexOf(char[] source, int sourceOffset, int sourceCount,
        char[] target, int targetOffset, int targetCount,
        int fromIndex) {
        if (fromIndex >= sourceCount) {
            return (targetCount == 0 ? sourceCount : -1);
        }
        if (fromIndex < 0) {
            fromIndex = 0;
        }
        if (targetCount == 0) {
            return fromIndex;
        }

        char first = target[targetOffset];
        int max = sourceOffset + (sourceCount - targetCount);

        for (int i = sourceOffset + fromIndex; i <= max; i++) {
            /* Look for first character. */
            if (source[i] != first) {
                while (++i <= max && source[i] != first) {
                    ;
                }
            }

            /* Found first character, now look at the rest of v2 */
            if (i <= max) {
                int j = i + 1;
                int end = j + targetCount - 1;
                for (int k = targetOffset + 1; j < end && source[j]
                    == target[k]; j++, k++) {
                    ;
                }

                if (j == end) {
                    /* Found whole string. */
                    return i - sourceOffset;
                }
            }
        }
        return -1;
    }

}
