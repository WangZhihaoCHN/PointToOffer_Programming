/*
*
* 题目描述：
*     汇编语言中有一种移位指令叫做循环左移（ROL），现在有个简单的任务，
* 就是用字符串模拟这个指令的运算结果。对于一个给定的字符序列S，请你把其
* 循环左移K位后的序列输出。例如，字符序列S=”abcXYZdef”,要求输出循环左
* 移3位后的结果，即“XYZdefabc”。是不是很简单？OK，搞定它！
*
* */

public class Test {
    /*
    * 算法思路：
    *     Plan A. 先接后切。通过将string后接同样的string，即string += string，
    * 之后再利用substr()方法，取当前string的偏移位开始的string.length长
    * 的子字符串，即为所求。
    *     Plan B. 字符换位。假设字符串abcdef，n=3，设X=abc，Y=def，所以字符串
    * 可以表示成XY，如题干，问如何求得YX。假设X的翻转为X'，X'=cba，同理Y'=fed，
    * 那么YX=(X'Y')'，三次翻转后可得结果。
    *
    *  */
    public static String LeftRotateString(String str,int n)  {
        int len = str.length();
        if(len == 0)
            return "";
        n = n % len;
        str += str;
        return str.substring(n, n+len);
    }


    public static void main(String args[]){
        String str = "abcdef";
        System.out.println(LeftRotateString(str, 3));
    }
}
