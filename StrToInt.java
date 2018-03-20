/*
*
* 题目描述：
*      写一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号。
*
* */


public class Test {
    /*
    * 算法思路：
    *    将字符串能转换为char[]，依次处理每一个字符。
    *    1. 首位可能是'+'或'-'，对应最后结果sum的正负，如果是负，需要最后sum*-1；
    *    2. 判断当前字符是否为数字，是则进行如下处理：
    * 将字符ch转换为对应的0~9数字n，将之前的sum*10+n。因为之前的sum都位于ch的高位，
    * 当前扫描到的需要填入sum的数字n，为最低位(即当前的个位)，因此需要之前的每个数字
    * 都*10，即结果sum*10，在加入数字n。
    *    3. 当前字符不是数字，异常字符串，返回0。
    *
    *  */
    public static int StrToInt(String str) {
        // 异常输入
        if(str.length()==0 || str == null)
            return 0;

        int sum = 0;                 // 记录当前转换的整数值
        boolean plus = true;         // 记录该数为正还是负
        char[] nums = str.toCharArray();
        for(int i=0; i<nums.length; i++){
            if(i==0){
                if(nums[0] == '+'){
                    plus = true;
                    continue;
                }
                if(nums[0] == '-'){
                    plus = false;
                    continue;
                }
            }
            if(nums[i] >= '0' && nums[i] <= '9'){
                sum = (sum * 10) + (nums[i] - '0');
                continue;
            }
            else
                return 0;
        }
        if(!plus)
            sum *= -1;
        return sum;
    }


    public static void main(String args[]){
        System.out.println(StrToInt("123"));
    }
}
