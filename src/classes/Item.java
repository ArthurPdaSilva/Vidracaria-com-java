package classes;

public class Item {
    private long id;
    private String nome;
    private String descricao;
    private float preco;
    private long foto;

    public Item() {
        this.id = System.currentTimeMillis();
    }

    public Item(String nome, String descricao, float preco, long foto) {
        this.id = System.currentTimeMillis();
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.foto = foto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public Long getFoto() {
        return foto;
    }

    public void setFoto(Long foto) {
        this.foto = foto;
    }
    
}
