public class User {
    public double coordinate;
    public double angle;

    public User(){}

    public User(double coordinate, double angle){
        this.coordinate = coordinate;
        this.angle = angle;
    }

    public void addUserCoordinate(double coordinate){
        this.coordinate = 0;
    }
}
