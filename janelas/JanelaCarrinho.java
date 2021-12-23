package janelas;

import DynamicLayout.DynamicLayout;
import static classes.Imagem.carregarImg;
import classes.ListaDeUsuarios;
import classes.Usuario;
import interfaces.InterfaceDeControle;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;
import java.text.DecimalFormat;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import ouvintes.OuvinteAddProduto;
import ouvintes.OuvinteBtnSair;
import ouvintes.OuvinteRemoverNaLista;

public class JanelaCarrinho extends JanelaEstrutura implements InterfaceDeControle{
    private Usuario usuarioLogado;
    private float valor;
    
    public JanelaCarrinho(){
        super(800, 600);
        super.addPainelCentral(0, 0, 800, 600);
        super.addTitulo(0, 10, 800, 60, "Carrinho de compras");
        super.addSeparador(80, 80, 600, 20, "HORIZONTAL");
        this.usuarioLogado = ListaDeUsuarios.obterInstancia().getUsuarioLogado();
        this.addItens();
        this.addBotoes();
        setResizable(false);
        setVisible(true);
    }
        
    @Override
    public void addItens() {
        JPanel telaItem = new JPanel();
        telaItem.setBounds(0, 100, 800, 1000);
        telaItem.setPreferredSize(new Dimension(0, 1000));
        telaItem.setLayout(new DynamicLayout(800, 1000));
        telaItem.setBackground(Color.white);
        telaItem.setOpaque(true);
        JScrollPane scroll = new JScrollPane(telaItem);
        scroll.setBounds(0, 140, 800, 400);
        
        if(usuarioLogado.getProdutosAdquiridos().isEmpty())
            this.addTextos();
        else{
            int x = 40, y = 10;
            DecimalFormat df = new DecimalFormat("##.00");
            String[] nomes = {"Total", "Qtnd.", "X"};
            JLabel[] colunas = new JLabel[3];
            JLabel[] opcoes = new JLabel[5];
            
            for(int c = 0; c < usuarioLogado.getProdutosAdquiridos().size(); c++){
                JPanel tela = new JPanel();
                tela.setLayout(new DynamicLayout(800, 200));
                tela.setBackground(Color.white);
                tela.setBounds(x, y, 800, 200);
                
                for(int cc = 0; cc < 5; cc++){
                    if(cc < 2){
                        opcoes[cc] = new JLabel(cc == 0 ? "" : "Nome");
                        
                        if(cc == 0){
                            try {
                                opcoes[0].setBounds(0, 0, 150, 150);
                                opcoes[0].setIcon(carregarImg(usuarioLogado.getProdutosAdquiridos().get(c).getFoto(), "produto", 150, 150));
                            } catch (IOException e) {
                                JOptionPane.showMessageDialog(null, "Falha ao enviar a imagem!");
                            }
                        }else{
                            opcoes[cc].setBounds(x, 0, 100, 30);
                            opcoes[cc].setToolTipText(usuarioLogado.getProdutosAdquiridos().get(c).getNome());
                        }
                    }else{
                        opcoes[cc] = new JLabel(nomes[cc-2], JLabel.CENTER);
                        opcoes[cc].setBounds(x, 0, 100, 30);
                        
                        if(cc == 4){
                            opcoes[cc].setOpaque(true);
                            opcoes[cc].setBounds(x, 0, 50, 30);
                            opcoes[cc].setBackground(new Color(223, 41, 53));
                            opcoes[cc].setCursor(new Cursor(Cursor.HAND_CURSOR));
                            opcoes[cc].setForeground(Color.white);
                            opcoes[cc].addMouseListener(new OuvinteRemoverNaLista(this, usuarioLogado.getProdutosAdquiridos().get(c)));
                            x = 40;
                        }
                    }    
                    x += 150;
                    opcoes[cc].setFont(new Font("Arial", Font.BOLD, 30));
                    tela.add(opcoes[cc]);
                }
                for(int cont = 0; cont < 3; cont++){
                    if(cont == 0){
                        colunas[cont] = new JLabel(usuarioLogado.getProdutosAdquiridos().get(c).getNome());
                        colunas[cont].setBounds(x, 50, 100, 30);
                    }else{
                        this.valor = usuarioLogado.getProdutosAdquiridos().get(c).getPreco() * usuarioLogado.getProdutosAdquiridos().get(c).getQuantidadeAdquirida();
                        colunas[cont] = new JLabel(cont == 1 ? "R$" + df.format(valor) : usuarioLogado.getProdutosAdquiridos().get(c).getQuantidadeAdquirida() + "");
                    }
                    colunas[cont].setFont(new Font("Arial", Font.BOLD, 30));
                    colunas[cont].setBounds(x, 50, 100, 30);
                    tela.add(colunas[cont]);
                    x += 170;
                }
                
                x = 40;
                y += 200;
                telaItem.add(tela);
                this.getPainel().add(scroll);
            }
            
        }
        
    }

    @Override
    public void addTextos() {
        JLabel mensagemVazia = new JLabel("Nenhum produto adicionado!", JLabel.CENTER);
        mensagemVazia.setFont(new Font("Arial", Font.BOLD, 40));
        mensagemVazia.setBounds(0, 120, 800, 60);
        this.getPainel().add(mensagemVazia);
    }

    @Override
    public void addBotoes() {
        String[] nomes = {"VOLTAR", "FINALIZAR COMPRA"};
        JButton[] btns = new JButton[2];
        int x = 40, w = 200;
        
        for(int c = 0; c < 2; c++){
            btns[c] = new JButton(nomes[c]);
            btns[c].setBackground(c == 0 ? new Color(223, 41, 53) : new Color(23, 184, 144));
            btns[c].setForeground(Color.white);
            btns[c].setCursor(new Cursor(Cursor.HAND_CURSOR));
            btns[c].addActionListener((c == 0 ) ? new OuvinteBtnSair(this) : new OuvinteAddProduto(this));
            btns[c].setFont(new Font("Arial", Font.BOLD, 40));
            btns[c].setBounds(x, 550, w, 40);
            x += 400;
            w += 100;
            this.getPainel().add(btns[c]);
        }
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
    
}