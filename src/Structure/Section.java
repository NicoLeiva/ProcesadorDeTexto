package Structure;

import Visitor.Visitor;

public class Section extends ElementCompound{
	
	private String title;
	private Integer level;
	
	public Section(String title, Integer level){
		this.title = title;
		this.level = level;
	}

	public Integer getLevel(){
		return this.level;
	}
	
	public String toString(){
		String aux = "";
		for (int i = 0; i < level; i++) {
			aux += "\t"; 
		}
		if (!title.equals(""))
			aux += title + "\n";
		for (Element content : getContent()) {
			aux+= content.toString();
		}
		return aux;
	}

	@Override
	public Object accept(Visitor v) {
		return v.visitorSection(this);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title.substring(0, title.length()-1);
	}
}
