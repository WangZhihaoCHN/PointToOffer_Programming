/*
*
* 题目描述：
*      在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，
* 重复的结点不保留，返回链表头指针。 例如，链表1->2->3->3->4->4->5
* 处理后为 1->2->5。
*
* */

public class Test {
    /*
    * 算法思路：
    *     通过迭代的思想实现：
    * 1.如果链表有0个和1个结点，则直接返回pHead。
    * 2.如果头节点是重复结点，则通过一个指针pNode，不断指向下一个结点，找到最近
    * 的一个与头结点val不同的结点，就从当前pNode结点开始迭代方法。
    * 3.如果头结点不是重复节点，则保留当前头结点，从下一个节点开始迭代方法。
    *
    * 注意审题！！！是删除掉所有的存在重复的结点，即如果有多个2就都删除掉。
    *  */
    public static ListNode deleteDuplication(ListNode pHead) {
        // 异常输入
        if(pHead == null || pHead.next == null)
            return pHead;

        ListNode pNode = pHead.next;
        if(pHead.val == pNode.val){             // 头结点和后一个结点是重复节点
            // 找到第一个与头结点val值不相同的结点
            // 判断pNode是否为空需要放在前面，因为pNode=null时，没有.val
            while(pNode != null && pNode.val == pHead.val)
                pNode = pNode.next;
            // 将该节点作为新的头接结点开始迭代
            return deleteDuplication(pNode);
        }else{                                  // 头结点和后一个结点不是重复节点
            // 迭代除头结点外的剩余链表部分，并链接到头结点上
            pHead.next = deleteDuplication(pHead.next);
            // 返回该没有重复的头结点
            return pHead;
        }
    }

    public static void main(String args[]) {
        ListNode p1 = new ListNode(1);
        ListNode p2 = new ListNode(1);
        ListNode p3 = new ListNode(1);
        ListNode p4 = new ListNode(1);
        ListNode p5 = new ListNode(1);
        ListNode p6 = new ListNode(1);
        ListNode p7 = new ListNode(1);
        p1.next = p2;
        p2.next = p3;
        p3.next = p4;
        p4.next = p5;
        p5.next = p6;
        p6.next = p7;
        p7.next = null;

        ListNode pHead = deleteDuplication(p1);
        while (pHead != null){
            System.out.print(pHead.val + " ");
            pHead = pHead.next;
        }
    }
}
