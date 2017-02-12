package titles;

public class Title {

	private TitleRank rank;
	private String name;
	private Title liege;
	private String id;

	public Title(TitleRank rank, String name, Title liege) {
		if (liege != null) {
			if (liege.getRank().compareTo(rank) < 1) {
				throw new IllegalArgumentException("Liege rank must be higher than the own rank");
			}
		} else if(rank == TitleRank.BARONY) {
			throw new IllegalArgumentException("Baronies always need a liege");
		}
		this.rank = rank;
		this.id = (rank.toString().charAt(0) + "_" + name).toLowerCase();
		this.name = name;
	}

	/**
	 * @return the liege
	 */
	public final Title getLiege() {
		return liege;
	}

	/**
	 * @return the id
	 */
	public final String getId() {
		return id;
	}

	/**
	 * @return the rank
	 */
	public final TitleRank getRank() {
		return rank;
	}

	/**
	 * @return the name
	 */
	public final String getName() {
		return name;
	}
	
}
