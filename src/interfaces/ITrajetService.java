/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;
import java.util.List;
import models.Trajet;



/**
 *
 * @author achraf
 */
public interface ITrajetService extends IService<Trajet, Integer> {
    
       public List<Trajet> getByUser(Integer r);
}

