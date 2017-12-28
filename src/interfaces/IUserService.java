/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import interfaces.IService;
import java.util.List;
import models.User;
/**
 *
 * @author esprit
 */
public interface IUserService extends IService<User, Integer> {
    
    User findById2(Integer id);
    List<User> getAll2();
    
    
}
