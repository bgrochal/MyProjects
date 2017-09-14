package pl.bgrochal.turtlepen.models;

public class Variable {

	private String name;
	private double value;
	
	public Variable(String name, double value) {
		this.name = name;
		this.value = value;
	}
	
	public void add(double inc) {
		this.value += inc;
	}
	
	public void sub(double dec) {
		this.value -= dec;
	}
	
	public void mul(double fac) {
		this.value *= fac;
	}
	
	public void div(double divisor) {
		this.value /= divisor;
	}
	
	public void mod(double divisor) {
		this.value %= divisor;
	}
	
	public void cast() {
		this.value = (int)this.value;
	}
	
	public void setValue(double val) {
		this.value = val;
	}
	
	public String getName() {
		return this.name;
	}
	
	public double getValue() {
		return this.value;
	}

}
