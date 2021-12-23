package ouvintes;

import configuracoes.Mensageiro;
import classes.ServicoAdquirido;
import configuracoes.Comprovante;
import janelas.JanelaContrataServico;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class OuvinteAddServico implements ActionListener{
    private JanelaContrataServico janela;
    private ServicoAdquirido servicoAdd;

    public OuvinteAddServico(JanelaContrataServico serv, ServicoAdquirido servico) {
        this.janela = serv;
        this.servicoAdd = servico;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "Serviço adquirido, verifique seu email!");
        this.janela.getUsuarioLogado().setServicoSolicitado(servicoAdd);
        
        //Confirmação por email
        Mensageiro msg = new Mensageiro();
        Comprovante.enviarComprovante("servico");
        msg.enviarMensagemAoCliente(this.janela.getUsuarioLogado().getEmail(), "Comprovante de contratação de serviço");
        JOptionPane.showMessageDialog(null, "Verifique seu email para obter o comprovante");
        
        this.janela.getUsuarioLogado().setServicoSolicitado(null);
        this.janela.dispose();
    }

}
