package ouvintes;

import classes.Produto;
import classes.Servico;
import janelas.JanelaDeAdministracao;
import janelas.JanelaEditarECadastrarProduto;
import janelas.JanelaEditarECadastrarServico;
import janelas.JanelaListarProEServ;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OuvinteBtnAdm implements ActionListener{
    private String nome;
    private Produto produto;
    private Servico servico;
    private JanelaDeAdministracao tela;
    
    public OuvinteBtnAdm(String nome, Produto produto, Servico servico, JanelaDeAdministracao janela){
        this.nome = nome;
        this.produto = produto;
        this.servico = servico;
        this.tela = janela;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        
        switch (nome) {
            case "Editar Item" -> new JanelaListarProEServ(nome, tela);
            case "Cadastrar Item" -> new JanelaEditarECadastrarProduto(nome, produto, tela);
            case "Editar Serviço" -> new JanelaListarProEServ(nome, tela);
            default -> new JanelaEditarECadastrarServico("Cadastrar Serviço", servico, tela);
        }
        
    }
    
}