package classes;

import configuracoes.Persistencia;
import java.util.ArrayList;

public class ListaDeUsuarios {
    private static ListaDeUsuarios instancia;
    private ArrayList<Usuario> usuarios;
    private Usuario usuarioLogado;
    
    public ListaDeUsuarios(){
        this.usuarios = new ArrayList<>();
    }
    
    public static ListaDeUsuarios obterInstancia(){
        if(instancia == null){
            Persistencia persistencia = new Persistencia();
            instancia = persistencia.recuperarLista("usuarios");
        }    
        return instancia;
    }
    
    public boolean adicionarUsuario(Usuario object){
        if(!usuarios.isEmpty()){
            for(int c = 0; c < usuarios.size() ; c++){
                if(usuarios.get(c).getEmail().equals(object.getEmail()))
                    return false;
            }    
        }
        usuarios.add(object);
        return true;
    }
    
    public Usuario recuperarUsuarioPeloEmail(String email){
        for(int c = 0; c < usuarios.size(); c++){
            if(usuarios.get(c).getEmail().equals(email))
                return usuarios.get(c);
        }
        return null;
    }
    
    public Usuario recuperarUsuarioPeloId(long id){
        for(int c = 0; c < usuarios.size(); c++){
            if(usuarios.get(c).getId() == id)
                return usuarios.get(c);
        }       
        return null;
    }
    
    public static ListaDeUsuarios getInstancia() {
        return instancia;
    }

    public static void setInstancia(ListaDeUsuarios instancia) {
        ListaDeUsuarios.instancia = instancia;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }
    
}
