 import java.util.Scanner;

public class test123 {
	//variables
	static String answer;
	static boolean continuing;
	
	//constructor
	public test123(String answer, boolean continuing) {
		BotTopic.answer = answer;
		test123.continuing = continuing;
	}

	public static void main(String[] args) {
		//user input scanner
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		
//		answer = input.nextLine();
//		answer.toLowerCase().contains("")
		
		while(continuing) {
			if (1 == 2) {} 
			else if ((answer.toLowerCase().contains("product") || answer.toLowerCase().contains("it") || answer.toLowerCase().contains("computer")) && answer.toLowerCase().contains("turn on")) {
				System.out.println("Have you tried a force restart?");
				answer = input.nextLine();
			} 
			else if (answer.toLowerCase().contains("not working") && answer.toLowerCase().contains("product")) {
				answer = input.nextLine();
			} 
			else if(answer.toLowerCase().contains("you")) {
				System.out.println("then go fuck yourself");
				answer = input.nextLine();
			}
			else if(answer.toLowerCase().contains("yes")) {
				System.out.println("what you wanna talk about?");
				BotTopic.answer = input.nextLine();
				return;
			}
			else if(answer.toLowerCase().contains("nvm")) {
				System.out.println("Alright then.");
				BotTopic.answer = "exit";
				return;
			}
			else {
				System.out.println("returning from test");
				return;
			}
		}
		
		input.close();

	}

}
