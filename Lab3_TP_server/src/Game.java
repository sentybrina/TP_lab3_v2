import java.util.Scanner;

public class Game {

    private Connection connection1;
    private Connection connection2;
    private User user1;
    private User user2;
    Scanner scanner = new Scanner(System.in);

    private double speed = 20;

    double randCoordinate = Math.random() * 40;

    public double flightDistance(double x, double alpha) {
        return x + speed * speed * Math.sin(2 * alpha * Math.PI / 180) / 10;
    }

    public Game(Connection connection1, Connection connection2) {
        this.connection1 = connection1;
        this.connection2 = connection2;
        user1 = new User();
        user2 = new User();
    }


    public void sendCoordinate1() {
        System.out.println("Координата 1 игрока" + 0);
        connection1.send(0); //координата отправляется 1 игроку
        //connection1.send(randCoordinate);
        user1.addUserCoordinate(0);

    }

    public void sendCoordinate2() {

        System.out.println("Координата 2 игрока" + randCoordinate);
        connection2.send(randCoordinate); //рандомная координата [0, 40] отправляется 2 игроку
        //connection2.send(0);
        user2.addUserCoordinate(randCoordinate);
    }


    public void start() {
        while (true) {
            try {
                System.out.println("Ход первого игрока:");
                double angle1 = connection1.readObject(Double.class);
                System.out.println("Первый игрок выстрелил под уголом " + angle1);
                double x1 = flightDistance(0, angle1);
                System.out.println("Первый игрок попал в координату " + x1);

                if ((x1 <= user2.coordinate + 2) && (x1 >= user2.coordinate - 2)) {
                    System.out.println("Игра закончена. Первый игрок выиграл! ");
                    connection1.disconnect();
                    connection2.disconnect();
                    return;
                }
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }

            try {
                System.out.println("Первый игрок промахнулся! Ход второго игрока:");
                double angle2 = connection2.readObject(Double.class);
                System.out.println("Второй игрок выстрелил под уголом " + angle2);
                double x2 = flightDistance(user2.coordinate, angle2);
                System.out.println("Второй игрок попал в координату " + x2);
                if ((x2 <= user1.coordinate + 2) && (x2 >= user1.coordinate - 2)) {
                    System.out.println("Игра закончена. Второй игрок выиграл! ");
                    connection1.disconnect();
                    connection2.disconnect();

                    return;
                }
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
        }
    }

}
