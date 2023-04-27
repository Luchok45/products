       import java.io.File;
       import java.io.FileNotFoundException;
       import java.util.Arrays;
       import java.util.Scanner;

        public class Main {
            static Scanner scanner = new Scanner(System.in);
            static String[] products = {"Хлеб", "Яблоки", "Молоко"};
            static int[] price = {20, 45, 67};
            static File saveFile = new File("basket.txt");
            public static void main(String[] args) throws FileNotFoundException {
                Basket basket = null;
                if(saveFile.exists()){
                } else {
                    basket = new Basket(products, price);
                }
                while(true){
                    showPrice();
                    System.out.println("Выберите товар и количество или введите `end`");
                    String input = scanner.nextLine();
                    if ("end".equals(input)) {
                        break;
                    }
                    String[] part = input.split(" ");
                    int productNumber = Integer.parseInt(part[0]) - 1;
                    int productCount = Integer.parseInt(part[1]) - 1;
                    basket.addToCart(productNumber, productCount);
                    basket.saveTxt(saveFile);
                }
                basket.printCart();

            }
            public static void showPrice(){
                System.out.println("Список товаров для покупки: ");
                for(int i =0; i < products.length; i++){
                    System.out.println(products[i] + " " + price[i] + " руб/шт");
                }
            }
        }
