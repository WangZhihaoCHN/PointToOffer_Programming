/*
*
* 题目描述：
*      请实现一个函数，用来判断一颗二叉树是不是对称的。
* 注意，如果一个二叉树同此二叉树的镜像是同样的，定义
* 其为对称的。
*
* */

public class Test {
    /*
    * 算法思路：
    *     对称的一棵二叉树，需要根节点具有左右子树，且左子树的左子树和
    * 右子树的右子树相同，左子树的右子树和右子树的左子树相同。满足上述
    * 条件，二叉树即对称。因此，采用递归的方法实现。
    *
    * 注意！！！因为要递归比较的是左子树的右子树和右子树的左子树，因此，
    * 递归方法必须重写，不能和原方法融合。
    *  */
    public static boolean isSymmetrical(TreeNode pRoot) {
        // 只有一个根节点，对称
        if(pRoot == null)
            return true;
        return rootSymmetrical(pRoot.left, pRoot.right);
    }

    // 递归判断左右子树是否对称的方法
    public static boolean rootSymmetrical(TreeNode pL, TreeNode pR) {
        if(pL == null)
            return pR == null;
        if(pR == null)
            return pL == null;
        if(pL.val != pR.val)
            return false;
        return rootSymmetrical(pL.left,pR.right) && rootSymmetrical(pL.right,pR.left);
    }

        public static void main(String args[]) {

    }
}
