package Filters;

import Structure.Element;
import Structure.Paragraph;

public class FilterParagraph extends Filter{

	public FilterParagraph() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean cumple(Element comp) {
		return comp instanceof Paragraph;
	}
	
}
