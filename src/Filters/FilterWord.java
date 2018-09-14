package Filters;

import Structure.Element;
import Structure.Word;

public class FilterWord extends Filter{

	public FilterWord() {
		
	}
	
	@Override
	public boolean cumple(Element comp) {
		return comp instanceof Word;
	}
	
}
