/*
*
* 题目描述：
*      在一个长度为n的数组里的所有数字都在0到n-1的范围内。数组中某些数字是重复的，
* 但不知道有几个数字是重复的。也不知道每个数字重复几次。请找出数组中任意一个重复的数字。
* 例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是第一个重复的数字2。
*
* */


public class Test {
    /*
    * 算法思路：
    *    该题解法，显然可以通过建立n大小的哈希表，通过判断当前数字所在hash项是否之前已存在，
    * 寻找第一个重复的数字。当所有的数据均遍历完，且未出现重复时，返回false。
    *    但是，从题目中可以看出，数组中所有数字的取值范围，刚好是数组的大小n。因此，可以不
    * 单独建立hash table，当一个数组中(下标i)的数字x被访问后，设置数组nums对应位的数nums[i]
    * 减掉length。之后再遇到同样的数时，会发现该位置上的数已经小于0了，那么直接返回这个数即可。
    *
    *  */

    /**
     * Parameters:
     *      numbers:     an array of integers
     *      length:      the length of array numbers
     *      duplication: (Output) the duplicated number in the array number,length of duplication array is 1,so using duplication[0] = ? in implementation;
     *      Here duplication like pointor in C/C++, duplication[0] equal *duplication in C/C++
     *      这里要特别注意~返回任意重复的一个，赋值duplication[0]
     * Return value:   true if the input is valid, and there are some duplications in the array number
     *      otherwise false
     * */
    public static boolean duplicate(int numbers[],int length,int [] duplication) {
        // 异常输入
        if(numbers == null || numbers.length == 0 || length == 0 || duplication == null)
            return false;

        // 记录当前数组中被访问到的数
        int num;
        for(int i=0; i<length; i++){
            num = numbers[i];
            // 之前修改过hash，使该位置的数为负，+length还原，便于后续操作
            if(num < 0)
                num += length;
            // 如果num对应的数组下标位置数字已经为负，则找到了第一个重复的数字
            if(numbers[num] < 0){
                duplication[0] = num;
                return true;
            }
            // 将对应下标位置的数字-length成为负数，表示该位置已经有数字，待找到第二个相同的数字
            numbers[num] -= length;
        }
        return false;
    }


    public static void main(String args[]){
        int numbers[] = {2,3,1,0,2,5,3};
        int duplication[] = {0};
        System.out.println(duplicate(numbers,7,duplication)+" "+duplication[0]);
    }
}
