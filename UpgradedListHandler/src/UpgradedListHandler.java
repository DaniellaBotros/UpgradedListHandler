
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
                sumMap.putIfAbsent(values[i],1);
            }
            else{
                sumMap.put(values[i],sumMap.get(values[i])+1);
            }

        }
        return sumMap;
       
    }

    


     public static ArrayList<String> readFile(String Filename)throws IOException{
        
        ArrayList<String> result = new ArrayList<>();
        Scanner reader = new Scanner(new File (Filename));
        
        reader.useDelimiter("[\\s,]+");

        result.add(reader.next());//listname
    
        while(reader.hasNext()){
    
           result.add(reader.next());//list
            
      
           
        }
        reader.close();
        
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
//Command Options [ConvTR,CleanSpaces,CleanDup,AutoCompair,ConvRT,Summary,SortNum]
/*
Requirments

1. Per list Table Column or any spacey delimiter with or without comma To Comma only separated Row Conversion[If needed by user for any string] (ConvR)
2. Per list Clean List from duplicated numbers only and put wanted dlimeter [For any string]  (CleanDup)
3. Automatic Comparison [For any string](AutoCompair)
4. Per list Comma Separated Row or any spacy seperation with or without comma To Table Column Conversion[If needed by user for any string] (ConvT)
5. get summary with count for any entered data with any delimiter in between [For any string] (Summary)
6. Sort list of numbers [numbers only] (SortNum)

Notice:
list name cannot contain spaces

 */

Boolean workreq=true;
String [] values; 
ArrayList<String> returnedResult;
 
while(workreq){
String text="";
String Filename="";
String listname=""; 
String text1="";
String listname1=""; 
System.out.println("What would you like me to do [ConvR,CleanDup,AutoCompair,ConvT,Summary,SortNum] ?");
Scanner sc = new Scanner(System.in);
String command=sc.nextLine();
switch (command){

case "Summary":
try{
        System.out.println("Enter file name or input d to use default file and list name:");
        Filename=sc.nextLine();
        if(Filename.equals("d")){
            Filename="input List1.txt";
            listname="List";
        }
        
        returnedResult=readFile(Filename);
        listname=returnedResult.get(0)+'\n';
        returnedResult.remove(0);
        values=returnedResult.toArray(new String[returnedResult.size()]);
        String str=getSummaryStr(getSummary(values));
        writeToFile(Filename,listname+str);
        
    
    
    }
        catch(Exception e){
System.out.println(e.getMessage());
}

break;


    case "ConvR":
try{
        System.out.println("Enter file name or input d to use default file:");
        Filename=sc.nextLine();
        if(Filename.equals("d")){
            Filename="input List1.txt";
        }
        returnedResult=readFile(Filename);
        listname=returnedResult.get(0)+'\n';
        returnedResult.remove(0);
        values=returnedResult.toArray(new String[returnedResult.size()]);
        text=String.join(",", values);

         writeToFile(Filename,listname+text);
        System.out.println("conversion to Row done in the file "+Filename+" !!");
}
catch(Exception e){
System.out.println(e.getMessage());
}

    break;


    case "CleanDup":
        System.out.println("Enter file name or input d to use default file:");
        Filename=sc.nextLine();
        if(Filename.equals("d")){
            Filename="input List1.txt";
        }
        try{
        returnedResult=readFile(Filename);
        listname=returnedResult.get(0)+'\n';
        returnedResult.remove(0);
        values=returnedResult.toArray(new String[returnedResult.size()]);
    System.out.println("Enter delimiter option (,,n,s,or anyother):");
        String delimeter=sc.nextLine();

         if(delimeter.equals("n")){
            delimeter= System.getProperty("line.separator");
        }
         if(delimeter.equals("s")){
            delimeter=" ";
        }
        values=getSummaryArrayStrings(getSummary(values));
        text=String.join(delimeter, values);
        writeToFile(Filename,listname+text);
        System.out.println("Done Cleaning and removing Duplicate in the file "+Filename+" !!");

    }catch(Exception e){
System.out.println(e.getMessage());
}

    break;


    case "AutoCompair":
    HashMap<listObj,Integer> entries = new HashMap <listObj,Integer>();
    System.out.println("Enter the 1st file name or input d to use default file:");
        Filename=sc.nextLine();
        if(Filename.equals("d")){
            Filename="input List1.txt";
        }
        try{

    returnedResult=readFile(Filename);
    listname=returnedResult.get(0);
    returnedResult.remove(0);
    values=returnedResult.toArray(new String[returnedResult.size()]);
    values=getSummaryArrayStrings(getSummary(values));
   ArrayList<listObj> list=getListOfObjects(values, listname);

System.out.println("Enter the 2nd file name or input d to use default file:");
        Filename=sc.nextLine();
        if(Filename.equals("d")){
            Filename="input List2.txt";
        }
    returnedResult=readFile(Filename);
    listname1=returnedResult.get(0);
    returnedResult.remove(0);
    values=returnedResult.toArray(new String[returnedResult.size()]);
    values=getSummaryArrayStrings(getSummary(values));
   ArrayList<listObj> list1=getListOfObjects(values, listname1);

    getEntries(entries,list);//get entries from list
    getEntries(entries,list1);//get entries from list1

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

  break;

case "SortNum":
        System.out.println("Enter file name or input d to use default file:");
        Filename=sc.nextLine();
        if(Filename.equals("d")){
            Filename="input List1.txt";
        }
        try{
        returnedResult=readFile(Filename);
        listname=returnedResult.get(0)+'\n';
        returnedResult.remove(0);
        int[] integerValues=new int[returnedResult.size()];
        for (int i=0; i<returnedResult.size();i++){
            integerValues[i]=Integer.parseInt(returnedResult.get(i));

        }
        Arrays.sort(integerValues);
        System.out.println("Enter delimiter option (,,n,s,or anyother):");
        String delimeter=sc.nextLine();

         if(delimeter.equals("n")){
            delimeter= System.getProperty("line.separator");
        }
         if(delimeter.equals("s")){
            delimeter=" ";
        }
        text="";
        for(int i=0; i<integerValues.length;i++){
            text=text+integerValues[i]+delimeter;
        }
        writeToFile(Filename,listname+text);
        System.out.println("Sort is Complete in the file "+Filename+ "!!");

    }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
 

break;


    case "ConvT":
    try{
        System.out.println("Enter file name or input d to use default file:");
        Filename=sc.nextLine();
        if(Filename.equals("d")){
            Filename="input List1.txt";
        }
        returnedResult=readFile(Filename);
        listname=returnedResult.get(0)+'\n';
        returnedResult.remove(0);
        values=returnedResult.toArray(new String[returnedResult.size()]);
        String delimeter = System.getProperty("line.separator");
        text=String.join(delimeter, values);
        writeToFile(Filename,listname+text);
        System.out.println("conversion to table done in the file "+Filename+" !!");
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
if(done.equals("y")){
workreq=false;
sc.close();
}


}







    }




}