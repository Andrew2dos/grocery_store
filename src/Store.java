import java.sql.*;
import java.util.HashMap;
import java.util.Scanner;

public class Store {

    public static HashMap<String, Double> addFood(){
        HashMap<String, Double> food = new HashMap<>();
        food.put("eggs", 75.0);
        food.put("salt", 14.9);
        food.put("milk", 59.9);
        food.put("rice", 44.0);
        food.put("meat", 560.2);
        food.put("tea", 225.7);
        food.put("cola", 69.0);

        return food;
    }

    public static double makePurchases(HashMap<String, Double> food) {
        Double sum = 0.0;
        String listOfPurchase = "";
        while (true) {

            Scanner scanner = new Scanner(System.in);
            String purchase = scanner.nextLine();

            if(purchase.equals("end")){
                break;
            }

            double amount = scanner.nextDouble();
            double price = food.get(purchase);

            sum += (amount * price);
            listOfPurchase = String.format("%s %s %.2f *%.2f =%.2f\n", listOfPurchase, purchase, price, amount, (amount * price));
            System.out.println("Для продолжения введите следующий товар и количество, или \"end\" для окончания покупок.");
        }
        System.out.println(listOfPurchase);
        return sum;
    }

    public static void main(String[] args) {
//        HashMap<String, Double> food = addFood();
//        System.out.println(food);
//        System.out.println("Для начала покупок введите название товара, или \"end\" для отмены.");
//        double sum = makePurchases(food);
//        if(sum > 1000) {
//            System.out.printf("Сумма: %.2fруб. Скидка 1%%\n" , sum);
//            sum = sum / 100 * 99;
//
//        }
//        System.out.printf("Итого к оплате: %.2fруб." , sum);

        String dbURL = "jdbc:postgresql://localhost/groceryStoreDataBase";
        String user = "postgres";
        String pass = "postgre";
        try {
            Connection conn = DriverManager.getConnection(dbURL, user, pass);
            System.out.println("Connection to Postgres server ");

            String sql = "SELECT * FROM drinks";

            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()){
                int id = result.getInt("id");
                String beverage = result.getString("beverage");
                int price = result.getInt("price");

                System.out.printf("%d - %s - %d\n", id, beverage, price);
            }

            conn.close();
        } catch (SQLException e) {
            System.out.println("Error to connection Postgres server ");
            e.printStackTrace();
        }
    }
}