package Filters;

import Structure.Element;

public class FilterQuantityToList extends Filter {

	private Integer umbral;
	private Filter filter;
	private int resultadoEsperado;

	public FilterQuantityToList(Filter filter, int resultadoEsperado, int umbral) {
		this.umbral = umbral;
		this.filter = filter;
		this.resultadoEsperado = resultadoEsperado;
	}

	public boolean cumple(Element comp){
		return ((Integer) comp.listar(filter).size()).compareTo(umbral) == resultadoEsperado;
	}
}