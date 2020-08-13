package leetCode.self;

/**
 * String 去除中间的点
 */
public class StringReplaceD {

    public static void main(String[] args) {
        String s = "aaa.aaa.wav";

        System.out.println(s.replaceAll("\\.", "_").replace("_wav", ".wav"));
    }

}
