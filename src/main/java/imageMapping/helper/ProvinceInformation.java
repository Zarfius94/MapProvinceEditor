package imageMapping.helper;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class ProvinceInformation {

	private int id;
	private List<Tuple<Integer, Integer>> coordinates;
	private int colorRGB;
	private String name;
	
	public ProvinceInformation(int id, int rgb) {
		this(id,(rgb >> 16) & 0x000000FF, (rgb >>8 ) & 0x000000FF, (rgb) & 0x000000FF);
	}

	public ProvinceInformation(int id, int red, int green, int blue){
		this(id,red,green,blue, "");
	}
	
	public ProvinceInformation(int id, int red, int green, int blue, String name) {
		this.id = id;
		this.coordinates = new ArrayList<Tuple<Integer, Integer>>();
		this.colorRGB = new Color(red, green, blue).getRGB();
		this.name = name;
	}
	
	public int getColor() {
		return this.colorRGB;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void addCoordinate(int x, int y) {
		Tuple<Integer, Integer> coordinate = new Tuple<Integer, Integer>(x, y);
		this.coordinates.add(coordinate);
	}
	
	public List<Tuple<Integer, Integer>> getCoordinates() {
		return this.coordinates;
	}
	
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
