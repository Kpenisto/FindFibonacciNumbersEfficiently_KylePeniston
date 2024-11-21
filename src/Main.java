import java.util.Scanner;
import java.math.BigInteger;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Fibonacci Comparison Program");
        System.out.println("1. Recursive Method");
        System.out.println("2. Dynamic Programming Method");
        System.out.println("3. Matrix Exponentiation Method");
        System.out.println("Select a method (1, 2, or 3): ");

        int choice = scanner.nextInt();
        System.out.println("Enter a number n for Fibonacci calculation: ");
        int n = scanner.nextInt();

        long startTime, endTime;
        switch (choice) {
            case 1: //Recursive Method
                startTime = System.currentTimeMillis();
                long resultRec = fibRecursive(n);
                endTime = System.currentTimeMillis();
                System.out.println("Fibonacci(" + n + ") = " + resultRec);
                System.out.println("Execution time: " + (endTime - startTime) + " milliseconds");
                break;

            case 2: //Dynamic Programming Method
                startTime = System.currentTimeMillis();
                BigInteger resultDyn = fibDynamic(n);
                endTime = System.currentTimeMillis();
                System.out.println("Fibonacci(" + n + ") = " + resultDyn);
                System.out.println("Execution time: " + (endTime - startTime) + " milliseconds");
                break;

            case 3: //Matrix Exponentiation Method
                startTime = System.currentTimeMillis();
                BigInteger resultMat = fibMatrix(n);
                endTime = System.currentTimeMillis();
                System.out.println("Fibonacci(" + n + ") = " + resultMat);
                System.out.println("Execution time: " + (endTime - startTime) + " milliseconds");
                break;

            default:
                System.out.println("Invalid choice. Please select 1, 2, or 3.");
        }

        scanner.close();
    }

    //Recursive Method
    public static long fibRecursive(long n) {
        if (n <= 1) return n;
        return fibRecursive(n - 1) + fibRecursive(n - 2);
    }

    //Dynamic Programming Method
    public static BigInteger fibDynamic(int n) {
        if (n == 0) return BigInteger.ZERO;
        if (n == 1) return BigInteger.ONE;

        BigInteger[] fib = new BigInteger[n + 1];
        fib[0] = BigInteger.ZERO;
        fib[1] = BigInteger.ONE;

        for (int i = 2; i <= n; i++) {
            fib[i] = fib[i - 1].add(fib[i - 2]);
        }
        return fib[n];
    }

    //Matrix Multiplication for Matrix Method
    public static BigInteger[][] multiplyMatrix(BigInteger[][] a, BigInteger[][] b) {
        BigInteger[][] result = new BigInteger[2][2];
        result[0][0] = a[0][0].multiply(b[0][0]).add(a[0][1].multiply(b[1][0]));
        result[0][1] = a[0][0].multiply(b[0][1]).add(a[0][1].multiply(b[1][1]));
        result[1][0] = a[1][0].multiply(b[0][0]).add(a[1][1].multiply(b[1][0]));
        result[1][1] = a[1][0].multiply(b[0][1]).add(a[1][1].multiply(b[1][1]));
        return result;
    }

    //Matrix Exponentiation
    public static BigInteger[][] powerMatrix(BigInteger[][] matrix, int n) {
        if (n == 1) return matrix;

        if (n % 2 == 0) {
            BigInteger[][] halfPower = powerMatrix(matrix, n / 2);
            return multiplyMatrix(halfPower, halfPower);
        } else {
            return multiplyMatrix(matrix, powerMatrix(matrix, n - 1));
        }
    }

    //Matrix Method
    public static BigInteger fibMatrix(int n) {
        if (n == 0) return BigInteger.ZERO;
        if (n == 1) return BigInteger.ONE;

        BigInteger[][] matrix = {{BigInteger.ZERO, BigInteger.ONE}, {BigInteger.ONE, BigInteger.ONE}};
        BigInteger[][] result = powerMatrix(matrix, n - 1);
        return result[1][1];
    }
}