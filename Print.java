/*
*
* 题目描述：
*      从上到下按层打印二叉树，同一层结点从左至右输出。每一层输出一行。
*
* */
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Test {
    /*
    * 算法思路：
    *     层次遍历二叉树。
    *     使用一个队列保存每层节点的子结点，入队列顺序为先左儿子后右儿子。
    *     在树的每一层，记录队列中结点的个数num，然后依次取出上一层的父结点，
    * 打印val值，并将其子节点入队列。当取出了num个结点后，本层结束，重复上述
    * 步骤，直至完成。
    *
    *  */
    public static ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
        // 异常输入
        if(pRoot == null)
            return ret;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(pRoot);

        ArrayList<Integer> line;
        int size;
        TreeNode node;
        while(!queue.isEmpty()){
            size = queue.size();
            line = new ArrayList<>();
            for(int i=0; i<size; i++){
                node = queue.poll();
                line.add(node.val);
                System.out.print(node.val+" ");
                if(node.left!=null)
                    queue.offer(node.left);
                if(node.right!=null)
                queue.offer(node.right);
            }
            ret.add(line);
            System.out.println();
        }

        return ret;
    }

    public static void main(String args[]) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        Print(t1);
    }
}
