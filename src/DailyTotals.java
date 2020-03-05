public class DailyTotals {
    private static double COST_PIZZA_SM = 0.00;
    private static double COST_PIZZA_LG = 0.00;
    private static double COST_SALAD = 0.00;
    private static double COST_COLA_1 = 0.00;
    private static double COST_COLA_6 = 0.00;
    private static double COST_COLA_2L = 0.00;
    private static int NUMBER_PIZZA_SM = 0;
    private static int NUMBER_PIZZA_LG = 0;
    private static int NUMBER_SALAD = 0;
    private static int NUMBER_COLA_1 = 0;
    private static int NUMBER_COLA_6 = 0;
    private static int NUMBER_COLA_2L = 0;
    private static int NUMBER_CUSTOMERS = 0;


    public DailyTotals() {

    }

    public int getNumberPizzaSm() { return this.NUMBER_PIZZA_SM; }
    public int getNumberPizzaLg() { return this.NUMBER_PIZZA_LG; }
    public int getNumberSalad() { return this.NUMBER_SALAD; }
    public int getNumberCola1() { return this.NUMBER_COLA_1; }
    public int getNumberCola6() { return this.NUMBER_COLA_6; }
    public int getNumberCola2l() { return this.NUMBER_COLA_2L; }
    public int getNumberCustomers() { return this.NUMBER_CUSTOMERS; }

    public void setNumberPizzaSm(int numberPizzaSm) { this.NUMBER_PIZZA_SM += numberPizzaSm; }
    public void setNumberPizzaLg(int numberPizzaLg) { this.NUMBER_PIZZA_LG += numberPizzaLg; }
    public void setNumberSalad(int numberSalad) { this.NUMBER_SALAD += numberSalad; }
    public void setNumberCola1(int numberCola1) { this.NUMBER_COLA_1 += numberCola1; }
    public void setNumberCola6(int numberCola6) { this.NUMBER_COLA_6 += numberCola6; }
    public void setNumberCola2l(int numberCola2l) { this.NUMBER_COLA_2L += numberCola2l; }
    public void setNumberCustomers() { this.NUMBER_CUSTOMERS += 1; }

    public void setCostPizzaSm(double costPizzaSm) { this.COST_PIZZA_SM += costPizzaSm; }
    public void setCostPizzaLg(double costPizzaLg) { this.COST_PIZZA_LG += costPizzaLg; }
    public void setCostSalad(double costSalad) { this.COST_SALAD += costSalad; }
    public void setCostCola1(double costCola1) { this.COST_COLA_1 += costCola1; }
    public void setCostCola6(double costCola6) { this.COST_COLA_6 += costCola6; }
    public void setCostCola2l(double cola2l) { this.COST_COLA_2L += cola2l; }

    public double revenueTotal() {
        return COST_PIZZA_SM + COST_PIZZA_LG + COST_SALAD + COST_COLA_1 + COST_COLA_6 + COST_COLA_2L;
    }
}
