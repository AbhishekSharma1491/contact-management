# contact-management
Contact Management is used for maintaining contact information.
Below are the supported functionalities:
- List contacts
- Add contact
- Edit contact
- Delete contact

Contact details include first name, last name, email, phone number and status of the user.

Project contains the below packages,
- controller : contains application logic and passing user input data to service.
- service : The middleware between controller and repository. Gather data from controller, performs validation and business logic, and calling repositories for data manipulation.
- repository :  layer for interaction with models and performing DB operations
- model : contains the entity.
- exception : application exception and handler

#Steps to run the application:
- Download the git project.
- Build the project using maven: mvn clean install
- Go to target directory and run the application using java command: java -jar contact-management-0.0.1-SNAPSHOT.jar

Code is hosted in Pivotal cloud. Link to access the application is,
https://contact-mgmt.cfapps.io/
