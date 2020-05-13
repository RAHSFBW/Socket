package TCPScoket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TCP_Send {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("192.168.111.1",2000);   //根据ip,port建立连接
        OutputStream outputStream = socket.getOutputStream();
        Scanner scanner =  new Scanner(System.in);

            try {
                while (true) {
                    String str = scanner.nextLine();
                    if(str.equals("请求关闭"))
                        break;
                    outputStream.write(str.getBytes());
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if(socket!=null)
                socket.close();
            }

    }
}
