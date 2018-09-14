package Visitor;

import java.util.ArrayList;

import Filters.Filter;
import Structure.Element;
import Structure.Paragraph;
import Structure.Section;
import Structure.Sentence;
import Structure.Word;

public class VisitorSectionType implements Visitor {
	
	private Filter filter;

	public VisitorSectionType(Filter filtro) {
		this.filter = filtro;
	}
	
	@Override
	public Object visitorWord(Word w) {
		return null;
	}

	@Override
	public Object visitorSentence(Sentence s) {
		return null;
	}

	@Override
	public Object visitorParagraph(Paragraph p) {
		return null;
	}

	@Override
	public Object visitorSection(Section se) {
		ArrayList<Element> elems = new ArrayList<>();
		if (filter.cumple(se))
			elems.add(se);
		return elems;
	}
}
