package Filters;

public abstract class FilterCompound extends Filter {
	
	private Filter f1;
	private Filter f2;	
	
	protected FilterCompound (Filter f1, Filter f2){
		this.f1 = f1;
		this.f2 = f2;
	}
	
	public Filter getF1() {
		return f1;
	}

	public void setF1(Filter f1) {
		this.f1 = f1;
	}

	public Filter getF2() {
		return f2;
	}

	public void setF2(Filter f2) {
		this.f2 = f2;
	}
}
