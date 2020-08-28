package com.theonecai.algorithms;

/**
 * https://cp-algorithms.com/data_structures/segment_tree.html
 * 线段树:
 * 求区间最大值及最大值出现次数:
 *
 * @Author: theonecai
 * @Date: Create in 2020/8/28 19:38
 * @Description:
 */
public class SegmentTreeForMax {
    public static final MaxPair NO_EXIST_PAIR = new MaxPair(Integer.MIN_VALUE, 0);

    private MaxPair[] tree;

    private int size;

    public SegmentTreeForMax(int size) {
        this.size = size;
        tree = new MaxPair[size * 4];
    }

    public SegmentTreeForMax(int[] nums) {
        this.size = nums.length;;
        this.tree = new MaxPair[this.size * 4];

        build(nums);
    }

    private MaxPair combine(MaxPair a, MaxPair b) {
        if (a.maxValue > b.maxValue) {
            return a;
        } else if (a.maxValue < b.maxValue) {
            return b;
        }

        return new MaxPair(a.maxValue, a.count + b.count);
    }

    public void build(int[] nums) {
        build(nums, 1, 0, this.size - 1);
    }

    private void build(int[] nums, int v, int tl, int tr) {
        if (tl == tr) {
            tree[v] = new MaxPair(nums[tl], 1);
            return;
        }

        int mid = middle(tl, tr);
        int leftV = v * 2;
        int rightV = v * 2 + 1;

        build(nums, leftV, tl, mid);
        build(nums, rightV, mid + 1, tr);

        tree[v] = combine(tree[leftV], tree[rightV]);
    }

    public void update(int position, int newValue) {
        update(1, 0, size - 1, position, newValue);
    }

    private void update(int v, int tl, int tr, int position, int newValue) {
        if (tl == tr) {
            tree[v] = new MaxPair(newValue, 1);
            return;
        }

        int mid = middle(tl, tr);
        int leftV = v * 2;
        int rightV = v * 2 + 1;

        if (position <= mid) {
            update(leftV, tl, mid, position, newValue);
        } else {
            update(rightV, mid + 1, tr, position, newValue);
        }

        tree[v] = combine(tree[leftV], tree[rightV]);
    }

    public MaxPair getMaxPair(int left, int right) {
        return getMaxPair(1, 0, size - 1, left, right);
    }

    private MaxPair getMaxPair(int v, int tl, int tr, int left, int right) {
        if (left > right) {
            return NO_EXIST_PAIR;
        }
        if (left == tl && right == tr) {
            return tree[v];
        }

        int mid = middle(tl, tr);
        int leftV = v * 2;
        int rightV = v * 2 + 1;

        return combine(getMaxPair(leftV, tl, mid, left, Math.min(mid, right)),
                getMaxPair(rightV, mid + 1, tr, Math.max(mid + 1, left), right));
    }

    private int middle(int tl, int tr) {
        return (tl + tr) / 2;
    }

    static class MaxPair {
        /**
         * 最大值
         */
        private int maxValue;
        /**
         * 出现次数
         */
        private int count;

        public MaxPair(int maxValue, int count) {
            this.maxValue = maxValue;
            this.count = count;
        }

        @Override
        public String toString() {
            return "MaxPair{" +
                    "maxValue=" + maxValue +
                    ", count=" + count +
                    '}';
        }
    }

    public static void main(String[] args) {
        int[] nums = ArrayUtil.randIntArray(10);
        ArrayUtil.print(nums);
        SegmentTreeForMax maxTree = new SegmentTreeForMax(nums);
        System.out.println(maxTree.getMaxPair(0, nums.length - 1));
    }
}
