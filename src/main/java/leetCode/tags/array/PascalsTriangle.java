package leetCode.tags.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 118. 杨辉三角
 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 * <p>
 * 示例:
 * <p>
 * 输入: 5
 * 输出:
 * [
 * [1],
 * [1,1],
 * [1,2,1],
 * [1,3,3,1],
 * [1,4,6,4,1]
 * ]
 */
public class PascalsTriangle {

    public static void main(String[] args) {
        PascalsTriangle pascalsTriangle = new PascalsTriangle();
//        List<List<Integer>> res = pascalsTriangle.generate(5);
        List<Integer> res = pascalsTriangle.getRow(5);
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>(numRows);
        List<Integer> lastList = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> resI = new ArrayList<>(i + 1);
            if (i == 0) {
                resI.add(1);
            } else {
                for (int j = 0; j < i + 1; j++) {
                    int n1 = j - 1 < 0 ? 0 : lastList.get(j - 1);
                    int n2 = j == i ? 0 : lastList.get(j);
                    resI.add(n1 + n2);
                }
            }
            res.add(resI);
        }
        return res;
    }

    public List<Integer> getRow(int rowIndex) {
        Integer[] lastArray = new Integer[rowIndex + 1];
        Integer[] newArray = new Integer[rowIndex + 1];
        for (int i = 0; i < rowIndex + 1; i++) {
            if (i == 0) {
                newArray[i] = 1;
            } else {
                for (int j = 0; j < i + 1; j++) {
                    int n1 = j - 1 < 0 ? 0 : lastArray[j - 1];
                    int n2 = j == i ? 0 : lastArray[j];
                    newArray[j] =  n1 + n2;
                }
            }
            System.arraycopy(newArray, 0, lastArray, 0, i + 1);;
        }
        return Arrays.asList(lastArray);
    }
}
