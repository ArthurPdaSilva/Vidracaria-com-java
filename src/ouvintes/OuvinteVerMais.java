package ouvintes;

import janelas.JanelaDeProduto;
import janelas.JanelaDeServico;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class OuvinteVerMais implements MouseListener{
    private JLabel verMais;
    private String nome;
    private JFrame tela;
    
    public OuvinteVerMais(JLabel verMais, String nome, JFrame tela){
        this.verMais = verMais;
        this.nome = nome;
        this.tela = tela;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
        if(nome.equals("Produtos")){
            new JanelaDeProduto();
        }else{
            new JanelaDeServico();
        }
        tela.dispose();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        verMais.setForeground(Color.black);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        verMais.setForeground(Color.white);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        verMais.setForeground(new Color(55, 114, 255));
    }
    
}
