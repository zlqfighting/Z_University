package Calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class SimpleCalculator extends JFrame implements ActionListener {
    JPanel panel_North;
    JPanel panel_Sourth;
    JPanel panel_Center;
    JTextField text;
    private static final int DEFAULT_WIDTH = 500;
    private static final int DEFAULT_HEIGHT = 180;

    Stack<Double> num_stack = new Stack<>();
    Stack<String> str_stack = new Stack<>();

    double resultNUM = 0.0;//暂存结果
    int flg = 1;//用于标识小数点个数
    int title =0;//用于标记是否清除

    //------------------------页面设计--------------------------------------

    public SimpleCalculator() {
        //创建500*200的窗口，并添加窗口名
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setTitle("Calculator");

        //创建3个panel
        panel_North = new JPanel();
        panel_Sourth = new JPanel();
        panel_Center = new JPanel();

        //对三个panel 进行布局
        panel_North.setLayout(new BorderLayout());
        //panel_Center.setLayout(new BoxLayout(panel_Center,BoxLayout.X_AXIS));//不能对齐
        panel_Center.setLayout(new GridLayout(1, 2));
        panel_Sourth.setLayout(new GridLayout(4, 4));

        //在panel_North中添加组件
        text = new JTextField(30);
        text.setHorizontalAlignment(SwingConstants.RIGHT);//文本对齐方式右对齐
      text.setText("0");
        panel_North.add(text);

        //panel_Center中添加组件

        JButton Clean = new JButton("AC");
        JButton backspace = new JButton("BACK");
        Clean.addActionListener(this);
        backspace.addActionListener(this);
        panel_Center.add(Clean, BorderLayout.WEST);
        panel_Center.add(backspace, BorderLayout.EAST);

        //panel_Sourth中添加组件
        String[] str = {"7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", "0", ".", "=", "+"};
        int len = str.length;
        for (int i = 0; i < len; i++) {
            JButton btn = new JButton(str[i]);
            btn.addActionListener(this);
            panel_Sourth.add(btn);
        }

        //向JFrame中添加3个Panel
        Container contentpan = this.getContentPane();//JFrame中不能直接添加组件
        contentpan.add(panel_North, BorderLayout.NORTH);
        contentpan.add(panel_Center, BorderLayout.CENTER);
        contentpan.add(panel_Sourth, BorderLayout.SOUTH);


    }
    //------------------------后台功能设计---------------------------------------------------

    //事件监听
    public void actionPerformed(ActionEvent event) {
        String label = event.getActionCommand();//可以获取按键上的标签
        if (label == "AC") {
            Cleaner_AC();
        } else if (label == "BACK") {
            re_back();
        } else if (label == "+" || label == "-" || label == "*" ||label == "/" || label == "=") {
            calculator(label);
        } else {
            number(label);
        }
    }

    //  清除函数
    public void Cleaner_AC() {
        text.setText(" ");
        flg = 1;
        while(num_stack.size()!=0)
        num_stack.pop();
        while(str_stack.size()!=0)
            str_stack.pop();


    }

    //回退函数
    public void re_back() {//-------------------------怎样删一个数?答：将从文本域中得到的字符串进行切割，不包含最后一个)------------------------------
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

    //将数字天填文本域---------------------此时是以字符串的形式传的--------------------------------
    public void number(String num) {

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


    public void calculator (String s)
    {
        //数字压栈

            if (num_stack.size() == 0)
                resultNUM = getNumberFromText();
            if (num_stack.size() <3)
            {
                num_stack.push(getNumberFromText());
                flg =1;
            }


//        System.out.println(num_stack.peek());
//        System.out.println(num_stack.size());
        text.setText(s);
        if(str_stack.size()<3)
            str_stack.push(s);
//        System.out.println(str_stack.size());

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
                  if(a2==0)
                      text.setText("除数不能为0");
                  re = a1/a2;

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


//-------------------计算时应该注意从text.getfile()中获取的是字符串不是数字----------------------

    public double getNumberFromText()
    {
        //转化过程中可能会出现转化失败的情况，可以使用try，catch
        double result = 0.0;
        try {
            result = Double.valueOf(text.getText()).doubleValue();//字符串转化为数值
        }
        catch (NumberFormatException e )
        {
            System.out.println("failed to change the value");
        }
        return result;
    }
    public static void main(String[] args)
    {
        EventQueue.invokeLater(()->
        {
            JFrame frame = new SimpleCalculator();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }



}



