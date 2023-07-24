import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static final String RESET = "\033[0m";  // Text Reset
    // Regular Colors
    public static final String BLACK = "\033[0;30m";   // BLACK
    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String YELLOW = "\033[0;33m";  // YELLOW
    public static final String BLUE = "\033[0;34m";    // BLUE
    public static final String PURPLE = "\033[0;35m";  // PURPLE
    public static final String CYAN = "\033[0;36m";    // CYAN
    public static final String WHITE = "\033[0;37m";   // WHITE

    public static void main(String[] args) {


        ArrayList<String> eventsName = new ArrayList<>();
        ArrayList<String> playerNames = new ArrayList<>();
        ArrayList<String> teamNames = new ArrayList<>();
        ArrayList<Integer> teamPoints = new ArrayList<>();
        ArrayList<Integer> winnerIndex = new ArrayList<>();
        int []rank = {0,20,10,5 ,0 , 0};
        int []individualRank = {0,100,50,30 ,0 , 0};
        int winnerCount = 0;
        int winnerPoint = 0;
        boolean keepPlay = true;
        int userChooseMenu , teams , playerIndividual , events ,players  ;

        Scanner input = new Scanner(System.in);

        //display general for the system
        generalRules();
        do{
            do {
                //display menu and accept the one that user want to use
                menu();
                userChooseMenu = input.nextInt();
            }while (userChooseMenu <= 0 || userChooseMenu >= 5);

            //depending on user , do the process (1,2,3,4,5)
            switch (userChooseMenu){
                case 1: //for normal teams(5 events)
                        events = 5;
                        //accept the number of teams
                        do {
                            System.out.println( "Enter the no of teams Entering 5 events" );
                            teams = input.nextInt();
                        }while (teams<2 || teams>4);

                        //accept the event's name
                        for (int i = 1 ; i<= events ; i++ ){
                            System.out.println("Enter Event Name " + i + " for the teams ");
                            String name = input.next();
                            eventsName.add(name);
                        }
                        //accept team's name and team's members name
                        for (int i = 1 ; i<= teams ; i++ ){
                            System.out.println("Enter the name of team " + i);
                            String teamN = input.next();
                            teamNames.add(teamN);
                            for (int j = 1 ; j<=events; j++ ){
                                System.out.println("Enter Participant name " + j + " for team " + teamNames.get(i-1) +" for the event : " + eventsName.get(j-1));
                                String name = input.next();
                                playerNames.add(name);
                            }
                        }

                        //accept team rank on each event and calculate the total score for each team
                        for (int i= 0; i<teams ; i++){
                            int total = 0 ;
                            for (int j = 0 ; j<events; j++ ){
                                String tn = teamNames.get(i);
                                String race = eventsName.get(j);
                                int userRank ;
                                do {
                                    System.out.println("Enter rank of team "+ tn+ " on the event : "+ race);
                                    userRank = input.nextInt();
                                }while (userRank <1 || userRank > 5);
                                int points = rank[userRank];
                                System.out.println( points +" points scored for this event");
                                total += points;
                            }
                            teamPoints.add(total);
                        }

                        //Display event details
                        System.out.println("This team will not be scored any points");
                        System.out.println("--Normal Team Information--");
                        System.out.println("Number of Team Registered : "+ teams);
                        System.out.println("Number of Events Participated : "+ events);
                        System.out.print("Events List for Teams : ");
                        for (int i= 0 ; i< eventsName.size() ; i++){
                            if (i== eventsName.size() -1){
                                System.out.print(eventsName.get(i));
                            }else {
                                System.out.print(eventsName.get(i) + "  , ");
                            }
                        }

                        System.out.println();
                        System.out.println("Score Points Won:");
                        System.out.println("------------------");
                        System.out.printf("\n \n");
                        System.out.print("All Teams Scores : " );
                        for (int i= 0 ; i< teamPoints.size() ; i++){
                            if (i== teamPoints.size() - 1){
                                System.out.print(teamPoints.get(i));
                            }else {
                                System.out.print(teamPoints.get(i) + " , ");
                            }
                        }
                        System.out.println();

                        //display the result
                        System.out.println("---------------------------------------------------------");
                        System.out.println("\t Team \t \t Participants \t \t Score");
                        System.out.println("---------------------------------------------------------");

                        for (int i=0 ; i< teams ; i++){
                            for (int j=0 ; j<events ; j++){
                                int playerIndex = 5 * i;
                                int teamScoreIndex = 0 + i;
                                System.out.println("\t Team : "+teamNames.get(i) + "\t Participants : " + playerNames.get(playerIndex +j) +"\t\t Team Score " + teamPoints.get(teamScoreIndex));
                            }
                            System.out.println("---------------------------------------------------------");
                        }

                        //calculate the winner
                        for (int i=0 ; i< teamPoints.size() ; i++){
                            if (winnerPoint < teamPoints.get(i)){
                                winnerPoint = teamPoints.get(i);
                            }
                        }
                        for (int i=0 ; i<teamPoints.size() ; i++){
                            if (winnerPoint == teamPoints.get(i)){
                                winnerCount += 1;
                                winnerIndex.add(i);
                            }
                        }
                        //display the winner
                        if (winnerIndex.size() == 1){
                            System.out.println("Team "+ teamNames.get(winnerIndex.get(0)) + " has won with score " + winnerPoint);
                        } else if (winnerIndex.size() > 1) {
                            for (int i=0 ; i<winnerIndex.size() ; i++){
                                if (i == winnerIndex.size()-1){
                                    System.out.print(  " and " + teamNames.get(winnerIndex.get(i)));
                                }else if (i == winnerIndex.size() -2) {
                                    System.out.print(teamNames.get(winnerIndex.get(i)));
                                } else {
                                    System.out.print(teamNames.get(winnerIndex.get(i)) + " , ");
                                }
                            }
                            System.out.print(" has won with score "+ winnerPoint);
                            System.out.println();
                        } else {
                            System.out.println("Something was wrong ---------------");
                        }
                        System.out.println("--------------------------------------------------------");
                        System.out.println("********************************************************");

                    break;
                case 2: //for normal individual(5 events)
                        events = 5;
                        //player number request
                        do {
                            System.out.println("Enter the no of individual players Entering 5 events (max:20)");
                            players = input.nextInt();
                        }while (players<2 || players>20);

                        //Events' name accept
                        for (int i = 1 ; i<= events ; i++ ){
                            System.out.println("Enter the event of name " + i + " that the individuals are entering ");
                            String name = input.next();
                            eventsName.add(name);
                        }

                        //accept player's name and ranks on each events
                        for (int i = 1 ; i<= players ; i++ ){
                            int total = 0;
                            System.out.println("Enter the name of participant " + i);
                            String playerN = input.next();
                            playerNames.add(playerN);
                            for (int j=1 ; j<= events ; j++){
                                int userRank ;
                                do {
                                    System.out.println("Enter rank of the individual : " + playerN + " on the event " + j + " : " + eventsName.get(j-1));
                                    userRank = input.nextInt();
                                }while (userRank <1 || userRank > 5);
                                int points = rank[userRank];
                                System.out.println( points +" points scored for this event");
                                total += points;
                            }
                            teamPoints.add(total);
                        }

                        //display the event details
                        System.out.println("This team will not be scored any points");
                        System.out.println("--Normal Team Information--");
                        System.out.println("Number of Participants : "+ players);
                        System.out.println("Number of Events : "+ events);
                        System.out.print("Events List for individuals : ");
                        for (int i= 0 ; i< eventsName.size() ; i++){
                            if (i== eventsName.size() -1){
                                System.out.print(eventsName.get(i));
                            }else {
                                System.out.print(eventsName.get(i) + "  , ");
                            }
                        }

                        //display the score
                        System.out.println();
                        System.out.println("Score Points Won:");
                        System.out.println("------------------");
                        System.out.printf("\n \n");
                        System.out.print("All individual Scores : " );
                        for (int i= 0 ; i< teamPoints.size() ; i++){
                            if (i== teamPoints.size() - 1){
                                System.out.print(teamPoints.get(i));
                            }else {
                                System.out.print(teamPoints.get(i) + " , ");
                            }
                        }
                        System.out.println();

                        //display the result
                        System.out.println("---------------------------------------------------------");
                        System.out.println(" \t Participant's name \t \t Score");
                        System.out.println("---------------------------------------------------------");

                        for (int i=0 ; i< players ; i++){
                            System.out.println("Player's Name : " + playerNames.get(i) + "\t\t Score : " + teamPoints.get(i));
                            if (i == players -1){
                                System.out.println("---------------------------------------------------------");
                            }
                        }

                        //calculate the winner
                        for (Integer teamPoint : teamPoints) {
                            if (winnerPoint < teamPoint) {
                                winnerPoint = teamPoint;
                            }
                        }
                        for (int i=0 ; i<teamPoints.size() ; i++){
                            if (winnerPoint == teamPoints.get(i)){
                                winnerCount += 1;
                                winnerIndex.add(i);
                            }
                        }
                        //display the winner
                        if (winnerIndex.size() == 1){
                            System.out.println("Congratulations! Individual participant, "+ playerNames.get(winnerIndex.get(0)) + " has won with score " + winnerPoint);
                        } else if (winnerIndex.size() > 1) {
                            System.out.print("Congratulations ! individual participants ");
                            for (int i=0 ; i<winnerIndex.size() ; i++){
                                if (i == winnerIndex.size()-1){
                                    System.out.print(  " and " + playerNames.get(winnerIndex.get(i)));
                                }else if (i == winnerIndex.size()-2) {
                                    System.out.print(playerNames.get(winnerIndex.get(i)));
                                } else {
                                    System.out.print(playerNames.get(winnerIndex.get(i)) + " , ");
                                }
                            }
                            System.out.print(" has won with score "+ winnerPoint);
                            System.out.println();
                        } else {
                            System.out.println("Something was wrong ---------------");
                        }

                        System.out.println("--------------------------------------------------------");
                        System.out.println("********************************************************");
                    break;
                case 3: //for special team (1 events)
                        events = 1;
                        players = 5;
                        //accept the number of teams
                        do {
                            System.out.println("Enter the no of teams Entering Special event");
                            teams = input.nextInt();
                        }while (teams<2 || teams>4);

                        //accept the event's name
                        System.out.println("Enter Event Name " + 1 + " for the teams ");
                        String evName = input.next();
                        eventsName.add(evName);

                        //accept the team's name and team players' name
                        for (int i = 1 ; i<= teams ; i++ ){
                            System.out.println("Enter the name of team " + i);
                            String teamN = input.next();
                            teamNames.add(teamN);
                            for (int j = 1 ; j<=players; j++ ){
                                System.out.println("Enter Participant name " + j + " for team : " + teamN);
                                String name = input.next();
                                playerNames.add(name);
                            }
                        }

                        //accept the rank of teams and calculate the score
                        for (int i= 0; i<teams ; i++){
                            String tn = teamNames.get(i);
                            String race = eventsName.get(0);
                            int userRank ;
                            do {
                                System.out.println("Enter rank of team "+ tn+ " on the event : "+ race);
                                userRank = input.nextInt();
                            }while (userRank <1 || userRank > 5);
                            int points = individualRank[userRank];
                            System.out.println( points +" points scored for this event");
                            teamPoints.add(points);
                        }

                        //display the event details
                        System.out.println("This team will not be scored any points");
                        System.out.println("--Normal Team Information--");
                        System.out.println("Number of Team Registered : "+ teams);
                        System.out.println("Number of Events Participated : "+ events);
                        System.out.print("Events List for Teams : " +  eventsName.get(0));

                        System.out.println();
                        System.out.println("Score Points Won:");
                        System.out.println("------------------");
                        System.out.printf("\n \n");
                        System.out.print("All Teams Scores : " );
                        for (Integer tp : teamPoints){
                            System.out.print(tp + " , ");
                        }
                        System.out.println();

                        //display the result
                        System.out.println("---------------------------------------------------------");
                        System.out.println("\t Special Team \t \t Participants \t \t Score");
                        System.out.println("---------------------------------------------------------");

                        for (int i=0 ; i< teams ; i++){
                            for (int j=0 ; j<players ; j++){
                                int playerIndex = 5 * i;
                                int teamScoreIndex = 0 + i;
                                System.out.println("\t Team : "+teamNames.get(i) + "\t Participants : " + playerNames.get(playerIndex +j) +"\t\t Team Score " + teamPoints.get(teamScoreIndex));
                            }
                            System.out.println("---------------------------------------------------------");
                        }

                        //calculate the winner
                        for (int i=0 ; i< teamPoints.size() ; i++){
                            if (winnerPoint < teamPoints.get(i)){
                                winnerPoint = teamPoints.get(i);
                            }
                        }
                        for (int i=0 ; i<teamPoints.size() ; i++){
                            if (winnerPoint == teamPoints.get(i)){
                                winnerCount += 1;
                                winnerIndex.add(i);
                            }
                        }
                        //display the winner
                        if (winnerIndex.size() == 1){
                            System.out.println("Team "+ teamNames.get(winnerIndex.get(0)) + " has won with score " + winnerPoint);
                        } else if (winnerIndex.size() > 1) {
                            for (int i=0 ; i<winnerIndex.size() ; i++){
                                if (i == winnerIndex.size()-1){
                                    System.out.print(  " and " + teamNames.get(winnerIndex.get(i)));
                                } else if (i == winnerIndex.size() -2) {
                                    System.out.print(teamNames.get(winnerIndex.get(i)));
                                }else {
                                    System.out.print(teamNames.get(winnerIndex.get(i)) + " , ");
                                }
                            }
                            System.out.print(" has won with score "+ winnerPoint);
                            System.out.println();
                        } else {
                            System.out.println("Something was wrong ---------------");
                        }
                        System.out.println("--------------------------------------------------------");
                        System.out.println("********************************************************");
                    break;
                case 4:

                        events = 1;
                        //player number request
                        do {
                            System.out.println("Enter the no of individual players Entering 5 events (max:20)");
                            players = input.nextInt();
                        }while (players<2 || players>20);

                        //Events' name accept
                        System.out.println("Enter the event of name " + 1 + " that the individuals are entering ");
                        String name = input.next();
                        eventsName.add(name);

                        //accept the name of player and ranks
                        for (int i = 1 ; i<= players ; i++ ){
                            System.out.println("Enter the name of participant " + i);
                            String playerN = input.next();
                            playerNames.add(playerN);
                            int userRank ;
                            do {
                                System.out.println("Enter rank of the individual : " + playerN + " on the event : " + eventsName.get(0));
                                userRank = input.nextInt();
                            }while (userRank <1 || userRank > 5);
                            int points = individualRank[userRank];
                            System.out.println( points +" points scored for this event");
                            teamPoints.add(points);
                        }

                        //display the event details
                        System.out.println("This team will not be scored any points");
                        System.out.println("--Normal Team Information--");
                        System.out.println("Number of Participants : "+ players);
                        System.out.println("Number of Events : "+ events);
                        System.out.print("Events List for individuals : " + eventsName.get(0));

                        System.out.println();
                        System.out.println("Score Points Won:");
                        System.out.println("------------------");
                        System.out.printf("\n \n");
                        System.out.print("All individual Scores : " );
                        for (int i= 0 ; i< teamPoints.size() ; i++){
                            if (i== teamPoints.size()-1){
                                System.out.print(teamPoints.get(i));
                            }else {
                                System.out.print(teamPoints.get(i) + " , ");
                            }
                        }
                        System.out.println();

                        //display the result
                        System.out.println("---------------------------------------------------------");
                        System.out.println(" \t Participant's name \t \t Score");
                        System.out.println("---------------------------------------------------------");

                        for (int i=0 ; i< players ; i++){
                            System.out.println("Player's Name : " + playerNames.get(i) + "\t\t Score : " + teamPoints.get(i));
                            if (i == players -1){
                                System.out.println("---------------------------------------------------------");
                            }
                        }

                        //calculate and determine who is the winner
                        for (Integer teamPoint : teamPoints) {
                            if (winnerPoint < teamPoint) {
                                winnerPoint = teamPoint;
                            }
                        }
                        for (int i=0 ; i<teamPoints.size() ; i++){
                            if (winnerPoint == teamPoints.get(i)){
                                winnerCount += 1;
                                winnerIndex.add(i);
                            }
                        }
                        if (winnerIndex.size() == 1){
                            System.out.println("Congratulations! Individual participant, "+ playerNames.get(winnerIndex.get(0)) + " has won with score " + winnerPoint);
                        } else if (winnerIndex.size() > 1) {
                            System.out.print("Congratulations ! individual participants ");
                            for (int i=0 ; i<winnerIndex.size() ; i++){
                                if (i == winnerIndex.size()-1){
                                    System.out.print(  " and " + playerNames.get(winnerIndex.get(i)));
                                }else if (i == winnerIndex.size()-2) {
                                    System.out.print(playerNames.get(winnerIndex.get(i)));
                                } else {
                                    System.out.print(playerNames.get(winnerIndex.get(i)) + " , ");
                                }
                            }
                            System.out.print(" has won with score "+ winnerPoint);
                            System.out.println();
                        } else {
                            System.out.println("Something was wrong ---------------");
                        }

                        System.out.println("--------------------------------------------------------");
                        System.out.println("********************************************************");

                    break;
            }
            //Ask the user , he wants to play again or not;
            //if user want to play , clear all the array list.
            //do this process again
            System.out.println("Do you want to play again? (y/n)");
            String yesOrNo = input.next();
            yesOrNo.toLowerCase();
            if (yesOrNo.equals("n")){
                keepPlay = false;
                System.out.println("Thanks for using our Tournament Scoring System");
            }else {
                keepPlay = true;
                eventsName.clear();
                teamNames.clear();
                playerNames.clear();
                winnerIndex.clear();
                teamPoints.clear();
            }
        }while (keepPlay);


    }
    public static void generalRules(){
        System.out.println("General Rule");
        System.out.println("************");
        System.out.println("=> 5 Events are set for Normal Teams and Individuals ");
        System.out.println("=> There can only be 5 Participants in both Normal and Special team  ");
        System.out.println("=> 5 Events are set for Normal Teams and Individuals ");
        System.out.println("=> Normal Teams and Individual Participants will participate in 5 Events ");
    }
    public static void menu(){
        System.out.println("******** MENU ************");
        System.out.println("Please Enter the following:");
        System.out.println("1 for Normal Teams //5 events ");
        System.out.println("2 for Normal Individual //5 events ");
        System.out.println("3 for Special Teams // 1event ");
        System.out.println("4 for Special Individual // 1 event ");
    }
}