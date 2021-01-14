package New_Calculator;

public class number  extends  SimpleCaculator{
    public number(String num) {
        if(text.getText().equals("+")||text.getText().equals("-")||text.getText().equals("*")||text.getText().equals("/")||text.getText().equals("=")||text.getText().equals("0"))
            text.setText("");

        //--------文本域中的首个字符不为“.”-----------
        if (text.getText().equals("") && num.equals(".")) {
            text.setText("0.");
        }
        else if ( !num.equals("."))
        {

            text.setText(text.getText() + num);
        }
        else if (!text.getText().equals("") && num.equals("."))
        {
            if (flg < 2)//-------------控制小数点只能出现一次--------
            {
                text.setText(text.getText() + ".");
                flg++;
            }
        }


    }
}
