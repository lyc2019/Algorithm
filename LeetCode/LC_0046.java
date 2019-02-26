/*
给定一个没有重复数字的序列，返回其所有可能的全排列。

示例:

输入: [1,2,3]
输出:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
 */
import java.util.List;
import java.util.ArrayList;

class LC_0046 {
    public List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> res = new ArrayList<List<Integer>>();
        // ArrayList<Integer> temp = new ArrayList<Integer>();
        dfs(res, nums, 0);

        return res;
    }

    private void dfs(List<List<Integer>> res, int[] nums, int j) {

        if (j == nums.length) {
            
            List<Integer> temp = new ArrayList<Integer>();
            for (int num : nums) {
                
                temp.add(num);
            }
            res.add(temp);
        }

        for (int i = j; i < nums.length; i++) {

            swap(nums, i, j);
            dfs(res, nums, j + 1);
            swap(nums, i, j);
        }
    }

    private void swap(int[] nums, int m, int n) {

        int temp = nums[m];
        nums[m] = nums[n];
        nums[n] = temp;
    }

    // public static void printAll(List<List<Integer>> arr) {

    //     for (int i = 0; i < arr.size(); i++) {

    //         System.out.print(i + "：");
    //         for (int j = 0; j < arr[i].length; j++) {

    //             System.out.print(arr[i] + " ");
    //         }
    //         System.out.println(" ");
    //     }
    // }

    // public static void main(String[] args) {
    //     int[] testArr = { 1, 2, 3 };
    // 	List<List<Integer>> arr = permute(testArr);
    //     printAll(arr);
    // }
}