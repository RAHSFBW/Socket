package TCPDoubletalk;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * 设置  allow parallel run 重复运行main
 */
public class Client {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
       // int port = scanner.nextInt();
        Socket socket = new Socket("192.168.111.1", 2000);
        //scanner.nextLine();
        OutputStream outputStream = socket.getOutputStream();
        String str = scanner.nextLine();
        outputStream.write(str.getBytes());

        socket.close();
    }
}
