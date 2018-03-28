/*
*
* 题目描述：
*      请实现两个函数，分别用来序列化和反序列化二叉树。
*
* */
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class Test {
    /*
    * 算法思路：
    *     序列化：
    *     先序遍历，递归思想。利用StringBuffer储存结点的val进行序列化。
    * 当前结点为null时，使用"#"占位，当不是时使用当前结点的val值进行添加。
    * 利用","符号隔离不同结点val值。将当前结点的左、右儿子，也按照上述步骤
    * 进行序列化。
    *
    *     反序列化：
    *     带遍历序号的先序遍历，递归思想。将序列化的字符串按","进行分割，
    * 形成字符串数组strs[]。通过一个全局变量index，初始化为-1，没遍历一个结点，
    * 进行index++，用于表示strs数组中，index下标位置的字符串为当前结点的val值。
    * 当strs[index]为"#"时，表示该结点为空，立即返回。否则，按照以上步骤，继续
    * 遍历当前结点的左、右儿子，继续进行反序列化。
    *
    *  */
    public static String Serialize(TreeNode root) {
        // 异常输入
        if(root == null)
            return "";

        // 构架序列化返回值String
        StringBuffer sb = new StringBuffer();
        MySerialize(root, sb);
        return sb.toString();
    }

    public static void MySerialize(TreeNode root, StringBuffer sb) {
        // 遇到相应结点是空时，用#进行补位
        if(root == null){
            sb.append("#,");
            return;
        }

        // 序列化字符串中添加当前结点的val，并用","进行隔断
        sb.append(root.val);
        sb.append(",");

        // 继续先序遍历，进行树的序列化
        MySerialize(root.left, sb);
        MySerialize(root.right, sb);
    }

    // 用于记录当前结点对应字符串数组中的下标，
    // 字符串数组即为序列化结果按照","进行分割的结果
    public static int index = -1;
    public static TreeNode Deserialize(String str) {
        // 异常输入
        if(str == null || str.equals(""))
            return null;

        String []strs = str.split(",");
        return MyDeserialize(strs);
    }

    public static TreeNode MyDeserialize(String[] strs) {
        index++;
        if(strs[index].equals("#"))
            return null;

        TreeNode root = new TreeNode(Integer.parseInt(strs[index]));
        root.left = MyDeserialize(strs);
        root.right = MyDeserialize(strs);
        return root;
    }

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
        /*TreeNode t1 = new TreeNode(5);
        TreeNode t2 = new TreeNode(4);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(2);
        t1.left = t2;
        t2.left = t3;
        t3.left = t4;
        TreeNode a = Deserialize(Serialize(t1));

        Print(a);*/
        Test t1 = new Test();
        t1.index=1;
        System.out.println(t1.index);

        Test t2 = new Test();
        System.out.println(t2.index);
    }
}
