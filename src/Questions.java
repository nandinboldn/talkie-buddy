import java.util.Scanner;

public class Questions {
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
	static boolean soundLow;
	static boolean charger;
	
	static boolean how;
	static boolean turnOn;
	static boolean wont;
	static boolean yes;
	static boolean no;
	static boolean ok;
	static boolean what;
	static boolean crash;
	static boolean program;
	static boolean knowItAll;
	static boolean ido;
	static boolean returns;
	static boolean exchange;
	static boolean overheat;
	static boolean wifi;
	
	//constructor
	public Questions(String answer, boolean continuing) {
		Questions.answer = answer;
		Questions.continuing = continuing;
	}

	// Reinitializes the phrases each call, updating values to what is inputted by user
	public static void phrases() {

		//Initial values for common phrases and words used by bot to check statements
		//(like different ways of saying product name, it's not not working etc etc)
		productName = answer.toLowerCase().contains("product") || answer.toLowerCase().contains("it") || answer.toLowerCase().contains("computer") || answer.toLowerCase().contains("laptop") || answer.toLowerCase().contains("device");
		notWorking = answer.toLowerCase().contains("not working") || answer.toLowerCase().contains("isn't working" ) || answer.toLowerCase().contains("isnt working") || answer.toLowerCase().contains("issue") || answer.toLowerCase().contains("issues");
		forceRestart = answer.toLowerCase().contains("force") && answer.toLowerCase().contains("restart");
		itWorked = answer.toLowerCase().contains("it worked") || answer.toLowerCase().contains("it works");
		didntWork = (answer.toLowerCase().contains("didn't") || answer.toLowerCase().contains("didnt")) && answer.toLowerCase().contains("work");
		lag = answer.toLowerCase().contains("lag") || answer.toLowerCase().contains("too long") || answer.toLowerCase().contains("slow");
		autoOff = (answer.toLowerCase().contains("automatic") || productName)&& answer.toLowerCase().contains("turn") && answer.toLowerCase().contains("off");
		lowSpace = answer.toLowerCase().contains("pretty") || answer.toLowerCase().contains("very") || answer.toLowerCase().contains("almost") || answer.toLowerCase().contains("about");
		fanLoud = (answer.toLowerCase().contains("fan") && (answer.toLowerCase().contains("loud") || answer.toLowerCase().contains("noisy")));
		soundLow = (answer.toLowerCase().contains("sound") && ((answer.toLowerCase().contains("low") || answer.toLowerCase().contains("quiet"))));
		knowItAll = (answer.toLowerCase().contains("not") && answer.toLowerCase().contains("help"));
		charger = (answer.toLowerCase().contains("charger") && answer.toLowerCase().contains("broke"));
		overheat = (answer.toLowerCase().contains("hot") || answer.toLowerCase().contains("heat"));
		wifi = answer.toLowerCase().contains("wifi") && answer.toLowerCase().contains("wont") && answer.toLowerCase().contains("connect");
		
		how = answer.toLowerCase().contains("how");
		turnOn = answer.toLowerCase().contains("turn on");
		wont = answer.toLowerCase().contains("wont");
		yes = answer.toLowerCase().contains("yes") || answer.toLowerCase().contains("yeah");
		no = answer.toLowerCase().contains("no");
		ok = answer.toLowerCase().contains("ok");
		what = answer.toLowerCase().contains("what");
		crash = answer.toLowerCase().contains("crash") || answer.toLowerCase().contains("die");
		program = answer.toLowerCase().contains("program")|| answer.toLowerCase().contains("software")|| answer.toLowerCase().contains("program");
		ido = answer.toLowerCase().contains("i do");
		returns = answer.toLowerCase().contains("return")|| answer.toLowerCase().contains("give back");
		exchange = answer.toLowerCase().contains("exchange");

	}
	
	public static void main(String[] args) {
		//user input scanner
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);

		//while continuing is true it will continue looping through the questions
		while(continuing) {
			phrases();
			//dialog for if the product is overheating
			if(overheat) {
				System.out.println("Have you tried putting it ontop of something so the fans underneath have better circulation?");
				answer = input.nextLine();
				boolean works = false;
				while(!works){
				if(yes) {
					System.out.println("There isn't anything else to really do about that besides trying to let the fans have as much circulation as possible. \n avoid using RAM intensive applications for long peroids of time to help");
					answer = input.nextLine();
					works = true;
				}
				else {
					System.out.println("Try that then, anything else?");
					answer = input.nextLine();
					works = true;
				}
				}
			}
			
			//dialog for wifi card not working
			if(wifi) {
				System.out.println("Try troubleshooting the wifi adapter / replugging it in if it's a external one");
				answer = input.nextLine();
				if(ok) {
					System.out.println("Did that fix it?");
					answer = input.nextLine();
					if(no) {
						System.out.println("Try resetting wifi settings then, if that doesn't work send it back to us.");
						answer = input.nextLine();
					}
				}
				
			}
			
			//dialog for if the sound on the computer is too low
			if(soundLow) {
				System.out.println("Try playing sounds with another device, is it still low using the other device?");
				answer = input.nextLine();
				if(yes) {
					System.out.println("Send it back to us to take a look then");
					answer = input.nextLine();
				}else {
					System.out.println("Go to settings, audio, and reset audio settings. Did that fix it?");
					answer = input.nextLine();
					boolean works = false;
					while(!works){
					if(yes) {
						System.out.println("Wonderful, anything else?");
						answer = input.nextLine();
						works = true;
					}
					else {
						System.out.println("Send it back to us to take a look then, anything else?");
						answer = input.nextLine();	
						works = true;
					}
					}
				}

			}
			
			//dialog for if the product is not turning on or is crashing
			else if ((productName && wont && turnOn) || (productName && crash)) {
				System.out.println("Try unplugging the power cable and plugging it back in a few seconds later.");
				answer = input.nextLine();
				boolean works = false;
				while(!works){
					phrases();
					if(itWorked || yes) {
						System.out.println("Did you have any other questions?");
						answer = input.nextLine();
						works = true;
					}
					else if(ok) {
						System.out.println("Did it work?");
						answer = input.nextLine();
					}
					else{
						System.out.println("Have you tried a force restart?");
						answer = input.nextLine();
						
						while(!works){
							phrases();
							if((forceRestart && (what || how)) || no) {
								System.out.println("Hold the power button for 5 seconds, wait 10 seconds then press it again. Let me know if that works.");
								answer = input.nextLine();
							}
							else if((yes && didntWork) || didntWork) {
								System.out.println("Has the product been able to turn on in the past?");
								answer = input.nextLine();
								while(!works){
									phrases();
									if(yes) {
										System.out.println("Plug it in and let it charge for a day or two, if it doesn't start ship it and we'll replace it.");
										answer = input.nextLine();
										works = true;
									}
									else {
										System.out.println("Ship it back to us, we'll replace it.");
										answer = input.nextLine();
										works = true;
									}
								}
							}
							else if(yes) {
								System.out.println("Did it work?");
								answer = input.nextLine();
								if(yes) {
									System.out.println("Wonderful, did you have any other questions?");
									answer = input.nextLine();
									works = true;
								}
								else {
									System.out.println("Has the product been able to turn on in the past?");
									answer = input.nextLine();
									while(!works){
										phrases();
										if(yes) {
											System.out.println("Plug it in and let it charge for a day or two, if it doesn't start ship it and we'll replace it.");
											answer = input.nextLine();
											works = true;
										}
										else {
											System.out.println("Ship it back to us, we'll replace it.");
											answer = input.nextLine();
											works = true;
										}
									}
								}
							}
							else{
								System.out.println("Wonderful, did you have any other questions?");
								answer = input.nextLine();
								works = true;
							}
						}
					}
				}
				
			} 
			
			//dialog for if the problem isn't specified very well
			else if (notWorking && productName) {
				System.out.println("Can you be more specific, what exactly is the problem?");
				answer = input.nextLine();
			} 
			
			//dialog for if the product is having slow input / delayed responses
			else if ((productName && lag) || (program && lag) || (lag)) {
				System.out.println("Try deleting the cashe");
				answer = input.nextLine();
				boolean works = false;
				while(!works){
					phrases();
					if(itWorked) {
						System.out.println("Great, did you have any other questions?");
						answer = input.nextLine();
						works = true;
					}
					else {
						System.out.println("Did it work?");
						answer = input.nextLine();
						phrases();
						if(itWorked || yes) {
							System.out.println("Great, did you have any other questions?");
							answer = input.nextLine();
							works = true;
						}
						else {
							System.out.println("Try reopening the program, or restarting the product. If that doesn't work you can ship it back to us to take a look at it.");
							answer = input.nextLine();
							phrases();
							works = true;
						}
					}
				}
			}
			
			//dialog for if the product's fan is very loud for some reason
			else if(fanLoud) {
				System.out.println("Check task manager to see if something is taking up a lot of CPU or memory power");
				answer = input.nextLine();
				boolean works = false;
				String answertemp2 = answer;
				while(!works) {
					phrases();
					if(knowItAll) {
						System.out.println("Just answer the question please.");
						answer = input.nextLine();
						answertemp2 = answer;
					}
					else if(ok || answer.toLowerCase().contains("nothing is") || no || answertemp2.toLowerCase().contains("yes")) {
						System.out.println("If nothing is then check if anything is downloading");
						answer = input.nextLine();
						phrases();
						if(knowItAll) {
							System.out.println("Alright, you seem to know everything so best of luck! Goodbye.");
							System.exit(0);
						}
						else if(ok || answer.toLowerCase().contains("nothing is") || no) {
							System.out.println("You can return the product to us to look at it in that case");
							answer = input.nextLine();
							works = true;
						}
					}
				}
			}
			
			//dialog for if the product turns itself off
			else if(autoOff) {
				System.out.println("Is it recouring? If it's a one time thing it's probably just an operating system bug. It happens, just restart the machine.");
				answer = input.nextLine();
				boolean works = false;
				while(!works){
					phrases();
					if(itWorked || ok) {
						System.out.println("Any other questions?");
						answer = input.nextLine();
						works = true;
					}
					else if(no) {
						System.out.println("Then just restart the machine and it should be solved. Any other questions?");
						answer = input.nextLine();
						works = true;
					}
					else {
						System.out.println("Check to see how much space your storage has, how full is it?");
						answer = input.nextLine();
						if(lowSpace) {
							System.out.println("Try deleting some items or cleaning up the disk. If that doesn't work ship us back the item and we'll take a look.");
							answer = input.nextLine();
							works = true;
						}
						else {
							System.out.println("Ship us back the item and we'll take a look.");
							answer = input.nextLine();
							works = true;
						}
					}
				}
			}
			
			//dialog for if the product's charger is broken
			else if(charger) {
				System.out.println("Ship it back to us, and we'll replace it.");
				answer = input.nextLine();
			}
			
			//dialog for if user wants to return the product
			else if(returns) {
				System.out.println("Could you tell me why you want to return it?");
				answer = input.nextLine();
				System.out.println("Alright, send it back to us and we'll refund you.");
				answer = input.nextLine();
			}
			
			//dialog for if the user wants to exchange the product
			else if(exchange) {
				System.out.println("Could you tell me why you want to exchange it?");
				answer = input.nextLine();
				System.out.println("Alright, send it back to us and we'll exchange it for you.");
				answer = input.nextLine();
			}
			
			//general dialog for if a question is answered / finished
			else if(yes) {
				long random = Math.round( Math.random());
				if(random < 0.5) {
					System.out.println("Alright, what else would you like to ask?");
					answer = input.nextLine();
				}else {
					System.out.println("Alright, what would you like to ask?");
					answer = input.nextLine();
				}
			}
			
			//general dialog 2 for if a question is answered / finished
			else if(ok) {
				System.out.println("Anything else?");
				answer = input.nextLine();
			}
			// say goodbye and end program
			else {
				System.out.println("Okay, have a good day. Goodbye!");
				answer = input.nextLine();
				System.exit(0);		
			}
		}
		
		input.close();

	}

}
