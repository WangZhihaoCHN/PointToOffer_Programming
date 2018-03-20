/*
*
* 题目描述：
*      写一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号。
*
* */


public class Test {
    /*
    * 算法思路：
    *    不适用四则运算，只能考虑使用逻辑运算(与、或、非、异或、同或)，实现两个数相加。
    *    我们试着加法分为三步：
    * 1. 同位相加(个位与个位、十位与十位)；
    * 2. 记录超过进位值的进位；
    * 3. 将前两步结果相加。
    *    通过例子分析，这一过程对于二进制同样适用。如：5+17=？，5的二进制是101,17的
    * 二进制10001。step 1：各位相加，得到结果10100，其中最后一位两个数都是1，相加结果
    * 是10，该步骤不计进位，因此该位是0; step 2：记录进位; step 3：把前两步结果相加，
    * 得到答案10110，准换成十进制为22.由此可见，三步走的策略对于二进制也是适用的。
    *    接下来，我们将三步走的加法策略，转换为逻辑运算：
    * 1. 不考虑进位的每一位对应相加。0+0、1+1的结果都是0,0+1或1+0的结果都是1。我们
    * 注意到，这种预算的结果和异相同。异或，相异为1，相同为0。
    * 2. 记录进位。对于0+0、0+1、1+0而言，都不产生进位，只有1+1时，会向高位产生一个进位。
    * 此时，我们可以看做是两个数先进行与&运算，然后再向左移动一位。只有两个数都是1的时候，
    * 与运算得到的结果是1，其与都是0。
    * 3. 前两步相加。持续上述两步，知道不产生进位为止。
    *
    *  */
    public static int Add(int num1, int num2) {
        // sum第一步的和，carry为第二部的进位记录
        int sum , carry;
        do{
            // 计算第1、2步的值
            sum = num1 ^ num2;
            carry = (num1 & num2) << 1;
            // 此时需要计算sum+carry，依旧重复num1和num2相加的步骤，因此重新赋值
            num1 = sum;
            num2 = carry;
        }while(num2 != 0);          //当carry=0时终止循环
        return sum;
    }


    public static void main(String args[]){
        System.out.println(Add(5, 17));
    }
}
