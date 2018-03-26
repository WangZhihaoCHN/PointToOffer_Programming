/*
*
* 题目描述：
*      请实现一个函数用来找出字符流中第一个只出现一次的字符。例如，当从字符流中只读出前两个字符"go"时，
* 第一个只出现一次的字符是"g"。当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"。
*
* */

import java.util.LinkedList;
import java.util.Queue;

public class Test {
    /*
    * 算法思路：
    *     在类中建立哈希表hashtable，包含256个元素，java默认初始化各个元素为0。设置一个index = 1。
    *     Insert()方法，读入ch字符，如果哈希表中对应下标的原始值为0，表示未出现过该字符，将其设为index，
    * 即hashtable[ch] = index，之后index++。如果该位置的值>0，表示已经出现过该字符，将其设为-1，
    * 之后不需要再考虑。
    *     FirstAppearingOnce()方法，只需要遍历哈希表，找到对应位置值(除-1外)最小的，返回对应下标即可。
    * 即，找到MIN(hashtable[i])，i字符即为所求。
    *
    *    考虑一个时间复杂度更小的方案，上述过程中，通过index来实现区分第一个只出现1次的字符，最终的寻找，
    * 依然需要遍历整个哈希表，找到值最小的以返回下标。我们可以设置一个队列，队列只插入第一次遇到的字符ch。
    * 当判断第一个只出现一次的字符时，只需要判断队首元素是否只出现一次，是则直接返回，不是则首元素出队列，
    * 继续查找。
    *
    *  */

    char []hashtable = new char[256];
    Queue<Character> queue = new LinkedList<>();

    //Insert one char from stringstream
    public void Insert(char ch) {
        // BitSet初始化元素为false，因此如果值为false，则是第一次或未出现
        if(hashtable[ch] == 0){
            hashtable[ch] = 1;
            queue.add(ch);
        }
        else
            hashtable[ch]++;
    }

    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce(){
        while(!queue.isEmpty()){
            // 不能直接取出第一个元素，这样会影响后续结果
            char ch = queue.element();
            if(hashtable[ch] == 1)
                return ch;
            else
                queue.poll();
        }
        return '#';
    }

    public static void main(String args[]){
        Test A = new Test();
        A.Insert('g');
        System.out.println(A.FirstAppearingOnce());
        A.Insert('o');
        System.out.println(A.FirstAppearingOnce());
        A.Insert('o');
        System.out.println(A.FirstAppearingOnce());
        A.Insert('g');
        System.out.println(A.FirstAppearingOnce());
        A.Insert('l');
        System.out.println(A.FirstAppearingOnce());
        A.Insert('e');
        System.out.println(A.FirstAppearingOnce());
    }
}
