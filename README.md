# SmallSchoolTCPServer
A small TCP Server to take two options based surveys in class


Getting Started:#######################################################################################################################################################
Clone the repo and add an empty folder called "USERS".
Enter your root directory in "Auswertung.java" and "WorkerRunnable.java" (notice the layout).
(The default port is 1234. This can be changed in "MultiThreadedServer.java" and "Client.java".)
(If the Client is not on the same maschine the host has to be changed in "Client.java".)
(If the Client is not in the same network, the port has to be port-forwarded and the the host has to be changed.)
Run "MultiThreadedServer.java".
In another instance run "Client.java".
To evaluate the survey use "Auswertung.java".
#######################################################################################################################################################################

Commands:##############################################################################################################################################################
(Clientside)
Use "CREATEACC (NAME) (PASSWORD)" to create an account.
Use "ACCESSACC (NAME) (PASSWORD)" to access an account and start the survey.
(NAME and PASSWORD can't contain spaces. NAME can't contain special characters.)
#######################################################################################################################################################################


Modifying the survey:##################################################################################################################################################
In "WorkerRunnable.java":
    Replace the questions in the "questions" variable to your questions. 
    Replace the vars in "vars" to those used in "Users.java".
    Set the for-loop to the length of "questions".
    Replace the variable assignment to those used in "Users.java".
    
In "Users.java":
    Replace the vars with yours (except "PW").
    
In "Auswertung.java":
    Replace the options in "optionAs" with your option As.
    Replace the options in "optionBs" with your option Bs.
    Set the for-loop to the length of "optionAs".
    
In "USERS" folder:
    Delete the old users.
#######################################################################################################################################################################

Note:##################################################################################################################################################################
This is not an application to use real passwords on, since they are not secured in anyway.
This application is free to use. (I'd be happy about a shoutout or link to this repo <3)
The usage of this application is at your own risk. I do not take responsibility for any damage this application may cause.
#######################################################################################################################################################################
