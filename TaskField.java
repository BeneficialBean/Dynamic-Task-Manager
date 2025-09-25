import javax.swing.*;
import java.awt.*;
/*
This class holds the JPanel that contains the tasks index number, JTextField, and modifiers JButtons
*/
public class TaskField  extends JPanel{//Extends JPanel so the object itself is a JPanel
    private boolean done;//holds the boolean value that represents if the task is done or not
    private JTextField textField;//JTextField that the user can add text to, to name the task
    private JButton doneButton;//JButton that sets the task as done
    private JButton removeButton;//JButton that deletes the task from the JFrame
    private JButton undoButton;//JButton that sets the task to undone
    private JLabel indexNumber;//JLabel that holds the index number of the task

    //Constructor
    public TaskField(){
        this.setLayout(new FlowLayout());//sets the layout to FlowLayout()

        indexNumber = new JLabel("");//initializes the JLabel to an empty label
        this.add(indexNumber);//adds to the JPanel

        textField = new JTextField(20);//intializes the JTextField with length 20 and adds it to the Jpanel
        this.add(textField);

        doneButton = new JButton("Complete");
        removeButton = new JButton("Del");
        undoButton = new JButton("Undo");
        //Initializes the 3 modifier buttons

        doneButton.setPreferredSize(new Dimension(101, 20));
        removeButton.setPreferredSize(new Dimension(60,20));
        undoButton.setPreferredSize(new Dimension(75,20));
        //sets the preferred size of each modifer button


        this.add(doneButton);
        //adds only the "Done" button
    }


    //////Mutator Methods//////


    /*
    Used to set the task to done
    no parameter or return value
    */
    public void setDone(){
        done = true;//sets the "done" boolean to true
        textField.setBackground(Color.green);//sets the background of the JTextField to green to indicate that it is done
        this.remove(doneButton);//removes the "done" button from the JPanel


        this.add(removeButton);//adds the "Del" and "Undo" buttons in place of the "done" button
        this.add(undoButton);
        revalidate();
    }

    /*
    Used to set the task to undone
    no parameter or return value
    */
    public void setUndone(){
        done = false;//sets "done" to false
        textField.setBackground(Color.white);//sets the background of the JTextField to white
        this.remove(removeButton);
        this.remove(undoButton);//removes the "Del" and "Undo" buttons, adds back the "Done" button
        this.add(doneButton);
        revalidate();
    }

    /*
    Used to set the index of the task
    parameter String, no return value
    */
    public void setLabel(String label){
        indexNumber.setText(label);//sets the JLabel to "label"
    }


    //////Accessor Methods//////

    /*
    Used to return the JButton "Done"
    no parameter, JButton return value
    */
    public JButton getButton(){
        return doneButton;
    }

    /*
    Used to return the JButton "Del"
    no parameter, JButton return value
    */
    public JButton getRemove(){
        return removeButton;
    }

    /*
    Used to return the JButton "Undo"
    no parameter, JButton return value
    */
    public JButton getUndo(){
        return undoButton;
    }

    /*
    Used to return the JLabel indexNumber
    no parameter, JLabel return value
    */
    public JLabel getIndex(){
        return indexNumber;
    }

    /*
    Used to return the boolean "done"
    no parameter, boolean return value
    */
    public boolean isDone(){
        return done;
    }
}
