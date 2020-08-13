package leetCode.tags.binary_search;

/**
 * 278. 第一个错误的版本
 * 你是产品经理，目前正在带领一个团队开发新的产品。不幸的是，你的产品的最新版本没有通过质量检测。由于每个版本都是基于之前的版本开发的，所以错误的版本之后的所有版本都是错的。
 * <p>
 * 假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。
 * <p>
 * 你可以通过调用 bool isBadVersion(version) 接口来判断版本号 version 是否在单元测试中出错。实现一个函数来查找第一个错误的版本。你应该尽量减少对调用 API 的次数。
 * <p>
 * 示例:
 * <p>
 * 给定 n = 5，并且 version = 4 是第一个错误的版本。
 * <p>
 * 调用 isBadVersion(3) -> false
 * 调用 isBadVersion(5) -> true
 * 调用 isBadVersion(4) -> true
 * <p>
 * 所以，4 是第一个错误的版本。
 * 通过次数53,373提交次数135,411
 */
public class FirstBadVersion {


    public static void main(String[] args) {
        System.out.println(new FirstBadVersion().firstBadVersion(2126753390));

    }

    public int firstBadVersion(int n) {
        int left = 1;
        int right = n;

        while (left <= right) {
            if (left == right) {
                return isBadVersion(left) ? left : left + 1;
            }
            // 这里市重点
            int mid = left + (right - left) / 2;
            if (mid < 0) {
                System.out.println("?");
            }
            if (!isBadVersion(mid)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }

        }
        if (left > right) {
            if (isBadVersion(left))
                return left;
            if (isBadVersion(right))
                return right;
        }
        return -1;
    }


    boolean isBadVersion(int version) {
        return version >= 1702766719 ? true : false;
    }
}
