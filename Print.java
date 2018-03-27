/*
*
* 题目描述：
*      请实现一个函数按照之字形打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右至左的顺序打印，
* 第三行按照从左到右的顺序打印，其他行以此类推。
*
* */
import java.util.ArrayList;
import java.util.Stack;

public class Test {
    /*
    * 算法思路：
    *     利用两个栈S1和S2分别存放奇数层和偶数层的树节点。
    *  */
    public static ArrayList<ArrayList<Integer> > Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();

        // 异常输入
        if(pRoot == null)
            return ret;

        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();
        int layer = 1;
        s1.push(pRoot);


        while (!s1.empty() || !s2.empty()){
            ArrayList<Integer> temp = new ArrayList<>();
            TreeNode node;
            if(layer%2 == 1){           // 奇数层
                // 从栈中依次取所有元素，打印对应val值，并将左儿子-右儿子压入偶数栈s2
                while(!s1.empty()){
                    node = s1.pop();
                    if(node == null)
                        continue;
                    temp.add(node.val);
                    s2.push(node.left);
                    s2.push(node.right);
                }
                //最后一行存在叶子节点的儿子均为空的状况，栈可以压入null占位，因此需要避免打印这样的空行
                if(temp.isEmpty())
                    continue;
                // 将当前行加入返回值中，并将层数加一
                ret.add(temp);
                layer += 1;
            }else{                      // 偶数层
                // 从栈中依次取所有元素，打印对应val值，并将右儿子-左儿子压入偶数栈s2
                while(!s2.empty()){
                    node = s2.pop();
                    if(node == null)
                        continue;
                    temp.add(node.val);
                    s1.push(node.right);
                    s1.push(node.left);
                }
                if(temp.isEmpty())
                    continue;
                // 将当前行加入返回值中，并将层数加一
                ret.add(temp);
                layer += 1;
            }
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
