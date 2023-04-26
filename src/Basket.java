import java.io.*;
import java.util.Arrays;

public class Basket implements Serializable {
    private static final long serialVersionUID = 1L;
    private String[] goods;
    private int[] prices;
    private int[] quantities;

    public Basket() {
    }

    public Basket(String[] goods, int[] price) {
        this.goods = goods;
        this.prices = price;
        this.quantities = new int[goods.length];
    }

    public static Basket loadFromTxtFile(File textFile) {
        Basket basket = new Basket();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(textFile))) {
            String goodsStr = bufferedReader.readLine();
            String priceStr = bufferedReader.readLine();
            String quantitiesStr = bufferedReader.readLine();


            basket.goods = goodsStr.split(" ");
            basket.prices = Arrays.stream(priceStr.split(" "))
                    .map(Integer::parseInt)
                    .mapToInt(Integer::intValue)
                    .toArray();
            basket.quantities = Arrays.stream(quantitiesStr.split(" "))
                    .map(Integer::parseInt)
                    .mapToInt(Integer::intValue)
                    .toArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return basket;
    }

    public void addToCart(int productNum, int amount) {
        quantities[productNum] += amount;
    }

    public void printCart() {
        int totalPrice = 0;
        System.out.println("Список продуктов: ");
        for (int i = 0; i < goods.length; i++) {
            if (quantities[i] > 0) {
                int currentPrice = prices[i] * quantities[i];
                totalPrice += currentPrice;
                System.out.println(i + 1 + "." + " " + goods[i] + " " + quantities[i] + " шт " + currentPrice + " в сумме");
            }
        }
        System.out.println("Сумма вашей покупки: " + totalPrice);
    }

    public void saveTxt(File textFile) throws FileNotFoundException {
        try (PrintWriter out = new PrintWriter(textFile)) {
            //out.println(String.join(" ", goods));
            //out.println(String.join(" ", Arrays.stream(prices)
           //         .mapToObj(String::valueOf)
            //        .toArray(String[]::new)));
            //out.println(String.join(" ", Arrays.stream(quantities)
            //        .mapToObj(String::valueOf)
            //        .toArray(String[]::new)));

            for (String good : goods){
                out.println(good + " ");
            }
            out.println();

            for (int price : prices){
                out.println(price + " ");
            }
            out.println();

            for (int quantity : quantities){
                out.println(quantity + " ");
            }
            out.println();
        }

    }
}



