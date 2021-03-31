/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author Mahdi
 */
// appeler lorsque le manager football ajoute au tab stats
public class ServiceScore {
        public void calculeScore (int but,int assist,boolean cl,int y,int r)
        {
            int s=0;
            s+=(but*5)+(assist*3)+y*(-2)+r*(-4);
            if(cl){
                s+=5;
            }
                    System.out.println(s);
        }
}
