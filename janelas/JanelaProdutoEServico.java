package janelas;

import DynamicLayout.DynamicLayout;
import configuracoes.Persistencia;
import static classes.Imagem.carregarImg;
import classes.ListaDeProdutos;
import classes.ListaDeServicos;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import ouvintes.OuvinteEnviarImagem;
import ouvintes.OuvintePlaceholder;

public abstract class JanelaProdutoEServico extends JanelaEstrutura{
    private JanelaDeAdministracao janelaParaAtualizar;
    private JLabel titulo;
    private String tipo;
    private JTextField nome;
    private JPanel imagemPanel;
    private JLabel imagemLabel;
    private JPanel painel;
    private JTextArea descricao;
    private JTextField preco;
    private Long imagem;
    private JButton salvar;
    private JSpinner quantidade;
    
    public JanelaProdutoEServico(String tipo, String titulo, JanelaDeAdministracao tela){
        super(700, 600);
        this.titulo = new JLabel(titulo);
        this.imagem = System.currentTimeMillis();
        this.tipo = tipo;
        this.janelaParaAtualizar = tela;
        try { addPainel(); } catch (IOException ex) {}
        addTitulo();
        addTxts();
        addBts();
        addFoto();
        setVisible(true);
        
    }
    
    private void addTitulo() {
        if(titulo.getText().equals("Produtos")){
            titulo = new JLabel(tipo.equals("Cadastrar Item") ? "Cadastrar Produto" : "Editar Produto", JLabel.CENTER);
        }else{
            titulo = new JLabel(tipo.equals("Cadastrar Serviço") ? "Cadastrar Serviço" : "Editar Serviço", JLabel.CENTER);
        }
        
        titulo.setBounds(0, 0, 500, 40);
        titulo.setFont(new Font("Arial", Font.BOLD, 40));
        
        painel.add(titulo);
    }

    private void addTxts() {
        nome = new JTextField("");
        descricao = new JTextArea("");
        preco = new JTextField("");
        
        nome.setBounds(10, 80, 300, 30);
        nome.addFocusListener(new OuvintePlaceholder(nome, "Nome"));
        painel.add(nome);
        
        JScrollPane descScroll = new JScrollPane(descricao);
        descScroll.setBounds(10, 120, 300, 70);
        descricao.setLineWrap(true);
        descricao.setWrapStyleWord(true);
        descricao.addFocusListener(new OuvintePlaceholder(descricao, "Descrição"));
        painel.add(descScroll);
        
        preco.setBounds(10, 200, 200, 30);
        preco.addFocusListener(new OuvintePlaceholder(preco, "Preço"));
        painel.add(preco);
        
    }

    private void addBts() {
        
        salvar = new JButton("Salvar");
        
        if(tipo.equals("Editar Produto") || tipo.equals("Editar ServiÃ§o")){
            JButton apagar = new JButton("Apagar");
            apagar.setBounds(30, 300, 205, 50);
            apagar.setBorder(null);
            apagar.setCursor(new Cursor(Cursor.HAND_CURSOR));
            apagar.setBackground(new Color(223, 41, 53));
            apagar.setForeground(Color.white);
            apagar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(JOptionPane.showConfirmDialog(null, "Tem certeza que deseja apagar este item?", "Confirmação", JOptionPane.YES_OPTION) == JOptionPane.YES_OPTION){
                        Persistencia pe = new Persistencia();
                        if(tipo.equals("Editar Produto")){
                            ListaDeProdutos prods = ListaDeProdutos.obterInstancia();
                            prods.apagarProduto(prods.recuperarProdutoPeloNome(nome.getText()));
                            pe.salvarListaDeProdutos(prods, "produtos");
                        }else{
                            ListaDeServicos servs = ListaDeServicos.obterInstancia();
                            servs.apagarServico(servs.recuperarServicoPeloNome(nome.getText()));
                            pe.salvarListaDeServicos(servs, "servicos");
                        }
                        JOptionPane.showMessageDialog(null, "Item apagado");
                        dispose();
                        janelaParaAtualizar.dispose();
                        JanelaInicio telaa = new JanelaInicio();
                        telaa.dispose();
                        new JanelaDeAdministracao();
                    }
                }
            });
            painel.add(apagar);
            salvar.setBounds(265, 300, 205, 50);
        }else{
            salvar.setBounds(50, 300, 400, 50);
        }
        salvar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        salvar.setBorder(null);
        salvar.setBackground(new Color(55, 114, 255));
        salvar.setForeground(Color.white);
        painel.add(salvar);
    } 

    private void addPainel() throws IOException {
        painel = new JPanel();
        painel.setLayout(new DynamicLayout(500, 400));
        painel.setBounds(100, 100, 500, 400);
        add(painel);
    }

    private void addFoto()  {
        imagemPanel =  new JPanel();
        imagemPanel.setLayout(new DynamicLayout(160, 160));
        imagemPanel.setBounds(320, 70, 160, 160);
        imagemLabel = new JLabel("", JLabel.CENTER);
        imagemLabel.setBounds(10, 17, 140, 140);
        imagemLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        imagemLabel.addMouseListener(new OuvinteEnviarImagem(this, this.getImagem(), this.titulo.getText().equals("Cadastrar Produto") || this.titulo.getText().equals("Editar Produto") ? "produto" : "serviço"));
        
                
        TitledBorder tituloFoto;
        tituloFoto = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Imagem");
        imagemPanel.setBorder(tituloFoto);
        
        try {
            imagemLabel.setIcon(carregarImg(this.imagem, this.titulo.equals("Produtos") ? "produto" : "servico",150, 150));
        } catch (IOException ex) {
            imagemLabel.setText("Enviar Imagem");
        }
        
        imagemPanel.add(imagemLabel);
        
        painel.add(imagemPanel);
    }

    public JLabel getTitulo() {
        return titulo;
    }

    public void setTitulo(JLabel titulo) {
        this.titulo = titulo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public JTextField getNome() {
        return nome;
    }

    public void setNome(JTextField nome) {
        this.nome = nome;
    }

    public JPanel getImagemPanel() {
        return imagemPanel;
    }

    public void setImagemPanel(JPanel imagemPanel) {
        this.imagemPanel = imagemPanel;
    }

    public JLabel getImagemLabel() {
        return imagemLabel;
    }

    public void setImagemLabel(JLabel imagemLabel) {
        this.imagemLabel = imagemLabel;
    }

    public JPanel getPainel() {
        return painel;
    }

    public void setPainel(JPanel painel) {
        this.painel = painel;
    }

    public JTextArea getDescricao() {
        return descricao;
    }

    public void setDescricao(JTextArea descricao) {
        this.descricao = descricao;
    }

    public JTextField getPreco() {
        return preco;
    }

    public void setPreco(JTextField preco) {
        this.preco = preco;
    }

    public Long getImagem() {
        return imagem;
    }

    public void setImagem(Long imagem) {
        this.imagem = imagem;
    }

    public JButton getSalvar() {
        return salvar;
    }

    public void setSalvar(JButton salvar) {
        this.salvar = salvar;
    }

    public JSpinner getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(JSpinner quantidade) {
        this.quantidade = quantidade;
    }

}