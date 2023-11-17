package Base.game.Plant;
public class Point {
    private double pointX;
    private double pointY;

    public Point(double pointX, double pointY) {
        this.pointX = pointX;
        this.pointY = pointY;
    }

    public double getPointX() {
        return pointX;
    }

    public void setPointX(double pointX) {
        this.pointX = pointX;
    }

    public double getPointY() {
        return pointY;
    }

    public void setPointY(double pointY) {
        this.pointY = pointY;
    }

    public double distance(Point other) {
        return Math.sqrt((other.getPointX() * other.getPointX()
                - this.getPointX() * this.getPointX())
                + (other.getPointY() * other.getPointY()
                - this.getPointY() * this.getPointY()));
    }


    @Override
    public boolean equals(Object o) {
        if (o instanceof Point) {
            if (((Point) o).getPointX() == this.getPointX()
                    && ((Point) o).getPointY() == this.getPointY()) {
                return true;
            }
        }
        return false;
    }

}
