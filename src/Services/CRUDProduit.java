/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Iservices.IServiceProduit;
import Entities.Produit;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Iservices.IServiceProduit;
import Utils.DataSource;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author USER
 */
public class CRUDProduit implements IServiceProduit{
    Statement ste;
    Connection conn = DataSource.getInstance().getConnection();
    
    
    /*public void ajouterproduit(Produit p) {
        try {
        ste = conn.createStatement();
        String req = "Insert into produit values(0,'"+p.getNom_produit()+"','"+p.getPrix()+"','"+p.getDescription()+"')";
        ste.executeUpdate(req);
        System.out.println("Produit ajouté");
    } catch (SQLException ex) {
            System.out.println("Produit non ajouté!!!!");    }
    }*/

    
    public void ajouterproduit(Produit p) {
       try {
            String req = "INSERT INTO `produit` (`IdUser`,`Categorie`,`nom_produit`,`Description`,`image`,`prix`) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setFloat(6, p.getPrix());
            ps.setBlob(5, p.getImage());
            ps.setString(4, p.getDescription());
            ps.setString(3, p.getNom_produit());
            ps.setString(2, p.getCategorie());
            ps.setInt(1, p.getId_user());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    public void modifierproduit(Produit p) {
       try {
            String req = "UPDATE `produit` SET `nom_produit` = '"+ p.getNom_produit()+ "',`Categorie` = '" +p.getCategorie()+ "',`description`='"+p.getDescription()+"',`id_user`='"+p.getId_user()+"',`prix`='"+p.getPrix()+"' WHERE `produit`.`id_produit` = " + p.getId_produit();
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Produit updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    public void supprimerproduit(int id_produit) {
        try {
            String req = "DELETE FROM `produit` WHERE id_produit = " + id_produit;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Produit deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    public List<Produit> afficherproduit() {
       List<Produit> prod = new ArrayList<Produit>();
        try {
            ste =conn.createStatement();
            String req = "SELECT * FROM `produit`";
        ResultSet result = ste.executeQuery(req);
        
        while (result.next()) {
            Produit resultProduit = new Produit(result.getInt("id_produit"),result.getInt("IdUser"), result.getString("Categorie"),result.getString("nom_produit"),result.getString("Description"),result.getBlob("image"),result.getFloat("prix"));
            System.out.println(resultProduit); 
            prod.add(resultProduit);
        }
               System.out.println("iciii") ;

        System.out.println(prod);
      
    } catch (SQLException ex) {
         System.out.println(ex);   
    }
   return prod;
    }
   
    
    public Produit getProduitByid (int n ){ 
        
        Produit prod = new Produit();
        try {
            ste =conn.createStatement();
            String req = "select * from produit where id_produit =  " + n;
            
            ResultSet result = ste.executeQuery(req);
            System.out.println("okkk"); 
        
        while (result.next()) {
            Produit resultProduit = new Produit(result.getInt("id_produit"),result.getInt("IdUser"), result.getString("Categorie"),result.getString("nom_produit"),result.getString("Description"),result.getBlob("image"),result.getFloat("prix"));
            System.out.println(resultProduit); 
            prod=resultProduit;
        }
        System.out.println("iciii") ;

        System.out.println(prod);
      
    } catch (SQLException ex) {
         System.out.println(ex);   
    }
   

return prod;
    }
    
    public int getByid (int n ){ 
    int t=0 ; 
    try {
            String requete = "select * from produit where ?= id_produit";
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setInt(1, n);
            ResultSet e = pst.executeQuery();
            while(e.next()){
            Produit pre = new Produit(); 
            
            pre.setId_produit(e.getInt("id_produit"));
            t=pre.getId_produit();   }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
   
return t ; 
    }
    
    public List<Produit> afficher_user_produit(int id) {
       List<Produit> prod = new ArrayList<Produit>();
        try {
            ste =conn.createStatement();
            String req = "SELECT * FROM `produit` WHERE IdUser = " + id;
        ResultSet result = ste.executeQuery(req);
        
        while (result.next()) {
            Produit resultProduit = new Produit(result.getInt("id_produit"),result.getInt("IdUser"), result.getString("Categorie"),result.getString("nom_produit"),result.getString("Description"),result.getBlob("image"),result.getFloat("prix"));
            System.out.println(resultProduit); 
            prod.add(resultProduit);
        }

        System.out.println(prod);
      
    } catch (SQLException ex) {
         System.out.println(ex);   
    }
   return prod;
    }
    
    
     public List<Produit> afficherproduitPrix() {
       List<Produit> prod = new ArrayList<Produit>();
        try {
            ste =conn.createStatement();
            String req = "SELECT * FROM `produit` WHERE prix > 0";
        ResultSet result = ste.executeQuery(req);
        
        while (result.next()) {
            Produit resultProduit = new Produit(result.getInt("id_produit"),result.getInt("IdUser"), result.getString("Categorie"),result.getString("nom_produit"),result.getString("Description"),result.getBlob("image"),result.getFloat("prix"));
            System.out.println(resultProduit); 
            prod.add(resultProduit);
        }
               System.out.println("iciii") ;

        System.out.println(prod);
      
    } catch (SQLException ex) {
         System.out.println(ex);   
    }
   return prod;
    }
     
     public List<Produit> afficherproduitParNom(String nom) {
       List<Produit> prod = new ArrayList<Produit>();
        try {
            ste =conn.createStatement();
            String req = "SELECT * FROM `produit` WHERE nom_produit = '"+nom+"'";
        ResultSet result = ste.executeQuery(req);
        
        while (result.next()) {
            Produit resultProduit = new Produit(result.getInt("id_produit"),result.getInt("IdUser"), result.getString("Categorie"),result.getString("nom_produit"),result.getString("Description"),result.getBlob("image"),result.getFloat("prix"));
            System.out.println(resultProduit); 
            prod.add(resultProduit);
        }
               System.out.println("iciii") ;

        System.out.println(prod);
      
    } catch (SQLException ex) {
         System.out.println(ex);   
    }
   return prod;
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
