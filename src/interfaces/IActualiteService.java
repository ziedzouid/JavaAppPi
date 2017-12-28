/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import models.Actualite;

/**
 *
 * @author ASUS
 */
public interface IActualiteService extends IService<Actualite, Integer>{
    List<Actualite> getActualitiesByCircle(Integer circleid);
    List<Actualite> getActualitiesByUser(Integer userid);
       
}
