import java.util.Scanner;

public class MainClass {
	//variable to decide if bot continues after a prompt
	static boolean continuing = true;

	public static void main(String[] args) {
		
		//user input scanner
		Scanner input = new Scanner(System.in);
		
		//initial bot output
		System.out.println("Hello, how may i help you?");
		String answer = input.nextLine();
		
		//starts the bot and calls BotTopic
		while(continuing) {
			new BotTopic(answer,continuing);
			BotTopic.main(args);	
		}
		
		input.close();
	}
}