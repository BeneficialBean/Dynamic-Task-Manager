import java.util.ArrayList;


/*


This class is used to keep track and arrange TaskField objects added to the JFrame in GUI
*/
public class TaskList{
    private ArrayList<TaskField> list;//ArrayList of TaskField objects

    //Constructor
    public TaskList(){
        list = new ArrayList<TaskField>();//initalizes ArrayList of TaskField objects
    }


    //////Mutator Methods//////

    /*
    Used to add TaskFields to "list"
    TaskField parameter, no return value
    */
    public void addTask(TaskField task){
        list.add(task);//adds task to "list"
        task.setLabel("" + (list.indexOf(task) + 1));//sets the label based on the index of the task in "list"
    }

    /*
    Removes the given task from "list"
    TaskField parameter, no return value
    */
    public void removeTask(TaskField task){
        list.remove(list.indexOf(task));//finds index of given "task" and removes it from "list"
    }

    /*
    Takes every TaskField in "list" and sets their label based on the index of the task
    no parameter, no return value
    */
    public void reassignTasks(){
        for (int i = 0; i < list.size(); i++){
            list.get(i).setLabel("" + (i + 1));
        }
    }

    /*
    Used to sort "list" by moving all completed tasks to the end of the list
    no parameter, no return value
    */
    public void sortComplete(){
        ArrayList<TaskField> temp = new ArrayList<TaskField>();//temp ArrayList to hold sorted tasks
        for (TaskField task : list){//iterates through each task in "list", adds it to "temp" if it is NOT done
            if(!task.isDone())
                temp.add(task);
        }
        for(TaskField task : list)//then iterates through "list" again, this time adding the task to "temp" if it is done
            if(task.isDone())
                temp.add(task);
        list = temp;//points "list" to "temp" ArrayList;
        reassignTasks();//reassigns tasks automatically
    }

    /*
    Used to clear all completed tasks from "list"
    no parameter, no return value
    */
    public void clearCompleted(){
        ArrayList<TaskField> temp = new ArrayList<TaskField>();//temp ArrayList to hold new task list
        for (TaskField task : list){//iterates through "list", adding tasks to "temp" if it is NOT done
            if (!task.isDone()){
                temp.add(task);
            }
        }
        list = temp;//points "list" to "temp" ArrayList
        reassignTasks();//reassigns tasks automatically
    }


    //////Accessor Methods//////

    /*
    Used to return the length of "list"
    no parameter, int return value
    */
    public int getLength(){
        return list.size();
    }

    /*
    Used to return the ArrayList that holds the tasks
    no parameter, ArrayList<TaskField> return value
    */
    public ArrayList<TaskField> getRawList(){
        return list;
    }
}
