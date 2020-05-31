package machine;
import java.util.Scanner;

enum MachineState {
    CHOOSING_ACTION,
    CHOOSING_COFFEE,
    FILL_WATER,
    FILL_MILK,
    FILL_BEANS,
    FILL_CUPS,
}

public class CoffeeMachine {

    public static boolean toggle = true;
    public static MachineState state = MachineState.CHOOSING_ACTION;
    public static int water = 400;
    public static int milk = 540;
    public static int beans = 120;
    public static int cups = 9;
    public static int money = 550;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nWrite action (buy, fill, take, remaining, exit): ");
        while (toggle) {
            input(scanner.next());
        }
    }

    public static void input(String command) {
        switch (state) {
            case CHOOSING_ACTION:
                selectAction(command);
                break;
            case CHOOSING_COFFEE:
                buy(command);
                break;
            case FILL_WATER:
            case FILL_MILK:
            case FILL_BEANS:
            case FILL_CUPS:
                fill(command);
                break;
            default:
        }
    }

    public static void status() {
        System.out.println("\nThe coffee machine has:");
        System.out.println(water + " of water");
        System.out.println(milk + " of milk");
        System.out.println(beans + " of coffee beans");
        System.out.println(cups + " of disposable cups");
        System.out.println("$" + money + " of money");

        state = MachineState.CHOOSING_ACTION;
        System.out.println("\n\nWrite action (buy, fill, take, remaining, exit): ");
    }

    public static void selectAction(String command) {
        String action = command;
        switch (action) {
            case "buy":
                state = MachineState.CHOOSING_COFFEE;
                System.out.println("\nWhat do you want to buy? 1 - espresso 2 - latte, 3 - cappuccino, back - to main menu: ");
                break;
            case "fill":
                state = MachineState.FILL_WATER;
                System.out.println("Write how many ml of water do you want to add: ");
                break;
            case "take":
                take();
                break;
            case "remaining":
                status();
                break;
            case "exit":
                toggle = false;
                break;
            default:
                break;
        }
    }

    public static void successfulCoffee() {
        System.out.println("I have enough resources, making you a coffee!");
        state = MachineState.CHOOSING_ACTION;
        System.out.println("\n\nWrite action (buy, fill, take, remaining, exit): ");
    }

    public static void noCoffee() {
        System.out.println("Sorry, not enough ingredients!");
        state = MachineState.CHOOSING_ACTION;
        System.out.println("\n\nWrite action (buy, fill, take, remaining, exit): ");
    }

    public static void buy(String command) {
        switch (command) {
            case "1":
                if (water >= 250 && beans >= 16 && cups >= 1) {
                    water -= 250;
                    beans -= 16;
                    cups -= 1;
                    money += 4;
                    successfulCoffee();
                } else {
                    noCoffee();
                }
                break;
            case "2":
                if (water >= 350 && milk >= 75 && beans >= 20 && cups >= 1) {
                    water -= 350;
                    milk -= 75;
                    beans -= 20;
                    cups -= 1;
                    money += 7;
                    successfulCoffee();
                } else {
                    noCoffee();
                }
                break;
            case "3":
                if (water >= 200 && milk >= 100 && beans >= 12 && cups >= 1) {
                    water -= 200;
                    milk -= 100;
                    beans -= 12;
                    cups -= 1;
                    money += 6;
                    successfulCoffee();
                } else {
                    noCoffee();
                }
                break;
            default:
                state = MachineState.CHOOSING_ACTION;
                System.out.println("\n\nWrite action (buy, fill, take, remaining, exit): ");
        }
    }

    public static void fill(String command) {
        switch(state) {
            case FILL_WATER:
                int fillWater = Integer.parseInt(command);
                water += fillWater;
                state = MachineState.FILL_MILK;
                System.out.println("\nWrite how many ml of milk do you want to add: ");
                break;
            case FILL_MILK:
                int fillMilk = Integer.parseInt(command);
                milk += fillMilk;
                state = MachineState.FILL_BEANS;
                System.out.println("Write how many grams of coffee beans do you want to add: ");
                break;
            case FILL_BEANS:
                int fillBeans = Integer.parseInt(command);
                beans += fillBeans;
                state = MachineState.FILL_CUPS;
                System.out.println("Write how many disposable cups of coffee do you want to add: ");
                break;
            case FILL_CUPS:
                int fillCups = Integer.parseInt(command);
                cups += fillCups;
                state = MachineState.CHOOSING_ACTION;
                System.out.println("\n\nWrite action (buy, fill, take, remaining, exit): ");
                break;
            default:
        }
    }

    public static void take() {
        System.out.println("I gave you $" + money);
        money = 0;
        state = MachineState.CHOOSING_ACTION;
        System.out.println("\n\nWrite action (buy, fill, take, remaining, exit): ");
    }
}
