/*
*
* 题目描述：
*     牛客最近来了一个新员工Fish，每天早晨总是会拿着一本英文杂志，
* 写些句子在本子上。同事Cat对Fish写的内容颇感兴趣，有一天他向Fish
* 借来翻看，但却读不懂它的意思。例如，“student. a am I”。
* 后来才意识到，这家伙原来把句子单词的顺序翻转了，正确的句子应该是
* “I am a student.”。Cat对一一的翻转这些单词顺序可不在行，你能
* 帮助他么？
*
* */


public class Test {
    /*
    * 算法思路：
    *     利用" "将原字符串分割，然后从后向前添加到新的字符串中。
    *
    * 注意！！特别需要注意异常输入。例如输入为" "，不应该返回null
    * 或""，而是应该返回原串。
    *  */
    public static String ReverseSentence(String str) {
        //异常输入
        if(str.trim().length() == 0 || str == null){
            return str;
        }

        String ret = "";
        String[] words = str.split(" ");
        for(int i = words.length - 1; i > 0; i--){
            ret += words[i];
            ret += " ";
        }
        ret += words[0];

        return ret;
    }


    public static void main(String args[]){
        System.out.println(ReverseSentence(""));
    }
}
