/*
*
* 题目描述：
*     小明很喜欢数学，有一天他在做数学作业时，要求计算出~16的和，他马上就写出了正确答案是100。
* 但是他并不满足于此，他在想究竟有多少种连续的正数序列的和为100(至少包括两个数)。没多久，
* 他就得到了另一组连续正数和为100的序列：18,19,20,21,22。现在把问题交给你，你能不能也很
* 快的找出所有和为S的连续正数序列？Good Luck！
*
* */

import java.util.ArrayList;


public class Test {
    /*
    * 算法思路：
    *     由于是正数序列，选择最小的正数1作为首个begin，与其连续的正数2作为首个end。问题的解决
    * 可以转化为，求出begin~end的序列和sum
    * 若sum>S，则减少序列中正数的个数，即begin++;
    * 反之，若sum<S，则增加序列中正数的个数，即end++。
    * 终止条件为begin>=S/2，即当前序列中的所有正数均大于或等于S/2，和必然大于S，不满足条件。
    *
    *   注意！！当begin~end的序列和正好为sum时，在添加新的序列条目时，begin和end都需要+1。
    *
    *  */
    public static ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();

        // 异常输入
        if(sum <= 2)
            return ret;

        ArrayList<Integer> line;
        int begin = 1;
        int end = 2;
        int tmp = 0;

        while(begin < (sum+1)/2){
            tmp = (begin + end) * (end - begin + 1) / 2;
            if(tmp == sum){
                line = new ArrayList<>();
                for(int i = begin; i <= end; i++){
                    line.add(i);
                }
                ret.add(line);
                begin++;
                end++;
            }else if(tmp < sum){
                end++;
            }else{
                begin++;
            }
        }
        return ret;
    }


    public static void main(String args[]){
        ArrayList<ArrayList<Integer>> ret = FindContinuousSequence(100);
        for(ArrayList<Integer> tmp : ret){
            for(Integer n : tmp)
                System.out.print(n + " ");
            System.out.println();
        }
    }
}
