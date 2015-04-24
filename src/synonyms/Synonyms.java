/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package synonyms;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.Species;
import similarity.SimilarMethString;

/**
 *
 * @author Mathialakan.Thavappi
 */
public class Synonyms {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        Synonyms synonyms = new Synonyms();
        //writeList(synonyms.read("C:\\Users\\Mathialakan.Thavappi\\Documents\\ResearchWork\\TestData\\test4.txt"),"C:\\Users\\Mathialakan.Thavappi\\Documents\\ResearchWork\\TestData\\test4True.txt");
        String fileOne = "C:\\Users\\Mathialakan.Thavappi\\Documents\\ResearchWork\\Yeast\\iND750.xml"; //args[0];
        String fileTwo = "C:\\Users\\Mathialakan.Thavappi\\Documents\\ResearchWork\\Yeast\\iFF708.xml"; //args[1];

//        synonyms.getMetaboliteList(fileOne);
//        synonyms.getMetaboliteList(fileTwo);
//
//        synonyms.write4(synonyms.getMetaboliteList(fileOne), synonyms.getMetaboliteList(fileTwo));


        String filenameOne;
        String filenameTwo;

        ArrayList<Metabolite> metaboliteSynonymListOne = synonyms.getmetaboliteSynonymList(fileOne);
        //synonyms.write(metaboliteSynonymListOne, "C:\\Users\\Mathialakan.Thavappi\\Documents\\ResearchWork\\Yeast\\iND750_syn.txt");
//         ExcelFile excelFileOne = new ExcelFile("C:\\Users\\Mathialakan.Thavappi\\Documents\\ResearchWork\\Yeast\\iND750_syn.xls");
//         if (excelFileOne.create()) {
//         System.out.println(" excel 1 is successfully created");
//         }
//         excelFileOne.CreateMetaboliteSheet(metaboliteSynonymListOne);

        ArrayList<Metabolite> metaboliteSynonymListTwo = synonyms.getmetaboliteSynonymList(fileTwo);
        //synonyms.write(metaboliteSynonymListTwo, "C:\\Users\\Mathialakan.Thavappi\\Documents\\ResearchWork\\Yeast\\iFF708_syn.txt");
//         ExcelFile excelFileTwo = new ExcelFile("C:\\Users\\Mathialakan.Thavappi\\Documents\\ResearchWork\\Yeast\\iFF708_syn.xls");
//         if (excelFileTwo.create()) {
//         System.out.println(" excel 2 is successfully created");
//         }
//         excelFileTwo.CreateMetaboliteSheet(metaboliteSynonymListTwo);

        ArrayList<MatchMetabolites> mmList = synonyms.compareMetaboliteList(metaboliteSynonymListOne, metaboliteSynonymListTwo);
         synonyms.write(mmList);
//         ExcelFile excelFileMatch = new ExcelFile("C:\\Users\\Mathialakan.Thavappi\\Documents\\ResearchWork\\Yeast\\match.xls");
//         if (excelFileMatch.create()) {
//         System.out.println(" excel is successfully created");
//         }
//         excelFileMatch.CreateMetaboliteMatchSheet(mmList);*/
    }

    public ArrayList<MatchMetabolites> compareMetaboliteList(ArrayList<Metabolite> metaboliteSynonymListOne, ArrayList<Metabolite> metaboliteSynonymListTwo) {
        ArrayList<MatchMetabolites> mMetaList = new ArrayList<MatchMetabolites>();
        for (Metabolite meta1 : metaboliteSynonymListOne) {
            for (Metabolite meta2 : metaboliteSynonymListTwo) {
                MatchMetabolites mMeta = new MatchMetabolites();
                mMeta.setMetaboliteOne(meta1.getName());
                mMeta.setMetaboliteTwo(meta2.getName());
                if (compareMetabolite(meta1, meta2)) {
                    mMeta.setMatch(true);
                } else {
                    mMeta.setMatch(false);
                }
                mMetaList.add(mMeta);
            }
        }
        return mMetaList;
    }

    public Boolean compareMetabolite(Metabolite meta1, Metabolite meta2) {
        if (meta1.getName() != null && meta2.getName() != null && meta1.getName().equals(meta2.getName())) {
            return true;
        }
        for (String syn1 : meta1.getSynonymList()) {
            for (String syn2 : meta2.getSynonymList()) {
                if (syn1.equals(syn2)) {
                    return true;
                }
            }
        }
        return false;
    }

    public ArrayList<String> getMetaboliteList(String fileName) {
        SBMLDocument SBMLDoc = new FileCtrl().read(fileName);
        ArrayList<String> metaboliteList = new ArrayList<String>();
        if (SBMLDoc != null) {
            Model model = SBMLDoc.getModel();
            System.out.println("number of metabolites  : " + model.getNumSpecies());
            for (Species sp : model.getListOfSpecies()) {
                String str =sp.getName().trim();
                if (str.charAt(0) == '_') {
                    str = str.replaceFirst("_", "");
                }
                metaboliteList.add(str);
            }
        }
        return metaboliteList;
    }

    public ArrayList<String> getSynonymList(String metabolite) {
        ArrayList<String> synonymList = null;
        SynonymWeb sBMLWeb = new SynonymWeb();
        String KEGG = sBMLWeb.getString("http://rest.kegg.jp//find/compound/" + metabolite).trim();
        //System.out.println("kegg:" + KEGG+"123");
        if (!KEGG.equals("")) {
            Scanner scan = new Scanner(KEGG);
            String KEGGId = "";
            if (scan.hasNext()) {
                KEGGId = scan.next();
            }

            KEGGId = KEGG.substring(KEGG.indexOf(":") + 1, KEGG.indexOf(":") + 7);
            System.out.println("kegg: " + KEGGId);
            String cyc = sBMLWeb.getString("http://websvc.biocyc.org/META/foreignid?ids=" + KEGGId).trim();
            System.out.println("cycID: " + cyc);
            if (cyc != null) {
                scan = new Scanner(cyc);

                String cycId = "";
                int pos = 0;
                while (pos < 2) {
                    scan.hasNext();
                    scan.next();
                    pos++;
                }
                if (scan.hasNext()) {
                    cycId = scan.next();
                }

                System.out.println("cycID: " + cycId);
                String cycXML = sBMLWeb.getString("http://websvc.biocyc.org/getxml?id=META:" + cycId).trim();
                //System.out.println("id: " + cycXML);
                synonymList = new ReadXML().read(cycXML, "synonym");
                for (String syn : synonymList) {
                     System.out.println("syn: " + syn);
                }
            }
        }
        return synonymList;
    }

    public ArrayList<Metabolite> getmetaboliteSynonymList(String file) {
        ArrayList<String> metaboliteList = getMetaboliteList(file);
        ArrayList<Metabolite> metaboliteSynonymList = new ArrayList<Metabolite>();
        for (String metabolite : metaboliteList) {
            Metabolite met = new Metabolite();
            //System.out.println("metabolite: " + metabolite);
            metabolite = metabolite.trim();
            //Character first = metabolite.charAt(0); 
            if (metabolite.charAt(0) == '_') {
                metabolite = metabolite.substring(1);
            }
            met.setName(metabolite);
            if (getSynonymList(metabolite) != null) {
                met.setSynonymList(getSynonymList(metabolite));
            }
            metaboliteSynonymList.add(met);
        }
        return metaboliteSynonymList;
    }

    public void write(ArrayList<Metabolite> list, String fname) {

        FileWriter writer;
        try {
            writer = new FileWriter(fname);
            writer.write("metabolite" + "\t" + "Synonyms" + "\n");
            System.out.println("metabolite");
            for (Metabolite met : list) {
                System.out.println(met.getName());
                writer.write(met.getName() + "\n");
                if (met.getNumberOfSynonyms() > 0) {
                    for (String syn : met.getSynonymList()) {
                        System.out.println("syn " + syn);
                        writer.write("\t\t" + syn + "\n");
                    }
                }
            }
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void write(ArrayList<MatchMetabolites> list) {

        FileWriter writer;
        String fname = "C:\\Users\\Mathialakan.Thavappi\\Documents\\ResearchWork\\TestData\\match.txt";
        try {
            writer = new FileWriter(fname);
            writer.write("metabolite One" + "\t" + "metabolite two" + "\t" + "similar" + "\n");
            for (MatchMetabolites met : list) {
                writer.write(met.getMetaboliteOne() + "\t" + met.getMetaboliteTwo() + "\t" + met.isMatch() + "\n");

            }
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void write4(ArrayList<String> str1, ArrayList<String> str2) {
        SimilarMethString sim = new SimilarMethString();
        FileWriter writer;
        try {
            writer = new FileWriter("C:\\Users\\Mathialakan.Thavappi\\Documents\\ResearchWork\\Yeast\\Abios6.txt");
            writer.write("String A" + "\t" + "String B" + "\t" + "Distance" + "\t" + "Ratio" + "\t" + "Similar" + "\t" + "\n");
            for (int j = 500; j < 600; j++) {
                for (int i = 0; i < 1; i++) {
                    String met1 = str1.get(j);
                    String met2 = str2.get(i);
                    int d = sim.myDistance(met1.trim(), met2.trim());
                    double r = sim.getRatio();
                    boolean same = false;
                    if (d <= 5 && r >= 0.09) {
                        same = true;
                    }
                    writer.write(met1 + "\t" + met2 + "\t" + d + "\t" + r + same + "\t" + "\n");
                }
            }
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public ArrayList<String> read(String name) throws FileNotFoundException {
        ArrayList<String> list = new ArrayList<String>();
        Scanner scan = new Scanner(new File(name));
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            if (line.contains("true")) {
                list.add(line);
            }
        }

        return list;

    }

    public static void writeList(ArrayList<String> list, String fname) {

        FileWriter writer;
        try {
            writer = new FileWriter(fname);

            for (String met : list) {

                writer.write(met + "\n");
            }
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
