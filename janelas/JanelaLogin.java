package janelas;

import interfaces.InterfaceDeControle;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import classes.ListaDeUsuarios;
import ouvintes.OuvinteEfetuarLogin;
import ouvintes.OuvinteBtsLogin;
import ouvintes.OuvintePlaceholder;

public final class JanelaLogin extends JanelaEstrutura implements InterfaceDeControle{
    private JTextField email;
    private JPasswordField senha;
    
    public JanelaLogin(){
        super(980,600);
        super.addPainelCentral(290, 80, 400, 300);
        super.addTitulo(0, 10, 400, 60, "Log in");
        this.addItens();
        this.addBotoes();
        this.addTextos();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
        if(ListaDeUsuarios.obterInstancia().getUsuarios().isEmpty()) {
    		JOptionPane.showMessageDialog(null, "Cadastre o proprietário!");
    		new JanelaDeCadastro();
    	}
    }
    
    
    @Override
    public void addItens() {
        email = new JTextField();
        email.setFont(new Font("Arial", Font.BOLD, 40));
        email.setBounds(25, 90, 350, 40);
        email.setText("Email");
        email.setForeground(Color.lightGray);
        
        //Implementação de placeholder no campo email
        email.addFocusListener(new OuvintePlaceholder(email, "Email"));
        this.getPainel().add(email);
        
        senha = new JPasswordField();
        senha.setFont(new Font("Arial", Font.BOLD, 40));
        senha.setBounds(25, 160, 350, 40);
        senha.setForeground(Color.lightGray);
        senha.setText("Senha");
        senha.setToolTipText("Insira sua senha");
        //Implementação de placeholder no campo senha
        senha.addFocusListener(new OuvintePlaceholder(senha, "Senha"));
        this.getPainel().add(senha);
        
        JLabel cadastro = new JLabel("Cadastrar-se", JLabel.CENTER);
        cadastro.setFont(new Font("Arial", Font.BOLD, 40));
        cadastro.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cadastro.setBounds(290, 380, 400, 40);
        cadastro.setForeground(new Color(55, 114, 255));
        cadastro.addMouseListener(new OuvinteBtsLogin(cadastro));
        add(cadastro);
    }

    @Override
    public void addTextos() {
        JLabel esqueceuSenha = new JLabel("Esqueceu sua senha?", JLabel.LEFT);
        esqueceuSenha.setBounds(25, 200, 150, 25);
        esqueceuSenha.setFont(new Font("Arial", Font.ITALIC, 25));
        esqueceuSenha.setForeground(new Color(55, 114, 255));
        esqueceuSenha.setCursor(new Cursor(Cursor.HAND_CURSOR));
        // Deixando label clicável
        esqueceuSenha.addMouseListener(new OuvinteBtsLogin(esqueceuSenha)); 
        this.getPainel().add(esqueceuSenha);
    }

    @Override
    public void addBotoes() {
        JButton entrar = new JButton("Entrar");
        entrar.setBorder(null);
        entrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        entrar.setFont(new Font("Arial", Font.BOLD, 40));
        entrar.setBounds(25, 240, 350, 40);
        entrar.setBackground(new Color(55, 114, 255));
        entrar.setForeground(Color.white);
        entrar.addActionListener(new OuvinteEfetuarLogin(this));
        this.getPainel().add(entrar);
    }

    public JTextField getEmail() {
        return email;
    }

    public void setEmail(JTextField email) {
        this.email = email;
    }

    public JTextField getSenha() {
        return senha;
    }

    public void setSenha(JPasswordField senha) {
        this.senha = senha;
    }

}