package Structure;

import java.util.ArrayList;

import Filters.Filter;
import Visitor.Visitor;

public abstract class Element {
	
	public abstract Object accept(Visitor v);
	public abstract ArrayList<Element> getContent();
	public abstract ArrayList<Element> listar(Filter f);

}
