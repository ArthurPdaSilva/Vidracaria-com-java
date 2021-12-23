package ouvintes;

import configuracoes.Mensageiro;
import configuracoes.Persistencia;
import classes.ListaDeUsuarios;
import janelas.JanelaEsqueceuASenha;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class OuvinteEnviarCodigoDeRecuperacao implements ActionListener{
    private final JanelaEsqueceuASenha tela;
    private String codigo;
    
    public OuvinteEnviarCodigoDeRecuperacao(JanelaEsqueceuASenha tela){
        this.tela = tela;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        
        ListaDeUsuarios listaDeUsuarios = ListaDeUsuarios.obterInstancia();
        Persistencia pe = new Persistencia();
        if(listaDeUsuarios.recuperarUsuarioPeloEmail(tela.getEmail().getText()) != null){
            this.codigo = gerarCodigo();
            
            Mensageiro msg = new Mensageiro();
            msg.enviarMensagemAoCliente(tela.getEmail().getText(), codigo);
            String codigoS = JOptionPane.showInputDialog(tela, "Digite o código que você recebeu pelo email", "Recuperação", JOptionPane.INFORMATION_MESSAGE);
            
            if(codigoS.equals(this.codigo)){
                String senha = JOptionPane.showInputDialog(tela, "Digite sua nova senha", "Senha", JOptionPane.INFORMATION_MESSAGE);
                
                while(senha.length() < 8){
                    JOptionPane.showMessageDialog(tela, "Sua senha deve ter mais que 8 caracteres");
                    senha = JOptionPane.showInputDialog(tela, "Digite uma senha válida!!!", "Senha", JOptionPane.INFORMATION_MESSAGE);
                }

                //Salvando nova senha
                
                listaDeUsuarios.recuperarUsuarioPeloEmail(tela.getEmail().getText()).setSenha(senha);
                String nome = listaDeUsuarios.recuperarUsuarioPeloEmail(tela.getEmail().getText()).getNome();
                long id = listaDeUsuarios.recuperarUsuarioPeloEmail(tela.getEmail().getText()).getId();
                pe.salvarListaDeUsuarios(listaDeUsuarios, "usuarios");
                listaDeUsuarios.recuperarUsuarioPeloId(id).setEmail(tela.getEmail().getText());
                listaDeUsuarios.recuperarUsuarioPeloId(id).setSenha(senha);
                listaDeUsuarios.recuperarUsuarioPeloId(id).setNome(nome);
                JOptionPane.showMessageDialog(tela, "Senha Alterada!");
                
            }else
                JOptionPane.showMessageDialog(tela, "Codigo incorreto");
            
            tela.dispose();
        }else
            JOptionPane.showMessageDialog(tela, "Email Incorreto");

    }
    
    public String gerarCodigo(){
        String codigoGerado = "";
        for(int i = 0; i < 6; i++){
            codigoGerado += (int) (Math.random() * 10);
        }
        return codigoGerado;
    }

}