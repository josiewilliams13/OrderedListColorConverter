package project2;
/**
 * The Color Class is used to store information about a particular Color object. Implements 
 * Comparable<e> interface.
 * 
 * @author Josie Williams
 */
public class Color implements Comparable <Color>{

	private int red;
	private int green;
	private int blue;
	private String name;
	private String hexValue;
	
	/**
	 * The string representing colorHexValue in the Color one-parameter constructor has to be in the format #XXXXXX 
	 * in which the "X"s are replaced by a hexadecimal value. String formatted different are considered invalid and 
	 * thrown an IllegalArgumentException. A valid colorHexValue is stored in the class data field hexValue. This 
	 * constructor is used in the main method to gather user input. 
	 * 
	 * @param String colorHexValue, a specific hexadecimal value as type String.
	 * @throws IllegalArgumentException if the colorHexValue is not a valid hexadecimal.
	 */
	public Color ( String colorHexValue ) throws IllegalArgumentException {
		for (int i = 0; i < colorHexValue.length(); i++) {
			if (colorHexValue.charAt(0) != '#' || colorHexValue.length() != 7 || colorHexValue.charAt(i) > 'f') {
				throw new IllegalArgumentException ();
				}
			}
		hexValue = colorHexValue;
		}
	/**
	 * The string representing colorName in the class two-parameter constructor can be anything, including null
	 * or an empty string. colorName is stored in the class data field for name. The colorHexValue has to be in the 
	 * format #XXXXXX in which the "X"s are replaced by a hexadecimal value. String formatted different are 
	 * considered invalid and thrown an IllegalArgumentException. A valid colorHexValue is stored in the class
	 * data field hexValue.
	 * 
	 * @param String colorHexValue, colorName, a specific hexadecimal value as type String and the name of the color.
	 * @throws IllegalArgumentException if the colorHexValue is not a valid hexadecimal.
	 */
	public Color (String colorHexValue, String colorName) throws IllegalArgumentException {
		if (colorHexValue.charAt(0) != '#' || colorHexValue.length() < 7 || colorHexValue.length() > 7) {
			throw new IllegalArgumentException ();
			}
		else {
			hexValue = colorHexValue;
			name = colorName;
		}
	}
	/**
	 * This three-parameter constructor stores three integers corresponding to the RGB values. Each integer must
	 * carry a value that is greater than zero, but less than 255. Each integer value is placed in the corresponding
	 * data field. If any of the values do not complete this conditional an IllegalArgumentException is thrown. 
	 * 
	 * @param int red, int green, int blue, the RGB values of a specific color.
	 * @throws IllegalArgumentException if the RGB values are not valid.
	 */
	public Color ( int red, int green, int blue) throws IllegalArgumentException {
			if (!(red >= 0 && red <= 255 && green >= 0 && green <= 255 && blue >= 0 && blue <= 255)) {
				throw new IllegalArgumentException ();
			}
			else {
				this.red = red;
				this.green = green;
				this.blue = blue;
			}
		}

	/**
	 * The getRed() is an getter method. Returns the value of the red component.
	 * 
	 * @return int red, if returning the red hexadecimal representation of the RGB values.
	 * @return int r, if returning the red RGB value of a hexadecimal.
	 */
	
	int getRed() {
		//check to see if the conversion from RGB to hex is needed.
		if (!(red == 0 && green == 0 && blue == 0) && hexValue == null) {
			hexValue = RGBToHex(red, green, blue);
		}
		//check to see if the conversion from hex to RGB is needed
		if (hexValue != null && red == 0 && green == 0 && blue == 0 ){
			int r = Integer.valueOf ((hexValue.substring (1,3)), 16);		
			return r;
		}
		//if neither is true return the value of blue.		
		return red;		
	}
	/**
	 * The getGreen() is an getter method. Returns the value of the green component.
	 * 
	 * @return int green, if returning the green hexadecimal representation of the RGB values.
	 * @return int g, if returning the green RGB value of a hexadecimal.
	 */
	int getGreen() {
		//check to see if the conversion from RGB to hex is needed.
		if (!(red == 0 && green == 0 && blue == 0) && hexValue == null) {
			hexValue = RGBToHex(red, green, blue);
		}
		//check to see if the conversion from hex to RGB is needed
		if (hexValue != null){
			int g = Integer.valueOf ((hexValue.substring (3,5)), 16);		
			return g;
		}
		//if neither is true return the value of blue.	
		return green;
	}
	/**
	 * The getBlue() is an getter method. Returns the value of the blue component.
	 * 
	 * @return int blue, if returning the blue hexadecimal representation of the RGB values.
	 * @return int b, if returning the blue RGB value of a hexadecimal.
	 */
	int getBlue() {
		//check to see if the conversion from RGB to hex is needed.
		if (!(red == 0 && green == 0 && blue == 0) && hexValue == null) {
			hexValue = RGBToHex(red, green, blue);
		}
		//check to see if the conversion from hex to RGB is needed
		if (hexValue != null && red == 0 && green == 0 && blue == 0 ){
			int b = Integer.valueOf ((hexValue.substring (5,7)), 16);		
			return b;
		}
		//if neither is true return the value of blue.	
		return blue;
		}
	
	/**
	 * The getName() is an getter method. Returns the name of the color Object.
	 * 
	 * @return name of Color object.
	 */
	
	String getName () {
		return name;
	}
	
	/**
	 * The getHexValue() is a getter methods that retrieves the hexadecimal associated the a specific color object.
	 * 
	 * @return the hexadecimal value in upper-case if there is already a String object in the hexValue variable.
	 */
	
	String getHexValue() {
		//if hexValue already has a String in it, return hexValue
		if (!(hexValue == null)) {
			return hexValue.toUpperCase();
		}
		//else call method RGBToHex to get hexValue
		return RGBToHex(red, green, blue);
	}
	/**
	 * The RGBToHex is a method that returns the hexadecimal equivalent of a RGB value.
	 * 
	 * @param int red, int green, int blue, the RGB values of a specific color.
	 * return hexadecimal as a String.
	 */
	
	public String RGBToHex (int red, int green, int blue) {
		// convert red, green, and blue RGB value to a hexadecimal equivalent
		String redHex = (Integer.toHexString(red)).toUpperCase();
		String greenHex = (Integer.toHexString(green).toUpperCase());
		String blueHex = (Integer.toHexString(blue).toUpperCase());
		
		//if the length of the hex value is less than 2, concatenate a 0
		if ((Integer.toHexString(red).length()) < 2) {
			redHex = "0" + (Integer.toHexString(red)).toUpperCase();
		}
		if ((Integer.toHexString(green).length()) < 2) {
			greenHex = "0" + (Integer.toHexString(green).toUpperCase());
		}
		if ((Integer.toHexString(blue).length()) < 2) {
			blueHex = "0" + (Integer.toHexString(blue).toUpperCase());
		}
		
		//combine strings and set equal to hexValue
		String hexValue = ("#" + redHex + greenHex + blueHex);
		//return hexValue
		return hexValue;
	}
	
	/**
	 * This class overrides the Object class equals method. The method returns a string according
	 * to the specified format.
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 * @param Object o, the object that will be set equal to the calling object.
	 * @return true if the hexadecimal value of o is equal to the caller's, false otherwise.
	 */

	@Override
	public boolean equals (Object o) {
		if (o == null) 
			return false;
		
		return this.getHexValue().equalsIgnoreCase(((Color) o).getHexValue());		
	}
	
	/**
	 * This class overrides the Object class toString. Method returns a string according to the
	 * specified format.
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 * @return String, the hexadecimal and RGB values of a specific color object
	 */
	
	@Override
	public String toString () {
		String red = "";
		String green = "";
		String blue = "";
		String str = "";

		//if red is less than 10, concatenate a String with two zero
		if (getRed() < 10) {
			red = "  "+ getRed();
		}
		//else if red is less than 100, concatenate a String with one zero
		else if (getRed() < 100) {
			red = " "+ getRed();
		}
		//else red concatenates an empty String.
		else {
			red = "" + getRed();
		}
		
		//if green is less than 10, concatenate a String with two zero
		if (getGreen() < 10) {
			green = "  "+ getGreen();
		}
		//else if green is less than 100, concatenate a String with one zero
		else if (getGreen() < 100) {
			green = " "+ getGreen();
		}
		//else blue concatenates an empty String.
		else {
			green = "" + getGreen();
		}
		
		//if blue is less than 10, concatenate a String with two zero
		if (getBlue() < 10) {
			blue = "  "+ getBlue();
		}
		//else if blue is less than 100, concatenate a String with one zero
		else if (getBlue() < 100) {
			blue = " "+ getBlue();
		}
		//else blue concatenates an empty String
		else {
			blue = "" + getBlue();
		}
		
		//if name is still at the default value str is set to this string
		if (name == null) {
			str = ("\n" + getHexValue() + ", (" + red + "," + green + "," + blue + ")" + "\n");
		}
		//if name is not at default value str is set to this string
		else {
			str = ("\n" + getHexValue() + ", (" + red + "," + green + "," + blue + "), " + 
		getName()) + "\n";
		}
		//return str
		return str;	
	}
	/**
	 * This class overrides the compareTo() method from the Object class. The method takes one string and
	 * compares the alphanumeric value to the String that called it.
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 * @param Color c, specified object that will be compared to caller.
	 * @return 0 if the two string are equal, else it returns a positive or a negative value.
	 */
	
	@Override
	public int compareTo(Color c) {
		//reference back to the caller and compare to parameter string. return the boolean result.
		return this.getHexValue().compareToIgnoreCase(c.getHexValue());
		}
}
