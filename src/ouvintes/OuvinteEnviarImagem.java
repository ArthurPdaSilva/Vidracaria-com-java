package ouvintes;

import classes.Imagem;
import static classes.Imagem.carregarImg;
import janelas.JanelaMeuPerfil;
import janelas.JanelaProdutoEServico;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class OuvinteEnviarImagem implements MouseListener{
    private JanelaProdutoEServico telaPadrao;
    private JanelaMeuPerfil telaPerfil;
    private Long nomeFoto;
    private String tipo;
    
    public OuvinteEnviarImagem(JanelaProdutoEServico tela, Long nomeFoto, String tipo){
        this.telaPadrao = tela;
        this.nomeFoto = nomeFoto;
        this.tipo = tipo;
    }
    
    public OuvinteEnviarImagem(JanelaMeuPerfil tela, Long nomeFoto, String tipo){
        this.telaPerfil = tela;
        this.nomeFoto = nomeFoto;
        this.tipo = tipo;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        JFileChooser abrirArq = new JFileChooser();
        abrirArq.addChoosableFileFilter(new FileNameExtensionFilter("Imagens", "jpg", "png", "gif", "jpeg"));
        abrirArq.removeChoosableFileFilter(abrirArq.getFileFilter());
        
        abrirArq.showOpenDialog(null);
        File foto = abrirArq.getSelectedFile();
        
        Imagem imagem = new Imagem(foto);
        try {imagem.salvarImg(nomeFoto, tipo);} catch (IOException ex) {}
        
        try{
            if(telaPadrao != null){
                telaPadrao.getImagemLabel().setIcon(carregarImg(nomeFoto, tipo, 150, 150));
                telaPadrao.setImagem(nomeFoto);
            }else{
                telaPerfil.getImagemLabel().setIcon(carregarImg(nomeFoto, tipo, 300, 300));
                telaPerfil.setImagem(nomeFoto);
            }
            
        }catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar a foto, tente reenvia-la ou escolha outra");
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
