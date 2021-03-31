/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;


import entites.Publication;
import java.util.List;

/**
 *
 * @author PC
 */
public interface IPubService {

    public void ajouter(Publication p);

    public void deleteVideo(Publication p);

    public List<Publication> afficherVideo();

    public void calculatereportspermonth();
    
    public void updatePub(Publication p);
}
