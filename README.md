# To Do List
BeneficialBean | evany227@gmail.com

## Functionality
This To Do List is a take on a classic: a numbered list of items you're "supposed" to finish but put off until you suddenly remember it exists again.
This program utilizes Java and it's Swing components to create a dynamic To Do List. The To Do List allows you to create a (theoretically) infinite list of items.
You can then edit the state of the item (done/not done) or delete it.

The To Do List also automatically resizes the frame based on how many tasks are created. The default size of tasks are set to 10. If the user decides to add more tasks, the frame extends so there is room for the next task box.



## Contents and Operation
The program uses 4 classes to operate: MyProgram (main), GUI, TaskList, and TaskField.

When MyProgram is run, it calls the **GUI** class. The **TaskField** class is a JPanel containing a JTextField, Complete, Delete, and Undo button. The **TaskList** class is the **ArrayList** that holds each TaskField object. The GUI class creates the JPanel that every component rests on and contains the operation of the "Add Task" and "Clear Completed Class" button.

When the "Add Task" button is pressed, a new **TaskField** object is created added to the **TaskList** object held in the **GUI** class. Then it checks if the JPanel is long enough for all the added tasks, and sorts the **TaskList**. Once it's done, it adds each **TaskField** object to the JPanel. It then implements the Complete, Delete, and Undo button and updates the current **TaskField's** JButton variables with the function.

When the "Clear Completed Tasks" button is pressed, it iterates through each **TaskField** in the **TaskList** and removes them from the JPanel if it's done. It then (if applicable) resizes the frame by removing completed tasks from the **TaskList** and comparing the previous length with the current length.

### **MyProgram (Main)**
The MyProgram class's only job is to call the constructor of the GUI class, which effectively runs the program.

### **GUI (Graphics)**
The GUI class handles the graphics of the class, including the JFrame, JButtons, and JPanels. It creates each Swing component and adds them to the JPanel.

#### Running the constructor
The constructor initializes instance variables and global variables, including:

```java
length; //stores the length of frame, used for resizing the frame

totTasks; //stores the total tasks allowed on the frame, used for resizing the frame

list = new TaskList();//A TaskList object holding all the TaskField objects added

JFrame f; //the frame that every componenet will sit ontop of
```
The program then creates a JButton called "addButton", which adds a task to the To Do List. When the JButton is pressed, a **TaskField** object *Task* is created, which represents the current task that will be implemented. *task* is then added to the **TaskList** *list*. It checks the length of *list* to see if the frame needs to be resized by adding a certain number of pixels if the length of *list* is greater than 10. It then calls the sortComplete() function on *list*, which takes all the completed tasks and moves them to the bottom. Once *list* is sorted, it calls getRawList() on *list*, which returns the raw ArrayList contained in **TaskList**. It adds each **TaskField** from *list* and adds it to the frame *f*. It then revalidates and repaints the frame.

The GUI Class also initializes each button that each **TaskField** *task* has. First, is the "Complete" button.

#### doneListener
When the "Complete" button is pressed, it calls setDone() on *task*, which sets it to done. It then calls *list*.sortComplete() again, and adds each **TaskField** to the frame. Finally, it calls getButton() on *task* and adds doneListener to the button.

#### removeListener
The next button is the "Del" button. When pressed, it removes *task* from JFrame *f* and *list*, and calls reassignTasks() which renumbers the tasks in *list*. It then resizes the frame, and calls getRemove() on *task* and adds removeListener to the button.

#### undoListener
The last button is the "Undo" button. When pressed, it calls setUndone() on *task* and resorts *list*. It then re adds each *task* to the frame *f*.

Once all of that is done, it adds the MouseListener action to the "Add Task" button.

The last thing that the program does is create a "Clear Completed Tasks" button. On the button press, it calls getRawList on *list*, and removes each completed task from *f* by calling each *task* with isDone(), which returns a boolean value based on its finished state. It then stores the previous length of *list*, clears *list* of completed tasks (clearCompleted()), and resizes (if applicable) the frame to the new number of tasks. It then adds the actionListener to the button, adds the button to *f*, sets the layout of *f* to FlowLayout(), and displays the full frame.

### **TaskList (List of TaskFields)**
The **TaskList** class holds an ArrayList of **TaskField** objects.

#### Running the constructor
The constructor initializes one instance variable:
```Java
ArrayList<TaskField> list; //Empty ArrayList of TaskField objects
```
The **TaskList** class has 5 mutator methods, and 2 accessor methods.

#### addTask(TaskField task)
addTask() adds the given *task* to *list*, and calls .setLabel() on *task* to set the index of the task.

#### removeTask(TaskField task)
removeTask() removes *task* from *list* via it's index, found by calling indexOf(*task*) on *list*

#### reassignTasks()
reassignTasks() takes each *task* in *list* and sets it's label in relation to it's position in the ArrayList

#### sortComplete()
sortComplete() takes each finished *task* and moves it to the end of the Arraylist. It first creates a *temp* ArrayList that will hold sorted tasks. It iterates through each *task* in *list*, and adds the task to *temp* if it is NOT done. Then, it adds the tasks that ARE done. It points the *list* variable to the *temp* object, and calls the reassignTasks() function.

#### clearCompleted()
clearCompleted removes each finished *task* from *list*. It first creates a *temp* ArrayList to hold NOT done tasks. It iterates through each *task* in *list* and adds the *task* if it is NOT done. Then, it points *list* to *temp*, and reassigns the tasks.

#### getLength()
This returns the length of *list*

#### getRawList()
This returns *list*

### **TaskField (Each individual task)**
The **TaskField** class holds each indivdual class and it's finished state

#### Running the constructor
When the constructor is run, it initializes it's instance variables:
```Java
boolean done; //Holds the boolean value that represents the finished state (Done or Not Done)

JTextField textField; //The text field that the user can add text to

JButton doneButton; //Button that sets the task as done

JButton removeButton; //Button that removes the task from the JFrame

JButton undoButton; //Button that sets the task to undone

JLabel indexNumber; //JLabel that holds the index number of the task
```

The constructor then sets the layout to FlowLayout(), adds the *indexNumber*, *textField*, and *doneButton*.

**TaskField** has 3 mutator methods and 5 accessor methods.

#### setDone()
setDone() sets the boolean *done* to true. It then sets the background color of *textField* to green, and removes *donebutton*. After, it adds *removeButton* and *undoButton* to the frame.

#### setUndone()
This sets *done* to false, sets the background of *textField* to white, removes *removeButton* and *undoButton*, and adds *doneButton*.

#### setLabel(String label)
This sets the text of *indexNumber* to *label*

#### getButton()
This returns the *doneButton* object

#### getRemove()
This returns the *removeButton* object

#### getUndo()
This returns the *undoButton* object

#### getIndex()
This returns the *indexNumber* object

#### isDone()
This returns the boolean value of *done*

## Design changes
I originally had a different view of how the project would go. I envisioned a bullet point list that would also dynamically change based on what tasks were finished or not. But, I decided that using a frame for each task would look cooler for the project. I also debated whether or not to add a date system to set a due date for each task. Finally, I had to choose the right words for the "Del", "Undo", and "Done" buttons, as using "Remove", "Set Undone", and "Complete" would be more fitting for a to do list, it would clutter the UI more. 












