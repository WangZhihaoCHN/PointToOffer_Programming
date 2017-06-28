import com.sun.javafx.collections.NonIterableChange;

import java.util.ArrayList;
import java.util.TreeSet;


/*
* 题目描述：
* 输入一个字符串,按字典序打印出该字符串中字符的所有排列。例如输入字符串abc,
* 则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
*
* 输入描述：
* 输入一个字符串,长度不超过9(可能有字符重复),字符只包括大小写字母。
*
* */

public class Test {
    /*
    * 算法思路：
    * 我们要求整个字符串的排列，可以看成两步：首先求所有可能出现在第一个位置的字符，即把第一个字符和后面所有的字符交换。
    * 例如输入“abc"，则分别把第一个字符a和后面的bc等字符交换。具体地，首先固定第一个字符，求后面所有字符的排列。
    * 这个时候我们仍把后面的所有字符分成两部分：后面字符的第一个字符，以及这个字符之后的所有字符。然后把第一个字符逐一
    * 和它后面的字符交换……
    * */
    public static ArrayList<String> Permutation(String str) {
        //新建用于返回的动态链表
        ArrayList<String> result = new ArrayList<>() ;
        //特殊情况返回空结果
        if(str == null || str.length() == 0)
            return result;

        //将字符串转换成字符数组，便于递归交换
        char[] chars = str.toCharArray();
        //利用TreeSet存储所有字符串，可以去重、有序
        TreeSet<String> temp = new TreeSet<>() ;

        //递归的寻找所有字符串排列
        Permutation(chars, 0, temp);

        //将TreeSet中的值添加到用于返回的ArrayList变量中
        result.addAll(temp) ;
        return result ;
    }

    /*
    * 递归函数：依此交换首字符和后续字符，寻找所有字符排列组合
    * */
    public static void Permutation(char[] chars, int begin, TreeSet<String> result) {
        //停止递归条件
        if(chars == null || chars.length == 0 || begin < 0 || begin > chars.length-1)
            return;
        //当递归过程中，第一个字符不断向末尾移动。到达字符串尾时，无法进行交换，则直接添加当前字符串
        if(begin == chars.length-1) {
            result.add(String.valueOf(chars)) ;
        }else {     //当前首字符不在末尾，依此跟后续字符进行交换，递归寻找所有排列
            for(int i = begin; i <= chars.length-1; i++) {
                swap(chars, begin, i);     //交换第一个字符和后续字符
                Permutation(chars, begin+1, result);       //寻找交换后的字符串排列
                swap(chars, begin, i);     //交换回初始字符串，以避免影响后续递归
            }
        }
    }

    /*
    * 用于交换字符数组x中，第a个字符和第b个字符
    * */
    public static void swap(char[] x, int a, int b) {
        char t = x[a];
        x[a] = x[b];
        x[b] = t;
    }

    public static void main(String args[]){
        ArrayList<String> a = Permutation("abc");
        for(String s : a){
            System.out.println(s);
        }
    }
}
