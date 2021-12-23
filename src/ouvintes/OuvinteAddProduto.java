package ouvintes;

import classes.ListaDeProdutos;
import classes.Produto;
import configuracoes.Comprovante;
import configuracoes.Mensageiro;
import configuracoes.Persistencia;
import janelas.JanelaCarrinho;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class OuvinteAddProduto implements ActionListener{
    private JanelaCarrinho janela;

    public OuvinteAddProduto(JanelaCarrinho aThis) {
        this.janela = aThis;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ListaDeProdutos produtoUser = ListaDeProdutos.obterInstancia();
        Mensageiro msg = new Mensageiro();
        Produto produtoNaLista;

        for(Produto produtos : this.janela.getUsuarioLogado().getProdutosAdquiridos()){
            produtoNaLista = produtoUser.recuperarProdutoPeloNome(produtos.getNome());
            produtoNaLista.setQuantidade(produtoNaLista.getQuantidade() - produtos.getQuantidadeAdquirida());
        }
        
        Persistencia p = new Persistencia();
        p.salvarListaDeProdutos(produtoUser, "produtos");
        
        Comprovante.enviarComprovante("produto");
        msg.enviarMensagemAoCliente(this.janela.getUsuarioLogado().getEmail(), "Comprovante de compra de produto");
        this.janela.getUsuarioLogado().setProdutosAdquiridos(new ArrayList());
        JOptionPane.showMessageDialog(null, "Verifique seu email para obter o comprovante");
        this.janela.dispose();
    }

}
