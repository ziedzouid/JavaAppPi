/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import models.Evaluation;
import interfaces.IService;

/**
 *
 * @author Shil Mohamed <mohamedshil at esprit.tn>
 */
public interface IEvaluationService extends IService<Evaluation, Integer> {


    List<Evaluation> getByTrajet(Integer r);
    List<Evaluation> getByUser(Integer r);
    Evaluation SearchByTrajetAndUserId(Integer r,Integer r1);
    public List<Evaluation> getByConduteur();
    

}
