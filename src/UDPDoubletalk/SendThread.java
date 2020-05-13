package UDPDoubletalk;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class SendThread extends Thread {
    private int port;
    public SendThread(int port){
        this.port = port;
    }
    @Override
    public void run() {
        DatagramSocket datagramSocket = null;
        try {
            datagramSocket = new DatagramSocket();
            Scanner scanner = new Scanner(System.in);

            while (true){
                String s = scanner.nextLine();
                if(s.equals("下次再聊")) break;
                byte[] bytes = s.getBytes();
                int length = bytes.length;
                InetAddress inetAddress = InetAddress.getLocalHost();           //192.168.111.1
//        InetAddress inetAddress = InetAddress.getByName("127.0.0.1");
                //int port = 2000;
                DatagramPacket datagramPacket = new DatagramPacket(bytes,length,inetAddress,port);

                datagramSocket.send(datagramPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(datagramSocket != null)
                datagramSocket.close();
        }

    }
}
