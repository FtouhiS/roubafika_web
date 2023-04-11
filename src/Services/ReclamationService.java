package Services;
import Iservices.IServiceReclamation;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import Entities.Reclamation;
import Utils.DataSource;

public class ReclamationService implements IServiceReclamation<Reclamation> {

	
        Statement ste;
        Connection cnx = DataSource.getInstance().getConnection();
	public ReclamationService(){
		cnx = DataSource.getInstance().getConnection();
	}

	@Override
	public void createOne(Reclamation reclamation) throws SQLException {

		// Check that sujet contains only letters and spaces
		if ((!reclamation.getSujet().matches("^[a-zA-Z ]*$"))||(reclamation.getSujet().isEmpty())) {
			throw new IllegalArgumentException("Sujet must only contain letters and spaces");
		}


		// Check that description is not too long
		int maxDescriptionLength = 1000;
		if (reclamation.getDescription().length() > maxDescriptionLength) {
			throw new IllegalArgumentException("Description must not exceed " + maxDescriptionLength + " characters");
		}


		/*	// Check that sujet is not empty
		if (reclamation.getSujet().isEmpty()) {
			throw new IllegalArgumentException("Sujet must not be empty");
		}*/

		// Check that description is not empty
		if (reclamation.getDescription().isEmpty()) {
			throw new IllegalArgumentException("Description must not be empty");
		}

		// Build the SQL query string
		String req = "INSERT INTO `reclamation`( `Date_ajout`, `Sujet`, `Description`, `id_user`)" +
				" VALUES ('"+reclamation.getDate_ajout()+"','"+reclamation.getSujet()+"','"+reclamation.getDescription()+"','"+reclamation.getId_user()+"')";
		// System.out.println(req);

		// Execute the SQL query
                System.out.println(cnx);
		Statement st = cnx.createStatement();
		st.executeUpdate(req);
		System.out.println("Reclamation ajouté !");
	}


	//The (?,?,?) syntax is a placeholder syntax used in a prepared statement to represent values that will be inserted into the database at runtime. Each ? corresponds to a single value that will be set using the set methods on the prepared statement.
	//If you want to insert specific values into the database instead of using placeholders, you can replace the (?,?,?) syntax with a comma-separated list of values wrapped in parentheses

	@Override
	public void updateOne(Reclamation reclamation) throws SQLException {
		String sql = "UPDATE reclamation SET `Date_ajout`=?, `Sujet`=?, `Description`=? , `id_user`=? WHERE `IdReclamation`=?";
		PreparedStatement pstmt = cnx.prepareStatement(sql);
		pstmt.setDate(1, reclamation.getDate_ajout());
		pstmt.setString(2, reclamation.getSujet());
		pstmt.setString(3, reclamation.getDescription());
                pstmt.setInt(4, reclamation.getId_user());
		pstmt.setInt(5, reclamation.getIdReclamation());
		int rowsUpdated = pstmt.executeUpdate();
		if (rowsUpdated > 0) {
			System.out.println("Reclamation updated successfully");
		} else {
			System.out.println("Reclamation update failed");
		}
	}


	public void updateOne1(Reclamation reclamation) throws SQLException {
		String sql = "UPDATE reclamation SET `Date_ajout`='" + reclamation.getDate_ajout() + "', `Sujet`='" + reclamation.getSujet() + "', `Description`='" + reclamation.getDescription() + "' WHERE `IdReclamation`=" + reclamation.getIdReclamation();
		PreparedStatement pstmt = cnx.prepareStatement(sql);
		pstmt.executeUpdate();
		System.out.println("Reclamation mise à jour !");
	}





	@Override
	public void deletOne(int id) throws SQLException {

		String req = "DELETE FROM reclamation WHERE IDreclamation = ?";
		PreparedStatement st = cnx.prepareStatement(req);
		st.setInt(1, id);
		st.executeUpdate();
		System.out.println("reclamation Supprimee !");


	}


	public List<Reclamation> afficherReclamation() {
		List<Reclamation> list = new ArrayList<>();
		try {
			String req = "Select * from reclamation";
			Statement st = cnx.createStatement();

			ResultSet RS= st.executeQuery(req);
			while(RS.next()){
				Reclamation r  = new Reclamation();
				r.setIdReclamation(RS.getInt(1));
				r.setDate_ajout(RS.getDate(2));
				r.setSujet(RS.getString(3));
				r.setDescription(RS.getString(4));
                                r.setId_user(RS.getInt(5));
				list.add(r);
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

		return list;
}

	@Override
	public List<Reclamation> selectAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	
        @Override
        public Reclamation findById(int idRec) throws SQLException {
        Reclamation resultRec=new Reclamation();
        PreparedStatement pre = cnx.prepareStatement("select * from `reclamation` WHERE IdReclamation=? ");
        pre.setInt(1, idRec);
        ResultSet result = pre.executeQuery();
        while (result.next()) {
            resultRec = new Reclamation(result.getInt("IdReclamation"), result.getDate("Date_ajout"), result.getString("Sujet"),result.getString("Description"),result.getInt("id_user"));
            
        }
        return resultRec;
    }

        public List<Reclamation> findBySujet(String sujet) {
		List<Reclamation> list = new ArrayList<>();
		try {
			String req = "SELECT * FROM reclamation WHERE Sujet LIKE '%"+sujet+"%'";
			Statement st = cnx.createStatement();

			ResultSet RS= st.executeQuery(req);
			while(RS.next()){
				Reclamation r  = new Reclamation();
				r.setIdReclamation(RS.getInt(1));
				r.setDate_ajout(RS.getDate(2));
				r.setSujet(RS.getString(3));
				r.setDescription(RS.getString(4));
                                r.setId_user(RS.getInt(5));
				list.add(r);
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

		return list;
}



}
