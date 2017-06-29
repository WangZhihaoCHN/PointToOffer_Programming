/*
*
* 题目描述：
* 求出1~13的整数中1出现的次数,并算出100~1300的整数中1出现的次数？为此他特别数了一下1~13中包含1的数字有1、10、11、12、13
* 因此共出现6次,但是对于后面问题他就没辙了。ACMer希望你们帮帮他,并把问题更加普遍化,可以很快的求出任意非负整数区间中1出现的次数。
*
* */

public class Test {
    /*
    * 算法思路：
    * 设定整数点（如个位、十位、百位）作为位置点x，从右向左，从低位到高位。同时，i表示x对应整数n的量级，如个十百千万…
    * 根据上述设定的位置x，我们可以对n进行分割，分为前后两部分，高位即x之前为head，低位即x之后为tail。例如，当x表示
    * 百位，21345可以分为，head:21、tail:45、x:3。介绍完上述部分后，我们通过一个例子来说明算法的整体思路~
    *   当x表示百位，且百位对应的数>=2时，如n=31456，x=4，i=100，head=31,tail=56。此时百位为1的次数有，最高两位
    * 0~31，其中每一次又都包含100个连续的点（即tail从0到99），即共有（head+1）*100个百位为1的情况。
    *   当x表示百位，且百位对应的数为1，如n=31156,i=100，则head=31,tail=56，此时百位对应的就是1，则共有最高两位
    * 0~30次是包含100个连续点，当最高两位为31（即a=31），本次只对应tail为00~56的情况，共tail+1次。所有点加起来共
    * 有（head*100+(tail+1)种。
    *   当x表示百位，且百位对应的数为0,如n=31056,i=100，则head=310,tail=56，此时百位为1的次数有最高两位0~30，
    * 其中每一个又都包含100个连续的点（即tail从0到99），所以加起来有head * i种。
    * */
    public static int NumberOf1Between1AndN_Solution(int n) {
        // 将n转换成字符串，进而转换成字符数组
        String tmp = String.valueOf(n);
        char[] num = tmp.toCharArray();
        int count = 0;

        int i = 1;      //表示整数n的量级位（如：个十百千万…）
        for(int x = num.length; x >= 1; x--){
            int head = n / i;
            head /= 10;             //第i位的高位
            int tail = n % i;      //第i位的低位

            /*
            * ① 第x位为0：
            *       高位为（0 ~ head-1）时，x位可能为1;因为当高位为head时。由于x位为0，靠tail部分无法使x为1。
            *       因此，x=0时，只存在(head-1-0+1)*i,即当head不满时，tail部分可以从0~（i-1），如x为百位则是0~99。
            * ② 第x位为1：
            *       高位为（0 ~ head-1）时，情况与①相似，即head*i。而由于x位为1，因此存在高位为head、低位为（0 ~ tail）时也为1的情况。
            *       因此，x=1时，有head*i+（tail-0+1）
            * ③ 第x位>=2：
            *       高位为（0 ~ head）时，x均可以为1，即在情况①的基础上，增加高位为head的0~tail-1，如x为百位则是0~99。
            *       因此，x>=2时，有(head-0+1)*i
             */
            if(num[x-1] == '0'){
                count += head * i;
            }else if(num[x-1] == '1'){
                count += (head * i + tail + 1);
            }else{
                count += ((head + 1) * i);
            }

            // 第x位对应的个/十/百/千…等位
            i *= 10;
        }

        return count;
    }



    public static void main(String args[]){
        System.out.println(NumberOf1Between1AndN_Solution(21345));
    }
}
