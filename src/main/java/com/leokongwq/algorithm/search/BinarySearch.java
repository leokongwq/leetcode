package com.leokongwq.algorithm.search;

/**
 * Created with IntelliJ IDEA.
 *
 * @author jiexiu
 * Date: 17/1/20
 * Time: 上午10:56
 * Email:leokongwq@gmail.com
 */
public class BinarySearch {

    /**
     * 二分法查找数组中特定元素, 查询到指定元素返回元素下标,否则返回-1
     */
    private static int binarySearch(int[] arr, int target) {
        if (null == arr || arr.length == 0) {
            return -1;
        }
        int low = 0;
        int high = arr.length - 1;
        //必须是 <= , 因为会查找到数组第一个和最后一个元素
        while (low <= high) {
            //防止溢出， 不能使用 (low + high) / 2 这种计算方法
            int mid = low + (high - low) / 2;
            if (arr[mid] > target) {
                high = mid - 1;
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 查询第一个等于 target 的下标
     */
    private static int binarySearchFindFirst(int[] arr, int target) {
        if (null == arr || arr.length == 0) {
            return -1;
        }
        int low = 0;
        int high = arr.length - 1;
        //必须是 <= , 因为会查找到数组第一个和最后一个元素
        while (low <= high) {
            //防止溢出， 不能使用 (low + high) / 2 这种计算方法
            int mid = low + (high - low) / 2;
            if (arr[mid] > target) {
                high = mid - 1;
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                //前一个元素不是目标元素，则 mid 就是第一个查找到的元素下标
                if (arr[mid - 1] != target) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 查询最后一个等于 target 的下标
     */
    private static int binarySearchFindLast(int[] arr, int target) {
        if (null == arr || arr.length == 0) {
            return -1;
        }
        int low = 0;
        int high = arr.length - 1;
        //必须是 <= , 因为会查找到数组第一个和最后一个元素
        while (low <= high) {
            //防止溢出， 不能使用 (low + high) / 2 这种计算方法
            int mid = low + (high - low) / 2;
            if (arr[mid] > target) {
                high = mid - 1;
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                //前一个元素不是目标元素，则 mid 就是第一个查找到的元素下标
                if (arr[mid + 1] != target) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            }
        }
        return -1;
    }

    /**
     * 查询第一个大于等于 target 的下标
     */
    private static int binarySearchFindFistGreatThanOrEq(int[] arr, int target) {
        if (null == arr || arr.length == 0) {
            return -1;
        }
        int low = 0;
        int high = arr.length - 1;
        //必须是 <= , 因为会查找到数组第一个和最后一个元素
        while (low <= high) {
            //防止溢出， 不能使用 (low + high) / 2 这种计算方法
            int mid = low + (high - low) / 2;
            if (arr[mid] >= target) {
                if (arr[mid - 1] < target) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            } else if (arr[mid] < target) {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 查询最后一个小于等于 target 的下标
     */
    private static int binarySearchFindFistLessThanOrEq(int[] arr, int target) {
        if (null == arr || arr.length == 0) {
            return -1;
        }
        int low = 0;
        int high = arr.length - 1;
        //必须是 <= , 因为会查找到数组第一个和最后一个元素
        while (low <= high) {
            //防止溢出， 不能使用 (low + high) / 2 这种计算方法
            int mid = low + (high - low) / 2;
            if (arr[mid] > target) {
                high = mid - 1;
            } else if (arr[mid] <= target) {
                if (arr[mid + 1] >= target) {
                    return mid;
                }
                low = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 3, 3, 4, 5, 7, 9};
        System.out.println(binarySearch(arr, 1));
        System.out.println(binarySearch(arr, 2));
        System.out.println(binarySearch(arr, 3));
        System.out.println(binarySearch(arr, 4));

        System.out.println(binarySearchFindFirst(arr, 3));
        System.out.println(binarySearchFindLast(arr, 3));

        System.out.println(binarySearchFindFistGreatThanOrEq(arr, 6));

        System.out.println(binarySearchFindFistLessThanOrEq(arr, 6));
    }

}
