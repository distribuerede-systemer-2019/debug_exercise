# Hackathon 2019 - Bank System
In this exercise session we will teach you how to use the debugger. This is done through intelliJ as this is your default IDE. Debugging is used two find bugs (as the name implies) and localise errors. If something doesn't work as intented, the debugger is your best friend. **These exercises are built on top of the hackathon. I have included some errors, and it's your job to fix them!**

## Introduction
Refer to the following image for for the introduction part of this exercise.

<img 3>

When debugging  you should run your code in debugging mode. This is the green icon next to the play icon **(point 1)**


When you want the code to stop at a certain point of your code, you click the margin between your line numbers and the code editing window. **(point 2)**

Now when you run the code, it will stop when it reaches the line where the break point is set. From here, you are going to use two options for now **(point 3)** 
1) Step over **(left button)** - which steps over the line and goes to the next line 
2) Step into **(right button)** - which let you step into another method, if the line that you are currently on invokes a method.

When you are debugging, you'll be able to watch how your variables change when you run the next line of your code. In the *variables* section **(point 4)** you'll see what the individual variables are evaluated to.

When you have located your error you have three options (for now) **(point 5)**. 
1) Fix the code and **rerun** it - **top icon**
2) Resume the program, which will now run normally or until it reaches another breakpoint - **middle icon**
3) Stop the execution **bottom icon** 

### Why can't i just use System.out.print?!
You can. But I would really recommend that you don't. Mainly for the following reasons:
1) It's slow. You'll constantly have to add and remove print statements, and every time you do so, you have to recompile your code.
2) They're part of the code. They change the line numbers so if you have some specific error description and it describes the error at line x, a print statement will change that. 
3) They can only output strings (and other primitive data). The debugger will show you objects.  

## How to initialize
1. Open your terminal (or git bash or whatever) and navigate to the folder you want to store your project in.
2. Clone this project
3. Open IntelliJ and import project with external model and maven. Remember to import maven. This can be done by clicking the pop’up and selecting `import`. Else you can right click the `pom.xml` file and select `maven` and `import`
4. Add new configuration in the top of IntelliJ. Select `tomcat` → `local` and click `fix` in the bottom where you have a warning.
5. **(THIS STEP IS ONLY NECESSARY IF YOU DIDN'T DO IT DURING THE HACKATHON! OR YOU WANT TO RESET YOUR DATABASE)** Open a connection to you MySQL database (can be done directly through the terminal, or Workbench or Sequel Pro ) and run the `init.sql` script which can be found in the resources folder.
6. Run your server - You should get an error. Proceed to exercises. 

## Exercises
For the following exercises you should only place breakpoints in your `CustomerEndpoint class

### Exercise 1
When you run your server, you get an error. Where is it? Find it and fix it. This isn't exactly a debugging exercise, but if you look at your server log (Tomcat Localhost Log), you will find your error.
Which error do you get? And on which line? Hint: the error is in your `config.json`

After you have fixed this, your server should be able to run. 

**Solution**: Change line 5 in config.json to DATABASE_PASSWORD

### Exercise 2
If you run your server, and go to localhost:8080/customers/ **(remember to use the correct path if your intellij uses localhost:8080/something_war_exploded/ use that)** you should get another error. 

Put a breakpoint in your `CustomerEndpoint` on line 28 and start your server in debug mode. After it has started send a http-get-request to your customers, and your code will stop executing on the line where you put your breakpoint. Then do the following: 

1) Step into `CustomerController.getCustomers`.
2) Step into `dbCon.query(sql)`.
3) Step into `getConnection`
4) Step over until you reach line 40. You get an exception. Why? Inspect the URL-variable. What's wrong. Remember how we connect to a MySQL-database.

**solution**: Change port to 3006

### Exercise 3
Try to create a customer. Use postman or advanced rest client to send a post request (or your client that you built during the hackathon, if you are sure that it nothing is wrong with it - if it fails, use postman or rest ;-) ). 

Everything succeeds, and your client returns the user, that you tried to created, but the user isn't added to the database. What's wrong? Set your breakpoint in the `customerEndpoint` in the `POST` method, and work your way from there. What happens with the PS?

Hint. If you're having trouble getting into the `updateCustomer` in line 45 in `CustomerEndpoint` you have to step into the line, which moves you into the Gson library, and then you have to *step out* (the up arrow) of this, and then step into line 45 again.  

**Solution**: Change line 103 in DB to return the variable `success` instead of true. Change line 73 in `customerController` to 
```
if(dbCon.executePreparedStatementUpdate(ps)) {
    out = customer;
}
```
and return out instead of customer. 

### Exercise 4
This exercise has two parts. Start by setting a breakpoint in your `PUT` method in `CusomerEndpoint`.
1) Use postman send a put-request with the following body:
```
{
  "name": "THIS IS CHANGED!",
  "balance": 10000,
  "account_no": 2
}
```
Everything works without errors. But the database is never updated. Why not? Tip: Inspect your prepared statement or your customer object. Where is the error? Look at the name of the fields of the customer class.

2) Change the `updateCustomer` to the following
```
public static Customer updateCustomer(Customer customer) {
        if (dbCon == null) {
            dbCon = new DatabaseController();
        }
        Customer customerFromDatabase = getCustomer(customer.getAccountNo());
        String sql = "UPDATE customers SET name = ?, balance = ? WHERE account_no = ?;";
        PreparedStatement ps = dbCon.prepare(sql);
        try {
            ps.setString(1, customerFromDatabase.getName());
            ps.setInt(2, customerFromDatabase.getBalance());
            ps.setInt(3, customerFromDatabase.getAccountNo());
            if(dbCon.executePreparedStatementUpdate(ps)) {
                return customer;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

``` 
And run again. Nothing gets updated even though you don't have the same problem as above. What's wrong? Fix it.

**Solution**: 

Part 1: The json that we're sending is using account_no which is how it looks in the database, **not in the model of our server** and thus the accountNo of a customer is always 0

Part 2: We're getting a customer from the database and updating that customer to the same values instead of actually using the input customer, that we're sending.   

## FINISHED