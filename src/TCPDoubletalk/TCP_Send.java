package TCPDoubletalk;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TCP_Send {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("192.168.111.1",2000);   //根据ip,port建立连接
        OutputStream outputStream = socket.getOutputStream();
        InputStream inputStream = socket.getInputStream();
        Scanner scanner =  new Scanner(System.in);


        //接收
        Thread t = new Thread(){
            @Override
            public void run() {
                int length = -1;
                byte[] buf = new byte[1024];
                try {
                while (true) {
                    if(socket.isClosed()){
                        inputStream.close();
                        break;
                    }

                    length = inputStream.read(buf);
                    if (length == -1) {
                        System.out.println("对方已关闭连接");
                     //   socket.close();         //client 关闭，输入流也自动关闭
                        break;
                    }
                    String str = new String(buf, 0, length);
                    System.out.println(str);
                }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if(inputStream!=null){
                            try {
                                inputStream.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

        };
        t.setDaemon(true);
        t.start();


        //发送
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
                t.interrupt();
                if(socket!=null)
                socket.close();
            }
    }
}
