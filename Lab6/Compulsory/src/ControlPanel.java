/*
am implementat ActionListener pentru a face handle la butoane (open, load...)
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ControlPanel extends JPanel implements ActionListener {
    final MainFrame frame;
    JButton saveBtn = new JButton("Save");
    JButton loadBtn = new JButton("Load");
    JButton resetBtn = new JButton("Reset");
    JButton exitBtn = new JButton("Exit");

    JFileChooser fc = new JFileChooser();


    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        //change the default layout manager (just for fun)
        setLayout(new GridLayout(2, 4));

        //configure listeners for all buttons
        add(saveBtn);
        add(loadBtn);
        add(resetBtn);
        add(exitBtn);
        saveBtn.addActionListener(this);
        loadBtn.addActionListener(this);
        resetBtn.addActionListener(this);
        exitBtn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        // filtru pt a recnoaste doar imaginile png
        fc.setFileFilter(new FileFilter() {

            public String getDescription() {
                return "PNG Images (*.png)";
            }

            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                } else {
                    String filename = f.getName().toLowerCase();
                    return filename.endsWith(".png");
                }
            }
        });

        if (actionEvent.getSource() == exitBtn)
            System.exit(0);
        else if (actionEvent.getSource() == saveBtn)
        {
            int returnVal = fc.showSaveDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION)
            {
                File filePath = new File(fc.getSelectedFile() + ".png");

                try {
                    ImageIO.write(frame.canvas.image, "PNG", filePath);
                } catch (IOException ex) {
                    System.err.println(ex);
                }
            }
        }
        else if(actionEvent.getSource() == loadBtn)
        {
            int returnVal = fc.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION)
            {
                File filePath = new File(String.valueOf(fc.getSelectedFile()));

                try {
                    BufferedImage img = ImageIO.read(filePath);
                    frame.canvas.setImage(img);
                } catch (IOException ex) {
                    System.err.println(ex);
                }

            }
        }
        else if(actionEvent.getSource() == resetBtn)
        {
            frame.canvas.resetScreenImage();
        }

    }
}
