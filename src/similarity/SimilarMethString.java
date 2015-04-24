/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package similarity;

import java.util.ArrayList;

/**
 *
 * @author Mathialakan
 */
public class SimilarMethString {
    
    int dist, length, cost;
            double ratio;
            int extension=0;
    ArrayList<String> permutedList= new ArrayList<String>();

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
    
    public int myDistance(String str1, String str2){
        ratio =0;
        if( "".equals(str1))
            {  
                if ("".equals(str2)) return 0;
                else  return str2.length();
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
        if(str1.length()>1 && str2.length()>1){
        if (!Character.isDigit(str1.charAt(0))&& Character.isLowerCase(str1.charAt(0))&&(str1.charAt(1)=='_')) str1= str1.replaceFirst(str1.substring(0, 2), "");
        if (!Character.isDigit(str2.charAt(0))&& Character.isLowerCase(str2.charAt(0))&&(str2.charAt(1)=='_')) str2= str2.replaceFirst(str2.substring(0, 2), "");
        }
        ArrayList<String> split1 = clsuterString(subMe(str1));
        ArrayList<String> split2 = clsuterString(subMe(str2));
        for(int j =0; j<split2.size(); j++){   
            for(int i =0; i<split1.size(); i++){
            int extra=0;
            if(isAllUppercase(split2.get(j)))
            {
                if(!isAllUppercase(split1.get(i))) extra=distanceShortForm(split2.get(j).trim(),split1.get(i).trim());
            }
            else if(isAllUppercase(split1.get(i))) extra=distanceShortForm(split1.get(i),split2.get(j));
            //else if(noty(s1, s2)) levenDist=0;
            //System.out.println("extra :"+extra+" i: "+i);
            if (extra>90) {
                split1.add(i, split2.get(j));
                split1.remove(i+1);
                }
            }
        }
        permute(split1, 0);
        //System.out.println(java.util.Arrays.toString(permutedList.toArray()));
        split1= permutedList;
        String s2 = joinedString(split2);
        // System.out.println("s2 "+ s2+ " ss "+ split1.size()+  "ps "+ permutedList.size());
      
        int levenDist=0;
        int distSub = new Leven().computeLevenshteinDistance(s2.toLowerCase(), split1.get(0).toLowerCase()); 
        for(int i =1; i<split1.size(); i++){
            levenDist = new Leven().computeLevenshteinDistance(s2.toLowerCase(), split1.get(i).toLowerCase()); 
            //System.out.println("levenDist "+ levenDist);
            if (levenDist < distSub)  distSub= levenDist; 
         }
        
        //System.out.println("dist :"+dist+" extension "+extension);
        int calLength =  str1.length()+extension;
        //System.out.println("dist :"+dist+" extension "+extension+" calLength "+calLength);
        dist=distSub;
        if(distSub> calLength) ratio =0; else ratio =(double)((calLength-distSub))/(double)(calLength);
        System.out.println("dist: "+ distSub);
        return distSub;
    } 
     
    public ArrayList<String> subMe(String str){
        //System.out.println( " str \t"+ str);
        String sub="";
        ArrayList<String> split = new ArrayList<String>();
        str = convertDigitsAsString(str);
       
        int i =0;
            while(i<str.length())
            { 
                while(i<str.length() && !isSpecialChar(str.charAt(i))){
                    sub+=str.charAt(i);
                    i++;
                
                }
                if(!"".equals(sub)) split.add(sub);
                i++;
              
                sub ="";
            }
       
        return split;
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
    
    private int distanceShortForm(String sfmS, String s){
        String[] prefix = new String[]{"Meth","Eth","Prop","But","Pent","Hex","Hept","Oct","Non","Dec","Undec",
            "Dodec","Tridec","Tetradec","Pentadec","Eicos","Triacont","Tetracont","Pentacont"};
        
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
    
    private  ArrayList<String> permuteString(ArrayList<String> str){
        //ArrayList<String> permutedList= new ArrayList<String>();
        permutedList.add(joinedString(str));
        int index = str.size()-1 ;
        int currPos = 0;
        int i=currPos+1;
        while(i < index){
            //System.out.println("i l: "+i+ "ind :"+ index);
            swap(str,currPos, i);
            permutedList.add(joinedString(str));
            i++; 
        }
         //System.out.println("pp1 l: "+permutedList.toString());
        return permutedList;
    }
    
    
   private void permute(ArrayList<String> arr, int k){
        for(int i = k; i < arr.size(); i++){
            java.util.Collections.swap(arr, i, k);
            permute(arr, k+1);
            java.util.Collections.swap(arr, k, i);
        }
        if (k == arr.size() -1){
            //System.out.println(java.util.Arrays.toString(arr.toArray()));
             permutedList.add(joinedString(arr));
        }
    }
    
    private  void doPerm(ArrayList<String> str, int index){
        
        permutedList.add(joinedString(str));
         //System.out.println("index : "+index);
        if(index == 0){
            //permutedList.add(joinedString(str));
            System.out.println("index : "+index+" jstr: "+joinedString(str)); }           
        else { //recursively solve this by placing all other chars at current first pos
            doPerm(str, index-1);
            int currPos = str.size()-index;
            for (int i = currPos+1; i < str.size(); i++) {//start swapping all other chars with current first char
                swap(str,currPos, i);
                //doPerm(str, index-1);
                //swap(str,i, currPos);//restore back my string buffer
            }
            //System.out.println("i : "+index);
        }
    }

    private void swap(ArrayList<String> str, int pos1, int pos2){
        String t1 = str.get(pos1);
        String t2 = str.get(pos2);
        
        str.remove(pos1);
        str.remove(pos2-1);
        str.add(pos1, t2);
        str.add(pos2, t1);
    } 
    
    private String joinedString( ArrayList<String> s){
        
        String temp="";
        for(String si:s) temp+=si;
        return temp;            
    }
    private  ArrayList<String> clsuterString(String[] s){
    
        ArrayList<String> clsuter= new ArrayList<String>();
        int i=0;
        while(i<s.length){
            String temp=s[i];
            if(isPrefix(temp)){ //System.out.println("temp "+temp);
            while(!isPrefix(s[i])&& i<(s.length-1)){
               i++;
               temp+=s[i];
            }}
            i++;
            clsuter.add(temp); 
        }
        return clsuter;
    }
    
     private  ArrayList<String> clsuterString(ArrayList<String> s){
    
         ArrayList<String> clsuter= new ArrayList<String>();
        int i=0;
        while(i<s.size()){
            String temp=s.get(i);
            
               // System.out.println("temp1213 "+temp);
            while(isPrefix(s.get(i))&& i<(s.size()-1)){
               i++;
               temp+=s.get(i);
            }
            //temp+=s.get(i);
            i++;
            temp =removeSpecialChar(temp);
            clsuter.add(temp); 
        }
        return clsuter;
    }
     
     private boolean isPrefix(String s){
    
        return (isNumber(s)||isPrefixNumber(s)||isDir(s)) ? true: false;   
    }
    
    private boolean isDir(String s){
    
        String dir="ABCDEFGHIJKLMNOPQRSTUVDLSRαβdlsr";
        return ((s.length()==1)&&dir.contains(s)) ? true : false;
    }
    private boolean isPrefixNumber(String s){
    
        String[] prefixNumber = new String[]{"mono","di","tri","tetra","penta","hexa","hepta","octa","nona","deca", 
            "icosa", "triaconta", "tetraconta","pentaconta","hexaconta","heptaconta","octaconta","nonaconta",
            "hecta","dicta","tricta","tetracta","pentacta","hexacta","heptacta","octacta","nonacta",
            "kilia","dilia","trilia","tetralia","pentalia","hexalia","heptalia","octalia","nonalia"}; 
    
        return (isMember(prefixNumber, s)) ? true: false;
        
    }
    
    private boolean isNumber(String s){//"twentyfour", "sixglucose"
    
        String[] number = new String[]{"one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
        "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety",
        "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen",
        "hundred", "thousand"}; 
        String temp =s;
        boolean contains;
        do{
            contains=false;
        for(String str: number)
            if (temp.contains(str)) {
                
                temp = temp.replaceAll(str, "") ;
                //System.out.println("temp coR "+ temp);
                contains = true;
                break;
            }
       
            if("".equals(temp))return true;
        } while(contains);
        return false;
        
    }
    
    private boolean isMember(int[] a, int e){
       for(int i=0; i< a.length; i++)
           if(a[i]==e) return true;
       return false;
    }
    
     private boolean isMember(String[] a, String e){
       for(int i=0; i< a.length; i++)
           if(a[i].equalsIgnoreCase(e)) return true;
       return false;
    }
}
