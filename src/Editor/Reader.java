package Editor;

public class Reader {
	
	private String str = "";
	private int position = 0;
	
	public Reader(String str, int position) {
		this.str = str;
		this.position = position;
	}
	
	private String readToDelimiter (String del){
		int initPos = position;
		while (str.length() > position) {
			if (!(str.substring(position, position+1).equals(del)))
				position++;
			else
				return str.substring(initPos, position);
		}
		
		if (initPos < str.length())
			return str.substring(initPos, position);
		else
			return "";
	}
	
	public String readTitle() {
		String title = readToDelimiter("\n");
		position++;
		return title;
	}
	
	public String readContent() {
		return readToDelimiter("\t");
	}
	
	public boolean hasToRead() {
		return (str.length() > position);
	}
	
	public int getLevel () {
		int acc = 0;
		int i = position;
		while ((str.length() > i) && (str.substring(i, i+1).equals("\t"))){
			acc++;
			i++;
		}
		return acc;
	}
	
	public void incrementPosition (int lvl){
		position += lvl;
	}
	
}
