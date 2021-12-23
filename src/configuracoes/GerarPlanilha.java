package configuracoes;

import java.io.File;
import java.text.DecimalFormat;

import javax.swing.JOptionPane;

import classes.ListaDeProdutos;
import classes.ListaDeServicos;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class GerarPlanilha {
	public static void criarPlanilha() {
		DecimalFormat df = new DecimalFormat("##.00");
		ListaDeProdutos listaP = ListaDeProdutos.obterInstancia();
		ListaDeServicos listaS = ListaDeServicos.obterInstancia();
		try {
			WritableWorkbook planilha = Workbook.createWorkbook(new File("dados.xls"));
			// Adicionando o nome da aba
			WritableSheet aba = planilha.createSheet("produtos", 0);
			
			// Cabeçalho
			String cabecalho[] = {"Nome", "Descrição", "Quantidade", "Preço"};
			
			// Cor de fundo das celular
			Colour bckcolor = Colour.DARK_GREEN;
			WritableCellFormat cellFormat = new WritableCellFormat();
			cellFormat.setBackground(bckcolor);
			
			// Cor e tipo de fonte
			WritableFont fonte = new WritableFont(WritableFont.ARIAL);
			fonte.setColour(Colour.GOLD);
			cellFormat.setFont(fonte);
			
			// Write the Header to the excel file
			for (int i = 0; i < cabecalho.length; i++) {
				Label label = new Label(i, 0, cabecalho[i]);
				aba.addCell(label);
				WritableCell cell = aba.getWritableCell(i, 0);
				cell.setCellFormat(cellFormat);
			}
			
			for(int linha = 0; linha < listaP.getProdutos().size(); linha++) {
				Label label = new Label(0, linha + 1, listaP.getProdutos().get(linha).getNome());
				aba.addCell(label);
				
				label = new Label(1, linha + 1, listaP.getProdutos().get(linha).getDescricao());
				aba.addCell(label);
				
				label = new Label(2, linha + 1, listaP.getProdutos().get(linha).getQuantidade() + "");
				aba.addCell(label);
				
				label = new Label(3, linha + 1, "R$" + df.format(listaP.getProdutos().get(linha).getPreco()));
				aba.addCell(label);
			}
			
			WritableSheet aba2 = planilha.createSheet("serviços", 1);
			// Cabeçalho
			String[] cabecalho2= {"Nome", "Descrição", "Preço"};
			for (int i = 0; i < cabecalho2.length; i++) {
				Label label = new Label(i, 0, cabecalho2[i]);
				aba2.addCell(label);
				WritableCell cell = aba2.getWritableCell(i, 0);
				cell.setCellFormat(cellFormat);
			}
			
			for(int linha = 0; linha < listaS.getServicos().size(); linha++) {
				Label label = new Label(0, linha + 1, listaS.getServicos().get(linha).getNome());
				aba2.addCell(label);
				
				label = new Label(1, linha + 1, listaS.getServicos().get(linha).getDescricao());
				aba2.addCell(label);
				
				label = new Label(2,linha + 1, "R$" + df.format(listaS.getServicos().get(linha).getPreco()));
				aba2.addCell(label);
			}
			
			planilha.write();
			// Fecha o arquivo
			planilha.close();
			JOptionPane.showMessageDialog(null, "Planilha criada com sucesso!");
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
}
