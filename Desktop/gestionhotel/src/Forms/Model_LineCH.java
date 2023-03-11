/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

/**
 *
 * @author Utilisateur
 */
class Model_LineCH {
    private String nch;
    private double nbrch;

    public Model_LineCH(String nch, double nbrch) {
        this.nch = nch;
        this.nbrch = nbrch;
    }

 

    public double getNbrch() {
        return nbrch;
    }

    public String getNch() {
        return nch;
    }
}
