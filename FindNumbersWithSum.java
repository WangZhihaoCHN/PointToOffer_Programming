/*
*
* 题目描述：
*     汇编语言中有一种移位指令叫做循环左移（ROL），现在有个简单的任务，
* 就是用字符串模拟这个指令的运算结果。对于一个给定的字符序列S，请你把其
* 循环左移K位后的序列输出。例如，字符序列S=”abcXYZdef”,要求输出循环左
* 移3位后的结果，即“XYZdefabc”。是不是很简单？OK，搞定它！
*
* */

import java.util.ArrayList;


public class Test {
    /*
    * 算法思路：
    *     数列满足递增，设两个头尾两个指针i和j，
    * 若ai + aj == sum，就是答案（相差越远乘积越小）
    * 若ai + aj > sum，aj肯定不是答案之一（前面已得出 i 前面的数已是不可能），j -= 1
    * 若ai + aj < sum，ai肯定不是答案之一（前面已得出 j 后面的数已是不可能），i += 1
    *
    *     注意！！当i == j时，终止搜索。
    *
    *  */
    public static ArrayList<Integer> FindNumbersWithSum(int [] array,int sum)  {
       ArrayList<Integer> ret = new ArrayList<>();

       if(array == null || array.length < 2)
           return ret;

       int head = 0, tail = array.length - 1;
       int sumTep;

       while(head != tail){
            sumTep = array[head] + array[tail];
            if(sumTep == sum){
                ret.add(array[head]);
                ret.add(array[tail]);
                break;
            }else if(sumTep < sum){
                head++;
            }else{
                tail--;
            }
       }
       return ret;
    }


    public static void main(String args[]){
        int array[] = {1,2,3,4};
        ArrayList<Integer> ret = FindNumbersWithSum(array,5);
        for(int tmp : ret){
            System.out.print(tmp + " ");
        }
    }
}
