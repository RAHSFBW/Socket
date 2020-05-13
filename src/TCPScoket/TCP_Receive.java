package TCPScoket;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCP_Receive {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(2000);
        Socket client = serverSocket.accept();//建立连接
        System.out.println("建立了一个连接"+client);
        InputStream inputStream = client.getInputStream();
        byte[] buf = new byte[1024];
        int length = -1;
            try {
                while ((length=inputStream.read(buf)) != -1){

                    String s = new String(buf,0,length);
                    System.out.println(s);
                }
                System.out.println("客户已关闭连接");
                client.close();         //client 关闭，输入流也自动关闭
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                client.close();         //client 关闭，输入流也自动关闭
                serverSocket.close();
            }

    }
}
