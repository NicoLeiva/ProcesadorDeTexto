package Visitor;

import java.util.ArrayList;

import Filters.Filter;
import Structure.Element;
import Structure.Paragraph;
import Structure.Section;
import Structure.Sentence;
import Structure.Word;

public class VisitorSentenceType implements Visitor {
	
	private Filter filter;

	public VisitorSentenceType(Filter filter) {
		this.filter = filter;
	}
	
	@Override
	public Object visitorWord(Word w) {

		return null;
	}

	@Override
	public Object visitorSentence(Sentence s) {
		if (filter.cumple(s)){
			ArrayList<Element> aux = new ArrayList<>();
			aux.add(s);
			return aux;
		}
		return new ArrayList<>();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object visitorParagraph(Paragraph p) {
		ArrayList<Element> aux = new ArrayList<>();
		for (Element e : p.getContent()) {
			aux.addAll((ArrayList<Element>)(e.accept(this)));
			return aux;
		}
		return new ArrayList<>();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object visitorSection(Section se) {
		ArrayList<Element> aux = new ArrayList<>();
		for (Element e : se.getContent()) {
			aux.addAll((ArrayList<Element>)(e.accept(this)));
		}
		return aux;
	}

}
