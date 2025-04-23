# ShopManager-CoreJava-JDBC-ConsoleBasedApplication
ShopManager is basically a Console-Based Application Developed using Core Java and JDBC for managing a Shop's Inventory.This application interacts with database to store and retrieve Shop Details and Product Information. It allows users to perform various operations like adding,removing, and updating Products .
## <u><strong>Features</strong></u>

- <strong>Shop Management</strong>:
  - Add a new shop or use an existing one.
  - Display shop details like Shop ID, Shop Name, Address, GST number, Contact number, and Shop Owner Name.

- <strong>Product Management</strong>:
  - Add new products to the shop.
  - Remove existing products from the shop.
  - Update product details like Product Name, Price, Quantity, and Availability.
  - Display all products available in the shop with their respective details.

## <u><strong>How to Use</strong></u>

1. <strong>Shop Details</strong>:
    - If it's the first run of the application, the user will be prompted to enter shop details.
    - If a shop already exists, the existing shop details will be displayed.
      ![1_shop](https://github.com/user-attachments/assets/f803fc75-a239-4418-81c8-06a38692aa5d)
   
2. <strong>Main Menu</strong>:
    - Users can select operations to perform from the main menu:
        - Add Products
        - Remove Products
        - Update Products
        - Exit
          ![shopExit](https://github.com/user-attachments/assets/0ca998d5-12dd-4347-9ca6-6f35442c7c79)

3. <strong>Add Products</strong>:
    - Users can add one or multiple products to the shop by entering product details like Product ID, Product Name, Price, and Quantity.
      ![2_shopAdd](https://github.com/user-attachments/assets/a6d64c54-9fbc-4a19-b248-63fd64a5ab9b)

4. <strong>Remove Products</strong>:
    - Users can view all available products in the shop and remove a specific product by entering its ID.
      ![4_shopRemove](https://github.com/user-attachments/assets/abebf24b-87bd-4541-8d74-3530cd5af680)

5. <strong>Update Products</strong>:
    - Users can view all available products in the shop and update a specific product by entering its ID.
      ![3_shopUpdate1](https://github.com/user-attachments/assets/039762dd-e3c9-4e4f-9d16-01dd09c84886)
    - Users can update Product Name, Price, Quantity, and Availability.
      ![3_shopUpdate2AndFetch](https://github.com/user-attachments/assets/1c9f7e0d-9aa7-4c0a-b265-36d488563428)

## <u><strong>How to Run</strong></u>

1. Clone the repository:
    
    git clone https://github.com/your-username/JDBC-Shop-Product-Management.git
    

2. Compile and run the View.java file:
    
    javac edu/jsp/View/View.java
    java edu.jsp.View.View
    

## <u><strong>Technologies Used</strong></u>

- JSE
- JEE
- PostgreSql
