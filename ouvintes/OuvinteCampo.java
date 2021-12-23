package ouvintes;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class OuvinteCampo implements KeyListener{

    @Override
    public void keyTyped(KeyEvent e) {
        char key = e.getKeyChar();
        if(!" ".equals(key+"") && !Character.isLetter(key))
            e.consume();
    }

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

}
