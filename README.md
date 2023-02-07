# smoke-test

This is a smoke test project that combines data-driven testing and keyword-driven testing using the formData.xlsx to store the test data.
Technologies Used

    Java
    Selenium WebDriver
    TestNG

Project Structure

The project has two main components:

    formData.xlsx - This is the data repository that stores all the test data required for the test cases.

    TestBaseClass.java - This is the shared repository that contains all the required functions and objects needed for the test cases. It includes the basic functionality of initializing the browser, navigating to the website, interacting with elements on the page, and ending the test.

    TestClass.java - This is the test class that performs the actual testing. It extends the TestBaseClass and implements the test steps, making use of the functions and objects provided in the shared repository. The test can be run as a standalone class or integrated with TestNG for test management and reporting.

How to run the test

    Clone the repository
    Import the project into your Java IDE (e.g. Eclipse)
    Make sure that all the dependencies (Selenium WebDriver, Apache POI, TestNG ) are added to the project
    Run the TestClass.java as a Java application or as a TestNG test.

Additional Info

Please note that the project has been developed and tested with the latest version of Selenium WebDriver, Apache POI and TestNG. The use of older versions may cause compatibility issues.
