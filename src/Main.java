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

        //colors
//        ArrayList<String> colorLists = new ArrayList<>();
//        colorLists.add("\033[0;31m");
//        colorLists.add("\033[0;32m");
//        colorLists.add("\033[0;33m");
//        colorLists.add("\033[0;34m");
//        colorLists.add("\033[0;35m");
//        colorLists.add("\033[0;36m");
//        colorLists.add("\033[0;37m");


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
        System.out.print(GREEN);
        generalRules();
        System.out.println(RESET);
        do{
            do {
                //display menu and accept the one that user want to use
                menu();
                //request the menu
                userChooseMenu = input.nextInt();

                //user entered number must be between 0 and 6
                //if user entered incorrect number , we will display the warning with red color.
                //and request again....
                if (userChooseMenu<=0 || userChooseMenu >=6){
                    System.out.println(RED+"Please select one from our lists 😒😒"+ RESET);
                }
            }while (userChooseMenu <= 0 || userChooseMenu >= 6);

            //depending on user , do the process (1,2,3,4,5)
            switch (userChooseMenu){
                case 1: //for normal teams(5 events)
                    events = 5;
                    //accept the number of teams
                    do {
                        System.out.println( "Enter the no of teams Entering 5 events" );
                        teams = input.nextInt();
                        if (teams<2 || teams >4){
                            System.out.println(RED+"Please enter between 0 to 5 😒😒"+ RESET);
                        }
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
                            System.out.println("Enter Participant name " + j + " for team " + BLUE + teamNames.get(i-1)+RESET +" for the event : " + GREEN + eventsName.get(j-1) + RESET);
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
                                System.out.println("Enter rank of team "+ BLUE + tn+ RESET + " on the event : "+ GREEN + race + RESET);
                                userRank = input.nextInt();
                                if (userRank<1 || userRank >5){
                                    System.out.println(RED+"Please enter between 0 to 6 😒😒"+ RESET);
                                }
                            }while (userRank <1 || userRank > 5);
                            int points = rank[userRank];
                            if (points == 20){
                                System.out.println(BLUE+ points +" points scored for this event 😊"+RESET);
                            }else {
                                System.out.println(CYAN + points +" points scored for this event" + RESET);
                            }
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

                    System.out.print(BLUE);
                    for (int i= 0 ; i< eventsName.size() ; i++){
                        if (i== eventsName.size() -1){
                            System.out.print(eventsName.get(i));
                        } else if (i== eventsName.size()-2) {
                            System.out.println(eventsName.get(i));
                        } else {
                            System.out.print(eventsName.get(i) + "  , ");
                        }
                    }
                    System.out.print(RESET);

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

                    //display the result
                    System.out.println("---------------------------------------------------------");
                    System.out.println("\t Team \t \t Participants \t\t \t Score");
                    System.out.println("---------------------------------------------------------");

                    for (int i=0 ; i< teams ; i++){
                        for (int j=0 ; j<events ; j++){
                            int playerIndex = 5 * i;
                            int teamScoreIndex = 0 + i;
                            System.out.println(YELLOW + "\t Team : "+teamNames.get(i) + "\t Participants : " + playerNames.get(playerIndex +j) +"\t\t Team Score " + teamPoints.get(teamScoreIndex)+ RESET);
                        }
                        System.out.println("---------------------------------------------------------");
                    }

                    //display the winner
                    if (winnerIndex.size() == 1){
                        System.out.println(YELLOW + "Team "+ teamNames.get(winnerIndex.get(0)) + " has won with score " + winnerPoint + RESET + ".👍👏👏");
                    } else if (winnerIndex.size() > 1) {
                        for (int i=0 ; i<winnerIndex.size() ; i++){
                            if (i == winnerIndex.size()-1){
                                System.out.print(PURPLE +  " and " + teamNames.get(winnerIndex.get(i)) + RESET);
                            }else if (i == winnerIndex.size() -2) {
                                System.out.print(GREEN + teamNames.get(winnerIndex.get(i)) + RESET);
                            } else {
                                System.out.print(CYAN + teamNames.get(winnerIndex.get(i)) + " , " + RESET);
                            }
                        }
                        System.out.print(" has won with score "+ GREEN + winnerPoint +RESET + ".👍👏👏");
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
                        if (players<2 || players >20){
                            System.out.println(RED+"Please enter between 2 to 20 😒😒"+ RESET);
                        }
                    }while (players<2 || players>20);

                    //Events' name accept
                    for (int i = 1 ; i<= events ; i++ ){
                        System.out.println("Enter the event of name " + i + " that the individuals are entering ");
                        String name = input.next();
                        eventsName.add(name);
                    }

                    //accept player's name and ranks on each event
                    for (int i = 1 ; i<= players ; i++ ){
                        int total = 0;
                        System.out.println("Enter the name of participant " + i);
                        String playerN = input.next();
                        playerNames.add(playerN);
                        for (int j=1 ; j<= events ; j++){
                            int userRank ;
                            do {
                                System.out.println("Enter rank of the individual : " + BLUE +playerN+RESET + " on the event " + j + " : " + GREEN +eventsName.get(j-1) +RESET);
                                userRank = input.nextInt();
                                if (userRank<1 || userRank >5){
                                    System.out.println(RED+"Please enter between 0 to 6 😒😒"+ RESET);
                                }
                            }while (userRank <1 || userRank > 5);
                            int points = rank[userRank];
                            total += points;
                            if (points == 20){
                                System.out.println(BLUE+ points +" points scored for this event 😊"+RESET);
                            }else {
                                System.out.println(CYAN + points +" points scored for this event" + RESET);
                            }
                        }
                        teamPoints.add(total);
                    }

                    //display the event details
                    System.out.println("This team will not be scored any points");
                    System.out.println("--Normal Team Information--");
                    System.out.println("Number of Participants : "+ players);
                    System.out.println("Number of Events : "+ events);
                    System.out.print("Events List for individuals : ");
                    System.out.print(BLUE);
                    for (int i= 0 ; i< eventsName.size() ; i++){
                        if (i== eventsName.size() -1){
                            System.out.print(eventsName.get(i));
                        }else {
                            System.out.print(eventsName.get(i) + "  , ");
                        }
                    }
                    System.out.print(RESET);

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
                        System.out.println("Player's Name : " + GREEN +playerNames.get(i) +RESET + "\t\t Score : " + YELLOW+teamPoints.get(i)+RESET);
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
                        System.out.println(YELLOW + "Congratulations! Individual participant, "+ playerNames.get(winnerIndex.get(0)) + " has won with score " + winnerPoint + RESET + ".👍👏👏");
                    } else if (winnerIndex.size() > 1) {
                        System.out.print("Congratulations ! individual participants ");
                        for (int i=0 ; i<winnerIndex.size() ; i++){
                            if (i == winnerIndex.size()-1){
                                System.out.print( PURPLE + " and " + playerNames.get(winnerIndex.get(i))+RESET);
                            }else if (i == winnerIndex.size()-2) {
                                System.out.print(GREEN+playerNames.get(winnerIndex.get(i))+RESET);
                            } else {
                                System.out.print(CYAN+playerNames.get(winnerIndex.get(i))+RESET + " , ");
                            }
                        }
                        System.out.print(" has won with score "+ GREEN + winnerPoint +RESET + ".👍👏👏");
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
                        if (teams<2 || teams >4){
                            System.out.println(RED+"Please enter between 0 to 5 😒😒"+ RESET);
                        }
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
                            System.out.println("Enter Participant name " + j + " for team : " + GREEN + teamN + RESET);
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
                            System.out.println("Enter rank of team "+ BLUE+tn+RESET+ " on the event : "+ GREEN +race+RESET);
                            userRank = input.nextInt();
                            if (userRank<1 || userRank >5){
                                System.out.println(RED+"Please enter between 0 to 6 😒😒"+ RESET);
                            }
                        }while (userRank <1 || userRank > 5);
                        int points = individualRank[userRank];
                        if (points == 100){
                            System.out.println(BLUE+ points +" points scored for this event 😊"+RESET);
                        }else {
                            System.out.println(CYAN + points +" points scored for this event" + RESET);
                        }
                        System.out.println( points +" points scored for this event");
                        teamPoints.add(points);
                    }

                    //display the event details
                    System.out.println("This team will not be scored any points");
                    System.out.println("--Normal Team Information--");
                    System.out.println("Number of Team Registered : "+ teams);
                    System.out.println("Number of Events Participated : "+ events);
                    System.out.print("Events List for Teams : " +  BLUE + eventsName.get(0)+RESET);

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
                            System.out.println( YELLOW + "\t Team : "+teamNames.get(i) + "\t Participants : " + playerNames.get(playerIndex +j) +"\t\t Team Score " + teamPoints.get(teamScoreIndex)+ RESET);
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
                        System.out.println("Team "+ YELLOW+teamNames.get(winnerIndex.get(0))+RESET + " has won with score " + YELLOW+winnerPoint+RESET);
                    } else if (winnerIndex.size() > 1) {
                        for (int i=0 ; i<winnerIndex.size() ; i++){
                            System.out.print(BLUE);
                            if (i == winnerIndex.size()-1){
                                System.out.print(  " and " + teamNames.get(winnerIndex.get(i)));
                            } else if (i == winnerIndex.size() -2) {
                                System.out.print(teamNames.get(winnerIndex.get(i)));
                            }else {
                                System.out.print(teamNames.get(winnerIndex.get(i)) + " , ");
                            }
                            System.out.print(RESET);
                        }
                        System.out.print(" has won with score "+ YELLOW + winnerPoint+RESET);
                        System.out.println();
                    } else {
                        System.out.println(RED+"Something was wrong ---------------"+RESET);
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
                        if (players<2 || players >20){
                            System.out.println(RED+"Please enter between 2 to 20 😒😒"+ RESET);
                        }
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
                            System.out.println("Enter rank of the individual : " + BLUE +playerN+RESET + " on the event : " + GREEN+eventsName.get(0)+RESET);
                            userRank = input.nextInt();
                            if (userRank<1 || userRank >5){
                                System.out.println(RED+"Please enter between 0 to 6 😒😒"+ RESET);
                            }
                        }while (userRank <1 || userRank > 5);
                        int points = individualRank[userRank];
                        if (points == 100){
                            System.out.println(BLUE+ points +" points scored for this event 😊"+RESET);
                        }else {
                            System.out.println(CYAN + points +" points scored for this event" + RESET);
                        }
                        teamPoints.add(points);
                    }

                    //display the event details
                    System.out.println("This team will not be scored any points");
                    System.out.println("--Normal Team Information--");
                    System.out.println("Number of Participants : "+ players);
                    System.out.println("Number of Events : "+ events);
                    System.out.print("Events List for individuals : " + BLUE +eventsName.get(0)+RESET);

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
                        System.out.println("Player's Name : " + BLUE+playerNames.get(i)+RESET + "\t\t Score : " + GREEN+teamPoints.get(i)+RESET);
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
                        System.out.println("Congratulations! Individual participant, "+ GREEN+playerNames.get(winnerIndex.get(0))+RESET + " has won with score " + YELLOW+winnerPoint+RESET);
                    } else if (winnerIndex.size() > 1) {
                        System.out.print("Congratulations ! individual participants ");
                        for (int i=0 ; i<winnerIndex.size() ; i++){
                            System.out.print(YELLOW);
                            if (i == winnerIndex.size()-1){
                                System.out.print(  " and " + playerNames.get(winnerIndex.get(i)));
                            }else if (i == winnerIndex.size()-2) {
                                System.out.print(playerNames.get(winnerIndex.get(i)));
                            } else {
                                System.out.print(playerNames.get(winnerIndex.get(i)) + " , ");
                            }
                            System.out.print(RESET);
                        }
                        System.out.print(" has won with score "+ YELLOW + winnerPoint+ RESET);
                        System.out.println();
                    } else {
                        System.out.println("Something was wrong ---------------");
                    }

                    System.out.println("--------------------------------------------------------");
                    System.out.println("********************************************************");

                    break;
                case 5:
                    int userSelectNo=0;
                    //accept what user want to play
                    do {
                        Custom();
                        userSelectNo= input.nextInt();
                        if (userSelectNo<=0 || userSelectNo>=3){
                            System.out.println(RED+"Please enter between 0 to 3 😒😒"+ RESET);
                        }
                    }while (userSelectNo<=0 || userSelectNo>=3);
                    //do the process using switch case;
                    switch (userSelectNo){
                        case 1:// team custom events
                            //accept event number
                            do {
                                System.out.println("Enter the no: of events you want to play min:1");
                                events = input.nextInt();
                                if (events <1){
                                    System.out.println(RED+"Please enter 1 or more 😒😒"+ RESET);
                                }
                            }while (events<1);

                            //accept event name
                            for (int i=1; i<=events ; i++){
                                System.out.println("Enter the event name " + i);
                                String eName = input.next();
                                eventsName.add(eName);
                            }
                            //accept team number
                            do {
                                System.out.println("Enter the no: of teams");
                                teams = input.nextInt();
                                if(teams<2){
                                    System.out.println(RED+"You can't play . Please enter 2 or above  😒😒"+ RESET);
                                }
                            }while (teams<2);

                            //accept team name;
                            for (int i=1; i<=teams ;i++){
                                System.out.println("Enter the name for team "+ i);
                                String teamN = input.next();
                                teamNames.add(teamN);
                            }
                            //accept the member of a team
                            do {
                                System.out.println("Enter the no: of players in a team"+RED+" (min:5) "+RESET);
                                players = input.nextInt();
                                if(players<2){
                                    System.out.println(RED+"You can't play . Please enter 5 or above  😒😒"+ RESET);
                                }
                            }while (players<5);

                            //accept the member's name
                            for (int i=0; i<teams ;i++){
                                for (int j=0;j<players ; j++){
                                    System.out.println("Enter the player's name " + (j+1)+" of team "+ BLUE+ teamNames.get(i)+RESET);
                                    String pName = input.next();
                                    playerNames.add(pName);
                                }
                            }
                            //accept team rank
                            for (int i=0 ; i<teams ; i++){
                                int total = 0;
                                for (int j=0 ; j<events ;j++){
                                    int userRank;
                                    do {
                                        System.out.println("Enter the rank of team " + BLUE +teamNames.get(i)+RESET + " in the event : "  +BLUE + eventsName.get(j) + RESET);
                                        userRank = input.nextInt();
                                        if (userRank<1 || userRank >5){
                                            System.out.println(RED+"Please enter between 0 to 6 😒😒"+ RESET);
                                        }
                                    }while (userRank < 1 || userRank >5);
                                    int points = rank[userRank];
                                    total += points;
                                    if (points == 20){
                                        System.out.println(BLUE+ points +" points scored for this event 😊"+RESET);
                                    }else {
                                        System.out.println(CYAN + points +" points scored for this event" + RESET);
                                    }
                                }
                                teamPoints.add(total);
                            }
                            //Display event details
                            System.out.println("This team will not be scored any points");
                            System.out.println("--Normal Team Information--");
                            System.out.println("Number of Team Registered : "+ teams);
                            System.out.println("Number of Events Participated : "+ events);
                            System.out.print("Events List for Teams : ");

                            System.out.print(BLUE);
                            for (int i= 0 ; i< eventsName.size() ; i++){
                                if (i== eventsName.size() -1){
                                    System.out.print(eventsName.get(i));
                                } else if (i== eventsName.size()-2) {
                                    System.out.println(eventsName.get(i));
                                } else {
                                    System.out.print(eventsName.get(i) + "  , ");
                                }
                            }
                            System.out.print(RESET);

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
                            //display the result
                            System.out.println("---------------------------------------------------------");
                            System.out.println("\t Team \t \t Participants \t\t \t Score");
                            System.out.println("---------------------------------------------------------");

                            for (int i=0 ; i< teams ; i++){
                                for (int j=0 ; j<events ; j++){
                                    int playerIndex = 5 * i;
                                    int teamScoreIndex = 0 + i;
                                    System.out.println(PURPLE + "\t Team : "+teamNames.get(i) + "\t Participants : " + playerNames.get(playerIndex +j) +"\t\t Team Score " + teamPoints.get(teamScoreIndex)+ RESET);
                                }
                                System.out.println("---------------------------------------------------------");
                            }
                            //display the winner
                            if (winnerIndex.size() == 1){
                                System.out.println(YELLOW + "Team "+ teamNames.get(winnerIndex.get(0)) + " has won with score " + winnerPoint + RESET + ".👍👏👏");
                            } else if (winnerIndex.size() > 1) {
                                for (int i=0 ; i<winnerIndex.size() ; i++){
                                    if (i == winnerIndex.size()-1){
                                        System.out.print(PURPLE +  " and " + teamNames.get(winnerIndex.get(i)) + RESET);
                                    }else if (i == winnerIndex.size() -2) {
                                        System.out.print(GREEN + teamNames.get(winnerIndex.get(i)) + RESET);
                                    } else {
                                        System.out.print(CYAN + teamNames.get(winnerIndex.get(i)) + " , " + RESET);
                                    }
                                }
                                System.out.print(" has won with score "+ GREEN + winnerPoint +RESET + ".👍👏👏");
                                System.out.println();
                            } else {
                                System.out.println("Something was wrong ---------------");
                            }
                            System.out.println("--------------------------------------------------------");
                            System.out.println("********************************************************");
                            break;
                        case 2:// individual custom events
                            //accept event number
                            do {
                                System.out.println("Enter the no: of events you want to play min:1");
                                events = input.nextInt();
                                if (events <1){
                                    System.out.println(RED+"Please enter 1 or more 😒😒"+ RESET);
                                }
                            }while (events<1);

                            //accept event name
                            for (int i=1; i<=events ; i++){
                                System.out.println("Enter the event name " + i);
                                String eName = input.next();
                                eventsName.add(eName);
                            }
                            //accept number of player
                            do {
                                System.out.println("Enter the no: of player");
                                players = input.nextInt();
                                if(players<2){
                                    System.out.println(RED+"You can't play . Please enter 2 or above  😒😒"+ RESET);
                                }
                            }while (players<2);

                            //accept player name;
                            for (int i=1; i<=players ;i++){
                                System.out.println("Enter the participant's name "+ i);
                                String playerN = input.next();
                                playerNames.add(playerN);
                            }
                            //accept team rank
                            for (int i=0 ; i<players ; i++){
                                int total = 0;
                                for (int j=0 ; j<events ;j++){
                                    int userRank;
                                    do {
                                        System.out.println("Enter the rank of player " + BLUE +playerNames.get(i)+RESET + " in the event : "  +BLUE + eventsName.get(j) + RESET);
                                        userRank = input.nextInt();
                                        if (userRank<1 || userRank >5){
                                            System.out.println(RED+"Please enter between 0 to 6 😒😒"+ RESET);
                                        }
                                    }while (userRank < 1 || userRank >5);
                                    int points = individualRank[userRank];
                                    total += points;
                                    if (points == 100){
                                        System.out.println(BLUE+ points +" points scored for this event 😊"+RESET);
                                    }else {
                                        System.out.println(CYAN + points +" points scored for this event" + RESET);
                                    }
                                }
                                teamPoints.add(total);
                            }
                            //Display event details
                            System.out.println("This team will not be scored any points");
                            System.out.println("--Normal Team Information--");
                            System.out.println("Number of participant Registered : "+ players);
                            System.out.println("Number of Events Participated : "+ events);
                            System.out.print("Events List for Teams : ");

                            System.out.print(BLUE);
                            for (int i= 0 ; i< eventsName.size() ; i++){
                                if (i== eventsName.size() -1){
                                    System.out.print(eventsName.get(i));
                                } else if (i== eventsName.size()-2) {
                                    System.out.println(eventsName.get(i));
                                } else {
                                    System.out.print(eventsName.get(i) + "  , ");
                                }
                            }
                            System.out.print(RESET);

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

                            //display the result
                            System.out.println("---------------------------------------------------------");
                            System.out.println(" \t Participant's name \t \t Score");
                            System.out.println("---------------------------------------------------------");

                            for (int i=0 ; i< players ; i++){
                                System.out.println("Player's Name : " + GREEN +playerNames.get(i) +RESET + "\t\t Score : " + YELLOW+teamPoints.get(i)+RESET);
                                if (i == players -1){
                                    System.out.println("---------------------------------------------------------");
                                }
                            }
                            //display the winner
                            if (winnerIndex.size() == 1){
                                System.out.println(YELLOW + "Congratulations! Individual participant, "+ playerNames.get(winnerIndex.get(0)) + " has won with score " + winnerPoint + RESET + ".👍👏👏");
                            } else if (winnerIndex.size() > 1) {
                                System.out.print("Congratulations ! individual participants ");
                                for (int i=0 ; i<winnerIndex.size() ; i++){
                                    if (i == winnerIndex.size()-1){
                                        System.out.print( PURPLE + " and " + playerNames.get(winnerIndex.get(i))+RESET);
                                    }else if (i == winnerIndex.size()-2) {
                                        System.out.print(GREEN+playerNames.get(winnerIndex.get(i))+RESET);
                                    } else {
                                        System.out.print(CYAN+playerNames.get(winnerIndex.get(i))+RESET + " , ");
                                    }
                                }
                                System.out.print(" has won with score "+ GREEN + winnerPoint +RESET + ".👍👏👏");
                                System.out.println();
                            } else {
                                System.out.println("Something was wrong ---------------");
                            }

                            System.out.println("--------------------------------------------------------");
                            System.out.println("********************************************************");
                            break;
                    }
                    break;
            }
            //Ask the user , he wants to play again or not;
            //if user want to play , clear all the array list.
            //do this process again
            System.out.println(GREEN+"Do you want to play again?"+BLUE+"y" +WHITE+"/"+RED+"n"+RESET);
            String yesOrNo = input.next();
            yesOrNo.toLowerCase();
            if (yesOrNo.equals("n")){
                keepPlay = false;
                System.out.println(BLUE + "Thanks for using our Tournament Scoring System" + RESET+ ".🙏🙏");
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
        System.out.println(BLUE + "1"+ RESET+" for Normal Teams //5 events ");
        System.out.println(BLUE + "2"+ RESET+" for Normal Individual //5 events ");
        System.out.println(BLUE + "3"+ RESET+" for Special Teams // 1event ");
        System.out.println(BLUE + "4"+ RESET+" for Special Individual // 1 event ");
        System.out.println(BLUE + "5"+ RESET+" Custom events : "+YELLOW +"(user can do whatever he want)" + RESET);
    }
    public static void Custom(){
        System.out.println("******** MENU ************");
        System.out.println("Please Enter the following:");
        System.out.println(BLUE + "1"+ RESET+" for Teams");
        System.out.println(BLUE + "2"+ RESET+" for Individual");
    }
}