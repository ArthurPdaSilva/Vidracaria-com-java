package ouvintes;

import classes.ListaDeProdutos;
import classes.ListaDeServicos;
import classes.Produto;
import classes.Servico;
import janelas.JanelaDeAdministracao;
import janelas.JanelaEditarECadastrarProduto;
import janelas.JanelaEditarECadastrarServico;
import janelas.JanelaListarProEServ;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class OuvinteSelecionarItem implements MouseListener{
    private Produto produto;
    private JanelaListarProEServ tela;
    private ListaDeProdutos lista = ListaDeProdutos.obterInstancia();
    private ArrayList <Produto> produtos = lista.getProdutos();
    private JanelaDeAdministracao janela;
    private Servico servico;
    private ListaDeServicos listaS = ListaDeServicos.obterInstancia();
    private ArrayList <Servico> servicos = listaS.getServicos();
    
    public OuvinteSelecionarItem(JanelaListarProEServ tela, String tipo, JanelaDeAdministracao janela){
        this.tela = tela;
        this.janela = janela;
        if(tipo.equals("Editar Item")){
            if(!(tela.getTabela().getSelectedRow() == -1))
                this.produto = produtos.get(tela.getTabela().getSelectedRow());
            else
                this.produto = produtos.get(0);
        }else{
            if(!(tela.getTabela().getSelectedRow() == -1))
                this.servico = servicos.get(tela.getTabela().getSelectedRow());
            else
                this.servico = servicos.get(0);
        }
        
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if(produto != null){
            produto = produtos.get(tela.getTabela().getSelectedRow());
            if(tela.getTabela().getSelectedRow() != -1)
                new JanelaEditarECadastrarProduto("Editar Produto", produto, janela);
        }else{
            servico = servicos.get(tela.getTabela().getSelectedRow());
            if(tela.getTabela().getSelectedRow() != -1)
                new JanelaEditarECadastrarServico("Editar Serviço", servico, janela);
        }
        tela.dispose();
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
}
