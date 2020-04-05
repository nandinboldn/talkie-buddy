import java.io.*;
import java.util.*;

public class RandomQuestions {
    static String close_answer;
    //hashmap to store the questions along with their answers
    static HashMap <String,String> randomQA = new HashMap<String,String>(100);

    //the random questions with their appropriate answers 
    //general question asking about the chatbot and the mood
    String general1  = randomQA.put("How are you?","I'm good. Thank you!");
    String general2  = randomQA.put("What's up?","Nothing much. How are you?");
    String general21  = randomQA.put("What is up?","Nothing much. Do you have any question for me?");
    String general3  = randomQA.put("How was your day?","My day was good,Thank you!\tHow was yours :) ?");
    String general4  = randomQA.put("Oka","Do you have any question for me ? :)");
    String general5  = randomQA.put("What are you?","I'm a chatbot. Thank you!");
    String general6  = randomQA.put("who are you?","I'm a chatbot, and my name is Talkie-Buddy. Thank you!");
    String general7  = randomQA.put("How old are you?","I'm only few months old. and you ?");
    String general8  = randomQA.put("Hey","Hey there. Did you have any questions for me ?");
    String general9  = randomQA.put("Hi","Hey there. Did you have any questions for me ?");
    String general10  = randomQA.put("I am good ","Okay. Did you have any questions for me ?");
    String general11  = randomQA.put("I'm good ","Okay. Did you have any questions for me ?");
    String general12  = randomQA.put("Good","Okay.Did you have any questions for me ?");
    
    
    //questions relatod to contact information of the company/store
    String contact1  = randomQA.put("Number to contact to store","Here are the number of company: 1-600-600-6000 , 1-800-900-1000");
    String contact2  = randomQA.put("Address of the store?","Here is the address of our local branch: 2424 Kobe St. , Laker way, LA");
    String contact3  = randomQA.put("How to contact","You can reach our local branch at 1-600-600-6000 , or emailmequick@gmail.com");
    String contact4  = randomQA.put("What is your working hours?","Our store is open on Mon-Fri 8AM-5PM");
    String contact5  = randomQA.put("What is your opening hours?","Our store opens at 8AM Mon-Fri");
    //random question
    String q3  = randomQA.put("No i dont want to","Okay. I won't push you :)");
    String q4 = randomQA.put("boring","I'm bored too!");
    String q5  = randomQA.put("bore","I'm bored too!");
    
    //cursing words
    String q6  = randomQA.put("Shut up","I can understand you. Please ask me a question? :)");
    String q7  = randomQA.put("Fuck","I can understand you. Please ask me a question? :)");
    String q8  = randomQA.put("Piss off","I can understand you. Please ask me a question? :)");
    String q9  = randomQA.put("you are stupid","I can understand you. Please ask me a question? :)");
    String q10  = randomQA.put("Piss off","I can understand you. Please ask me a question? :)");
    String q11  = randomQA.put("stupid","I can understand you. Please ask me a question? :)");
    
   
    
    public String returnAnswer(String answer){
        //In case we don't get any answer reasonable to the question
        String close_answer = "I couldn't understand what you said! Sorry :(";
        for (String answer_key: randomQA.keySet()){
            double result = returnSimilarity(answer, answer_key);
            //if the user's input is more than 65% similar to the keys stored
            if (result > 0.75) close_answer = randomQA.get(answer_key);
        }
        return close_answer;
    }

    public RandomQuestions(String answer){
        RandomQuestions.close_answer = returnAnswer(answer);
        
    }
	/**
     * JaroWinklerDistance
     * Copied from https://commons.apache.org/sandbox/commons-text/jacoco/org.apache.commons.text.similarity/JaroWinklerDistance.java.html
     * apply method changed to returnSimilarity
     */
    public static Double returnSimilarity(final CharSequence left, final CharSequence right) {
        final double defaultScalingFactor = 0.1;
        final double percentageRoundValue = 100.0;

        if (left == null || right == null) {
            throw new IllegalArgumentException("Strings must not be null");
        }

        int[] mtp = matches(left, right);
        double m = mtp[0];
        if (m == 0) {
            return 0D;
        }
        double j = ((m / left.length() + m / right.length() + (m - mtp[1]) / m)) / 3;
        double jw = j < 0.7D ? j : j + Math.min(defaultScalingFactor, 1D / mtp[3]) * mtp[2] * (1D - j);
        return Math.round(jw * percentageRoundValue) / percentageRoundValue;
    }

    protected static int[] matches(final CharSequence first, final CharSequence second) {
        CharSequence max, min;
        if (first.length() > second.length()) {
            max = first;
            min = second;
        } else {
            max = second;
            min = first;
        }
        int range = Math.max(max.length() / 2 - 1, 0);
        int[] matchIndexes = new int[min.length()];
        Arrays.fill(matchIndexes, -1);
        boolean[] matchFlags = new boolean[max.length()];
        int matches = 0;
        for (int mi = 0; mi < min.length(); mi++) {
            char c1 = min.charAt(mi);
            for (int xi = Math.max(mi - range, 0), xn = Math.min(mi + range + 1, max.length()); xi < xn; xi++) {
                if (!matchFlags[xi] && c1 == max.charAt(xi)) {
                    matchIndexes[mi] = xi;
                    matchFlags[xi] = true;
                    matches++;
                    break;
                }
            }
        }
        char[] ms1 = new char[matches];
        char[] ms2 = new char[matches];
        for (int i = 0, si = 0; i < min.length(); i++) {
            if (matchIndexes[i] != -1) {
                ms1[si] = min.charAt(i);
                si++;
            }
        }
        for (int i = 0, si = 0; i < max.length(); i++) {
            if (matchFlags[i]) {
                ms2[si] = max.charAt(i);
                si++;
            }
        }
        int transpositions = 0;
        for (int mi = 0; mi < ms1.length; mi++) {
            if (ms1[mi] != ms2[mi]) {
                transpositions++;
            }
        }
        int prefix = 0;
        for (int mi = 0; mi < min.length(); mi++) {
            if (first.charAt(mi) == second.charAt(mi)) {
                prefix++;
            } else {
                break;
            }
        }
        return new int[] { matches, transpositions / 2, prefix, max.length() };
    }




}