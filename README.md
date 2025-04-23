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
        - Fetch Products
        - ![5_shopFetch](https://github.com/user-attachments/assets/1271e800-d150-4668-95ea-0a5e20376eb0)
        - Exit 
        - ![shopExit](https://github.com/user-attachments/assets/0ca998d5-12dd-4347-9ca6-6f35442c7c79)

3. <strong>Add Products</strong>:
    - Users can add one or multiple products to the shop by entering product details like Product ID, Product Name, Price, and Quantity.
      ![2_shopAdd](https://github.com/user-attachments/assets/a6d64c54-9fbc-4a19-b248-63fd64a5ab9b)

4. <strong>Remove Products</strong>:
    - Users can view all available products in the shop and remove a specific product by entering its ID.
      ![4_shopRemove](https://github.com/user-attachments/assets/abebf24b-87bd-4541-8d74-3530cd5af680)

5. <strong>Update Products</strong>:
    - Users can view all available products in the shop and update a specific product by entering its ID.
      ![3_shopUpdate1](https://github.com/user-attachments/assets/4a89d951-63d9-47e8-aa74-7c5115242c1c)
    - Users can update Product Name, Price, and Quantity.
      ![3_shopUpdate2](https://github.com/user-attachments/assets/3064713c-446c-4a8a-8c52-72870d8e87a6)
      ![3_shopUpdate3](https://github.com/user-attachments/assets/3956f541-262b-4546-ad64-209b84174411)

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
