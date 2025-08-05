import java.util.Scanner;

class Test {

    void works(){

        System.out.print("Class works");

    }

}

public class App {

    static void method(){

        System.out.print("Method works!!");

    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello, World!");
        String name = scanner.nextLine();

        method();

        Test test = new Test();

        test.works();
    }

}