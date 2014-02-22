package bowerbird;

public class Main {

	private static Bowerbird bowerbird;
	
	public static void main(String[] args) {
		bowerbird = new Bowerbird();
		bowerbird.doSearch("Electronics", "iPhone");
	}
	
}
