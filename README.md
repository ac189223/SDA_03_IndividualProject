## SDA_03_IndividualProject

[ Specification ](#spec)  
[ Requirements ](#requir)  
[ Solution ](#sol)  

<a name="spec"></a>
#### Specification
Build a todo list application, that allows a user to 
* create new tasks, 
* assign them a title, 
* assign them a due date, 
* choose a project for that task belong to,
* edit tasks, 
* mark tasks as done,
* remove tasks,
* quit and save the current task list to file, 
* restart the application with the former state restored.

<a name="requir"></a>
#### Requirements
The solution must achieve the following requirements
* model a task with a task title, due date, status and project,
* display a collection of tasks that can be sorted both by date and project,
* support the ability to add, edit, mark as done, and remove tasks,
* support a text-based user interface,
* load and save task list to file,
* other creative features.

<a name="sol"></a>
#### Solution
Implemented according to 
[UML diagram](https://github.com/ac189223/SDA_03_IndividualProject/blob/ChangesAreComing/src/main/resources/IndividualProject_UML_miniature.png)
within MVC pattern with Interface to establish JDBC connection with local MySQL database named 
[IP](https://github.com/ac189223/SDA_03_IndividualProject/blob/ChangesAreComing/src/main/resources/IndividualProject_MySQL.png).
* [Model](https://github.com/ac189223/SDA_03_IndividualProject/tree/ChangesAreComing/src/main/java/IP_07/Model)
  * classes to represent task and project used in application
  * register containing list of existing tasks and projects together with applicable methods
* [View](https://github.com/ac189223/SDA_03_IndividualProject/tree/ChangesAreComing/src/main/java/IP_07/View)
  * printer of prepared popups to the screen
* [Controller](https://github.com/ac189223/SDA_03_IndividualProject/tree/ChangesAreComing/src/main/java/IP_07/Controller)
  * controllers to control the main flow of the application, as well as flow of parts for tasks and projects
  * popups builders to prepare popups according to the requirements and application flow
  * message builders to prepare messages, that will appear in popups
  * validator, that controls dates entered by user
* [Interface](https://github.com/ac189223/SDA_03_IndividualProject/tree/ChangesAreComing/src/main/java/IP_07/Interface)
  * connector to establish connection with local database
  * controller to controll the flow of quering against database
  * query builder to prepare appropiate SQL queries
  * class representing dataList format of data fetched from database  
* [Test](https://github.com/ac189223/SDA_03_IndividualProject/tree/ChangesAreComing/src/test/java/IP_07/Model)
  * tests for all methods from register
* [Resources](https://github.com/ac189223/SDA_03_IndividualProject/tree/ChangesAreComing/src/main/resources)
  * UML diagram of the application
  * diagram of the MySQL database
  * user manual
  * archive files in JSON format
