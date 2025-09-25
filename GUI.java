import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


/*
This class serves as the main "canvas" class. It creates a JFrame with a title and buttons that then add TaskField objects in a FlowLayout.
*/
public class GUI{
    private TaskList list; //TaskList object that stores and organizes TaskFields
    private int length; //Stores the length of the frame, used for the frame resizing
    private int totTasks; //Stores the total tasks added, used for the frame resizing
    private ArrayList<TaskField> taskList; //arraylist holding tasks, inizialized from "list" TaskList object, used to add and remove tasks stored in "list"

    //Constructor
    public GUI(){
        length = 450; //initializes the default frame length to 450 and default total tasks to 10;
        totTasks = 10;
        JFrame f = new JFrame(); //creates JFrame "f"

        f.setSize(400, length); //sets the JFrame "f" to 400 width and "length" length

        JPanel titlePanel = new JPanel(); //JPanel holding the title JPanel
        titlePanel.setPreferredSize(new Dimension(400, 25));

        JLabel title = new JLabel("To Do List");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        titlePanel.add(title); //JLabel holding the title with String "To Do List"

        f.add(titlePanel);//add title panel to frame

        list = new TaskList();//TaskList "list" holding all TaskField objects added

        JButton addButton = new JButton("Add Task");// JButton that creates and adds TaskField "task"
        class addListener implements MouseListener{
            public void mouseClicked(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
            public void mousePressed(MouseEvent e){
                TaskField task = new TaskField();//creates TaskField "task" when button is pressed

                list.addTask(task);//add "task" to TaskList object "list"
                if(list.getLength() > 10){
                    length += 35;
                    totTasks += 1;
                    f.setSize(400, length);
                }//expands frame size and increments totTasks if there are more than 9 TaskFields in "list""

                list.sortComplete();//sorts completed tasks to the bottom of the frame
                taskList = list.getRawList();//gets ArrayList from "list"
                for(TaskField toDo : taskList)
                    f.add(toDo);

                f.revalidate();
                f.repaint();//adds all TaskFields in "list" to frame and update frame with new info

                class doneListener implements ActionListener{//ActionListener for "done" button in "task"
                    public void actionPerformed(ActionEvent e){
                        task.setDone();//sets current task to done
                        list.sortComplete();//sorts completed tasks to the bottom of the frame

                        taskList = list.getRawList();
                        for(TaskField toDo : taskList)
                            f.add(toDo);


                        f.revalidate();
                        f.repaint();//adds all TaskFields in "list" to frame and updates frame with new info
                    }
                }
                task.getButton().addActionListener(new doneListener());//adds doneListener() to the "done" button in "task"

                class removeListener implements ActionListener{//ActionListener for "Del" button in "task"
                    public void actionPerformed(ActionEvent e){
                        f.remove(task);//removes "task" from Jframe
                        list.removeTask(task);//removes "task" from "list"
                        list.reassignTasks();//renumbers tasks in "list"

                        if(length > 450 && totTasks > 10){//resizes frame if frame is longer than 450 and total tasks is greater than 10
                            length -= 35;
                            totTasks -= 1;
                            f.setSize(400, length);//resizes jframe
                        }

                        f.revalidate();
                        f.repaint();//updates jframe with new info
                    }
                }
                task.getRemove().addActionListener(new removeListener());//adds removeListener() to the "Del" button in "task"

                class undoListener implements ActionListener{//ActionListener for "Undo" button in "task"
                    public void actionPerformed(ActionEvent e){
                        task.setUndone();//sets "task" to undone
                        list.sortComplete();//sorts completed tasks to the bottom of frame

                        taskList = list.getRawList();//gets ArrayList of TaskFields from "list"
                        for(TaskField toDo : taskList)
                            f.add(toDo);

                        f.revalidate();
                        f.repaint();//adds all TaskFields in "list" to frame and updates frame with new info
                    }
                }

                task.getUndo().addActionListener(new undoListener());//adds undoListener() to the "Undo" button in "task"
            }
        }
        addButton.addMouseListener(new addListener());//adds addListener() to the "Add Task" button
        f.add(addButton);//adds the "Add Task" button to the JFrame

        JButton clearDoneButton = new JButton("Clear Completed Tasks");//creates JButton that clears JFrame of completed tasks
        class clearListener implements MouseListener{
            public void mouseClicked(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
            public void mousePressed(MouseEvent e){
                taskList = list.getRawList(); //gets ArrayList of TaskFields from "list"
                for(TaskField toDo : taskList)
                    if(toDo.isDone())
                        f.remove(toDo);//removes finished TaskFields from the JFrame

                int prevLen = list.getLength();//stores the length of the list before clearing the completed TaskFields
                list.clearCompleted();//clears the completed TaskFields in "list"
                if(length > 450 && totTasks > 10){//resizes frame if frame is longer than 450 and total tasks is greater than 10
                    int lenDiff = prevLen - list.getLength();
                    length -= 35 * lenDiff;//subtracts from "length" 35 times n number of removed TaskFields
                    if (length < 450){//sets length to 450 if length goes below default value
                        length = 450;
                    }
                    totTasks -= 1 * lenDiff;//subtracts from "totTasks" n number of removed TaskFields
                    f.setSize(400, length);//resizes JFrame
                }

                f.revalidate();
                f.repaint();//updates frame with new info
            }
        }


        clearDoneButton.addMouseListener(new clearListener());
        f.add(clearDoneButton);//adds clearListener() to the "Clear Completed Tasks" button and adds it to the JFrame

        f.setLayout(new FlowLayout());
        f.setVisible(true);//sets JFrame layout to Flowlayout() and makes the JFrame visible
    }
}
