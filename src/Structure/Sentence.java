package Structure;

import Visitor.Visitor;

public class Sentence extends ElementCompound{
	
	public Sentence(){
	}
	
	public void addContent (Word w){
		super.addContent(w);
	}
	
	public String toString(){
		String aux = "";
		int cant = getContent().size() - 1;
		for (int i = 0; i < getContent().size(); i++) {
			if (i < cant)
				aux += getContent().get(i).toString() + " ";
			else
				aux += getContent().get(i).toString();
		}
		return aux + " ";
	}

	@Override
	public Object accept(Visitor v) {
		return v.visitorSentence(this);
	}
}
