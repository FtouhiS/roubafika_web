/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Entities.Demande;
import Entities.Service;
import Entities.Service.Categorie;
import Entities.Demande;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author moham
 */
public class MyDemande {

    private static String USER_NAME = "roubafikaservice";  // GMail user name (just the part before "@gmail.com")
    private static String PASSWORD = "pbmtdethvxelgsdl"; // GMail password
    //private static String RECIPIENT = "mohamedbahaeddine.brinsi@esprit.tn";

    private PreparedStatement pst;
    private Statement ste;
    private Connection connection;
    private ResultSet rs;

    public MyDemande() {
        connection = DataSource.getInstance().getConnection();
    }

    public ObservableList<Demande> afficherDemande() {
        ObservableList<Demande> listeDemande = FXCollections.observableArrayList();
        String requete = "SELECT nom_demandeur,prenom_demandeur,email_demandeur,date_demande,titre FROM demande join service on demande.id_serv= service.id_service";
        Statement st;
        ResultSet rs;
        try {

            ste = connection.createStatement();
            rs = ste.executeQuery(requete);
            while (rs.next()) {
                Demande demande = new Demande();
                demande.setNom_demandeur(rs.getString("nom_demandeur"));
                demande.setPrenom_demandeur(rs.getString("prenom_demandeur"));
                demande.setEmail_demandeur(rs.getString("email_demandeur"));
                demande.setDate_demande(rs.getString("date_demande"));
                demande.setTitreService(rs.getString("titre"));
                listeDemande.add(demande);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MyDemande.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listeDemande;

    }

    public Service getServiceByID(int id) {
        String requete = "SELECT * FROM service where id_service=" + id;
        Statement st;
        ResultSet rs;
        Service s = new Service();
        try {

            ste = connection.createStatement();
            rs = ste.executeQuery(requete);
            while (rs.next()) {

                String categorieStr = rs.getString("categorie");
                Categorie categorie = Categorie.valueOf(categorieStr);
                s = new Service(rs.getString("titre"), rs.getString("description"), rs.getString("date_annonce"), rs.getString("adresse"), categorie, rs.getInt("idUser"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MyDemande.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;

    }

    public void ajouterDemande(Demande d) throws SQLException {
        String requete = "INSERT INTO demande (nom_demandeur, prenom_demandeur, email_demandeur, date_demande, id_serv,idUser) VALUES (?,?,?,?,?,?)";
        try {
            connection = DataSource.getInstance().getConnection();
            pst = connection.prepareStatement(requete);
            pst.setString(1, d.getNom_demandeur());
            pst.setString(2, d.getPrenom_demandeur());
            pst.setString(3, d.getEmail_demandeur());
            pst.setString(4, d.getDate_demande());
            pst.setInt(5, d.getId_serv());
            pst.setInt(6, d.getIdUser());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MyDemande.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean deleteDemande(String id) {
        String requete = "DELETE FROM demande WHERE nom_demandeur= '" + id + "'";
        try {
            ste = connection.createStatement();
            int rowsDeleted = ste.executeUpdate(requete);
            if (rowsDeleted > 0) {
                System.out.println("Service was successfully deleted");
                return true;
            } else {
                System.out.println("Failed to delete the service");
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MyService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Failed to delete the service");
            return false;
        }
    }

    public void modifierDemande(Demande d, String id) {
        String requete = "UPDATE demande SET nom_demandeur = '" + d.getNom_demandeur() + "', prenom_demandeur = '" + d.getPrenom_demandeur() + "', date_demande = '" + d.getDate_demande() + "', id_serv = '" + d.getId_serv() + "' WHERE nom_demandeur = '" + id + "'";
        try {
            ste = connection.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(MyDemande.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    public void sendEmailNotif(String recipient, Service s) {
//        String from = USER_NAME;
//        String pass = PASSWORD;
//        String[] to = {recipient}; // list of recipient email addresses
//        String subject = "Votre demande a ete bien effectuée";
//        String body = "Vous avez demandé le service  <b>" + s.getTitre() + "</b> <br/>" + s.getDescription();
//        sendFromGMail(from, pass, to, subject, body);
//    }
//
//    private static void sendFromGMail(String from, String pass, String[] to, String subject, String body) {
//        Properties props = System.getProperties();
//        String host = "smtp.gmail.com";
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.host", host);
//        props.put("mail.smtp.user", from);
//        props.put("mail.smtp.password", pass);
//        props.put("mail.smtp.port", "587");
//        props.put("mail.smtp.auth", "true");
//
//        Session session = Session.getDefaultInstance(props);
//        MimeMessage message = new MimeMessage(session);
//
//        try {
//            message.setFrom(new InternetAddress(from));
//            InternetAddress[] toAddress = new InternetAddress[to.length];
//
//            // To get the array of addresses
//            for (int i = 0; i < to.length; i++) {
//                toAddress[i] = new InternetAddress(to[i]);
//            }
//
//            for (int i = 0; i < toAddress.length; i++) {
//                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
//            }
//
//            message.setSubject(subject);
//            message.setText(body);
//            Transport transport = session.getTransport("smtp");
//            transport.connect(host, from, pass);
//            transport.sendMessage(message, message.getAllRecipients());
//            transport.close();
//        } catch (AddressException ae) {
//            ae.printStackTrace();
//        } catch (MessagingException me) {
//            me.printStackTrace();
//        }
//    }

    public ObservableList<Demande> DemandeByUser(int idUser) {
        ObservableList<Demande> list = FXCollections.observableArrayList();
        String requete = "select * from demande where idUser= " + idUser;
        Statement st;
        ResultSet rs;

        try {
            ste = connection.createStatement();
            rs = ste.executeQuery(requete);
            while (rs.next()) {
                list.add(new Demande(rs.getString("nom_demandeur"), rs.getString("prenom_demandeur"), rs.getString("email_demandeur"), rs.getString("date_demande"), rs.getInt("id_serv")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(MyService.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("azsazsaz"+list);
        return list;
    }

    
    
     public void sendMail(String reponse , String mail ) throws Exception {
        String mailContent = "<!DOCTYPE html>\n"
                + "\n"
                + "<html lang=\"en\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" xmlns:v=\"urn:schemas-microsoft-com:vml\">\n"
                + "\n"
                + "<head>\n"
                + "	<title></title>\n"
                + "	<meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\" />\n"
                + "	<meta content=\"width=device-width, initial-scale=1.0\" name=\"viewport\" />\n"
                + "	<!--[if mso]><xml><o:OfficeDocumentSettings><o:PixelsPerInch>96</o:PixelsPerInch><o:AllowPNG/></o:OfficeDocumentSettings></xml><![endif]-->\n"
                + "	<!--[if !mso]><!-->\n"
                + "	<link href=\"https://fonts.googleapis.com/css?family=Lato\" rel=\"stylesheet\" type=\"text/css\" />\n"
                + "	<!--<![endif]-->\n"
                + "	<style>\n"
                + "		* {\n"
                + "			box-sizing: border-box;\n"
                + "		}\n"
                + "\n"
                + "		body {\n"
                + "			margin: 0;\n"
                + "			padding: 0;\n"
                + "		}\n"
                + "\n"
                + "		a[x-apple-data-detectors] {\n"
                + "			color: inherit !important;\n"
                + "			text-decoration: inherit !important;\n"
                + "		}\n"
                + "\n"
                + "		#MessageViewBody a {\n"
                + "			color: inherit;\n"
                + "			text-decoration: none;\n"
                + "		}\n"
                + "\n"
                + "		p {\n"
                + "			line-height: inherit\n"
                + "		}\n"
                + "\n"
                + "		@media (max-width:670px) {\n"
                + "			.icons-inner {\n"
                + "				text-align: center;\n"
                + "			}\n"
                + "\n"
                + "			.icons-inner td {\n"
                + "				margin: 0 auto;\n"
                + "			}\n"
                + "\n"
                + "			.row-content {\n"
                + "				width: 100% !important;\n"
                + "			}\n"
                + "\n"
                + "			.column .border {\n"
                + "				display: none;\n"
                + "			}\n"
                + "\n"
                + "			.stack .column {\n"
                + "				width: 100%;\n"
                + "				display: block;\n"
                + "			}\n"
                + "		}\n"
                + "	</style>\n"
                + "</head>\n"
                + "\n"
                + "<body style=\"background-color: #F5F5F5; margin: 0; padding: 0; -webkit-text-size-adjust: none; text-size-adjust: none;\">\n"
                + "	<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"nl-container\" role=\"presentation\"\n"
                + "		style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #F5F5F5;\" width=\"100%\">\n"
                + "		<tbody>\n"
                + "			<tr>\n"
                + "				<td>\n"
                + "					<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-1\"\n"
                + "						role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"
                + "						<tbody>\n"
                + "							<tr>\n"
                + "								<td>\n"
                + "									<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n"
                + "										class=\"row-content stack\" role=\"presentation\"\n"
                + "										style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 650px;\"\n"
                + "										width=\"650\">\n"
                + "										<tbody>\n"
                + "											<tr>\n"
                + "												<td class=\"column column-1\"\n"
                + "													style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; padding-top: 5px; padding-bottom: 5px; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\"\n"
                + "													width=\"100%\">\n"
                + "													<div class=\"spacer_block\"\n"
                + "														style=\"height:30px;line-height:30px;font-size:1px;\"> </div>\n"
                + "												</td>\n"
                + "											</tr>\n"
                + "										</tbody>\n"
                + "									</table>\n"
                + "								</td>\n"
                + "							</tr>\n"
                + "						</tbody>\n"
                + "					</table>\n"
                + "					<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-2\"\n"
                + "						role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"
                + "						<tbody>\n"
                + "							<tr>\n"
                + "								<td>\n"
                + "									<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n"
                + "										class=\"row-content stack\" role=\"presentation\"\n"
                + "										style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #D6E7F0; color: #000000; width: 650px;\"\n"
                + "										width=\"650\">\n"
                + "										<tbody>\n"
                + "											<tr>\n"
                + "												<td class=\"column column-1\"\n"
                + "													style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; padding-left: 25px; padding-right: 25px; padding-top: 5px; padding-bottom: 60px; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\"\n"
                + "													width=\"100%\">\n"
                + "													<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"text_block\"\n"
                + "														role=\"presentation\"\n"
                + "														style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\"\n"
                + "														width=\"100%\">\n"
                + "														<tr>\n"
                + "															<td\n"
                + "																style=\"padding-left:15px;padding-right:10px;padding-top:20px;\">\n"
                + "																<div style=\"font-family: sans-serif\">\n"
                + "																	<div\n"
                + "																		style=\"font-size: 12px; font-family: Lato, Tahoma, Verdana, Segoe, sans-serif; mso-line-height-alt: 18px; color: #052d3d; line-height: 1.5;\">\n"
                + "																		<p\n"
                + "																			style=\"margin: 0; font-size: 14px; text-align: center; mso-line-height-alt: 75px;\">\n"
                + "																			<span style=\"font-size:50px;\"><strong><span\n"
                + "																						style=\"font-size:50px;\"><span\n"
                + "																							style=\"font-size:38px;\">WELCOME\n"
                + "																							TO\n"
                + "																							ROBAFIKIA</span></span></strong></span>\n"
                + "																		</p>\n"
                + "																		<p\n"
                + "																			style=\"margin: 0; font-size: 14px; text-align: center; mso-line-height-alt: 51px;\">\n"
                + "																			<span style=\"font-size:34px;\"><strong><span\n"
                + "																						style=\"font-size:34px;\"><span\n"
                + "																							style=\"color:#2190e3;font-size:34px;\">"+reponse+"</span></span></strong></span>\n"
                + "																		</p>\n"
                + "																	</div>\n"
                + "																</div>\n"
                + "															</td>\n"
                + "														</tr>\n"
                + "													</table>\n"
                + "													<table border=\"0\" cellpadding=\"10\" cellspacing=\"0\"\n"
                + "														class=\"text_block\" role=\"presentation\"\n"
                + "														style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\"\n"
                + "														width=\"100%\">\n"
                + "														<tr>\n"
                + "															<td>\n"
                + "																<div style=\"font-family: sans-serif\">\n"
                + "																	<div\n"
                + "																		style=\"font-size: 12px; mso-line-height-alt: 14.399999999999999px; color: #555555; line-height: 1.2; font-family: Lato, Tahoma, Verdana, Segoe, sans-serif;\">\n"
                + "																		<p\n"
                + "																			style=\"margin: 0; font-size: 14px; text-align: center;\">\n"
                + "																			<span\n"
                + "																				style=\"font-size:18px;color:#000000;\">Thanks\n"
                + "																				for purchasing our service.</span></p>\n"
                + "																		<p\n"
                + "																			style=\"margin: 0; font-size: 14px; text-align: center;\">\n"
                + "																			<span\n"
                + "																				style=\"font-size:18px;color:#000000;\">This\n"
                + "																				mail contains all the infos (do no\n"
                + "																				reply).</span></p>\n"
                + "																		<p\n"
                + "																			style=\"margin: 0; font-size: 14px; text-align: center;\">\n"
                + "																			<span\n"
                + "																				style=\"font-size:18px;color:#000000;\"><br>\n"
                + "																				"+"<br>Purchase Date : "+Date.valueOf(LocalDate.now())+"<br>Expiration Date : "+Date.valueOf(java.time.LocalDate.now().plusYears(1))+"<br> </span></p>\n"
                + "																		<p\n"
                + "																			style=\"margin: 0; font-size: 14px; text-align: center;\">\n"
                + "																			<span\n"
                + "																				style=\"font-size:18px;color:#000000;\"><br>Details:<br> Company name : "+"ROBAFIKIA"+"</span>\n"
                + "																		</p>\n"
                + "																		<p\n"
                + "																			style=\"margin: 0; font-size: 14px; text-align: center;\">\n"
                + "																			<span\n"
                + "																				style=\"font-size:18px;color:#000000;\"><br>Purchased\n"
                + "																				by : "+reponse+"</span></p>\n"
                + "																		<p\n"
                + "																			style=\"margin: 0; font-size: 14px; text-align: center; mso-line-height-alt: 14.399999999999999px;\">\n"
                + "																			 </p>\n"
                + "																	</div>\n"
                + "																</div>\n"
                + "															</td>\n"
                + "														</tr>\n"
                + "													</table>\n"
                + "												</td>\n"
                + "											</tr>\n"
                + "										</tbody>\n"
                + "									</table>\n"
                + "								</td>\n"
                + "							</tr>\n"
                + "						</tbody>\n"
                + "					</table>\n"
                + "					<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-3\"\n"
                + "						role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"
                + "						<tbody>\n"
                + "							<tr>\n"
                + "								<td>\n"
                + "									<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n"
                + "										class=\"row-content stack\" role=\"presentation\"\n"
                + "										style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 650px;\"\n"
                + "										width=\"650\">\n"
                + "										<tbody>\n"
                + "											<tr>\n"
                + "												<td class=\"column column-1\"\n"
                + "													style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; padding-top: 20px; padding-bottom: 60px; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\"\n"
                + "													width=\"100%\">\n"
                + "													<table border=\"0\" cellpadding=\"10\" cellspacing=\"0\"\n"
                + "														class=\"text_block\" role=\"presentation\"\n"
                + "														style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\"\n"
                + "														width=\"100%\">\n"
                + "														<tr>\n"
                + "															<td>\n"
                + "																<div style=\"font-family: sans-serif\">\n"
                + "																	<div\n"
                + "																		style=\"font-size: 12px; mso-line-height-alt: 18px; color: #555555; line-height: 1.5; font-family: Lato, Tahoma, Verdana, Segoe, sans-serif;\">\n"
                + "																		<p\n"
                + "																			style=\"margin: 0; font-size: 14px; text-align: center;\">\n"
                + "																			MasterHR © -  Your favorite company tool.\n"
                + "																		</p>\n"
                + "																		<p\n"
                + "																			style=\"margin: 0; font-size: 14px; text-align: center;\">\n"
                + "																			Tunis, Tunisia.</p>\n"
                + "																	</div>\n"
                + "																</div>\n"
                + "															</td>\n"
                + "														</tr>\n"
                + "													</table>\n"
                + "													<table border=\"0\" cellpadding=\"10\" cellspacing=\"0\"\n"
                + "														class=\"divider_block\" role=\"presentation\"\n"
                + "														style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\"\n"
                + "														width=\"100%\">\n"
                + "														<tr>\n"
                + "															<td>\n"
                + "																<div align=\"center\">\n"
                + "																	<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n"
                + "																		role=\"presentation\"\n"
                + "																		style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\"\n"
                + "																		width=\"60%\">\n"
                + "																		<tr>\n"
                + "																			<td class=\"divider_inner\"\n"
                + "																				style=\"font-size: 1px; line-height: 1px; border-top: 1px dotted #C4C4C4;\">\n"
                + "																				<span> </span></td>\n"
                + "																		</tr>\n"
                + "																	</table>\n"
                + "																</div>\n"
                + "															</td>\n"
                + "														</tr>\n"
                + "													</table>\n"
                + "													<table border=\"0\" cellpadding=\"10\" cellspacing=\"0\"\n"
                + "														class=\"text_block\" role=\"presentation\"\n"
                + "														style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\"\n"
                + "														width=\"100%\">\n"
                + "														<tr>\n"
                + "															<td>\n"
                + "																<div style=\"font-family: sans-serif\">\n"
                + "																	<div\n"
                + "																		style=\"font-size: 12px; mso-line-height-alt: 14.399999999999999px; color: #4F4F4F; line-height: 1.2; font-family: Lato, Tahoma, Verdana, Segoe, sans-serif;\">\n"
                + "																		<p\n"
                + "																			style=\"margin: 0; font-size: 12px; text-align: center;\">\n"
                + "																			<span style=\"font-size:14px;\"><strong>Support\n"
                + "																					:\n"
                + "																					masterhrcomapny@gmail.com</strong></span>\n"
                + "																		</p>\n"
                + "																	</div>\n"
                + "																</div>\n"
                + "															</td>\n"
                + "														</tr>\n"
                + "													</table>\n"
                + "												</td>\n"
                + "											</tr>\n"
                + "										</tbody>\n"
                + "									</table>\n"
                + "								</td>\n"
                + "							</tr>\n"
                + "						</tbody>\n"
                + "					</table>\n"
                + "					<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-4\"\n"
                + "						role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"
                + "						<tbody>\n"
                + "							<tr>\n"
                + "								<td>\n"
                + "									<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n"
                + "										class=\"row-content stack\" role=\"presentation\"\n"
                + "										style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 650px;\"\n"
                + "										width=\"650\">\n"
                + "										<tbody>\n"
                + "											<tr>\n"
                + "												<td class=\"column column-1\"\n"
                + "													style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; padding-top: 5px; padding-bottom: 5px; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\"\n"
                + "													width=\"100%\">\n"
                + "													<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n"
                + "														class=\"icons_block\" role=\"presentation\"\n"
                + "														style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\"\n"
                + "														width=\"100%\">\n"
                + "														<tr>\n"
                + "															<td\n"
                + "																style=\"vertical-align: middle; color: #9d9d9d; font-family: inherit; font-size: 15px; padding-bottom: 5px; padding-top: 5px; text-align: center;\">\n"
                + "																<table cellpadding=\"0\" cellspacing=\"0\"\n"
                + "																	role=\"presentation\"\n"
                + "																	style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\"\n"
                + "																	width=\"100%\">\n"
                + "																	<tr>\n"
                + "																		<td\n"
                + "																			style=\"vertical-align: middle; text-align: center;\">\n"
                + "																			<!--[if vml]><table align=\"left\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"display:inline-block;padding-left:0px;padding-right:0px;mso-table-lspace: 0pt;mso-table-rspace: 0pt;\"><![endif]-->\n"
                + "																			<!--[if !vml]><!-->\n"
                + "																			<table cellpadding=\"0\" cellspacing=\"0\"\n"
                + "																				class=\"icons-inner\" role=\"presentation\"\n"
                + "																				style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; display: inline-block; margin-right: -4px; padding-left: 0px; padding-right: 0px;\">\n"
                + "																				<!--<![endif]-->\n"
                + "																				<tr>\n"
                + "																				</tr>\n"
                + "																			</table>\n"
                + "																		</td>\n"
                + "																	</tr>\n"
                + "																</table>\n"
                + "															</td>\n"
                + "														</tr>\n"
                + "													</table>\n"
                + "												</td>\n"
                + "											</tr>\n"
                + "										</tbody>\n"
                + "									</table>\n"
                + "								</td>\n"
                + "							</tr>\n"
                + "						</tbody>\n"
                + "					</table>\n"
                + "				</td>\n"
                + "			</tr>\n"
                + "		</tbody>\n"
                + "	</table><!-- End -->\n"
                + "</body>\n"
                + "\n"
                + "</html>";
        String myAccountEmail = "roubafikaservice@gmail.com";
        String password = "pbmtdethvxelgsdl";
        System.out.println("Preparing to send email");
        Properties p = new Properties();

        p.put("mail.smtp.auth", "true");
        p.put("mail.smtp.ssl.enable", "true");
        p.put("mail.smtp.host", "smtp.gmail.com");
        p.put("mail.smtp.port", "465");

        Session session = Session.getInstance(p, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        Message message = prepareMessage(session, myAccountEmail,mail, mailContent);

        Transport.send(message);
        System.out.println("Message sent successfully");

    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recipient , String mailContent) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("Welcome to ROBAFIKIA");
            message.setContent(mailContent, "text/html");
            return message;
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

}
