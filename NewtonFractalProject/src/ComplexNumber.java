import java.util.*;

public class ComplexNumber {
	
	private double x; //real part of the complex number
	private double y; //imaginary part of the complex number
	
	public ComplexNumber(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public String toString() {
		return this.x + " + " + this.y + "i ";
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public ComplexNumber plus(ComplexNumber other) {
		double newX = this.x + other.getX();
		double newY = this.y + other.getY();
		return new ComplexNumber(newX, newY);
	}
	
	public ComplexNumber minus(ComplexNumber other) {
		double newX = this.x - other.getX();
		double newY = this.y - other.getY();
		return new ComplexNumber(newX, newY);
	}
	
	public ComplexNumber times(ComplexNumber other) {
		double newX = this.x*other.getX() - this.y*other.getY();
		double newY = this.x*other.getY() + this.y*other.getX();
		return new ComplexNumber(newX, newY);
	}
	
	public ComplexNumber divide(ComplexNumber other) {
		double newX = (this.x*other.getX()+this.y*other.getY())/(Math.pow(other.getX(),2) + Math.pow(other.getY(),2));
		double newY = (this.y*other.getX()-this.x*other.getY())/(Math.pow(other.getX(),2) + Math.pow(other.getY(),2));
		return new ComplexNumber(newX, newY);
	}
	
	public double arg() {
		return Math.atan2(this.y, this.x);
	}
	
	public double magnitude() {
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}
	
	public ComplexNumber scale(double a) {
		return new ComplexNumber(a*this.x, a*this.y);
	}
	
	public ComplexNumber reciprocal() {
		double scale = this.x*this.x+this.y*this.y;
		return new ComplexNumber(this.x/scale, -1*this.y/scale);
	}

	public ComplexNumber exp() {
		return new ComplexNumber(Math.exp(this.x) * Math.cos(this.y), Math.exp(this.x) * Math.sin(this.y));
	}
	
	public ComplexNumber sin() {
		return new ComplexNumber(Math.sin(this.x) * Math.cosh(this.y), Math.cos(this.x) * Math.sinh(this.y));
	}
	
	public ComplexNumber cos() {
		return new ComplexNumber(Math.cos(this.x) * Math.cosh(this.y), -1*Math.sin(this.x) * Math.sinh(this.y));
	}
	
	public ComplexNumber tan() {
		return sin().divide(cos());
	}
	
	public static void main(String[] args) {
		ComplexNumber test = new ComplexNumber(1, 2);
		ComplexNumber test2 = new ComplexNumber(3, 4);
		System.out.println(test.tan());
	}
}
