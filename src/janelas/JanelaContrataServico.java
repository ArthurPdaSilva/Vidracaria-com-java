package janelas;

import static classes.Imagem.carregarImg;
import classes.ListaDeUsuarios;
import classes.ServicoAdquirido;
import classes.Usuario;
import interfaces.InterfaceDeControle;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.io.IOException;
import java.text.DecimalFormat;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import ouvintes.OuvinteAddServico;


public class JanelaContrataServico extends JanelaEstrutura implements InterfaceDeControle{
    private ServicoAdquirido servicoAdd;
    private Usuario usuarioLogado;
    private JLabel[] campos;
    private float valor;
    
    public JanelaContrataServico(ServicoAdquirido s){
        super(400, 290);
        super.addPainelCentral(0, 0, 400, 290);
        this.servicoAdd = s;
        this.usuarioLogado = ListaDeUsuarios.obterInstancia().getUsuarioLogado();
        this.addItens();
        this.addBotoes();
        this.addTextos();
        setResizable(false);
        setDefaultCloseOperation(JanelaCompraProduto.DISPOSE_ON_CLOSE);
        setVisible(true);        
    }

    @Override
    public void addItens() {
        JLabel icone = new JLabel();
        try {
            icone.setIcon(carregarImg(servicoAdd.getServico().getFoto(), "servico", 150, 150));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
        icone.setBounds(20, 60, 180, 150);
        this.getPainel().add(icone);
        
        JTextArea descricao = new JTextArea();
        descricao.setText(servicoAdd.getServico().getDescricao());
        descricao.setEditable(false);
        descricao.setLineWrap(true);
        descricao.setWrapStyleWord(true);
        descricao.setBounds(200, 75, 150, 50);
        this.getPainel().add(descricao);
    }

    @Override
    public void addTextos() {
        DecimalFormat df = new DecimalFormat("##.00");
        this.valor = servicoAdd.getServico().getPreco();
        String[] nomes = {"Nome: " + servicoAdd.getServico().getNome(), "Descrição:", "Preço: R$" + df.format(valor)};
        this.campos = new JLabel[3];
        int y = 20;
                
        for(int c = 0; c < 3; c++){
            campos[c] = new JLabel(nomes[c]);
            campos[c].setFont(new Font("Arial", Font.BOLD, 35));
            switch (c) {
                case 0 -> campos[c].setBounds(20, y, 150, 35);
                case 1 -> {
                    campos[c].setBounds(200, y, 200, 35);
                    y += 120;
                }
                default -> campos[c].setBounds(200, y, 200, 35);
            }
            this.getPainel().add(campos[c]);
        }
    }
    
    @Override
    public void addBotoes() {
        JButton btn = new JButton("CONTRATAR");
        btn.addActionListener(new OuvinteAddServico(this, servicoAdd));
        btn.setBorder(null);
        btn.setBounds(20, 220, 350, 40);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBackground(new Color(23, 184, 144));
        btn.setForeground(Color.white);
        this.getPainel().add(btn);
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }
    
}
