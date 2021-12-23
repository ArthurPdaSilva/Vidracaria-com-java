package janelas;

import static classes.Imagem.carregarImg;
import classes.Produto;
import java.awt.Color;
import java.io.IOException;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import ouvintes.OuvinteCadastrarProEServ;
import ouvintes.OuvinteEditarProEServ;
import ouvintes.OuvinteCampoNumero;

public class JanelaEditarECadastrarProduto extends JanelaProdutoEServico{
    private Produto produto;
    private JanelaDeAdministracao tela;
    
    public JanelaEditarECadastrarProduto(String tipo, Produto produto, JanelaDeAdministracao tela) {
        super(tipo, "Produtos", tela);
        this.tela = tela;
        this.produto = produto;
        addQuantidade();
        addPropriedades();
        setVisible(true);
    }

    private void addQuantidade() {
        SpinnerModel modelo = new SpinnerNumberModel(1, 1, 9999, 1);
        setQuantidade(new JSpinner(modelo));
        getQuantidade().setBounds(220, 200, 50, 30);
        getQuantidade().setToolTipText("Quantidade do produto");
        getPainel().add(getQuantidade());
    }

    private void addPropriedades() {
        getPreco().addKeyListener(new OuvinteCampoNumero());
        if(this.getTipo().equals("Cadastrar Item")){
            getNome().setText("Nome");
            getNome().setForeground(Color.lightGray);
            
            getDescricao().setText("Descrição");
            getDescricao().setForeground(Color.lightGray);
            
            getPreco().setText("Preço");
            getPreco().setForeground(Color.lightGray);
            
            getSalvar().addActionListener(new OuvinteCadastrarProEServ(this));
        }else{
            getNome().setText(this.produto.getNome());
            getDescricao().setText(this.produto.getDescricao());
            getPreco().setText(String.valueOf(this.produto.getPreco()));
            setImagem(this.produto.getFoto());
            try {getImagemLabel().setIcon(carregarImg(this.getImagem(), "produto", 150, 150));} catch (IOException ex) {this.getImagemLabel().setText("Enviar Imagem");}
            getQuantidade().setValue(this.produto.getQuantidade());
            getSalvar().addActionListener(new OuvinteEditarProEServ(this.produto, this, tela));
        }           
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

}