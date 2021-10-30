package executable;

import java.io.File;
import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;


import elements.BuyingOrder;
import elements.Market;
import elements.SellingOrder;
import elements.Trader;

import java.io.FileNotFoundException;
/**
 * Main class where all the main operations are made. Reading the inputs, doing the operations according to the inputs, outputting the results 
 * @author Taha Baturhan Akbulut
 *
 */
public class Main{
	/**
	 * Random object for making random operations with a given seed
	 */
	public static Random myRandom;
	/**
	 * Main method where all the main operations are made
	 * @param args Input and output files
	 * @throws FileNotFoundException
	 */
	public static void main(String args[]) throws FileNotFoundException{
		Scanner in = new Scanner(new File(args[0]));
		PrintStream out = new PrintStream(args[1]);
		
		
		ArrayList<Trader> traders= new ArrayList<>();
		Trader.numberOfUsers=0;
		Market.nofInvalid=0;
		int randomSeed=in.nextInt();
		myRandom=new Random(randomSeed);
		int fee= in.nextInt();
		Market market= new Market(fee);
		int C= in.nextInt();
		int D= in.nextInt();
		in.nextLine();
		
		
		for(int i=0;i<C;i++) {
			String line[]=in.nextLine().split(" ");
			Trader trader= new Trader(Double.parseDouble(line[0]),Double.parseDouble(line[1]));
			traders.add(trader);
		}
		
		for(int i=0;i<D;i++) {
			String line[]=in.nextLine().split(" ");
			if(Integer.parseInt(line[0])==10) {
				//give buying order of specific price
				int trader_id=Integer.parseInt(line[1]);
				double price=Double.parseDouble(line[2]);
				double amount=Double.parseDouble(line[3]);
				traders.get(trader_id).buy(amount, price, market);
				market.checkTransactions(traders);

			}
			else if(Integer.parseInt(line[0])==11){
				//give buying order of market price
				int trader_id=Integer.parseInt(line[1]);
				double amount=Double.parseDouble(line[2]);
				SellingOrder currentOrder=market.getSellingOrders().peek();
				if(currentOrder!=null) {
					traders.get(trader_id).buy(amount, currentOrder.getPrice(), market);
					market.checkTransactions(traders);
				}
				else {
					Market.nofInvalid++;
				}
				
			}
			else if(Integer.parseInt(line[0])==20) {
				//give selling order of specific price
				int trader_id=Integer.parseInt(line[1]);
				double price=Double.parseDouble(line[2]);
				double amount=Double.parseDouble(line[3]);
				traders.get(trader_id).sell(amount, price, market);
				market.checkTransactions(traders);
			}
			
			else if(Integer.parseInt(line[0])==21){
				//give selling order of market price
				int trader_id=Integer.parseInt(line[1]);
				double amount=Double.parseDouble(line[2]);
				BuyingOrder currentOrder=market.getBuyingOrders().peek();
				if(currentOrder!=null) {
					traders.get(trader_id).sell(amount, currentOrder.getPrice(), market);
					market.checkTransactions(traders);
				}
				else {
					Market.nofInvalid++;
				}
				
			}
			else if(Integer.parseInt(line[0])==3){
				//deposit a certain amount of dollars to wallet
				int trader_id=Integer.parseInt(line[1]);
				double amount=Double.parseDouble(line[2]);
				traders.get(trader_id).depositToWallet(amount);
			}
			else if(Integer.parseInt(line[0])==4){
				//withdraw a certain amount of dollars from wallet
				int trader_id=Integer.parseInt(line[1]);
				double amount=Double.parseDouble(line[2]);
				traders.get(trader_id).withdrawFromWallet(amount);
			}
			else if(Integer.parseInt(line[0])==5){
				//print wallet status
				int trader_id=Integer.parseInt(line[1]);
				out.println("Trader "+trader_id+": "+String.format("%.5f",(traders.get(trader_id).getWallet().getDollars()+traders.get(trader_id).getWallet().getBlockedDollars()))+"$ "+String.format("%.5f",(traders.get(trader_id).getWallet().getCoins()+traders.get(trader_id).getWallet().getBlockedCoins()))+"PQ");
			}
			else if(Integer.parseInt(line[0])==777){
				//give rewards to all traders
				for(Trader j:traders) {
					j.rewardToTraders(myRandom.nextDouble()*10);
				}
			}
			else if(Integer.parseInt(line[0])==666){
				//make open market operation
				market.makeOpenMarketOperation(Double.parseDouble(line[1]),traders);
				
			}
			else if(Integer.parseInt(line[0])==500){
				//print the current market size
				double totalDollars=0;
				double totalCoins=0;
				for(BuyingOrder j:market.getBuyingOrders()) {
					totalDollars+=(j.getPrice()*j.getAmount());
				}
				for(SellingOrder j:market.getSellingOrders()) {
					totalCoins+=j.getAmount();
				}
				out.println("Current market size: "+String.format("%.5f",totalDollars)+" "+String.format("%.5f",totalCoins));
				
			}
			else if(Integer.parseInt(line[0])==501){
				//print number of successful transactions
				out.println("Number of successful transactions: "+ market.getTransactions().size());
			}
			else if(Integer.parseInt(line[0])==502){
				//print the number of invalid queries
				out.println("Number of invalid queries: "+Market.nofInvalid);
			}
			else if(Integer.parseInt(line[0])==505){
				//print the current prices
				BuyingOrder currentBuyOrder=market.getBuyingOrders().peek();
				SellingOrder currentSellOrder=market.getSellingOrders().peek();
				out.print("Current prices: ");
				if(currentBuyOrder!=null && currentSellOrder!=null) {
					out.print(String.format("%.5f",currentBuyOrder.getPrice())+" "+String.format("%.5f",currentSellOrder.getPrice())+" "+String.format("%.5f",((currentBuyOrder.getPrice()+currentSellOrder.getPrice())/2)));
				}
				else if(currentBuyOrder==null&&currentSellOrder!=null) {
					out.print(String.format("%.5f", 0.00)+" "+String.format("%.5f",currentSellOrder.getPrice())+" "+String.format("%.5f",currentSellOrder.getPrice()));
				}
				else if(currentBuyOrder!=null&&currentSellOrder==null) {
					out.print(String.format("%.5f",currentBuyOrder.getPrice())+" "+String.format("%.5f", 0.00)+" "+String.format("%.5f",currentBuyOrder.getPrice()));
				}
				else {
					out.print(String.format("%.5f", 0.00)+" "+String.format("%.5f", 0.00)+" "+String.format("%.5f", 0.00));
				}
				out.println();
				
			}
			else if(Integer.parseInt(line[0])==555){
				//print all traders' wallet status
				for(Trader t:traders) {
					out.println("Trader "+t.getID()+": "+String.format("%.5f",(t.getWallet().getDollars()+t.getWallet().getBlockedDollars()))+"$ "+String.format("%.5f",(t.getWallet().getCoins()+t.getWallet().getBlockedCoins())) +"PQ");
				}
			}
			
		}
		
	}
}

