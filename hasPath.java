/*
*
* 题目描述：
*     请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
* 路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向左，向右，向上，向下移动一个格子。
* 如果一条路径经过了矩阵中的某一个格子，则该路径不能再进入该格子。 例如 a b c e s f c s a d e e
* 3*4矩阵中包含一条字符串"bcced"的路径，但是矩阵中不包含"abcb"路径，因为字符串的第一个字符b占据了
* 矩阵中的第一行第二个格子之后，路径不能再次进入该格子。
*
* */

public class Test {
    /*
    * 算法思路：
    *     这是一个可以用回朔法解决的典型题。
    *     首先，在矩阵中任选一个格子作为路径的起点。如果路径上的第i个字符不是ch，那么这个格子不可能处在路径上的第i个位置。
    * 如果路径上的第i个字符正好是ch，那么往相邻的格子寻找路径上的第i+1个字符。除在矩阵边界上的格子之外，其他格子都有4个相邻的格子。
    * 重复这个过程直到路径上的所有字符都在矩阵中找到相应的位置。
    * 　　由于回朔法的递归特性，路径可以被开成一个栈。当在矩阵中定位了路径中前n个字符的位置之后，在与第n个字符对应的格子的周围都没有找到第n+1个
    * 字符，这个时候只要在路径上回到第n-1个字符，重新定位第n个字符。
    * 　　由于路径不能重复进入矩阵的格子，还需要定义和字符矩阵大小一样的布尔值矩阵，用来标识路径是否已经进入每个格子。 当矩阵中坐标为
    * （row,col）的格子和路径字符串中相应的字符一样时，从4个相邻的格子(row,col-1),(row-1,col),(row,col+1)以及(row+1,col)中去定位
    * 路径字符串中下一个字符如果4个相邻的格子都没有匹配字符串中下一个的字符，表明当前路径字符串中字符在矩阵中的定位不正确，我们需要回到
    * 前一个，然后重新定位。
    * 　　一直重复这个过程，直到路径字符串上所有字符都在矩阵中找到合适的位置
    *
    *  */

    public static boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        // 异常输入
        if(matrix == null || matrix.length <= 0 || str == null || str.length <= 0 || matrix.length != rows*cols
                || rows <= 0 || cols <= 0 || rows*cols < str.length)
            return false;

        // 防止重复路径，为整个矩阵的创建一个布尔值矩阵，标识路径是否已经访问过该位置字符
        boolean[] visited = new boolean[rows * cols];

        // 记录当前已经搜索到哪些元素了
        int[] strIndex = new int[1];
        strIndex[0] = 0;

        // 路径可能从字符矩阵的任何一个位置作为开始
        for(int i=0; i<rows; i++)
            for(int j=0; j<cols; j++)
                if(myHasPath(matrix,rows,cols,str,i,j,visited,strIndex))
                    return true;
        return false;
    }

    /**
     *  char[] matrix: 在其上寻找字符串的符号矩阵
     *  int rows: 矩阵的行
     *  int cols: 矩阵的列
     *  char[] str: 欲寻找的字符串
     *  int nextRow: 继续寻找字符串剩余部分的起始行
     *  int nextCol: 继续寻找字符串剩余部分的起始列
     *  Boolean[] visited: 矩阵中已经访问过的情况(防止重复访问统一矩阵中的元素)
     *  int[] strIndex: (只有一个元素的数组，因为java的方法只能使形参)记录当前已经寻到字符串的下标，下一步将从该位置起，继续寻找剩余部分
     */
    public static boolean myHasPath(char[] matrix, int rows, int cols, char[] str, int nowRow, int nowCol, boolean[] visited, int[] strIndex) {
        // 返回值，当有任一方向满足条件时，修改为true
        boolean flag = false;

        // 当前行列位置没有超出矩阵限制，且当前元素未被访问过，且当前元素就是需要找的元素时，继续向上下左右四个方向寻找
        if(nowCol>=0 && nowCol<cols && nowRow>=0 && nowRow<rows && !visited[nowRow*cols+nowCol] && matrix[nowRow*cols+nowCol] == str[strIndex[0]]){
            // 继续寻找其他方向上的字符串下一个位置的字符
            strIndex[0]++;
            // 一直匹配到了字符串的末尾，因此返回true
            if(strIndex[0] == str.length)
                return true;
            // 更新访问过情况
            visited[nowRow*cols+nowCol] = true;

            flag = myHasPath(matrix,rows,cols,str,nowRow-1,nowCol,visited,strIndex) ||
                    myHasPath(matrix,rows,cols,str,nowRow+1,nowCol,visited,strIndex) ||
                    myHasPath(matrix,rows,cols,str,nowRow,nowCol-1,visited,strIndex) ||
                    myHasPath(matrix,rows,cols,str,nowRow,nowCol+1,visited,strIndex);

            // 当没有找到合适的路径时，需要回溯，因此标记字符串下标的数字需要复原
            if(!flag){
                strIndex[0]--;
                visited[nowRow*cols+nowCol] = false;
            }
        }
        return flag;
    }

    public static void main(String args[]) {
        char[] matrix = {'a','b','c','e','s','f','c','s','a','d','e','e'};
        boolean flag = hasPath(matrix,3,4,"bcced".toCharArray());
        System.out.println(flag);
    }
}
