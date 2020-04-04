# talkie-buddy
	
Project Description:

This is an illustration of interactive conversational program with the purpose of responding to the text input given by user. Most user's purposes are about technical support or complain for computer or laptop. 

The topic added to the program's repertoire is Reading/Writing about the Reviews and Ratings of the products. The user is able to write a review about the product by simply asking bot to write a review, whereas is able to look up the reviews about products from the local saved data. 

Design Choices:

MainClass, it’s the main operation class. User should begin with that class to run codes. First interactive interface ask for user input. Code save the input as string variable "answer." Then, call the BotTopic class with argument “answer”.

For class BotTopic, at first using tolowerCase() to convert all user inputs to lowercase letters to simplify program. Using if condition statements to check if user want to ask question or make complaints. Based on that, code call class ComplaintsClass or Questions to detailed check input. And, making some simple greeting responses. Also, using boolean variable continuing and while loop to check if the turns of a dialogue should end or keep running.

As addition to BotTopic class, the else-if conditions were implemented in order to writing / reading the product reviews and ratings.Review and rating are included inside one condition first then user is prompted to specify which one they want to do. The FileWriter,FileReader classes were used to modify the local datas review.txt, rating.txt to write and revise the product that user wants. 

SpellCheck and Dictionary classes were implemented to check the spelling of the words user enters simply by checking if the word exists inside the English dictionary - the local file words.txt - if not then checks whether the word exists after 1.appending any character at the end/front 2.eliminating any character from the front/end 3.swapping any adjacent characters . Thus the program prints out the suggestions of the words misspelled by the user. Nevertheless, because this method takes quite long to process each time user enters input the spell checking is limited throughout the program.

Inside RandomQuestions class, the possible random questions outside the main topics which could be asked by users are saved as keys in HashMap along with their answers as values. The JaroWinklerDistance algorithm was used to calculate the similarity rate between the questions saved and the question asked by user. If the rate is higher than 75%, RandomQuestions class returns the answer by saving the result inside the static variable close_answer. 



For class ComplaintsClass, at first, create different variable to justify synonym (ie: “not working” and “isn’t working”) and to define different issues (ie: “autoOff”). Then using these variables and if statement to determine user’s problem. Also give appreciated response.  


