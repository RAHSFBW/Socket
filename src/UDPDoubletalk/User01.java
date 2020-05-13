package UDPDoubletalk;

public class User01 {
    public static void main(String[] args) {
        new ReceiveThread(2000).start();
        new SendThread(2001).start();
    }
}
