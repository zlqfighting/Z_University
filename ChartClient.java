package 聊天;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;


public class ChartClient extends JFrame {
    private JTextArea jta;
    private JTextField jtf;
    private JButton jb;
    private JLabel name;
    private Socket s;
    private PrintWriter pw;
    private BufferedReader br;
     String file ="demo.txt";
    BufferedWriter fl;



    public ChartClient()
    {
        this.setSize(300,350);
        this.setTitle("聊天室");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jta = new JTextArea(15,20);

        jtf = new JTextField(15);
        jb = new JButton("发送");
        name = new JLabel("");
        JPanel jp = new JPanel();
        jp.setLayout(new FlowLayout());
        jp.add(name);
        jp.add(jtf);
        jp.add(jb);
        jb.addActionListener(new SendMessage());

        this.setLayout(new BorderLayout());
        this.add(jta,BorderLayout.CENTER);
        this.add(jp,BorderLayout.SOUTH);
        getMessage();
        this.setVisible(true);
    }
    public void getMessage()
    {
        try{
            s = new Socket("127.0.0.1",6666);
            pw = new PrintWriter(s.getOutputStream());
            new ClientThread().start();

        }catch (UnknownHostException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        name.setText(JOptionPane.showInputDialog(this,"请输入你的名字"));

    }

class ClientThread extends Thread
{


public void run() {
        while(true) {
            try {
                br = new BufferedReader(new InputStreamReader(s.getInputStream()));

                jta.append(br.readLine()+ "\n");

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
class SendMessage  implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e) {
       pw.println(name.getText()+"说:"+jtf.getText()+"\n");

        try {
            fl = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,true)));
            fl.write(name.getText()+"说:"+jtf.getText());
            fl.newLine();
fl.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        pw.flush();

       jtf.setText("");
    }
}

    public static void main(String[] args) {

        new ChartClient();
        new ChartClient();
    }
}