package bowerbird;

import java.util.ArrayList;

import bowerbird.amazon.AmazonItemTransient;
import bowerbird.amazon.AmazonSearchManager;

public class Bowerbird {

	public AmazonSearchManager amazon;
	
	public Bowerbird() {
		amazon = new AmazonSearchManager();
	}
	
	public void doSearch(String category,String term) {
		ArrayList<AmazonItemTransient> itemsAmazon = amazon.performSearch(category,term);
		for(AmazonItemTransient item: itemsAmazon) {
			System.out.println("Got item " + item);
		}
	}
	
}
