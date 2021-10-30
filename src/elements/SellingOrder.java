package elements;

/**
 * This is the child class of the Order class for selling orders.
 * @author Taha Baturhan Akbulut
 *
 */
public class SellingOrder extends Order implements Comparable<SellingOrder>{
	/**
	 * Constructor for the SellingOrder class.
	 * @param traderID ID of the trader
	 * @param amount Amount of the PQs to be ordered to sell
	 * @param price Price of one PQ to be ordered to sell
	 */
	public SellingOrder(int traderID, double amount, double price) {
		super(traderID, amount, price);
	}

	/**
	 * CompareTo method for sorting the elements of the PriorityQueue in the market.
	 */
	public int compareTo(SellingOrder o) {
		if(this.price<o.price) {
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
