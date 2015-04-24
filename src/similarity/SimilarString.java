/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package similarity;


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Mathialakan
 */

public class SimilarString {
 
  
    /**
     * @param args the command line arguments
     */
    
    int dist, length, cost;
            double ratio;
            int extension=0;

    public int getDist() {
        return dist;
    }

    public void setDist(int dist) {
        this.dist = dist;
    }

    public double getRatio() {
        return ratio;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }
    
    public int  distance (String s1, String s2){
       
        s1 = convertDigitsAsString(s1);
        s2 = convertDigitsAsString(s2);
        s1 = removeSpecialChar(s1).toUpperCase();
        s2 = removeSpecialChar(s2).toUpperCase();
        int first = 0, firstS;
                
        if ( s1.length() < s2.length()){
            String temp = s2;
            s2 = s1;
            s1 = temp;
        }
        int lastB = s1.length();
        int lastS = s2.length();
        int smallL = lastS;
        int bigL = lastB;
        length = lastB;
        boolean mismatch;
        int[] d = new int[bigL];
        //System.out.println("S1 "+s1 +" S2 "+s2  + " lastB "+ lastB +" lastS "+ lastS);
        for(int i=0; i< bigL; i++){
            d[i] = 0;
            first = i;
            firstS = 0;
        while((first< lastS)&& (d[i]!=smallL)){
            mismatch= false;
            while(!mismatch && (firstS < lastS) && (d[i]!=smallL)){
                if(s1.charAt(first) != s2.charAt(firstS)) mismatch = true;
                else d[i]++;
                   
                first++;
                firstS++;
                //System.out.println("first :"+first+" d: "+d[i]);
            }
            mismatch= false;
            while(!mismatch && (0 < lastS) && (d[i]!=smallL)){
                if(s1.charAt(lastB-1) != s2.charAt(lastS-1)) mismatch = true;
                 else   d[i]++;
                lastS--;
                lastB--;
                // System.out.println("lastS :"+lastS+" lastB :"+lastB+" d: "+d[i]);
            }
        }
        }
        //System.out.println("max :"+max(d));
        return length-max(d);
    }
    
    public int myDistance(String str1, String str2){
        ratio =0;
        if( "".equals(str1))
        {  
            if ("".equals(str2)) return -1;
            else   return str2.length();
        }
        else if ("".equals(str2))    return str1.length(); 
        extension=0;
        if ( str1.length() < str2.length()){
            String temp = str2;
            str2 = str1;
            str1 = temp;
        }  
        if (str1.charAt(0)=='_') str1= str1.replaceFirst("_", "");
        if (str2.charAt(0)=='_') str2= str2.replaceFirst("_", "");
        if (!Character.isDigit(str1.charAt(0))&& Character.isLowerCase(str1.charAt(0))&&(str1.charAt(1)=='_')) str1= str1.replaceFirst(str1.substring(0, 2), "");
        if (!Character.isDigit(str2.charAt(0))&& Character.isLowerCase(str1.charAt(0))&&(str2.charAt(1)=='_')) str2= str2.replaceFirst(str2.substring(0, 2), "");
        //System.out.println("str1 :" + str1+" str2 :"+ str2);
        ArrayList<String> split1 = subMe(str1);
        ArrayList<String> split2 = subMe(str2);
        
        int[] pos = new int[split1.size()];
        for(int k=0; k< pos.length; k++) pos[k] =-1; 
        int distSub;
        int dist=0;
       
        if ( split1.size() < split2.size()){
          ArrayList<String> temp = split2;
          split2 = split1;
          split1 = temp;
          }  
        int[][] distanceAll = new int[split2.size()][split1.size()];
        
        int i=0,j; 
        for(String s2: split2){
            distSub = 100;
            j=0;
            for(String s1: split1){
               int levenDist = new Leven().computeLevenshteinDistance(s2.toLowerCase(), s1.toLowerCase());
               //System.out.println("s2 :"+s2+" s1: "+s1);
               int extra=0;
               if(isAllUppercase(s2))
               {if(!isAllUppercase(s1)) extra=distanceShortForm(s2.trim(),s1.trim());}
               else if(isAllUppercase(s1)) extra=distanceShortForm(s1,s2);
               //else if(noty(s1, s2)) levenDist=0;
               //System.out.println("extra :"+extra+" i: "+i);
               if (extra>90) levenDist=0;
               distanceAll[i][j]=levenDist;
                 if (levenDist < distSub){  distSub= levenDist; pos[i] = j; }
                 j++;
             }
           i++;
           dist +=distSub;
         }
        for(int k=0; k< split1.size(); k++) 
            
            if(!isMember(pos, k)) dist+=split1.get(k).length();
        
        //printArray(distanceAll);
       
        //System.out.println("dist :"+dist+" extension "+extension);
        int calLength =  str1.length()+extension;
        //System.out.println("dist :"+dist+" extension "+extension+" calLength "+calLength);
        if(dist> calLength) ratio =0; else ratio =(double)((calLength-dist))/(double)(calLength);
        return dist;
    } 
    
    public int distanceStandardFormat(String str1, String str2){
         if( str1=="")
            {   if (str2== "") return 0;
                else {  return str2.length();}
            }
        else if (str2== "")   { return str1.length();} 
        extension=0;
       if ( str1.length() < str2.length()){
            String temp = str2;
            str2 = str1;
            str1 = temp;
        }  
        if (str1.charAt(0)=='_') str1= str1.replaceFirst("_", "");
        if (str2.charAt(0)=='_') str2= str2.replaceFirst("_", "");
        if (!Character.isDigit(str1.charAt(0))&& Character.isLowerCase(str1.charAt(0))&&(str1.charAt(1)=='_')) str1= str1.replaceFirst(str1.substring(0, 2), "");
        if (!Character.isDigit(str2.charAt(0))&& Character.isLowerCase(str1.charAt(0))&&(str2.charAt(1)=='_')) str2= str2.replaceFirst(str2.substring(0, 2), "");
        //System.out.println("str1 :" + str1+" str2 :"+ str2);
        //ArrayList<String> split1 = subMe(str1);
        //ArrayList<String> split2 = subMe(str2);
        str1 = convertDigitsAsString(str1);
        str2 = convertDigitsAsString(str2);
        str1 = removeSpecialChar(str1);
        str2 = removeSpecialChar(str2);
     
         int levenDist = new Leven().computeLevenshteinDistance(str2.toLowerCase(), str1.toLowerCase());
               //System.out.println("s2 :"+s2+" s1: "+s1);
              // int extra=0;
              // if(isAllUppercase(str2))
               //{if(!isAllUppercase(str1)) extra=distanceShortForm(str2.trim(),str1.trim());}
               //else if(isAllUppercase(str1)) extra=distanceShortForm(str1,str2);
               
               //System.out.println("extra :"+extra+" i: "+i);
               //if (extra>90) levenDist=0;
       
        int calLength =  str1.length();//+extension;
        //System.out.println("dist :"+dist+" extension "+extension+" calLength "+calLength);
        if(levenDist> calLength) ratio =0; else ratio =(double)((calLength-levenDist))/(double)(calLength);
        return levenDist;
    } 
    
    /*private boolean noty(String str1, String str2){
        
        if ( str1.length() < str2.length()){
            String temp = str2;
            str2 = str1;
            str1 = temp;
        } 
        str1.c
    }*/
    public int myDistanceURI(String str1, String str2){
         
        ratio =0;
        if( "".equals(str1)||str1==null)
        {   if ("".equals(str2)||str2==null) return -1;
            else  return str2.length();
        }
        else if ("".equals(str2)||str2==null)   return str1.length(); 
       
        if ( str1.length() < str2.length()){
            String temp = str2;
            str2 = str1;
            str1 = temp;
        }         
        ArrayList<String> split1 = subURIMe(str1);
        ArrayList<String> split2 = subURIMe(str2);
        
        int[] pos = new int[split1.size()];
        int distSub;
        int dist=0;
        
        if ( split1.size() < split2.size()){
          ArrayList<String> temp = split2;
          split2 = split1;
          split1 = temp;
          }  
        int[][] distanceAll = new int[split2.size()][split1.size()];
        
        int i=0,j; 
        for(String s2: split2){
            distSub = 100;
            j=0;
            for(String s1: split1){
               int levenDist = new Leven().computeLevenshteinDistance(s2, s1);
      
               distanceAll[i][j]=levenDist;
                 if (levenDist < distSub){  distSub= levenDist; pos[i] = j;}
                 j++;
             }
           i++;
           dist +=distSub;
         }
        for(int k=0; k< split1.size(); k++) 
            if(!isMember(pos, k)) dist+=split1.get(k).length();
        
        printArray(distanceAll);
        //System.out.println("dist :"+dist);
        if(dist> str1.length()) ratio =0; else ratio =(double)((str1.length()-dist))/(double)str1.length();
        return dist;
    } 
    
    private boolean isMember(int[] a, int e){
       for(int i=0; i< a.length; i++)
           if(a[i]==e) return true;
       return false;
    }
    
     private boolean isMember(String[] a, String e){
       for(int i=0; i< a.length; i++)
           if(a[i]==e) return true;
       return false;
    }
     
    /*public int distanceAllMe(int[][] distanceAll){
        int distSub;
        int dist =0;
        int[] pos = new int[distanceAll[0].length];
        for(int r=0; r< distanceAll.length; r++){
        distSub = distanceAll[r][0];
        pos[r]=0;
        for(int c=0; c<distanceAll[r].length; c++)
            if (distanceAll[r][c] <distSub){
                distSub= distanceAll[r][c];
                pos[r]=c;
            }
        dist+=distSub;
       }
       
      
    }*/
    
    public void printArray(int[][] a){
       
        for(int i=0; i<a.length; i++){
            for(int j=0; j<a[i].length; j++)
                //System.out.print("a[ "+i+" , "+j+"] = "+a[i][j]);
            System.out.print(a[i][j]+"\t");
                System.out.println();
        }
    }
    
    public ArrayList<String> subMe(String str){
        //System.out.println( " str \t"+ str);
        String sub="";
        ArrayList<String> split = new ArrayList<String>();
        str = convertDigitsAsString(str);
        //str = str.replace("(", "");
        //str = str.replace(")", "");
        //System.out.println( " str \t"+ str);
        int i =0;
            while(i<str.length())
            { 
            while(i<str.length() && !isSpecialChar(str.charAt(i))){
                sub+=str.charAt(i);
                i++;
                //System.out.println("i :"+i+ " sub :"+ sub);
            }
                split.add(sub);
                i++;
                //System.out.println("sub  "+i+ " \t "+ sub);
                sub ="";
            }
       
        return split;
    } 
    public ArrayList<String> subURIMe(String str){
        //System.out.println( " str \t"+ str);
        String sub="";
        ArrayList<String> split = new ArrayList<String>();
        //str = convertDigitsAsString(str);
        //System.out.println( " str \t"+ str);
        int i =0;
            while(i<str.length())
            { 
            while(i<str.length() && !isSpecialChar(str.charAt(i))){
                sub+=str.charAt(i);
                i++;
                //System.out.println("i :"+i+ " sub :"+ sub);
            }
                split.add(sub);
                i++;
                //System.out.println("sub  "+i+ " \t "+ sub);
                sub ="";
            }
       
        return split;
    } 
    
     public int  distanceNew (String s1, String s2){
         
         int d=0;
        int first = 0, firstS;
                
        if ( s1.length() < s2.length()){
            String temp = s2;
            s2 = s1;
            s1 = temp;
        }
        int lastB = s1.length();
        int lastS = s2.length();
        int smallL = lastS;
        int bigL = lastB;
        length = lastB;
        boolean mismatch;
        //int[] d = new int[bigL];
       // System.out.println("S1 "+s1 +" S2 "+s2  + " lastB "+ lastB +" lastS "+ lastS);
      
        while((first< lastS)&& (d!=smallL)){
            mismatch= false;
            while(!mismatch && (first < lastS) && (d!=smallL)){
                if(s1.charAt(first) != s2.charAt(first)) mismatch = true;
                else d++;
                   
                first++;
                //firstS++;
               // System.out.println("first :"+first+" d: "+d);
            }
            mismatch= false;
            while(!mismatch && (0 < lastS) && (d!=smallL)){
                if(s1.charAt(lastB-1) != s2.charAt(lastS-1)) mismatch = true;
                 else   d++;
                lastS--;
                lastB--;
                 //System.out.println("lastS :"+lastS+" lastB :"+lastB+" d: "+d);
            }
        }
        
        //System.out.println("max :"+d);
        return bigL-d;
    }
    
    public int max(int[] a){
        int m=a[0];
        for(int i=0; i<a.length; i++)
            if(m<a[i]) m= a[i];
        return m;
    }
    
    public void similarity(String s1, String s2){
        dist = distance(s1, s2);
        //System.out.print(s1+"\t"+s2+"\t"+ dist +"\t"+ratio(s1, s2)+"\n");
     
    }
    
   
    private int distanceShortForm(String sfmS, String s){
        String[] prefix = new String[]{"Meth","Eth","Prop","But","Pent","Hex","Hept","Oct","Non","Dec","Undec",
            "Dodec","Tridec","Tetradec","Pentadec","Eicos","Triacont","Tetracont","Pentacont"};
        String[] prefixNumber = new String[]{"mono","di","tri","tetra","penta","hexa","hepta","octa","nona","deca", 
            "icosa", "triaconta", "tetraconta","pentaconta","hexaconta","heptaconta","octaconta","nonaconta",
            "hecta","dicta","tricta","tetracta","pentacta","hexacta","heptacta","octacta","nonacta",
            "kilia","dilia","trilia","tetralia","pentalia","hexalia","heptalia","octalia","nonalia"}; 
        //System.out.println(" : "+sfmS+" : ");
        if (sfmS.equals("")) return -1;
        s = s.toUpperCase();
        
        int[] pos= new int[sfmS.length()];
        for(int j=0; j<sfmS.length(); j++){//System.out.println(sfmS+" : "+pos[j]);
            pos[j] = s.indexOf(sfmS.charAt(j));
            if (pos[j]==-1) return -1;
           // System.out.println(sfmS+" : "+pos[j]);
        }
        int score = 0;
        if ( pos[0]==-1 ) return -1;
        else if (pos[0]==0)  score=33;
            int unitScore = 100/pos.length;
         for(int i=0; i<pos.length-1; i++)
         { 
         if(pos[i]<pos[i+1]) score+=unitScore;
         //System.out.println(score+" : "+pos[i]);  
         }
             
         return score;
    }
    private boolean isAllUppercase(String s){
       //System.out.println("upper : "+s);
        for(int i=0; i<s.length(); i++) if (Character.isLowerCase(s.charAt(i))){  return false;}
        return true;        
    }
    
    private String convertDigitsAsString(String s){
        String num;
       
        int i=0;
        while(i < s.length()){
            num ="";
                while( i< s.length() && Character.isDigit(s.charAt(i)) ){
                  num+=Character.toString(s.charAt(i));
                  i++; 
                  //System.out.println(i +" nnn " + num);
                }
                
               if(num!="") {
                    String word =new NumberWord().convert(Integer.parseInt(num));
                   s = s.replaceFirst(num, word);
                   extension +=word.length()-num.length();
                   i = i+word.length()-num.length();
               }
               i++;
        }
        return s;
    }
    
    public boolean isSpecialChar(char c){
        String s=",-_ ";
        return s.contains(Character.toString(c));
    }
    
    public String removeSpecialChar(String str){
        String s="()[]-_ ";
        for(int i=0; i< s.length(); i++)
            str = str.replace(Character.toString(s.charAt(i)), "");
        return str;
    }
    
    public int countChar(String s, char c){
        int count=0;
        for(int i=0; i< s.length(); i++)
            if (s.charAt(i)==c) count++;
        return count;
    }
    
    public double ratio(String s1, String s2){
        //System.out.println("length :"+length+" dist :"+dist);
        //double l = (s1.length() > s2.length())? (double) s1.length() : (double)s2.length();
        return Math.round(((length-dist)/length)*100);
    
    }
    
    public void write(String[][] strings) {
       int bl = lengthBig(strings);
    FileWriter writer;
        try {
        writer = new FileWriter("D:\\USD\\SBML\\similarity.txt");
       for(int i=0; i< strings.length ; i++){
        dist = distance(strings[i][0], strings[i][1]);
       int n= countTab(bl,strings[i][0] );
            //System.out.println("n :"+ n);
            writer.write(strings[i][0]);
            for(int j=0; j< n; j++) writer.write("\t");
            writer.write(strings[i][1]);
            for(int j=0; j< 2; j++) writer.write("\t");
            writer.write(String.valueOf(dist));
            for(int j=0; j< 2; j++) writer.write("\t");
            writer.write(ratio(strings[i][0],strings[i][1])+"\n");
        }
        
       //t.similarity("Radulescu2008_NFkB_hierarchy_M_14_25_28_Lipniacky", "Radulescu2008_NFkB_hierarchy_M_39_65_90");
           writer.close();
        } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } 
    }
    
    public int lengthBig(String[][] strings){
        int big=strings[0][0].length();
        for(int i=0; i< strings.length ; i++)
            if(big < strings[i][0].length()) big= strings[i][0].length();
        //System.out.println("big :" +big);
        return big;
    }
    
    public int countTab(int big, String s){     
        return 1+(big-s.length())/4;
    }
    
    public int minimum(int a, int b, int c){
        int[] temp= new int[]{a,b,c};
        int min = temp[0];
        for(int i=0; i< temp.length; i++)
            if(min > temp[i] ) min = temp[i];
    
    return min;
    }
    
  public int LevenshteinDistance(String s, int len_s, String t, int len_t)
    {
      /* test for degenerate cases of empty strings */
      if (len_s == 0) return len_t;
      if (len_t == 0) return len_s;

      /* test if last characters of the strings match */
      if (s.charAt(len_s-1) == t.charAt(len_t-1)) cost = 0;
      else                          cost = 1;

      /* return minimum of delete char from s, delete char from t, and delete char from both */
      return minimum(LevenshteinDistance(s, len_s - 1, t, len_t    ) + 1,
                     LevenshteinDistance(s, len_s    , t, len_t - 1) + 1,
                     LevenshteinDistance(s, len_s - 1, t, len_t - 1) + cost);
    }
    
    public void testLevenshteinDistance(String[][] s ){
        for(int i=0; i< s.length; i++)
        //System.out.println(s[i][0]+"\t"+s[i][1]+"\t"+LevenshteinDistance(s[i][0],s[i][0].length(), s[i][1], s[i][1].length() ));
        //System.out.println(s[i][0]+"\t"+s[i][1]+"\t"+Leven.computeLevenshteinDistance(s[i][0], s[i][1] ));
        System.out.println(s[i][0]+"\t"+s[i][1]+"\t"+new Leven().computeLevenshteinDistance(s[i][0], s[i][1] ));
    }
    
    public int uricom(String str1, String str2){
        if (str1==null||str1=="") return 0;
        if (str2==null||str2=="") return 0;
        return (str1.equals(str2)) ? 1: 0; 
    }
    
    private String getKEGGCompoundId(String uri){
        String[] uriList= uri.split(",");
        String uriType ="";
        String kegg=""; 
        for (int i=0; i<uriList.length; i++){
            if (uriList[i].contains("kegg")){
                uriType = "kegg.compound";
                kegg = uriList[i].substring(uriList[i].indexOf(uriType)+uriType.length()+1);
                
            }
            
        }
       return kegg;
        
    }
    
     private String getUniportompoundId(String uri){
        String[] uriList= uri.split(",");
        String uriType ="";
        String uniport=""; 
        for (int i=0; i<uriList.length; i++){
            if (uriList[i].contains("uniprot")){
                uriType ="miriam:uniprot";
                uniport = uriList[i].substring(uriList[i].indexOf(uriType)+uriType.length()+1);
            }
        }
        return uniport;
        
    }
     
     private String getPubChemCompoundId(String uri){
        String[] uriList= uri.split(",");
        String uriType ="";
        String  pubchem=""; 
        for (int i=0; i<uriList.length; i++){
            if (uriList[i].contains("pubchem")){
                uriType = "pubchem.compound";
                pubchem = uriList[i].substring(uriList[i].indexOf(uriType)+uriType.length()+1);
            }
            
        }
       return pubchem;
       
    }
    
    private String getKEGGReactionId(String uri){
        String[] uriList= uri.split(","); 
        String uriType ="";
        String uriValue ="";
        //System.out.println("l : "+uriList.length);
        for (int i=0; i<uriList.length; i++){ 
            if (uriList[i].contains("kegg")){ 
            int start = uriList[i].indexOf("kegg.reaction")+"kegg.reaction".length()+1;
            uriValue =  uriList[i].substring(start,start+6 );
            //System.out.println("uriValue : "+uriValue);
            return uriValue; 
        }
        }
        return uriValue;
        
    }
   
    private String getECReactionId(String uri){
        String[] uriList= uri.split(","); 
        String uriType ="";
        String uriValue ="";
        //System.out.println("l : "+uriList.length);
        for (int i=0; i<uriList.length; i++){
        
            if (uriList[i].contains("ec-code")){
                uriValue =uriList[i].substring(uriList[i].indexOf("miriam:ec-code")+"miriam:ec-code".length()+1);
                return uriValue;
            }
          }
        return uriValue;
        
    }
    
    private String getGNOReactionId(String uri){
        String[] uriList= uri.split(","); 
        String uriType ="";
        String uriValue ="";
        //System.out.println("l : "+uriList.length);
        for (int i=0; i<uriList.length; i++){ 
            
        if (uriList[i].contains("obo.go")){
            uriValue = uriList[i].substring(uriList[i].indexOf("obo.go")+"obo.go".length()+1);
             return uriValue;
        }
        
        }
        return uriValue;
        
    }
    
     private String getKEGGParameterId(String uri){
        String[] uriList= uri.split(",");
        String uriType ="";
        String kegg="", pubchem="",uniport=""; 
        for (int i=0; i<uriList.length; i++){
            if (uriList[i].contains("kegg")){
                uriType = "kegg.compound";
                kegg = uriList[i].substring(uriList[i].indexOf(uriType)+uriType.length()+1);
                
            }
            else if (uriList[i].contains("pubchem")){
                uriType = "pubchem.compound";
                pubchem = uriList[i].substring(uriList[i].indexOf(uriType)+uriType.length()+1);
            }
            else if (uriList[i].contains("uniprot")){
                uriType ="miriam:uniprot";
                uniport = uriList[i].substring(uriList[i].indexOf(uriType)+uriType.length()+1);
            }
        }
        if (!kegg.equals("")) return kegg;
        if (!pubchem.equals("")) return pubchem;
        else return uniport;
        
    }
    
      private String getKEGGCompartmentId(String uri){
        String[] uriList= uri.split(",");
        String uriType ="";
        String bto="", go="",uniport=""; 
        for (int i=0; i<uriList.length; i++){
            if (uriList[i].contains("obo.bto")){
                uriType = "obo.bto";
                bto = uriList[i].substring(uriList[i].indexOf(uriType)+uriType.length()+1);
                
            }
            else if (uriList[i].contains("obo.go")){
                uriType = "obo.go";
                go = uriList[i].substring(uriList[i].indexOf(uriType)+uriType.length()+1);
            }
            /*else if (uriList[i].contains("uniprot")){
                uriType ="miriam:uniprot";
                uniport = uriList[i].substring(uriList[i].indexOf(uriType)+uriType.length()+1);
            }*/
        }
        if (!bto.equals("")) return bto;
        if (!go.equals("")) return go;
        else return uniport;
        
    }
     
  

  


}
