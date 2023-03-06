/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import entities.Client;
import services.ClientService;

/**
 *
 * @author ep
 */
public class Test {

    public static void main(String[] args) {
        ClientService cs=new ClientService();
       cs.create(new Client("Kamali","Kamal","0444444444","alaoui@gmail.com"));
        
        for(Client c:cs.findAll()){
            System.out.println(c);
        }
        
    }
}
