package bowerbird.amazon;

import java.util.ArrayList;
import bowerbird.gpb.BowerbirdMessageProtocolGPB.ParseResultGBP;

public class AmazonSearchManager {
	
	private ItemSearchParser searchParser;
	private ItemLookupParser lookupParser;
	private SignedRequestsHelper helper;
	
	public AmazonSearchManager() {
		try {
            helper = SignedRequestsHelper.getInstance(Constants.ENDPOINT, Constants.AWS_KEY, Constants.AWS_SECRET);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        searchParser = new ItemSearchParser(helper);
        lookupParser = new ItemLookupParser(helper);
	}
	
	public ArrayList<AmazonItemTransient> performSearch(String category,String query) {
		ArrayList<AmazonItemTransient> results = searchParser.performSearch(category, query);
		return results;

	}
	
}
