package Filters;

import Structure.Element;
import Structure.Sentence;

public class FilterSentence extends Filter{

	public FilterSentence() {
		
	}
	
	@Override
	public boolean cumple(Element comp) {
		return comp instanceof Sentence;
	}
	
}
