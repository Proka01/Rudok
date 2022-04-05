package gui.dialogs;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class SimpleDialog extends JDialog {

    public SimpleDialog(Frame parent, String title, boolean modal){
        super(parent, title, modal);

        setSize(500,600);
        setLocationRelativeTo(parent);

        //TODO: pitati zasto sa ovom linijom ne radi
        //postavljamo dijalog da bude BorderLayout
        //this.setLayout(new BorderLayout(this.getWidth(),this.getHeight()));

        JLabel infoJlabel = new JLabel("Aleksa Prokic - 14/2020 RN");
        infoJlabel.setBackground(Color.BLUE);
        add(infoJlabel,BorderLayout.NORTH);

        URL url = getClass().getResource("ja.jpeg");
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH));
        //JLabel imageLabel = new JLabel(new ImageIcon(url));
        JLabel imageLabel = new JLabel(imageIcon);
        add(imageLabel,BorderLayout.CENTER);

    }

}
