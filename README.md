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
      ![ss1](https://github.com/user-attachments/assets/9214a1ef-3ede-4eaa-9559-dd027639ed6a)

2. <strong>Main Menu</strong>:
    - Users can select operations to perform from the main menu:
        - Add Products
        - Remove Products
        - Update Products
        - Exit

3. <strong>Add Products</strong>:
    - Users can add one or multiple products to the shop by entering product details like Product ID, Product Name, Price, and Quantity.

4. <strong>Remove Products</strong>:
    - Users can view all available products in the shop and remove a specific product by entering its ID.

5. <strong>Update Products</strong>:
    - Users can view all available products in the shop and update a specific product by entering its ID.
    - Users can update Product Name, Price, Quantity, and Availability.

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
