import java.util.Random;
import java.util.Scanner;

public class Task_1 {

    public static void main(String[] args) {

        int min, max, range, guess = 0;

        Scanner sc = new Scanner(System.in);

        System.out.println("Number Game");

        System.out.println("Enter Starting range:");
        min = sc.nextInt();

        System.out.println("Enter Starting range:");
        max = sc.nextInt();

        Random rn = new Random();
        range = max - min + 1;

        int randomNum = rn.nextInt(range) + min;

        System.out.println(randomNum);

        while (true) {

            guess = sc.nextInt();

            if (randomNum == guess) {
                System.out.println("Correct guess !!");
                break;
            }

            if (randomNum < guess) {
                System.out.println("guess is high");
            }

            if (randomNum > guess) {
                System.out.println("guess is low");
            }

        }

        sc.close();
    }

}
