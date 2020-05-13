package UDPDoubletalk;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ReceiveThread extends Thread {
    private  int port;
    public ReceiveThread(int port){
        this.port = port;
    }
    @Override
    public void run() {
        DatagramSocket datagramSocket = null;
        try {
            datagramSocket = new DatagramSocket(port);
            while (true){
                byte[] buf = new byte[1024];
                int length = buf.length;

                DatagramPacket datagramPacket = new DatagramPacket(buf,length);

                datagramSocket.receive(datagramPacket);

                String s = new String(datagramPacket.getData());
                InetAddress address = datagramPacket.getAddress();

                System.out.println(address+": "+s);
                if(s.equals("下次再聊"))
                    break;

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(datagramSocket!=null)
            datagramSocket.close();
        }
    }
}
