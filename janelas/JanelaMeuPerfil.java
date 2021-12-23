package janelas;

import DynamicLayout.DynamicLayout;
import static classes.Imagem.carregarImg;
import classes.ListaDeUsuarios;
import interfaces.InterfaceDeControle;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import ouvintes.OuvinteEnviarImagem;
import ouvintes.OuvinteBtnSair;
import ouvintes.OuvinteCampo;
import ouvintes.OuvintePlaceholder;
import ouvintes.OuvinteSalvarAlteracoesUser;

public final class JanelaMeuPerfil extends JanelaEstrutura implements InterfaceDeControle{
    private ListaDeUsuarios listaDeUsuarios;
    private JPanel imagemPanel;
    private JLabel imagemLabel;
    private long imagem;
    private JTextField nome;  
    private JPasswordField senha;
    private JTextField email;
    private long newImage;
    
    public JanelaMeuPerfil(){
        super(900, 700);
        this.listaDeUsuarios = ListaDeUsuarios.obterInstancia();
        this.imagem = listaDeUsuarios.getUsuarioLogado().getImagem();
        this.newImage = System.currentTimeMillis();
        super.addPainelCentral(50, 50, 800, 600);
        super.addTitulo(0, 10, 800, 50,  listaDeUsuarios.getUsuarioLogado().getNome());
        this.addItens();
        this.addBotoes();
        this.addTextos();
        addFoto();
        setVisible(true);
    }
    
    @Override
    public void addItens() {
        email = new JTextField(listaDeUsuarios.getUsuarioLogado().getEmail());
        email.setEnabled(false);
        email.setForeground(Color.black);
        email.setFont(new Font("Arial", Font.BOLD, 50));
        email.setBounds(400, 80, 350, 50);
        this.getPainel().add(email);
        
        nome = new JTextField(listaDeUsuarios.getUsuarioLogado().getNome());
        nome.addKeyListener(new OuvinteCampo());
        nome.addFocusListener(new OuvintePlaceholder(nome, "Nome"));
        nome.setFont(new Font("Arial", Font.BOLD, 50));
        nome.setBounds(400, 160, 350, 50);
        this.getPainel().add(nome);
        
        senha = new JPasswordField(listaDeUsuarios.getUsuarioLogado().getSenha());
        senha.addFocusListener(new OuvintePlaceholder(senha, "Senha"));
        senha.setFont(new Font("Arial", Font.BOLD, 50));
        senha.setBounds(400, 240, 350, 50);
        this.getPainel().add(senha);
        
    }
    
    private void addFoto()  {
        imagemPanel =  new JPanel();
        imagemPanel.setLayout(new DynamicLayout(300, 300));
        imagemPanel.setBounds(60, 72, 300, 300);
        imagemPanel.setBackground(null);
        imagemLabel = new JLabel("", JLabel.CENTER);
        imagemLabel.setBounds(10, 18, 285, 280);
        imagemLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        imagemLabel.addMouseListener(new OuvinteEnviarImagem(this, this.newImage, "user"));
        
        TitledBorder tituloFoto;
        tituloFoto = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Imagem");
        imagemPanel.setBorder(tituloFoto);
        
        try {
            imagemLabel.setIcon(carregarImg(listaDeUsuarios.getUsuarioLogado().getImagem(), "user", 285, 280));
        } catch (IOException ex) {
            imagemLabel.setText("Enviar Imagem");
        }
        
        imagemPanel.add(imagemLabel);
        
        this.getPainel().add(imagemPanel);
        
    }
    
    @Override
    public void addTextos() {}

    @Override
    public void addBotoes() {
        JButton[] btn = new JButton[2];
        String[] nomes = {"SALVAR", "VOLTAR"};
        int x = 480;
        
        for(int c = 0; c < 2; c++){
            btn[c] = new JButton(nomes[c]);
            btn[c].setBounds(x, 480, 300, 60);
            btn[c].setCursor(new Cursor(Cursor.HAND_CURSOR));
            btn[c].setForeground(Color.white);
            btn[c].setFont(new Font("Arial", Font.BOLD, 18));
            if(c == 0)
                btn[c].addActionListener(new OuvinteSalvarAlteracoesUser(this));
            else
                btn[c].addActionListener(new OuvinteBtnSair(this));
            btn[c].setBackground(c == 0 ? new Color(23, 184, 144) : new Color(223, 41, 53));
            btn[c].setBorder(null);
            x = 60;
            this.getPainel().add(btn[c]);
        }
    }
    

    public JPanel getImagemPanel() {
        return imagemPanel;
    }

    public JLabel getImagemLabel() {
        return imagemLabel;
    }

    public long getImagem() {
        return imagem;
    }

    public void setImagem(long imagem) {
        this.imagem = imagem;
    }

    public JTextField getNome() {
        return nome;
    }

    public void setNome(JTextField nome) {
        this.nome = nome;
    }

    public JPasswordField getSenha() {
        return senha;
    }

    public void setSenha(JPasswordField senha) {
        this.senha = senha;
    }

    public long getNewImage() {
        return newImage;
    }

    public void setNewImage(long newImage) {
        this.newImage = newImage;
    }

    public JTextField getEmail() {
        return email;
    }
    
}