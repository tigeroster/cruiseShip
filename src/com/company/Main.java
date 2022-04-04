package com.company;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

class CruiseShip {
    // DECLARING ALL THE STATIC VARIABLES TO BE ACCESSED LATER
    // mainShipMenu & subShipMenu LOOPS THROUGH THE MENU
    private static boolean mainShipMenu = true;
    private static boolean subShipMenu = true;
    // passengerCounter WILL COUNT THE NUMBER OF PASSENGERS ADDED TO A CABIN
    private static int passengerCounter = 0;

    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);

        // CREATING A 2D ARRAY FOR THE PASSENGER. THIS REPRESENTS THAT THERE ARE 3 PASSENGERS FOR EACH CABIN
        Passenger[][] passengers = new Passenger[12][3];
        // CREATING THE CABIN ARRAY AND CREATING 12 CABINS OBJECTS
        Cabin[] cabins = new Cabin[12];

        // THE initialise METHOD WILL RUN FIRST TO MAKE ALL THE CABINS EMPTY
        initialise(cabins, passengers);
        // START OF THE PROGRAM
        while(mainShipMenu){
            while(subShipMenu){
                System.out.println("------------------------------------------------------------------------------------");
                System.out.println("------------------------------------------------------------------------------------");
                System.out.println("                      Hello Welcome to the Cruise Ship!");
                System.out.println("------------------------------------------------------------------------------------");
                System.out.println("------------------------------------------------------------------------------------");
                System.out.println("               Please select one of the options below to proceed\n");
                System.out.println("|                   A : Add a Passenger to a Cabin");
                System.out.println("------------------------------------------------------------------------------------");
                System.out.println("|                   V : View all Cabins");
                System.out.println("------------------------------------------------------------------------------------");
                System.out.println("|                   E : Display Empty Cabins");
                System.out.println("------------------------------------------------------------------------------------");
                System.out.println("|                   D : Delete Passenger from Cabin");
                System.out.println("------------------------------------------------------------------------------------");
                System.out.println("|                   F : Find cabin from Customer Name");
                System.out.println("------------------------------------------------------------------------------------");
                System.out.println("|                   S : Store program data into a file");
                System.out.println("------------------------------------------------------------------------------------");
                System.out.println("|                   L : Load program data from file");
                System.out.println("------------------------------------------------------------------------------------");
                System.out.println("|                   O : View passengers Ordered alphabetically by name.");
                System.out.println("------------------------------------------------------------------------------------");
                System.out.println("|                   T : View Passenger Expenses");
                System.out.println("------------------------------------------------------------------------------------");
                System.out.println("------------------------------------------------------------------------------------");
                System.out.println("|                   X : Exit the Program");
                System.out.println("------------------------------------------------------------------------------------");
                System.out.println();

                System.out.println("Enter your option to proceed : ");
                String selection = input.next();
                // CONVERTING THE INPUT TO UPPERCASE
                selection = selection.toUpperCase(Locale.ROOT);

                switch (selection) {
                    case "A" -> addPassengerToCabin(passengers, cabins);
                    case "V" -> viewAllCabins(passengers);
                    case "E" -> checkRoomVacancy(cabins,passengers);
                    case "D" -> deletePassengerFromCabin(cabins, passengers);
                    case "F" -> findCabinFromPassenger(cabins, passengers);
                    case "S" -> storeProgramDataInFile(passengers);
                    case "L" -> loadProgramDataFromFile(passengers);
                    case "O" -> viewPassengersAlphabetically(passengers);
                    case "T" -> viewPassengerExpense(passengers);
                    case "X" -> System.exit(0);
                    default -> System.out.println("Invalid Input");
                }
                System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
                System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
                System.out.println("Would you like to continue with another program? \n {a} Enter the Number : 1 to Continue" +
                                "\n " +
                                "{b} Enter any Number to Exit");
                System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
                System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
                if(input.nextInt() == 1){
                    subShipMenu = true;
                }else{
                    subShipMenu = false;
                }
            }// END OF subShipMenu

            subShipMenu = true;
            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
            System.out.println("Would you like to continue with this program? \n {a} Enter the Number : 1 to Continue" +
                    "\n " +
                    "{b} Enter any Number to Exit");
            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
            if(input.nextInt() == 1){
                mainShipMenu = true;
            }else{
                System.out.println("Thank you! Have a Nice day ahead!");
                System.exit(0);
            }
        } // END OF mainShipMenu
    }

    // THE initialise METHOD IS INITIALISED
    private static void initialise(Cabin[] cabinRef, Passenger[][] passengers) {
        for (int x = 0; x < 12; x++ ) {
            // MAKING ALL THE ROOMS EMPTY
            cabinRef[x] = new Cabin();
            cabinRef[x].mainName = "nobody";
            for(int i = 0;i<3;i++){
                // DECLARING A PASSENGER OBJECT
                 Passenger passenger = new Passenger();
                 passenger.setFirstName("nobody");
                 passenger.setSurName("nobody");
                 passenger.setExpenses(0.0);
                 // ADDING THE PASSENGER OBJECT TO THE PASSENGER ARRAY
                 passengers[x][i] = passenger;
            }
        }
    }

    // THE CHECK ROOM VACANCY METHOD IS INITIALISED
    public static void checkRoomVacancy(Cabin[] cabins, Passenger[][] passengers){
        // LOOPS THROUGH THE CABINS AND PASSENGER ARRAYS
        for(int x = 0; x< cabins.length; x++){
            for (int i = 0; i < 3; i++){
                if(passengers[x][i].getFirstName().equals("nobody")){
                    System.out.println("Cabin " + (x + 1) + " passenger " + (i + 1) + " is empty");
                }
            }
        }
    }

    // THE ADD PASSENGER TO A CABIN METHOD IS INITIALISED
    public static void addPassengerToCabin(Passenger[][] passengers, Cabin[] cabins){
        int count = 0;
        while (count != 1){
            try{
                Scanner sc = new Scanner(System.in);
                System.out.println("Here are the Available Rooms\n");
                checkRoomVacancy(cabins, passengers);
                System.out.println();
                System.out.println("Enter a Cabin Number from (1 - 12)");
                int cabinNum = sc.nextInt() - 1;
                System.out.println("Enter the Passenger Number from (1 - 3)");
                int passengerNum = sc.nextInt() - 1;
                // DECLARING THE PASSENGER OBJECT
                Passenger passenger = new Passenger();
                System.out.println("Enter the Firstname of the Passenger : ");
                String firstName = sc.next();
                System.out.println("Enter the Surname of the Passenger : ");
                String surName = sc.next();
                System.out.println("Enter the expenses of the Passenger : ");
                double expenses = sc.nextDouble();
                if(passengerCounter == 36) {
                    // THE CRUISE SHIP IS FULL. ADDING THE EXTRA PASSENGERS TO THE WAITING LIST
                    WaitingList waitingPassengers = new WaitingList(firstName, surName, expenses);
                    WaitingList.enQueue(waitingPassengers);
                    System.out.println("The Cruise Ship is full");
                    System.out.println("Passenger " + waitingPassengers.getFirstName() + " " + waitingPassengers.getSurName() + " " +
                            "added to the " +
                            "waiting list");
                    count++;
                }else if(passengers[cabinNum][passengerNum].getFirstName().equals("nobody")){
                    // ADDING A PASSENGER TO THE CABIN
                    passenger.setFirstName(firstName);
                    passenger.setSurName(surName);
                    passenger.setExpenses(expenses);
                    cabins[cabinNum].setName(firstName);
                    passengers[cabinNum][passengerNum] = passenger;
                    count++;
                    // INCREASING THE PASSENGER COUNT BY 1
                    passengerCounter++;
                    System.out.println("The Passenger was Successfully added to the Cabin");
                    System.out.println("------------- Passenger Details -----------------");
                    System.out.println("\n Passenger ID : " + (passengerNum + 1) + "\n Passenger Name : " + passenger.getFirstName() + " " + passenger.getSurName() +
                            "\n Passenger's Cabin : " + (cabinNum  + 1) + "\n Passenger Expenses : " + passenger.getExpenses() + "\n");
                }else{
                    // RUNS WHEN THE POSITION IS ALREADY OCCUPIED
                    System.out.println("The position is already occupied by another Passenger");
                    System.out.println("Searching for vacant rooms.....");
                    System.out.println("\nDisplaying Empty Rooms.....\n");
                    // THIS METHOD WILL RUN TO DISPLAY THE EMPTY ROOMS
                    checkRoomVacancy(cabins, passengers);
                    System.out.println("\nAdd the Passenger again\n");
                }
            }catch (InputMismatchException e){
                System.out.println("Invalid Input\nEnter an Valid Input");
            }catch(ArrayIndexOutOfBoundsException c){
                System.out.println("Invalid number. Please enter an input within the given range");
            }
        }
    }

    // THE DELETE PASSENGER FROM A CABIN METHOD IS INITIALISED
    public static void deletePassengerFromCabin(Cabin[] cabins, Passenger[][] passengers) {
        try{
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the Cabin Number (1 - 12)");
            int cabinNum = sc.nextInt() - 1;
            System.out.println("Enter the Passenger Number (1 - 3)");
            int passengerNum = sc.nextInt() - 1;
            if(!passengers[cabinNum][passengerNum].getFirstName().equals("nobody")){
                cabins[cabinNum].setName("nobody");
                // DELETING THE PASSENGER FROM THE CABIN
                passengers[cabinNum][passengerNum].setFirstName("nobody");
                passengers[cabinNum][passengerNum].setSurName("nobody");
                passengers[cabinNum][passengerNum].setExpenses(0.0);
                System.out.println("Passenger deleted from the Cabin\n");
                // CHECKING THE WAITING LIST
                WaitingList waitingPassenger = WaitingList.deQueue();
                if(waitingPassenger != null){
                    // ADDING THE WAITING PASSENGERS THE VACANT POSITION
                    passengers[cabinNum][passengerNum].setFirstName(waitingPassenger.getFirstName());
                    passengers[cabinNum][passengerNum].setSurName(waitingPassenger.getSurName());
                    passengers[cabinNum][passengerNum].setExpenses(waitingPassenger.getExpenses());
                    System.out.println("A waited Passenger " + waitingPassenger.getFirstName() + " " + waitingPassenger.getSurName() +
                            " was added to the Vacant");
                    System.out.println("------------- Passenger Details -----------------");
                    System.out.println("\n Passenger ID : " + (passengerNum + 1) + "\n Passenger Name : " + waitingPassenger.getFirstName() + " " + waitingPassenger.getSurName() +
                            "\n Passenger's Cabin : " + (cabinNum  + 1) + "\n Passenger Expenses : " + waitingPassenger.getExpenses() + "\n");
                }else{
                    passengerCounter--;
                    System.out.println("No Passengers were found in the Waiting List");
                }
            }else{
                System.out.println("This Passenger slot is already Empty");
            }

        }catch(InputMismatchException e){
            System.out.println("Invalid Input");
        }catch(ArrayIndexOutOfBoundsException w){
            System.out.println("Input Out of Range. Please enter a input within the given range");
        }
    }

    // THE VIEW ALL CABINS METHOD IS INITIALISED
    public static void viewAllCabins(Passenger[][] passengers){
        for(int i = 0; i<12;i++){
            for(int x = 0; x<3; x++){
                if(!passengers[i][x].getFirstName().equals("nobody")){
                    System.out.println("Cabin " + (i + 1) + " has the passenger " + (x + 1) + " who is " + passengers[i][x].getFirstName() + " " + passengers[i][x].getSurName());
                }else{
                    System.out.println("Cabin " + (i + 1) + " passenger " + (x + 1) + " is empty");
                }
            }
        }
    }

    // THE FIND CABINS FORM PASSENGER METHOD IS INITIALISED
    public static void findCabinFromPassenger(Cabin[] cabins, Passenger[][] passengers){
        Scanner sc = new Scanner(System.in);
        boolean found = false;
        System.out.println("Enter the Passenger's Firstname : ");
        String passengerFirstName = sc.nextLine();
        System.out.println("Enter the Passenger's Surname : ");
        String passengerSurName = sc.nextLine();
        // LOOPS THROUGH THE CABINS AND PASSENGER ARRAYS
        for (int i = 0; i<cabins.length; i++){
            for(int x = 0;x<3; x++){
                if (passengerFirstName.equalsIgnoreCase(passengers[i][x].getFirstName()) && passengerSurName.equalsIgnoreCase(passengers[i][x].getSurName())){
                    found = true;
                    System.out.println(passengerFirstName + " " + passengerSurName + " is in Cabin " + (i + 1));
                }
            }
        }
        if(!found){
            System.out.println("Sorry! We can't find this Passenger");
        }
    }

    // THE STORE PROGRAM DATA IN FILE METHOD IS INITIALISED
    public static void storeProgramDataInFile (Passenger[][] passengers) {
            try(FileOutputStream fileOut = new FileOutputStream("passengerData.txt");
            ObjectOutputStream writer = new ObjectOutputStream(fileOut);){
                // LOOPS THROUGH THE PASSENGER ARRAY TO STORE THE DATA TO A FILE
                for(int i = 0; i<12; i++){
                    for(int j = 0; j<3; j++){
                        writer.writeObject(passengers[i][j]);
                    }
                }
                writer.close();
                System.out.println("\nPassenger Data Stored into the File\n");
            }catch(IOException e){
                e.printStackTrace();
            }
    }

    // THE LOAD PROGRAM DATA FROM FILE METHOD IS INITIALISED
    public static void loadProgramDataFromFile(Passenger[][] passengers) {
        try(FileInputStream fileIn = new FileInputStream("passengerData.txt");
        ObjectInputStream reader = new ObjectInputStream(fileIn)){
            for(int i = 0;i<12; i++){
                for(int j = 0; j<3; j++){
                    // ADDS THE DATA FROM FILE TO THE PASSENGER ARRAY
                    passengers[i][j] = (Passenger) reader.readObject();
                    if(!passengers[i][j].getFirstName().equals("nobody")){
                        passengerCounter++;
                    }
                }
            }
            System.out.println("\nPassenger Data loaded Successfully");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // THE VIEW PASSENGERS ALPHABETICALLY METHOD IS INITIALISED
    public static void viewPassengersAlphabetically(Passenger[][] passengers){
        // CONVERTING THE 2D PASSENGER ARRAY TO A 1D ARRAY
        // https://stackoverflow.com/questions/8935367/convert-a-2d-array-into-a-1d-array
        Passenger[] allPassengers = Arrays.stream(passengers).flatMap(Stream::of).toArray(Passenger[]::new);
        Passenger temp;
        System.out.println("All the Passengers in the Alphabetical Order");
        System.out.println("--------------------------------------------\n");
        for(int j = 0; j<allPassengers.length;j++){
            for(int i = j + 1;i<allPassengers.length;i++){
                //COMPARING THE TWO VALUES
                if(((allPassengers[j].getFirstName()).compareTo((allPassengers[i]).getFirstName())) > 0){
                    temp = allPassengers[j];
                    allPassengers[j] = allPassengers[i];
                    allPassengers[i] = temp;
                }
            }
            if(!allPassengers[j].getFirstName().equals("nobody")){
                // PRINTS NAMES TO THE CONSOLE ALPHABETICALLY
                System.out.println((j + 1) + " " + allPassengers[j].getFirstName() + " " + allPassengers[j].getSurName());
            }
        }
    }

    // THE VIEW PASSENGER EXPENSE METHOD IS INITIALISED
    public static void viewPassengerExpense(Passenger[][] passengers){
        int count = 0;
        while (count == 0){
            try{
                Scanner sc = new Scanner(System.in);
                System.out.println("Choose your option \n 1 : Expense per Passenger \n 2 : Expense for all the Passengers");
                int input = sc.nextInt();
                if(input == 1){
                    // PRINTS THE EXPENSE OF ONE PASSENGER
                    System.out.println("Enter the Passenger Name : ");
                    String passenger = sc.next();
                    for(int x = 0;x<12;x++){
                        for(int i = 0;i<3;i++){
                            if(passengers[x][i].getFirstName().equals(passenger)){
                                System.out.println(passenger + " has spent " + passengers[x][i].getExpenses());
                            }else{
                                System.out.println("The Passenger cannot be found");
                            }
                        }
                    }
                    count++;
                }else if(input == 2){
                    // PRINTS THE SUM OF ALL THE EXPENSES OF THE PASSENGERS IN ONE CABIN
                    System.out.println("Enter the Cabin Number");
                    double totalExpenses = 0;
                    int cabin = sc.nextInt() - 1;
                    for(int j = 0;j<12;j++){
                        if(cabin == j){
                            for(int k = 0;k<3;k++){
                                // CONVERTING THE DOUBLE TO AN INTEGER
                                int passengerExpense = (int) passengers[j][k].getExpenses();
                                totalExpenses += passengerExpense;
                            }
                        }
                    }
                    System.out.println("The Passengers has spent " + totalExpenses);
                    count++;
                }
            }catch(InputMismatchException e){
                System.out.println("Invalid Input");
            }catch(ArrayIndexOutOfBoundsException e){
                System.out.println("Input Out of Range");
            }
        }
    }
}