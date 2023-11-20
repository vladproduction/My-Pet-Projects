package Form_Maker;

import javax.swing.*;

public class RunApp {
    public static void main(String[] args) {
        final double AMOUNT_SIDES = 3;
        final double LINE_SIZE = 50;
        Marker marker = new Marker(100,200);
        for(int i = 0; i<=AMOUNT_SIDES+1;i++){
            marker.moving((int)LINE_SIZE);
            marker.setCourse(marker.getCourse() + 720/AMOUNT_SIDES);
        }
        FrameApp frameApp = new FrameApp(marker);
        frameApp.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frameApp.setVisible(true);
    }
}
