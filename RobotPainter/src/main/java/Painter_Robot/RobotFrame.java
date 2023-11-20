package Painter_Robot;

import javax.swing.*;

public class RobotFrame extends JFrame
{
    public RobotFrame(Robot robot) {
        // Устанавливаем заголовок окна
        setTitle("Robot Frame");
        // Добавляем компонент для рисования пути робота
        add(new RobotPathComponent(robot));
        // Устанавливаем размеры окна
        setBounds(100, 100, 500, 500);
    }
}
