import java.util.Scanner;

public class BotTopic {
	//variables
	static String answer;
	static String answerTemp;
	static boolean continuing;
	
	//constructor
	public BotTopic(String answer, boolean continuing) {
		BotTopic.answer = answer;
		BotTopic.continuing = continuing;
	}

	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		
		//while continuing is true keep looping
		while (continuing) {	
			//if user has a problem or complaint
			if (answer.toLowerCase().contains("complaint") || answer.toLowerCase().contains("problem")) {
				System.out.println("What's the problem?");
				answer = input.nextLine();
				new Questions(answer, continuing);
				Questions.main(args);
			} 
			//if user has a question
			else if (answer.toLowerCase().contains("question")) {
				System.out.println("What's the question?");
				answer = input.nextLine();
				new Questions(answer, continuing);
				Questions.main(args);
			} 
			//if the user decides he has nothing to ask end program
			else if (answer.toLowerCase().contains("never mind") || (answer.toLowerCase().contains("nothing") && (answer.toLowerCase().contains("ask") || answer.toLowerCase().contains("say")))) {
				System.exit(0);
			} 
			//if the user says hello back for some reason ask if they have a question / complaint
			else if (answer.toLowerCase().contains("hello") && !answer.toLowerCase().contains("complaint") && !answer.toLowerCase().contains("problem") && !answer.toLowerCase().contains("review")) {
				System.out.println("Did you have a question to ask or a complaint?");
				answer = input.nextLine();
				//if they answer "yes" (aka not answering at all) ask again which one they have (question or complaint)
				if(answer.toLowerCase().contains("yes")) {
					System.out.println("But which one? a complaint or a question?");
					answer = input.nextLine();
				}
				//if they don't then end program
				else {
					System.exit(0);
				}
			} 
			else {
				//ask user if it's a question or complaint
				System.out.println("Is that a problem you want solved or are you writing a complaint?");
				answer = input.nextLine();
				//if they answer "yes" reinforce if it's a question or complaint
				if(answer.toLowerCase().contains("yes")) {
					System.out.println("But which one? a complaint or a question?");
					answer = input.nextLine();
				}
			}
		}
	
	input.close();
	}

	
}
