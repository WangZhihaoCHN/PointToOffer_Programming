package com.peanut.offer;

/**
 * Created by WangZhihao on 2017/4/9.
 *
 * 题目描述：
 * 输入两个链表，找出它们的第一个公共结点。
 *
 */
public class Solution {
    /**
     *
     * 算法思路：
     * 如果两个链表有公共节点，那么两链表的后半部分长度相同，且公共节点一定位于链表尾部向
     * 前至公共部分头部这一段区间内。因此，我们可以首先求出两个链表的长度差，然后让长链表
     * 指针先步进差值的长度，然后再让两个链表的指针一起前进，并比较大小，扎到公共节点。
     *
     * */

    public static ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {

        /* 非法输入检测 */
        if (pHead1 == null || pHead2 == null)
            return null;
        ListNode p1 = pHead1;
        ListNode p2 = pHead2;

        while (p1 != null && p2 != null){
            p1 = p1.next;
            p2 = p2.next;
        }
        
        /*
         * 如果p1不空，则pHead1链表长，p1到链表尾部的长度，即为差值.
         * 因此只需要p1继续向后移，而将p2重置为pHead2，然后跟随p1一
         * 起后移。当p1到达尾部时，p2的位置恰好两链表从尾部起长度相等
         * 的部分。   p2不为空的情况，同理。
         * */
        if (p1 != null){
            while (p1 != null){
                pHead1 = pHead1.next;
                p1 = p1.next;
            }
        }else if (p2 != null){
            while (p2 != null){
                pHead2 = pHead2.next;
                p2 = p2.next;
            }
        }

        //此时，pHead1和pHead2均指在了相同长度处，因此接下来只需要步进、比较即可。
        while (pHead1 != null){
            if (pHead1 == pHead2)
                return pHead1;
            pHead1 = pHead1.next;
            pHead2 = pHead2.next;
        }
        return null;
    }

    public static void main(String [] args){

        /**
         * 两个链表在标号为6的节点交汇
         * 1—2-3
         *       > 6-7
         *   4-5
         **/

        ListNode n7 = new ListNode(7);
        ListNode n6 = new ListNode(6);
        n6.next = n7;
        ListNode n5 = new ListNode(5);
        n5.next = n6;
        ListNode n4 = new ListNode(4);
        n4.next = n5;
        ListNode n3 = new ListNode(3);
        n3.next = n6;
        ListNode n2 = new ListNode(2);
        n2.next = n3;
        ListNode n1 = new ListNode(1);
        n1.next = n2;

        System.out.println(FindFirstCommonNode(n1,n4).val);
    }
}
