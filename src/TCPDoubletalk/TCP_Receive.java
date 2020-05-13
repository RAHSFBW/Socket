package TCPDoubletalk;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TCP_Receive {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(2000);
        Socket client = serverSocket.accept();//建立连接
        System.out.println("建立了一个连接"+client);
        InputStream inputStream = client.getInputStream();
        OutputStream outputStream = client.getOutputStream();

        byte[] buf = new byte[1024];
        int length = -1;

        //发送消息
        Thread t = new Thread(){
            @Override
            public void run() {
                Scanner scanner = new Scanner(System.in);
                try {
                    while (true) {
                        String str = scanner.nextLine();
                        if(str.equals("请求关闭"))
                            break;

                            outputStream.write(str.getBytes());
                        }
                }catch (IOException e) {
                            e.printStackTrace();
                    }finally {
                            if(outputStream!=null){
                                try {
                                    outputStream.flush();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    outputStream.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
        };
        t.setDaemon(true);
        t.start();


            //接收消息
            try {
                while (true){
                    //接收消息
                    length = inputStream.read(buf);
                    if(length == -1){
                        System.out.println("对方已关闭连接");
                        break;
                    }
                    String str = new String(buf,0,length);
                    System.out.println(str);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                t.interrupt();
                client.close();         //client 关闭，输入流也自动关闭
                serverSocket.close();
            }
    }
}
