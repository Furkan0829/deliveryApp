Inside the src/main folder I've created the two classes which are entity classes which are going to represent the two tables named FoodItems and Orders in the foodDeliveryApp database specified in pom.xml file.

In the first class i.e in the FoodItem class I've used the feilds such as - 
id - to represent the primary key column of the table with the help of @Id annotation
name - to hold the name of the food item and to represent the name column in database as well
price - to hold the price of particular item 
order - this fied is used to creatr has a relationship with the Order class to map both classes with help of @ManyToOne along with @Joincolumn which is going to specify the column which is building the relationship

Use in the application of this class/-
Adding Food Items: When a user chooses to add a food item, a new instance of FoodItem is created and persisted to the database.
Viewing Food Items: Instances of FoodItem are retrieved from the database and displayed to the user.


In the second class i.e in the Order class I've used the feilds such as - 

id - to represent the primary key column of the table with the help of @Id annotation
customer_name - to hold the name of the customer and to represent the respective column in database as well
address - to hold the address of particular customer 
foodItems - of type list<FoodItem> bcz an order can consist of multiple items 

Use in application of this class - 
When a user places an order, a new instance of Order is created, associated with the selected FoodItem objects, and persisted to the database.
Instances of Order are retrieved from the database and displayed, including the associated food items.


The FoodOrder class serves as the entry point of the application and provides a simple menu-driven interface to interact with the database. 

Users can perform the following operations:

1.Add Food Items to the database.
2.Place Orders by associating food items with customer details.
3.View All Food Items stored in the database.
4.View All Orders along with their associated food items.
5.Exit the application.

Displays the above menu to the user and takes input to perform one of the operations.
1.Add Food Item:
Reads food item details (name and price) from the user.
Creates a new FoodItem object and persists it to the database.

2.Place Order:
Takes customer details (name and address).
Asks the user to enter food item IDs for the order.
Retrieves the corresponding FoodItem objects from the database and associates them with the Order.
Persists the Order object to the database.

3.View All Food Items:
Fetches all FoodItem entities using an HQL query (FROM FoodItem) and displays them.

4.View All Orders:
Fetches all Order entities using an HQL query (FROM Order) and displays their details along with the associated food items.

5.Exit:
Closes the EntityManager and EntityManagerFactory and exits the application.
