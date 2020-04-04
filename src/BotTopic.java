package src;

import java.io.*;
import java.util.Scanner;

public class BotTopic {
	//variables
	static String answer;
	static String answerTemp;
	static boolean continuing;
	
	//constructor
	public BotTopic(String answer, boolean continuing) {
		BotTopic.answer = answer;
		BotTopic.continuing = continuing;
	}

	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		
		//while continuing is true keep looping
		while (continuing) {	
			//if user has a problem or complaint
			if (answer.toLowerCase().contains("complaint") || answer.toLowerCase().contains("problem")) {
				System.out.println("What's the problem?");
				answer = input.nextLine();
				new Questions(answer, continuing);
				Questions.main(args);
			} 
			//if user has a question
			else if (answer.toLowerCase().contains("question")) {
				System.out.println("What's the question?");
				answer = input.nextLine();
				new Questions(answer, continuing);
				Questions.main(args);
			} 
			//if the user decides he has nothing to ask end program
			else if (answer.toLowerCase().contains("never mind") || (answer.toLowerCase().contains("nothing") && (answer.toLowerCase().contains("ask") || answer.toLowerCase().contains("say")))) {
				System.exit(0);
			} 
			//if the user says hello back for some reason ask if they have a question / complaint
			else if (answer.toLowerCase().contains("hello") && !answer.toLowerCase().contains("complaint") && !answer.toLowerCase().contains("problem") && !answer.toLowerCase().contains("review")) {
				System.out.println("Did you have a question to ask or a complaint?");
				answer = input.nextLine();
				//if they answer "yes" (aka not answering at all) ask again which one they have (question or complaint)
				if(answer.toLowerCase().contains("yes")) {
					System.out.println("But which one? a complaint or a question?");
					answer = input.nextLine();
				}
				//if they don't then end program
				else {
					System.exit(0);
				}
			} 


			//the new topic added to repertoire --- Product Review and Product Rating


			//To read reviews/ratings about the products
			else if ( !answer.toLowerCase().contains("write") && (answer.toLowerCase().contains("see") || answer.toLowerCase().contains("read") || answer.toLowerCase().contains("know") || answer.toLowerCase().contains("like to")) && (answer.toLowerCase().contains("about")	|| answer.toLowerCase().contains("review") || answer.toLowerCase().contains("rate") || answer.toLowerCase().contains("rating"))	){
				System.out.println("Which product would you like to revise?");	
				String p_name = input.nextLine();
					try {
						Scanner scanner_review = new Scanner(new File("review.txt"));
						Scanner scanner_rate = new Scanner(new File("rating.txt"));
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

						System.out.println("We found " + count_rates + " rating(s) "  + count_reviews + " review(s) for (" + p_name + ")");
						System.out.println("Would you like to see any of the reviews and the ratings?");
						//give user the option to choose whether to display both reviews & ratings
						answer = input.nextLine();

						//if the user refuses, terminate the program
						if ( answer.toLowerCase().contains("never mind") || answer.toLowerCase().contains("nothing") || answer.toLowerCase().contains("no") || answer.toLowerCase().contains("nope") || answer.toLowerCase().contains("not at all")){
							System.out.println("Alright. Have a Good Day");
							System.exit(0);
						}
						//display the ratings of the product
						else if( !answer.toLowerCase().contains("review") && answer.toLowerCase().contains("only") && ( answer.toLowerCase().contains("rate") || answer.toLowerCase().contains("rating")) ){
							System.out.println("Here are the ratings for the product (" + p_name + ")" + "\n" + ratings);
							answer = input.nextLine();
						}
						//display the reviews of the product
						else if(!answer.toLowerCase().contains("rating") && answer.toLowerCase().contains("only") && answer.toLowerCase().contains("review") || answer.toLowerCase().contains("revision") || answer.toLowerCase().contains("revise")){
							System.out.println("Here are the reviews for the product (" + p_name +")" +"\n" + reviews);
							answer = input.nextLine();
						}
						//in other cases, display both reviews and ratings 
						else{
							System.out.println("Here are both reviews and ratings for the product ("+ p_name +")" +"\nReviews:\n" + reviews +"\nRatings:\n" + ratings);
							answer = input.nextLine();
						}

				 	} catch (Exception e) {

						System.out.println("Can't find the the data file.");
						e.printStackTrace();
						System.exit(1);
					}

			}

			//To write/save the review or rating for a product
			else if(answer.toLowerCase().contains("write") || answer.toLowerCase().contains("review") || ( answer.toLowerCase().contains("about") && answer.toLowerCase().contains("product") && !answer.toLowerCase().contains("read")) || answer.toLowerCase().contains("revision") || answer.toLowerCase().contains("rate")){
				//ask if user wants to write a review or to give a rate on a product
				System.out.println("Did you want to write a review about product? or to rate a product?");
				answer = input.nextLine();
				//if the user wants to write a review about a product, let them do it and save it into review.txt file
				if(answer.toLowerCase().contains("review") || answer.toLowerCase().contains("revision") || answer.toLowerCase().contains("revise") || answer.toLowerCase().contains("write")){
					
					boolean loop_product = true;
					while(loop_product){
						System.out.println("Please indicate the product that want to write a review about:");
						String review_product = input.nextLine();
						System.out.println(review_product + " is the product. Correct? (y/n)");
						answer = input.nextLine();
						if (answer.toLowerCase().equals("y")){
							System.out.println("Please write your review about the product (" + review_product + ") on the line below");
							String review = input.nextLine();
							try{
								FileWriter review_file = new FileWriter("review.txt", true);
								//save the prdocut reviews as product_name~~~product_reivew format
								review_file.write(review_product +"~~~"+ review + "\n");
								review_file.close();
								System.out.println("Your review is saved! Thank you!");
								
								//break the loop
								answer = input.nextLine();
								loop_product = false;
							}catch (IOException e) {
								System.out.println("An error occurred.");
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
					System.out.println("Please the indicate the product that you want to give rating");
					String rate_product = input.nextLine();
					//boolean for the while loop below
					boolean rate_loop = true;
					while(rate_loop){
						System.out.println("Please the indicate the rating number - from 1 to 5 - for this product: (" + rate_product + ")");
						//keep prompting the user until they enter a valid integer
						while (!input.hasNextInt()) {
							String wrong_input = input.next();
							System.out.printf("\"%s\" is not a valid number.\n", wrong_input);
						}
						// if the user puts an integer in as expected
						int rate_num = input.nextInt();
						if(rate_num < 5){
							
							try{
								FileWriter rate_file = new FileWriter("rating.txt", true);
								//save the ratings as product_name~~~product_rating format
								rate_file.write(rate_product +"~~~"+ rate_num + "\n");
								rate_file.close();
								System.out.println(rate_product + " - " + rate_num + " Your product rating is saved! Thank you!");
								answer = input.nextLine();
								rate_loop = false;
							}catch (IOException e) {
								System.out.println("An error occurred.");
								e.printStackTrace();
								System.exit(1);
							}
							
						}
						//if not, loop until they do
						else{
							System.out.println("Wrong number!!!");
							rate_loop = true;
						}
					}

					
				}

			}

			//the section for handling the random input from the user with at least 5 reasonable answers
			else {
				boolean general_question =answer.contains(s) ;
				boolean wh_question = ;
				if ()
				System.out.println("How may I help you?");
				//ask user if it's a question or complaint
				System.out.println("Is that a problem you want solved or are you writing a complaint?");
				answer = input.nextLine();
				//if they answer "yes" reinforce if it's a question or complaint
				if(answer.toLowerCase().contains("yes")) {
					System.out.println("But which one? a complaint or a question?");
					answer = input.nextLine();
				}
			}
		}
	
	input.close();
	}

	
}
