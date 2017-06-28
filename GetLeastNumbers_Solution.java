import java.util.ArrayList;

/*
*
* 题目描述：
* 输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4。
*
* */

public class Test {
    /*
    * 算法思路：
    * 我们可以先创建一个大小为k数据最小堆来储存最小的k个数字，接下来我们每次从输入的n个整数中读入一个数。
    * 如果容器中已有的数字少于k个，则直接把这次读入的整数放入容器之中；如果容器中已有k个数字了，也就是堆已满，
    * 此时我们不能再插入新的数字而只能替换已有的数字。找出这已有的k个数中的最大值，然后拿这次待插入的证书和
    * 最大值进行比较。如果待插入的值比当前以后的最大值小，则用这个数替换当前以后有的最大值；如果待插入的值
    * 比以前已有的最大值还要打，那么这个数不可能是最小的k整数之一，于是我们可以抛弃这个证书。
    * 我们可以选择用不同的二叉树来实现这个思想，由于每次都需要找到k个整数中的最大数字，我们很容易想到用最大堆。
    * */

    public static ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {

        //返回值
        ArrayList<Integer> result = new ArrayList<Integer>();

        //异常输入
        if(input==null || input.length==0 || input.length<k)
            return result;


        //构建大顶堆（过程就是针对每一个非叶子节点，自底向上的利用adjustHeap递归调整二叉树为堆）
        for(int i=k/2-1; i>=0; i--)             //长度为k的数组，最后一个非叶子节点的为k/2
            //堆排序中的MAX-HEAPIFY递归函数，维护i为根的子树的堆性质
            adjustHeap(input,i,k-1);

        for(int i : input)
            System.out.print(i + " ");
        System.out.println();

        /*
            此时，前k个元素的大顶堆已经构建好了，剩下的就是其余的和大顶堆的最大值比较了。
            我们只有可能要做的主要有三件事情：
            1.在k个证书中找到最大数（即堆顶）；
            2.有可能需要删除最大数（即改变堆顶）；
            3.有可能要插入一个新的数字（即小于堆顶的元素）。
        */
        for(int i=k; i<input.length; i++){
            //后续元素中有小于堆顶的值，因此需要替换掉堆顶，并进行调整保证前k个元素仍然为堆
            if(input[i] < input[0]){
                swap(input, 0, i);
                //交换后可能引起堆特性失效，需要重新调整
                adjustHeap(input,0,k-1);
            }
        }

        //我们将调整好的前k个数放进链表里面
        for(int i=0;i<k;i++){
            result.add(input[i]);
        }
        return result;
    }

    /*交换数组中指定两个位置的元素*/
    public static void swap(int[] input, int i, int j){
        int tmp = input[i];
        input[i] = input[j];
        input[j] = tmp;
    }

    /*维护最大堆的性质，输入数组input、根节点i以及数组的长度，调整以i为根的二叉树为最大堆*/
    public static void adjustHeap(int[] input,int i,int k){
        //计算左右儿子的下标
        int left = 2*i + 1;
        int right = left + 1;
        //子节点越界k，停止递归
        if(left > k)
            return;
        //保存父节点与左右两个子节点中最大节点的下标
        int largest = -1;

        //找到三个节点中最大的节点
        if(left <= k && input[i] > input[left])
            largest = i;
        else largest = left;
        if(right <= k && input[largest] < input[right])
            largest = right;

        /*
            当最大节点不是根节点时，交换根节点与相应的最大节点。
            同时由于交换后，左/右子树可能不满足堆性质，因此递归进行该过程
        */
        if(largest != i){
            swap(input, i, largest);
            adjustHeap(input, largest, k);
        }
    }


    public static void main(String args[]){
        int a[] = {4,5,1,6,2,7,3,8};
        ArrayList<Integer> result = GetLeastNumbers_Solution(a,4);
        for(Integer i : result)
            System.out.print(i + " ");
    }
}
