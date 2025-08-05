import java.util.Scanner;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

//check an item, overall inventory,


public class Hi {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Inventory inventory = new Inventory("12ab","bscs");
        Inventory inventory2 = new Inventory("34cb","bs1s");

        List<Inventory> li = new ArrayList<Inventory>();
        HashMap<String, List<Inventory>> item = new HashMap<>();

        li.add(inventory);
        li.add(inventory2);

        item.put("Bread", li);

        System.out.println("\n\n Quick Stock Inventory Checker \n\n ------------------------------------------\n");
        System.out.println("Please select the letter of your desired option:");
        System.out.println("\n A: Check Inventory \n B: Check an Item's details\n C: Quit\n");

        System.out.print("Input:");
        
        //System.out.print("\nInput the name of the item: ");

        String search = scanner.nextLine();

        System.out.print("\nItem name: " + search);
        System.out.print("    Item desc: " + item.get(search));

    }
}


class Inventory {

String id;
String name;
    
    public Inventory (String id, String name) {

        this.id = id;
        this.name = name;

    }

    @Override
    public String toString() {

        return id + name;

    }
    

}