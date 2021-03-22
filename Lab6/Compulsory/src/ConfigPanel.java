import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class ConfigPanel extends JPanel {
    final MainFrame frame;
    JLabel sidesLabel, colorLabel, sizesLabel, shapeLabel;

    JSpinner sidesField, sizesField;
    JComboBox colorCombo, shapeCombo;

    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        //create the label and the spinner
        sidesLabel = new JLabel("Number of sides:");
        colorLabel = new JLabel("Color:");
        sizesLabel = new JLabel("Size:");
        shapeLabel = new JLabel("Shape:");
        sidesField = new JSpinner(new SpinnerNumberModel(3, 3, 100, 1));
        sizesField = new JSpinner(new SpinnerNumberModel(10, 10, 100, 1));

        sidesField.setValue(6);     // default number of sides
        sizesField.setValue(50);    // default size number (radius)

        /*
        int red = (int)(Math.random()*256);
        int green = (int)(Math.random()*256);
        int blue = (int)(Math.random()*256);

        Color colors = new Color(red, green, blue);
        */
        String[] colors = {"Random", "Black"};
        colorCombo = new JComboBox(colors);

        String[] shapes = {"Regular Polygon", "Circle"};
        shapeCombo = new JComboBox(shapes);

        //JPanel uses FlowLayout by default
        add(shapeCombo);
        add(sidesLabel);
        add(sidesField);
        add(sizesLabel);
        add(sizesField);
        add(colorLabel);
        add(colorCombo);

        ActionListener l = arg0 -> {

            if (Objects.requireNonNull(shapeCombo.getSelectedItem()).toString().equals("Circle")) {
                sidesField.setVisible(false);
                sidesLabel.setVisible(false);
                sizesLabel.setText("Radius:");
            } else {
                sidesField.setVisible(true);
                sidesLabel.setVisible(true);
                sizesLabel.setText("Size:");
            }
        };
        shapeCombo.addActionListener(l);
        l.actionPerformed(new ActionEvent(shapeCombo, ActionEvent.ACTION_PERFORMED, ""));
    }

    public int sidesNumber() {
        return (int) sidesField.getValue();
    }

    public String getShapeColor() {
        return (String) colorCombo.getSelectedItem();
    }

    public String getShape() {
        return (String) shapeCombo.getSelectedItem();
    }

    public int getShapeSize() {
        return (int) sizesField.getValue();
    }
}
