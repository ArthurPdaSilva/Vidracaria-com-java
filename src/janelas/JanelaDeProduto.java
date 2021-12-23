package janelas;

import DynamicLayout.DynamicLayout;
import static classes.Imagem.carregarImg;
import classes.ListaDeProdutos;
import interfaces.InterfaceDeControle;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import ouvintes.OuvinteBtnSair;
import ouvintes.OuvinteProduto;

public final class JanelaDeProduto extends JanelaEstrutura implements InterfaceDeControle{
    
    public JanelaDeProduto() {
        super(1000, 700);
        super.addPainelCentral(50, 50, 900, 600);
        super.addTitulo(0, 40, 900, 60, "Nossos Produtos");
        super.addSeparador(200, 100, 500, 20, "HORIZONTAL");
        this.addItens();
        this.addBotoes();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    @Override
    public void addItens() {
        ListaDeProdutos produtos = ListaDeProdutos.obterInstancia();
        JPanel telaItem = new JPanel();
        telaItem.setBounds(0, 120, 900, 500);
        telaItem.setPreferredSize(new Dimension(0, 500));
        telaItem.setLayout(new DynamicLayout(900, 500));
        telaItem.setBackground(Color.white);
        telaItem.setOpaque(true);
        JScrollPane scroll = new JScrollPane(telaItem);
        scroll.setBounds(0, 140, 900, 500);
        
        if(produtos.getProdutos().isEmpty())
            this.addTextos();
        else{
            int x = 40, y = 10;
            String[] nomes = {"Nome: ", "Preço: R$"};
            DecimalFormat df = new DecimalFormat("##.00");

            for(int c = 0; c < produtos.getProdutos().size(); c++){
                JPanel tela = new JPanel();
                tela.setLayout(new DynamicLayout(190, 200));
                tela.setBackground(Color.white);
                
                JLabel icone = new JLabel();
                JLabel nome = new JLabel(nomes[0] + produtos.getProdutos().get(c).getNome());
                JLabel preco = new JLabel(nomes[1] + df.format(produtos.getProdutos().get(c).getPreco()));
                
                if(c != 0 && c % 4 == 0){
                    y += 230;
                    tela.setBounds(40, y, 190, 200);
                    x = 40;
                }else
                    tela.setBounds(x, y, 190, 200);
                    
                
                icone.setBounds(0, 0, 150, 150);
                nome.setBounds(0, 155, 190, 25);
                preco.setBounds(0, 180, 190, 25);
                
                icone.addMouseListener(new OuvinteProduto(produtos.getProdutos().get(c)));
                icone.setCursor(new Cursor(Cursor.HAND_CURSOR));
                nome.setFont(new Font("Arial", Font.BOLD, 25));
                preco.setFont(new Font("Arial", Font.BOLD, 25));
                
                try {
                    icone.setIcon(carregarImg(produtos.getProdutos().get(c).getFoto(), "produto", 150, 150));
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                
                x += 210;
                
                tela.add(icone);
                tela.add(nome);
                tela.add(preco);
                telaItem.add(tela);
            }
        }
        
        this.getPainel().add(scroll);
    }

    @Override
    public void addTextos() {
        JLabel mensagemVazia = new JLabel("Nenhum produto cadastrado!", JLabel.CENTER);
        mensagemVazia.setFont(new Font("Arial", Font.BOLD, 40));
        mensagemVazia.setBounds(0, 120, 900, 60);
        this.getPainel().add(mensagemVazia);
    }

    @Override
    public void addBotoes() {
        String[] bts = {"VOLTAR", "MEU CARRINHO"};
        int y = 10;
        for(int i = 0; i < bts.length; i++){
            JButton btn = new JButton(bts[i]);
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btn.setBorder(null);
            btn.setFont(new Font("Arial", Font.BOLD, 15));
            btn.setBounds(y, 10, 200, 40);
            btn.setBackground(bts[i].equals("VOLTAR") ? new Color(223, 41, 53) : new Color(23, 184, 144));
            btn.setForeground(Color.white);
            btn.addActionListener(bts[i].equals("VOLTAR") ? new OuvinteBtnSair(this) : new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new JanelaCarrinho();
                }
            });
            this.getPainel().add(btn);
            y += 690;
        }
        
        
    }
}
