package practice;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by harshitha.suresh on 31/01/2018.
 */
public class Factorial {
    public static void main(String[] args) {
        int n = 5;
        System.out.println("args = [" + factorial(n) + "]");
        System.out.println("args = [" + factorialRecursion(n) + "]");
        System.out.println("args = [" + factorialTailRecursion(n) + "]");

        Set<Integer> numbers = new HashSet<>();
        numbers.add(1);
        Set<Integer> newNumbers = Collections.unmodifiableSet(numbers);
        System.out.println(numbers);
        System.out.println(newNumbers);
        numbers.add(2);
        System.out.println(numbers);
        System.out.println(newNumbers);

    }

    private static int factorialTailRecursion(int n) {
        return factorialHelper(1, n);
    }

    private static int factorialHelper(int accumulator, int n) {
        if(n == 1) return accumulator;
        return factorialHelper(accumulator * n, n-1);
    }

    private static int factorialRecursion(int n) {
        if(n == 1){
            return 1;
        }
        else {
            return n * factorial(n-1);
        }
    }


    private static int factorial(int n) {
        int factorial = 1;
        for(int i = 1; i <= n; i++){
            factorial *= i;
        }
        return factorial;
    }
}
