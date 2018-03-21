/*
*
* 题目描述：
*      给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],
* 其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。不能使用除法。
*
* */


public class Test {
    /*
    * 算法思路：
    *    该题中B[i]不是B[i-1]的基础上，乘某个数得来的，因此不能使用连乘的方法；
    * 又由于不能使用除法，因此不能先计算A[0]~A[n-1]的连乘积S，然后每个B[i]=S/A[i]
    * 的方法。
    *
    *    考虑如下矩阵图：
    * B数组              矩阵
    * B0  | 1    A1   A2   …    An-2   An-1
    * B1  | A0   1    A2   …    An-2   An-1
    * B2  | A0   A1   1    …    An-2   An-1
    * ……  | A0   A1   …    1    An-2   An-1
    * Bn-2| A0   A1   …    An-3 1      An-1
    * Bn-1| A0   A1   …    1    An-2   1
    *    根据此矩阵可以发现，通过对角线上的1，将该矩阵分为了两部分。
    * 而计算B[i]时，也可以分两部分计算连乘。首先是下三角的部分，
    * 从上向下满足 B[i] = B[i-1] * A[i-1]。之后是上三角的部分，
    * 从下向上满足 temp[j] = A[j+1] * temp[j+1]，B[j] *= temp[j]。
    *    综上，可得结果。
    *
    *  */
    public static int[] multiply(int[] A) {
        // 异常输入
        if(A == null || A.length == 0)
            return null;

        // 初始化B数组
        int B[] = new int[A.length];
        B[0] = 1;

        // 计算下三角
        for(int i=1; i<A.length; i++){
            B[i] = B[i-1] * A[i-1];
        }

        // 计算上三角
        int temp = 1;
        for(int j=A.length-2; j>=0; j--){
            temp = temp * A[j+1];
            B[j] *= temp;
        }
        return B;
    }


    public static void main(String args[]){
        int A[] = {1,2,3,4,5};
        int B[] = multiply(A);
        for (int num : B) {
            System.out.println(num + " ");
        }
    }
}
