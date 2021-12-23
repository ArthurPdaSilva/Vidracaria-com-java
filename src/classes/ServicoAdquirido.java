package classes;

import java.text.DecimalFormat;

public class ServicoAdquirido{
    private String endereco;
    private String rua;
    private String complemento;
    private Servico servico;
    
    public ServicoAdquirido(){}

    public ServicoAdquirido(String endereco, String rua, String complemento, Servico servico) {
        this.endereco = endereco;
        this.rua = rua;
        this.complemento = complemento;
        this.servico = servico;
    }
    
    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }
    
    
    @Override
    public String toString(){
        DecimalFormat df = new DecimalFormat("##.00");
        return "Nome do serviço: " + getServico().getNome() + 
                "\npreço: R$" + df.format(getServico().getPreco()) + 
                "\ndescrição: " + getServico().getDescricao() +
                "\nendereço:" + getEndereco() +
                "\nrua:" + getRua() +
                "\nComplemento: " + getComplemento();
    }
}
