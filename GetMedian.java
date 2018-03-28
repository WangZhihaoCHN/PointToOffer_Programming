/*
*
* 题目描述：
*     如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，
 * 那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，
 * 那么中位数就是所有数值排序之后中间两个数的平均值。
*
* */

import java.util.Comparator;
import java.util.PriorityQueue;

public class Test {
    /*
    * 算法思路：
    *     可以利用两个堆(大根堆和小根堆)来实现中位数的寻找。
    *     试想，随着数据流的不断插入，我们建立了相应的大根堆和小根堆。其中，
    * 小根堆存放的是已插入数据中较大的一半，大根堆存放的是已插入数据中较小的
    * 一半。这样的话，小根堆的顶点和大根堆的顶点，就是最接近数据流排序后中心
    * 的两个数。
    *     因此，这个问题可以如下解决：
    * 1. 建立大、小根两个堆；
    * 2. 当待插入的数字，是第奇数个的时候，数字最终应该进入小根堆。同理，当
    * 待插入的是第偶数个数据的时候，数字最终应该进入大根堆。
    * 3. 步骤2中，数据并不是直接进入相应的堆，而是应该需要进入小根堆的数据，
    * 需要先插入大根堆，待大根堆内部排序后，得到大根堆的顶部结点，插入到小根堆
    * 中。需要进入大根堆的数据同理。这样可以保证大根堆和小根堆的头结点，仍然是
    * 数据流中中部的两个结点。
    * 4. 需要返回的中位数，当数据流数目为奇数个，返回小根堆的头结点；为偶数个
    * 时，返回大根堆头结点数据和小根堆头结点数据的平均值。
    *
    *  */

    // 可以借助java的PriorityQueue来实现最大、最小堆
    // 小根堆
    private static PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    // 大根堆，PriorityQueue默认是一个小根堆，然而可以通过传入
    // 自定义的Comparator来实现大根堆，使用默认的初始容量11
    private static PriorityQueue<Integer> maxHeap = new PriorityQueue<>(15, new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2-o1;
        }
    });

    // 记录当前数据流是第几个数据(用于判断奇数个还是偶数个)
    private static int count = 1;

    public static void Insert(Integer num) {
        if(count%2 == 1){
            maxHeap.offer(num);
            minHeap.offer(maxHeap.poll());
        }else{
            minHeap.offer(num);
            maxHeap.offer(minHeap.poll());
        }
        count++;
    }

    public static Double GetMedian() {
        // 由于count在每次插入后会+1，因此判断共有奇数还是偶数的时候，需要-1
        // Double作为返回值，而插入的数据是Integer，所以采用new Double(Integer value)的方式实现需要
        if((count-1)%2 == 1)
            return new Double(minHeap.peek());
        // 如果除以2放在new Double()的括号内，将会先得到整数再转换Double，结果将会是直接舍弃小数
        else
            return new Double((minHeap.peek()+maxHeap.peek()))/2;
    }

    public static void main(String args[]) {
        Insert(6);
        Insert(3);
        System.out.println(GetMedian());
        Insert(9);
        Insert(2);
        System.out.println(GetMedian());
        Insert(1);
        System.out.println(GetMedian());
    }
}
