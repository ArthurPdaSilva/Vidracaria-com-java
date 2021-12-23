package janelas;

import DynamicLayout.DynamicLayout;
import classes.ListaDeProdutos;
import classes.ListaDeServicos;
import classes.Produto;
import classes.Servico;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import ouvintes.OuvinteSelecionarItem;

public class JanelaListarProEServ extends JanelaEstrutura{
    private String tipo;
    private JTable tabela;
    private JanelaDeAdministracao tela;
    
    public JanelaListarProEServ(String tipo, JanelaDeAdministracao janela){
        super(300, 500);
        this.tipo = tipo;
        this.tela = janela;
        addTable();
        setResizable(false);
        setVisible(true);
    }

    private void addTable() {
        DefaultTableModel  modelo  = new DefaultTableModel();
        if(tipo.equals("Editar Item")){
            modelo.addColumn("Nome do Produto");
            modelo.addColumn("Descrição"); 
            ListaDeProdutos lista = ListaDeProdutos.obterInstancia();
            ArrayList <Produto> produtos = lista.getProdutos();
            if(produtos.size() > 0){
                for(Produto p : produtos){
                    Object[] linha = new Object[2];
                    linha[0] = p.getNome();
                    linha[1] = p.getDescricao();
                    modelo.addRow(linha);
                }
                
                tabela = new JTable(modelo);
                tabela.setCursor(new Cursor(Cursor.HAND_CURSOR));
                tabela.addMouseListener(new OuvinteSelecionarItem(this, "Editar Item", tela));
                tabela.setLayout(new DynamicLayout(300, 500));
                JScrollPane scroll = new JScrollPane(tabela);
            
                scroll.setBounds(0, 0, 300, 500);
                add(scroll);
            }else{
                getContentPane().setBackground(Color.white);
                JLabel msg = new JLabel("Não há produtos cadastrados" , JLabel.CENTER);
                msg.setFont(new Font("Arial", Font.BOLD, 20));
                msg.setBounds(0, 0, 300, 500);
                add(msg);
            }
            
        }else{
            modelo.addColumn("Nome do Serviço");
            modelo.addColumn("Descrição"); 
            ListaDeServicos lista = ListaDeServicos.obterInstancia();
            ArrayList <Servico> servicos = lista.getServicos();
            
            if(servicos.size() > 0){
                for(Servico s : servicos){
                    Object[] linha = new Object[2];
                    linha[0] = s.getNome();
                    linha[1] = s.getDescricao();
                    modelo.addRow(linha);
                }
                
                tabela = new JTable(modelo);
                tabela.addMouseListener(new OuvinteSelecionarItem(this, "Editar Serviço", tela));
                tabela.setLayout(new DynamicLayout(300, 500));
                tabela.setCursor(new Cursor(Cursor.HAND_CURSOR));
                JScrollPane scroll = new JScrollPane(tabela);
            
                scroll.setBounds(0, 0, 300, 500);
                add(scroll);
                
            }else{
                getContentPane().setBackground(Color.white);
                JLabel msg = new JLabel("Não há serviços cadastrados", JLabel.CENTER);
                msg.setFont(new Font("Arial", Font.BOLD, 20));
                msg.setBounds(0, 0, 300, 500);
                add(msg);
            }
        }
    }

    public JTable getTabela() {
        return tabela;
    }
    
}