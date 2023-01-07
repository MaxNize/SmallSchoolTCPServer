# SmallSchoolTCPServer

A small TCP Server to take two options based surveys in class

Big shoutouts to @jjenkov and his website https://jenkov.com/tutorials/java-multithreaded-servers/multithreaded-server.html.
Most of the Server stuff was made by him.

Getting Started:########################################################################  
-(The default port is 1234. This can be changed in "MultiThreadedServer.java" and "Client.java".)  
-(If the Client is not on the same machine as the host, the host has to be changed in "Client.java" to the IP-Address of the host machine.)  
-(If the Client is not in the same network, the port has to be port-forwarded and the the host has to be changed.)  
-Run "StartServer.java".  
-In another instance run "StartClient.java".  
-To evaluate the survey use "StartAuswertung.java".
####################################################################################

Commands:###########################################################################  
(Clientside)  
-Use "CREATEACC (NAME) (PASSWORD)" to create an account.  
-Use "ACCESSACC (NAME) (PASSWORD)" to access an account and start the survey.
-use "0" for the first option and "1" for the second option.
-(NAME and PASSWORD can't contain spaces. NAME can't contain special characters.)  
#####################################################################################

Modifying the survey:#####################################################################  
-In "WorkerRunnable.java":  
 --Replace the questions in the "questions" variable to your questions.  
 --Replace the vars in "vars" to those used in "Users.java".  
 --Replace the variable assignment to those used in "Users.java".

-In "Users.java":  
 --Replace the vars with yours (except "PW").

-In "Auswertung.java":  
 --Replace the options in "optionAs" with your option As.  
 --Replace the options in "optionBs" with your option Bs.

-(In "USERS" folder):  
 --You may want to delete the old users, espeacially if
they are from an old survey.  
#####################################################################################

Note:#################################################################################  
This is not an application to use real passwords on, since they are not secured
in anyway.This application is free to use. (I'd be happy about a shoutout or
link to this repo <3) The usage of this application is at your own risk. I do
not take responsibility for any damage this application may cause.  
#####################################################################################
