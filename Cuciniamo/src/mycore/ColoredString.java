package mycore;

import java.awt.Color;
import java.io.Serializable;

/**
 *
 * @author pierpytom
 */
public class ColoredString implements Serializable {
	
	Color color;
	String string;
	
	public ColoredString(String string, Color color) {
		this.string = string;
		this.color = color;
	}
	
	public ColoredString(){
		this.string = "";
		this.color = Color.black;
	}

	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setString(String string) {
		this.string = string;
	}
	
	public String getString() {
		return string;
	}
	
}
