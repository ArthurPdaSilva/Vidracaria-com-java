package ouvintes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import classes.ListaDeProdutos;
import classes.ListaDeServicos;
import configuracoes.GerarPlanilha;


public class OuvinteCriarPlanilha implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		ListaDeProdutos listaP = ListaDeProdutos.obterInstancia();
		ListaDeServicos listaS = ListaDeServicos.obterInstancia();
		if(listaP.getProdutos().isEmpty() || listaS.getServicos().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Tenha pelo menos um serviço e um produto em estoque!!");
		}else {
			GerarPlanilha.criarPlanilha();
			JOptionPane.showMessageDialog(null, "Planilha criada com sucesso!");
		}
		
	}

}
