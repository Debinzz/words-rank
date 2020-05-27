import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.text.html.HTMLDocument.Iterator;

class MyComparator implements Comparator<Map.Entry<String,Integer>>{
    public int compare(HashMap.Entry<String,Integer> he1,HashMap.Entry<String,Integer> he2) {
        return he2.getValue()-he1.getValue();
    }
}

public class Solution{


  public static void main(String[] args) {
	  
	  File a = new File("D:/tmp/a.txt");
	  File b = new File("D:/tmp/b.txt");
	  String line;
	  ArrayList <String> words=new ArrayList<String>();
	  ArrayList <String> lines=new ArrayList<String>();
	  HashMap <String,Integer> map=new HashMap<String,Integer>();
	  
	  try(BufferedReader in=new BufferedReader(new InputStreamReader(new FileInputStream(a)));)
	  {
		  while((line=in.readLine())!=null)
		  {
			  
			  lines.add(line);
			  if(line.length()!=0&&line.charAt(1)!='='&&!line.equals(" "))
			  {
				  String[] word=line.split("\\s+");
				  for(String s:word)
				  {
					  
					  if(!map.containsKey(s))
					  {
						  map.put(s, 1);
						  System.out.println(s);
					  }
					  else
					  {
						  int value=map.get(s);
						  value++;
						  map.put(s, value);
						  //System.out.println("key"+value);
					  }
				  }
			  }
			  System.out.println(line);
		  }
	  }
	  catch(Exception e)
	  {
		  e.printStackTrace();
	  }
	  
	  List<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(map.entrySet());
      Collections.sort(list,new MyComparator());
	  
	  System.out.println("\n通过Map.entrySet遍历key和value");  
//      for(Map.Entry<String, Integer> entry: list)
//      {
//       System.out.println("Key: "+ entry.getKey()+ " Value: "+entry.getValue());
//      }
	  try(BufferedWriter out=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(b)));)
	  {
		  for(Map.Entry<String, Integer> item: list) {
              out.write(item.getKey()+","+item.getValue());
              out.newLine();
          }
	  }
	  catch(Exception e)
	  {
		  e.printStackTrace();
	  }
  }

}