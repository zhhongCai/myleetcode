package com.theonecai.algorithms;

/**
 * @Author: theonecai
 * @Date: Create in 2020/6/18 10:31
 * @Description:
 */
public class BinarySearch {

    /**
     * 数组a已排序且没有重复数据,查找matchValue在数组中的下标
     * @param a
     * @param matchValue
     * @return
     */
    public static int search(Integer[] a, int matchValue) {
        int low = 0;
        int high = a.length - 1;
        int mid;
        while (low <= high) {
            mid = getMid(low, high);
            if (a[mid] == matchValue) {
                return mid;
            }
            if (a[mid] > matchValue) {
                high = mid - 1;
                continue;
            }
            if (a[mid] < matchValue) {
                low = mid + 1;
            }
        }

        return -1;
    }

    /**
     * 数组a已排序,查找第一个值等于给定值的元素
     * @param a
     * @param matchValue
     * @return
     */
    public static int searchFirst(Integer[] a, int matchValue) {
        int low = 0;
        int high = a.length - 1;
        int mid;
        while (low <= high) {
            mid = getMid(low, high);
            if (a[mid] > matchValue) {
                high = mid - 1;
                continue;
            }
            if (a[mid] < matchValue) {
                low = mid + 1;
                continue;
            }
            if (a[mid] == matchValue) {
                if (mid == 0 || a[mid - 1] != matchValue) {
                    return mid;
                }
                high = mid - 1;
            }
        }

        return -1;
    }

    /**
     * 数组a已排序,查找最后一个值等于给定值的元素
     * @param a
     * @param matchValue
     * @return
     */
    public static int searchLast(Integer[] a, int matchValue) {
        int low = 0;
        int high = a.length - 1;
        int mid;
        while (low <= high) {
            mid = getMid(low, high);
            if (a[mid] > matchValue) {
                high = mid - 1;
                continue;
            }
            if (a[mid] < matchValue) {
                low = mid + 1;
                continue;
            }
            if (a[mid] == matchValue) {
                if (mid == a.length - 1 || a[mid + 1] != matchValue) {
                    return mid;
                }
                low = mid + 1;
            }
        }

        return -1;
    }

    /**
     * 数组a已排序,查找第一个大于等于给定值的元素
     * @param a
     * @param matchValue
     * @return
     */
    public static int searchGreatEqFirst(Integer[] a, int matchValue) {
        int low = 0;
        int high = a.length - 1;
        int mid;
        while (low <= high) {
            mid = getMid(low, high);
            if (a[mid] >= matchValue) {
                if (mid == 0 || a[mid - 1] < matchValue) {
                    return mid;
                }
                high = mid - 1;
                continue;
            }
            if (a[mid] < matchValue) {
                low = mid + 1;
            }
        }

        return -1;
    }

    private static int getMid(int low, int high) {
        return (low + high) / 2;
    }

    /**
     * 数组a已排序,查找最后一个小于等于给定值的元素
     * @param a
     * @param matchValue
     * @return
     */
    public static int searchLessEqLast(Integer[] a, int matchValue) {
        int low = 0;
        int high = a.length - 1;
        int mid;
        while (low <= high) {
            mid = getMid(low, high);
            if (a[mid] <= matchValue) {
                if (mid == a.length - 1 || a[mid + 1] > matchValue) {
                    return mid;
                }
                low = mid + 1;
                continue;
            }
            if (a[mid] > matchValue) {
                high = mid - 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int len = 15;
        Integer[] a = ArrayUtil.randArray(len);
        QuickSort<Integer> sort = new QuickSort<>();
        sort.quickSort(a);
        ArrayUtil.print(a);

        int val = 15;
        System.out.println("matchValue: " + val);
        System.out.println("search: " + search(a, val));
        System.out.println("searchFirst: " + searchFirst(a, val));
        System.out.println("searchLast: " + searchLast(a, val));
        System.out.println("searchGreatEqFirst: " + searchGreatEqFirst(a, val));
        System.out.println("searchLessEqLast: " + searchLessEqLast(a, val));

    }
}
