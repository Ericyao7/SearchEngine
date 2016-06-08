import java.util.*;



class TrieNode {
	public boolean terminable;  // 是不是单词结尾（即叶子节点）
	public int count;  // 单
	public Map<Character, TrieNode> children = null;
	public String pages = null;
	public boolean isLeaf;
	public TrieNode() {
		 children = new HashMap<Character, TrieNode>();  
		 pages = "";
	}
}


public class Trie {
	private TrieNode root;
	ArrayList<String> results=new ArrayList<String>();
	private char[] characterTable= {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',  // use when traverse
			   'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
	private String pages;
			 
	public Trie() {
		root = new TrieNode();
	}
	
	
	public void insert(String word,String pages) {
		TrieNode node = root;
		word = word.trim();
		for (int i = 0; i < word.length(); i++) {
			if (!(node.children.containsKey(word.charAt(i)))) {
				node.children.put(word.charAt(i), new TrieNode());
			}
			node = node.children.get(word.charAt(i));
		}
		 node.terminable = true;
		 node.count ++;
		 node.pages +=pages+" ";
		
	}

 
	public String find(String word) {
		TrieNode node = root;
		for(int i = 0; i < word.length(); i++) {
			if(!(node.children.containsKey(word.charAt(i)))) {
				return null;
			}else{
				node = node.children.get(word.charAt(i));
			}
		}
		return node.pages;  
	}



	public TrieNode getRoot(){  
		return this.root;  
	}

	
	public void setRoot(TrieNode readFile) {
		// TODO Auto-generated method stub
		root=readFile;
	}
	
	
	public void traverse() {
		  StringBuffer word = new StringBuffer("");
		  TrieNode node = root;
		  traverseTrie(node, word);
		 }
		 
	public void traverseTrie(TrieNode node, StringBuffer word) {
		  if(node.terminable) {
		   System.out.println(word + "------" + node.count);  
		   if(node.children.isEmpty()) return;
		  }
		  for(int i=0; i<characterTable.length; i++) {
		   if(!(node.children.containsKey(characterTable[i]))) continue;
		   traverseTrie(node.children.get(characterTable[i]), word.append(characterTable[i]));
		   word.deleteCharAt(word.length()-1);
		  }
		 }
}


