import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DigitalClock extends JFrame {
    private JLabel headingLabel;
    private JLabel timeLabel;
    
    public DigitalClock() {
        super("Digital Clock");

        headingLabel = new JLabel("My Digital Clock");
        headingLabel.setBorder(BorderFactory.createEmptyBorder(100,0,0,0));
        headingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headingLabel.setFont(new Font("Arial", Font.BOLD, 50));

        timeLabel = new JLabel();
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 100));

        // Add labels to the frame
        getContentPane().add(headingLabel, BorderLayout.NORTH);
        getContentPane().add(timeLabel, BorderLayout.CENTER);

        // Set frame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);

        // Update time every second
        Timer timer = new Timer(1000, e -> updateTime());
        timer.start();
    }

    private void updateTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String time = dateFormat.format(calendar.getTime());
        timeLabel.setText(time);
    }

    public static void main(String[] args) {
        new DigitalClock();
    }
}
