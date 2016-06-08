import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ReadFile {
	private static ArrayList filelist = new ArrayList(); 
	private Trie tr=new Trie();
	
	
	public static ArrayList<File> getFileList(String strPath) {
		File dir = new File(strPath);
		File[] files = dir.listFiles(); // put all the files in this directory in
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				String fileName = files[i].getName();
				if (files[i].isDirectory()) { // see if it is a dir
					getFileList(files[i].getAbsolutePath()); // get the absolute path
				} else if (fileName.endsWith("txt")) { // if it is a txt document
					String strFileName = files[i].getAbsolutePath();
					System.out.println("---" + strFileName);
					filelist.add(files[i]);
				} else {
					continue;
				}
			}

		}
		return filelist;
	}
	
	
	public TrieNode readFile(String filepath){
	    String word[];
		System.out.println("readFile path "+ filepath);
		boolean space=true;
		String lineTxt=null;
		int pages = 1;
		//System.out.println("jinlai le ");
		filelist = getFileList(filepath);
		for(int j = 0;j < filelist.size(); j ++){
			//System.out.println("dfdfdfdf  "+filelist.get(j));
			try{
				//Read the file
				File file=new File(filelist.get(j).toString());
				if(file.isFile()&&file.exists()){
					InputStreamReader read=new InputStreamReader(new FileInputStream(file),"UTF-8");
					BufferedReader bufferedReader=new BufferedReader(read);
					
					
					//pre-process the string
					while((lineTxt=bufferedReader.readLine())!=null){
						word = lineTxt.split(" ");
						for(int i = 0;i<word.length;i++){
							if(word[i].charAt(0)>'a'||word[i].charAt(0)<'z'){
								//Read the file and store in the trie
								//System.out.println("word "+word[i]);
								tr.insert(word[i],Integer.toString(pages));
							}
						}
						
					}
					read.close();
				}else {
					System.out.println("File not found");
				}
			}catch (Exception e) {
				System.out.println("Error");
				e.printStackTrace();
			}
			pages++;
		}
		
		
		
		return tr.getRoot();
	}
	
	
}
