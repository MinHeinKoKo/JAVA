import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> eventsName = new ArrayList<>();
        ArrayList<String> playerNames = new ArrayList<>();
        ArrayList<String> teamNames = new ArrayList<>();
        ArrayList<Integer> teamPoints = new ArrayList<>();
        int []rank = {0,20,10,5 ,0 , 0};
        int winnerPoint = 0;
        boolean keepPlay = true;
        int userChooseMenu , teams , playerIndividual , events  ;

        Scanner input = new Scanner(System.in);

        generalRules();
         do {
             menu();
             userChooseMenu = input.nextInt();
         }while (userChooseMenu <= 0 || userChooseMenu >= 5);

         switch (userChooseMenu){
             case 1:
                    do {
                        events = 5;
                        do {
                            System.out.println("Enter the no of teams Entering 5 events");
                            teams = input.nextInt();
                        }while (teams<2 || teams>4);
                        for (int i = 1 ; i<= events ; i++ ){
                            System.out.println("Enter Event Name " + i + " for the teams ");
                            String name = input.next();
                            eventsName.add(name);
                        }
                        for (int i = 1 ; i<= teams ; i++ ){
                            for (int j = 1 ; j<=events; j++ ){
                                System.out.println("Enter Participant name " + j + " for team " + i);
                                String name = input.next();
                                playerNames.add(name);
                            }
                            System.out.println("Enter the name of team " + i);
                            String teamN = input.next();
                            teamNames.add(teamN);
                        }

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

                        System.out.println("This team will not be scored any points");
                        System.out.println("--Normal Team Information--");
                        System.out.println("Number of Team Registered : "+ teams);
                        System.out.println("Number of Events Participated : "+ events);
                        System.out.print("Events List for Teams : ");
                        for (int i= 0 ; i< eventsName.size() ; i++){
                            System.out.print(eventsName.get(i) + "\t,");
                        }
                        System.out.println();
                        System.out.println("Score Points Won:");
                        System.out.println("------------------");
                        System.out.printf("\n \n");
                        System.out.print("All Teams Scores : " );
                        for (int i= 0 ; i< teamPoints.size() ; i++){
                            System.out.print(teamPoints.get(i) + "\t,");
                        }
                        System.out.println();

                        System.out.println("---------------------------------------------------------");
                        System.out.println("\t Team \t \t Participants \t \t Score");
                        System.out.println("---------------------------------------------------------");

                        for (int i=0 ; i< teams ; i++){
                            for (int j=0 ; j<events ; j++){
                                int playerIndex = 5 * i;
                                int teamScoreIndex = 0 + i;
                                System.out.println("\t Team : "+teamNames.get(i) + "\t Participants : " + playerNames.get(playerIndex +j) +"\t Team Score " + teamPoints.get(teamScoreIndex));
                            }
                            System.out.println("---------------------------------------------------------");
                        }

                        for (int i=0 ; i< teamPoints.size() ; i++){
                            if (winnerPoint < teamPoints.get(i)){
                                winnerPoint = teamPoints.get(i);
                            }
                        }
                        for (int i=0 ; i<teamPoints.size() ; i++){
                            if (winnerPoint == teamPoints.get(i)){
                                System.out.println("Team "+ teamNames.get(i) + " has won with score " + winnerPoint);
                            }
                        }
                        System.out.println("--------------------------------------------------------");
                        System.out.println("********************************************************");

                        System.out.println("Do you want to play again? (y/n)");
                        String yesOrNo = input.next();
                        yesOrNo.toLowerCase();
                        if (yesOrNo.equals("n")){
                            keepPlay = false;
                        }
                    }while (keepPlay);
                 break;
             case 2:
                 break;
             case 3:
                 break;
             case 4:
                 break;
         }

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