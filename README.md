MAD302-FinalAssignment-Jennyfer

Product Management App
Overview
This Android application is developed using Kotlin and demonstrates a complete product management system. The app allows users to add, edit, view, and delete products with a clean and intuitive user interface. It follows a multi-activity structure and uses RecyclerView for efficient data display.

Features
The application provides full CRUD (Create, Read, Update, Delete) functionality. Users can add new products, edit existing ones, view detailed information, and delete products with confirmation. Input validation is implemented to ensure data integrity and improve usability.

Screens
The app consists of three main screens:
- Main Screen: Displays the list of products using RecyclerView with options to edit or delete.
- Add/Edit Screen: Allows users to enter product details with proper validation.
- Detail Screen: Shows complete product information and includes delete functionality with confirmation dialog.

Technologies Used
- Kotlin
- Android SDK
- RecyclerView
- ViewBinding
- ConstraintLayout & LinearLayout

Validation
The application includes proper validation for all input fields:
- Product name cannot be empty
- Price must be a valid positive number
- Description cannot be empty  
Error messages are displayed directly in the input fields.

How to Run
1. Clone the repository
2. Open the project in Android Studio
3. Sync Gradle files
4. Run the application on an emulator or physical device

Project Structure
The project follows a clean structure with separate activities for each screen and a dedicated adapter for RecyclerView. Data is passed between activities using intents.
