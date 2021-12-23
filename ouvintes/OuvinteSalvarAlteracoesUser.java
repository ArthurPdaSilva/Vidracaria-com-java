package ouvintes;

import configuracoes.Persistencia;
import classes.ListaDeUsuarios;
import janelas.JanelaInicio;
import janelas.JanelaMeuPerfil;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class OuvinteSalvarAlteracoesUser implements ActionListener{
    private JanelaMeuPerfil tela;
    
    public OuvinteSalvarAlteracoesUser(JanelaMeuPerfil tela){
        this.tela = tela;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        ListaDeUsuarios listaDeUsuarios = ListaDeUsuarios.obterInstancia();
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
        Persistencia pe = new Persistencia();
        
        if(!tela.getSenha().getText().matches(pattern)){
            JOptionPane.showMessageDialog(null, "Preencha corretamente o campo senha, Deve conter, nó mínimo: letra minúscula, maiúscula, um caractere especial, \nnão pode conter espaço em branco e ter no mínimo 8 caracteres!");
        }else{
            listaDeUsuarios.getUsuarioLogado().setNome(tela.getNome().getText().trim());
            listaDeUsuarios.getUsuarioLogado().setSenha(tela.getSenha().getText().trim());
            if(listaDeUsuarios.getUsuarioLogado().getImagem() != tela.getImagem())
                listaDeUsuarios.getUsuarioLogado().setImagem(tela.getNewImage());
            JOptionPane.showMessageDialog(null, "Dados salvos com sucesso");
        }
        pe.salvarListaDeUsuarios(listaDeUsuarios, "usuarios");
        
        tela.dispose();
        new JanelaInicio();

    }
    
}