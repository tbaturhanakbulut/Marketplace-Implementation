package elements;

/**
 * This is the child class of the Order class for buying orders.
 * @author Taha Baturhan Akbulut
 *
 */
public class BuyingOrder extends Order implements Comparable<BuyingOrder> {
	/**
	 * Constructor of the BuyingOrder class
	 * @param traderID ID of the trader
	 * @param amount Amount of the PQs to be ordered to buy
	 * @param price Price of one PQ to be ordered to buy
	 */
	public BuyingOrder(int traderID, double amount, double price) {
		super(traderID, amount, price);
		// TODO Auto-generated constructor stub
	}
	/**
	 * CompareTo method for sorting the elements of the PriorityQueue in the market.
	 */
	public int compareTo(BuyingOrder o) {
		if(this.price>o.price) {
			return -1;
		}
		else if(this.price==o.price) {
			if(this.amount>o.amount) {
				return -2;
			}
			else if(this.amount<o.amount) {
				return 2;
			}
			else {
				if(this.traderID<o.traderID) {
					return -3;
				}
				else{
					return 3;
				}
			}
		}
		else {
			return 1;
		}
		
	}

}
