package ouvintes;

import configuracoes.Criptografia;
import configuracoes.Persistencia;
import classes.ListaDeUsuarios;
import classes.Usuario;
import janelas.JanelaDeCadastro;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date; 
import javax.swing.JOptionPane;

public class OuvinteBtnCadastrar implements ActionListener{
    private JanelaDeCadastro janela;
    
    public OuvinteBtnCadastrar(JanelaDeCadastro janela){
        this.janela = janela;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Regex
        String patternEmail = "\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b";
        
        
        ListaDeUsuarios listaDeUsuarios = ListaDeUsuarios.obterInstancia();
        String mensagem;
        int icone = JOptionPane.WARNING_MESSAGE;
        String nome = janela.getArrayCampos()[0].getText().trim();
        String email = janela.getArrayCampos()[1].getText().trim();
        Date dataAntes = (Date) janela.getDataCampo().getValue();
        String genero = janela.getBtnRadios()[0].isSelected() ? "Masculino" : (janela.getBtnRadios()[1].isSelected() ? "Feminino" : "Outro");
        String password = janela.getSenha().getText().trim();
        
        if(nome.isEmpty() || email.isEmpty() || password.isEmpty()){
            mensagem = "Preencha todos os campos!";
        }else if(!email.matches(patternEmail)){
            mensagem = "Preencha corretamente o campo email!";
        }else if(listaDeUsuarios.recuperarUsuarioPeloEmail(email) != null){
            mensagem = "Usuário já cadastrado!";
        }else if(password.length() < 8){
            mensagem = "Sua senha deve ter mais que 8 caracteres";
        }else{
            Calendar cal = Calendar.getInstance();
            int anoAtual = cal.get(Calendar.YEAR);
            cal.setTime(dataAntes);
            int anoDigitado = cal.get(Calendar.YEAR);
            if(anoDigitado >= anoAtual){
                mensagem = "Digite uma data válida!";
            }else{
                //Recuperando instância e cadastrando
                Usuario user = new Usuario(nome, email, password, genero, dataAntes, Long.parseLong("1111111111111"));
                listaDeUsuarios.adicionarUsuario(user);
                Persistencia pe = new Persistencia();
                pe.salvarListaDeUsuarios(listaDeUsuarios, "usuarios");
                
                mensagem = "Cadastro concluído!";
            }
            
            icone = JOptionPane.INFORMATION_MESSAGE;
        }
        
        JOptionPane.showMessageDialog(janela, mensagem, "Aviso!", icone);
        
        if(mensagem.equals("Cadastro concluído!"))
            janela.dispose();
    }

}
