package janelas;

import interfaces.InterfaceDeControle;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import ouvintes.OuvinteEnviarCodigoDeRecuperacao;
import ouvintes.OuvinteBtnSair;

public final class JanelaEsqueceuASenha extends JanelaEstrutura implements InterfaceDeControle{
    private JTextField email;
    
    public JanelaEsqueceuASenha(){
        super(300,200);
        super.addPainelCentral(0, 0, 300, 200);
        super.addTitulo(0, 0, 300, 35, "Enviar o código de recuperação");
        this.addItens();
        this.addBotoes();
        this.addTextos();
        setDefaultCloseOperation(JanelaEsqueceuASenha.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void addItens() {
        email = new JTextField();
        email.setFont(new Font("Arial", Font.BOLD, 30));
        email.setBounds(25, 65, 250, 30);
        this.getPainel().add(email);
    }

    @Override
    public void addTextos() {
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        emailLabel.setBounds(25, 40, 300, 30);
        this.getPainel().add(emailLabel);
    }

    @Override
    public void addBotoes() {
        String[] nomes = {"Voltar", "Enviar"};
        JButton[] btns = new JButton[2];
        int x = 25;
        
        for(int c = 0; c < 2; c++){
            btns[c] = new JButton(nomes[c]);
            btns[c].setBounds(x, 120, 100, 30);
            btns[c].setBackground(c == 0 ? new Color(223, 41, 53) : new Color(23, 184, 144));
            btns[c].setForeground(Color.white);
            btns[c].setCursor(new Cursor(Cursor.HAND_CURSOR));
            btns[c].setBorder(null);
            btns[c].addActionListener(c == 0 ? new OuvinteBtnSair(this) : new OuvinteEnviarCodigoDeRecuperacao(this));
            this.getPainel().add(btns[c]);
            x += 150;
        }
    }
    
    public JTextField getEmail() {
        return email;
    }
}