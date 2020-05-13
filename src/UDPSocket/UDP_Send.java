package UDPSocket;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class UDP_Send {
    public static void main(String[] args) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket();
        Scanner scanner = new Scanner(System.in);

        while (true){
            String s = scanner.nextLine();
            if(s.equals("下次再聊")) break;
            //byte[] bytes = "hello,i'm coming!".getBytes();
            byte[] bytes = s.getBytes();
            int length = bytes.length;
            InetAddress inetAddress = InetAddress.getLocalHost();           //192.168.111.1
//        InetAddress inetAddress = InetAddress.getByName("127.0.0.1");
            int port = 2000;
            DatagramPacket datagramPacket = new DatagramPacket(bytes,length,inetAddress,port);

            datagramSocket.send(datagramPacket);

        }


        //datagramSocket.close();
    }
}
