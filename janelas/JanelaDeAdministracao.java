package janelas;

import DynamicLayout.DynamicLayout;
import classes.Imagem;
import classes.ListaDeUsuarios;
import classes.Produto;
import classes.Servico;
import classes.Usuario;
import interfaces.InterfaceDeControle;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import ouvintes.OuvinteBtnAdm;
import ouvintes.OuvinteBtnSair;
import ouvintes.OuvinteCriarPlanilha;

public final class JanelaDeAdministracao extends JanelaEstrutura implements InterfaceDeControle{
    private Usuario usuarioLogado;
    
    public JanelaDeAdministracao(){
        super(900,700);
        this.usuarioLogado = ListaDeUsuarios.obterInstancia().getUsuarioLogado();
        super.addPainelCentral(50, 50, 800, 600);
        super.addTitulo(0, 10, 800, 50, "BEM VINDO, " + usuarioLogado.getNome() +"!");
        super.addSeparador(400, 120, 20, 400, "VERTICAL");
        super.addSeparador(100, 80, 650, 20, "HORIZONTAL");
        this.addItens();
        this.addBotoes();
        this.addTextos();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    @Override
    public void addTextos() {
        String[] subs = {"Área Técnica", "Clientes cadastrados"};
        JLabel[] tts = new JLabel[2];
        int x = 80;
        
        for(int c = 0; c < 2; c++){
            tts[c] = new JLabel(subs[c], JLabel.CENTER);
            tts[c].setFont(new Font("Arial", Font.BOLD, 40));
            tts[c].setBounds(x, 85, 300, 40);
            this.getPainel().add(tts[c]);
            x += 360;
        }
    }

    @Override
    public void addBotoes() {
        String[] bts = {"Voltar", "Limpar"};
        int x = 40;
        for(int i = 0; i < 2; i++){
            JButton btn = new JButton(bts[i]);
            btn.setBorder(null);
            btn.setBounds(x, 550, 200, 40);
            btn.setFont(new Font("Arial", Font.BOLD, 20));
            btn.setBackground(new Color(223, 41, 53));
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btn.setForeground(Color.white);
            btn.addActionListener(btn.getText().equals("Voltar") ? new OuvinteBtnSair(this) : new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Imagem img = new Imagem();
                img.limparImagensInultilizadas();
                JOptionPane.showMessageDialog(null, "Itens denecess�rios apagados");
                dispose();
                new JanelaDeAdministracao();
            }
        });
            this.getPainel().add(btn);
            x += 550;
        }
    }

    @Override
    public void addItens() {
        String[] subs = {"Cadastrar Item", "Editar Item", "Cadastrar Serviço", "Editar Serviço", "Gerar Planilha"};
        JButton[] btns = new JButton[5];
        int y = 120;
        
        for(int c = 0; c < 5; c++){
            btns[c] = new JButton(subs[c]);
            btns[c].setBorder(null);
            btns[c].setBounds(120, y, 200, 60);
            btns[c].setFont(new Font("Arial", Font.BOLD, 20));
            btns[c].setBackground(new Color(23, 184, 144));
            btns[c].setForeground(Color.white);
            btns[c].setCursor(new Cursor(Cursor.HAND_CURSOR));
            btns[c].addActionListener(c < 4 ? new OuvinteBtnAdm(subs[c], new Produto(), new Servico(), this) : new OuvinteCriarPlanilha());
            this.getPainel().add(btns[c]);
            y += 80;
        }
        
        DefaultTableModel modelo  = new DefaultTableModel();
        modelo.addColumn("Nome");
        modelo.addColumn("Email"); 
        JTable tabela;

        
        if(ListaDeUsuarios.obterInstancia().getUsuarios().size() > 0){
            for(Usuario u : ListaDeUsuarios.obterInstancia().getUsuarios()){
                Object[] linha = new Object[2];
                linha[0] = u.getNome();
                linha[1] = u.getEmail();
                modelo.addRow(linha);
            }

            tabela = new JTable(modelo);
            tabela.setLayout(new DynamicLayout(300, 500));
            tabela.setCursor(new Cursor(Cursor.HAND_CURSOR));
            tabela.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {}
				
				@Override
				public void mousePressed(MouseEvent e) {}
				
				@Override
				public void mouseExited(MouseEvent e) {}
				
				@Override
				public void mouseEntered(MouseEvent e) {}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					if(tabela.getSelectedRow() == 0) {
						JOptionPane.showMessageDialog(null, "Não é possível apagar ou editar o Adm!");
					}else{
						ListaDeUsuarios listav2 = ListaDeUsuarios.obterInstancia();
						String patternEmail = "\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b";

						String[] qts = {"Apagar", "Editar"};
						int resposta =  JOptionPane.showOptionDialog(null, "Opções",
				                "Opções", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, qts, qts[0]);
						
						if(resposta == 0) {
							listav2.getUsuarios().remove(tabela.getSelectedRow());
							JOptionPane.showMessageDialog(null, "Usuário apagado com sucesso!");
							modelo.removeRow(tabela.getSelectedRow());
							new JanelaDeAdministracao();
							dispose();
						}else{
							String[] opcoes = {"nome", "email", "senha"};
							String v = (String) JOptionPane.showInputDialog(null, "Selecione qual o que você quer editar", "Edição", JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);
							String novoValor = (String) JOptionPane.showInputDialog(null, "Qual o novo valor para o campo?", "Novo valor", JOptionPane.INFORMATION_MESSAGE);
							if(v.equals("nome")) {
								listav2.getUsuarios().get(tabela.getSelectedRow()).setNome(novoValor);
							}else { 
								if(v.equals("email")) {
							
									if(!novoValor.matches(patternEmail) && listav2.recuperarUsuarioPeloEmail(novoValor) != null) {
										JOptionPane.showMessageDialog(null, "Email inválido!");
									}else {
										listav2.getUsuarios().get(tabela.getSelectedRow()).setEmail(novoValor);
									}
								
								}else {
									if(novoValor.length() < 8) {
										JOptionPane.showMessageDialog(null, "Senha inválida!");
									}else {
										listav2.getUsuarios().get(tabela.getSelectedRow()).setEmail(novoValor);
									}
								}
							}
							
						}
					}
				}
			});
						
					

            JScrollPane scroll = new JScrollPane(tabela);
            scroll.setBounds(440, 120, 300, 400);
            this.getPainel().add(scroll);
        }
    } 
}
	