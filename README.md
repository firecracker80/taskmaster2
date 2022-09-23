This is a mobile app designed to allow users to add tasks and view all added tasks.

**Main Activity Page**
This page contain two buttons that when clicked, will take you the their respective pages.

**Add Task Page**
Currently does not hold anything, yet.

**All Tasks Page**
Currently does not hold anything, yet.

Lab 26 9/21/2022

![Home Page](Images/homescreen.png)
![Add Task](Images/addtask.png)
![All Task](Images/alltask.png)

Lab 27 9/21/2022
**Setting Page**
This page will allow users to enter and save their name. Once they click the saved button, a "Settings saved" text appears at the bottom.

**Task Detail**
Currently does not hold anything, yet.

**User Profile Page**
This page will hold the user's name input, which when saved, will display "Welcome to {userName}'s Tasks!" in the Main Activity. That part of the code, commented out because there is an error around the @Override and the onResume(). I haven't been to debug it yet.

![Home Page](Images/newhomepage.png)
![Settings](Images/settings.png)
![Task Details](Images/taskdetails.png)

Lab 28 9/22/2022
**Adapter**
Created the RecyclerView adapter to enable rendering on the Main Activity.

**Fragment**
Created the TaskFragment, though I am not entirely sure how to use it, let alone what to put in the fragment xml.

**Models**
Create two models, Task, which holds the constructor for each task that will be created, and Status enum, which will hold the statuses for tasks created.

I still have some syntax issues in the Main Activity for the onResume method and the RecyclerView. Not sure how to move forward from here. Hence the reason why the app won't build, and no screenshots available to show.