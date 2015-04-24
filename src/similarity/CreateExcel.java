/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package similarity;
import  java.io.*;  
import  org.apache.poi.hssf.usermodel.HSSFSheet;  
import  org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;
import  org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import java.util.ArrayList;
/**
 *
 * @author Mathialakan
 */
public class CreateExcel {
   
    
    public  void writeExlModels(String fn, String mn1,String mn2, ArrayList<String> resultName, ArrayList<String> resultURI,String method){
    
    int n = resultName.size();
    int m = resultURI.size();
    String sheetName;
    System.out.println("n :"+ n+ " m : "+ m+" nm : "+ n*m);
    if(method.equals("Leaven Edit Distance")) sheetName ="L_"+mn1+"_"+mn2;
    else sheetName ="M_"+mn1+"_"+mn2;
    System.out.println("sheetName :"+ sheetName);
    int rh=0;
    java.io.File myfile = new java.io.File(fn);
    String filename=fn ;
    HSSFWorkbook hwb = null;
    if(!myfile.exists()) hwb=new HSSFWorkbook();
     else
        try {
             hwb = (HSSFWorkbook)WorkbookFactory.create(myfile);
        } catch (IOException|InvalidFormatException e) {
            e.printStackTrace();
        }
       try{  
       
        HSSFSheet sheet =  hwb.createSheet(sheetName);
       //writeExcel(sheet,resultName);   
        rh++;
        HSSFRow rowmethod=   sheet.createRow((short)rh);
        rowmethod.createCell((short) 0).setCellValue(method);
        rh++;
        HSSFRow rowheadAmout=   sheet.createRow((short)rh);
        rowheadAmout.createCell((short) 1).setCellValue(mn1);
        rowheadAmout.createCell((short) 2).setCellValue(n);
         rowheadAmout.createCell((short) 3).setCellValue(mn2);
        rowheadAmout.createCell((short) 4).setCellValue(m);
        rh++;
        HSSFRow rowhead=   sheet.createRow((short)rh);
        rowhead.createCell((short) 2).setCellValue("Name_1");
        rowhead.createCell((short) 3).setCellValue("Name_2");
        rowhead.createCell((short) 4).setCellValue("DistanceName");
        rowhead.createCell((short) 5).setCellValue("RatioName");
        rowhead.createCell((short) 6).setCellValue("DecesionName");
        rowhead.createCell((short) 7).setCellValue("URI_1");
        rowhead.createCell((short) 8).setCellValue("URI_2");
        rowhead.createCell((short) 9).setCellValue("Distance URI");
        rowhead.createCell((short) 10).setCellValue("RatioURI");
        rowhead.createCell((short) 11).setCellValue("DecesionURI");
        
        rh++;
        int rb=rh;
        int k=0;
        int rbseq=0;
        for(int i=0; i< n ; i++)
            for(int j=0; j< m ; j++)
            {
                rbseq =(m*i+j+rh);
                rb = rbseq%3267;
                System.out.println("rb :"+ rb);
                if (k>10) break;
                if (rbseq > 32767){
                    k++;
                    
                    sheet = hwb.createSheet(sheetName+"_"+k);
                    rbseq =rh;
                }}
            /* if (method.equals("Leaven Edit Distance")){
                int distLaven = new Leven().computeLevenshteinDistance(str1[i], str2[j]);
                int distLavenURI = new Leven().computeLevenshteinDistance(stru1[i], stru2[j]);
                int len= str1[i].length(); 
                if(str1[i].length()< str2[j].length()) len= str2[j].length();
                double ratioLaven = (len-distLaven)/(double)len;
                int lenURI= stru1[i].length(); 
                if(stru1[i].length()< stru2[j].length()) lenURI= stru2[j].length();
                double ratioLavenURI = (lenURI-distLavenURI)/(double)lenURI;
                HSSFRow row=   sheet.createRow((short)rb);
                row.createCell((short) 2).setCellValue(str1[i]);
                row.createCell((short) 3).setCellValue(str2[j]);
                row.createCell((short) 4).setCellValue(distLaven);
                row.createCell((short) 5).setCellValue(ratioLaven);
                row.createCell((short) 6).setCellValue((ratioLaven>0.70? 1:0));
                row.createCell((short) 7).setCellValue(stru1[i]);
                row.createCell((short) 8).setCellValue(stru2[j]);
                row.createCell((short) 9).setCellValue(distLavenURI);
                row.createCell((short) 10).setCellValue(ratioLavenURI);
                row.createCell((short) 11).setCellValue(uricom(stru1[i], stru2[j]));
               } else{
                HSSFRow row=   sheet.createRow((short)rb);
                row.createCell((short) 2).setCellValue(str1[i]);
                row.createCell((short) 3).setCellValue(str2[j]);
                row.createCell((short) 4).setCellValue(distanceMe(str1[i], str2[j] ));
                row.createCell((short) 5).setCellValue(ratio);
                row.createCell((short) 6).setCellValue((ratio>0.70? 1:0));
                row.createCell((short) 7).setCellValue(stru1[i]);
                row.createCell((short) 8).setCellValue(stru2[j]);
                row.createCell((short) 9).setCellValue(distanceURIMe(stru1[i], stru2[j]));
                row.createCell((short) 10).setCellValue(ratio);
                row.createCell((short) 11).setCellValue(uricom(stru1[i], stru2[j]));
                }
            }*/
      
        FileOutputStream fileOut =  new FileOutputStream(filename);
        hwb.write(fileOut);
        fileOut.close();
        System.out.println("Your excel file has been generated!");

    } catch ( Exception ex ) {
            System.out.println(ex);

        }
    }
    /*
public  void writeExlString(String fn, String sheetName, String[] str1, String[] str2){
        try{
        String filename=fn ;
        HSSFWorkbook hwb=new HSSFWorkbook();
        HSSFSheet sheet =  hwb.createSheet(sheetName);

        HSSFRow rowhead=   sheet.createRow((short)0);
        rowhead.createCell((short) 0).setCellValue("String A");
        rowhead.createCell((short) 1).setCellValue("String B");
        rowhead.createCell((short) 2).setCellValue("Distance");
        rowhead.createCell((short) 3).setCellValue("Ratio");
   
        for(int i=0; i< str1.length ; i++){
                HSSFRow row=   sheet.createRow((short)(i+1));
                row.createCell((short) 0).setCellValue(str1[i]);
                row.createCell((short) 1).setCellValue(str2[i]);
                row.createCell((short) 2).setCellValue(distanceMe(str1[i], str2[i] ));
                row.createCell((short) 3).setCellValue(ratio);
            }

        FileOutputStream fileOut =  new FileOutputStream(filename);
        hwb.write(fileOut);
        fileOut.close();
        System.out.println("Your excel file has been generated!");

    } catch ( Exception ex ) {
            System.out.println(ex);

        }
    }
     
public  void writeExlEcoli(String fn, String sheetName, String[] str1, String[] str2, String method){
    
    int n = str1.length;
    int m = str2.length;
    System.out.println("n :"+ n+ " m : "+ m+" nm : "+ n*m);
    int rh=0;
        try{
        String filename=fn ;
        HSSFWorkbook hwb=new HSSFWorkbook();
        HSSFSheet sheet =  hwb.createSheet(sheetName);
        for(int i=0; i<4; i++) //sheet = hwb.createSheet(sheetName+i);
           sheet.autoSizeColumn(i);
        rh++;
        HSSFRow rowmethod=   sheet.createRow((short)rh);
        rowmethod.createCell((short) 0).setCellValue(method);
        rh++;
        HSSFRow rowhead=   sheet.createRow((short)rh);
        rowhead.createCell((short) 2).setCellValue("String A");
        rowhead.createCell((short) 3).setCellValue("String B");
        rowhead.createCell((short) 4).setCellValue("Distance");
        rowhead.createCell((short) 5).setCellValue("Ratio");
        rh++;
        HSSFRow rowheadAmout=   sheet.createRow((short)rh);
        rowheadAmout.createCell((short) 0).setCellValue(n);
        rowheadAmout.createCell((short) 1).setCellValue(m);
        rh++;
        int rb=rh;
        int k=0;
        int rbseq=0;
        for(int i=0; i< n ; i++)
            for(int j=0; j< m ; j++)
            {
                rbseq =(m*i+j+rh);
                rb = rbseq%3267;
                System.out.println("rb :"+ rb);
                if (k>10) break;
                if (rbseq > 32767){
                    k++;
                    //sheetName = sheetName+"_"+k;
                    //System.out.println("sheetName :"+ sheetName);
                    //FileOutputStream fileOut =  new FileOutputStream(filename);
                    //hwb.write(fileOut);
                    sheet = hwb.createSheet(sheetName+"_"+k);
                    rbseq =rh;
                }
             if (method.equals("Leaven Edit Distance")){
                 int distL =new Leven().computeLevenshteinDistance(str1[i], str2[j] );
             
                int big =str1[i].length();
                if(big <str2[j].length()) big = str2[j].length();
                double ratioL; 
                if(distL > big) ratioL =0; else ratioL =(double)((big-distL))/(double)big;
                HSSFRow row=   sheet.createRow((short)rb);
                row.createCell((short) 2).setCellValue(str1[i]);
                row.createCell((short) 3).setCellValue(str2[j]);
                row.createCell((short) 4).setCellValue(distL);
                row.createCell((short) 5).setCellValue(ratioL);
               } else{
                HSSFRow row=   sheet.createRow((short)rb);
                row.createCell((short) 2).setCellValue(str1[i]);
                row.createCell((short) 3).setCellValue(str2[j]);
                row.createCell((short) 2).setCellValue(distanceMe(str1[i], str2[j] ));
                row.createCell((short) 3).setCellValue(ratio);
                }
            }
      
        FileOutputStream fileOut =  new FileOutputStream(filename);
        hwb.write(fileOut);
        fileOut.close();
        System.out.println("Your excel file has been generated!");

    } catch ( Exception ex ) {
            System.out.println(ex);

        }
    }

*/
private String getModelNumber(String modelName){
    int last = modelName.length();
    int first = modelName.length()- 6;
    if (first < 0) first =0;
   return modelName.substring(first, last);
     
    
}
/*
public  void writeExlModels(String fn, String mn1, String mn2, String[] str1, String[] str2,String[] strKEGG1, String[] strKEGG2,String[] strEC1, String[] strEC2,String[] strGNO1, String[] strGNO2,String method, int tdist, double tratio){
    
    int n = str1.length;
    int m = str2.length;
    String sheetName;
    sheetName =getModelNumber(mn1)+"_"+getModelNumber(mn2);
    System.out.println("n :"+ n+ " m : "+ m+" nm : "+ n*m);
    if(method.equals("Leaven Edit Distance")) sheetName +="_L";
    //else sheetName ="M_"+getModelNumber(mn1)+"_"+getModelNumber(mn2);
    System.out.println("sheetName :"+ sheetName);
    int rh=0;
    java.io.File myfile = new java.io.File(fn);
    String filename=fn ;
    HSSFWorkbook hwb = null;
    if(!myfile.exists()) hwb=new HSSFWorkbook();
     else
        try {
             hwb = (HSSFWorkbook)WorkbookFactory.create(myfile);
        } catch (IOException|InvalidFormatException e) {
            e.printStackTrace();
        }
       try{  
       
        HSSFSheet sheet =  hwb.createSheet(sheetName);
        for(int i=0; i<4; i++) //sheet = hwb.createSheet(sheetName+i);
           sheet.autoSizeColumn(i);
        rh++;
        HSSFRow rowmethod=   sheet.createRow((short)rh);
        rowmethod.createCell((short) 0).setCellValue(method);
        rh++;
        HSSFRow rowheadAmout=   sheet.createRow((short)rh);
        rowheadAmout.createCell((short) 1).setCellValue(mn1);
        rowheadAmout.createCell((short) 2).setCellValue(n);
         rowheadAmout.createCell((short) 3).setCellValue(mn2);
        rowheadAmout.createCell((short) 4).setCellValue(m);
        rh++;
        HSSFRow rowheadThreshold=   sheet.createRow((short)rh);
        rowheadThreshold.createCell((short) 1).setCellValue("Threshold ratio");
        rowheadThreshold.createCell((short) 2).setCellValue(tdist);
         rowheadThreshold.createCell((short) 3).setCellValue("Threshold distance");
        rowheadThreshold.createCell((short) 4).setCellValue(tratio);
        rh++;
        HSSFRow rowhead=   sheet.createRow((short)rh);
        rowhead.createCell((short) 2).setCellValue("Name_1");
        rowhead.createCell((short) 3).setCellValue("Name_2");
        rowhead.createCell((short) 4).setCellValue("DistanceName");
        rowhead.createCell((short) 5).setCellValue("RatioName");
        rowhead.createCell((short) 6).setCellValue("DecesionName");
        rowhead.createCell((short) 7).setCellValue("KEGG_1");
        rowhead.createCell((short) 8).setCellValue("KEGG_2");
        rowhead.createCell((short) 9).setCellValue("Distance KEGG");
        rowhead.createCell((short) 10).setCellValue("Ratio KEGG");
        rowhead.createCell((short) 11).setCellValue("Decesion KEGG");
        rowhead.createCell((short) 12).setCellValue("EC_1");
        rowhead.createCell((short) 13).setCellValue("EC_2");
        rowhead.createCell((short) 14).setCellValue("Distance EC");
        rowhead.createCell((short) 15).setCellValue("Ratio EC");
        rowhead.createCell((short) 16).setCellValue("Decesion EC");
        rowhead.createCell((short) 17).setCellValue("GNO_1");
        rowhead.createCell((short) 18).setCellValue("GNO_2");
        rowhead.createCell((short) 19).setCellValue("Distance GNO");
        rowhead.createCell((short) 20).setCellValue("Ratio GNO");
        rowhead.createCell((short) 21).setCellValue("Decesion GNO");
        
        rh++;
        int rb=rh;
        int k=0;
        int rbseq=0;
        for(int i=0; i< n ; i++)
            for(int j=0; j< m ; j++)
            {
                rbseq =(m*i+j+rh);
                rb = rbseq%3267;
                System.out.println("rb :"+ rb);
                if (k>10) break;
                if (rbseq > 32767){
                    k++;
                    
                    sheet = hwb.createSheet(sheetName+"_"+k);
                    rbseq =rh;
                }
             if (method.equals("Leaven Edit Distance")){
                int distLaven = new Leven().computeLevenshteinDistance(str1[i], str2[j]);
                int distLavenURI = new Leven().computeLevenshteinDistance(strKEGG1[i], strKEGG1[j]);
                int len= str1[i].length(); 
                if(str1[i].length()< str2[j].length()) len= str2[j].length();
                double ratioLaven = (len-distLaven)/(double)len;
                int lenURI= strKEGG1[i].length(); 
                if(strKEGG1[i].length()< strKEGG1[j].length()) lenURI= strKEGG1[j].length();
                double ratioLavenURI = (lenURI-distLavenURI)/(double)lenURI;
                HSSFRow row=   sheet.createRow((short)rb);
                row.createCell((short) 2).setCellValue(str1[i]);
                row.createCell((short) 3).setCellValue(str2[j]);
                row.createCell((short) 4).setCellValue(distLaven);
                row.createCell((short) 5).setCellValue(ratioLaven);
                row.createCell((short) 6).setCellValue((ratioLaven>0.70? 1:0));
                row.createCell((short) 7).setCellValue(strKEGG1[i]);
                row.createCell((short) 8).setCellValue(strKEGG1[j]);
                row.createCell((short) 9).setCellValue(distLavenURI);
                row.createCell((short) 10).setCellValue(ratioLavenURI);
                row.createCell((short) 11).setCellValue(uricom(strKEGG1[i], strKEGG1[j]));
               } else{
                HSSFRow row=   sheet.createRow((short)rb);
                int d= distanceMe(str1[i], str2[j] );
                row.createCell((short) 2).setCellValue(str1[i]);
                row.createCell((short) 3).setCellValue(str2[j]);
                row.createCell((short) 4).setCellValue(d);
                row.createCell((short) 5).setCellValue(ratio);
                row.createCell((short) 6).setCellValue(((ratio>tratio && d<tdist )? 1:0));
                row.createCell((short) 7).setCellValue(strKEGG1[i]);
                row.createCell((short) 8).setCellValue(strKEGG2[j]);
                row.createCell((short) 9).setCellValue(distanceURIMe(strKEGG1[i], strKEGG2[j]));
                row.createCell((short) 10).setCellValue(ratio);
                row.createCell((short) 11).setCellValue(uricom(strKEGG1[i], strKEGG2[j]));
                row.createCell((short) 12).setCellValue(strEC1[i]);
                row.createCell((short) 13).setCellValue(strEC2[j]);
                row.createCell((short) 14).setCellValue(distanceURIMe(strEC1[i], strEC2[j]));
                row.createCell((short) 15).setCellValue(ratio);
                row.createCell((short) 16).setCellValue(uricom(strEC1[i], strEC2[j]));
                row.createCell((short) 17).setCellValue(strGNO1[i]);
                row.createCell((short) 18).setCellValue(strGNO2[j]);
                row.createCell((short) 19).setCellValue(distanceURIMe(strGNO1[i], strGNO2[j]));
                row.createCell((short) 20).setCellValue(ratio);
                row.createCell((short) 21).setCellValue(uricom(strGNO1[i], strGNO2[j]));
                }
            }
      
        FileOutputStream fileOut =  new FileOutputStream(filename);
        hwb.write(fileOut);
        fileOut.close();
        System.out.println("Your excel file has been generated!");

    } catch ( Exception ex ) {
            System.out.println(ex);

        }
    }

*/

/*
public void write2(String[] str1, String[] str2) {
       
    FileWriter writer;
        try {
        writer = new FileWriter("E:\\similarity.txt");
        writer.write("String A"+ "\t"+"String B"+"\t"+"Distance"+"\t"+ "Ratio"+ "\t" +"\n");
        for(int i=0; i< str1.length ; i++)
           
        {
            /*int distL =new Leven().computeLevenshteinDistance(str1[i], str2[i] );
            int big =str1[i].length();
            if(big <str2[i].length()) big = str2[i].length();
            double ratioL; 
            if(distL > big) ratioL =0; else ratioL =(double)((big-distL))/(double)big;
            writer.write(str1[i]+ "\t"+str2[i]+"\t"+distL+"\t"+ratioL +"\n");*/
           /* similarMetString sms = new similarMetString();
             writer.write(str1[i]+ "\t"+str2[i]+"\t"+sms.distanceMe(str1[i], str2[i] )+"\t"+sms.getRatio() +"\n");
           //writer.write(str1[i]+ "\t"+str2[i]+"\t"+distanceStandardFormat(str1[i], str2[i] )+"\t"+ratio +"\n");
           //writer.write(str1[i]+ "\t"+str2[i]+"\t"+distanceMe(str1[i], str2[i] )+"\t"+ratio +"\n");
          //writer.write(str1[i][0]+ "\t"+str1[i][1]+"\t"+distanceMe(str1[i][0], str1[i][1] )+"\t"+ ratio+ "\t" +"\n");
        }
        writer.close();
        } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } 
  }
  
public void writeEcoli(String[] str1, String[] str2, String mn1, String mn2) {
       
    String file = "E:\\"+mn1+"_"+mn2+"L.txt" ;
    FileWriter writer;
        try {
         writer = new FileWriter(file); 
         writer.write("species A"+ "\t"+str1.length+"\t"+"species A"+"\t"+ str2.length+ "\t" +"\n");
        writer.write("String A"+ "\t"+"String B"+"\t"+"Distance"+"\t"+ "Ratio"+ "\t" +"\n");
         for(int i=0; i< str1.length ; i++)
            for(int j=0; j< str2.length ; j++)
            {
                /*int distL =new Leven().computeLevenshteinDistance(str1[i], str2[j] );
                int big =str1[i].length();
                if(big <str2[j].length()) big = str2[j].length();
                double ratioL; 
                if(distL > big) ratioL =0; else ratioL =(double)((big-distL))/(double)big;
                writer.write(str1[i]+ "\t"+str2[j]+"\t"+distL+"\t"+ratioL +"\n");*/

              /*  writer.write(str1[i]+ "\t"+str2[j]+"\t"+distanceStandardFormat(str1[i], str2[j] )+"\t"+ratio +"\n");
               // writer.write(str1[i][0]+ "\t"+str1[i][1]+"\t"+distanceMe(str1[i][0], str1[i][1] )+"\t"+ ratio+ "\t" +"\n");
            }
        writer.close();
        } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } 
  }

public void writeNew(String[] str1, String[] str2, String[] stru1, String[] stru2, String mn1, String mn2) {
       
    String file = "E:\\"+mn1+"_"+mn2+"G.txt" ;
    FileWriter writer;
        try {
         writer = new FileWriter(file); 
         //writer.write("species A"+ "\t"+str1.length+"\t"+"species A"+"\t"+ str2.length+ "\t" +"\n");
        writer.write("Name A"+ "\t"+"GO Term A"+"\t"+"Name B"+ "\t"+"GO Term B"+"\t"+"Distance"+"\t"+ "Ratio"+ "\t" +"\n");
         for(int i=0; i< str1.length ; i++)
            for(int j=0; j< str2.length ; j++)
            {
                /*int distL =new Leven().computeLevenshteinDistance(str1[i], str2[j] );
                int big =str1[i].length();
                if(big <str2[j].length()) big = str2[j].length();
                double ratioL; 
                if(distL > big) ratioL =0; else ratioL =(double)((big-distL))/(double)big;
                writer.write(str1[i]+ "\t"+str2[j]+"\t"+distL+"\t"+ratioL +"\n");*/
              /*  GOTerm.term("0005739");
                writer.write(str1[i]+ "\t"+GOTerm.term(getGOId(stru1[i]))+"\t"+str2[j]+"\t"+GOTerm.term(getGOId(stru2[j]))+"\t"+distanceMe(str1[i], str2[j] )+"\t"+ratio +"\t"+stru1[i]+ "\t"+stru2[j]+ "\t"+distanceURIMe(stru1[i], stru2[j] )+"\t"+ratio+"\n");
                //writer.write(str1[i]+ "\t"+str2[j]+"\t"+distanceStandardFormat(str1[i], str2[j] )+"\t"+ratio +"\n");
               // writer.write(str1[i][0]+ "\t"+str1[i][1]+"\t"+distanceMe(str1[i][0], str1[i][1] )+"\t"+ ratio+ "\t" +"\n");
            }
        writer.close();
        } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } 
  }

public void writeLaven(String[] str1, String[] str2, String[] stru1, String[] stru2, String mn1,String mn2) {
    String file = "E:\\"+mn1+"_"+mn2+"L.txt" ;     
    FileWriter writer;
        try {
        writer = new FileWriter(file);
        writer.write("Leaven Edit Distance  Algorithm\n");
                    writer.write("Name_"+mn1+ "\t"+"Name_"+mn2+"\t"+"Distance Name"+"\t"+"Ratio_Name" +"\t"+"Name_Decision\t"+"URI_"+mn1+ "\t"+"URI_"+mn2+"\t"+  "URI Distance"+"\t"+"URI Ratio" +"\n");

        for(int i=0; i< str1.length ; i++)
            for(int j=0; j< str2.length ; j++)
        {
            int distLaven = new Leven().computeLevenshteinDistance(str1[i], str2[j]);
            int distLavenURI = new Leven().computeLevenshteinDistance(stru1[i], stru2[j]);
            int len= str1[i].length(); 
            if(str1[i].length()< str2[j].length()) len= str2[j].length();
            double ratioLaven = (len-distLaven)/(double)len;
            int lenURI= stru1[i].length(); 
            if(stru1[i].length()< stru2[j].length()) lenURI= stru2[j].length();
            double ratioLavenURI = (lenURI-distLavenURI)/(double)lenURI;
            
            writer.write(str1[i]+ "\t"+str2[j]+"\t"+distLaven+"\t"+ratioLaven+"\t"+(ratioLaven>0.70? 1:0)+"\t" +stru1[i]+ "\t"+stru2[j]+"\t"+  distLavenURI+"\t"+ratioLavenURI +"\t"+uricom(stru1[i], stru2[j])+"\n");
        }
        writer.close();
        } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } 
  }
    
public void write3(String[] str1, String[] str2, String[] strKEGG1, String[] strKEGG2,String[] strEC1, String[] strEC2,String[] strGNO1, String[] strGNO2, String mn1,String mn2 ) {
    String file = "E:\\"+mn1+"_"+mn2+"M.txt" ; 
    FileWriter writer;
        try {
        writer = new FileWriter(file);
        writer.write("Name_"+mn1+ "\t"+"Name_"+mn2+"\t"+"Distance Name"+"\t"+"Ratio_Name" +"\t"+"Name_Decision\t"+"KEGG_"+mn1+ "\t"+"KEGG_"+mn2+"\t"+  "KEGG_ Distance"+"\t"+"KEGG_ Ratio" +"\t"+"KEGG_Decision\t"
                +"EC_"+mn1+ "\t"+"EC_"+mn2+"\t"+  "EC_ Distance"+"\t"+"EC_ Ratio" +"\t"+"EC_Decision\t"
                +"GNO_"+mn1+ "\t"+"GNO_"+mn2+"\t"+  "GNO_ Distance"+"\t"+"GNO_ Ratio" +"\t"+"GNO_Decision\t"+"\n");

        for(int i=0; i< str1.length ; i++)
            for(int j=0; j< str2.length ; j++)
        {
            //int dist = distanceMe(str1[i], str2[j] );    
            //if()
            writer.write(str1[i]+ "\t"+str2[j]+"\t"+distanceMe(str1[i], str2[j] )+"\t"+ratio+"\t"+(ratio>0.70? 1:0)+"\t" +strKEGG1[i]+ "\t"+strKEGG2[j]+"\t"+  distanceURIMe(strKEGG1[i], strKEGG2[j])+"\t"+ratio +"\t"+uricom(strKEGG1[i], strKEGG2[j])+
                    "\t"+ strEC1[i]+ "\t"+strEC2[j]+"\t"+  distanceURIMe(strEC1[i], strEC2[j])+"\t"+ratio +"\t"+uricom(strEC1[i], strEC2[j])+
            "\t"+ strGNO1[i]+ "\t"+strGNO2[j]+"\t"+  distanceURIMe(strGNO1[i], strGNO2[j])+"\t"+ratio +"\t"+uricom(strGNO1[i], strGNO2[j])+"\n");
        }
        
        writer.close();
        } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } 
  }
      public void write4(String[] str1, String[] str2) {
       
    FileWriter writer;
        try {
        writer = new FileWriter("E:\\similaritymm.txt");
        writer.write("String A"+ "\t"+"String B"+"\t"+"Distance"+"\t"+ "Ratio"+ "\t" +"\n");
        for(int i=0; i< str1.length ; i++)
            for(int j=0; j< str2.length ; j++)
        {
            //writer.write(str1[i]+ "\t"+str2[j]+"\t"+new Leven().computeLevenshteinDistance(str1[i], str2[i] )+"\t" +"\n");
            
            writer.write(str1[i]+ "\t"+str2[j]+"\t"+distanceMe(str1[i], str2[j] )+"\t"+ratio +"\n");
        }
        writer.close();
        } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } 
  }
*/
}