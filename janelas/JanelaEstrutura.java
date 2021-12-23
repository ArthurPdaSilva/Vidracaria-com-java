package janelas;

import DynamicLayout.DynamicLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

/**
 * Classe mãe de todas as telas
 * 
 * @author Vidraçaria
 */

public abstract class JanelaEstrutura extends JFrame{
    private JPanel painel;
    /**
     * Cria um modelo padrão de tela sempre que chamado
     * 
     * @param w → passará a largura do JFrame, do inglês: width
     * @param h → passará a altura do JFrame, do inglês: height
     * 
     */
    public JanelaEstrutura(int w, int h){
        setTitle("Vidraçaria Campos");
        setIconImage(new ImageIcon("imagens/icones/icone.png").getImage());
        setSize(w, h);
        getContentPane().setBackground(new Color(18, 38, 58));
        setLayout(new DynamicLayout(w, h));
        setLocationRelativeTo(null);
    }

    public void addPainelCentral(int x, int y, int w, int h) {
        painel = new JPanel(); 
        painel.setLayout(new DynamicLayout(w, h));
        painel.setBounds(x, y, w, h);
        painel.setBackground(Color.white);
        painel.setOpaque(true);
        add(painel);
    }

    public void addTitulo(int x, int y, int w, int h, String t){
        JLabel titulo = new JLabel(t, JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 50));
        titulo.setBounds(x, y, w, h);
        painel.add(titulo);
    }
    
    public void addSeparador(int x, int y, int w, int h, String alinhamento) {
        JSeparator separa = alinhamento.equals("VERTICAL") ? new JSeparator(SwingConstants.VERTICAL) : new JSeparator(SwingConstants.HORIZONTAL);
        separa.setBounds(x, y, w, h);
        separa.setBackground(Color.black);
        painel.add(separa);
    }
    
    public JPanel getPainel() {
        return painel;
    }

    public void setPainel(JPanel painel) {
        this.painel = painel;
    }

}
