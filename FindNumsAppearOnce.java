/*
*
* 题目描述：
* 把只包含因子2、3和5的数称作丑数（Ugly Number）。例如6、8都是丑数，但14不是，因为它包含因子7。
* 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
*
* */


import java.util.HashMap;

public class Test {

    /*
    * 算法思路：
    * 我们可以定义哈希表的键值（Key）是数组中的数字，而值（Value）是该数字出现的次数。
    * 同时我们还需要从头开始扫描字符串两次。第一次扫描字符串时，每扫描到一个字符就在哈希
    * 表的对应项中把次数加1.接下来的第二次扫描时，每扫描到一个数字就能从哈希表中得到该数
    * 字出现的次数。这样就很容易能找到只出现一次的数字。
    * */
    public static void FindNumsAppearOnce(int [] array, int num1[], int num2[]) {
        // 异常输入
        if(array==null || array.length<=0)
            return;
        // 建立哈希表
        HashMap<Integer, Integer> hash = new HashMap<>();
        hash.clear();

        // 遍历数组元素，并导入哈希表，如果已存在相应key值，Value赋值2；否则新引入<Key，Value>
        for(int i=0; i<array.length; i++){
            if(hash.containsKey(array[i]))
                hash.put(array[i],2);       // put会覆盖掉原来的<Key，Value>对
            else
                hash.put(array[i],1);
        }

        // 遍历哈希表，得到两个出现次数为1的数字
        boolean flag = false;               // 用于区分num1和num2
        for(Integer i : hash.keySet()){
            if(hash.get(i) == 1){
                if (flag == false){
                    num1[0] = i;
                    flag = true;
                }else{
                    num2[0] = i;
                }
            }
        }
    }

    public static void main(String args[]){
        int a[] = {1,2,2,1,3,4};
        int num1[] = new int[1];
        int num2[] = new int[1];
        FindNumsAppearOnce(a, num1, num2);
        System.out.println(num1[0] + " " + num2[0]);
    }
}
