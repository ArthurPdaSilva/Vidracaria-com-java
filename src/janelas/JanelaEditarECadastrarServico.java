package janelas;

import static classes.Imagem.carregarImg;
import classes.Servico;
import java.awt.Color;
import java.io.IOException;
import ouvintes.OuvinteCadastrarProEServ;
import ouvintes.OuvinteEditarProEServ;
import ouvintes.OuvinteCampoNumero;

public class JanelaEditarECadastrarServico extends JanelaProdutoEServico{
    private Servico servico;
    private JanelaDeAdministracao tela;
    
    public JanelaEditarECadastrarServico(String tipo, Servico servico, JanelaDeAdministracao tela){
        super(tipo, "Serviços", tela);
        this.tela = tela;
        this.servico = servico;
        addPropriedades();
        setVisible(true);
    }

    private void addPropriedades() {
        getPreco().addKeyListener(new OuvinteCampoNumero());
        if(this.getTipo().equals("Cadastrar Serviço")){
            getNome().setText("Nome");
            getNome().setForeground(Color.lightGray);
            
            getDescricao().setText("Descrição");
            getDescricao().setForeground(Color.lightGray);
            
            getPreco().setText("Preço");
            getPreco().setForeground(Color.lightGray);
            
            getSalvar().addActionListener(new OuvinteCadastrarProEServ(this));
        }else{
            getNome().setText(this.servico.getNome());
            getDescricao().setText(this.servico.getDescricao());
            getPreco().setText(String.valueOf(this.servico.getPreco()));
            setImagem(this.servico.getFoto());
            try {getImagemLabel().setIcon(carregarImg(this.getImagem(), "servico", 150, 150));} catch (IOException ex) {this.getImagemLabel().setText("Enviar Imagem");}
            getSalvar().addActionListener(new OuvinteEditarProEServ(this.servico, this, tela));
        }
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

}