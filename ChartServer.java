package 聊天;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.HashSet;
import java.util.Iterator;

public class ChartServer {
    private HashSet<Socket> clients = new HashSet<>();


    public ChartServer() {
        try {
            ServerSocket ss = new ServerSocket(6666);
            while (true) {
                Socket s = ss.accept();
                clients.add(s);
                new ServerThread1(s, clients).start();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }}
    class ServerThread1 extends Thread {
        private Socket s;
        private HashSet clients;
        public ServerThread1(Socket s, HashSet clients) {
            this.s = s;
            this.clients = clients;
        }

        public void run() {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));

                while (true) {

                    sendMessage(br.readLine()+ "\n");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        private void sendMessage(String str) {
            Iterator it = clients.iterator();
            while (it.hasNext()) {

                Socket temp = (Socket) it.next();
                try {
                    PrintWriter pw = new PrintWriter(temp.getOutputStream());
                    pw.println(str);
                    pw.flush();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


      public static void main(String[] args) {
            new ChartServer();
        }



}