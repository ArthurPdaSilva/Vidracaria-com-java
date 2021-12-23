package configuracoes;

import classes.ListaDeUsuarios;
import classes.Usuario;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;

public class Comprovante {
    public static void enviarComprovante(String tipo){
        DecimalFormat df = new DecimalFormat("##.00");
        
        try {
            Usuario userLogado = ListaDeUsuarios.obterInstancia().getUsuarioLogado();
            String file = "comprovantes/comprovante-de-" + userLogado.getNome() + ".pdf";
            PdfWriter pdfWrite;
            pdfWrite = new PdfWriter(file);
            PdfDocument pdfDocument = new PdfDocument(pdfWrite);
            Document doc = new Document(pdfDocument);
            
            Style normal = new Style();
            PdfFont font = PdfFontFactory.createFont(StandardFonts.COURIER);
            normal.setFont(font).setFontSize(14);
            
            //Criando parágrafos
            Paragraph pgTitulo;
            if(tipo.equals("produto")){
                pgTitulo = new Paragraph("Comprovante de Produto").setBold().addStyle(normal);
                pgTitulo.setTextAlignment(TextAlignment.CENTER);
                doc.add(pgTitulo);
                
                Table table = new Table(4);
                table.addCell(new Cell().add(new Paragraph("Nome do produto").setBold().addStyle(normal)));
                table.addCell(new Cell().add(new Paragraph("Preço em R$").setBold().addStyle(normal)));
                table.addCell(new Cell().add(new Paragraph("Decrição").setBold().addStyle(normal)));
                table.addCell(new Cell().add(new Paragraph("Quantidade adquirida").setBold().addStyle(normal)));
                
                for(int c = 0; c < userLogado.getProdutosAdquiridos().size(); c++){
                    table.addCell(new Paragraph(userLogado.getProdutosAdquiridos().get(c).getNome()).addStyle(normal));
                    table.addCell(new Paragraph(df.format(userLogado.getProdutosAdquiridos().get(c).getPreco())).addStyle(normal));
                    table.addCell(new Paragraph(userLogado.getProdutosAdquiridos().get(c).getDescricao()).addStyle(normal));
                    table.addCell(new Paragraph(userLogado.getProdutosAdquiridos().get(c).getQuantidadeAdquirida() + "").addStyle(normal));
                    doc.add(table);
                }
            }else{
                pgTitulo = new Paragraph("Comprovante de Serviço").setBold().addStyle(normal);
                pgTitulo.setTextAlignment(TextAlignment.CENTER);
                Paragraph pg = new Paragraph(userLogado.getServicoSolicitado().toString()).addStyle(normal);
                doc.add(pgTitulo);
                doc.add(pg);
            }
            
            doc.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
}
