package Painter_Robot;

import javax.swing.*;

public class RobotManager
{
    public static void main(String[] args) {
        // Количество сторон многоугольника
        final int COUNT = 100;
        // Длина стороны
        final int SIDE = 5;
        
        Robot robot = new Robot(100, 20);// координаты
        // Создаем замкнутую фигуру с количеством углов COUNT
        for (int i = 0; i < COUNT; i++) {
            robot.forward(SIDE);
            robot.setCourse(robot.getCourse() + 360 / COUNT);
        }

        // Создаем форму для отрисовки пути нашего робота
        RobotFrame rf = new RobotFrame(robot);
        rf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        rf.setVisible(true);
    }
}
