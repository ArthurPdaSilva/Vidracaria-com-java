package ouvintes;

import classes.ListaDeUsuarios;
import janelas.JanelaCarrinho;
import janelas.JanelaLogin;
import janelas.JanelaMeuPerfil;
import janelas.JanelaDeAdministracao;
import janelas.JanelaEstrutura;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;

public class OuvinteMenu implements MouseListener{
    private JLabel label;
    private JanelaEstrutura tela;
    
    public OuvinteMenu(JLabel label, JanelaEstrutura tela){
        this.label = label;
        this.tela = tela;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
        switch (this.label.getText()) {
            case "Meu Perfil" -> {
                tela.dispose();
                new JanelaMeuPerfil();
            }
            case "Meu Carrinho" -> new JanelaCarrinho();
            case "Sair" -> {
                tela.dispose();
                ListaDeUsuarios lista = ListaDeUsuarios.obterInstancia();
                lista.setUsuarioLogado(null);
                new JanelaLogin();
            }
            default -> {
                tela.dispose();
                new JanelaDeAdministracao();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        label.setForeground(Color.white);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        label.setForeground(new Color(55, 114, 255));
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        label.setForeground(new Color(55, 114, 255));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        label.setForeground(Color.black);
    }
    
}