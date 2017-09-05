package com.peanut.offer;

/**
 * Created by WangZhihao on 2017/8/29.
 *
 * 题目描述：
 *    在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
 *    输入一个数组,求出这个数组中的逆序对的总数P。并将P对1000000007取模的结果输出。
 *    即输出P%1000000007
 *
 * 输入描述：
 *    题目保证输入的数组中没有的相同的数字
 *    数据范围：
 *        对于%50的数据,size<=10^4
 *        对于%75的数据,size<=10^5
 *        对于%100的数据,size<=2*10^5
 *
 */
public class Solution {
    /**
     *
     * 算法思路：
     *     利用归并排序的思想。
     *     我们以数组{7,5,6,4}为例来分析统计逆序对的过程：
     * (1)把长度为4的数组分解成两个长度为2的子数组；
     * (2)把长度为2的数组分解成两个成都为1的子数组；
     * (3)把长度为1的子数组 合并、排序并统计逆序对；
     * (4)把长度为2的子数组合并、排序，并统计逆序对；
     *
     *    在（1）和（2）中，我们先把数组{7,5,6,4}分解成两个长度为2的子数组，
     * 再把这两个子数组分别拆成两个长度为1的子数组。接下来一边合并相邻的子数组，
     * 一边统计逆序对的数目。在第一对长度为1的子数组{7}、{5}中7大于5，因此(7,5)
     * 组成一个逆序对。同样在第二对长度为1的子数组{6}、{4}中也有逆序对(6,4)。
     * 由于我们已经统计了这两对子数组内部的逆序对，因此需要把这两对子数组排序，
     * 以免在以后的统计过程中再重复统计。
     *
     * */

    /* 归并函数：将两个子数组([start…mid]和[mid+1…end])合并成增序数组，统计合并过程中，两数组
    间的逆序对数目(子数组内部的逆序对数合并前已统计)，返回该值。归并过程从两个数组头开始依次取最小
    放置与新数组中，其中增加计算两数组间的逆序对数目的过程。 */
    public static int Merge(int []array, int start, int mid, int end, int []copy){
        /* 以mid为中间值，划分数组为[start…mid]和[mid+1…end],然后i指向左数组，j指向右数组，
        copy作为归并结果进行归并。合并时从copy数组第一个元素开始放置两数组的最小值，因为当出现前
        面的数组值array[i]大于后面数组值array[j]时，则前面数组array[i]~array[mid]都是大于
        array[j]的，此时count+=mid-i+1。 */

        int count = 0;      //合并过程的逆序对数
        /* i指向左数组的第一个元素，j指向右数组的第一个元素，k指向归并排序后的辅助数组第一个元素 */
        int i = start, j = mid+1, k = start;

        while(i<=mid && j<=end){
            if(array[i] > array[j]){
                copy[k++] = array[j];
                j++;
                count += mid-i+1;
                if(count>1000000006)
                    count = count % 1000000007;
            }else{
                copy[k++] = array[i];
                i++;
            }
        }
        while(i <= mid)
            copy[k++] = array[i++];

        while(j <= end)
            copy[k++] = array[j++];

        for(int x=start; x<=end; x++){
            array[x] = copy[x];
            System.out.print(array[x]+" ");
        }
        System.out.println("\n计数"+count);

        return count;
    }

    /**
     *    该函数采用归并排序的思想，通过递归的方式，计算整个数组中的逆序对数量。
     * 考虑到节约存储空间，因此将数组一分为二不再重新开辟空间，而是通过start和
     * end标注，节约空间。通过辅助数组copy记录当前归并排序好的数组，防止篡改原
     * 数组。
     * @param array 现阶段归并排序的原数组
     * @param start 归并排序递归中，子数组的起始位置
     * @param end 归并排序递归中，子数组的结束位置
     * @param copy 为避免重复申请内存空间，用于归并的辅助数组
     * @return 当对前子数组的逆序对数目
     */
    public static int myInversePairs(int []array, int start, int end, int []copy){
        /* 归并排序过程中，子数组只有一个元素时，逆序对数为0，返回。 */
        if(start == end)
            return 0;

        /* 归并排序过程中，子数组大于等于两个元素时，将该数组一分为二，递归
        调用myInversePairs()函数，分别计算左右两个子数组的逆序对数，然后
        调用Merge()归并两个子数组，并计算该过程产生的逆序对数。 */
        int mid = (start+end)/2;
        int left = myInversePairs(array,start,mid,copy)%1000000007;
        int right = myInversePairs(array,mid+1,end,copy)%1000000007;
        int pairsCount = Merge(array,start,mid,end,copy);
        int total = (left+right+pairsCount)%1000000007;
        System.out.println("left:"+left+", right:"+right+", pairsCount:"+pairsCount);
        return total;
    }

    public static int InversePairs(int [] array) {
        //特殊输入判断
        if(array == null || array.length == 0 || array.length == 1)
            return 0;
        //创建辅助数组copy[]，以防止归并排序过程中额外申请内存空间
        int copy[] = new int[array.length];
        for(int i=0; i<copy.length; i++)
            copy[i] = array[i];
        return myInversePairs(array, 0, array.length-1, copy);
    }

    public static void main(String [] args){

        int in[] = {1,2,3,4,5,6,7,0};
        System.out.println(InversePairs(in));
    }
}
