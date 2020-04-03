import java.util.Scanner;

public class ComplaintsClass {
	//variables
	static String answer;
	static boolean continuing;
	
	//variables for common phrases and words used by bot to check statements
	//(like different ways of saying product name, it's not not working etc etc)
	static boolean productName;
	static boolean notWorking;
	static boolean forceRestart;
	static boolean itWorked;
	static boolean didntWork;
	static boolean lag;
	static boolean autoOff;
	static boolean lowSpace;
	static boolean fanLoud;
	static boolean charger;
	static boolean soundLow;
	
	static boolean how;
	static boolean turnOn;
	static boolean wont;
	static boolean yes;
	static boolean no;
	static boolean ok;
	static boolean what;
	static boolean crash;
	static boolean program;
	static boolean returns;
	static boolean exchange;
	static boolean knowItAll;
	static boolean ido;
	
	//constructor
	public ComplaintsClass(String answer, boolean continuing) {
		ComplaintsClass.answer = answer;
		ComplaintsClass.continuing = continuing;
	}

	public static void phrases() {

		//inital values for common phrases and words used by bot to check statements
		//(like different ways of saying product name, it's not not working etc etc)
		productName = answer.toLowerCase().contains("product") || answer.toLowerCase().contains("it") || answer.toLowerCase().contains("computer") || answer.toLowerCase().contains("laptop") || answer.toLowerCase().contains("device");
		notWorking = answer.toLowerCase().contains("not working") || answer.toLowerCase().contains("isn't working") || answer.toLowerCase().contains("isnt working");
		forceRestart = answer.toLowerCase().contains("force") && answer.toLowerCase().contains("restart");
		itWorked = answer.toLowerCase().contains("it worked") || answer.toLowerCase().contains("it works");
		didntWork = (answer.toLowerCase().contains("didn't") || answer.toLowerCase().contains("didnt")) && answer.toLowerCase().contains("work");
		lag = answer.toLowerCase().contains("lag") || answer.toLowerCase().contains("too long") || answer.toLowerCase().contains("slow");
		autoOff = answer.toLowerCase().contains("automatic") && answer.toLowerCase().contains("turn") && answer.toLowerCase().contains("off");
		lowSpace = answer.toLowerCase().contains("pretty") || answer.toLowerCase().contains("very") || answer.toLowerCase().contains("almost") || answer.toLowerCase().contains("about");
		fanLoud = (answer.toLowerCase().contains("fan") && (answer.toLowerCase().contains("loud") || answer.toLowerCase().contains("noisy")));
		charger = (answer.toLowerCase().contains("charger") && answer.toLowerCase().contains("broke"));
		soundLow = (answer.toLowerCase().contains("sound") && (answer.toLowerCase().contains("low")));
		knowItAll = (answer.toLowerCase().contains("not") && answer.toLowerCase().contains("help"));
				
		how = answer.toLowerCase().contains("how");
		turnOn = answer.toLowerCase().contains("turn on");
		wont = answer.toLowerCase().contains("wont");
		yes = answer.toLowerCase().contains("yes") || answer.toLowerCase().contains("yeah");
		no = answer.toLowerCase().contains("no");
		ok = answer.toLowerCase().contains("ok");
		what = answer.toLowerCase().contains("what");
		crash = answer.toLowerCase().contains("crash") || answer.toLowerCase().contains("die");
		program = answer.toLowerCase().contains("program");
		returns = answer.toLowerCase().contains("return");
		exchange = answer.toLowerCase().contains("exchange");
		ido = answer.toLowerCase().contains("i do");
	}
	
	public static void main(String[] args) {
		//user input scanner
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		
		answer = input.nextLine();
		// answer.toLowerCase().contains("");	
		
		while(continuing) {
			phrases();
			if(charger) {
				System.out.println("Ship it back to us, and we'll replace it.");
				answer = input.nextLine();
			}
			else if(returns) {
				System.out.println("Could you tell me why you want to return it?");
				answer = input.nextLine();
				System.out.println("Alright, send it back to us and we'll refund you.");
				answer = input.nextLine();
			}
			else if(yes) {
				System.out.println("Alright, what else would you like to ask?");
				answer = input.nextLine();
			}
			else if(ok) {
				System.out.println("Anything else?");
				answer = input.nextLine();
			}
			else {
				System.out.println("Do you have any questions you want to ask?");
				answer = input.nextLine();
				if(yes || ido) {
					Questions.answer = "question";
					continuing = false;
					return;
				}
				else {
					System.out.println("Okay, have a good day. Goodbye!");
					answer = input.nextLine();
					System.exit(0);		
				}
			}
		}
		
		input.close();

	}
}
