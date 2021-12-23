package janelas;

import static classes.Imagem.carregarImg;
import classes.ListaDeUsuarios;
import classes.Produto;
import classes.Usuario;
import interfaces.InterfaceDeControle;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import ouvintes.OuvinteBtnSair;
import ouvintes.OuvinteMudanca;

public final class JanelaCompraProduto extends JanelaEstrutura implements InterfaceDeControle{
    private Produto produto;
    private JSpinner quantidade;
    private JLabel[] campos;
    private float valor;
    
    public JanelaCompraProduto(Produto p){
        super(500, 400);
        super.addPainelCentral(0, 0, 500, 400);
        super.addSeparador(80, 280, 320, 20, "HORIZONTAL");
        this.produto = p;
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
            icone.setIcon(carregarImg(produto.getFoto(), "produto", 150, 150));
        } catch (IOException ex) {
            Logger.getLogger(JanelaDeProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
        icone.setBounds(40, 80, 180, 150);
        this.getPainel().add(icone);
        
        if(produto.getQuantidade() > 0){
            SpinnerModel ModeloQuantidade = new SpinnerNumberModel(1, 1, produto.getQuantidade(), 1);
            quantidade = new JSpinner(ModeloQuantidade);
            quantidade.addChangeListener(new OuvinteMudanca(this));
            quantidade.setBounds(370, 160, 50, 35);
            this.getPainel().add(quantidade);
        }
        
        JTextArea descricao = new JTextArea();
        descricao.setText(produto.getDescricao());
        descricao.setEditable(false);
        descricao.setLineWrap(true);
        descricao.setWrapStyleWord(true);
        descricao.setBounds(250, 75, 150, 50);
        this.getPainel().add(descricao);
        
    }

    @Override
    public void addTextos() {
        DecimalFormat df = new DecimalFormat("##.00");
        this.valor = produto.getPreco();
        String[] nomes = {produto.getNome(), "Descri��o:", "Quantidade:", "Pre�o: R$" + df.format(valor)};
        campos = new JLabel[4];
        int y = 40;
        
        for(int c = 0; c < 4; c++){
            if(produto.getQuantidade() < 1 && nomes[c].equals("Quantidade:")){
                campos[c] = new JLabel("Esgotado");
                campos[c].setForeground(Color.red);
            }else
                campos[c] = new JLabel(nomes[c]);
            
            campos[c].setFont(new Font("Arial", Font.BOLD, 35));
            switch (c) {
                case 0 -> campos[c].setBounds(40, 40, 150, 35);
                case 1 -> {
                    campos[c].setBounds(250, y, 200, 35);
                    y += 120;
                }
                default -> {
                    campos[c].setBounds(250, y, 200, 35);
                    y += 40;
                }
            }
            
            
            this.getPainel().add(campos[c]);
        }
    }

    @Override
    public void addBotoes() {
        String[] nomes = {"VOLTAR", "COMPRAR"};
        JButton[] btns = new JButton[2];
        int x = 40;
        for(int c = 0; c < 2; c++){
            btns[c] = new JButton(nomes[c]);
            if(produto.getQuantidade() < 1 && nomes[c].equals("COMPRAR"))
                btns[c].setEnabled(false);
            btns[c].setBackground(c == 0 ? new Color(223, 41, 53) : new Color(23, 184, 144));
            btns[c].setForeground(Color.white);
            btns[c].setCursor(new Cursor(Cursor.HAND_CURSOR));
            if(c == 0)
                btns[c].addActionListener(new OuvinteBtnSair(this));
            else
                btns[1].addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ListaDeUsuarios lista = ListaDeUsuarios.obterInstancia();
                        Usuario userLogado = lista.getUsuarioLogado();
                        if(userLogado.adicionarProduto(produto, (int) quantidade.getValue())){
                            JOptionPane.showMessageDialog(null, "Produto adicionado ao carrinho com sucesso!");
                            dispose();
                        }else
                            JOptionPane.showMessageDialog(null, "Produto já adicionado ao carrinho");
                            
                    }
                });
            btns[c].setBounds(x, 320, 200, 40);
            btns[c].setFont(new Font("Arial", Font.BOLD, 40));
            x += 220;
            this.getPainel().add(btns[c]);
        }
    }
    
    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public JSpinner getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(JSpinner quantidade) {
        this.quantidade = quantidade;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public JLabel[] getCampos() {
        return campos;
    }

    public void setCampos(JLabel[] campos) {
        this.campos = campos;
    }

}
