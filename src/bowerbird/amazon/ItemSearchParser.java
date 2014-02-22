package bowerbird.amazon;

import java.util.ArrayList;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import bowerbird.gpb.BowerbirdMessageProtocolGPB.AmazonItemGPB;
import bowerbird.gpb.BowerbirdMessageProtocolGPB.AmazonItemGPBOrBuilder;

public class ItemSearchParser extends BaseParser{

	private static final String TAG_ITEM = "Item";
	private static final String TAG_ASIN = "ASIN";
	private static final String TAG_TITLE = "Title";
	
	private ArrayList<AmazonItemTransient> results;
	private AmazonItemTransient currentItem;
	private String currentTag;
	
	
	public ItemSearchParser(SignedRequestsHelper helper) {
		super(helper);
		results = new ArrayList<AmazonItemTransient>();
	}
	
	public ArrayList<AmazonItemTransient> performSearch(String index,String keywords) {
		Map<String, String> params = this.baseParameters();
        params.put("Operation", "ItemSearch");
        params.put("SearchIndex",index);
        params.put("Keywords",keywords);
        
        results.clear();
        signAndParse(params);
        return results;
	}
	
	public void startElement(String uri, String localName,String qName, 
            Attributes attributes) throws SAXException {
		if(qName.equals(TAG_ITEM)) {
			currentItem = new AmazonItemTransient();
			currentItem.title = "";
		} 
		currentTag = qName;
	}
	
	public void endElement(String uri, String localName,
			String qName) throws SAXException {
		if(qName.equals(TAG_ITEM)) {
			results.add(currentItem);
			currentItem = null;
		}
	}
	
	public void characters(char ch[], int start, int length) throws SAXException {
		if(currentTag.equals(TAG_ASIN)) {
			currentItem.asin = new String(ch,start,length);
		} else if(currentTag.equals(TAG_TITLE)) {
			currentItem.title+=new String(ch,start,length);
		}
	}

}
