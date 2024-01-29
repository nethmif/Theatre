package Theatre;
import java.util.*; // Scanner, ArrayList, InputMismatchException
import java.io.*;  // FileWriter, IOException, PrintWriter and File were imported
public class Theatre {
    private static ArrayList<Ticket> TicketsArrayList = new ArrayList<>();  // the ArrayList for ticket information was created
    public static void main(String[] args) {
        int[] Row1 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};  // the array for Row1 was created
        int[] Row2 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};  // the array for Row2 was created
        int[] Row3 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};  // the array for Row3 was created
        int[][] allRows= {Row1, Row2, Row3};    // the three arrays for the three rows (Row1, Row2, Row3) were put into one array to avoid repetition of the same process for all the rows
        System.out.println("-------------------------------------------------\n"+"Welcome to the New Theatre"); // the following message, Welcome to the New Theatre, is being displayed at the start of the program
        while (true) {
            try {
                System.out.println("-------------------------------------------------\nPlease select an option:\n1) Buy a ticket\n2) Print seating area\n3) Cancel ticket\n4) List available seats\n5) Save to file\n6) Load from file\n7) Print ticket information and total price\n8) Sort tickets by price\n    0)Quit\n-------------------------------------------------"); // the 8 options are displayed to the user
                Scanner input = new Scanner(System.in);
                System.out.print("Enter option: "); // user can enter the preferred option
                int option = input.nextInt();
                if (option >= 0 && option < 9) {
                    if (option==1) {
                        buy_ticket(allRows);
                    } else if (option==2){
                        printing_seating_area(Row1, Row2, Row3, allRows);
                    } else if (option==3){
                        cancel_ticket(allRows);
                    } else if (option==4){
                        show_available(allRows);
                    } else if (option==5){
                        save(allRows);
                    } else if (option==6) {
                        load();
                    } else if (option==7){
                        show_tickets_info();
                    } else if (option==8){
                        sort_tickets();
                    } else if (option==0){
                        break;
                    }
                } else {
                    System.out.println("Incorrect option entered"); // this message is displayed to the user if they enter a value out of the options 0 to 8
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter an integer");  // this message will be displayed if the user enters another character instead of an integer
            }
        }
    }
    public static void buy_ticket(int[][] allRows) {
        try {
            Scanner input = new Scanner(System.in);
            System.out.print("Please enter the row number: ");  // asks the user to enter the row number that they prefer
            int row = input.nextInt();
            if (row < 4 && row > 0) {   // makes sure that the row number they entered is a number within the range 0-3 (excluding 0 and including 3)
                System.out.print("Please enter the seat number: "); // asks the user to enter the seat number that they prefer
                int seat = input.nextInt();
                if (seat<=allRows[row-1].length && seat>0) {    // makes sure that the seat number entered by the user is within the range of the maximum number of seats for each row (Row1- 12 seats, Row2- 16 seats, Row3- 20 seats)
                    for (int i = 0; i < allRows.length; i++) {  // a for count is made to repeat the same process for all the three rows
                        if (row == i + 1 && seat <= allRows[i].length) {    // checks whether the seat number and the row number entered by the user is valid
                            if (allRows[i][seat - 1] == 1) {    // if the particular seat number entered by the user holds the value(1) in the array it means that the seat has already been occupied
                                System.out.println("Seat occupied");    // this message will be displayed to the user so that he can book another seat
                            }else if (allRows[i][seat - 1] == 0) {  // if the array holds the value 0 (means that the seat is not yet occupied) then the user is allowed to book the seat after getting his/ her ticket information
                                System.out.print("Please enter the name: ");    // the user is asked to enter the name
                                String name = input.next();
                                if (name.matches("[a-zA-Z]+")) {    // makes sure that the user enters a valid name as the input
                                    System.out.print("Please enter the surname: "); // the user is asked to enter the surname
                                    String surname = input.next();
                                    if (surname.matches("[a-zA-Z]+")) {    // makes sure that the user enters a valid surname as the input
                                        System.out.print("Please enter the email: ");   // the user is asked to enter the email address
                                        String email = input.next();
                                        if (email.contains("@")) {  // an email verification process is done. Firstly, it checks for the presence of the @ sign in the address entered by the user
                                            if (email.contains(".")) {  //  Secondly, it checks for the presence of the . (dot) in the address entered by the user
                                                Person person = new Person(name, surname, email);
                                                System.out.print("Please enter the price of a ticket: £ "); // only if both the @ and . are there in the email address, the user wil be asked to enter the price of the ticket. (considered as a ticket)
                                                double price = input.nextDouble();
                                                Ticket ticket = new Ticket(row, seat, price, person);
                                                TicketsArrayList.add(ticket);   // the ticket consisting of all the user information is added to the ArrayList
                                                allRows[i][seat - 1] = 1;   // now the array value in the seat booked by the user is changed to 1 to indicate that the seat is now being occupied.
                                            } else {
                                                System.out.println("Invalid. Please re-enter a valid email address.");  // if the . is not there in the email address entered the user the following message will be displayed to the user and he/ she should fill in the ticket information again
                                            }
                                        } else {
                                            System.out.println("Invalid. Please re-enter a valid email address.");  // if the @ is not there in the email address entered the user the following message will be displayed to the user and he/ she should fill in the ticket information again
                                        }
                                    }else{
                                        System.out.println("Please enter a valid surname");
                                    }
                                } else{
                                    System.out.println("Please enter a valid name");
                                }
                            }
                        }
                    }
                } else{
                    System.out.println("Please enter a valid seat number.");    // the message is displayed to the user indicating that he/ she is entering a seat value that is out of the range
                }
            } else {
                System.out.println("Error: This row number does not exist. Please select 1-3.");    // the message is displayed to the user indicating that he/ she is entering a row value that is out of the range
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please enter an integer");  // an error message ill be displayed if the user enter another character instead of an integer.
        }
    }
    public static void printing_seating_area(int[] Row1, int[] Row2, int[] Row3, int[][] allRows){
        System.out.print("      ");
        for (int i=0; i<18; i++){
            System.out.print("*");
            if(i==8){
                System.out.println("\n      * "+"Stage"+" *" );
                System.out.print("      ");
            }
        }
        for (int j=0; j < allRows.length; j++) {
            System.out.println("");
            if (allRows[j]==Row1){
                System.out.print("    ");   // the appropriate amount of space will be left in the beginning of the first row
            }if (allRows[j]==Row2){
                System.out.print("  "); // the appropriate amount of space will be left in the beginning of the second row
            }
            for (int i=0; i<allRows[j].length; i++){
                if (allRows[j][i]==1){
                    System.out.print("X");  // in the array the positions that hold 1 are replaced by X when displaying, to indicate that the seat is being occupied
                } else if (allRows[j][i]==0){
                    System.out.print("O");  // in the array the positions that hold 0 are replaced by O when displaying, to indicate that the seat is being empty
                }if (i==5 && allRows[j]==Row1){
                    System.out.print(" ");  // to properly align by leaving a space in the middle of the row.
                }if (i==7 && allRows[j]==Row2){
                    System.out.print(" ");  // to properly align by leaving a space in the middle of the row.
                }if (i==9 && allRows[j]==Row3){
                    System.out.print(" ");  // to properly align by leaving a space in the middle of the row.
                }
            }
        }
        System.out.println("");
    }
    public static void ticketRemoval(int row, int seat){
        Ticket removeTicket = null;
        for (Ticket ticket : TicketsArrayList) {
            if (ticket.getRow() == row && ticket.getSeat() == seat) {   // checks for the presence of the row and seat in each ticket
                removeTicket = ticket;
                break;
            }
        }if (removeTicket != null) {
            TicketsArrayList.remove(removeTicket);  // if the row and seat are both their in t=the ticket then the ticket is removed as requested by the user
        }
    }
    public static void cancel_ticket(int[][] allRows) {
        try {
            Scanner input = new Scanner(System.in);
            System.out.print("Please enter the row number: ");  // the user is asked to enter the row number that they want to cancel
            int row = input.nextInt();
            System.out.print("Please enter the seat number: "); // the user is asked for the seat number that they want to cancel.
            int seat = input.nextInt();
            if (row < 4 && row > 0) {   // checks whether the cancelling row is within the range 0-3
                if (seat<=allRows[row-1].length && seat>0) {
                    for (int i = 0; i < 3; i++) {
                        if (row == i + 1 && seat < allRows[i].length && allRows[i][seat - 1] == 1) {    // checks whether the seat has been occupied
                            allRows[i][seat - 1] = 0;   // the arrays value is changed back to 0 to indicate that it is again empty
                            ticketRemoval(row, seat);   // the appropriate ticket according to the information given by the user will be cancelled
                        }
                        else if (row == i + 1 && seat < allRows[i].length && allRows[i][seat - 1] == 0){
                            System.out.println("Please re-enter. This seat has not been occupied"); // this message will be displayed indicating that the seat has not yet been occupied
                        }
                    }
                } else {
                    System.out.println("Incorrect entry. Please re-enter a valid seat");
                }
            }else {
                System.out.println("Incorrect entry. Please re-enter a valid row");
            }
        } catch(IndexOutOfBoundsException e){
            System.out.println("Please enter an integer.");
        }
    }
    public static void show_available(int[][] allRows) {
        for (int j=0; j < allRows.length; j++) {
            System.out.print("Seats available in row " + (j+1) + ": ");
            for (int i = 0; i < allRows[j].length; i++) {
                if (allRows[j][i] == 0) {
                    System.out.print(", ");
                    System.out.print(i + 1);    // displays the empty seats to the user
                }
            }
            System.out.println("");
        }
    }
    private static void save(int[][] allRows){
        try{
            FileWriter saveFile= new FileWriter("Save.txt");
            PrintWriter rowsPrint = new PrintWriter(saveFile);
            for (int j=0; j < allRows.length; j++) {
                rowsPrint.println("");
                rowsPrint.print("Row " +(j+1)+": ");
                for (int i=0; i<allRows[j].length; i++) {
                    rowsPrint.print(allRows[j][i]); // the arrays information for each row is being stored in a text file
                }
            }
            saveFile.close();
        } catch (IOException e){
            System.out.println("Error occurred while writing to a file.");
            e.printStackTrace();
        }
    }
    public static void load(){
        try {
            File ReadFile= new File("Save.txt");
            Scanner input= new Scanner(ReadFile);
            while (input.hasNextLine()) {
                String display = input.nextLine();
                System.out.println(display);    // the arrays information for each row stored in the text file is being displayed.
            }
            input.close();
        } catch (IOException e) {
            System.out.println("Error occurred while reading the file.");
            e.printStackTrace();
        }
    }
    public static void show_tickets_info() {
        double TotalPriceTicket = 0.0;
        for (Ticket ticket : TicketsArrayList) {
            ticket.print();
            TotalPriceTicket += ticket.getPrice();  // the ticket prices are added up
            System.out.println("-------------------------------------------------");
        }
        System.out.println("Total price of tickets: £ "+TotalPriceTicket);  // the total price of all the tickets are displayed  after displaying all the ticket information
    }
    public static void sort_tickets() {
        int bottom = TicketsArrayList.size()-2;
        Ticket temp;
        boolean exchanged = true;
        while (exchanged) {     // bubble sort algorithm
            exchanged = false;
            for (int i= 0; i<= bottom; i++) {
                if (TicketsArrayList.get(i).getPrice() > TicketsArrayList.get(i+ 1).getPrice()) {
                    temp = TicketsArrayList.get(i);
                    TicketsArrayList.set(i, (TicketsArrayList.get(i+1))) ;
                    TicketsArrayList.set((i+1), temp) ;
                    exchanged = true;
                }
            }
            bottom--;
        }
        for (Ticket ticket : TicketsArrayList) {
            ticket.print();   // the tickets  are displayed according to the prices in ascending order.
            System.out.println("-------------------------------------------------");
        }
    }
}
