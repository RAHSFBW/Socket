package UDPSocket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Arrays;

public class UDP_Receive {
    public static void main(String[] args) throws IOException {
        int port = 2000;
        DatagramSocket datagramSocket = new DatagramSocket(2000);
        while (true){
            byte[] buf = new byte[1024];
            int length = buf.length;

            DatagramPacket datagramPacket = new DatagramPacket(buf,length);

            datagramSocket.receive(datagramPacket);

            //System.out.println(datagramPacket.getData().toString());
            //System.out.println(Arrays.toString(datagramPacket.getData()));
            // if(datagramSocket.)
            String s = new String(datagramPacket.getData());
           // System.out.println(s);

            InetAddress address = datagramPacket.getAddress();
           // System.out.println(address);

            int port1 = datagramPacket.getPort();
           // System.out.println(port);

            System.out.println(address+": "+s);

        }



        //datagramSocket.close();


    }
}
