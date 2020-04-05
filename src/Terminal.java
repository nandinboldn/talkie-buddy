import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;


public class Terminal {

	static String answer;
	protected Shell shell;
	static Text chatlog;
	static Button enter;
	static Text input;
	
	//counter to check if user is asking a general topic / area or a specific question
	// 0 = main topics 1 = questions 2 = reviews
	static int sentinelvalue = 0;
	static int sentinelsubmenu = 0;
	static String menu = "";
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Terminal window = new Terminal();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		//main gui
		shell = new Shell();
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		shell.setSize(900, 700);
		shell.setText("SWT Application");
		
		//chatlog gui
		chatlog = new Text(shell,SWT.WRAP | SWT.READ_ONLY | SWT.V_SCROLL);
		chatlog.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		chatlog.setBounds(29, 39, 816, 422);
		
		//user input gui
		input = new Text(shell, SWT.V_SCROLL);
		input.setBounds(29, 534, 605, 84);
		
		//enter button
		enter = new Button(shell, SWT.NONE);
		enter.setBounds(671, 534, 174, 84);
		enter.setText("Enter");
		
		//inital statement
		chatlog.append("Hello, how may i help you?");
		
		//spell checking class
		SpellCheck sc = new SpellCheck();
		
		//button click
		enter.addListener(SWT.Selection, new Listener()
		{
		    @Override
		    public void handleEvent(Event event)
		    {
		    	String text = input.getText();
		    	//sc.run() checks the spelling of the sentence, thus only gets inside the loop if the no spelling mistake
		    	//otherwise run() will print spelling mistakes
		    	if(sc.run(text)) {
			    	
		    		input.setText("");
					chatlog.append("\n"+text+"\n");

					if(sentinelvalue == 0) {
						new BotTopic(text);
						BotTopic.main(null);
					}
					else if (sentinelvalue == 1) {
						new Questions(text, menu, sentinelsubmenu);
						Questions.main(null);
					}
					else {
						
					}
		    	}
		    	


		    	
		    }
		});
	}
}
