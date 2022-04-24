package com.mstc;

import com.mstc.gui.MsTcGui;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }

                //1. Create the frame.
                JFrame frame = new JFrame("MultiShip Targeting Computer");
                //2. Set Icon
                frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/logo/ui_icon.png")));
                //3. Optional: What happens when the frame closes?
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                //4. Create your panel and put it in the frame.
                frame.getContentPane().add(new MsTcGui().getContentPane(), BorderLayout.CENTER); // new MsTcGui().getMainPanel()
                //5. Center the window
                frame.setLocationByPlatform(true);
                //6. Set the size depending on the size of the screen
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                frame.setPreferredSize(new Dimension((int) (screenSize.width / 2.70), (int) (screenSize.height / 1.43)));
                //7. Size the frame.
                frame.pack();
                //8. Show it.
                frame.setVisible(true);
            }
        });
    }
}
