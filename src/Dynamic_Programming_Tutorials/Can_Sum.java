package Dynamic_Programming_Tutorials;

import java.util.HashMap;
import java.util.Map;

/**
 * Mon, 14 Jun 2021, 1:02 AM
 * Description:
 **/
public class Can_Sum {
    public static boolean canSum(int targetSum, int[] numbers) {
        return canSumRecursive(targetSum, numbers, new HashMap<>());
    }

    public static boolean canSumRecursive(int targetSum, int[] numbers, Map<Integer, Boolean> memo) {
        if (memo.containsKey(targetSum)) {
            return memo.get(targetSum);
        }
        if (targetSum == 0) {
            return true;
        }
        if (targetSum < 0) {
            return false;
        }
        for (int num : numbers) {
            int newTargetSum = targetSum - num;
            if (canSum(newTargetSum, numbers)) {
                memo.put(targetSum, true);
                return true;
            }
        }
        memo.put(targetSum, false);
        return false;
    }

    public static void main(String[] args) {
        System.out.println(canSum(7, new int[]{2, 3}));
        System.out.println(canSum(7, new int[]{5, 3, 4, 6}));
    }
}
