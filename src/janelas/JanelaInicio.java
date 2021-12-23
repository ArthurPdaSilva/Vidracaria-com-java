package janelas;

import DynamicLayout.DynamicLayout;
import static classes.IconeDoPerfil.addIcone;
import static classes.Imagem.carregarImg;
import classes.JPanelArredondado;
import classes.ListaDeProdutos;
import classes.ListaDeServicos;
import classes.ListaDeUsuarios;
import classes.Usuario;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import ouvintes.OuvinteProduto;
import ouvintes.OuvinteVerMais;

public class JanelaInicio extends JanelaEstrutura{
    private Usuario usuarioLogado;
    private JPanel painelProdutos;
    private JPanel painelServicos;
    private JLabel verMais;
    
    public JanelaInicio(){
        super(980,600);
        this.usuarioLogado = ListaDeUsuarios.obterInstancia().getUsuarioLogado();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        try {
            addIcone(null, this, 980);
        } catch (FileNotFoundException ex){
            try {
                this.usuarioLogado.setImagem(Long.parseLong("1111111111111"));
                addIcone(null, this, 980);
            } catch (IOException ex1) {
                JOptionPane.showMessageDialog(null, ex1.getMessage());
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
        addTitulo();
        addPaineis();
        Produtos();
        Servicos();
        setVisible(true);
    }

    private void addTitulo() {
        JLabel titulo = new JLabel("Seja bem vindo, Sr(a). " + this.usuarioLogado.getNome() , JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 30));
        titulo.setForeground(Color.white);
        titulo.setBounds(0, 0, 980, 60);
        add(titulo);
    }

    private void addLabels(JPanel painel, String nome) {
        JLabel produtos = new JLabel("Confira Nossos " + nome + "!");
        produtos.setFont(new Font("Arial", Font.BOLD, 30));
        produtos.setForeground(Color.white);
        produtos.setBounds(0, 0, 750, 30);
        painel.add(produtos);
        
        verMais = new JLabel("Ver Tudo");
        verMais.setFont(new Font("Arial", Font.BOLD, 30));
        verMais.addMouseListener(new OuvinteVerMais(verMais, nome, this));
        verMais.setCursor(new Cursor(Cursor.HAND_CURSOR));
        verMais.setBounds(781, 0, 80, 30);
        verMais.setForeground(new Color(55, 114, 255));
        painel.add(verMais);
    }
    
    public void addSeparador(JPanel painel) {
        JSeparator separa = new JSeparator(SwingConstants.HORIZONTAL);
        separa.setBounds(0, 27, 850, 20);
        separa.setBackground(Color.white);
        separa.setBorder(null);
        
        painel.add(separa);
    }
    
    private void Produtos(){
        ListaDeProdutos lista = ListaDeProdutos.obterInstancia();
        JPanel faixa = new JPanel();
        faixa.setBounds(0, 40, 850, 180);
        faixa.setBackground(null);
        faixa.setLayout(null);
        int x = 0;
        if(!(lista.getProdutos().size() < 1)){
            for(int i = 0; i < lista.getProdutos().size(); i++){
                JPanel produto = new JPanelArredondado(15, Color.white);
                produto.setBackground(null);
                produto.setLayout(null);
                produto.setCursor(new Cursor(Cursor.HAND_CURSOR));
                produto.setBounds(x, 0, 150, 150);

                JLabel foto = new JLabel();
                foto.setBounds(0, 8, 149 ,100);
                
                try {
                    foto.setIcon(carregarImg(lista.getProdutos().get(i).getFoto(), "produto", 150, 150));
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Problema ao carregar a foto!");
                }
                
                foto.addMouseListener(new OuvinteProduto(lista.getProdutos().get(i)));
                JLabel nome = new JLabel("Nome: " + lista.getProdutos().get(i).getNome());
                nome.setBounds(0, 115, 150, 12);
                nome.setFont(new Font("Arial", Font.BOLD, 12));
                produto.add(nome);

                DecimalFormat df = new DecimalFormat("##.00");
                JLabel preco = new JLabel("Preço: R$" + String.valueOf(df.format(lista.getProdutos().get(i).getPreco())));
                preco.setBounds(0, 135, 150, 12);
                preco.setFont(new Font("Arial", Font.BOLD, 12));
                produto.add(preco);
                
                produto.add(foto);
                faixa.add(produto);
                painelProdutos.add(faixa);
                x+=200;
            
            }
        }else{
            JLabel aviso = new JLabel("Não há Produtos cadastrados", JLabel.CENTER);
            aviso.setBounds(0, 0, 900, 200);
            aviso.setForeground(Color.white);
            aviso.setFont(new Font("Arial", Font.BOLD, 60));
            painelProdutos.add(aviso);
        }
        
    }
    private void Servicos(){
        ListaDeServicos lista = ListaDeServicos.obterInstancia();
        JPanel faixa = new JPanel();
        faixa.setBounds(0, 40, 850, 180);
        faixa.setBackground(null);
        faixa.setLayout(null);
        int x = 0;
        if(!(lista.getServicos().size() < 1)){
            for(int i = 0; i < lista.getServicos().size(); i++){
                JPanel servico = new JPanelArredondado(15, Color.white);
                servico.setBackground(null);
                servico.setLayout(null);
                servico.setBounds(x, 0, 150, 150);
                servico.setCursor(new Cursor(Cursor.HAND_CURSOR));
                
                JLabel foto = new JLabel();
                foto.setBounds(0, 8, 149 ,100);
                try {
                    foto.setIcon(carregarImg(lista.getServicos().get(i).getFoto(), "servico", 150, 150));
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                
                JLabel nome = new JLabel("Nome: " + lista.getServicos().get(i).getNome());
                nome.setBounds(0, 115, 150, 12);
                nome.setBackground(null);
                nome.setFont(new Font("Arial", Font.BOLD, 12));
                servico.add(nome);

                DecimalFormat df = new DecimalFormat("##.00");
                JLabel preco = new JLabel("Preço: R$" + String.valueOf(df.format(lista.getServicos().get(i).getPreco())));
                preco.setBounds(0, 130, 150, 12);
                preco.setFont(new Font("Arial", Font.BOLD, 12));
                preco.setBackground(null);
                servico.add(preco);
                servico.add(foto);
                faixa.add(servico);
                painelServicos.add(faixa);
                x+=200;
            
            }
        }else{
            JLabel aviso = new JLabel("Não há Serviços cadastrados", JLabel.CENTER);
            aviso.setBounds(0, 0, 900, 200);
            aviso.setForeground(Color.white);
            aviso.setFont(new Font("Arial", Font.BOLD, 60));
            painelServicos.add(aviso);
        }
        
    }
    
    private void addPaineis() {
        painelServicos = new JPanel();
        painelServicos.setLayout(new DynamicLayout(850, 200));
        painelServicos.setBackground(null);
        painelServicos.setBounds(40, 350, 900, 200);
        addLabels(painelServicos, "Serviços");
        addSeparador(painelServicos);
        add(painelServicos);
        
        painelProdutos = new JPanel();
        painelProdutos.setLayout(new DynamicLayout(850, 200));
        painelProdutos.setBackground(null);
        painelProdutos.setBounds(40, 100, 900, 200);
        addLabels(painelProdutos, "Produtos");
        addSeparador(painelProdutos);
        add(painelProdutos);
    }
    
    public void addTextos() {
        JLabel mensagemVazia = new JLabel("Nenhum item cadastrado!", JLabel.CENTER);
        mensagemVazia.setFont(new Font("Arial", Font.BOLD, 40));
        mensagemVazia.setBounds(0, 120, 900, 60);
        this.painelProdutos.add(mensagemVazia);
        this.painelServicos.add(mensagemVazia);
    }
    
    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public JLabel getVerMais() {
        return verMais;
    }
    
}
