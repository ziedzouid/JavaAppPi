/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import interfaces.IService;
import models.Voiture;

/**
 *
 * @author DELL
 */
public interface IVoitureServices extends IService<Voiture,Integer>{

    @Override
    public void add(Voiture t);

    @Override
    public List<Voiture> getAll();

    @Override
    public Voiture findById(Integer r);

    @Override
    public void update(Voiture t);

    @Override
    public void remove(Integer r);

  
    public Voiture search(Voiture t);
    
    
    
    
}
