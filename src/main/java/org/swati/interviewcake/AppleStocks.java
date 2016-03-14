package org.swati.interviewcake;

/**
 * Writing programming interview questions hasn't made me rich. Maybe trading Apple stocks will.
 Suppose we could access yesterday's stock prices as a list, where:

 The indices are the time in minutes past trade opening time, which was 9:30am local time.
 The values are the price in dollars of Apple stock at that time.
 So if the stock cost $500 at 10:30am, stock_prices_yesterday[60] = 500.

 Write an efficient function that takes stock_prices_yesterday and returns the best profit I could have made from 1 purchase and 1 sale of 1 Apple stock yesterday.

 For example:

 stock_prices_yesterday = [10, 7, 5, 8, 11, 9]

 get_max_profit(stock_prices_yesterday)
 # returns 6 (buying for $5 and selling for $11)

 No "shorting"—you must buy before you sell. You may not buy and sell in the same time step (at least 1 minute must pass).
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class AppleStocks {
    public static int getMaxProfit(int[] stockPricesYesterday) {
        if (stockPricesYesterday == null || stockPricesYesterday.length < 2) {
            throw new IllegalArgumentException("Getting profit requires at least 2 values");
        }
        int buy = stockPricesYesterday[0];
        int profit = Integer.MIN_VALUE;
        for (int i = 1; i < stockPricesYesterday.length; i++) {
            profit = Math.max(profit, stockPricesYesterday[i] - buy);
            buy = Math.min(buy, stockPricesYesterday[i]);
        }
        return profit;
    }
    public static void main(String[] args) {
        int[] stockPricesYesterday = new int[]{10, 7, 5, 10};
        System.out.println("Max profit: " + getMaxProfit(stockPricesYesterday));
    }
}
