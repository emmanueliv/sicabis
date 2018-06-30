//################################################################################
//      ## Fecha de creación: 18/12/15
//      ## Fecha de última modificación: 18/12/15
//      ## Responsable: Emmanuel De la Isla Vértiz
//      ## Módulos asociados: Envío correo
//################################################################################
package com.issste.sicabis.utils;

import com.issste.sicabis.ejb.modelo.Contactos;
import com.issste.sicabis.ejb.modelo.CorreoElectronico;
import com.issste.sicabis.ejb.modelo.Usuarios;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMail implements MailRemote {

    private Properties prop;
    private OutputStream output;
    private InputStream input;
    private final String FILE_NAME = "mail.properties";
    private final String COMMENT = "Archivo de Configuracion para cliente de correo";
    private boolean requireAuth;

    @Override
    public void escribirConfiguracion(Config conf) throws Exception {
        prop = new Properties();

        try {
            output = new FileOutputStream(FILE_NAME);
            prop.put("host", conf.getHost());
            prop.put("port", conf.getPort());
            prop.put("auth", String.valueOf(conf.isAuth()));
            prop.put("usr", conf.getUsr());
            prop.put("pwd", conf.getPassword());
            prop.put("protocol", String.valueOf(conf.getProtocol().getProtocolo()));
            prop.put("tls", String.valueOf(conf.isTLS()));
            prop.put("ssl", String.valueOf(conf.isSsl()));

            prop.store(output, COMMENT);
        } catch (IOException fnf) {
            LOG.log(Level.SEVERE, fnf.getMessage());
            throw new Exception("Error al escribir la configuracion de correo");

        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException ioe) {
                    throw new Exception("Error al cerrar el archivo");
                }
            }
        }
    }

    /**
     * Lee la configuracion almacenada previamente
     *
     * @throws Exception Error de lectura del archivo de configuracion
     */
    private Properties leerConfiguracion() throws Exception {
        prop = new Properties();
        Properties conf = new Properties();
        try {
            input = new FileInputStream(FILE_NAME);
            prop.load(input);
            conf.setProperty("mail.smtp.host", prop.getProperty("mail.smtp.host"));
            conf.setProperty("mail.smtp.starttls.enable", prop.getProperty("mail.smtp.starttls.enable"));
            conf.setProperty("mail.smtp.port", prop.getProperty("mail.smtp.port"));
            conf.setProperty("mail.smtp.user", prop.getProperty("mail.smtp.user"));
            conf.setProperty("mail.smtp.password", prop.getProperty("mail.smtp.password"));
            conf.setProperty("mail.smtp.auth", prop.getProperty("mail.smtp.auth"));
            //conf.setProperty("mail.smtp.ssl.trust", "*");
            conf.setProperty("mail.smtp.socketFactory.port", "25");
            conf.setProperty("mail.smtp.socketFactory.fallback", "false");
            requireAuth = Boolean.parseBoolean(prop.getProperty("auth"));

            return conf;
        } catch (FileNotFoundException fe) {
            LOG.log(Level.SEVERE, "Error al leer el archivo", fe);
            escribirDefaults();
            throw new Exception("El cliente de correo no esta configurado");
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Archivo mal Formado", e);
            escribirDefaults();
            throw new Exception("El archivo esta mal formado, se sobreescribira un archivo muestra");
        }
    }

    @Override
    public void sendMail(String[] destinatarios, String asunto, String mensaje) throws Exception {
        Properties p = leerConfiguracion();
        Session sesion = Session.getDefaultInstance(p);
        try {
            if (requireAuth) {
                sesion = Session.getInstance(p, new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(prop.getProperty("usr"), prop.getProperty("pwd"));
                    }
                });
            } else {
                Session.getDefaultInstance(p);
            }

            MimeMessage correo = new MimeMessage(sesion);
            correo.setFrom(new InternetAddress(prop.getProperty("mail.smtp.user"), "SICABIS"));
            InternetAddress[] direcciones = new InternetAddress[destinatarios.length];

            for (int i = 0; i < destinatarios.length; i++) {
                direcciones[i] = new InternetAddress(destinatarios[i]);
            }

            correo.addRecipients(Message.RecipientType.TO, direcciones);
            correo.setSubject(asunto);
            correo.addRecipients(Message.RecipientType.CC, "mariel.suarezpi@issste.gob.mx, emmanuel.delaisla.it@gmail.com");
            correo.setContent(mensaje, "text/html; charset=utf-8");
            Transport t = sesion.getTransport("smtp");
            if (!requireAuth) {
                t.connect();
            }
            Transport.send(correo);
            System.out.println("Correo enviado :D");
        } catch (MessagingException me) {
            me.printStackTrace();
            throw new Exception("Error al enviar el correo electrónico verificar con el Administrador.");
        }
    }

    @Override
    public void sendAttachMail(String[] destinatarios, String asunto, String mensaje, File attachment) throws Exception {
        Properties p = leerConfiguracion();
        Session sesion = Session.getDefaultInstance(p);
        try {
            if (requireAuth) {
                sesion = Session.getInstance(p, new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(prop.getProperty("usr"), prop.getProperty("pwd"));
                    }
                });
            } else {
                Session.getDefaultInstance(p);
            }

            MimeMessage correo = new MimeMessage(sesion);
            correo.setFrom(new InternetAddress(prop.getProperty("mail.smtp.user"), "SICABIS"));
            InternetAddress[] direcciones = new InternetAddress[destinatarios.length];

            for (int i = 0; i < destinatarios.length; i++) {
                direcciones[i] = new InternetAddress(destinatarios[i]);
            }
            
            correo.addRecipients(Message.RecipientType.TO, direcciones);
            correo.setSubject(asunto);
            correo.addRecipients(Message.RecipientType.CC, "mariel.suarezpi@issste.gob.mx, emmanuel.delaisla.it@gmail.com");
            
            BodyPart adjunto = new MimeBodyPart();
            adjunto.setDataHandler(new DataHandler(new FileDataSource(attachment)));
            adjunto.setFileName(attachment.getName());
            
            BodyPart texto = new MimeBodyPart();
            texto.setContent(mensaje, "text/html; charset=utf-8");
            
            MimeMultipart multiparte = new MimeMultipart();
            multiparte.addBodyPart(adjunto);
            multiparte.addBodyPart(texto);
            correo.setContent(multiparte);
            System.out.println("antes de conectar session------->");
            Transport t = sesion.getTransport("smtp");
            if (!requireAuth) {
                t.connect();
            }
            System.out.println("antes de send el correo");
            Transport.send(correo);
            System.out.println("Correo enviado satisfactorio :D");
        } catch (MessagingException me) {
            me.printStackTrace();
            throw new Exception("Error al enviar el correo electrónico verificar con el Administrador.");
        }
    }

    private void escribirDefaults() throws Exception {
        Config c = new Config();
        c.setAuth(true);
        c.setHost("mail.server.com"); //Direccion del servidor de correo
        c.setIsTLS(true); //El servidor esta configurado para usar TLS?           
        c.setUsr("user@mail.com"); // Usuario
        c.setPassword("pswd");  // ContraseÃ±a
        c.setPort("587"); // Puerta del servidor SMTP
        c.setProtocol(ProtocolActual.SMTP); // Protocolo : (SMTP, SMTPS)
        escribirConfiguracion(c);
    }
    private static final Logger LOG = Logger.getLogger(MailRemote.class.getName());

    public static void writeMail(Usuarios user, String asunto, String msj) {
        try {
            StringBuilder sb = new StringBuilder();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String fecha = sdf.format(new Date());
            sb.append("<h2>");
            sb.append("SICABIS");
            sb.append("</h2>");
            sb.append("<br> El d&iacute;a: ");
            sb.append(fecha);
            sb.append("<br><b>" + user.getNombre() + "</b>");
            sb.append("<br>");
            sb.append("<br>Estimado Delegado:");
            sb.append("<br><br>" + msj + "<b>");

            String correo = "  <table>\n"
                    + "            <tr>\n"
                    + "                <td>\n"
                    + "                    <img src=\"http://www2.issste.gob.mx:8080/images/logos/issste2013-header_2.jpg\" alt=\"Header\" />\n"
                    + "                </td>\n"
                    + "            </tr>\n"
                    + "            <tr style=\"height: 25px;\"></tr>\n"
                    + "            <tr>\n"
                    + "                <td>\n"
                    + "                    <label>" + sb.toString() + "</label>\n"
                    + "                </td>\n"
                    + "            </tr>\n"
                    + "            <tr>\n"
                    + "                <td>\n"
                    + "                    <img src=\"http://www2.issste.gob.mx:8080/images/logos/issste2013-footer.jpg\" alt=\"Footer\" />\n"
                    + "                </td>\n"
                    + "            </tr>\n"
                    + "        </table>";
            SendMail sendMail = new SendMail();
            //sendMail.sendMail(new String[]{user.getEmail()}, asunto, correo);
            sendMail.sendAttachMail(new String[]{user.getCorreo()}, asunto, correo, new File(""));
        } catch (Exception ex) {
            Logger.getLogger(SendMail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void writeMailCorreo(String correo, String asunto, String msj) {
        try {
            SendMail sendMail = new SendMail();
            System.out.println("justo antes de enviar ----------->" + correo);
            sendMail.sendMail(new String[]{correo}, asunto, msj);
            System.out.println("justo despues de enviar ------------>");
        } catch (Exception ex) {
            Logger.getLogger(SendMail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void writeMailFileCorreo(String correo, String asunto, String msj, File adjunto) {
        try {
            SendMail sendMail = new SendMail();
            System.out.println("justo antes de enviar atach ----------->" + correo);
            String destinatarios[] = correo.split("\\;");
            for (String d : destinatarios) {
                System.out.println("d------>"+d);
            }
            sendMail.sendAttachMail(destinatarios, asunto, msj, adjunto);
            System.out.println("justo despues de enviar atach ------------>");
        } catch (Exception ex) {
            Logger.getLogger(SendMail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}