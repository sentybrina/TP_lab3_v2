import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while(true) {

            System.out.println("Введите адрес вервера");
            String ip = scanner.nextLine();

            try {
                Socket s = new Socket(ip, 8080);
                Game g = new Game(new Connection(s));
               // g.coordinataSopernica();
                g.myMove();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
