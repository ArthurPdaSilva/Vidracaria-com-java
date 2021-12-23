package ouvintes;

import configuracoes.Persistencia;
import classes.ListaDeProdutos;
import classes.ListaDeServicos;
import classes.Produto;
import classes.Servico;
import janelas.JanelaEditarECadastrarProduto;
import janelas.JanelaEditarECadastrarServico;
import janelas.JanelaProdutoEServico;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class OuvinteCadastrarProEServ implements ActionListener{
    private JanelaProdutoEServico tela;
            
    public OuvinteCadastrarProEServ(JanelaEditarECadastrarProduto tela){
        this.tela = tela;
    }
    
    public OuvinteCadastrarProEServ(JanelaEditarECadastrarServico tela){
        this.tela = tela;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Persistencia pe = new Persistencia();
        ListaDeProdutos listaProds = ListaDeProdutos.obterInstancia();
        ListaDeServicos listaServs = ListaDeServicos.obterInstancia();
        
        if(!tela.getNome().getText().equals("Nome") && !tela.getDescricao().getText().equals("Descrição") && !tela.getPreco().getText().equals("Preço") && tela.getImagemLabel().getIcon() != null){
            if(tela instanceof JanelaEditarECadastrarServico){
                Servico newServico = new Servico(tela.getNome().getText(),
                tela.getDescricao().getText(),
                Float.parseFloat(tela.getPreco().getText()),
                tela.getImagem());
                
                if(listaServs.adicionarServico(newServico)){
                    pe.salvarListaDeServicos(listaServs, "servicos");
                    JOptionPane.showMessageDialog(tela, "Serviço Cadastrado!");
                    
                }else
                    JOptionPane.showMessageDialog(tela, "Esse servico já existe!");
                
            }else{
                Produto newProduto = new Produto(tela.getNome().getText(),
                tela.getDescricao().getText(), 
                Integer.parseInt((String) tela.getQuantidade().getValue().toString()),
                Float.parseFloat(tela.getPreco().getText()),
                tela.getImagem());
                
                if(listaProds.adicionarProduto(newProduto)){
                    pe.salvarListaDeProdutos(listaProds, "produtos");
                    JOptionPane.showMessageDialog(tela, "Produto Cadastrado!");
                }else
                    JOptionPane.showMessageDialog(tela, "Esse Produto Já Existe!");    
            }
        }else{
            JOptionPane.showMessageDialog(tela, "Preêncha todos os campos");
        }
        tela.dispose();
    }
    
}
