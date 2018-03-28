/*
*
* 题目描述：
*     给定一颗二叉搜索树，请找出其中的第k大的结点。例如，
* 5 / \ 3 7 /\ /\ 2 4 6 8 中，按结点数值大小顺序第三个结点的值为4。
*
* */

public class Test {
    /*
    * 算法思路：
    *     对于二叉搜索树，其中序遍历得到的结果，第k个就是第k大的结点。
    *     递归实现，通过一个全局变量记录当前便利到的结点的下标，当到达
    * 所要求的k时，进而返回。
    *
    *  */
    public static int index = 0;
    public static TreeNode KthNode(TreeNode pRoot, int k){
        // 迭代到当前结点时为空，直接返回，不计数
        if(pRoot != null){          // 不为空时，进入该节点，首先迭代左儿子(中序遍历)
            TreeNode node = KthNode(pRoot.left, k);
            // 遍历过程中，出现了index==k，即在当前结点的左儿子中，
            // 有中序遍历的k个结点，直接返回
            if(node != null)
                return node;

            // 左儿子遍历没有，计算当前结点在中序遍历中的序号，
            // 如果正好就是第k个，则直接返回
            index++;
            if(index == k)
                return pRoot;

            // 左儿子遍历和当前结点都不是第k个，同左儿子一样道理，
            // 寻找右儿子遍历中是否有第k个序号的数
            node = KthNode(pRoot.right, k);
            if(node != null)
                return node;
        }
        return null;
    }

    public static void main(String args[]) {
        TreeNode t1 = new TreeNode(5);
        TreeNode t2 = new TreeNode(4);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(2);
        t1.left = t2;
        t2.left = t3;
        t3.left = t4;
        System.out.println(KthNode(t1, 3).val);
    }
}
