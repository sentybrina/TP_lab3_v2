import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args){
        try{
            System.out.println();

            ServerSocket ss = new ServerSocket(8080, 0, InetAddress.getByName(null));

            System.out.println("Start socket at");
            System.out.println(ss.toString());
            System.out.println(ss.getLocalSocketAddress());
            System.out.println(ss.getLocalPort());
            System.out.println(ss.getInetAddress());

            Socket s1 = ss.accept();
            Socket s2 = ss.accept();
            Game g = new Game(new Connection(s1), new Connection(s2));
            g.sendCoordinate1();
            g.sendCoordinate2();
            g.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
