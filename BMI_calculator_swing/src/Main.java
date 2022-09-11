import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    Main(){
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension screen_dimension = new Dimension(600,200);
        setSize(screen_dimension);
        setLocation((dim.width/2)-(screen_dimension.width/2),(dim.height/2)-(screen_dimension.height/2));

        setLayout(new BorderLayout());

        JPanel center_panel = new JPanel();
        center_panel.setLayout(new BoxLayout(center_panel,BoxLayout.X_AXIS));

        center_panel.add(Box.createHorizontalGlue());

        JPanel labels_panel = new JPanel();
        labels_panel.setLayout(new BoxLayout(labels_panel,BoxLayout.Y_AXIS));
        JLabel height_lbl = new JLabel("Your Height In Centimeters");
        JLabel weight_lbl = new JLabel("Your Weight In Kilograms");
        labels_panel.add(height_lbl);
        labels_panel.add(Box.createVerticalStrut(20));
        labels_panel.add(weight_lbl);

        center_panel.add(labels_panel);
        center_panel.add(Box.createHorizontalStrut(30));

        JPanel text_fields_panel = new JPanel();
        text_fields_panel.setLayout(new BoxLayout(text_fields_panel,BoxLayout.Y_AXIS));
        Dimension text_fields_dimension = new Dimension(50,30);

        JTextField height_text_field = new JTextField();
        height_text_field.setPreferredSize(text_fields_dimension);
        height_text_field.setMaximumSize(text_fields_dimension);
        height_text_field.setMinimumSize(text_fields_dimension);

        JTextField weight_text_field = new JTextField();
        weight_text_field.setPreferredSize(text_fields_dimension);
        weight_text_field.setMinimumSize(text_fields_dimension);
        weight_text_field.setMaximumSize(text_fields_dimension);
        text_fields_panel.add(height_text_field);
        text_fields_panel.add(Box.createVerticalStrut(10));
        text_fields_panel.add(weight_text_field);

        center_panel.add(text_fields_panel);
        center_panel.add(Box.createHorizontalStrut(20));

        JPanel information_panel = new JPanel();
        information_panel.setLayout(new BoxLayout(information_panel, BoxLayout.Y_AXIS));

        JPanel warning_panel = new JPanel();
        warning_panel.setLayout(new GridBagLayout());
        warning_panel.setMaximumSize(new Dimension(150,30));
        warning_panel.setPreferredSize(new Dimension(150,30));
        warning_panel.setMinimumSize(new Dimension(150,30));
        warning_panel.setBorder(BorderFactory.createLineBorder(Color.black));
        JLabel warning_lbl = new JLabel("You are Over weighted");
        warning_panel.add(warning_lbl);
        information_panel.add(warning_panel);

        information_panel.add(Box.createVerticalStrut(10));

        JPanel radio_buttons = new JPanel();
        radio_buttons.setBorder(BorderFactory.createLineBorder(Color.black));
        radio_buttons.setMaximumSize(new Dimension(150,30));
        radio_buttons.setPreferredSize(new Dimension(150,30));
        radio_buttons.setMinimumSize(new Dimension(150,30));

        radio_buttons.setLayout(new BoxLayout(radio_buttons,BoxLayout.X_AXIS));
        JRadioButton meters = new JRadioButton("Meters");
        meters.setSelected(true);
        JRadioButton inches = new JRadioButton("Inches");
        radio_buttons.add(meters);
        radio_buttons.add(inches);
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(meters);
        buttonGroup.add(inches);

        meters.addActionListener(e -> {
            height_lbl.setText("Your Height In Centimeters");
            weight_lbl.setText("Your Weight in Kilograms");

            double weight = Double.parseDouble(weight_text_field.getText());
            double height = Double.parseDouble(height_text_field.getText());

            height = height*(1/0.39371);
            weight = weight*(1/2.20462);

            weight_text_field.setText(String.format("%.2f", weight));
            height_text_field.setText(String.format("%.2f",height));

        });

        inches.addActionListener(e -> {
            height_lbl.setText("Your Height in Inches");
            weight_lbl.setText("Your Weight in Pounds");

            double weight = Double.parseDouble(weight_text_field.getText());
            double height = Double.parseDouble(height_text_field.getText());

            height = height*(0.39371);
            weight = weight*(2.20462);

            weight_text_field.setText(String.format("%.2f", weight));
            height_text_field.setText(String.format("%.2f",height));
        });

        information_panel.add(radio_buttons);

        center_panel.add(information_panel);
        center_panel.add(Box.createHorizontalStrut(20));


        JButton calculate_BMI = new JButton("Calculate BMI");
        center_panel.add(calculate_BMI);

        calculate_BMI.addActionListener(e -> {
            double height = Double.parseDouble(height_text_field.getText());
            double weight = Double.parseDouble(weight_text_field.getText());

            if(meters.isSelected()){
                calculation(height, weight,warning_lbl);
            }

            if(inches.isSelected()){
                height = height*(1/0.39371);
                weight = weight*(1/2.20462);
                calculation(height,weight,warning_lbl);
            }
        });

        center_panel.add(Box.createHorizontalGlue());


        JPanel bottom_panel = new JPanel(new GridBagLayout());
        JLabel bottom_lbl = new JLabel("All Right Reserved to KUST 2021");
        bottom_panel.add(bottom_lbl);

        add(center_panel,BorderLayout.CENTER);
        add(bottom_panel,BorderLayout.SOUTH);

    }

    static void calculation(double height, double weight,JLabel warning_lbl){
        System.out.println("height: " + height);
        System.out.println("weight: " + weight);

        height = height/100;
        double result = weight/(height*height);
        System.out.println(result);

        if(result <18.5){
            warning_lbl.setText("Underweight");
        }else if(result <24.9){
            warning_lbl.setText("Normal Weight");
        }else if(result < 29.9){
            warning_lbl.setText("Overweight");
        }else{
            warning_lbl.setText("Obesity");
        }

    }


    public static void main(String[] args) {
        Main main = new Main();
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setVisible(true);
//        main.pack();
    }
}
