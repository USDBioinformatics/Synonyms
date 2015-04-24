/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package synonyms;

import java.util.ArrayList;

/**
 *
 * @author Mathialakan.Thavappi
 */
public class Metabolite {
    String name;
    ArrayList<String> synonymList ;

    public Metabolite() {
        synonymList = new ArrayList<String>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getSynonymList() {
        return synonymList;
    }

    public void setSynonymList(ArrayList<String> synonymList) {
        this.synonymList = synonymList;
    }
    
    public int getNumberOfSynonyms() {
        return synonymList.size();
    }
}
