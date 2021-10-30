package elements;
/**
 * This is the class for the transactions between the buying orders and the selling orders
 * @author Taha Baturhan Akbulut
 *
 */
public class Transaction {
	/**
	 * Selling order holder
	 */
	private SellingOrder sellingOrder;
	/**
	 * Buying order holder
	 */
	private BuyingOrder buyingOrder;
	
	/**
	 * Constructor for the BuyingOrder class
	 * @param sellingOrder Selling order holder
	 * @param buyingOrder Buying order holder
	 */
	public Transaction(SellingOrder sellingOrder,BuyingOrder buyingOrder) {
		this.sellingOrder=sellingOrder;
		this.buyingOrder=buyingOrder;
		
	}
}
