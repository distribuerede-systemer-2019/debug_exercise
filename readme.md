# Hackathon 2019 - Bank System
Our task today is to build a client-server application for a bank's back office application for a specific branch. The tellers at the bank will use the client server application to create and delete customer accounts, and to record deposits and withdrawals from customer accounts.

We want a client server application for this purpose. Client side will format the messages so the server side will store the information. The application will store customer accounts and account information and retrieve them if needed. 

The transactional guarantees (or lack thereof) should be considered and justified. What happens when a client logs on and wants to do a transfer between two accounts and the system crashes midway? 

You are free to choose how the client talks to the server, options are sockets, a message queue (RabbitMQ for example), HTTP or RPC. Consider the possibility of future expansion with bank transfers across branches (servers). 

## System Functionality
- The tellers should be able to choose desired functionality from a menu.
- The tellers should be able to create new customers and edit or delete existing customers. 
- The tellers should be able to alter customer’s balances (withdraw/deposit).
- The tellers should be able to list user accounts at branch.
- The tellers should be able to query the balance on user account.

## Non Functional Requirements
- The system should operate as a client application on teller side, and a server application on the server side.
- The system should provide an interactive interface for input (interactive command line, GUI, something).
- The system should be persistent. The user accounts and balances should be preserved from session to session (Database, flat file, something). 
- The system should be non SQL-injectable. (See the branch `prepared-statements`)
- The customer account should at least have the following information: 
  - Name: String
  - Balance: Integer
  - AccountNumber: Integer 
You can add more information as needed.

## How to initialize
1. Open your terminal (or git bash or whatever) and navigate to the folder you want to store your project in.
2. Clone this project
3. Open IntelliJ and import project with external model and maven. Remember to import maven. This can be done by clicking the pop’up and selecting `import`. Else you can right click the `pom.xml` file and select `maven` and `import`
4. Add new configuration in the top of IntelliJ. Select `tomcat` → `local` and click `fix` in the bottom where you have a warning. 
5. make a copy of `config.dist.json` called `config.json` and change the values to your database credentials.
6. Open a connection to you MySQL database (can be done directly through the terminal, or Workbench or Sequel Pro ) and run the `init.sql` script which can be found in the resources folder.
7. Run your server
8. If everything works as it should you can go to `localhost:8080/customers` which should retrieve one customer.

## Fix the To Do’s 
In IntelliJ you can select the TODO tab in the bottom (or search for TODO) which will list all the items, that you have to complete. 

You can get inspiration from the already finished `getCustomers` which is done in both the database and the controller. 

## Client
Next up is build you client, which can be found here https://github.com/distribuerede-systemer-2019/hackathon-client/blob/master/readme.md
