package Iservices;
import java.sql.SQLException;
import java.util.List;
import Entities.Reclamation;

public interface IServiceReclamation<T> {
	
	void createOne(T t) throws SQLException;
    void updateOne(T t) throws SQLException;
    void deletOne(int id) throws SQLException;
    List<T> selectAll() throws SQLException;
    public Reclamation findById(int idRec) throws SQLException;

}
