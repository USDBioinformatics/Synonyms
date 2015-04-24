/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package synonyms;

/**
 *
 * @author Mathialakan.Thavappi
 */
public class MatchMetabolites {
    String metaboliteOne;
    String metaboliteTwo;
    boolean match;

    public String getMetaboliteOne() {
        return metaboliteOne;
    }

    public void setMetaboliteOne(String metaboliteOne) {
        this.metaboliteOne = metaboliteOne;
    }

    public String getMetaboliteTwo() {
        return metaboliteTwo;
    }

    public void setMetaboliteTwo(String metaboliteTwo) {
        this.metaboliteTwo = metaboliteTwo;
    }

    public boolean isMatch() {
        return match;
    }

    public void setMatch(boolean match) {
        this.match = match;
    }
}
