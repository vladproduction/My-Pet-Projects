package Form_Maker;

import javax.swing.*;
import java.awt.*;

public class PathComponent extends JComponent {
    private Marker marker;

    public PathComponent(Marker marker) {
        this.marker = marker;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(Marker.LineMaker markLine: marker.getList()){ //should be protected class
            int x1 = (int) Math.round(markLine.getX1());
            int y1 = (int) Math.round(markLine.getY1());
            int x2 = (int) Math.round(markLine.getX2());
            int y2 = (int) Math.round(markLine.getY2());
            g.drawLine(x1,y1,x2,y2);
        }
    }
}
