package elements;
/**
 * This is the class for the traders in the market
 * @author Taha Baturhan Akbulut
 *
 */
public class Trader {
	/**
	 * ID of the trader
	 */
	private int id;
	/**
	 * Wallet of the trader
	 */
	private Wallet wallet;
	/**
	 * Counter for the total traders
	 */
	public static int numberOfUsers = 0;
	/**
	 * Constructor of the Trader class
	 * @param dollars
	 * @param coins
	 */
	public Trader(double dollars, double coins) {
		wallet= new Wallet(dollars,coins);
		this.id=numberOfUsers;
		numberOfUsers++;
	}
	/**
	 * Method for giving the sell order into the market after checking some conditions
	 * @param amount Amount of the PQs that is going to be sold
	 * @param price Price of a PQ of the order
	 * @param market Market where all the operations are made
	 * @return
	 */
	public int sell(double amount, double price,Market market) {
		if(wallet.getCoins()>=amount) {
			wallet.blockCoins(amount);
			SellingOrder sellingOrder=new SellingOrder(id,amount,price);
			market.giveSellOrder(sellingOrder);
			
		}
		else {
			Market.nofInvalid++;
			
		}
		return 0;
	}
	/**
	 * Method for giving the buy order into the market after checking some conditions
	 * @param amount Amount of the PQs that is going to be bought
	 * @param price Price of a PQ of the order
	 * @param market Market where all the operations are made
	 * @return
	 */
	public int buy(double amount, double price, Market market) {
		if(wallet.getDollars()>=amount*price) {
			wallet.blockDollars(amount*price);
			BuyingOrder buyingOrder= new BuyingOrder(id,amount,price);
			market.giveBuyOrder(buyingOrder);
		}
		else {
			Market.nofInvalid++;
		}
		
		
		
		return 0;
	}
	
	/**
	 * Getter method for the wallet of the trader
	 * @return wallet of the trader
	 */
	public Wallet getWallet() {
		return wallet;
	}
	/**
	 * Getter method for the ID of the trader
	 * @return ID of the trader
	 */
	public int getID() {
		return id;
	}
	
	/**
	 * Method for deposit operation
	 * @param amount Amount of dollars that is going to be deposited
	 */
	public void depositToWallet(double amount) {
		wallet.setDollars(amount);
	}
	/**
	 * Method for withdraw operation
	 * @param amount Amount of dollars that is going to be withdrawn
	 */
	public void withdrawFromWallet(double amount) {
		if(wallet.getDollars()>=amount)
			wallet.setDollars(-amount);
		else {
			Market.nofInvalid++;
		}
	}
	/**
	 * Method for rewarding the traders
	 * @param amount Amount of rewarded coins
	 */
	public void rewardToTraders(double amount) {
		wallet.setCoins(amount);
	}
	
}
