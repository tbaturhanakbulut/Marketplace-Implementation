package elements;
/**
 * This is the class for the wallet of a trader
 * @author Taha Baturhan Akbulut
 *
 */
public class Wallet {
	/**
	 * Dollars of the trader
	 */
	private double dollars;
	/**
	 * Coins of the trader
	 */
	private double coins;
	/**
	 * Blocked dollars of the trader
	 */
	private double blockedDollars;
	/**
	 * Blocked coins of the trader
	 */
	private double blockedCoins;
	/**
	 * Constructor for the Wallet class
	 * @param dollars Initial dollars of the trader
	 * @param coins Initial coins of the trader
	 */
	public Wallet(double dollars, double coins) {
		this.dollars=dollars;
		this.coins=coins;
		
	}
	/**
	 * Getter method for returning the dollars of the trader
	 * @return dollars
	 */
	public double getDollars() {
		return dollars;
	}
	/**
	 * Getter method for returning the blocked dollars of the trader
	 * @return blocked dollars
	 */
	public double getBlockedDollars() {
		return blockedDollars;
	}
	/**
	 * Setter method for withdraw and deposit
	 * @param amount Amount of dollars which is going to be added or subtracted from the wallet
	 */
	void setDollars(double amount) {
		//setter for withdraw and deposit
		dollars+=amount;
	}
	/**
	 * Getter method for the coins of the trader
	 * @return coins
	 */
	public double getCoins() {
		return coins;
	}
	/**
	 * Getter method for the blocked coins of the trader
	 * @return blocked coins
	 */
	public double getBlockedCoins() {
		return blockedCoins;
	}
	/**
	 * Setter method to add and subtract coins
	 * @param amount Amount of coins which is going to be added or subtracted from the wallet
	 */
	public void setCoins(double amount) {
		coins+=amount;
	}
	/**
	 * Blocking the coins in the wallet
	 * @param amountOfCoins Amount of coins which is going to be blocked
	 */
	void blockCoins(double amountOfCoins) {
		coins-=amountOfCoins;
		blockedCoins+=amountOfCoins;
	}
	/**
	 * Blocking the dollars in the wallet
	 * @param amountOfDollars Amount of dollars which is going to be blocked
	 */
	void blockDollars(double amountOfDollars) {
		dollars-=amountOfDollars;
		blockedDollars+=amountOfDollars;
	}
	/**
	 * After a buy order of the trader this method is called to decrease the blocked dollars
	 * @param amountOfDollars Amount of dollars which is going to be decreased
	 */
	void afterBuy(double amountOfDollars) {
		blockedDollars-=amountOfDollars;
	}
	/**
	 * After a sell order of the trader this method is called to decrease the blocked coins
	 * @param amountOfCoins Amount of coins which is going to be decreased
	 */
	void afterSell(double amountOfCoins) {
		blockedCoins-=amountOfCoins;
	}
}

