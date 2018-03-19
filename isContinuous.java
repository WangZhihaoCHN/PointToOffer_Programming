/*
*
* 题目描述：
*     LL今天心情特别好,因为他去买了一副扑克牌,发现里面居然有2个大王,
* 2个小王(一副牌原本是54张^_^)...他随机从中抽出了5张牌,想测测自己的
* 手气,看看能不能抽到顺子,如果抽到的话,他决定去买体育彩票,嘿嘿！！
* “红心A,黑桃3,小王,大王,方片5”,“Oh My God!”不是顺子.....LL不高兴
* 了,他想了想,决定大\小 王可以看成任何数字,并且A看作1,J为11,Q为12,
* K为13。上面的5张牌就可以变成“1,2,3,4,5”(大小王分别看作2和4),
* “So Lucky!”。LL决定去买体育彩票啦。 现在,要求你使用这幅牌模拟上面
* 的过程,然后告诉我们LL的运气如何。为了方便起见,你可以认为大小王是0。
*
* */


public class Test {
    /*
    * 算法思路：
    *     构成顺子，需要满足以下几个条件：
    * 1. 最大值为max，最小值为min，max-min<5;
    * 2. 除0外，没有其他重复数字
    *
    * 其中，求min、max以及重复数字时，可以排序，但这样不是最佳求解。
    *
    * 考虑使用BitMap思想，节约存储空间、提高效率：
    *     我们欲对1~13这些数字排序(前面分析，0不需要考虑)，只需要建立
    * 至少13位的bit数组，所有bit位清0后，依此将取得的5张牌对应的数字放
    * 入该数组。放入过程为，将1向高位移动当前扑克牌数字num位，即1<<num。
    * 将bit数组(其实是一个变量)与该答案相或，即bit|(1<<num)。以上则为
    * 放入过程。
    *     放入数字过程中，通过变量max和min记录最大和最小值，并根据放入
    * 过程，不断更新max和min，并且需要比较是否满足条件 max-min<5，一旦
    * 不满足，立即返回false。
    *     其中，放入过程中，如果此位置已经有数字，则不满足条件2，返回false。
    * 该过程的判断可通过bit数组移位来实现。将当前bit数组，向低位移动当前扑克
    * 牌数字num位，即bit>>num。再将该答案与1相与，比较结果是否为1，从而判断
    * 是否有重复数字。(bit>>num)&1 == 1?
    *
    * 注意！！min不能初始化为0，否则没有比它小的数字了，应该初始化为扑克牌
    * 最大的数字13
    *  */
    public static boolean isContinuous(int [] numbers) {
        if(numbers.length != 5)
            return false;

        int min = 13;
        int max = 0;
        int bit = 0;

        for(int num : numbers){
            if(num == 0)
                continue;
            if(((bit>>(num-1)) & 1) == 1)
                return false;
            if(num < min)
                min = num;
            if(num > max)
                max = num;
            if(max-min>=5)
                return false;
            bit |= (1<<(num-1));
        }
        return true;
    }


    public static void main(String args[]){
        int a[] = {1,3,2,5,4};
        System.out.println(isContinuous(a));
    }
}
