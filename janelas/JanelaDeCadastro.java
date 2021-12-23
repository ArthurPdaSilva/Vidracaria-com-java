package janelas;

import interfaces.InterfaceDeControle;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import ouvintes.OuvinteBtnCadastrar;
import ouvintes.OuvinteCampo;
import net.sf.nachocalendar.components.DateField;
import ouvintes.OuvinteBtnSair;

public final class JanelaDeCadastro extends JanelaEstrutura implements InterfaceDeControle{
    private JTextField[] arrayCampos;
    private DateField dataCampo;
    private JPasswordField senha;
    private JRadioButton[] btnRadios;
    
    public JanelaDeCadastro(){
        super(600, 600);
        super.addPainelCentral(100, 50, 400, 500);
        super.addTitulo(0, 10, 400, 50, "Cadastre-se");
        this.addItens();
        this.addBotoes();
        this.addTextos();
        setDefaultCloseOperation(JanelaDeCadastro.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
    
    @Override
    public void addTextos() {
        String[] arrayNomes = {"Nome Completo", "Email", "Senha", "Data de Nasci.", "Gênero"};
        JLabel[] arrayLabels = new JLabel[5];
        int y = 120;
        
        for(int c = 0; c < 5; c++){
            arrayLabels[c] = new JLabel(arrayNomes[c]);
            if(c == 4)
                arrayLabels[c].setBounds(180, (y - 60), 120, 30);
            else
                arrayLabels[c].setBounds(20, y, 150, 30);
            arrayLabels[c].setFont(new Font("Arial", Font.BOLD, 30));
            this.getPainel().add(arrayLabels[c]);
            y += 60;
        }
        
    }
    
    @Override
    public void addItens() {
        String[] arrayNomes = {"M", "F", "Outro"};
        arrayCampos = new JTextField[2];
        senha = new JPasswordField();
        btnRadios = new JRadioButton[3];
        ButtonGroup btngroup = new ButtonGroup();
        int x = 180;
        int y = 150;
        
        for(int c = 0; c < 2; c++){
            arrayCampos[c] = new JTextField("");
            if(c == 0)
                arrayCampos[0].addKeyListener(new OuvinteCampo());
            arrayCampos[c].setBounds(20, y, 350, 30);
            arrayCampos[c].setFont(new Font("Arial", Font.PLAIN, 16));
            this.getPainel().add(arrayCampos[c]);
            y += 60;
        }
        
        senha.setBounds(20, y, 350, 30);
        senha.setFont(new Font("Arial", Font.PLAIN, 16));
        this.getPainel().add(senha);
        
        //Data de nascimento
        dataCampo = new DateField();
        dataCampo.setBounds(20, (y + 65), 120, 30);
        dataCampo.setFont(new Font("Arial", Font.PLAIN, 16));
        this.getPainel().add(dataCampo);
        
        for(int c = 0; c < 3; c++){
            btnRadios[c] = new JRadioButton(arrayNomes[c]);
            btnRadios[c].setBounds(x, (y + 65), 65, 30);
            btnRadios[c].setFont(new Font("Arial", Font.BOLD, 15));
            btngroup.add(btnRadios[c]);
            this.getPainel().add(btnRadios[c]);
            x += 65;
        }
    }

    @Override
    public void addBotoes() {
        String[] nomes = {"VOLTAR", "CADASTRAR"};
        JButton[] btns = new JButton[2];
        int x = 20;
        
        for(int c = 0; c < 2; c++){
            btns[c] = new JButton(nomes[c]);
            btns[c] = new JButton(nomes[c]);
            btns[c].setBackground(c == 0 ? new Color(223, 41, 53) : new Color(23, 184, 144));
            btns[c].setForeground(Color.white);
            btns[c].addActionListener(c == 0 ? new OuvinteBtnSair(this) : new OuvinteBtnCadastrar(this));
            btns[c].setCursor(new Cursor(Cursor.HAND_CURSOR));
            btns[c].setBounds(x, 420, 170, 40);
            btns[c].setBorder(null);
            btns[c].setFont(new Font("Arial", Font.BOLD, 15));
            x += 180;
            this.getPainel().add(btns[c]);
        }
    }

    public JTextField[] getArrayCampos() {
        return arrayCampos;
    }

    public void setArrayCampos(JTextField[] arrayCampos) {
        this.arrayCampos = arrayCampos;
    }

    public DateField getDataCampo() {
        return dataCampo;
    }

    public void setDataCampo(DateField dataCampo) {
        this.dataCampo = dataCampo;
    }

    public JPasswordField getSenha() {
        return senha;
    }

    public void setSenha(JPasswordField senha) {
        this.senha = senha;
    }
    
    public JRadioButton[] getBtnRadios() {
        return btnRadios;
    }

    public void setBtnRadios(JRadioButton[] btnRadios) {
        this.btnRadios = btnRadios;
    }

}
    