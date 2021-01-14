package New_Calculator;

public class calculator extends SimpleCaculator{
    public calculator(String s) {
        //number into stack

        if (num_stack.size() == 0)
            resultNUM = getNumberFromText();
        if (num_stack.size() <3)
        {
            num_stack.push(getNumberFromText());
            flg =1;
        }

        // symbol into stack
        text.setText(s);
        if(str_stack.size()<3)
            str_stack.push(s);


        if(str_stack.size() == 3)
        {
            String z = str_stack.pop();
            double a1 = num_stack.pop();
            double a2 = num_stack.pop();
            double re = 0;
            if(str_stack.peek()=="*")
            {
                re = a1*a2;

            }
            if(str_stack.peek()=="/")
            {
                if(a1==0)
                    text.setText("除数不能为0");
                else
                    re = a2/a1;

            }
            str_stack.pop();
            num_stack.push(re);
            str_stack.push("=");
        }

        if (str_stack.size() == 2&&(str_stack.peek()=="="||str_stack.peek()=="-"||str_stack.peek()=="+"))
        {

            String qq = str_stack.pop();
            if (str_stack.peek() == "+")
            {

                resultNUM += num_stack.peek();
            }
            else if (str_stack.peek() == "-")
            {

                resultNUM -= num_stack.peek();
            }
            else if (str_stack.peek() == "*")
            {

                resultNUM *= num_stack.peek();

            }
            else if (str_stack.peek() == "/")
            {
                if (num_stack.peek() == 0)
                    text.setText("除数不能为0");
                else
                {
                    text.setText("");
                    resultNUM /= num_stack.peek();
                }
            }
            num_stack.pop();
            num_stack.pop();
            num_stack.push(resultNUM);
            str_stack.pop();
            str_stack.push(qq);
            if(qq=="=")
                text.setText(String.valueOf(resultNUM));

        }
    }
    public double getNumberFromText()
    {

        double result = 0.0;
        try {
            result = Double.valueOf(text.getText()).doubleValue();
        }
        catch (NumberFormatException e )
        {
            System.out.println("failed to change the value");
        }
        return result;
    }





}
