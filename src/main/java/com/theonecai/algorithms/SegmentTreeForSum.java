package com.theonecai.algorithms;

import org.junit.Assert;

/**
 * https://cp-algorithms.com/data_structures/segment_tree.html
 * 线段树:
 * 快速求和( log(n) ): sum of a[left..right];
 * update( log(n) ): a[i] = x;
 * @Author: theonecai
 * @Date: Create in 2020/8/28 21:11
 * @Description:
 */
public class SegmentTreeForSum {
    /**
     * tree[1] = sum of elements[0, n-1]
     * tree[2] = sum of elements[0, (n-1) / 2]
     * tree[3] = sum of elements[(n - 1) / 2 + 1, n-1]
     * ...
     */
    private int[] tree;
    private int size;

    public SegmentTreeForSum(int size) {
        tree = new int[size * 4];
        this.size = size;
    }

    public SegmentTreeForSum(int[] elements) {
        this.tree = new int[elements.length * 4];
        this.size = elements.length;
        build(elements, 1, 0, elements.length - 1);
    }

    public void build(int[] elements, int v, int tl, int tr) {
        if (tl == tr) {
            tree[v] = elements[tl];
            return;
        }

        int mid = (tr + tl) / 2;
        int leftV = v * 2;
        int rightV = leftV + 1;
        build(elements, leftV, tl, mid);
        build(elements, rightV, mid + 1, tr);
        tree[v] = tree[leftV] + tree[rightV];
    }

    private int sum(int v, int tl, int tr, int left, int right) {
        if (left > right) {
            return 0;
        }
        if (tl == left && tr == right) {
            return tree[v];
        }
        int mid = (tl + tr) / 2;
        int leftV = v * 2;
        int rightV = v * 2 + 1;
        return sum(leftV, tl, mid, left, Math.min(mid, right)) +
                sum(rightV, mid + 1, tr, Math.max(mid + 1, left), right);
    }

    /**
     * 求和 a[left~right]
     */
    public int sum(int left, int right) {
        return sum(1, 0, this.size - 1, left, right);
    }

    /**
     * 更新a[position] = newValue;
     */
    public void update(int position, int newValue) {
        update(1, 0, this.size - 1, position, newValue);
    }

    private void update(int v, int tl, int tr, int position, int newValue) {
        if (tl == tr) {
            tree[v] = newValue;
        } else {
            int mid = (tl + tr) / 2;
            int leftV = v * 2;
            int rightV = v * 2 + 1;
            if (position <= mid) {
                update(leftV, tl, mid, position, newValue);
            } else {
                update(rightV, mid + 1, tr, position, newValue);
            }
            tree[v] = tree[leftV] + tree[rightV];
        }
    }


    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6,7,8};
        SegmentTreeForSum segmentTreeForSum = new SegmentTreeForSum(nums);
        Assert.assertEquals(36, segmentTreeForSum.sum(0, 7));
        Assert.assertEquals(7, segmentTreeForSum.sum(2, 3));
        Assert.assertEquals(15, segmentTreeForSum.sum(6, 7));
        segmentTreeForSum.update(3, 5);
        Assert.assertEquals(8, segmentTreeForSum.sum(2, 3));
        Assert.assertEquals(15, segmentTreeForSum.sum(6, 7));
    }
}
