# talkie-buddy
	
Project Description:

This is an illustration of interactive conversational program with the purpose of responding to the text input given by user. Most user's purposes are about technical support or complain for computer or laptop. 


Design Choices:

MainClass, it’s the main operation class. User should begin with that class to run codes. First interactive interface ask for user input. Code save the input as string variable "answer." Then, call the BotTopic class with argument “answer”.

For class BotTopic, at first using tolowerCase() to convert all user inputs to lowercase letters to simplify program. Using if condition statements to check if user want to ask question or make complaints. Based on that, code call class ComplaintsClass or Questions to detailed check input. And, making some simple greeting responses. Also, using boolean variable continuing and while loop to check if the turns of a dialogue should end or keep running.

For class ComplaintsClass, at first, create different variable to justify synonym (ie: “not working” and “isn’t working”) and to define different issues (ie: “autoOff”). Then using these variables and if statement to determine user’s problem. Also give appreciated response.  


