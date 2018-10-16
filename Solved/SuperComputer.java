package INF237;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Andre Berge on 19.03.2018.
 */

class SegTreeNode {
    public int firstIndex, lastIndex, sum;
    public SegTreeNode left, right;

    public SegTreeNode(int firstIndex, int lastIndex, int sum) {
        this.firstIndex = firstIndex;
        this.lastIndex = lastIndex;
        this.sum = sum;
        if (firstIndex == lastIndex) {
            this.left = null;
            this.right = null;
        } else {
            int mid = firstIndex + (lastIndex - firstIndex) / 2;
            this.left = new SegTreeNode(firstIndex, mid, sum);
            this.right = new SegTreeNode(mid + 1, lastIndex, sum);
        }
    }

}

public class SuperComputer {
    public static void UpdateBit(int[] switchArray, int index, SegTreeNode node) {
        int mid = node.firstIndex + (node.lastIndex - node.firstIndex) / 2;

        if (switchArray[index] == 0)
            node.sum -= 1;
        else
            node.sum += 1;

        if (!(index == node.firstIndex && index == node.lastIndex)) {
            if (index > mid)
                UpdateBit(switchArray, index, node.right);
            else if (index <= mid)
                UpdateBit(switchArray, index, node.left);
        }
    }

    public static int GetSumFromRange(int startIndex, int endIndex, SegTreeNode node) {
        if (startIndex <= node.firstIndex && endIndex >= node.lastIndex )
            return node.sum;

        if (node.lastIndex < startIndex || node.firstIndex > endIndex)
            return 0;

        return GetSumFromRange(startIndex, endIndex, node.left) + GetSumFromRange(startIndex,endIndex,node.right);

    }

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] input = in.readLine().split(" ");
        int bits = Integer.parseInt(input[0]);
        int ops = Integer.parseInt(input[1]);
        String[] operation;
        int[] switchArray = new int[bits];
        SegTreeNode segmentTree = new SegTreeNode(0, switchArray.length-1, 0);

        for (int i = 0; i < ops; i++) {
            operation = in.readLine().split(" ");
            if (operation[0].equals("F")) {
                int index = Integer.parseInt(operation[1]) - 1;
                if (switchArray[index] == 0)
                    switchArray[index] = 1;
                else
                    switchArray[index] = 0;

                UpdateBit(switchArray, index, segmentTree);
            } else {
                int firstIndex = Integer.parseInt(operation[1]) - 1;
                int secondIndex = Integer.parseInt(operation[2]) - 1;
                System.out.println(GetSumFromRange(firstIndex, secondIndex, segmentTree));
            }
        }

    }
}
