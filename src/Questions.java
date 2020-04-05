import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;

public class Questions {
	//variables
	static String answer;
	static String menu;
	static int submenu;
	
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
	public Questions(String answer, String menu, int submenu) {
		Questions.answer = answer;
		Questions.menu = menu;
		Questions.submenu = submenu;
	}

	// Reinitializes the phrases each call, updating values to what is inputted by user
	public static void phrases() {

		//Initial values for common phrases and words used by bot to check statements
		//(like different ways of saying product name, it's not not working etc etc)
		productName = answer.toLowerCase().contains("product") || answer.toLowerCase().contains("it") || answer.toLowerCase().contains("computer") || answer.toLowerCase().contains("laptop") || answer.toLowerCase().contains("device");
		notWorking = answer.toLowerCase().contains("not working") || answer.toLowerCase().contains("isn't working" ) || answer.toLowerCase().contains("isnt working") || answer.toLowerCase().contains("issues");
		forceRestart = answer.toLowerCase().contains("force") && answer.toLowerCase().contains("restart");
		itWorked = answer.toLowerCase().contains("it worked") || answer.toLowerCase().contains("it works");
		didntWork = (answer.toLowerCase().contains("didn't") || answer.toLowerCase().contains("didnt")) && (answer.toLowerCase().contains("work") || productName);
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
		yes = answer.toLowerCase().contains("yes") || answer.toLowerCase().contains("yeah") || answer.toLowerCase().contains("ya");
		no = answer.toLowerCase().contains("no");
		ok = answer.toLowerCase().contains("ok") || answer.toLowerCase().contains("okay") || answer.toLowerCase().contains("k");
		what = answer.toLowerCase().contains("what");
		crash = answer.toLowerCase().contains("crash") || answer.toLowerCase().contains("die");
		program = answer.toLowerCase().contains("program");
		ido = answer.toLowerCase().contains("i do");
		returns = answer.toLowerCase().contains("return");
		exchange = answer.toLowerCase().contains("exchange");
	}
	
	public static void terminalreset() {
		Terminal.menu = "";
		Terminal.sentinelsubmenu = 0;
	}
	
	public static void terminalsubmenu() {
		Terminal.sentinelsubmenu = Terminal.sentinelsubmenu + 1;
	}
	
	public static void terminalwrite(String text) {
		Terminal.chatlog.append(text);
	}
	
	public static void main(String[] args) {
		
		//Initial values for common phrases and words used by bot to check statements
		//(like different ways of saying product name, it's not not working etc etc)
		productName = answer.toLowerCase().contains("product") || answer.toLowerCase().contains("it") || answer.toLowerCase().contains("computer") || answer.toLowerCase().contains("laptop") || answer.toLowerCase().contains("device");
		notWorking = answer.toLowerCase().contains("not working") || answer.toLowerCase().contains("isn't working" ) || answer.toLowerCase().contains("isnt working") || answer.toLowerCase().contains("issues");
		forceRestart = answer.toLowerCase().contains("force") && answer.toLowerCase().contains("restart");
		itWorked = answer.toLowerCase().contains("it worked") || answer.toLowerCase().contains("it works");
		didntWork = (answer.toLowerCase().contains("didn't") || answer.toLowerCase().contains("didnt")) && (answer.toLowerCase().contains("work") || productName);
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
		yes = answer.toLowerCase().contains("yes") || answer.toLowerCase().contains("yeah") || answer.toLowerCase().contains("ya");
		no = answer.toLowerCase().contains("no");
		ok = answer.toLowerCase().contains("ok") || answer.toLowerCase().contains("okay");
		what = answer.toLowerCase().contains("what");
		crash = answer.toLowerCase().contains("crash") || answer.toLowerCase().contains("die");
		program = answer.toLowerCase().contains("program");
		ido = answer.toLowerCase().contains("i do");
		returns = answer.toLowerCase().contains("return");
		exchange = answer.toLowerCase().contains("exchange");
		
		//dialog for if user wants to return the product
		if(returns || menu.contains("returns")) {
			Terminal.menu = "returns";
			
			if(Terminal.sentinelsubmenu == 0) {
				terminalwrite("Could you tell me why you want to return it?");
				terminalsubmenu();
				return;
			}
			if(Terminal.sentinelsubmenu == 1) {
				terminalwrite("Alright, send it back to us and we'll refund you.");
				terminalreset();
				return;
			}
		}
		
		//dialog for if the user wants to exchange the product
		else if(exchange || menu.contains("exchange")) {
			Terminal.menu = "exchange";
			
			if(Terminal.sentinelsubmenu == 0) {
				terminalwrite("Could you tell me why you want to exchange it?");
				terminalsubmenu();
				return;
			}
			if(Terminal.sentinelsubmenu == 1) {
				terminalwrite("Alright, send it back to us and we'll exchange it for you.");
				terminalreset();
				return;
			}
		}
		
		//dialog for if the product is overheating
		else if(overheat || menu.contains("overheat")) {
			Terminal.menu = "overheat";
			
			if(Terminal.sentinelsubmenu == 0) {
				terminalwrite("Have you tried putting it ontop of something so the fans underneath have better circulation?");
				terminalsubmenu();
				return;
			}
			if(Terminal.sentinelsubmenu == 1){
				if(yes) {
					terminalwrite("There isn't anything else to really do about that besides trying to let the fans have as much circulation as possible. Avoid using RAM intensive applications for long peroids of time to help.");
					terminalreset();
					return;
				}
				else {
					terminalwrite("Try that then, anything else?");
					terminalreset();
					return;
				}
			}
		}
			
		//dialog for wifi card not working
		else if(wifi || menu.contains("wifi")) {
			Terminal.menu = "wifi";
			
			if(Terminal.sentinelsubmenu == 0) {
				terminalwrite("Try troubleshooting the wifi adapter / replugging it in if it's a external one");
				terminalsubmenu();
				return;
			}
			
			if(Terminal.sentinelsubmenu == 1) {
				if(itWorked) {
					terminalwrite("Wonderful, anything else?");
					terminalreset();
					return;
				}
				else {
					terminalwrite("Did that fix it?");
					terminalsubmenu();
					return;
				}
			}
			if(Terminal.sentinelsubmenu == 2) {
				if(itWorked) {
					terminalwrite("Wonderful, anything else?");
					terminalreset();
					return;
				}
				else {
					terminalwrite("Try resetting wifi settings then, if that doesn't work send it back to us.");
					terminalreset();
					return;
				}
			}
		}
			
		//dialog for if the sound on the computer is too low
		else if(soundLow || menu.contains("soundlow")) {
			Terminal.menu = "soundlow";
			
			if(Terminal.sentinelsubmenu == 0) {
				terminalwrite("Try playing sounds with another device, is it still low using the other device?");
				terminalsubmenu();
				return;
			}
			if(Terminal.sentinelsubmenu == 1) {
				if(yes) {
					terminalwrite("Send it back to us to take a look then");
					terminalreset();
					return;
				}
				else {
					terminalwrite("Go to settings, audio, and reset audio settings. Did that fix it?");
					terminalsubmenu();
					return;
				}
			}
			if(Terminal.sentinelsubmenu == 2) {
				if(yes) {
					terminalwrite("Wonderful, anything else?");
					terminalreset();
					return;
				}
				else {
					terminalwrite("Send it back to us to take a look then, anything else?");
					terminalreset();
					return;
				}
			}
		}

			
		//dialog for if the product is not turning on or is crashing
		else if ((productName && wont && turnOn) || (productName && crash) || menu.contains("noton")) {
			Terminal.menu = "noton";
			
			if(Terminal.sentinelsubmenu == 0) {
				terminalwrite("Try unplugging the power cable and plugging it back in a few seconds later.");	
				terminalsubmenu();
				return;
			}
			
			if(Terminal.sentinelsubmenu == 1) {
				if(itWorked || yes) {
					terminalwrite("Did you have any other questions?");
					terminalreset();
					return;
				}
				else {
					terminalwrite("Did it work?");
					terminalsubmenu();
					return;
				}
			}
			if(Terminal.sentinelsubmenu == 2) {
				if(yes) {
					terminalwrite("Wonderful, did you have any other questions?");
					terminalreset();
					return;
				}
				else {
					terminalwrite("Have you tried a force restart?");	
					terminalsubmenu();
					return;
				}
			}
			if(Terminal.sentinelsubmenu == 3) {
				if((forceRestart && (what || how)) || no) {
					terminalwrite("Hold the power button for 5 seconds, wait 10 seconds then press it again. If that didn't work, has the product been able to turn on in the past?");
					terminalsubmenu();
					return;
				}
				else{
					terminalwrite("Has the product been able to turn on in the past?");
					terminalsubmenu();
					return;
				}
			}
			if(Terminal.sentinelsubmenu == 4) {
				if(yes) {
					terminalwrite("Plug it in and let it charge for a day or two, if it doesn't start ship it and we'll replace it.");
					terminalreset();
					return;
				}
				else {
					terminalwrite("Ship it back to us, we'll replace it.");
					terminalreset();
					return;
				}
			}
		}
			
			//dialog for if the problem isn't specified very well
			else if (notWorking && productName) {
				terminalwrite("Can you be more specific, what exactly is the problem?");
			} 
			
			//dialog for if the product is having slow input / delayed responses
			else if ((productName && lag) || (program && lag) || (lag) || menu.contains("lag")) {
				Terminal.menu = "lag";
				
				if(Terminal.sentinelsubmenu == 0) {
					terminalwrite("Try deleting the cashe");	
					terminalsubmenu();
					return;
				}
				
				if(Terminal.sentinelsubmenu == 1) {
					if(itWorked) {
						terminalwrite("Great, did you have any other questions?");
						terminalreset();
						return;

					}
					else {
						terminalwrite("Did it work?");
						terminalsubmenu();
						return;
					}
				}
				if(Terminal.sentinelsubmenu == 2) {
					if(itWorked || yes) {
						terminalwrite("Great, did you have any other questions?");
						terminalreset();
						return;
					}
					else {
						terminalwrite("Try reopening the program, or restarting the product. If that doesn't work you can ship it back to us to take a look at it.");
						terminalreset();
						return;
					}
				}
			}
			
			//dialog for if the product's fan is very loud for some reason
			else if(fanLoud || menu.contains("fanloud")) {
				Terminal.menu = "fanloud";
				
				if(Terminal.sentinelsubmenu == 0) {
					terminalwrite("Check task manager to see if something is taking up a lot of CPU or memory power.");
					terminalsubmenu();
					return;
				}

				if(Terminal.sentinelsubmenu == 1) {
					if(ok || answer.toLowerCase().contains("nothing is") || no ) {
						terminalwrite("If nothing is then check if anything is downloading");
						terminalsubmenu();
						return;
					}
					else if (yes) {
						terminalwrite("Free up some of those programs. Any other questions?");
						terminalreset();
						return;
					}
					else {
						terminalwrite("Any other questions?");
						terminalreset();
						return;
					}
				}
				
				if(Terminal.sentinelsubmenu == 2) {
					if(ok || answer.toLowerCase().contains("nothing is") || no) {
						terminalwrite("You can return the product to us to look at it in that case");
						terminalreset();
						return;
					}
					else {
						terminalwrite("Stop the downloads and check wait a bit, if that does't fix it return the product.");
						terminalreset();
						return;
					}
				}
			}
			
			//dialog for if the product turns itself off
			else if(autoOff || menu.contains("autooff")) {
				Terminal.menu = "autooff";
				
				if(Terminal.sentinelsubmenu == 0) {
					terminalwrite("Is it recouring? If it's a one time thing it's probably just an operating system bug. It happens, just restart the machine.");
					terminalsubmenu();
					return;
				}

				
				if(Terminal.sentinelsubmenu == 1) {
					if(itWorked || ok) {
						terminalwrite("Any other questions?");
						terminalreset();
						return;
					}
					else if(no) {
						terminalwrite("Then just restart the machine and it should be solved. Any other questions?");
						terminalreset();
						return;
					}
					else {
						terminalwrite("Check to see how much space your storage has, how full is it?");
						terminalsubmenu();
						return;
					}
				}
				if(Terminal.sentinelsubmenu == 1) {
						if(lowSpace) {
							terminalwrite("Try deleting some items or cleaning up the disk. If that doesn't work ship us back the item and we'll take a look.");
							terminalreset();
							return;
						}
						else {
							terminalwrite("Ship us back the item and we'll take a look.");
							terminalreset();
							return;
						}
				}
			}
			
			//dialog for if the product's charger is broken
			else if(charger) {
				terminalwrite("Ship it back to us, and we'll replace it.");
				terminalreset();
				return;
			}
			
			//general dialog for if a question is answered / finished
			else if(yes) {
				long random = Math.round( Math.random());
				if(random < 0.5) {
					terminalwrite("Alright, what else would you like to ask?");
					return;
				}else {
					terminalwrite("Alright, what would you like to ask?");
					return;
				}
			}
			
			//general dialog 2 for if a question is answered / finished
			else if(ok) {
				terminalwrite("Anything else?");
				return;
			}
			// say goodbye and end program
			else {
				Terminal.chatlog.append("Okay, have a good day. Goodbye!");
				return;
			}
		}
	}
