import java.util.Scanner;

public class ConsoleCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.print("Enter first number: ");
                double num1 = Double.parseDouble(scanner.nextLine());
                System.out.print("Enter an operator (+, -, *, /): ");
                char operator = scanner.nextLine().charAt(0);
                System.out.print("Enter second number: ");
                double num2 = Double.parseDouble(scanner.nextLine());

                double result;


                switch (operator) {
                    case '+':
                        result = num1 + num2;
                        break;
                    case '-':
                        result = num1 - num2;
                        break;
                    case '*':
                        result = num1 * num2;
                        break;
                    case '/':
                        if (num2 == 0) {
                            System.out.println("Error: Division by zero is not allowed.");
                            continue;
                        }
                        result = num1 / num2;
                        break;
                    default:
                        System.out.println("Error: Invalid operator. Please use +, -, *, or /.");
                        continue;
                }

                
                System.out.println("Result: " + result);

            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input. Please enter numeric values.");
            }

            System.out.print("Do you want to perform another calculation? (yes/no): ");
            String choice = scanner.nextLine().trim().toLowerCase();
            if (!choice.equals("yes")) {
                System.out.println("Goodbye!");
                break;
            }
        }

        scanner.close();
    }
}