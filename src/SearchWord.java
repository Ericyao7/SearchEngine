//Final version
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class SearchWord{
	
	public SearchWord(){
		
	}
	
	public String getPath(){
		File directory = new File("");
		try{
		    //get the current abslute path
		    System.out.println(directory.getAbsolutePath());
		    return directory.getAbsolutePath();
		}catch(Exception e){
			System.out.println("can not get current path");
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public void getResult(String[] str){
		Map<String,Integer> map=new HashMap<String,Integer>(); 
		String findOutput = null;
		String[] arr = null;
		Trie tr=new Trie();
	    ReadFile rf=new ReadFile();
	    
	    tr.setRoot(rf.readFile(getPath()+"/files"));
		//tr.traverse();
		
	    String userInputString = null;
		
		//System.out.println("here "+ str.)
		
		for(int i = 0; i<str.length;i++){
			userInputString = str[i];
			findOutput = tr.find(userInputString);
			if(findOutput!=null){
				System.out.println("The word "+userInputString+" appear in:");
				arr = findOutput.split(" ");
				for(int j = 0;j<arr.length;j++){
					System.out.println("NO."+arr[j]+" page"+" ");
					if(!map.containsKey(arr[j])){
						map.put(arr[j], 1);
					}else{
						map.put(arr[j], map.get(arr[j])+1);
					}
				}
			}else{
				System.out.println("The word "+userInputString+" does not exist in any page");
			}
		}
		
		//Print out the intersection
		System.out.println("");
		
		Iterator iter = map.entrySet().iterator();
		String t1 = null;
		int t2 = 0;
				
		while(iter.hasNext()){
			Map.Entry entry = (Map.Entry) iter.next();
			t1 = (String) entry.getKey();
			t2 = (Integer) entry.getValue();
				
			if(t2>=str.length){
				System.out.println("The intersection page is :"+t1);
				
			}
			//System.out.println(str.length+" "+t1+" "+t2);
					
		}
		
	}
	
	
	public static void main(String[] args){
		//String str = null;
		String[] strArr = null;
		System.out.println("Please input the word you want to ssearch");
		try {
            
            InputStreamReader is_reader = new InputStreamReader(System.in);
            strArr = new BufferedReader(is_reader).readLine().split(" ");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
		SearchWord sc =new SearchWord();
		
		sc.getResult(strArr);
	}
}
