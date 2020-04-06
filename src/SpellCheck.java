
import java.util.ArrayList;
import java.util.Scanner;

public class SpellCheck {

    private Dictionary dict;
    //using the text file dictionary words.txt expecting it's located one directory level up
    final static String filePath = "src/words.txt";
    final static char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    
    
    SpellCheck() {
        dict = new Dictionary();
        dict.build(filePath);

    }
    
    static void terminalwrite(String text) {
		Terminal.chatlog.append("\n" +text);
	}
    
    boolean run(String input_sentence) {
        Scanner scan = new Scanner(input_sentence);
        boolean done = true;

        //iterate through the sentence
        for(String word: input_sentence.split("\\s+")){

            if(word.equals("")){
                return false;
            }
            //if the word in a sentence is in a dictionary ~~ words.txt
            if(dict.contains(word)){
                done = done && true;
            }else{
		terminalwrite(input_sentence);
                terminalwrite(word + " is not spelled correctly, " + printSuggestions(word));
                done = done && false;
            }

        }
        return done;
    }

    String printSuggestions(String input) {
        StringBuilder sb = new StringBuilder();
        ArrayList<String> print = makeSuggestions(input);
        //if there is no suggestion
        if (print.size() == 0) {
            return "and I have no idea what word you could mean :( \n";
        }
        //if there's any suggestion(s)
        sb.append("perhaps you meant ");
        int i = 1;
        for (String s : print) {
            sb.append(s);
            if(i < print.size()) {
            	sb.append(", or ");
            }
            i++;
        }
    	sb.append("?");
        return sb.toString();
    }

    private ArrayList<String> makeSuggestions(String input) {
        ArrayList<String> toReturn = new ArrayList<>();
        toReturn.addAll(charAppended(input));
        toReturn.addAll(charMissing(input));
        toReturn.addAll(charsSwapped(input));
        return toReturn;
    }

    private ArrayList<String> charAppended(String input) { 
        ArrayList<String> toReturn = new ArrayList<>();
        for (char c : alphabet) {
            String atFront = c + input;
            String atBack = input + c;
            if (dict.contains(atFront)) {
                toReturn.add(atFront);
            }
            if (dict.contains(atBack)) {
                toReturn.add(atBack);
            }
        }
        return toReturn;
    }

    private ArrayList<String> charMissing(String input) {   
        ArrayList<String> toReturn = new ArrayList<>();

        int len = input.length() - 1;
        //try removing char from the front
        if (dict.contains(input.substring(1))) {
            toReturn.add(input.substring(1));
        }
        for (int i = 1; i < len; i++) {
            //try removing each char between (not including) the first and last
            String working = input.substring(0, i);
            working = working.concat(input.substring((i + 1), input.length()));
            if (dict.contains(working)) {
                toReturn.add(working);
            }
        }
        if (dict.contains(input.substring(0, len))) {
            toReturn.add(input.substring(0, len));
        }
        return toReturn;
    }

    private ArrayList<String> charsSwapped(String input) {   
        ArrayList<String> toReturn = new ArrayList<>();

        for (int i = 0; i < input.length() - 1; i++) {
            String working = input.substring(0, i);
            working = working + input.charAt(i + 1); 
            working = working + input.charAt(i); 
            working = working.concat(input.substring((i + 2)));
            if (dict.contains(working)) {
                toReturn.add(working);
            }
        }
        return toReturn;
    }

}
