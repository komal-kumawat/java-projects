import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class AnalogClock extends JPanel {

    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;
    private static final int CENTER_X = WIDTH / 2;
    private static final int CENTER_Y = HEIGHT / 2;
    private static final int RADIUS = 170;
    private static final int SECOND_HAND_LENGTH = 130;
    private static final int MINUTE_HAND_LENGTH = 110;
    private static final int HOUR_HAND_LENGTH = 90;
    private static final Font CLOCK_FONT = new Font("Arial", Font.BOLD, 18);

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw clock face
        g.setColor(Color.white);
        g.fillOval(CENTER_X - RADIUS, CENTER_Y - RADIUS, 2 * RADIUS, 2 * RADIUS);
        g.setColor(Color.black);
        g.drawOval(CENTER_X - RADIUS, CENTER_Y - RADIUS, 2 * RADIUS, 2 * RADIUS);

        // Draw numbers
        for (int i = 1; i <= 12; i++) {
            double angle = Math.PI / 6 * (i - 3);
            int x = CENTER_X + (int) ((RADIUS - 25) * Math.cos(angle));
            int y = CENTER_Y + (int) ((RADIUS - 25) * Math.sin(angle));
            g.setFont(CLOCK_FONT);
            g.drawString(Integer.toString(i), x, y);
        }

        // Draw hour markers
        for (int i = 0; i < 60; i += 5) {
            double angle = Math.PI / 30 * i;
            int x1 = CENTER_X + (int) ((RADIUS - 10) * Math.sin(angle));
            int y1 = CENTER_Y - (int) ((RADIUS - 10) * Math.cos(angle));
            int x2 = CENTER_X + (int) (RADIUS * Math.sin(angle));
            int y2 = CENTER_Y - (int) (RADIUS * Math.cos(angle));
            g.drawLine(x1, y1, x2, y2);
        }

        // Get current time
        Calendar time = Calendar.getInstance();
        int hour = time.get(Calendar.HOUR);
        int minute = time.get(Calendar.MINUTE);
        int second = time.get(Calendar.SECOND);

        // Draw hour hand
        double hourAngle = (hour % 12 + minute / 60.0) * 360 / 12;
        drawHand(g, hourAngle, HOUR_HAND_LENGTH);

        // Draw minute hand
        double minuteAngle = (minute + second / 60.0) * 360 / 60;
        drawHand(g, minuteAngle, MINUTE_HAND_LENGTH);

        // Draw second hand
        double secondAngle = second * 360 / 60;
        g.setColor(Color.pink);
        drawHand(g, secondAngle, SECOND_HAND_LENGTH);
    }

    private void drawHand(Graphics g, double angle, int length) {
        double radianAngle = Math.toRadians(angle);
        int x = CENTER_X + (int) (length * Math.sin(radianAngle));
        int y = CENTER_Y - (int) (length * Math.cos(radianAngle));
        g.drawLine(CENTER_X, CENTER_Y, x, y);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Analog Clock");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setResizable(false);
        frame.add(new AnalogClock());
        frame.setVisible(true);

        // Update clock every second
        Timer timer = new Timer(1000, e -> frame.repaint());
        timer.start();
    }
}
