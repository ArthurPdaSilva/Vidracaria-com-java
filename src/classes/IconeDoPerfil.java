package classes;

import DynamicLayout.DynamicLayout;
import static classes.Imagem.carregarImg;
import janelas.JanelaEstrutura;
import janelas.JanelaInicio;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import ouvintes.OuvinteMenu;

public class IconeDoPerfil {
    
    public static void addIcone(JPanel painel, JanelaEstrutura tela, int x) throws IOException{
        JPanel opcoes = new JPanel();
        ListaDeUsuarios listaDeUsuarios = ListaDeUsuarios.obterInstancia();
        
        JLabel icone = new JLabel();
        JPanel borda = new JPanel();
        borda.setBounds(x-90, 0, 90, 80);
        borda.setLayout(new DynamicLayout(90, 80));
        icone.setBounds(20, 15, 82, 62);
        
        TitledBorder tituloFoto;
        tituloFoto = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(painel == null ? Color.white : Color.black), listaDeUsuarios.getUsuarioLogado().getNome());
        tituloFoto.setTitleColor(painel == null ? Color.white : Color.black);
        borda.setBorder(tituloFoto);
        borda.setBackground(null);
        icone.setIcon(carregarImg(listaDeUsuarios.getUsuarioLogado().getImagem(),"user", 50, 50));
        
        icone.setCursor(new Cursor(Cursor.HAND_CURSOR));
        icone.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JanelaInicio janela = null;
                if(tela instanceof JanelaInicio)
                    janela = (JanelaInicio) tela;
                if(opcoes.isVisible()){
                    opcoes.setVisible(false);
                    if(tela instanceof JanelaInicio){
                        janela.getVerMais().setVisible(true);
                    }
                        
                }else{
                    opcoes.setVisible(true);
                    if(tela instanceof JanelaInicio)
                        janela.getVerMais().setVisible(false);
                }
            }
            
            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
            
        });
        
        opcoes.setCursor(new Cursor(Cursor.HAND_CURSOR));
        opcoes.setLayout(new GridLayout(3 ,1));
        opcoes.setBounds(x-90, 80, 90, 90);
        String bt = listaDeUsuarios.getUsuarioLogado().getEmail().equals(listaDeUsuarios.getUsuarios().get(0).getEmail()) ? "Tela de Adm" : "Meu Carrinho";
        String[] ops = {"Meu Perfil", bt, "Sair"};
        
        for(int i = 0; i < 3; i++){
            JLabel label = new JLabel(ops[i], JLabel.CENTER);
            label.addMouseListener(new OuvinteMenu(label, tela));
            opcoes.add(label);
        }
        
        opcoes.setVisible(false);
        borda.add(icone);
        
        if(painel == null){
            tela.add(borda);
            tela.add(opcoes);
        }else{
            painel.add(borda);
            painel.add(opcoes);
        }
        
        
    }
}
