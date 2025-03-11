import java.util.Scanner;

// Abstract class Account
abstract class Account {
    protected double interestRate;
    protected double amount;

    public abstract double calculateInterest();
}

// Class for Fixed Deposit (FD) accounts
class FDAccount extends Account {
    private int noOfDays;
    private int ageOfACHolder;

    public FDAccount(double amount, int noOfDays, int ageOfACHolder) {
        if (amount < 0 || noOfDays < 0 || ageOfACHolder < 0) {
            throw new IllegalArgumentException("Invalid input values. Please enter non-negative values.");
        }
        this.amount = amount;
        this.noOfDays = noOfDays;
        this.ageOfACHolder = ageOfACHolder;
    }

    @Override
    public double calculateInterest() {
        if (amount < 1_00_000) { // Less than 1 crore
            if (noOfDays >= 7 && noOfDays <= 14) interestRate = (ageOfACHolder >= 60) ? 5.0 : 4.5;
            else if (noOfDays >= 15 && noOfDays <= 29) interestRate = (ageOfACHolder >= 60) ? 5.25 : 4.75;
            else if (noOfDays >= 30 && noOfDays <= 45) interestRate = (ageOfACHolder >= 60) ? 6.0 : 5.5;
            else if (noOfDays >= 46 && noOfDays <= 60) interestRate = (ageOfACHolder >= 60) ? 7.5 : 7.0;
            else if (noOfDays >= 61 && noOfDays <= 184) interestRate = (ageOfACHolder >= 60) ? 8.0 : 7.5;
            else if (noOfDays >= 185 && noOfDays <= 365) interestRate = (ageOfACHolder >= 60) ? 8.5 : 8.0;
        } else { // 1 crore or more
            if (noOfDays >= 7 && noOfDays <= 14) interestRate = 6.5;
            else if (noOfDays >= 15 && noOfDays <= 29) interestRate = 6.75;
            else if (noOfDays >= 30 && noOfDays <= 45) interestRate = 6.75;
            else if (noOfDays >= 46 && noOfDays <= 60) interestRate = 8.0;
            else if (noOfDays >= 61 && noOfDays <= 184) interestRate = 8.5;
            else if (noOfDays >= 185 && noOfDays <= 365) interestRate = 10.0;
        }
        return (amount * interestRate) / 100;
    }
}

// Class for Savings Bank (SB) accounts
class SBAccount extends Account {
    private String accountType;

    public SBAccount(double amount, String accountType) {
        if (amount < 0) {
            throw new IllegalArgumentException("Invalid amount. Please enter a non-negative value.");
        }
        this.amount = amount;
        this.accountType = accountType;
    }

    @Override
    public double calculateInterest() {
        if (accountType.equalsIgnoreCase("Normal")) {
            interestRate = 4.0;
        } else if (accountType.equalsIgnoreCase("NRI")) {
            interestRate = 6.0;
        } else {
            throw new IllegalArgumentException("Invalid account type. Choose either 'Normal' or 'NRI'.");
        }
        return (amount * interestRate) / 100;
    }
}

// Class for Recurring Deposit (RD) accounts
class RDAccount extends Account {
    private int noOfMonths;
    private double monthlyAmount;
    private int ageOfACHolder;

    public RDAccount(double monthlyAmount, int noOfMonths, int ageOfACHolder) {
        if (monthlyAmount < 0 || noOfMonths < 0 || ageOfACHolder < 0) {
            throw new IllegalArgumentException("Invalid input values. Please enter non-negative values.");
        }
        this.monthlyAmount = monthlyAmount;
        this.noOfMonths = noOfMonths;
        this.ageOfACHolder = ageOfACHolder;
    }

    @Override
    public double calculateInterest() {
        if (ageOfACHolder >= 60) {
            if (noOfMonths == 6) interestRate = 8.0;
            else if (noOfMonths == 9) interestRate = 8.25;
            else if (noOfMonths == 12) interestRate = 8.5;
            else if (noOfMonths == 15) interestRate = 8.75;
            else if (noOfMonths == 18) interestRate = 9.0;
            else if (noOfMonths == 21) interestRate = 9.25;
        } else {
            if (noOfMonths == 6) interestRate = 7.5;
            else if (noOfMonths == 9) interestRate = 7.75;
            else if (noOfMonths == 12) interestRate = 8.0;
            else if (noOfMonths == 15) interestRate = 8.25;
            else if (noOfMonths == 18) interestRate = 8.5;
            else if (noOfMonths == 21) interestRate = 8.75;
        }
        return (monthlyAmount * noOfMonths * interestRate) / 100;
    }
}

// Main Class
public class InterestCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nSelect the option:");
            System.out.println("1. Interest Calculator – SB");
            System.out.println("2. Interest Calculator – FD");
            System.out.println("3. Interest Calculator – RD");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            try {
                switch (choice) {
                    case 1: // SB Interest
                        System.out.print("Enter the average amount in your account: ");
                        double sbAmount = scanner.nextDouble();
                        System.out.print("Enter account type (Normal/NRI): ");
                        String accountType = scanner.next();
                        SBAccount sbAccount = new SBAccount(sbAmount, accountType);
                        System.out.println("Interest gained: Rs. " + sbAccount.calculateInterest());
                        break;

                    case 2: // FD Interest
                        System.out.print("Enter the FD amount: ");
                        double fdAmount = scanner.nextDouble();
                        System.out.print("Enter the number of days: ");
                        int noOfDays = scanner.nextInt();
                        System.out.print("Enter your age: ");
                        int age = scanner.nextInt();
                        FDAccount fdAccount = new FDAccount(fdAmount, noOfDays, age);
                        System.out.println("Interest gained: Rs. " + fdAccount.calculateInterest());
                        break;

                    case 3: // RD Interest
                        System.out.print("Enter the monthly deposit amount: ");
                        double rdAmount = scanner.nextDouble();
                        System.out.print("Enter the number of months: ");
                        int noOfMonths = scanner.nextInt();
                        System.out.print("Enter your age: ");
                        int rdAge = scanner.nextInt();
                        RDAccount rdAccount = new RDAccount(rdAmount, noOfMonths, rdAge);
                        System.out.println("Interest gained: Rs. " + rdAccount.calculateInterest());
                        break;

                    case 4: // Exit
                        System.out.println("Exiting the program. Goodbye!");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
