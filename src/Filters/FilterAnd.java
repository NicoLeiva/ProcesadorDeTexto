package Filters;

import Structure.Element;

public class FilterAnd extends FilterCompound{
	
	public FilterAnd(Filter f1, Filter f2) {
		super(f1, f2);
	}

	@Override
	public boolean cumple(Element comp) {
		return getF1().cumple(comp) && getF2().cumple(comp);
	}

}
