/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import interfaces.IEvaluationService;
import interfaces.IPassagerService;
import interfaces.ITrajetService;
import interfaces.IUserService;
import models.Evaluation;
import models.Passager;
import models.Trajet;
import models.User;
import services.EvaluationService;
import services.PassagerService;
import services.TrajetService;
import services.UserService;

/**
 *
 * @author Shil Mohamed <mohamedshil at esprit.tn>
 */
public class VariablesGlobale {

    public static IUserService iUserService =new UserService();
    public static User user ;
    
    public static ITrajetService iTrajetService = new TrajetService();
    public static Trajet TrajetSelectionne ;

    public static IPassagerService ipassagerService = new PassagerService(); //unused
    public static Passager PassagerCourant ;

   public static IEvaluationService iEvaluationService = new EvaluationService();
   public static Evaluation evaluationCourante ;
   

  


}
