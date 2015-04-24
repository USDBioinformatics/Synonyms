/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package similarity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.sbml.jsbml.Annotation;
import org.sbml.jsbml.CVTerm;
import org.sbml.jsbml.Compartment;
import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.Parameter;
import org.sbml.jsbml.Reaction;
import org.sbml.jsbml.Species;

/**
 *
 * @author Mathialakan.Thavappi
 */
public class SBMLElements {
  /**
     * @param args the command line arguments
     */
    
   private Map<String, String> resources;

    public SBMLElements() {
    }

    public Map<String, String> getResources() {
        return resources;
    }

    public void setResources(Map<String, String> resources) {
        this.resources = resources;
    }
   
   public String getKEGGCompoundId(Species species){
        String id ="";
        //System.out.println("keg......."+species.toString());
        if(species.isSetAnnotation()){ 
            Annotation a = species.getAnnotation();
             if(a.isSetAnnotation()) id = getKEGGCompoundId(a.getCVTerm(0).toString());
        }  
       return id;
        
    }
   
   public void generateResource(Species species){
    resources = getResources(species);
   }
   
   public void generateResource(Reaction reaction){
    resources = getResources(reaction);
   }
   
    public void generateResource(Compartment compartment){
    resources = getResources(compartment);
   }
    
   public Map<String, String> getResources(Species species){
       Map<String, String> temp = new HashMap<String, String>(); 
        if(species.isSetAnnotation()){ 
            Annotation a = species.getAnnotation();
             if(a.isSetAnnotation()) 
                 for(CVTerm cvm : a.getListOfCVTerms()){
                    System.out.println("cvterm :"+ cvm.toString());
                    for( String res: cvm.getResources()){
                        System.out.println("resource: "+ res);
                         temp.putAll(getResources(res));
                    }
                 }
        }  
       return temp;
   }
     
   public Map<String, String> getResources(Compartment compartment){
       Map<String, String> temp = new HashMap<String, String>(); 
        if(compartment.isSetAnnotation()){ 
            Annotation a = compartment.getAnnotation();
             if(a.isSetAnnotation()) 
                 for(CVTerm cvm : a.getListOfCVTerms()){
                    System.out.println("cvterm :"+ cvm.toString());
                    for( String res: cvm.getResources()){
                        System.out.println("resource: "+ res);
                         temp.putAll(getResources(res));
                    }
                 }
        }  
       return temp;
   }
   
    public Map<String, String> getResources(Reaction reaction){
       Map<String, String> temp = new HashMap<String, String>(); 
        if(reaction.isSetAnnotation()){ 
            Annotation a = reaction.getAnnotation();
             if(a.isSetAnnotation()) 
                 for(CVTerm cvm : a.getListOfCVTerms()){
                    System.out.println("cvterm :"+ cvm.toString());
                    for( String res: cvm.getResources()){
                        System.out.println("resource: "+ res);
                         temp.putAll(getResources(res));
                    }
                 }
        }  
       return temp;
   }
    
    public Map<String, String> getResources(String  resource){
       Map<String, String> temp = new HashMap<String, String>(); 
       String[] terms = resource.split(":");
       int length = terms.length;
       temp.put(terms[length-2], terms[length-1]);
       // temp.put(terms[length-2].substring(0, terms[length-2].indexOf(".")), terms[length-1]);
       return temp;
   }
    
    public String getKEGGCompoundId(){  
       return this.resources.get("kegg.compound");  
    }
    
    public String getKEGGCompoundId(String uri){
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
    
    public String getGNOCompoundId(){  
       return this.resources.get("obo.go");  
    }
     
    public String getGOCompoundId(Species species){
        String id ="";
        if(species.isSetAnnotation()){ 
            Annotation a = species.getAnnotation();
             if(a.isSetAnnotation()) id = getGOCompoundId(a.getCVTerm(0).toString());
        }  
       return id;
        
    }
    
    public String getGOCompoundId(String uri){
        String[] uriList= uri.split(",");
        String uriType ="";
        String kegg=""; 
        for (int i=0; i<uriList.length; i++){
            if (uriList[i].contains("go")){
                uriType = "obo.go";
                kegg = uriList[i].substring(uriList[i].indexOf(uriType)+uriType.length()+1);
                
            }  
        }
       return kegg;
        
    }
       
    public String getUniportCompoundId(){  
       return this.resources.get("uniprot");  
    }
    public String getUniportCompoundId(Species species){
        String id ="";
        if(species.isSetAnnotation()){ 
            Annotation a = species.getAnnotation();
             if(a.isSetAnnotation()) id = getUniportCompoundId(a.getCVTerm(0).toString());
        }  
       return id;
        
    }
    
    public String getUniportCompoundId(String uri){
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
    
     public String getPubChemCompoundId(){  
       return this.resources.get("pubchem.compound");  
    } 
    public String getPubChemCompoundId(Species species){
        String id ="";
        if(species.isSetAnnotation()){ 
            Annotation a = species.getAnnotation();
             if(a.isSetAnnotation()) id = getPubChemCompoundId(a.getCVTerm(0).toString());
        }  
       return id;
        
    }
    
    public String getPubChemCompoundId(String uri){
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
    
    public String getKEGGReactionId(){  
       return this.resources.get("kegg.reaction");  
    } 
    
    public String getKEGGReactionId(Reaction reaction){
        String id ="";
        if(reaction.isSetAnnotation()){ 
            Annotation a = reaction.getAnnotation();
            if(a.isSetAnnotation()) 
                id = getKEGGReactionId(a.getCVTerm(0).toString());
        }  
       return id;
        
    }
     
    public String getKEGGReactionId(String uri){
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
   
    public String getECReactionId(){  
       return this.resources.get("ec-code");  
    } 
     
    public String getECReactionId(Reaction reaction){
        String id ="";
        if(reaction.isSetAnnotation()){ 
            Annotation a = reaction.getAnnotation();
            if(a.isSetAnnotation()) 
                id = getECReactionId(a.getCVTerm(0).toString());
        }  
       return id;
        
    }
    
    public String getECReactionId(String uri){
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
    
    public String getGNOReactionId(){  
       return this.resources.get("obo.go");  
    } 
    
    public String getGNOReactionId(Reaction reaction){
        String id ="";
        if(reaction.isSetAnnotation()){ 
            Annotation a = reaction.getAnnotation();
            if(a.isSetAnnotation()) 
                id = getGNOReactionId(a.getCVTerm(0).toString());
        }  
       return id;
        
    }
    
    public String getGNOReactionId(String uri){
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
    
     public String getKEGGParameterId(String uri){
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
    
    public String getBrendaCompartmentId(Compartment compartment){
        String id="";
        if(compartment.isSetAnnotation()){
        Annotation a = compartment.getAnnotation();
            if(a.isSetAnnotation()) id = getBrendaCompartmentId(a.getCVTerm(0).toString());}
        return id;
    }
     
    public String getGNOCompartmentId(Compartment compartment){
        String id="";
        if(compartment.isSetAnnotation()){
        Annotation a = compartment.getAnnotation();
            if(a.isSetAnnotation()) id = getGNOCompartmentId(a.getCVTerm(0).toString());}
        return id;
    }
    
    public String getBrendaCompartmentId(String uri){
        String[] uriList= uri.split(",");
        String uriType ="";
        String uriValue =""; 
        for (int i=0; i<uriList.length; i++){
            if (uriList[i].contains("obo.bto")){
                uriType = "obo.bto";
                uriValue = uriList[i].substring(uriList[i].indexOf(uriType)+uriType.length()+1);
                
            }
        }
       
      return uriValue;   
    }
    
    public String getGNOCompartmentId(String uri){
        String[] uriList= uri.split(",");
        String uriType ="";
        String uriValue=""; 
        for (int i=0; i<uriList.length; i++){
           if (uriList[i].contains("obo.go")){
                uriType = "obo.go";
                uriValue = uriList[i].substring(uriList[i].indexOf(uriType)+uriType.length()+1);
            }
           
        }
       return uriValue;   
    }
    
     public String[] speciesName( Model bm){
        //System.out.println("modelId:  "+bm.getId());
        ListOf<Species> species1 = bm.getListOfSpecies();
        String[] s1 = new String[species1.size()];
        
        for(int i=0; i<species1.size(); i++ ){
            if (!"".equals(species1.get(i).getName())) s1[i] = species1.get(i).getName().trim(); 
            else if (!"".equals(species1.get(i).getId())) s1[i] = species1.get(i).getId().trim();
            //System.out.println("s1..."+s1[i]);
            
        }
        //System.out.println("modelId:  "+bm.getId());
        return s1;
      }
     
    public String[] speciesURI( Model bm){
         ListOf<Species> species2 = bm.getListOfSpecies();
         
         String[] su2 = new String[species2.size()];
        for(int i=0; i<species2.size(); i++ ){
            if(species2.get(i).isSetAnnotation()){ Annotation a1 = species2.get(i).getAnnotation();
            if(a1.isSetAnnotation()) su2[i] = getKEGGCompoundId(a1.getCVTerm(0).toString());
            //System.out.println("cv 2 : "+a1.getCVTerm(0));
            //System.out.println("su2 "+su2[i]);
            }
        } 
        return su2;
      }
      
       public void species(Model bm1,  Model bm2, String mn1, String mn2){
          // writeEcoli(speciesName(bm1), speciesName(bm2), mn1, mn2);
           //writeExlEcoli("E:\\ecoli.xls", (mn1+"_"+mn2), speciesName(bm1), speciesName(bm2), "Leaven Edit Distance" );
           //this.writeExlModels("E:\\SBMLModelsRan.xls", mn1, mn2, speciesName(bm1), speciesName(bm2), speciesURI(bm1), speciesURI(bm2), "Our method", 6, 0.70);
           //this.writeExlModels("E:\\SBMLModels.xls", mn1, mn2, speciesName(bm1), speciesName(bm2), speciesURI(bm1), speciesURI(bm2), "Leaven Edit Distance", 6, 0.70);
           //write3(speciesName(bm1), speciesName(bm2), speciesURI(bm1), speciesURI(bm2),  mn1, mn2 );
           //writeLaven(speciesName(bm1), speciesName(bm2), speciesURI(bm1), speciesURI(bm2), mn1, mn2);
       }
       
      private String[] compartmentURI(Model bm){
      
        ListOf<Compartment> compartment = bm.getListOfCompartments();
        String[] su1 = new String[compartment.size()];
       
        for(int i=0; i<compartment.size(); i++ ){
            Annotation a1 = compartment.get(i).getAnnotation();
            if(a1.isSetAnnotation()) su1[i] = getBrendaCompartmentId(a1.getCVTerm(0).toString());
             //System.out.println("cv : "+a1.getCVTerm(0));
             //System.out.println("su1 "+su1[i]);
        } 
        return su1; 
      }
      
      private String getGOId(String uriId){
          //System.out.println("go "+ uriId.substring(uriId.length()-7, uriId.length()));
          return uriId.substring(uriId.length()-7, uriId.length());
      }
      
      private String[] compartmentName(Model bm){
      
        ListOf<Compartment> compartment = bm.getListOfCompartments();
        String[] s1 = new String[compartment.size()];
        for(int i=0; i<compartment.size(); i++ ){
            if (!"".equals(compartment.get(i).getName())) s1[i] = compartment.get(i).getName().trim(); 
            else if (!"".equals(compartment.get(i).getId())) s1[i] = compartment.get(i).getId().trim();
            //System.out.println("s1 "+s1[i]);
        } 
        return s1;
      }
      
      private void compartment( Model bm1,  Model bm2, String  mn1, String mn2){
         /* try{
         
            GOTerm.term("0005739");
        }catch(Exception ie){
            System.out.println(ie.getMessage());
        }*/
       // writeNew(compartmentName(bm1), compartmentName(bm2), compartmentURI(bm1), compartmentURI(bm2),  mn1, mn2);
      }
      
      private String[] parameterName( Model bm){
      
        ListOf<Parameter> parameter1 = bm.getListOfParameters();
         String[] s1 = new String[parameter1.size()];
       
        for(int i=0; i<parameter1.size(); i++ ){
            if (!"".equals(parameter1.get(i).getName())) s1[i] = parameter1.get(i).getName().trim(); 
            else if (!"".equals(parameter1.get(i).getId())) s1[i] = parameter1.get(i).getId().trim();
            //System.out.println("s1 "+s1[i]);
            
        } 
        return s1;
      }
      
      private String[] parameterURI( Model bm){
         ListOf<Parameter> parameter2 = bm.getListOfParameters();
        
        String[] su2 = new String[parameter2.size()]; 
        
        for(int i=0; i<parameter2.size(); i++ ){
      
            Annotation a1 = parameter2.get(i).getAnnotation();
            if(a1.isSetAnnotation()) su2[i] = getKEGGCompoundId(a1.getCVTerm(0).toString());
             //System.out.println("cv : "+a1.getCVTerm(0));
             //System.out.println("su1 "+su2[i]);
        }
        return su2;
      }
      
      private void parameter( Model bm1,  Model bm2, String  mn1, String mn2){
        //write3(parameterName(bm1), parameterName(bm2), parameterURI(bm1), parameterURI(bm2), mn1, mn2);
      }
      
      private String[] reactionName( Model bm){
        ListOf<Reaction> reaction1 = bm.getListOfReactions();
        String[] r1 = new String[reaction1.size()];
       //System.out.println("rn  "+bm.getNumReactions());
        for(int i=0; i<reaction1.size(); i++ ){
            if (!"".equals(reaction1.get(i).getName())) r1[i] = reaction1.get(i).getName().trim(); 
            else if (!"".equals(reaction1.get(i).getId())) r1[i] = reaction1.get(i).getId().trim();
            //System.out.println("r1 "+r1[i]);
            
        } 
        return r1;
      }
      
      private String[] reactionKEGG( Model bm){
           //System.out.println("Model _ID : "+bm.getId());
        ListOf<Reaction> reaction2 = bm.getListOfReactions();
        
        String[] ru2 = new String[reaction2.size()]; 
        for(int i=0; i<reaction2.size(); i++ ){
            if(reaction2.get(i).isSetAnnotation()){
                Annotation a1 = reaction2.get(i).getAnnotation();
                if(a1.isSetAnnotation()){
                    List<CVTerm> cvterms = a1.getListOfCVTerms();
                    for(CVTerm cvterm: cvterms ){
                        ru2[i] = getKEGGReactionId(cvterm.toString());
                         //System.out.println("cv 2 : "+cvterm.toString());
                         CVTerm.Qualifier q =cvterm.getBiologicalQualifierType();
                        // System.out.println("reactionKEGG : "+q.toString());
                        if(!ru2[i].equals("")) break;
                    }
               
                //System.out.println("ru2 "+ru2[i]);
            }
            }
        }
        return ru2;
      }
      
    private String[] reactionEC( Model bm){
         ListOf<Reaction> reaction2 = bm.getListOfReactions();
        
        String[] ru2 = new String[reaction2.size()]; 
        for(int i=0; i<reaction2.size(); i++ ){
            
            Annotation a1 = reaction2.get(i).getAnnotation();
            if(a1.isSetAnnotation()){ ru2[i] = getECReactionId(a1.getCVTerm(0).toString());
            
            }

        }
        return ru2;
      }
       
    private String[] reactionGNO( Model bm){
         ListOf<Reaction> reaction2 = bm.getListOfReactions();
        
        String[] ru2 = new String[reaction2.size()]; 
        for(int i=0; i<reaction2.size(); i++ ){
            
            Annotation a1 = reaction2.get(i).getAnnotation();
            if(a1.isSetAnnotation()){ ru2[i] = getGNOReactionId(a1.getCVTerm(0).toString());
            //System.out.println("cv 2 : "+a1.getCVTerm(0));
            //System.out.println("ru2 "+ru2[i]);
            }

        }
        return ru2;
      }
       
      private void reaction( Model bm1,  Model bm2, String  mn1, String mn2){
       // write3(reactionName(bm1), reactionName(bm2), reactionKEGG(bm1), reactionKEGG(bm2),reactionEC(bm1), reactionEC(bm2), reactionGNO(bm1), reactionGNO(bm2),mn1, mn2);
        //this.writeExlModels("E:\\SBMLModelsReact.xls", mn1, mn2, reactionName(bm1), reactionName(bm2), reactionKEGG(bm1), reactionKEGG(bm2),reactionEC(bm1), reactionEC(bm2),
          //      reactionGNO(bm1), reactionGNO(bm2), "Our method", 6, 0.70);
      }
}
