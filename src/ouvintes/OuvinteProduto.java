package ouvintes;

import classes.Produto;
import janelas.JanelaCompraProduto;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class OuvinteProduto implements MouseListener{
    private Produto produto;
    
    public OuvinteProduto(Produto p) {
        this.produto = p;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        new JanelaCompraProduto(produto);
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

}
