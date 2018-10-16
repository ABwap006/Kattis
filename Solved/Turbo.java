package INF237;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Created by Andre Berge on 19.03.2018.
 */

class SegTreeNode2 {
    public int firstIndex, lastIndex, sum;
    public SegTreeNode2 left, right;

    public SegTreeNode2(int firstIndex, int lastIndex) {
        this.firstIndex = firstIndex;
        this.lastIndex = lastIndex;
        this.sum = sum;
        if (firstIndex == lastIndex) {
            this.left = null;
            this.right = null;
            this.sum = 1;
        } else {
            int mid = firstIndex + (lastIndex - firstIndex) / 2;
            this.left = new SegTreeNode2(firstIndex, mid);
            this.right = new SegTreeNode2(mid + 1, lastIndex);
            this.sum = this.left.sum + this.right.sum;
        }
    }

}

public class Turbo {
    public static void UpdateBit(int index, SegTreeNode2 node) {
        int mid = node.firstIndex + (node.lastIndex - node.firstIndex) / 2;

        node.sum -= 1;

        if (!(index == node.firstIndex && index == node.lastIndex)) {
            if (index > mid)
                UpdateBit(index, node.right);
            else if (index <= mid)
                UpdateBit(index, node.left);
        }
    }

    public static int GetSumFromRange(int startIndex, int endIndex, SegTreeNode2 node) {
        if (startIndex <= node.firstIndex && endIndex >= node.lastIndex )
            return node.sum;

        if (node.lastIndex < startIndex || node.firstIndex > endIndex)
            return 0;

        return GetSumFromRange(startIndex, endIndex, node.left) + GetSumFromRange(startIndex,endIndex,node.right);

    }

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int[] arr, arrToUse;
        String[] input = in.readLine().split(" ");
        int arraySize = Integer.parseInt(input[0]);
        arr = new int[arraySize];
        arrToUse = new int[arraySize];

        for (int i = 0; i < arraySize; i++) {
            arr[i] = Integer.parseInt(in.readLine());
            arrToUse[arr[i]-1] = i;
        }

        SegTreeNode2 seg2 = new SegTreeNode2(0, arraySize-1);

        int leftCount = 0, rightCount = 1;
        for(int i = 0; i < arrToUse.length; i++) {

            if (i % 2 == 0) {

                UpdateBit(arrToUse[leftCount], seg2);
                System.out.println(GetSumFromRange(0, arrToUse[leftCount++], seg2));

            } else {

                UpdateBit(arrToUse[arrToUse.length - rightCount], seg2);
                System.out.println(GetSumFromRange(arrToUse[arrToUse.length - rightCount++], arrToUse.length-1, seg2));

            }
        }
    }
}
