package com.peanut.offer;

/**
 * Created by WangZhihao on 2017/8/29.
 *
 * 题目描述：
 *    在一个字符串(1<=字符串长度<=10000，全部由字母组成)中
 *    找到第一个只出现一次的字符,并返回它的位置
 *
 */
public class Solution {
    /**
     *
     * 算法思路：
     * 构建一个Hash表，第一次遍历字符串，统计每个字母出现的次数。
     * 第二次遍历数组，找到第一个非重复出现的字符。
     *
     * */

    public static int FirstNotRepeatingChar(String str) {
        //特殊输入判断
        if(str == null || str.length() == 0)
            return -1;

        /*
         *  hash表。由于字符共有256个，其实字母一共有26*2个，
         *  但是考虑到测试集不定与程序简便性，直接使用一个256
         *  长度的整形数组作为Hash表。
         */
        int hash[] = new int[256];

        //第一次循环，将字符串中出现的字符，在哈希表中次数加1
        int tmp;
        for(int i=0; i<str.length(); i++){
            tmp = str.charAt(i);
            hash[tmp]++;
        }

        //第二次循环，将字符串中出现的字符作为key，在哈希表中查询。
        //出现次数为1的，即为要找的字符，返回即可。
        tmp = -1;
        for(int i=0; i<str.length(); i++){
            tmp = str.charAt(i);
            if(hash[tmp] == 1)
                return i;
        }

        return 0;
    }

    public static void main(String [] args){
        int a = FirstNotRepeatingChar("google");
        char out = (char) a;
        System.out.print(out);
    }
}
