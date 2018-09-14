package Filters;

import Structure.Constant;
import Structure.Element;
import Structure.Word;

public class FilterWordType extends Filter {

	private String umbral;
	private Filter fWord;
	
	public FilterWordType(String umbral) {
		this.umbral = umbral;
		fWord = new FilterWord();
	}
	
	@Override
	public boolean cumple(Element comp) {
		if (fWord.cumple(comp)){
			if (umbral.equals(Constant.TODA)){
					return true;
			}
			else
				if (((Word)comp).getType() != null)
					return ((Word)comp).containsType(umbral);
		}
		return false;
	}
}
