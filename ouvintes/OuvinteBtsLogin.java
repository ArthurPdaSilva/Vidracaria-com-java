package ouvintes;

import janelas.JanelaEsqueceuASenha;
import janelas.JanelaDeCadastro;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;

public class OuvinteBtsLogin implements MouseListener{
    private JLabel btn;
    
    public OuvinteBtsLogin(JLabel btn){
        this.btn = btn;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if(btn.getText().equals("Esqueceu sua senha?"))
            new JanelaEsqueceuASenha();
        else
            new JanelaDeCadastro();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        btn.setForeground(Color.BLACK);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        btn.setForeground(new Color(55, 114, 255));
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(btn.getText().equals("Esqueceu sua senha?"))
            btn.setForeground(Color.DARK_GRAY);
        else
            btn.setForeground(Color.white);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        btn.setForeground(new Color(55, 114, 255));
    }
   
}