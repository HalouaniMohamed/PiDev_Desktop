/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;

/**
 *
 * @author ASUS
 */
<<<<<<<< HEAD:src/services/EventInterface.java
public interface EventInterface<T> {
     
      public List<T> afficher();
      
      
========
public interface CRUDInterfaceHamma<T> {

    public void add(T p);

    public List<T> show();

    public void delete(T p);
>>>>>>>> master:src/services/CRUDInterfaceHamma.java
}
