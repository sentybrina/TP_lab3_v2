import java.util.Scanner;

public class Game {

    private Connection connection;
    private User user1;
    private User user2;
    Scanner scanner = new Scanner(System.in);

    private double speed = 20;

    public double flightDistance(double x, double alpha) {
        return x + speed * speed * Math.sin(2 * alpha) / 10;
    }

    public Game(Connection connection) {
        this.connection = connection;
        user1 = new User();
        user2 = new User();
    }

    public void coordinataSopernica(){
        double youCoordinate = scanner.nextDouble();
        double anotherCoordinate = scanner.nextDouble();
        System.out.println("Ваша координата " + youCoordinate + "Координата противника " + anotherCoordinate);

    }

    public void myMove() {
        while (true) {
            try {

                System.out.println("Ваш ход. Введите угол выстрела");
                double angle = scanner.nextDouble();
                connection.send(angle);
                System.out.println("Ожидаем хода соперника");


            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
        }
    }

}
