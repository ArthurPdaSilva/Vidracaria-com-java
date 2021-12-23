package configuracoes;

import classes.ListaDeProdutos;
import classes.ListaDeServicos;
import classes.ListaDeUsuarios;
import classes.Usuario;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

public class Persistencia {
    //Xtream faz a manipulação do xml, DomDriver é o codificador
    private XStream xstream = new XStream(new DomDriver("UTF-8"));
    private File arquivo;
    
    public Persistencia(){
        xstream.addPermission(AnyTypePermission.ANY);
    }
    
    public void salvarListaDeUsuarios(ListaDeUsuarios object, String nome){
        ListaDeUsuarios listaDeUsuarios = ListaDeUsuarios.obterInstancia();
        //Aqui criará um arquivo xml
        arquivo = new File(nome + ".xml");
        
        //Criptografando
        Criptografia cg = new Criptografia();
        for (Usuario usuario : object.getUsuarios()) {
            usuario.setNome(cg.criptogafar(usuario.getNome()));
            usuario.setEmail(cg.criptogafar(usuario.getEmail()));
            usuario.setSenha(cg.criptogafar(usuario.getSenha()));
        }
        
        //Transformar o objeto em uma string para ser gravada no arquivo xml
        String xmlString = xstream.toXML(object);
        
        //Se arquivo não existir
        try {
            //Vai criar um arquivo, senão existir
            arquivo.createNewFile();
            //Salvar as informações no arquivo
            PrintWriter gravar = new PrintWriter(arquivo);
            //Salvando as informações
            gravar.print(xmlString);
            //E depois fechamos o  arquivo
            gravar.close();
        
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
        // Descriptografando
        for(Usuario usuario : listaDeUsuarios.getUsuarios()) {
            usuario.setNome(cg.descriptografar(usuario.getNome()));
            usuario.setEmail(cg.descriptografar(usuario.getEmail()));
            usuario.setSenha(cg.descriptografar(usuario.getSenha()));
        }
    }
    
    public ListaDeUsuarios recuperarLista(String nome){
        arquivo = new File(nome + ".xml");
        
        //Se o arquivo existir
        try {
            //Pegar os bits desse arquivo
            if(arquivo.exists()){
                FileInputStream mani = new FileInputStream(arquivo);
                //Faz a conversão dos dados do arquivo xml para o tipo Central
                
                ListaDeUsuarios newLista = (ListaDeUsuarios) xstream.fromXML(mani);
                Criptografia cg = new Criptografia();
                
                for (Usuario usuario : newLista.getUsuarios()) {
                    usuario.setNome(cg.descriptografar(usuario.getNome()));
                    usuario.setEmail(cg.descriptografar(usuario.getEmail()));
                    usuario.setSenha(cg.descriptografar(usuario.getSenha()));
                }
                
                return newLista;
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
            
        //Se o arquivo não existire, ele vai retornar uma nova instancia
        return new ListaDeUsuarios();
    }   
    
    public void salvarListaDeProdutos(ListaDeProdutos object, String nome){
        
        arquivo = new File(nome + ".xml");
        String xmlString = xstream.toXML(object);
        
        try {
            arquivo.createNewFile();
            PrintWriter gravar = new PrintWriter(arquivo);
            gravar.print(xmlString);
            gravar.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    public ListaDeProdutos recuperarProduto(String nome){
        arquivo = new File(nome + ".xml");
        
        try {
            if(arquivo.exists()){
                FileInputStream mani = new FileInputStream(arquivo);
                return (ListaDeProdutos) xstream.fromXML(mani);
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
            
        return new ListaDeProdutos();
    }   
    
    public void salvarListaDeServicos(ListaDeServicos object, String nome){
        
        arquivo = new File(nome + ".xml");
        String xmlString = xstream.toXML(object);
        
        try {
            arquivo.createNewFile();
            PrintWriter gravar = new PrintWriter(arquivo);
            gravar.print(xmlString);
            gravar.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    public ListaDeServicos recuperarServico(String nome){
        arquivo = new File(nome + ".xml");
        
        try {
            if(arquivo.exists()){
                FileInputStream mani = new FileInputStream(arquivo);
                return (ListaDeServicos) xstream.fromXML(mani);
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
            
        return new ListaDeServicos();
    }   

}
