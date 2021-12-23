package ouvintes;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTextField;

public class OuvinteCampoNumero implements KeyListener{

    @Override
    public void keyTyped(KeyEvent e) {
        JTextField campo = (JTextField) e.getSource();
        String numbers = "0123456789";
        char key = e.getKeyChar();
        if(!numbers.contains(key + ""))
            e.consume();
    }

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

}
