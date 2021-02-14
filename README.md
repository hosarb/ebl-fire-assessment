# EBL FIRE assessment
This project is the answer to EBL FIRE Assessment sent to Hossein Arbaboon.

This is a simple Spring Boot system with some REST APIs to manage a person information database.

To run the project you need to have jdk version 8 ot higher and maven 3.

Using your favorite IDE, or the command line, build the project by this command :

##### mvn clean install

The main class is my.ebl.fire.Application. The configurations are the defualt.

After running the main class, the system can be accessed in the path : http://localhost:8080

To see the apis documentation brows to http://localhost:8080/swagger-ui/index.html and use admin/admin to login.

There are 2 types of apis here.
1. Reporting apis. These apis can be accessed by these two users : admin/admin or user/user
2. Data manipulation apis. These apis can be accessed by only by : admin/admin
