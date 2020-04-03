import java.util.Scanner;

public class MainClass {
	//variable to decide if bot continues after a prompt
	static boolean continuing = true;

	public static void main(String[] args) {
		
		Scanner input = null;
		boolean spell_check = false;
		String answer = "";
		while(!spell_check){
			//user input scanner
			input = new Scanner(System.in);
			//initial bot output
			System.out.println("Hello, how may i help you?");
			answer = input.nextLine();
			//check the spelling word by word
			SpellCheck sc = new SpellCheck();
			spell_check = sc.run(answer);
		}


		//starts the bot and calls BotTopic
		while(continuing) {
			new BotTopic(answer,continuing);
			BotTopic.main(args);	
		}
		
		input.close();
	}
}