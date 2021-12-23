package ouvintes;

import janelas.JanelaCompraProduto;
import java.text.DecimalFormat;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class OuvinteMudanca implements ChangeListener{
    private JanelaCompraProduto janela;

    public OuvinteMudanca(JanelaCompraProduto janela) {
        this.janela = janela;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        DecimalFormat df = new DecimalFormat("##.00");
        int valor = (Integer) janela.getQuantidade().getValue();
        janela.setValor(janela.getProduto().getPreco() * valor);
        janela.getCampos()[3].setText("Preço: R$" + df.format(janela.getValor()));
    }

}
