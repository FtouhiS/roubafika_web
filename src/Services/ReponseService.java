package Services;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Iservices.IReponseService;
import Entities.Reponse;
import Iservices.IServiceReclamation;
import Entities.Reclamation;
import Utils.DataSource;

public class ReponseService implements IReponseService<Reponse> {

	private Connection cnx;

	public ReponseService(){
		cnx = DataSource.getInstance().getConnection();
	}

	@Override
	public void createOne(Reponse reponse) throws SQLException {
		String req = "INSERT INTO `reponse`(`Idrep`, `DescriptionRep`, `id_rec`)" +
				" VALUES ('"+reponse.getIdrep()+"', '"+reponse.getDescriptionRep()+"', '"+reponse.getId_rec()+"')";
		System.out.println(req);
		Statement st = cnx.createStatement();
		st.executeUpdate(req);
		System.out.println("Reponse ajouté !");
	}


	//The (?,?,?) syntax is a placeholder syntax used in a prepared statement to represent values that will be inserted into the database at runtime. Each ? corresponds to a single value that will be set using the set methods on the prepared statement.

	//If you want to insert specific values into the database instead of using placeholders, you can replace the (?,?,?) syntax with a comma-separated list of values wrapped in parentheses

	@Override
	public void updateOne(Reponse reponse) throws SQLException {
		String sql = "UPDATE reponse SET  `DescriptionRep`=? WHERE `Idrep`=?";
		PreparedStatement pstmt = cnx.prepareStatement(sql);
		pstmt.setString(1, reponse.getDescriptionRep());
		int rowsUpdated = pstmt.executeUpdate();
		if (rowsUpdated > 0) {
			System.out.println("Reponse updated successfully");
		} else {
			System.out.println("Reponse update failed");
		}
	}
	
	
	public void updateOne1(Reponse reponse) throws SQLException {
		String sql = "UPDATE reponse SET `Description`='" + reponse.getDescriptionRep() +  "' WHERE `Idrep`=" + reponse.getIdrep();
		PreparedStatement pstmt = cnx.prepareStatement(sql);
		pstmt.executeUpdate();
		System.out.println("nouveau mise à jour !");
	}





	@Override
	public void deletOne(int id) throws SQLException {

		String req = "DELETE FROM reponse WHERE Idrep = ?";
		PreparedStatement st = cnx.prepareStatement(req);
		st.setInt(1, id);
		st.executeUpdate();
		System.out.println("reponse Supprimee !");


	}


	public List<Reponse> afficherReponse() {
		List<Reponse> list = new ArrayList<>();
		try {
			String req = "Select * from reponse";
			Statement st = cnx.createStatement();

			ResultSet RS= st.executeQuery(req);
			while(RS.next()){
				Reponse r  = new Reponse();
				r.setIdrep(RS.getInt(1));
				
				r.setDescriptionRep(RS.getString(2));
				list.add(r);
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

		return list;
	}

	@Override
	public List<Reponse> selectAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

    






	


}
