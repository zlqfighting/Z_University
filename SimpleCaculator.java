package New_Calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public  class SimpleCaculator extends JFrame implements ActionListener {
    JPanel panel_North;//--------------------------------------------------------------
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

    public SimpleCaculator() {
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

        //panel_Center中添加组件 m8888

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


    @Override
    public void actionPerformed(ActionEvent e) {
        String label = e.getActionCommand();//可以获取按键上的标签
        if (label == "AC") {
            new Cleaner_AC();
        } else if (label == "BACK") {
            new re_back();
        } else if (label == "+" || label == "-" || label == "*" ||label == "/" || label == "=") {
            new calculator(label);
        } else {
            new number(label);
        }
    }
}
