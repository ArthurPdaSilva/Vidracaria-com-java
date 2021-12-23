package classes;

import configuracoes.Persistencia;
import java.util.ArrayList;

public class ListaDeProdutos {
    private static ListaDeProdutos instancia;
    private ArrayList<Produto> produtos;
    
    public ListaDeProdutos(){
        this.produtos = new ArrayList<>();
    }
    
    public static ListaDeProdutos obterInstancia(){
        if(instancia == null){
            Persistencia persistencia = new Persistencia();
            instancia = persistencia.recuperarProduto("produtos");
        }    
        return instancia;
    }
    
    public boolean adicionarProduto(Produto item){
        if(!produtos.isEmpty()){
            for(int c = 0; c < produtos.size() ; c++){
                if(produtos.get(c).getNome().equals(item.getNome()))
                    return false;
            }
        }
        produtos.add(item);
        return true;
    }
    
    public Produto recuperarProdutoPeloNome(String nome){
        for(int c = 0; c < produtos.size(); c++){
            if(produtos.get(c).getNome().equals(nome))
                return produtos.get(c);
        }
        return null;
    }
    
    public boolean substituirProduto(Produto produto){
        for(int c = 0; c < produtos.size(); c++){
            if(produtos.get(c).getId() == produto.getId()){
                this.produtos.set(c, produto);
                return true;
            }
        }
        return false;
    }
    
    public boolean apagarProduto(Produto produto){
        for(int c = 0; c < produtos.size(); c++){
            if(produtos.get(c).getId() == produto.getId()){
                this.produtos.remove(produtos.get(c));
                return true;
            }
        }
        return false;
    }
    
    
    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }

    public static ListaDeProdutos getInstancia() {
        return instancia;
    }

    public static void setInstancia(ListaDeProdutos instancia) {
        ListaDeProdutos.instancia = instancia;
    }
    
}