package algo;

import java.util.*;

public class LeetCode1 {
    public static void main(String[] args) {
        Queue<Integer> dq = new LinkedList<Integer>();

//        dq.add(6);
//        System.out.println(dq.remove());
//        System.out.println(dq);

        int[] myArr = {1, 2, 3, 1, 2, 3};
        int[] myArr2 = {1, 0, 1, 1};
//        System.out.println(containsNearbyDuplicate3(myArr2, 1, 1));
        HashSet<Integer> memoi = new HashSet<Integer>();
        System.out.print(" contains ? " + memoi.contains(0) + "\n");

        System.out.print(divisorSubstrings(2124, 1));
    }

    boolean shuffleThePieces(int[] arr, int[][] pieces) {
        HashMap<Integer, Integer> holder1 = new HashMap<Integer, Integer>();
        Map<Integer, Integer> holder2 = new HashMap<Integer, Integer>();
        int l1 = arr.length;
        int l2 = 0;
        for (int i : arr) {
            Integer j = holder1.get(i);
            holder1.put(i, (j == null) ? 1 : j + 1);
        }
        for (int[] i : pieces) {
            for (int j : i) {
                Integer k = holder2.get(i);
                holder2.put(j, (k == null) ? 1 : k + 1);
            }
        }
        if (holder2.equals(holder1)) {
            return true;
        } else {
            return false;
        }

    }


    static int divisorSubstrings(int n, int k) {
        HashSet<Integer> divisorList = new HashSet<Integer>();
        int counter = 0;
        String intStr = String.valueOf(n);
        for (int i = 0; i < intStr.length() - k + 1; i++) {
            String subString = intStr.substring(i, i + k);
            int subInt = Integer.parseInt(subString);
            System.out.println("testing " + n + " against " + subInt);
            if (subInt != 0 && n % subInt == 0 && !divisorList.contains(subInt)) {
                counter++;
                divisorList.add(subInt);
                System.out.println(!divisorList.contains(subInt));
                System.out.println(divisorList);
            }
        }
        return counter;
    }

    public boolean containsNearbyAlmostDuplicate3(int[] nums, int k, int t) {
        HashSet<Integer> memoi = new HashSet<Integer>();
        for (int i = 0; i < nums.length; i++) {
            int min = Integer.MIN_VALUE;
            int max = Integer.MAX_VALUE;


            if (memoi.contains(nums[i]) || (nums[i] >= min && nums[i] <= max)) {
                for (int j = Math.max((i - k), 0); j < Math.min((i + k + 1), nums.length - 1); j++) {
                    System.out.print("currently at " + i + " comparing " + nums[i] + " and " + nums[j] + "\n");
                    if (!(i == j) && Math.abs(nums[i] - nums[j]) <= t) {
                        return true;
                    }
                }
            } else {
                memoi.add(nums[i]);
                min = nums[i] - t;
                max = nums[i] + t;
            }

        }
        return false;
    }

    //contains duplicate 2
    public static boolean containsNearbyDuplicate2(int[] nums, int k) {
        HashSet<Integer> memoi = new HashSet<Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (memoi.contains(nums[i])) {
                for (int j = Math.max((i - k), 0); j < Math.min((i + k + 1), nums.length - 1); j++) {
                    System.out.print("currently at " + i + " comparing " + nums[i] + " and " + nums[j] + "\n");
                    if (nums[i] == nums[j] && !(i == j)) {
                        return true;
                    }
                }
            } else {
                memoi.add(nums[i]);
            }
        }
        return false;
    }
}
