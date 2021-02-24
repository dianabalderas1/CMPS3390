package dbalderas1.a6;

/**
 * UpdateCoinTimerTaskDriver class for A5
 * @author Diana Balderas
 * @version 1.0
 */

public class UpdateCoinTimerTask implements Runnable {
    private Coin coin;

    /**
     * Override constructor to update the data on bitcoin and ethereum
     * @param coin representing the value of bitcoin and ethereum
     */
    public UpdateCoinTimerTask(Coin coin) {
        this.coin = coin;
    }

    /**
     * Default constructor to check on the update price value of bitcoin and ethereum
     */
    @Override
    public void run() {
        System.out.println("Checking for update on " + coin.getName());
        double curValue = this.coin.getCurrentPrice();
        CoinGecko.updateCurrentPrice(this.coin);
        if(curValue != this.coin.getCurrentPrice()) {
            System.out.println("------------------------- Price Changed " + this.coin.getName()
                    + " " + curValue + " ---> " + this.coin.getCurrentPrice() + "-----------------------------");
        }
    }
}
