
import java.lang.*;
import java.util.Arrays;
/*
 * Name: Aira Peregrino
 * Date: 2/10/2020
 */

public class RealPolynomial {
	private double[] coefficientArray; 
	//double array for coefficients
	
	private int degree; 
	//integer for degree
	
	private String nicePolyString = "";
	private String polyString = "";
	//two strings, one for a method that returns the polynomial string,
	//and one for a method that returns the polynomial string, but formatted
	//nicely.
	
	
	/*
	 * table of contents:
	 * 
	 * RealPolynomial is the class of real polynomials.
	 * getDegree returns the degree of the polynomial.
	 * getCoefficient takes in an integer i and returns the ith coefficient of the polynomial.
	 * getString returns the polynomial as a string in the form of ax^n + bx^(n-1) + ... + z
	 * getNiceSTring returns the polynomial as a string, but in an easier to read form.
	 * polyScalarmult takes in a real number a and returns a polynomial multiplied by the scalar a.
	 * hornerMethod takes in a real number val and uses Horner's Method to evaluate the polynomial at that point.
	 * newRoot takes in a real number r and returns the polynomial with a new root r.
	 * polyAdd takes in a RealPolynomial and adds it to the polynomial.
	 * interpolatorinator takes in two arrays, a set of x-values and y-values and returns a polynomial that interpolates all of them. 
	 *  
	 */
	public RealPolynomial(String cString) {
		
		String[] cStringArray = cString.split(" "); 
		//the string separates coefficients by spaces
		
		int length = cStringArray.length;
		double[] coefficientArray = new double[length];
		
		int i = 0; 
		//this integer is used to reassign values of the empty double array coefficientArray
		
		double current_double = 0; 
		//this double is the current value to reassign coefficientArray[i] to 
		
		nicePolyString = "";
		polyString = "";
		for(String coeff: cStringArray){
			
			current_double = Double.parseDouble(coeff);
			
			coefficientArray[i] = current_double;
			i = i + 1;
			
			int current_degree = length - i;
			//current_degree is used both for putting together the nice string and the normal string
			if (current_double != 1) {
				if(current_degree>1) {
					nicePolyString = nicePolyString + coeff + "(x^" + (current_degree) + ") + ";
				} else if(current_degree == 1) {
					nicePolyString = nicePolyString + coeff + "x" + " + ";
				} else if(current_degree == 0) {
					nicePolyString = nicePolyString + coeff;
				}
			} else if (current_double == 1) {
				if(current_degree>1) {
					nicePolyString = nicePolyString + "(x^" + (current_degree) + ") + ";
				} else if(current_degree == 1) {
					nicePolyString = nicePolyString + "x" + " + ";
				} else if(current_degree == 0) {
					nicePolyString = nicePolyString + "1";
				}
			}
			if (current_degree!=0) {
				polyString = polyString + coeff + "x^" + (current_degree) + " + ";
			} else if (current_degree == 0) {
				polyString = polyString + coeff + "x^" + (current_degree) + " ";
			}
			
		
		}
		this.coefficientArray = coefficientArray;
		this.degree = coefficientArray.length - 1;
	}

	public int getDegree() { 
		//returns the degree of the polynomial
		
		return degree;
	
	}
	
	public String getNiceString() {
		/*returns a nicer string than getString() does
		 * specifically, it takes into account:
		 * 		whether or not the current coefficient is "1" (will return "x" instead of "1x")
		 * 		whether or not the current degree is "0" (will return "5" instead of "5.0x^0.0")
		 */
		return nicePolyString;
		
	}
	
	public String getString() {
		//returns a string of RealPolynomial in the form of ax^n + bx^(n-1) ... z
		
		return polyString;
		
	}
	
	public double getCoefficient(int n) {
		//returns the nth coefficient in the polynomial
		return coefficientArray[n];
		
	}
	
	public RealPolynomial polyScalarMult(double a) {
		//returns a new polynomial, with every coefficient multiplied by a scalar a
		
		double[] arr = new double[coefficientArray.length];
		for (int i = 0; i<coefficientArray.length; i++) {
			arr[i] = coefficientArray[i];
		}
		int arr_length = arr.length;
		String arr_string = "";
		for(int i = 0; i<arr_length; i++) {
			arr[i] = arr[i]*a;
			arr_string = arr_string + arr[i] + " ";
		}
		RealPolynomial newPoly = new RealPolynomial(arr_string);
		return newPoly;
	}
	
	public double hornerMethod(double val){ //the actual horner's method, given recursively.
		
		double result = coefficientArray[0]; //the iteration starts with "0", the first element of the array
        for (int i = 1; i <= getDegree(); i++){
        	
            result = result * val + coefficientArray[i];
            //the result is multiplied by "x" (the value to be evaluated at)
            //and then added to the next coefficient, the same way one would
            //do it by hand.
        }
        return result; 
        
	}
	
	public RealPolynomial newRoot(double r) {
		//returns a new polynomial, with a new root r
		
		double[] arr = coefficientArray.clone();
		int arr_length = arr.length;
		
		double[] newArr = new double[arr_length+1];
		String newArr_string = "";
			
		for (int i = 0; i < arr.length + 1; i++) {
			if (i == 0) {
				newArr[i] = arr[i];
			} else if (i < arr.length) {
				newArr[i] = arr[i] - (r * arr[i-1]);
			} else {
				newArr[i] = -1 * r * arr[i-1];
			}
			newArr_string = newArr_string + newArr[i] + " ";
		}
		RealPolynomial newRootPoly = new RealPolynomial(newArr_string);
		return newRootPoly;
	}
	
	public RealPolynomial polyAdd(RealPolynomial g) {
		//returns the sum of two polynomials
		int leadingzeroes1D = 0;
		int leadingzeroes2D = 0;
		for (double i : this.coefficientArray) {
			if (i == 0) {
				leadingzeroes1D += 1;
			} else {
				break;
			}
		}
		for (double j : g.coefficientArray) {
			if (j == 0) {
				leadingzeroes2D += 1;
			} else {
				break;
			}
		}
		
		int poly1D = this.degree - leadingzeroes1D;
		int poly2D = g.getDegree() - leadingzeroes2D;
		int higherdegree = 0;
		
		if (poly1D > poly2D) {
			higherdegree = poly1D;
		} else {
			higherdegree = poly2D;
		}
		
		double[] firstarr = new double[this.coefficientArray.length];
		double[] secondarr = new double[g.coefficientArray.length];
		
		String newArr_string = "";
		String final_string = "";
		for (int i = 0; i < higherdegree + 1; i++) {
			int current1 = this.coefficientArray.length - i - 1;
			int current2 = g.coefficientArray.length - i - 1;
			if (current1 >= 0) {
				firstarr[i] = this.getCoefficient(current1);
			}
			if (current2 >= 0) {
				secondarr[i] = g.getCoefficient(current2);
			}
			if (current1 >= 0 && current2 >= 0) {
				newArr_string = newArr_string + (firstarr[i] + secondarr[i]) + " ";
			} else if (current1 >= 0) {
				newArr_string = newArr_string + firstarr[i] + " ";
			} else if (current2 >= 0) {
				newArr_string = newArr_string + secondarr[i] + " ";
			}
		}
		RealPolynomial OAddPoly = new RealPolynomial(newArr_string);
		for (int i = 0; i < OAddPoly.degree + 1; i ++) {
			final_string = final_string + OAddPoly.getCoefficient(OAddPoly.degree - i) + " ";
		}
		RealPolynomial FAddPoly = new RealPolynomial(final_string);
		return FAddPoly;
	}
	
	public static RealPolynomial interpolatorinator(double[] x, double[] y) {
		//returns a polynomial that interpolates the x and y coefficients.
		String blank = "1";
		double a_value = 0;
		
		RealPolynomial wpoly = new RealPolynomial(blank);
		RealPolynomial ppoly = new RealPolynomial(blank);
		/*
		 * even though only wPoly should actually equal 1, the method returns an error when left empty,
		 * so i used "1" as a placeholder until the value actually gets updated to a nonempty string. 
		 */
		for (int i = 0; i < x.length; i++) {
			if (i == 0) {
				wpoly = new RealPolynomial("1");
				ppoly = new RealPolynomial(String.valueOf(y[0]));
			} else {
				wpoly = wpoly.newRoot(x[i-1]);
				a_value = (y[i]-ppoly.hornerMethod(x[i]))/(wpoly.hornerMethod(x[i]));
				ppoly = ppoly.polyAdd(wpoly.polyScalarMult(a_value));
			}
		}
		return ppoly;
	}
	
	public static String approximatorinator(double interval_start, double interval_end, int steps, RealPolynomial AppPoly) {
		double width = (interval_end - interval_start)/steps;
		double act_value = 0;
		double app_value = 0;
		double x_val = 0;
		double max_x = 0;
		double error = 0;
		double max_error = 0;
		
		for (int i = 0; i < steps; i++) {
			x_val = interval_start + width*i;
			
			act_value = Math.sin(x_val);
			
			app_value = AppPoly.hornerMethod(x_val);
			error = app_value - act_value;
			if (Math.abs(error) > Math.abs(max_error)) {
				max_error = app_value - act_value;
				max_x = interval_start + width*i;
			}
		}
		return "Max error found: " + Math.abs(max_error) + ", found at x = " + max_x;
	}

	public static void main(String[] args) {
		RealPolynomial test = new RealPolynomial("1 -1");
		
		double result = 0; 
		for (double x = .9999995; x < 1.0000005; x = x + .00000001) {
			result = Math.pow(test.hornerMethod(x), 3);
			System.out.println("x = " + x + ", f(x) = " + result);
		}
	}
}
