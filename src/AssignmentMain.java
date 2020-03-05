import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class AssignmentMain {

    static final double COST_PIZZA_SM = 12.75;
    static final double COST_PIZZA_LG = 18.75;
    static final double COST_SALAD = 7.25;
    static final double COST_COLA_1 = 1.75;
    static final double COST_COLA_6 = 8.00;
    static final double COST_COLA_2L = 3.75;
    static final double SMALL_BOTTLE_DEPOSIT = 0.10;
    static final double LARGE_BOTTLE_DEPOSIT = 0.25;
    static final double GST = 0.05;
    static final Scanner scanner = new Scanner(System.in);
    static Boolean orderInProgress = true;
    static double orderTotal = 0.00;
    static DailyTotals dailyTotal = new DailyTotals();


    public static void main(String[] args) {

        while(orderInProgress) {

            System.out.println(topOutput());
            System.out.println(middleOutput());
            System.out.print("Enter item choice (P - Pizza, S - Sides, C- Cola, or X - exit): ");
            char foodItem = scanner.next().charAt(0);
            switch (Character.toLowerCase(foodItem)) {
                case 'p': pizzaOutput();
                break;
                case  's': sidesOutput();
                break;
                case 'c': cokeOutput();
                break;
                case 'x': System.out.println(dailySummary());
                          xPressed();
                break;
                default: System.out.println("Please enter a specified letter");
                break;
            }
        }
    }

    public static String topOutput() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM-dd-yyyy");

        String date = simpleDateFormat.format(new Date());
        String storeName = "Austin's";

        return "********************************************\n" +
                "*** Welcome to " + storeName + " Pizza Restaurant ***\n" +
                "***      Today's date is " + date + "     ***\n" +
                "********************************************";
    }

    public static String middleOutput() {
        return  "--------------------------------------------\n" +
                "Item             Size                  Price\n" +
                "--------------------------------------------\n" +
                "Pizza            Small               $" + COST_PIZZA_SM + "\n" +
                "Pizza            Large               $" + COST_PIZZA_LG + "\n" +
                "Sides            Salad                $" + COST_SALAD + "\n" +
                "Cola             1 can                $" + COST_COLA_1 + "\n" +
                "Cola             6-pack               $" + COST_COLA_6 + "\n" +
                "Cola             2 L                  $" + COST_COLA_2L + "\n";
    }

    public static void pizzaOutput() {
        System.out.print("Enter size of pizza (s or l): ");
        char foodSize = scanner.next().charAt(0);
        if (Character.toLowerCase(foodSize) == 'x') {
            xPressed();
            return;
        }
        String foodSizeChoice = Character.toLowerCase(foodSize) == 's' ? "small" : "large";
        System.out.print("Enter number of " + foodSizeChoice + " pizzas to order: ");
        Integer foodNumber = scanner.nextInt();
        double priceOfSinglePizza = foodSizeChoice == "small" ? COST_PIZZA_SM : COST_PIZZA_LG;
        double foodPrice = calcGST((priceOfSinglePizza * foodNumber));

        if (priceOfSinglePizza == COST_PIZZA_SM) {
            dailyTotal.setCostPizzaSm((foodPrice));
            dailyTotal.setNumberPizzaSm(foodNumber);
        } else {
            dailyTotal.setCostPizzaLg(foodPrice);
            dailyTotal.setNumberPizzaLg(foodNumber);
        }
        System.out.printf("Pizza cost is (incl. GST): $ %.2f", foodPrice);
        System.out.println();
        isOrderDone(foodPrice);
    }

    public static void sidesOutput() {
        System.out.print("Enter number of salads to order: ");
        Integer saladNumber = scanner.nextInt();
        double priceOfSalad = calcGST(COST_SALAD * saladNumber);
        dailyTotal.setCostSalad(priceOfSalad);
        dailyTotal.setNumberSalad(saladNumber);
        System.out.printf("Side cost is (incl. GST): $ %.2f", priceOfSalad);
        System.out.println();
        isOrderDone(priceOfSalad);
    }

    public static void cokeOutput() {
        System.out.print("Enter size of cola (one, six, or two): ");
        String cokeNumber = scanner.next().substring(0, 3);
        cokeNumber.toLowerCase();
        String cokeSizeChosen = "";
        double priceOfCokeChosen = 0.0;
        if (cokeNumber.equals("one")) {
            cokeSizeChosen = " single colas ";
            priceOfCokeChosen = COST_COLA_1;
        } else if (cokeNumber.equals("six")) {
            cokeSizeChosen = " cola  6-pack ";
            priceOfCokeChosen = COST_COLA_6;
        } else if (cokeNumber.equals("two")){
            cokeSizeChosen = " 2L colas ";
            priceOfCokeChosen = COST_COLA_2L;
        } else {
            xPressed();
            return;
        }

        System.out.print("Enter number of" + cokeSizeChosen + "to order: ");
        Integer numberOfCokes = scanner.nextInt();
        double priceOfCokes = 0.0;
        if (cokeNumber.equals("one")) {
            priceOfCokes = calcGST(calcDeposit(priceOfCokeChosen * numberOfCokes, numberOfCokes, 0));
            dailyTotal.setCostCola1(priceOfCokes);
            dailyTotal.setNumberCola1(numberOfCokes);
        } else if (cokeNumber.equals("six")) {
            priceOfCokes = calcGST(calcDeposit(priceOfCokeChosen * numberOfCokes, numberOfCokes * 6, 0));
            dailyTotal.setCostCola6(priceOfCokes);
            dailyTotal.setNumberCola6(numberOfCokes);
        } else if (cokeNumber.equals("two")) {
            priceOfCokes = calcGST(calcDeposit(priceOfCokeChosen * numberOfCokes, 0, numberOfCokes));
            dailyTotal.setCostCola2l(priceOfCokes);
            dailyTotal.setNumberCola2l(numberOfCokes);
        } else {
            xPressed();
            return;
        }

        System.out.printf("Cola cost is (incl. GST and deposit): $ %.2f", priceOfCokes);
        System.out.println();
        isOrderDone(priceOfCokes);
    }

    public static void isOrderDone(double price) {
        System.out.print("Is customer order complete? (Y/N)");
        orderTotal += price;
        if (Character.toLowerCase(scanner.next().charAt(0)) == 'y') {
            dailyTotal.setNumberCustomers();
//            orderInProgress = false;
            System.out.printf("The total cost for this customer is $ %.2f", orderTotal);
            orderTotal = 0.00;
            System.out.println();
        }
    }

    public static String dailySummary() {
        return "Daily Summary\n" +
                "--------------------------------------------\n" +
                "Item        Size        Price      Qty. Sold\n" +
                "--------------------------------------------\n" +
                "Pizza       Small      $12.75              " + dailyTotal.getNumberPizzaSm() + "\n" +
                "Pizza       Large      $18.75              " + dailyTotal.getNumberPizzaLg() + "\n" +
                "Sides       Salad       $7.25              " + dailyTotal.getNumberSalad() +"\n" +
                "Cola        1 can       $1.75              " + dailyTotal.getNumberCola1() + "\n" +
                "Cola        6-pack      $8.00              " + dailyTotal.getNumberCola6() + "\n" +
                "Cola        2 L         $3.75              " + dailyTotal.getNumberCola2l() + "\n" +
                "--------------------------------------------\n" +
                "The total revenue for the day is $" + dailyTotal.revenueTotal() + "\n" +
                "The total number of customers today is " + dailyTotal.getNumberCustomers() + "\n" +
                "Good bye!";
    }

    public static double calcDeposit(double cost, Integer numOfSmallCans, int numOfBigBottles) {
        return cost + (SMALL_BOTTLE_DEPOSIT * numOfSmallCans) + (LARGE_BOTTLE_DEPOSIT * numOfBigBottles);
    }

    public static double calcGST(double cost) {
        return cost + (cost * GST);
    }

    public static Boolean xPressed() {
        return orderInProgress = false;
    }
}
