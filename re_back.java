package New_Calculator;

public class re_back extends SimpleCaculator{
    public re_back() {
        String str = text.getText();

        int len = str.length();
        //-----------------数字的回退数字是有没有问题的因为没有进栈，但是理论上符号好像进栈了，但是实验了栈顶出栈，但问题解决不了！！！----------------------
//        if(str == "+"||str == "-"||str == "*"||str == "/"||str == "=")
//        {
//            str_stack.pop();
//            title =1;
////            System.out.println();
//        }
        text.setText(str.substring(0, len - 1));

        if (text.getText().length() <= 1)
            flg = 1;

    }
}
