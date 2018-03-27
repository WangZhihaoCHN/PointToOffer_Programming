/*
*
* 题目描述：
*      给定一个二叉树和其中的一个结点，请找出中序遍历顺序的
* 下一个结点并且返回。注意，树中的结点不仅包含左右子结点，同时
* 包含指向父结点的指针。
*
* */

public class Test {
    /*
    * 算法思路：
    *     分析中序过程遍历，该问题的解决可归纳为如下几种情况：
    *
    * 1. 该结点是非叶子结点，此时又存在下面两种情况：
    *    1) 该结点有右子树
    *    此时，只需要设置一个指针，沿着该节点右子树不断寻找左儿子结点即可。
    *    2) 该节点没有右子树
    *    此时，需要一个指针指向父结点，如果当前结点正好是其父结点的左子树，
    * 则可以直接返回。否则，继续向上寻找父结点，并重复上述过程，判断是否为
    * 左子树。找到这样的父结点，则返回，否则，中序遍历到当前结点已结束。
    *
    * 2. 该结点是叶子结点，寻找过程同1.2)
    *
    *  */
    public TreeLinkNode GetNext(TreeLinkNode pNode){
        // 异常输入
        if(pNode == null)
            return null;

        TreeLinkNode p;
        // 有右子树
        if(pNode.right != null){
            // 寻找右子树的最左儿子结点
            p = pNode.right;
            while (p.left != null)
                p = p.left;
            return p;
        }else{      // 其他两种情况，找该节点所位于的左子树的父结点
            p = pNode.next;
            while (p != null && p.left != pNode){
                pNode = p;
                p = p.next;
            }
            return p;
        }
    }

    public static void main(String args[]) {

    }
}
