import java.util.Scanner;
import java.util.HashMap;

//check an item, overall inventory,



public class Hi {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Inventory inventory = new Inventory();

        HashMap<String, String> item = new HashMap<>();

        item.put("Bread", inventory.id );

        System.out.println("\n\n Quick Stock Inventory Checker \n\n -------------------------");
        System.out.print("\nInput the name of the item: ");

        String search = scanner.nextLine();

        System.out.print("Item name: " + search + " Id: " + item.get(search));

    }
}


class Inventory {

String id;

    public Inventory(){

        this.id = "a2x";

    }

}
