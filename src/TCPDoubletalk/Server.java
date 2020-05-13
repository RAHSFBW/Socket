package TCPDoubletalk;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 批量处理客户端数据
 */
public class Server {
    public static void main(String[] args) throws IOException {

        new ConnectionThread().start();
    }
}

class ConnectionThread extends Thread{
    @Override
    public void run() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(2000);
            while (true){

                Socket socket = serverSocket.accept();
                System.out.println("建立了一个连接"+socket.getInetAddress());
                new clientThread(socket).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(serverSocket!=null)
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class clientThread extends Thread{
    private Socket socket;
    public clientThread(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();
            byte[] buf = new byte[1024];
            int length = buf.length;

            while ((length = inputStream.read(buf)) != -1){
                System.out.println(new String(buf,0,length));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
