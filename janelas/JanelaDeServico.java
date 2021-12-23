package janelas;

import classes.ListaDeServicos;
import interfaces.InterfaceDeControle;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import ouvintes.OuvinteBtnSair;
import ouvintes.OuvinteServicoSelecionado;

public final class JanelaDeServico extends JanelaEstrutura implements InterfaceDeControle{
    private JComboBox<String> cbServicos;
    private JTextField[] campos;
    
    public JanelaDeServico(){
        super(800, 700);
        super.addPainelCentral(50, 50, 700, 600);
        super.addTitulo(0, 10, 700, 60, "Serviço");
        super.addSeparador(350, 100, 20, 400, "VERTICAL");
        this.addItens();
        this.addBotoes();
        this.addTextos();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void addTextos() {
        String[] nomes = {"Endereço:", "Serviço oferecidos:", "Rua:", "Complemento:"};
        JLabel[] textos = new JLabel[4];
        int y = 180;
        
        for(int c = 0; c < 4; c++){
            textos[c] = new JLabel(nomes[c]);
            if(c == 1)
                textos[c].setBounds(400, (y - 80), 300, 40);
            else{
                textos[c].setBounds(50, y, 200, 40);
                y += 80;
            }
            textos[c].setFont(new Font("Arial", Font.BOLD, 40));
            this.getPainel().add(textos[c]);
        }
    }

    @Override
    public void addItens() {
        ListaDeServicos servicos = ListaDeServicos.obterInstancia();
        String[] serv = new String[servicos.getServicos().size()];
        for(int c = 0; c < serv.length; c++){
            serv[c] = servicos.getServicos().get(c).getNome();
        }
        cbServicos = new JComboBox<>(serv);
        cbServicos.setBounds(400, 225, 300, 50);
        cbServicos.setFont(new Font("Arial", Font.PLAIN, 20));
        this.getPainel().add(cbServicos);
        
        campos = new JTextField[3];
        int y = 225;
        
        for(int c = 0; c < 3; c++){
            campos[c] = new JTextField();
            campos[c].setFont(new Font("Arial", Font.PLAIN, 30));
            campos[c].setBounds(50, y, 200, 40);
            this.getPainel().add(campos[c]);
            y += 80;
        }
    }

    @Override
    public void addBotoes() {
        String[] nomes = {"VOLTAR", "CONTRATAR"}; 
        JButton[] bts = new JButton[2];
        
        for(int c = 0; c < 2; c++){
            bts[c] = new JButton(nomes[c]);
            bts[c].setBorder(null);
            bts[c].setFont(new Font("Arial", Font.BOLD, 15));
            bts[c].setBackground(c == 0 ? new Color(223, 41, 53) : new Color(23, 184, 144));
            bts[c].setForeground(Color.white);
            bts[c].setCursor(new Cursor(Cursor.HAND_CURSOR));
            if(c == 0)
                bts[c].setBounds(10, 550, 340, 40);
            else
                bts[c].setBounds(350, 550, 340, 40);
            
            bts[c].addActionListener(c == 0 ? new OuvinteBtnSair(this) : new OuvinteServicoSelecionado(this));
            this.getPainel().add(bts[c]);
        }
    }

    public JComboBox<String> getCbServicos() {
        return cbServicos;
    }

    public void setCbServicos(JComboBox<String> cbServicos) {
        this.cbServicos = cbServicos;
    }

    public JTextField[] getCampos() {
        return campos;
    }

    public void setCampos(JTextField[] campos) {
        this.campos = campos;
    }
    
}
