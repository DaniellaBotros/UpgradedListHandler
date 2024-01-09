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

    public static String[] getSummaryArrayStrings( HashMap <String,Integer> sumMap){
        return sumMap.keySet().toArray(new String[0]);
       
    }

    public static String getSummaryStr(HashMap <String,Integer> sumMap){
         String text="Entry"+'\t'+":"+'\t'+"Count"+'\n';
        for (String str : sumMap.keySet()){
            
            text=text+str+'\t'+":"+'\t'+sumMap.get(str)+'\n';

        }
        return text;
    }


    public static HashMap <String,Integer> getSummary(String [] values){
        HashMap <String,Integer> sumMap= new  HashMap <String,Integer>();
        for(int i=0; i<values.length; i++){
            if(sumMap.isEmpty() ||(sumMap.getOrDefault(values[i],-1)==-1) ){
                sumMap.putIfAbsent(values[i],0);
            }
            else{
                sumMap.put(values[i],sumMap.get(values[i])+1);
            }

        }
        return sumMap;
       
    }

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

    public static String [] readFile(String Filename)throws IOException{
        String[] result = new String[2];
        Scanner reader = new Scanner(new File (Filename));
        result[0]=reader.nextLine();//listname
        String text="";
        while(reader.hasNextLine()){
            text=text+reader.nextLine();//list
        }
        reader.close();
        result[1]=text;
        return result;

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
//Command Options [ConvTR,CleanSpaces,CleanDup,AutoCompair,ConvRT,Sumamary]
/*
Requirments
Do the below steps separately if needed or Automatically:

1. Per list Table Column To Comma separated Row Conversion[If needed by user] (ConvTR)
2. Per List Clean unnecessary spaces between Commas(CleanSpaces)
3. Per list Clean List from duplication (CleanDup)
4. Automatic Comparison (AutoCompair)
5. Per list Comma Separated Row To Table Column Conversion[If needed by user] (ConvRT)

 */

Boolean workreq=true;
String [] values; 
String [] returnedResult;
 
while(workreq){
String text="";
String Filename="";
String listname=""; 
String text1="";
String listname1=""; 
System.out.println("What would you like me to do [ConvTR,CleanSpaces,CleanDup,AutoCompair,ConvRT,Sumamary] ?");
Scanner sc = new Scanner(System.in);
String command=sc.nextLine();
switch (command){

case "Sumamary":
try{
        System.out.println("Enter file name or input d to use default file and list name:");
        Filename=sc.nextLine();
        if(Filename=="d"){
            Filename="input List1.txt";
            listname="List";
        }
        
        returnedResult=readFile(Filename);
        listname=returnedResult[0]+'\n';
        text=returnedResult[1];
        values=getstrNumbers(text);
        String str=getSummaryStr(getSummary(values));
        writeToFile(Filename,listname+str);
        
    
    
    }
        catch(Exception e){
System.out.println(e.getMessage());
}

break;


    case "ConvTR":
try{
        System.out.println("Enter file name or input d to use default file:");
        Filename=sc.nextLine();
        if(Filename=="d"){
            Filename="input List1.txt";
        }
        returnedResult=readFile(Filename);
         listname=returnedResult[0]+'\n';
        text=returnedResult[1];
        values=getstrNumbers(text);
        text=String.join(",", values);
         writeToFile(Filename,listname+text);

}
catch(Exception e){
System.out.println(e.getMessage());
}

    break;


    case "CleanSpaces":
    try{
     
        System.out.println("Enter file name or input d to use default file:");
        Filename=sc.nextLine();
        if(Filename=="d"){
            Filename="input List1.txt";
        }
        System.out.println("Enter delimiter option (,,t,n,s,or anyother):");
        String delimeter=sc.nextLine();
        if(delimeter=="t"){
            delimeter="\t";

        }
         if(delimeter=="n"){
            delimeter="\n";
        }
         if(delimeter=="s"){
            delimeter=" ";
        }
        returnedResult=readFile(Filename);
        listname=returnedResult[0]+'\n';
        text=returnedResult[1];
        values=getstrNumbers(text);
        text=String.join(delimeter, values);
        writeToFile(Filename,listname+text);
}
catch(Exception e){
System.out.println(e.getMessage());
}
    break;


    case "CleanDup":
        System.out.println("Enter file name or input d to use default file:");
        Filename=sc.nextLine();
        if(Filename=="d"){
            Filename="input List1.txt";
        }
        try{
        returnedResult=readFile(Filename);
        listname=returnedResult[0]+'\n';
        text=returnedResult[1];
    System.out.println("Enter delimiter option (,,t,n,s,or anyother):");
        String delimeter=sc.nextLine();
        if(delimeter=="t"){
            delimeter="\t";

        }
         if(delimeter=="n"){
            delimeter="\n";
        }
         if(delimeter=="s"){
            delimeter=" ";
        }
        values=getSummaryArrayStrings(getSummary(getstrNumbers(text)));
        text=String.join(delimeter, values);
        writeToFile(Filename,listname+text);
        

    }catch(Exception e){
System.out.println(e.getMessage());
}

    break;


    case "AutoCompair":
    HashMap<listObj,Integer> entries = new HashMap <listObj,Integer>();
    System.out.println("For Auto Compare insert value in default input files then press y when you are ready");
    String run=sc.nextLine();
    if(run=="y"){

try{
        returnedResult=readFile("input List1.txt");
        listname=returnedResult[0]+'\n';
        text=returnedResult[1];
        ArrayList<listObj>list=getListOfObjects(getSummaryArrayStrings(getSummary(getstrNumbers(text))),listname);
        returnedResult=readFile("input List2.txt");
        listname1=returnedResult[0]+'\n';
        text1=returnedResult[1];
        ArrayList<listObj>list1=getListOfObjects(getSummaryArrayStrings(getSummary(getstrNumbers(text1))),listname1);

        String strRepeated= "Repeated in "+listname+" and "+listname1+":"+'\n';;
        String strUnique="Unique in "+listname+":"+'\n';
        String strUnique1="Unique in "+listname1+":"+'\n';
    
        for(Map.Entry<listObj, Integer> m : entries.entrySet()){
            if(m.getValue()>0){
                strRepeated=strRepeated+m.getKey().getIntegerValue()+",";
            }
            else if ((m.getValue()==0)&& (m.getKey().toString().equals(listname))) {
                strUnique=strUnique+m.getKey().getIntegerValue()+",";
            }
            else if((m.getValue()==0)&& (m.getKey().toString().equals(listname1))){
                strUnique1=strUnique1+m.getKey().getIntegerValue()+",";
            }   
        }
        strUnique=strUnique+'\n'+strUnique1;

        
        writeToFile("Repeated.txt",strRepeated);

        writeToFile("Unique.txt",strUnique);


      
   
    System.out.println("Auto compare is complete!!");




    }
    catch(Exception e){
       System.out.println(e.getMessage()); 
    }

    }
 



    break;

    case "ConvRT":
    try{
        System.out.println("Enter file name or input d to use default file:");
        Filename=sc.nextLine();
        if(Filename=="d"){
            Filename="input List1.txt";
        }
        returnedResult=readFile(Filename);
        listname=returnedResult[0]+'\n';
        text=returnedResult[1];
        values=getstrNumbers(text);
        text=String.join("\n", values);
        writeToFile(Filename,listname+text);
}
catch(Exception e){
System.out.println(e.getMessage());
}
    break;

    default:
    System.out.println("In-correct Command!! try Again!!");
    break;
}

System.out.println("Are you done? (y/n)");
String done=sc.nextLine();
if(done=="y"){
workreq=false;
}
sc.close();

}







    }




}