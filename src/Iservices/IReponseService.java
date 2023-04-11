/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Iservices;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author asus
 */
public interface IReponseService<T> {
    void createOne(T t) throws SQLException;
    void updateOne(T t) throws SQLException;
    void deletOne(int id) throws SQLException;
    List<T> selectAll() throws SQLException;
}
