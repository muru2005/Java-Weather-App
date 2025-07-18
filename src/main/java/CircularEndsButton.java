import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class CircularEndsButton extends JButton {

    private static final int ARC_WIDTH = 20;
    private static final int ARC_HEIGHT = 20;

    public CircularEndsButton(String text) {
        super(text);
        setContentAreaFilled(false); // Important to make custom painting visible
        setFocusPainted(false);      // Remove the focus border
        setOpaque(false);            // Make sure the button itself is not opaque
        setBorderPainted(false);     // Remove the border
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        try {
            // Antialiasing for smoother edges
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Create the button shape with circular ends
            Shape buttonShape = createButtonShape();
            
            // Draw the button shape
            g2d.setColor(getBackground());
            g2d.fill(buttonShape);

            // Draw the text
            g2d.setColor(getForeground());
            FontMetrics fm = g2d.getFontMetrics();
            String text = getText();
            int textWidth = fm.stringWidth(text);
            int textHeight = fm.getAscent();
            g2d.drawString(text, (getWidth() - textWidth) / 2, (getHeight() + textHeight) / 2 - 2);

        } finally {
            g2d.dispose();
        }
    }

    private Shape createButtonShape() {
        int width = getWidth();
        int height = getHeight();
        GeneralPath path = new GeneralPath();

        // Draw the circular ends
        path.append(new Ellipse2D.Double(0, (height - ARC_HEIGHT) / 2, ARC_HEIGHT, ARC_HEIGHT), false);
        path.append(new Ellipse2D.Double(width - ARC_HEIGHT, (height - ARC_HEIGHT) / 2, ARC_HEIGHT, ARC_HEIGHT), false);

        // Draw the middle rectangle
        path.append(new RoundRectangle2D.Double(ARC_HEIGHT / 2, 0, width - ARC_HEIGHT, height, ARC_WIDTH, ARC_HEIGHT), false);

        return path;
    }

    @Override
    protected void paintBorder(Graphics g) {
        // Do nothing, we don't want a border
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Circular Ends Button Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new FlowLayout());

            // Create a button with circular ends
            CircularEndsButton button = new CircularEndsButton("Click Me");
            button.setBackground(Color.BLUE);
            button.setForeground(Color.WHITE);

            // Add the button to the frame
            frame.add(button);

            frame.setSize(300, 200);
            frame.setVisible(true);
        });
    }
}
