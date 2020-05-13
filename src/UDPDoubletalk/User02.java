package UDPDoubletalk;

public class User02 {
    public static void main(String[] args) {
        new SendThread(2000).start();
        new ReceiveThread(2001).start();
    }
}
