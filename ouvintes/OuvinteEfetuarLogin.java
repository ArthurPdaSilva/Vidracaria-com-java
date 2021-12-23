package ouvintes;

import classes.ListaDeUsuarios;
import classes.Usuario;
import janelas.JanelaInicio;
import janelas.JanelaLogin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class OuvinteEfetuarLogin implements ActionListener{
    private JanelaLogin login;
    
    public OuvinteEfetuarLogin(JanelaLogin login){
        this.login = login;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(logando(login.getEmail().getText().trim(), login.getSenha().getText().trim()) != null){
            new JanelaInicio();
            login.dispose();
        }else
            JOptionPane.showMessageDialog(login, "Senha ou email inválido!");
    }
    
    public Usuario logando(String email, String senha){
        ListaDeUsuarios listaDeUsuarios = ListaDeUsuarios.obterInstancia();
        
        for(int i = 0; i < listaDeUsuarios.getUsuarios().size(); i++){
            if(listaDeUsuarios.getUsuarios().get(i).getEmail().equals(email) && listaDeUsuarios.getUsuarios().get(i).getSenha().equals(senha)){
                listaDeUsuarios.setUsuarioLogado(listaDeUsuarios.getUsuarios().get(i));
                return listaDeUsuarios.getUsuarios().get(i);
            }
        }
        
        return null;
    }
}