/*
*
* 题目描述：
*      求1+2+3+...+n，要求不能使用乘除法、for、while、if、else、switch、case等
* 关键字及条件判断语句（A?B:C）。

 *
* */


public class Test {
    /*
    * 算法思路：
    *    1.由于不能使用乘除法，因此计算公式(1+n)*n/2不能使用；
    *    2.由于不能使用判断语句，因此传统的递归方法不能使用，
    * 因为递归需要判断终止条件。
    *    考虑逻辑与&&的短路特性，conditionA && conditionB，
    * 当A为false时，B不执行。利用该特性，我们可以改进递归，实现
    * 这一问题。
    *
    *  */
    public static int Sum_Solution(int n) {
        int sum = n;
        boolean ans = n>0 && (sum += Sum_Solution(n-1))>0;
        return sum;
    }


    public static void main(String args[]){
        System.out.println(Sum_Solution(5));
    }
}
