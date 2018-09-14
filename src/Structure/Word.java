package Structure;

import java.util.ArrayList;

import Filters.Filter;
import Visitor.Visitor;

public class Word extends Element{

	private String text;
	private ArrayList<String> type;
	
	public Word(String text, ArrayList<String> type) {
		this.text = text;
		this.type = type;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public ArrayList<String> getType(){
		if (type == null)
			return null;
		return new ArrayList<>(type);
	}
	
	public boolean containsType(String type) {
		return this.type.contains(type);
	}
	
	public String toString(){
		return text.toString();
	}

	@Override
	public Object accept(Visitor v) {
		return v.visitorWord(this);
	}
	

	@Override
	public ArrayList<Element> getContent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Element> listar(Filter f) {
		ArrayList<Element> out = new ArrayList<Element>();
		if (f.cumple(this))
			 out.add(this);
		return out;
	}
}
