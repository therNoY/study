package leetCode.sort;

import sun.plugin2.message.Message;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    /**
     * 快速排序
     */
    public void kuaiPai(int left, int right, int arr[]) {
        int l = left;
        int r = right;
        int temp = 0;
        int pivot = arr[(left + right) / 2];
        while (l < r) {
            while (arr[l] < pivot) l++;
            while (arr[r] > pivot) r--;

            if (l >= r) break;
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            if (arr[l] == pivot) --r;
            if (arr[r] == pivot) ++l;
        }
        //在中间数的两端生成新的两段 乱序数
        if (l == r) {
            l++;
            r--;
        }
        if (left < r) kuaiPai(left, r, arr);
        if (right > l) kuaiPai(l, right, arr);
    }

    /**
     * 归并排序 (由上往下)
     */
    public void guiBing1(int[] nums) {

    }

    /**
     * 堆排序
     */
    public void dui() {

    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        commonTest();

//        effectiveTest();
    }

    private static void effectiveTest() throws IllegalAccessException, InstantiationException {
        int timer = 10;
        int num = 200000;
        List<String> method = Arrays.asList("冒泡", "选择", "插入", "希尔", "归并", "快速");
        List<Class<? extends Sort>> sorts = Arrays.asList(BubbleSort.class, SelectionSort.class,
                InsertionSort.class, ShellSort.class, MergeSort.class, QuickSort.class);
        List<List<Long>> res = new ArrayList<>(timer);
        long start;
        System.out.println("+==============开始============");
        for (int i = 0; i < timer; i++) {
            int[] nums = Common.randomMake(0, num * 2, num);
            for (int j = 0; j < method.size(); j++) {
                start = System.currentTimeMillis();
                sorts.get(j).newInstance().sort(nums);
                if (res.size() < (j + 1) || res.get(j) == null) {
                    res.add(new ArrayList<>());
                }
                long time = System.currentTimeMillis() - start;
                res.get(j).add(time);
                System.out.println(method.get(j) + "排序的 第" + i + "次排序耗时" + time);
            }
        }

        System.out.println("================结束================");
        System.out.println("排序规模是:" + num + "的数组大小" + timer + "次");

        for (int i = 0; i < res.size(); i++) {
            String name = method.get(i);
            List<Long> time = res.get(i);
            long sum = 0;
            for (int j = 0; j < time.size(); j++) {
                sum += time.get(j);
            }
            System.out.println(name + "排序 总用时：" + sum + "ms 平均耗时：" + sum / (timer + 0.0) + "ms");
        }

    }

    private static void commonTest() {
        for (int i = 0; i < 5; i++) {
            int nums[] = Common.randomMake(0, 20, 15);
//            int nums[] = new int[]{5, 4, 3, 2, 1, 6, 7, 8, 1, 0, 3, 2, 1};
            System.out.println("排序前");
            Common.show(nums);
//            new BubbleSort().sort(nums);
//            new SelectionSort().sort(nums);
            new InsertionSort().mySort(nums);
//            new ShellSort().sort(nums);
//            new HeapSort().sort(nums);
//            new MergeSort().sort(nums);
            System.out.println("排序。。。。。。。");
            Common.show(nums);
        }
    }


}
