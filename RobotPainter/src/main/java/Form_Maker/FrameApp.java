package Form_Maker;

import javax.swing.*;

public class FrameApp extends JFrame {
    public FrameApp(Marker markerLineMaker){
        setTitle("Form_Maker");
        add(new PathComponent(markerLineMaker));
        setBounds(200,200,500,500);
    }
}
