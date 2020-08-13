package leetCode.codeNum;


/**
 * 给定⼀个⾮空字符串 s 和⼀个缩写 abbr，请校验它们是否匹配。
 * 假设字符串中只包含⼩写字⺟，缩写中只包含⼩写字⺟和数字。缩写中的数字
 * 表示其缩略的字符数；连续多位数字表示⼀个多位数。
 * 例如，字符串 “word” 的缩写有且仅有以下这些：[“word", "1ord", "w1rd",
 * "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d",
 * "w3", “4"]。 例 1：输⼊：s = “internationalization"，abbr = "i12iz4n"
 *  返回：true
 *  解释：abbr 中的 12 表示有⼗⼆个字符被缩略了。
 * 例 2：输⼊：s = “apple"，abbr = “a2e"
 *  返回：false
 */
public class Code2 {


    public static void main(String[] args) {
//        String s = "internationalization", abbr = "i12iz4n";
        String s = "apple", abbr = "a2e";
        valid(s, abbr);
    }

    static boolean valid(String word, String abbr){

        Integer abbrIndex = 0;
        Integer wordIndex = 0;
        char[] abbrChars = abbr.toCharArray();
        char[] wordChars = word.toCharArray();

        Object e;
        do {
            e =  getNextElement(abbrChars, abbrIndex);
            if (e instanceof Character) {
                // 如果是字符就比较相应位置的字符是否相同
                if (wordChars[wordIndex] != (char)e) {
                    return false;
                }
                wordIndex ++;
                abbrIndex ++;
            }else if (e instanceof Integer){
                // 如果是数字 就判断原来的字符长度是否够
                int i = (int) e;
                if (wordIndex + i > wordChars.length){
                    return false;
                }
                wordIndex += i;
                abbrIndex += e.toString().length();
            }
        }while (e != null);


       return true;

    }

    /**
     * 从缩写的字中取出一个元素 可能是数组 也可能是一个字符
     * @param abbrChars
     * @param abbrIndex
     * @return
     */
    private static Object getNextElement(char[] abbrChars, Integer abbrIndex) {
        char c;
        StringBuffer number = new StringBuffer();
        for (; abbrIndex < abbrChars.length; abbrIndex++) {
            c = abbrChars[abbrIndex];
            if (c >= 48 && c <= 57) {
                number.append(c);
            }else {
                if (number.length() > 0){
                    return Integer.valueOf(number.toString());
                }
                return Character.valueOf(c);
            }
        }

        if (number.length() > 0)
            return Integer.valueOf(number.toString());

        return null;
    }

}
