import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class BeautifulMindReaderGame extends JFrame {

    private JPanel contentPanel;
    private JLabel stepLabel;
    private JButton actionButton;
    private int step;
    private int randomNumber;
    private Random random;

    public BeautifulMindReaderGame() {
        // Initialize game state and random generator
        random = new Random();
        randomNumber = random.nextInt(49) + 2; // Generate a random number between 2 and 50
        step = 0;

        // Set up the frame
        setTitle("Mind Reader Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 400);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        // Content panel with gradient background
        contentPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color startColor = new Color(29, 72, 131);
                Color endColor = new Color(94, 201, 236);
                GradientPaint gradient = new GradientPaint(0, 0, startColor, 0, getHeight(), endColor);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Label to display game steps
        stepLabel = new JLabel("Welcome to the Mind Reader Game!", SwingConstants.CENTER);
        stepLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        stepLabel.setForeground(Color.WHITE);
        stepLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        stepLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));

        // Action button
        actionButton = new JButton("Start Game");
        actionButton.setFont(new Font("Arial", Font.BOLD, 16));
        actionButton.setBackground(new Color(94, 131, 226));
        actionButton.setForeground(Color.WHITE);
        actionButton.setFocusPainted(false);
        actionButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        actionButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        actionButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        actionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextStep();
            }
        });

        // Add components to the content panel
        contentPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        contentPanel.add(stepLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        contentPanel.add(actionButton);

        // Add content panel to the frame
        add(contentPanel, BorderLayout.CENTER);
    }

    private void nextStep() {
        step++;
        switch (step) {
            case 1:
                stepLabel.setText("Step 1: Think of a number.");
                actionButton.setText("Next");
                break;
            case 2:
                stepLabel.setText("Step 2: Multiply it by 2.");
                break;
            case 3:
                stepLabel.setText("Step 3: Add " + randomNumber + " to the result.");
                break;
            case 4:
                stepLabel.setText("Step 4: Divide the result by 2.");
                break;
            case 5:
                stepLabel.setText("Step 5: Subtract your original number from the result.");
                break;
            case 6:
                double result = randomNumber / 2.0;
                stepLabel.setText("Your final answer is: " + result + "!");
                actionButton.setText("Restart");
                break;
            default:
                step = 0;
                randomNumber = random.nextInt(49) + 2;
                stepLabel.setText("Welcome to the Mind Reader Game!");
                actionButton.setText("Start Game");
                break;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BeautifulMindReaderGame game = new BeautifulMindReaderGame();
            game.setVisible(true);
        });
    }
}
