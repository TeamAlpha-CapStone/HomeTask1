import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
class InvalidNumberException extends Exception{
    public InvalidNumberException(String message)
    {
        super(message);  
    }
}
class WrongFormatException extends Exception{
    public WrongFormatException(String message)
    {
        super(message);  
    }
}
class InvalidEnergySourceException extends Exception {
    public InvalidEnergySourceException(String message) {
        super(message);
    }
}
class ChargingStation 
{
    String name;
    int no_of_charging_location;
    int no_of_free_charging_locations_available_now;
    int location;
    List<String> type;
     public ChargingStation(String name, int no_of_charging_location,int location, String...type)
    {
        this.name=name;
        this.no_of_charging_location=no_of_charging_location;
        this.type=new ArrayList<>();
        this.location=location;
        for(String t: type)
        {
             this.type.add(t);
        }
        no_of_free_charging_locations_available_now=no_of_charging_location;
    }
}

class Main
{
    // static String cuponcodegenerator() {
    //     Random random = new Random();
    //     StringBuilder sb = new StringBuilder();
    //     for (int i = 0; i < 3; i++) {
    //         char randomChar = (char) ('a' + random.nextInt(26));
    //         sb.append(randomChar);
    //     }
    //     sb.append('@');
    //     for (int i = 0; i < 3; i++) {
    //         char randomChar = (char) ('A' + random.nextInt(26));
    //         sb.append(randomChar);
    //     } 
    //     return sb.toString();
    // }
    static String weather()
    {
        Random random = new Random();
        int randomNumber = random.nextInt(5);
        switch (randomNumber) {
            case 0:
                return "The Weather is Sunny today, so you are recomended to pick \"solar\"";
            case 1:
                return "The Weather is Windy today, so you are recomended to pick \"wind\"";
            case 2:
                return "The Weather is neither sunny/ windy, so you are recomended to pick \"powergrid\""; 
            default:
                return "The Weather is neither Sunny/ Windy, so you are recomended to pick \"powergrid\"";
        }
    }
    static void selectChargingStation(ChargingStation csarray[])
    {
         int c=0;
        for(ChargingStation i: csarray)
        {
            System.out.println(" press: "+c+" to select charging station name: "+i.name+" ,charging staion's location: "+i.location);
            c++;
        } 
    }
    static String selectenergysourse(ChargingStation i)
    {
        System.out.println("Available Energy sources: " + i.type);
        String s="powergrid";
        String cuponcode=null;
        String recomended_e_sourse=weather();
        String com=null;
        switch (recomended_e_sourse) {
            case "The Weather is Sunny today, so you are recomended to pick \"solar\"":
                com="solar";
                break;
            case "The Weather is Windy today, so you are recomended to pick \"wind\"":
                com="wind";
                break;
            case "The Weather is neither sunny/ windy, so you are recomended to pick \"powergrid\"":
                com="powergrid"; 
                break;
            default:
                com="powergrid";
        }
        System.out.println(recomended_e_sourse);
        try  {
            Scanner sc = new Scanner(System.in);
            s = sc.next();
            if(s.equals(com))
            {
                System.out.println("Thanks for Choosing the recomended Energy Sourse. Now you got a coupon.");
                System.out.println("cupon code is: aju@JCD");
            }
            //System.out.println(s);
            if (!(i.type.contains(s))) {
                throw new InvalidEnergySourceException("Should Enter any one of the given types: " + (i.type)+" hence, Selected powergrid by default");
            }
        } catch (InvalidEnergySourceException e) {
            s="powergrid";
            throw new InputMismatchException("InvalidEnergySourceException Exception: " + e.getMessage());
        }
        return s;
    }
    static void proceedforpayment() throws WrongFormatException
    {
        System.out.println("Select:\n 1 for cash \n 2 for card \n 3 for cupon");
        Scanner sc=new Scanner(System.in);
        System.out.print("Now enter your Selection:");
        int method= sc.nextInt();
        switch (method) {
            case 1:
                System.out.println("paid with cash");
                break;
            case 2:
                System.out.println("paid with card");
                break;
            case 3:
            String format="^[a-z]{3}+@[A-Z]{3}+$";
            sc=new Scanner(System.in);
            System.out.print("Enter your Cupon Code:");
            String j=sc.next();
            int count=0;
                    while(count<3)
                        {
                        if(!j.matches(format))
                            {
                                throw new WrongFormatException("Enterd Format is wrong");
                            }
                        if(j.equals("aju@JCD"))
                            {
                                System.out.println("paid with cupon");
                                break;
                            }
                        else{
                            System.out.println("Wrong cupon, enter again");
                            count++;
                            System.out.print("Enter your Cupon Code:");
                            j=sc.next();
                        }
                        if(count ==2)
                            {
                                System.out.println("max attempts reached");
                                break;
                            }
                        }
                break;
            default:
                System.out.println("Select payment method is invalid");;
}
    }
    static void chargeCar() throws Exception
    {
        //detailed functialities will be added later
        System.out.println("\nCar Charging....\nCar charged....\n");
        try {
            proceedforpayment();
        } catch (WrongFormatException e) {
            throw new Exception("Exception occured in proceedforpayment(): "+e.getMessage());
        }
    }
    public static void main(String[] args)
    {
        ChargingStation hp=new ChargingStation("hp",5,0,"wind","solar","powergrid");
        ChargingStation bharath=new ChargingStation("bharath",7,1,"wind","solar","powergrid");
        ChargingStation indane=new ChargingStation("indane",4,2,"wind","solar","powergrid");
        ChargingStation reliance=new ChargingStation("reliance",6,3,"wind","solar","powergrid");
        ChargingStation csarray[]={hp,bharath,indane,reliance};
        System.out.println("available charging stations:");
        int selected_station=0;
        selectChargingStation(csarray);
        System.out.print("Now Enter your selection: ");
        Scanner sc=new Scanner(System.in);
        while(true)
        {
            try{   
            int i=sc.nextInt();
            if(i<0 || i>csarray.length-1)
            {
                throw new InvalidNumberException("Enter a number betwenn 0 and "+(csarray.length-1)+" hence, Selected 0 by default");
            }
            selected_station=i;
            break;
        } catch (InvalidNumberException e) {       //Handling Multiple Exceptions
            System.out.println("Must be in range: "+e);
        }
        catch (InputMismatchException e) {
            System.out.println("Must be a number: "+e);
        } 
        }
        
        //==========================================================================
        System.out.println("Now select your desired energy sourse:");
        String selected_E_sourse=null;
        try {
            selected_E_sourse=selectenergysourse(csarray[selected_station]);
        } catch (InputMismatchException e) {            //rethrowing exception
            System.out.println("Exception Occured: "+e.getMessage());
        }
        System.out.println("You have "+csarray[selected_station].no_of_free_charging_locations_available_now+
        " free charging slots available for charging with your selecte energy source "+selected_E_sourse +", and it takes 1 MInute to charge.");
       
        try {
            chargeCar(); 
        } catch (Exception e) {     //chaining exception
            System.out.println("Exception occured: "+e);
            //e.printStackTrace();
        }
        finally{                    // Resource management
            if (sc != null) {
                sc.close();
        }
    }
}
}
