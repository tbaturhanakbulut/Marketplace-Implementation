package elements;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * This is the class for the market.
 * @author Taha Baturhan Akbulut
 *
 */
public class Market {
	/**
	 * Counter for invalid queries
	 */
	public static int nofInvalid=0;
	/**
	 * Sorted queue for the selling orders
	 */
	private PriorityQueue<SellingOrder> sellingOrders = new PriorityQueue<SellingOrder>();
	/**
	 * Sorted queue for the selling orders
	 */
	private PriorityQueue<BuyingOrder> buyingOrders= new PriorityQueue<BuyingOrder>();
	/**
	 * ArrayList for the transactions that have been made
	 */
	private ArrayList<Transaction> transactions=new ArrayList<Transaction>();
	/**
	 * Commission of the market
	 */
	private int fee;
	
	/**
	 * Constructor for the Market class
	 * @param fee Commission of the market
	 */
	public Market(int fee) {
		this.fee= fee;
	}
	/**
	 * Method for adding the order in the SellingOrder queue
	 * @param order Order which is going to be added
	 */
	public void giveSellOrder(SellingOrder order) {
		sellingOrders.add(order);
	}
	/**
	 * Method for adding the order in the BuyingOrder queue
	 * @param order Order which is going to be added
	 */
	public void giveBuyOrder(BuyingOrder order) {
		buyingOrders.add(order);
	}
	
	/**
	 * Method for Trader0(System)'s operations to regulate the market
	 * @param price Price which is wanted to reach
	 * @param traders ArrayList of traders to call checkTransactions
	 */
	public void makeOpenMarketOperation(double price,ArrayList<Trader> traders) {	
			if(buyingOrders.size()>0&&price<=buyingOrders.peek().price) {
				while(price<=buyingOrders.peek().price) {
					SellingOrder sellOrder= new SellingOrder(0,buyingOrders.peek().amount,buyingOrders.peek().price);
					sellingOrders.add(sellOrder);
					checkTransactions(traders);
					if(buyingOrders.size()==0) {
						break;
					}
				}
			}
			else if(sellingOrders.size()>0&&price>=sellingOrders.peek().price) {
				while(price>=sellingOrders.peek().price) {
					BuyingOrder buyOrder= new BuyingOrder(0,sellingOrders.peek().amount,sellingOrders.peek().price);
					buyingOrders.add(buyOrder);
					checkTransactions(traders);
					if(sellingOrders.size()==0) {
						break;
					}
				}
				
			}
		
		
	}
	/**
	 * Method for controlling the overlapped orders in the market
	 * @param traders ArrayList of the traders to handling some operations in the traders' wallet
	 */
	public void checkTransactions(ArrayList<Trader> traders) {
		while(true) {
			if(sellingOrders.size()>0&&buyingOrders.size()>0) {
				SellingOrder sellOrder= sellingOrders.peek();
				BuyingOrder buyOrder= buyingOrders.peek();
				if(buyOrder.price>=sellOrder.price) {
					Transaction transaction=null;
					if(buyOrder.amount>sellOrder.amount) {
						BuyingOrder transBuyOrder= new BuyingOrder(buyOrder.traderID,sellOrder.amount,sellOrder.price);
						transaction= new Transaction(sellOrder,transBuyOrder);
						buyOrder.setAmount(sellOrder.amount);
						traders.get(buyOrder.traderID).getWallet().afterBuy(sellOrder.amount*sellOrder.price);
						traders.get(buyOrder.traderID).getWallet().setCoins(sellOrder.amount);
						traders.get(sellOrder.traderID).getWallet().afterSell(sellOrder.amount);
						traders.get(sellOrder.traderID).getWallet().setDollars(sellOrder.amount*sellOrder.price*(1.00-fee/1000.00));
						sellingOrders.poll();
					}
					else if(buyOrder.amount<sellOrder.amount) {
						SellingOrder transSellOrder=new SellingOrder(sellOrder.traderID,buyOrder.amount,sellOrder.price);
						transaction=new Transaction(transSellOrder,buyOrder);
						sellOrder.setAmount(buyOrder.amount);
						traders.get(buyOrder.traderID).getWallet().afterBuy(buyOrder.amount*sellOrder.price);
						traders.get(buyOrder.traderID).getWallet().setCoins(buyOrder.amount);
						traders.get(sellOrder.traderID).getWallet().afterSell(buyOrder.amount);
						traders.get(sellOrder.traderID).getWallet().setDollars(buyOrder.amount*sellOrder.price*(1.00-fee/1000.00));
						buyingOrders.poll();
					}
					else {
						transaction=new Transaction(sellOrder,buyOrder);
						traders.get(buyOrder.traderID).getWallet().afterBuy(buyOrder.amount*sellOrder.price);
						traders.get(buyOrder.traderID).getWallet().setCoins(buyOrder.amount);
						traders.get(sellOrder.traderID).getWallet().afterSell(buyOrder.amount);
						traders.get(sellOrder.traderID).getWallet().setDollars(buyOrder.amount*sellOrder.price*(1.00-fee/1000.00));
						buyingOrders.poll();
						sellingOrders.poll();
					}
					transactions.add(transaction);
				}
				else {
					break;
				}
			
			}
			else {
				break;
			}
		}
	}
	/**
	 * Method for returning the commission of the market
	 * @return fee Commission of the market
	 */
	public int getFee() {
		return fee;
	}
	/**
	 * Method for returning the queue of the selling orders
	 * @return Sorted queue for the selling orders
	 */
	public PriorityQueue<SellingOrder> getSellingOrders() {
		return sellingOrders;
	}
	/**
	 * Method for returning the queue of the buying orders
	 * @return Sorted queue for the buying orders
	 */
	public  PriorityQueue<BuyingOrder>  getBuyingOrders() {
		return buyingOrders;
	}
	/**
	 * Method for returning the ArrayList of the transactions
	 * @return ArrayList for the transactions that have been made
	 */
	public ArrayList<Transaction> getTransactions(){
		return transactions;
	}
	
}
