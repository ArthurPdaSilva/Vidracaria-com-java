package ouvintes;

import classes.ListaDeServicos;
import classes.Servico;
import classes.ServicoAdquirido;
import janelas.JanelaContrataServico;
import janelas.JanelaDeServico;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class OuvinteServicoSelecionado implements ActionListener{
    private JanelaDeServico janela;

    public OuvinteServicoSelecionado(JanelaDeServico serv) {
        this.janela = serv;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String endereco = janela.getCampos()[0].getText();
        String rua = janela.getCampos()[1].getText();
        String complemento = janela.getCampos()[2].getText();
        
        if(endereco.isEmpty() || rua.isEmpty() || complemento.isEmpty())
            JOptionPane.showMessageDialog(janela, "Prencha os campos obrigatórios!");
        else{
            String x = String.valueOf(janela.getCbServicos().getSelectedItem());
            ListaDeServicos servicos = ListaDeServicos.obterInstancia();
            Servico serv = servicos.recuperarServicoPeloNome(x);
            ServicoAdquirido sv = new ServicoAdquirido(endereco, rua, complemento, serv);
            new JanelaContrataServico(sv);
        }
    }

}
