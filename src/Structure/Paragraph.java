package Structure;

import Visitor.Visitor;

public class Paragraph extends ElementCompound {

	public Paragraph() {

	}

	public void addContent(Sentence s) {
		super.addContent(s);
	}

	public String toString() {
		String aux = "";
		for (Element content : getContent()) {
			aux += content.toString();
		}
		return aux + "\n";
	}

	@Override
	public Object accept(Visitor v) {
		return v.visitorParagraph(this);

	}


}
