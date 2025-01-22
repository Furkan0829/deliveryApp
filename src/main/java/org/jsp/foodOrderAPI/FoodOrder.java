package org.jsp.foodOrderAPI;

import javax.persistence.*;
import java.util.List;
import java.util.Scanner;

public class FoodOrder {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("dev");
        EntityManager em = emf.createEntityManager();

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("1. Add Food Item\n2. Place Order\n3. View All Food Items\n4. View All Orders\n5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter food item name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter food item price: ");
                    double price = scanner.nextDouble();

                    em.getTransaction().begin();
                    FoodItem foodItem = new FoodItem();
                    foodItem.setName(name);
                    foodItem.setPrice(price);
                    em.persist(foodItem);
                    em.getTransaction().commit();

                    System.out.println("Food item added successfully.");
                    break;

                case 2:
                    System.out.print("Enter customer name: ");
                    String customerName = scanner.nextLine();
                    System.out.print("Enter address: ");
                    String address = scanner.nextLine();

                    em.getTransaction().begin();
                    Order order = new Order();
                    order.setCustomerName(customerName);
                    order.setAddress(address);

                    System.out.print("Enter number of food items: ");
                    int itemCount = scanner.nextInt();
                    scanner.nextLine();

                    for (int i = 0; i < itemCount; i++) {
                        System.out.print("Enter food item ID: ");
                        Long foodItemId = scanner.nextLong();
                        scanner.nextLine();

                        FoodItem item = em.find(FoodItem.class, foodItemId);
                        if (item != null) {
                            order.getFoodItems().add(item);
                        } else {
                            System.out.println("Food item not found.");
                        }
                    }

                    em.persist(order);
                    em.getTransaction().commit();

                    System.out.println("Order placed successfully.");
                    break;

                case 3:
                    List<FoodItem> foodItems = em.createQuery("FROM FoodItem", FoodItem.class).getResultList();
                    foodItems.forEach(item -> System.out.println(item));
                    break;

                case 4:
                    List<Order> orders = em.createQuery("FROM Order", Order.class).getResultList();
                    orders.forEach(o -> {
                        System.out.println("Order ID: " + o.getId() + ", Customer: " + o.getCustomerName() + ", Address: " + o.getAddress());
                        o.getFoodItems().forEach(item -> System.out.println("  " + item));
                    });
                    break;

                case 5:
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }

        em.close();
        emf.close();
        scanner.close();
    }
}