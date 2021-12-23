package ouvintes;

import janelas.JanelaCarrinho;
import janelas.JanelaCompraProduto;
import janelas.JanelaEsqueceuASenha;
import janelas.JanelaInicio;
import janelas.JanelaDeCadastro;
import janelas.JanelaEstrutura;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OuvinteBtnSair implements ActionListener{
    private JanelaEstrutura janela;
    
    public OuvinteBtnSair(JanelaEstrutura janela){
        this.janela = janela;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!(janela instanceof JanelaEsqueceuASenha) && !(janela instanceof JanelaDeCadastro) && !(janela instanceof JanelaCompraProduto) && !(janela instanceof JanelaCarrinho))
            new JanelaInicio();
        janela.dispose();
    }

}
