package dbalderas1.a6;

/**
 * Main Driver class for A6
 * @author Diana Balderas
 * @version 1.0
 */

public class Main {

    public static void main(String[] args) {
	    // write your code here
        Coin bitcoin = new Coin("bitcoin");
        Coin ethereum = new Coin("ethereum");
        Coin dogecoin = new Coin("dogecoin");
        CoinGecko.updateCurrentPrice(bitcoin);
        CoinGecko.updateCurrentPrice(ethereum);
        CoinGecko.updateCurrentPrice(dogecoin);

        CoinGecko.updatePriceHistory(bitcoin, 7);

        System.out.println(bitcoin);
        System.out.println(ethereum);
        System.out.println(dogecoin);

    }
}
