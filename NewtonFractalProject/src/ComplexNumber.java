import java.util.*;

public class ComplexNumber {
	
	private double x; //real part of the complex number
	private double y; //imaginary part of the complex number
	
	public ComplexNumber(double u, double v) {
		this.x = x;
		this.y = y;
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
	
	public ComplexNumber times(ComplexNumber other) {
		double newX = this.x * other.getX();
		double newY = this.y * other.getY();
		return new ComplexNumber(newX, newY);
	}
	
	public double arg() {
		return Math.atan2(this.y, this.x);
	}
	
	public double magnitude() {
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}
}
