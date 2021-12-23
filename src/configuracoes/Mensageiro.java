package configuracoes;

import classes.ListaDeUsuarios;
import classes.Usuario;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;

public class Mensageiro {
    
    public void enviarMensagemAoCliente(String email, String mensagem){
        Usuario userLogado = ListaDeUsuarios.obterInstancia().getUsuarioLogado();
        // Criação do objeto para inserir as propriedades dos parâmetros
        Properties a = new Properties();
        
        // Inserção dos parâmetros para estabelecer conexão com o gmail
        a.put("mail.smtp.user", "vidracariaprojetim@gmail.com"); 
        a.put("mail.smtp.host", "smtp.gmail.com"); 
        a.put("mail.smtp.port", "25"); 
        a.put("mail.debug", "true"); 
        a.put("mail.smtp.auth", "true"); 
        a.put("mail.smtp.starttls.enable","true"); 
        a.put("mail.smtp.EnableSSL.enable","true");
        
        // Essas duas propriedades, ultilizam a versão do protocolo SSl compatível
        a.setProperty("mail.pop3s.ssl.protocols", "TLSv1.2");
        a.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        
        a.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");   
        a.setProperty("mail.smtp.socketFactory.fallback", "false");   
        a.setProperty("mail.smtp.port", "465");   
        a.setProperty("mail.smtp.socketFactory.port", "465");
        
        // Autenticação da conta
        Session session = Session.getDefaultInstance(a, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication("vidracariaprojetim@gmail.com", ListaDeUsuarios.obterInstancia().getUsuarios().get(0).getSenha());
            }
        });
        
        // Inicialização da sessão
        session.setDebug(true);
        
        // Envio da mensagem
        try {
            //Criação do objeto mensagem
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("vidracariaprojetim@gmail.com"));
            
            // Atribuição do Destinatário
            Address[] toUser = InternetAddress.parse(email);
            msg.setRecipients(Message.RecipientType.TO, toUser);
            
            // Configuação do corpo da mensagem
            boolean isNumeric =  mensagem.matches("[+-]?\\d*(\\.\\d+)?");
            if(isNumeric){
                msg.setSubject("CÓDIGO DE VERIFICAÇÃO DA SUA CONTA NA NOSSA LOJA");
                msg.setText("O CÓDIGO DE VERIFICAÇÃO DA SUA CONTA É: " + mensagem);
            }else{
                msg.setSubject("COMPROVANTE DE COMPRA E CONTRATAÇÃO");
                MimeBodyPart corpo = new MimeBodyPart();
                Multipart multipart = new MimeMultipart();
                DataSource local = new FileDataSource("comprovantes/comprovante-de-" + userLogado.getNome() + ".pdf");
                corpo.setDataHandler(new DataHandler(local));
                corpo.setFileName("Comprovante.pdf");
                multipart.addBodyPart(corpo);
                
                msg.setContent(multipart);
            }
            
            // Envio da mensagem
            Transport.send(msg);
             
        } catch (MessagingException ex) {
        	JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
}