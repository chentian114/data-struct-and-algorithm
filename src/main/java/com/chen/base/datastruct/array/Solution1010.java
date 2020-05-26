package com.chen.base.datastruct.array;

/**
 * @author: Chentian
 * @date: Created in 2020/5/19 5:30
 * @desc 两数之和整除60，将两数分别%60所得余数之和为60即可得结果
 * 整数%60之后，所得余数在0~59之间，用arr[60]存放相应余数的数量
 * arr[1] * arr[59] 可得余数为1和余数为59的整数对整除60的数量
 * arr[0] 和 arr[30] 只能相同的余数组对才能整除60，求和公式为 1+2+...+(arr[0]-1) = (arr[0]-1) * arr[0] / 2
 */
public class Solution1010 {

    public int numPairsDivisibleBy60(int[] time) {
        if(time == null || time.length == 0 ){
            return 0;
        }
        int[] data = new int[60];

        for (int i = 0 ; i < time.length; i++ ){
            int num = time[i]%60;
            data[num] += 1;
        }

        int result = 0;
        result += (data[0] * (data[0] - 1)) >> 1 ;
        result += (data[30] * (data[30] - 1)) >> 1;
        for (int i = 1 ; i <= 29; i++){
            result += (data[i] * data[60-i]);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] time = {30,20,150,100,40};
//        int[] time = {60,60,60};
        Solution1010 solution1010 = new Solution1010();
        System.out.println(solution1010.numPairsDivisibleBy60(time));
    }
}
