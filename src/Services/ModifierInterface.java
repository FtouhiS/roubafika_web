/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;
import java.util.List;
import Entities.Utilisateur;

/**
 *
 * @author Soulaima
 */
public interface ModifierInterface {
    public List<Utilisateur> SearchByName(String name);
    public List<Utilisateur> sortByName() ;

}