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
    JButton undoBtn = new JButton("Undo");

    JFileChooser fc = new JFileChooser();

    /**
     * am adaugat 2 componente noi:
     *  JTextArea -> un log (istoric) ce tine evidenta butoanelor apasate etc..
     *  JScrollPane -> scroll pt log
     */
    JTextArea log = new JTextArea(5, 10);
    JScrollPane logScrollPane = new JScrollPane(log);

    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        //change the default layout manager (just for fun)
        setLayout(new GridLayout(1, 5));

        //configure listeners for all buttons
        add(undoBtn);
        add(saveBtn);
        add(loadBtn);
        add(resetBtn);
        add(exitBtn);
        add(logScrollPane, BorderLayout.CENTER);
        undoBtn.addActionListener(this);
        saveBtn.addActionListener(this);
        loadBtn.addActionListener(this);
        resetBtn.addActionListener(this);
        exitBtn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

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

                //This is where a real application would save the file.
                log.append("Saving: " + filePath.getName() + "\n");
            }
            else {
                log.append("Save command cancelled by user.\n");
            }
            log.setCaretPosition(log.getDocument().getLength());
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

                //This is where a real application would save the file.
                log.append("Loading: " + filePath.getName() + "\n");
            }
            else {
                log.append("Load command cancelled by user.\n");
            }
            log.setCaretPosition(log.getDocument().getLength());
        }
        else if(actionEvent.getSource() == resetBtn)
        {
            frame.canvas.resetScreenImage();
            log.append("Draw again.\n");
        }
        /*
         * in momentul cand apas pe acest buton
         * voi lua ultima figura, o desenez alba (o "sterg"),
         * apoi elimin din arraylist-ul de shape-uri figura
         */
        else if(actionEvent.getSource() == undoBtn)
        {
            Shape shape = frame.canvas.removeLastShape();
            if(shape != null) {
                log.append("Succesfuly removed the last shape.\n");
                frame.canvas.graphics.setColor(Color.white);
                frame.canvas.graphics.fill(shape);
                frame.canvas.shapes.remove(shape);
                System.out.println(frame.canvas.shapes.size());
                repaint();
            }
        }
    }
}
