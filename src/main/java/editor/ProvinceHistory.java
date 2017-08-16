package editor;

import java.util.ArrayList;
import java.util.List;

import imageMapping.helper.ProvinceInformation;
import titles.Holding;

public class ProvinceHistory {
	
	int maxSettlements;
	List<Holding> holdings;
	ProvinceInformation pi;
	
	public ProvinceHistory(ProvinceInformation pi) {
		this(pi, 3);
	}
	
	public ProvinceHistory(ProvinceInformation pi, int maxSettlements) {
		this.pi = pi;
		this.maxSettlements = maxSettlements;
		this.holdings = new ArrayList<Holding>();
	}
	
	/**
	 * Add a new holding.
	 * @param h holding to add.
	 */
	public void addSettlements(Holding h) {
		this.holdings.add(h);
	}

	/**
	 * @return the maxSettlements
	 */
	public final int getMaxSettlements() {
		return maxSettlements;
	}

	/**
	 * @param maxSettlements the maxSettlements to set
	 */
	public final void setMaxSettlements(int maxSettlements) {
		this.maxSettlements = maxSettlements;
	}

	/**
	 * @return the holdings
	 */
	public final List<Holding> getHoldings() {
		return holdings;
	}

	/**
	 * @return the pi
	 */
	public final ProvinceInformation getPi() {
		return pi;
	}

	
}
