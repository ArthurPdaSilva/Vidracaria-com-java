package classes;

import configuracoes.Persistencia;
import java.util.ArrayList;

public class ListaDeServicos {
    private static ListaDeServicos instancia;
    private ArrayList<Servico> servicos;
    
    public ListaDeServicos(){
        this.servicos = new ArrayList<>();
    }
    
    public static ListaDeServicos obterInstancia(){
        if(instancia == null){
            Persistencia persistencia = new Persistencia();
            instancia = persistencia.recuperarServico("servicos");
        }    
        return instancia;
    }
    
    public boolean adicionarServico(Servico item){
        if(!servicos.isEmpty()){
            for(int c = 0; c < servicos.size() ; c++){
                if(servicos.get(c).getNome().equals(item.getNome()))
                    return false;
            }
        }
        servicos.add(item);
        return true;
    }
    
    public Servico recuperarServicoPeloNome(String nome){
        for(int c = 0; c < servicos.size(); c++){
            if(servicos.get(c).getNome().equals(nome))
                return servicos.get(c);
        }
        return null;
    }

    public ArrayList<Servico> getServicos() {
        return servicos;
    }

    public void setServicos(ArrayList<Servico> servicos) {
        this.servicos = servicos;
    }

    public static ListaDeServicos getInstancia() {
        return instancia;
    }

    public static void setInstancia(ListaDeServicos instancia) {
        ListaDeServicos.instancia = instancia;
    }
    
    public boolean substituirServico(Servico servico){
        for(int c = 0; c < servicos.size(); c++){
            if(servicos.get(c).getId() == servico.getId()){
                this.servicos.set(c, servico);
                return true;
            }
        }
        return false;
    }
    
    public boolean apagarServico(Servico servico){
        for(int c = 0; c < servicos.size(); c++){
            if(servicos.get(c).getId() == servico.getId()){
                this.servicos.remove(servicos.get(c));
                return true;
            }
        }
        return false;
    }
}
