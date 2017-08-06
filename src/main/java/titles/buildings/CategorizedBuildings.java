package titles.buildings;

import java.util.ArrayList;
import java.util.List;

import titles.HoldingType;

/**
 * Collection of buildings for a holding type.
 * @author Zarfius
 *
 */
public class CategorizedBuildings {
	
	private HoldingType category;
	List<Building> buildings;
	
	/**
	 * Create a new category to fill with specific buildings for it.
	 * @param category holding type
	 */
	public CategorizedBuildings(HoldingType category) {
		this.category = category;
		this.buildings = new ArrayList<Building>();
	}
	
	/**
	 * Add a building to the category
	 * @param building Building to be added.
	 */
	public void addBuilding(Building building) {
		this.buildings.add(building);
	}

	/**
	 * @return the category
	 */
	public final HoldingType getCategory() {
		return category;
	}

	/**
	 * @return the buildings
	 */
	public final List<Building> getBuildings() {
		return buildings;
	}

}
