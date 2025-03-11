import java.util.Scanner;

public class InterestCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input principal amount, rate of interest, and time period
        System.out.print("Enter Principal Amount: ");
        double principal = scanner.nextDouble();

        System.out.print("Enter Rate of Interest (in %): ");
        double rate = scanner.nextDouble();

        System.out.print("Enter Time (in years): ");
        double time = scanner.nextDouble();

        // Calculate Simple Interest
        double simpleInterest = (principal * rate * time) / 100;
        System.out.println("Simple Interest: " + simpleInterest);

        // Calculate Compound Interest
        System.out.print("Enter number of times interest is compounded per year: ");
        int n = scanner.nextInt();
        double compoundInterest = principal * Math.pow((1 + rate / (100 * n)), n * time) - principal;
        System.out.println("Compound Interest: " + compoundInterest);

        scanner.close();
    }
}
