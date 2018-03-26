/*
*
* 题目描述：
*      请实现一个函数用来匹配包括'.'和'*'的正则表达式。
* 模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符
* 可以出现任意次（包含0次）。 在本题中，匹配是指字符串的
* 所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和
* "ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配。
*
* */


public class Test {
    /*
    * 算法思路：
    *     利用递归的方法实现,当字符串和匹配串均到达结尾时，返回true。匹配串先到结尾，返回false。
    * 由于存在abc和abca*b*c*等情况，所以字符串先到结尾，不返回，后续迭代情况会进行验证，但需要
    * 保证下标不越界。
    * 一、当模式串中的第二个字符不是*时：
    *     1.第一个字符相匹配(包含字符相同和匹配串为.两种情况)，字符串和匹配串均后移1位，继续匹配剩余部分。
    *     2.第一个字符与不匹配，返回false。
    * 二、当模式串中的第二个字符是*时：
    *     1.第一个字符不匹配，匹配串后移两个字符，继续匹配剩余部分。
    *     2.第一个字符匹配，后续匹配方式较为复杂，有以下三种：
    *          ① 字符串不变，模式串后移2字符，继续匹配，相当于x*中x不出现。
    *          ② 字符串后移1字符，模式串后移2字符。继续匹配，相当于x*中，x只出现一次。
    *          ③ 字符串后移1字符，模式串不变，继续匹配，相当于x*中x出现多次(2次及以上)。
    *     其中，二.2.②步骤多余，因为，当满足条件③时，后续部分会判断条件①，而这两步的组合，
    * 就等于二.2.②这一步，因此该部分可以删去。
    *
    * 注意！！！字符串和匹配串是否到达结尾的判断很重要。(因为java字符串的结尾不是'\0')
    *
    *  */
    public static boolean match(char[] str, char[] pattern) {
        // 异常输入
        if(str == null || pattern == null)
            return false;
        return isMatch(str,0,pattern,0);
    }

    public static boolean isMatch(char[] str, int strIndex, char[] pattern, int patternIndex){
        // 字符串和匹配串均到达结尾
        if(strIndex == str.length && patternIndex == pattern.length)
            return true;

        // 匹配串先到达结尾
        if(patternIndex == pattern.length)
            return false;

        // 第二个字符是'*'
        if(patternIndex+1 < pattern.length && pattern[patternIndex+1] == '*'){
            // 保证字符串没有先到达结尾，否则，只需要判断后续是否存在x*即可
            if(strIndex != str.length)
                // 首字符不匹配
                if(str[strIndex] != pattern[patternIndex] && pattern[patternIndex] != '.')
                    return isMatch(str,strIndex,pattern,patternIndex+2);
                // 首字符匹配，情况复杂
                else
                    return (isMatch(str,strIndex,pattern,patternIndex+2)
                            || isMatch(str,strIndex+1,pattern,patternIndex));
            else
                return isMatch(str,strIndex,pattern,patternIndex+2);

        }

        // 第二个字符不是'*'
        if(strIndex < str.length && str[strIndex] == pattern[patternIndex] || pattern[patternIndex] == '.')
            return isMatch(str,strIndex+1,pattern,patternIndex+1);
        return false;
    }


    public static void main(String args[]){
        System.out.println(match("".toCharArray(),".".toCharArray()));
    }
}
