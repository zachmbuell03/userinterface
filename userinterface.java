package userinterface;
	
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class userinterface extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textBox;      // The text box at the top
    private Color storedGreenHue;    // Stores the random green color for this run only

    
    public userinterface() {
        super("Menu Program Example");

        // -----------------------------
        // 1. CREATE THE TEXT BOX
        // -----------------------------
        textBox = new JTextField(30);
        add(textBox, BorderLayout.NORTH);

        // -----------------------------
        // 2. CREATE THE MENU BAR
        // -----------------------------
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");

        JMenuItem option1 = new JMenuItem("Print Date & Time");
        JMenuItem option2 = new JMenuItem("Write to log.txt");
        JMenuItem option3 = new JMenuItem("Random Green Background");
        JMenuItem option4 = new JMenuItem("Exit Program");

        // Add menu items to menu
        menu.add(option1);
        menu.add(option2);
        menu.add(option3);
        menu.add(option4);

        // Add menu to menu bar
        menuBar.add(menu);

        // Attach menu bar to frame
        setJMenuBar(menuBar);

        // -----------------------------
        // 3. MENU OPTION BEHAVIORS
        // -----------------------------

        // OPTION 1: Print date/time in text box
        option1.addActionListener(e -> {
            String now = LocalDateTime.now().toString();
            textBox.setText(now);
        });

        // OPTION 2: Write text box contents to log.txt
        option2.addActionListener(e -> {
            try (FileWriter writer = new FileWriter("log.txt", true)) {
                writer.write(textBox.getText() + System.lineSeparator());
                System.out.println("Wrote to: " + new java.io.File("log.txt").getAbsolutePath());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error writing to file.");
            }
        });

        // OPTION 3: Change background to a random green hue
        option3.addActionListener(e -> {
            // Only generate the random green ONCE per program run
        	storedGreenHue = generateRandomGreen();
        	getContentPane().setBackground(storedGreenHue);

        });

        // OPTION 4: Exit program
        option4.addActionListener(e -> System.exit(0));

        // -----------------------------
        // 4. FRAME SETTINGS
        // -----------------------------
        setSize(500, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // -----------------------------
    // Generate a random green shade
    // -----------------------------
    private Color generateRandomGreen() {
        Random rand = new Random();
        int green = 150 + rand.nextInt(106); // 150–255 for bright green
        return new Color(0, green, 0);
    }

    public static void main(String[] args) {
        new userinterface();
    }

}
