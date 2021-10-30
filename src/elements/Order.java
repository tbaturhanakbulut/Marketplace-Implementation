package elements;
/**
 * This is the main class for the orders in the market
 * @author Taha Baturhan Akbulut
 *
 */
public class Order{
	/**
	 * Amount of the PQ in the order
	 */
	double amount;
	
	/**
	 * Price of the one PQ
	 */
	double price;
	
	/**
	 * ID of the trader who gives this order
	 */
	int traderID;
	/**
	 * Constructor of the Order class
	 * @param traderID ID of the trader who gives this order
	 * @param amount Amount of the PQ in the order
	 * @param price Price of the one PQ
	 */
	public Order(int traderID, double amount, double price) {
		this.traderID=traderID;
		this.price=price;
		this.amount=amount;
	}

	/**
	 * Used for decreasing the some amount of the ordered PQs after a transaction.
	 * @param amount Amount which is going to be decreased
	 */
	public void setAmount(double amount) {
		this.amount-=amount;
	}
	/**
	 * Getter method for the price of the PQ
	 * @return price of the PQ
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * Getter method for the amount of the PQ
	 * @return amount of the PQs
	 */
	public double getAmount() {
		return amount;
	}

	


}

