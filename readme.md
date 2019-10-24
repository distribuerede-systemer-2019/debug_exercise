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
5. **(THIS STEP IS ONLY NECESSARY IF YOU DIDN'T DO IT DURING THE HACKATHON!)** Open a connection to you MySQL database (can be done directly through the terminal, or Workbench or Sequel Pro ) and run the `init.sql` script which can be found in the resources folder.
6. Run your server

## Exercises
For the following exercises you should only place breakpoints in your `CustomerEndpoint class

### Exercise 1

