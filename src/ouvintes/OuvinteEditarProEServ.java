package ouvintes;

import configuracoes.Persistencia;
import classes.Item;
import classes.ListaDeProdutos;
import classes.ListaDeServicos;
import classes.Produto;
import classes.Servico;
import janelas.JanelaDeAdministracao;
import janelas.JanelaEditarECadastrarProduto;
import janelas.JanelaEditarECadastrarServico;
import janelas.JanelaInicio;
import janelas.JanelaProdutoEServico;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class OuvinteEditarProEServ implements ActionListener{
    private JanelaProdutoEServico tela;
    private long id;
    private JanelaDeAdministracao janelaParaAtualizar;
    
    public OuvinteEditarProEServ(Produto produto , JanelaEditarECadastrarProduto telaP, JanelaDeAdministracao tela){
        this.tela = telaP;
        this.id = produto.getId();
        this.janelaParaAtualizar = tela;
    }
    
    public OuvinteEditarProEServ(Servico servico, JanelaEditarECadastrarServico telaS, JanelaDeAdministracao tela){
        this.tela = telaS;
        this.id = servico.getId();
        this.janelaParaAtualizar = tela;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        Persistencia pe = new Persistencia();
        ListaDeProdutos lista = ListaDeProdutos.obterInstancia();
        ListaDeServicos listaServ = ListaDeServicos.obterInstancia();
        
        Item item = new Item();
        
        if(tela instanceof JanelaEditarECadastrarProduto){
            item = new Produto(tela.getNome().getText(), tela.getDescricao().getText(), (int) tela.getQuantidade().getValue(), Float.parseFloat(tela.getPreco().getText()), tela.getImagem());
            item.setId(id);
            lista.substituirProduto((Produto) item);
            pe.salvarListaDeProdutos(lista, "produtos");
        }else{
            item = new Servico(tela.getNome().getText(), tela.getDescricao().getText(), Float.parseFloat(tela.getPreco().getText()), tela.getImagem());
            item.setId(id);
            listaServ.substituirServico((Servico) item);
            pe.salvarListaDeServicos(listaServ, "servicos");
        }
        tela.dispose();
        janelaParaAtualizar.dispose();
        
        JanelaInicio telaa = new JanelaInicio();
        telaa.dispose();
        new JanelaDeAdministracao();
        
        JOptionPane.showMessageDialog(null, "Edição concluida");
        
    }
}
