/*
*
* 题目描述：
*      一个链表中包含环，请找出该链表的环的入口结点。
*
* */

public class Test {
    /*
    * 算法思路：
    *     通过一快一慢两个指针，慢指针从头节点开始每次走一步，快指针从头结点开始，每次走两步。
    * 在某一时刻，快、慢两指针会在同一节点相遇，此时快指针比满指针多走了k次环的路程。
    *
    * ——>为什么一定能追上？快指针每次走两步，满指针每次走一步，快指针在每次一步步的追慢指针，
    * 环长度有限，所以一定能追上。
    * 1：快指针与慢指针之间差一步。此时继续往后走，慢指针前进一步，快指针前进两步，两者相遇。
    * 2：快指针与慢指针之间差两步。此时唏嘘往后走，慢指针前进一步，快指针前进两步，两者之间相差一步，转化为第一种情况。
    * 3：快指针与慢指针之间差N步。此时继续往后走，慢指针前进一步，快指针前进两步，两者之间相差(N+1-2)-> N-1步。
    *
    * 为方便描述，我们如下定义：
    *     x是链表起点到环入口的距离(结点数量)，y是环入口到相遇结点的距离，n是环的长度。
    *     已知S快=2S慢，且S快=x+k*n+y，S慢=x+y，因此x+k*n+y=2x+2y。所以，x=k*n-y。
    *     x的表达式进一步分割为，x=(k-1)*n+(n-y)。其中k-1位在环中转的圈数，n-y正好是从相遇结点
    * 走回环入口的距离。
    *     此时，如果将快指针指回链表头部，并改为一次直走一步，满指针在相遇位置开始，同步进行移动。
    * 当快指针走过x距离时，到达了环的入口。而此时，慢指针走过了n-y到达环的头部并且又转了k-1圈。可见，
    * 两个指针的下次相遇，就是在环的入口节点。
    *     如果想求环的长度，只需要一个指针在入口处不动，另一指针一次一步并计数，等重回入口处是，转过一圈，
    * 计数值为环的长度。
    *
    *  */
    public ListNode EntryNodeOfLoop(ListNode pHead) {
        // 异常输入
        if(pHead == null || pHead.next == null)
            return null;

        // 一快一慢两个指针
        ListNode pSlow = pHead, pFast = pHead;
        while(pSlow.next != null && pFast.next != null){
            // 慢指针每次走一步，快指针每次走两步
            pSlow = pSlow.next;
            pFast = pFast.next.next;
            // 快指针追上慢指针
            if(pSlow == pFast){
                // 快指针移回头结点，与慢指针同步移动
                pFast = pHead;
                while (true){
                    // 再次相遇，即为环的入口
                    if(pFast == pSlow)
                        return pSlow;
                    pFast = pFast.next;
                    pSlow = pSlow.next;
                }
            }
        }
        return null;
    }

    public static void main(String args[]) {

    }
}
