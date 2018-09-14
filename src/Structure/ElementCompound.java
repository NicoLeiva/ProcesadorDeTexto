package Structure;

import java.util.ArrayList;

import Filters.Filter;
import Visitor.Visitor;

public abstract class ElementCompound extends Element {
	
	private ArrayList<Element> content = new ArrayList<>();
	
	public void addContent (Element e){
		if (e != null)
			content.add(e);
	}
	
	public ArrayList<Element> getContent (){
		return new ArrayList<>(content);
	}
	
	public void setContent (ArrayList<Element> content){
		this.content = content;
	}
	
	@SuppressWarnings("unchecked")
	public Object visitorPart (Visitor v){
		ArrayList<Object> elems = new ArrayList<>();
		for (Element e : content) {
			elems.addAll((ArrayList<Object>)(e.accept(v)));
		}
		return elems;
	}
	
	@Override
	public ArrayList<Element> listar(Filter f) {
		ArrayList<Element> out = new ArrayList<Element>();
		if (f.cumple(this)){
			out.add(this);
		}
		else{
			for (Element element : content){
				out.addAll(element.listar(f));
			}
		}
		return out;
	}
}
