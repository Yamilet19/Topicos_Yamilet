package practicas;

/** @author YAMILET */

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Saludador1 {
    public static void main(String args[]){
      Saludador1 saludador1 =new Saludador1();
      saludador1.run();
    }

    private void run() {
        JFrame frame = new JFrame("Saludador");
        frame.setSize(600, 400);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BorderLayout border = new BorderLayout();
        
        JLabel lbl = new JLabel("Escribe un nombre para saludar");
        JTextField txtField = new JTextField();
        JButton btnSaludar = new JButton("Saludar!");
        
        btnSaludar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                 JOptionPane.showMessageDialog(
                        frame, 
                        "Hola "+txtField.getText()+"!!",
                        "Saludo", 
                        JOptionPane.INFORMATION_MESSAGE  
                 );
            }
        });
        
        frame.add(lbl,BorderLayout.PAGE_START);
        frame.add(txtField,BorderLayout.CENTER);
        frame.add(btnSaludar,BorderLayout.PAGE_END);
        
        //frame.pack();
        frame.setVisible(true);
    }
}
