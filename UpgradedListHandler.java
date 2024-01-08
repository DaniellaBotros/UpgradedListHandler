package UpgradedListHandler;
import java.io.*; 
import java.util.*;  
import java.lang.*;


class listObj {
        private String listname;
        private int value;
        public listObj(String listname ,int value){
            this.listname=listname;
            this.value=value;
        }

        @Override
        public boolean equals(Object o){
            if (o == this) {
            return true;
        }
        if (!(o instanceof listObj)) {
            return false;
        }

        listObj c = (listObj) o;
     return (c.value == this.value);
    }

    @Override
    public String toString(){
        return this.listname;

    }

    public int getIntegerValue(){
        return this.value;
    }
     @Override
    public int hashCode() 
    { 
        return this.value; 
    } 

}


public class UpgradedListHandler { 

    public static String[] getstrNumbers(String text){
        //Assuming list may begin and end with ',' we remove ','
         String str=text;
        if(text.charAt(0)==','){
            str=str.substring(1, text.length());// end = inputed end index-1
        }
        if(text.charAt(text.length()-1)==','){
            str=str.substring(0, text.length()-1);// end = inputed end index-1
        }
        return str.split( "[\\s,]+");

    }


    public static void writeToFile(String Filename,String str) throws IOException{
        ///////////////////////Write to output files///////////////////////////
        FileWriter fwOb=new FileWriter(new File(Filename));    
        PrintWriter pwOb = new PrintWriter(fwOb, false);
        
        pwOb.write(str);

        pwOb.flush();
        pwOb.close();
        fwOb.close(); 

    }

    

    public static void getEntries (HashMap <listObj,Integer> entries, ArrayList<listObj>list ){
        for (listObj lo : list) {
            if(entries.isEmpty() ||(entries.getOrDefault(lo,-1)==-1) ){
                entries.putIfAbsent(lo,0);
            }
            else{
                entries.replace(lo,(entries.get(lo)+1));
            }
            
        }
  
}


    public static ArrayList<listObj> getListOfObjects(String[] text, String name){
        ArrayList<listObj> list= new ArrayList<listObj>();
       for (int i=0; i< text.length; i++){
        list.add(new listObj(name, Integer.parseInt(text[i])));

       }

       return list;
    }


    public static void main (String[] args){



    }




}