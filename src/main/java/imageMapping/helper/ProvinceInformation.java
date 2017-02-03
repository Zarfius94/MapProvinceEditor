package imageMapping.helper;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a province with all its values.
 * @author Zarfius
 * @version 1.0
 *
 */
public class ProvinceInformation {

	private int id;
	private List<Tuple<Integer, Integer>> coordinates;
	private int colorRGB;
	private String name;

	/**
	 * new province.
	 * @param id id of the province
	 * @param rgb color of the province
	 */
	public ProvinceInformation(int id, int rgb) {
		this(id,(rgb >> 16) & 0x000000FF, (rgb >>8 ) & 0x000000FF, (rgb) & 0x000000FF);
	}

	/**
	 * new province.
	 * @param id id of the province
	 * @param red red color part
	 * @param green green color part
	 * @param blue blue color part
	 */
	public ProvinceInformation(int id, int red, int green, int blue){
		this(id,red,green,blue, "");
	}
	
	/**
	 * new province.
	 * @param id id of the province
	 * @param red red color part
	 * @param green green color part
	 * @param blue blue color part
	 * @param name name of the province
	 */
	public ProvinceInformation(int id, int red, int green, int blue, String name) {
		this.id = id;
		this.coordinates = new ArrayList<Tuple<Integer, Integer>>();
		this.colorRGB = new Color(red, green, blue).getRGB();
		this.name = name;
	}
	
	/**
	 * returns the color of the province.
	 * @return color of the province
	 */
	public int getColor() {
		return this.colorRGB;
	}
	
	/**
	 * returns the id of the province.
	 * @return id of the province
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * add a coordinate to the province.
	 * @param x x-coordinate
	 * @param y y-coordinate
	 */
	public void addCoordinate(int x, int y) {
		Tuple<Integer, Integer> coordinate = new Tuple<Integer, Integer>(x, y);
		this.coordinates.add(coordinate);
	}
	
	/**
	 * returns all coordinates for the province.
	 * @return coordinates of the province
	 */
	public List<Tuple<Integer, Integer>> getCoordinates() {
		return this.coordinates;
	}
	
	/**
	 * returns the name of the province.
	 * @return name of the province
	 */
	public String getName() {
		return this.name;
	}
	
	@Override
	public String toString() {
		String s = "Province Id: " + this.id +", color: " + Integer.toHexString(this.colorRGB) + ", name: " + name;
		if(!this.coordinates.isEmpty()){
			s = s.concat("| following coordinates have been set:");
			for(Tuple<Integer,Integer> t : this.coordinates){
				s = s.concat(" (" + t.x + "|"+t.y+")");
			}
		}
		return s;
	}
}
