package Default;

import java.util.Scanner;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class Hi2 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Inventory inventory = new Inventory("Expiry date: Jun 24 2090", "Manufacture Date: 2025", "Production Date: 2020", "Barcode: 112039", "Product Desc: Bread", "Product Size: Size varies", 1200, "Fresh", 30.0);
        Inventory inventory2 = new Inventory("Expiry date: 2030", "Manufacture Date: 2025", "Production Date: 2021", "Barcode: 091824", "Product Desc: Milk", "Product Size: 1L", 800, "Fresh", 50.0);

        HashMap<String, Inventory> item = new HashMap<>();
        
        item.put("bread", inventory);
        item.put("milk", inventory2);

        while (true) {
            System.out.println("\nQuick Stock Inventory Checker \n------------------------------------------");
            System.out.println("Please select the letter of your desired option:");
            System.out.println(" A: Check Inventory");
            System.out.println(" B: Check a product's detail");
            System.out.println(" C: Buy Product");
            System.out.println(" D: Quit");
            System.out.print("Input: ");
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("b")) {
                System.out.print("Insert the name of the Item: ");
                String search = scanner.nextLine();

                if (item.containsKey(search)) {
                    System.out.println("\nProduct Information of item \"" + search + "\":");
                    System.out.print(item.get(search));
                } else {
                    System.out.println("Item not found.");
                }

                System.out.print("\n\nDo you want to continue? [Y/N] ");
                String yesOrNo = scanner.nextLine();
                if (yesOrNo.equalsIgnoreCase("N")) break;

            } else if (userInput.equalsIgnoreCase("a")) {
                System.out.println("\nStocked Products:");
                for (String key : item.keySet()) {
                    System.out.println("- " + key + " (" + item.get(key).quantity + " pcs available)");
                }

            } else if (userInput.equalsIgnoreCase("c")) {
                System.out.print("Enter product name to buy: ");
                String productName = scanner.nextLine();

                if (!item.containsKey(productName)) {
                    System.out.println("Product not found.");
                    continue;
                }

                Inventory selectedItem = item.get(productName);
                System.out.println("Selected: " + selectedItem.prodDesc + " - P" + selectedItem.price + " each");
                System.out.print("Enter quantity: ");
                int qty = scanner.nextInt();

                if (qty > selectedItem.quantity) {
                    System.out.println("Not enough stock. Available: " + selectedItem.quantity);
                    scanner.nextLine(); // clear buffer
                    continue;
                }

                double total = qty * selectedItem.price;
                System.out.println("Total: P" + total);
                System.out.print("Enter cash: P");
                double cash = scanner.nextDouble();
                scanner.nextLine(); // clear buffer

                if (cash < total) {
                    System.out.println("Insufficient cash.");
                    continue;
                }

                double change = cash - total;
                selectedItem.quantity -= qty;

                // Receipt
                System.out.println("\n===== RECEIPT =====");
                System.out.println("Item: " + selectedItem.prodDesc);
                System.out.println("Quantity: " + qty);
                System.out.println("Price: P" + selectedItem.price);
                System.out.println("Total: P" + total);
                System.out.println("Cash: P" + cash);
                System.out.println("Change: P" + change);
                System.out.println("===================");

            } else if (userInput.equalsIgnoreCase("d")) {
                break;

            } else {
                System.out.println("Invalid input.");
            }
        }
        scanner.close();
    }
}


class Inventory {

    String exp;
    String mnf;
    String prod;
    String barcode;
    String prodDesc;
    String prodSize;
    int quantity;
    String status;
    double price;

    public Inventory(String exp, String mnf, String prod, String barcode, String prodDesc, String prodSize, int quantity, String status, double price) {
        this.exp = exp;
        this.mnf = mnf;
        this.prod = prod;
        this.barcode = barcode;
        this.prodDesc = prodDesc;
        this.prodSize = prodSize;
        this.quantity = quantity;
        this.status = status;
        this.price = price;
    }

    @Override
    public String toString() {
        return "----------------------\n " + exp + "\n " + mnf + "\n " + prod + "\n " + barcode + "\n " + prodDesc + "\n " + prodSize + "\n Quantity: " + quantity + "\n Status: " + status + "\n Price: P" + price + "\n----------------------";
    }
}