/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package synonyms;

import java.io.*;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import java.util.ArrayList;
import java.util.List;
//import sbmlchecker.Species;
//import sbmlchecker.Compartment;
//import sbmlchecker.Reaction;

/**
 *
 * @author Mathialakan.Thavappi
 */
public class ExcelFile {

    private String fileName;
    File file;
    // boolean exist;

    public ExcelFile() {
    }

    public ExcelFile(String fileName) {
        this.fileName = fileName;
        file = new File(fileName);
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean exists() {
        return file.exists();
    }

    public boolean delete() {
        return file.delete();
    }

    public void deleteOnExit() {
        file.deleteOnExit();
    }

    public boolean createNew() {
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean create() {
        if (exists()) {
            delete();
            file = new File(fileName);
        }
        return true;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void CreateMetaboliteSheet(File file, String fileName, String sheetName, int r, int c, ArrayList<String> title, List<Metabolite> data) {

        int columns = title.size();
        int records = data.size();
        System.out.println("1 Excel: ");
        int n = c, m = r;
        File myfile = file;//new File(fileName);
        String filename = fileName;
        HSSFWorkbook hwb = null;
        if (!myfile.exists()) {
            hwb = new HSSFWorkbook();
        } else {
            try {
                hwb = (HSSFWorkbook) WorkbookFactory.create(myfile);
            } catch (IOException | InvalidFormatException e) {
                e.printStackTrace();
            }
        }
        try {
            HSSFSheet sheet = hwb.createSheet(sheetName);
            System.out.println("12 Excel: ");
            HSSFRow rowhead = sheet.createRow((short) m);
            for (String head : title) {
                rowhead.createCell((short) n).setCellValue(head);
                n++;
            }
            System.out.println("123 Excel: ");
            int rb = ++m;
            for (Metabolite met : data) {
                n = c;
                if (met != null) {
                    HSSFRow rowbody = sheet.createRow((short) rb);
                    if (met.getName() != null) {
                        rowbody.createCell((short) n).setCellValue(met.getName());
                        n++;
                        rb++;
                    }
                    System.out.println("1234 Excel: ");
                    if (met.getNumberOfSynonyms() > 0) {
                        for (String syn : met.getSynonymList()) {
                            if (syn != null) {
                                rowbody = sheet.createRow((short) rb);
                                rowbody.createCell((short) n).setCellValue(syn);
                                //n++;
                                System.out.println(" Excel: " + syn);
                            }
                            rb++;
                        }
                    }
                } else {
                    System.out.println(" Excel: met is null ");
                }
            }

            FileOutputStream fileOut = new FileOutputStream(filename);
            hwb.write(fileOut);
            fileOut.close();
            if ((rb - (r + 1) == records) && (n - c == columns)) {
                System.out.println("Your excel file has been generated! with cells of" + (records * columns));
            }

        } catch (Exception ex) {
            System.out.println(ex);

        }
    }

    public void CreateMetaboliteSheet(String sheetName, int r, int c, ArrayList<String> title, List<Metabolite> data) {

        CreateMetaboliteSheet(file, fileName, sheetName, r, c, title, data);
    }

    public void CreateMetaboliteSheet(int r, int c, ArrayList<String> title, List<Metabolite> data) {

        CreateMetaboliteSheet(file, fileName, "metabolites", r, c, title, data);
    }

    public void CreateMetaboliteSheet(String sheetName, ArrayList<String> title, List<Metabolite> data) {

        CreateMetaboliteSheet(file, fileName, sheetName, 1, 1, title, data);
    }

    public void CreateMetaboliteSheet(ArrayList<String> title, List<Metabolite> data) {

        CreateMetaboliteSheet(file, fileName, "metabolites", 1, 1, title, data);
    }

    public void CreateMetaboliteSheet(List<Metabolite> data) {
        ArrayList<String> title = new ArrayList<String>();
        title.add("metabolite");
        title.add("synonyms");

        CreateMetaboliteSheet(file, fileName, "metabolites", 1, 1, title, data);
    }

    public void CreateMetaboliteMatchSheet(File file, String fileName, String sheetName, int r, int c, ArrayList<String> title, List<MatchMetabolites> data) {

        int columns = title.size();
        int records = data.size();
        System.out.println("1m Excel: ");
        int n = c, m = r;
        File myfile = file;//new File(fileName);
        String filename = fileName;
        HSSFWorkbook hwb = null;
        if (!myfile.exists()) {
            hwb = new HSSFWorkbook();
        } else {
            try {
                hwb = (HSSFWorkbook) WorkbookFactory.create(myfile);
            } catch (IOException | InvalidFormatException e) {
                e.printStackTrace();
            }
        }
        try {
            HSSFSheet sheet = hwb.createSheet(sheetName);
            System.out.println("12m Excel: ");
            HSSFRow rowhead = sheet.createRow((short) m);
            for (String head : title) {
                rowhead.createCell((short) n).setCellValue(head);
                n++;
            }
            System.out.println("123m Excel: ");
            int rb = ++m;
            for (MatchMetabolites met : data) {
                n = c;
                if (met != null) {
                    HSSFRow rowbody = sheet.createRow((short) rb);
                    if (met.getMetaboliteOne() != null) {
                        rowbody.createCell((short) n).setCellValue(met.getMetaboliteOne());
                        n++;
                    }
                    if (met.getMetaboliteTwo() != null) {
                        rowbody.createCell((short) n).setCellValue(met.getMetaboliteTwo());
                        n++;
                    }
                    if (met.isMatch()) {
                        rowbody.createCell((short) n).setCellValue("T");

                    } else {
                        rowbody.createCell((short) n).setCellValue("F");
                    }
                    //n++;
                    System.out.println("1234m Excel: ");

                    rb++;
                } else {
                    System.out.println("null Excel: ");
                }
            }

            FileOutputStream fileOut = new FileOutputStream(filename);
            hwb.write(fileOut);
            fileOut.close();
            if ((rb - (r + 1) == records) && (n - c == columns)) {
                System.out.println("Your excel file has been generated! with cells of" + (records * columns));
            }

        } catch (Exception ex) {
            System.out.println(ex);

        }
    }

    public void CreateMetaboliteMatchSheet(String sheetName, int r, int c, ArrayList<String> title, List<MatchMetabolites> data) {

        CreateMetaboliteMatchSheet(file, fileName, sheetName, r, c, title, data);
    }

    public void CreateMetaboliteMatchSheet(int r, int c, ArrayList<String> title, List<MatchMetabolites> data) {

        CreateMetaboliteMatchSheet(file, fileName, "metabolites", r, c, title, data);
    }

    public void CreateMetaboliteMatchSheet(String sheetName, ArrayList<String> title, List<MatchMetabolites> data) {

        CreateMetaboliteMatchSheet(file, fileName, sheetName, 1, 1, title, data);
    }

    public void CreateMetaboliteMatchSheet(ArrayList<String> title, List<MatchMetabolites> data) {

        CreateMetaboliteMatchSheet(file, fileName, "metabolites", 1, 1, title, data);
    }

    public void CreateMetaboliteMatchSheet(List<MatchMetabolites> data) {
        ArrayList<String> title = new ArrayList<String>();
        title.add("metabolite One");
        title.add("metabolite Two");
        title.add("Is Match");

        CreateMetaboliteMatchSheet(file, fileName, "metabolites", 1, 1, title, data);
    }
}
