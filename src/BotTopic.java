import java.io.*;
import java.util.*;
public class BotTopic {
	//variables
	static String answer;
	static String answerTemp;
	static boolean continuing;
	
	//constructor
	public BotTopic(String answer) {
		BotTopic.answer = answer;
	}

	public static void terminalwrite(String text) {
		Terminal.chatlog.append(text);
	}
	
	public static void main(String[] args) {
		//while continuing is true keep looping
			//if user has a problem or complaint
			if (answer.toLowerCase().contains("complaint") || answer.toLowerCase().contains("problem")) {
				terminalwrite("What's the problem?");
				Terminal.sentinelvalue = 1;
			} 
			//if user has a question
			else if (answer.toLowerCase().contains("question")) {
				terminalwrite("What's the question?");
				Terminal.sentinelvalue = 1;
			} 
			//if the user decides he has nothing to ask end program
			else if (answer.toLowerCase().contains("never mind") || (answer.toLowerCase().contains("nothing") && (answer.toLowerCase().contains("ask") || answer.toLowerCase().contains("say")))) {
				System.exit(0);
			} 
			//if the user says hello back for some reason ask if they have a question / complaint
			else if (answer.toLowerCase().contains("hello") && !answer.toLowerCase().contains("complaint") && !answer.toLowerCase().contains("problem") && !answer.toLowerCase().contains("review")) {
				terminalwrite("Did you have a question to ask or a complaint?");
				
				//if they answer "yes" (aka not answering at all) ask again which one they have (question or complaint)
				if(answer.toLowerCase().contains("yes")) {
					terminalwrite("But which one? a complaint or a question?");
				}
				//if they don't then end program
				else {
					System.exit(0);
				}
			} 
			
			
			//the new topic added to repertoire --- Product Review and Product Rating


			//To read reviews/ratings about the products
			else if ( !answer.toLowerCase().contains("write") && (answer.toLowerCase().contains("see") || answer.toLowerCase().contains("read") || answer.toLowerCase().contains("know") || answer.toLowerCase().contains("like to")) || (answer.toLowerCase().contains("about")	|| answer.toLowerCase().contains("review") || answer.toLowerCase().contains("rate") || answer.toLowerCase().contains("rating"))	){
				terminalwrite("Which product would you like to revise?");	
				String p_name = Terminal.input.getText();
					try {
						Scanner scanner_review = new Scanner(new File("src/review.txt"));
						Scanner scanner_rate = new Scanner(new File("src/rating.txt"));
						StringBuilder reviews = new StringBuilder();
						StringBuilder ratings = new StringBuilder();
						int count_reviews = 0;
						int count_rates = 0;

						while (scanner_review.hasNextLine()) {
							String line = scanner_review.nextLine();
							String lines[] = line.split("~~~");
							
							if(lines[0].equalsIgnoreCase(p_name)) {
								reviews.append(lines[1] + "\n");
								count_reviews++;
							}
						
						}

						while (scanner_rate.hasNextLine()) {
							String line = scanner_rate.nextLine();
							String lines[] = line.split("~~~");
							if(lines[0].equalsIgnoreCase(p_name)) {
								ratings.append(lines[1] + "\n");
								count_rates++;
							}
						}

						terminalwrite("We found " + count_rates + " rating(s) "  + count_reviews + " review(s) for (" + p_name + ")");
						terminalwrite("Would you like to see any of the reviews and the ratings?");
						//give user the option to choose whether to display both reviews & ratings
						answer = Terminal.input.getText();

						//if the user refuses, terminate the program
						if ( answer.toLowerCase().contains("never mind") || answer.toLowerCase().contains("nothing") || answer.toLowerCase().contains("no") || answer.toLowerCase().contains("nope") || answer.toLowerCase().contains("not at all")){
							terminalwrite("Alright. Have a Good Day");
							System.exit(0);
						}
						//display the ratings of the product
						else if( !answer.toLowerCase().contains("review") && answer.toLowerCase().contains("only") && ( answer.toLowerCase().contains("rate") || answer.toLowerCase().contains("rating")) ){
							terminalwrite("Here are the ratings for the product (" + p_name + ")" + "\n" + ratings);
						}
						//display the reviews of the product
						else if(!answer.toLowerCase().contains("rating") && answer.toLowerCase().contains("only") && answer.toLowerCase().contains("review") || answer.toLowerCase().contains("revision") || answer.toLowerCase().contains("revise")){
							terminalwrite("Here are the reviews for the product (" + p_name +")" +"\n" + reviews);
						}
						//in other cases, display both reviews and ratings 
						else{
							terminalwrite("Here are both reviews and ratings for the product ("+ p_name +")" +"\nReviews:\n" + reviews +"\nRatings:\n" + ratings);
						}

				 	} catch (Exception e) {

						terminalwrite("Can't find the the data file.");
						e.printStackTrace();
						System.exit(1);
					}

			}

			//To write/save the review or rating for a product
			else if(answer.toLowerCase().contains("write") || answer.toLowerCase().contains("review") || ( answer.toLowerCase().contains("about") && answer.toLowerCase().contains("product") && !answer.toLowerCase().contains("read")) || answer.toLowerCase().contains("revision") || answer.toLowerCase().contains("rate")){
				//ask if user wants to write a review or to give a rate on a product
				terminalwrite("Did you want to write a review about product? or to rate a product?");
				answer = Terminal.input.getText();
				//if the user wants to write a review about a product, let them do it and save it into review.txt file
				if(answer.toLowerCase().contains("review") || answer.toLowerCase().contains("revision") || answer.toLowerCase().contains("revise") || answer.toLowerCase().contains("write")){
					
					boolean loop_product = true;
					while(loop_product){
						terminalwrite("Please indicate the product that want to write a review about:");
						String review_product = Terminal.input.getText();
						terminalwrite(review_product + " is the product. Correct? (y/n)");
						answer = Terminal.input.getText();
						if (answer.toLowerCase().equals("y")){
							terminalwrite("Please write your review about the product (" + review_product + ") on the line below");
							String review = Terminal.input.getText();
							try{
								FileWriter review_file = new FileWriter("src/review.txt", true);
								//save the prdocut reviews as product_name~~~product_reivew format
								review_file.write(review_product +"~~~"+ review + "\n");
								review_file.close();
								terminalwrite("Your review is saved! Thank you!");
								
								//break the loop
								answer = Terminal.input.getText();
								loop_product = false;
							}catch (IOException e) {
								terminalwrite("An error occurred.");
								e.printStackTrace();
							}
							
							

						}
						else if (answer.toLowerCase().equals("n")){
							//start the loop again to get the name of the product
							loop_product = true;
						}
						else{
							//break the loop if the user writes something else
							loop_product = false;
							
						}
					}

				}
				//if the user wants to give a rating on a product let them do it and save their rating(s) to rating.txt
				else if (answer.toLowerCase().contains("rate") || answer.toLowerCase().contains("rating")){
					terminalwrite("Please the indicate the product that you want to give rating");
					String rate_product = Terminal.input.getText();
					//boolean for the while loop below
					boolean rate_loop = true;
					while(rate_loop){
						terminalwrite("Please the indicate the rating number - from 1 to 5 - for this product: (" + rate_product + ")");
						//keep prompting the user until they enter a valid integer
						while(!Terminal.input.getText().matches("\\d+")) {
							terminalwrite("Please enter a single digit from 1 to 5!!");
						}
						//the user puts an integer in as expected
						int rate_num = Integer.parseInt(answer);
						
						if(rate_num < 5){
							
							try{
								FileWriter rate_file = new FileWriter("src/rating.txt", true);
								//save the ratings as product_name~~~product_rating format
								rate_file.write(rate_product +"~~~"+ rate_num + "\n");
								rate_file.close();
								terminalwrite(rate_product + " - " + rate_num + " Your product rating is saved! Thank you!");
								rate_loop = false;
							}catch (IOException e) {
								terminalwrite("An error occurred.");
								e.printStackTrace();
								System.exit(1);
							}
							
						}
						//if not, loop until they do
						else{
							terminalwrite("Wrong number!!!");
							rate_loop = true;
						}
					}

					
				}

			}
			
			
			
			
			
			else {
				//get the question from the input and generate an answer
				RandomQuestions question = new RandomQuestions(answer);
				terminalwrite(question.close_answer);

//				//ask user if it's a question or complaint
//				terminalwrite("Is that a problem you want solved or are you writing a complaint?");
//				//if they answer "yes" reinforce if it's a question or complaint
//				if(answer.toLowerCase().contains("yes")) {
//					terminalwrite("But which one? a complaint or a question?");
//				}

			}
			

			
		}
}
