package classes;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Imagem {
    private InputStream origem;
    private OutputStream destino;
    private File foto;
    
    public Imagem(File foto){
        this.foto = foto;
        this.origem = null;
        this.destino  = null;
    }
    
    public Imagem(){}
    
    public void salvarImg(Long nomeFoto, String tipo) throws IOException {
        String nome = String.valueOf(nomeFoto);
        String local = verificacao(tipo);
        
        try {
            //Define a entrada e saida da foto
            origem = new FileInputStream(this.foto);
            destino = new FileOutputStream("imagens/" + local + nome);
            byte[] buffer = new byte[1024];
            int length;
            
            //percorre todo o buffer da imagem e escreve no destino
            while ((length = origem.read(buffer)) > 0)
                destino.write(buffer, 0, length);
            origem.close();
            destino.close();
        } catch(NullPointerException ex){}
    }
    
    public static ImageIcon carregarImg(Long nome, String tipo, int w, int h) throws IOException{
        String local = verificacao(tipo);
        
        String newNome = String.valueOf(nome);
        BufferedImage imagem = ImageIO.read(new FileInputStream("imagens/" + local + newNome));
        return imagem == null ? null : new ImageIcon(imagem.getScaledInstance(w, h, Image.SCALE_SMOOTH));
    }
    
    public static String verificacao(String tipo){
        String local;
        
        local = switch (tipo) {
            case "user" -> "users/";
            case "produto" -> "produtos/";
            default -> "servicos/";
        };
        return local;
    }
    
    public void limparImagensInultilizadas(){
        File[] prods = new File("imagens/produtos/").listFiles();
        File[] users = new File("imagens/users/").listFiles();
        File[] servs = new File("imagens/servicos/").listFiles();
        boolean t;
        
        if(prods.length > 0){
            for(int i = 0; i < prods.length; i++){
                t = true;
                for(int j = 0; j < ListaDeProdutos.obterInstancia().getProdutos().size(); j++){
                    if(prods[i].getName().equals(String.valueOf(ListaDeProdutos.obterInstancia().getProdutos().get(j).getFoto())))
                        t = false;
                }
                ver(t, prods, i);
            }
        }
        if(users.length > 0){
            for(int i = 0; i < users.length; i++){
                t = true;
                for(int j = 0; j < ListaDeUsuarios.obterInstancia().getUsuarios().size(); j++){
                    if(users[i].getName().equals(String.valueOf(ListaDeUsuarios.obterInstancia().getUsuarios().get(j).getImagem())))
                        t = false;
                }
                ver(t, users, i);
            }
        }
        if(servs.length > 0){
            for(int i = 0; i < servs.length; i++){
                t = true;
                for(int j = 0; j < ListaDeServicos.obterInstancia().getServicos().size(); j++){
                    if(servs[i].getName().equals(String.valueOf(ListaDeServicos.obterInstancia().getServicos().get(j).getFoto())))
                        t = false;
                }
                ver(t, servs, i);
            }
        }
    }
    
    private void ver(boolean teste, File[]lista, int posicao){
        if(teste && !(lista[posicao].getName().equals("1111111111111"))){
            lista[posicao].delete();
        }
    }
}