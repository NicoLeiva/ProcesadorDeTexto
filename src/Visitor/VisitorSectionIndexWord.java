package Visitor;

import java.util.ArrayList;

import Filters.Filter;
import Structure.Element;
import Structure.Paragraph;
import Structure.Section;
import Structure.Sentence;
import Structure.Word;

public class VisitorSectionIndexWord implements Visitor {
	
	private Filter filterType;
	
	public VisitorSectionIndexWord(Filter filterType) {
		this.filterType = filterType;
	}

	@Override
	public Object visitorWord(Word w) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitorSentence(Sentence s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitorParagraph(Paragraph p) {
		// TODO Auto-generated method stub
		return new ArrayList<>();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object visitorSection(Section se) {
		ArrayList<Object> aux = new ArrayList<>();
		String level = "";
		for (int i = 0; i < se.getLevel()-1; i++) {
			level+= "\t";
		}
		aux.add(level + se.getTitle());
		aux.add(se.listar(filterType).size());
		for (Element e : se.getContent()) {
			aux.addAll((ArrayList<Object>)(e.accept(this)));
		}
		return aux;
	}

}
