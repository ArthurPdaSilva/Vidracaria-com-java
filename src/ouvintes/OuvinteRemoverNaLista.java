package ouvintes;

import classes.Produto;
import janelas.JanelaCarrinho;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class OuvinteRemoverNaLista implements MouseListener {
    private JanelaCarrinho carrinho;
    private Produto produto;
    
    public OuvinteRemoverNaLista(JanelaCarrinho aThis, Produto get) {
        this.carrinho = aThis;
        this.produto = get;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.carrinho.getUsuarioLogado().apagarProdutoAdquirido(produto);
        this.carrinho.dispose();
        new JanelaCarrinho();
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

}