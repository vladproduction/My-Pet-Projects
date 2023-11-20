package Form_Maker;

import java.util.ArrayList;
import java.util.List;

public class Marker {
    private double x;
    private double y;
    double course=0;
    private List<LineMaker> list = new ArrayList();

    public Marker(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public void moving(int lineSize){
        final double xStart = x;
        final double yStart = y;
        x += lineSize * Math.cos(course / 180 * Math.PI);
        y += lineSize * Math.sin(course / 180 * Math.PI);
        list.add(new LineMaker(xStart,yStart,x,y));
    }
    protected class LineMaker{
        private double x1;
        private double y1;
        private double x2;
        private double y2;

        public LineMaker(double x1, double y1, double x2, double y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }

        public double getX1() {
            return x1;
        }

        public double getY1() {
            return y1;
        }

        public double getX2() {
            return x2;
        }

        public double getY2() {
            return y2;
        }
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getCourse() {
        return course;
    }

    public void setCourse(double course) {
        this.course = course;
    }

    public List<LineMaker> getList() {
        return list;
    }
}
