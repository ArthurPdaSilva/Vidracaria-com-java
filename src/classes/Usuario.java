package classes;

import java.util.ArrayList;
import java.util.Date;

public class Usuario {
    private long id;
    private String nome;
    private String email;
    private String senha;
    private String genero;
    private Long imagem;
    private Date data;
    private ArrayList<Produto> produtosAdquiridos;
    private ServicoAdquirido servicoSolicitado;
    
    public Usuario(){
        this.id = System.currentTimeMillis();
        this.produtosAdquiridos = new ArrayList<>();
        this.servicoSolicitado = null;
    }

    public Usuario(String nome, String email, String senha, String genero, Date data, Long img) {
        this.id = System.currentTimeMillis();
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.genero = genero;
        this.data = data;
        this.imagem = img;
        this.produtosAdquiridos = new ArrayList<>();
        this.servicoSolicitado = null;
    }
    
    
    //Adições no usuário logado
    public boolean adicionarProduto(Produto p, int q){
        //Verifico se produto já foi adquirido
        if(!produtosAdquiridos.isEmpty()){
            for(int c = 0; c < produtosAdquiridos.size(); c++){
                if(produtosAdquiridos.get(c).getId() == p.getId()){
                   return false; 
                }
            }
        }
        
        p.setQuantidadeAdquirida(q);
        this.produtosAdquiridos.add(p);
        return true;
    }
    
    public void apagarProdutoAdquirido(Produto produto) {
        produtosAdquiridos.remove(produto);
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public long getId() {
        return id;
    }

    public Long getImagem() {
        return imagem;
    }

    public void setImagem(Long imagem) {
        this.imagem = imagem;
    }

    public ArrayList<Produto> getProdutosAdquiridos() {
        return produtosAdquiridos;
    }

    public void setProdutosAdquiridos(ArrayList<Produto> produtosAdquiridos) {
        this.produtosAdquiridos = produtosAdquiridos;
    }

    public ServicoAdquirido getServicoSolicitado() {
        return servicoSolicitado;
    }

    public void setServicoSolicitado(ServicoAdquirido servicoSolicitado) {
        this.servicoSolicitado = servicoSolicitado;
    }

}