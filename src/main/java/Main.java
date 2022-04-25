import java.net.*;
import java.io.*;
public class Main {

    static byte[] sendData = new byte[]{
            0x24, 0x43,  0x4F,  0x4E, 0x30,
            0x00,  0x00,  0x01,  0x00,  0x00,
            0x00,  0x00,  0x00,  0x00,  0x00,
            0x00,  0x00,  0x00,  0x00,  0x00,
            0x00,  0x00,  0x00,  0x00, 0x0,
            0x00,  0x00,  0x00,  0x00,  0x00,
            0x00,  0x00,  0x00,  0x00,  0x00,
            0x00,  0x00,  0x00,  0x00,  0x00,
            0x00,  0x00,  0x00,  0x00,  0x00,
            0x00,  0x00,  0x00,
    };

    public static void main(String[] args) throws Exception{
        int port = 32000;
        System.out.println(new EchoClient().sendEcho(sendData));
    }
    java.lang.String address = "185.231.245.68";

    public static class EchoClient {
        private DatagramSocket socket;
        private InetSocketAddress address;

        private byte[] buf;

        public EchoClient() throws Exception{
            socket = new DatagramSocket();
            address = new InetSocketAddress("stun001.heredes.org", 32000);
        }

        public byte[] sendEcho(byte[] data) throws Exception{
            DatagramPacket packet
                    = new DatagramPacket(data, data.length, address);

            int i1 = 5;
            int c = data.length / i1 + data.length % i1;

            for(int i = 0; i < c;i++){
                int currentNum = i1 *i + i % i1;
                byte[] part = new byte[i1];
                for(int j = currentNum; j<currentNum + i1; j++){
                    part[j - currentNum] = currentNum + j < data.length ? data[currentNum + j] : 0x00;
                }
                packet = new DatagramPacket(part, part.length, address);
                socket.send(packet);
            }

//            socket.send(packet);
            packet = new DatagramPacket(data, data.length);
            socket.receive(packet);
            return data;
        }

        public void close() {
            socket.close();
        }
    }
}