package Default;

import java.util.Scanner;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class Hi2 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        HashMap<String, Inventory> item = new HashMap<>();

        Inventory inventory4 = new Inventory(
            "Expiry Date: May 1, 2028",
            "Manufacture Date: March 10, 2025",
            "Production Date: March 8, 2025",
            "Barcode: 4801668501056",
            "Product Desc: Healthy tuna flakes in vegetable oil.",
            "Product Size: 180g can",
            100,
            "Fresh",
            26.0
        );

        Inventory inventory5 = new Inventory(
            "Expiry Date: July 30, 2027",
            "Manufacture Date: April 18, 2025",
            "Production Date: April 15, 2025",
            "Barcode: 4800145011206",
            "Product Desc: Spicy sardines perfect with rice.",
            "Product Size: 155g can",
            25,
            "Fresh",
            21.0
        );

        Inventory inventory = new Inventory(
            "Expiry Date: March 5, 2028",
            "Manufacture Date: August 12, 2025",
            "Production Date: August 10, 2025",
            "Barcode: 4801003001255",
            "Product Desc: Premium corned beef with natural beef strands.",
            "Product Size: 380g can",
            190,
            "Fresh",
            41.0
        );
        Inventory inventory3 = new Inventory(
            "Expiry date: August 15, 2027",
            "Manufacture Date: February 10, 2025",
            "Production Date: February 5, 2025",
            "Barcode: 4806517440029",
            "Product Desc: Premium sardines in rich tomato sauce.",
            "Product Size: 155g can",
            90,
            "Fresh",
            29.90
        );

        Inventory inventory2 = new Inventory(
            "Expiry Date: September 18, 2026",
            "Manufacture Date: January 5, 2025",
            "Production Date: January 1, 2025",
            "Barcode: 4897000065337",
            "Product Desc: Chinese-style pork luncheon meat.",
            "Product Size: 397g can",
            450,
            "Fresh",
            33.25
        );


        
        item.put("corned beef", inventory);
        item.put("century tuna", inventory4);
        item.put("young town sardines", inventory3);
        item.put("luncheon meat", inventory2);
        item.put("ligo sardines", inventory5);
        


        while (true) {
            System.out.println("\nQuick Stock Inventory Checker \n------------------------------------------");
            System.out.println("Please select the letter of your desired option:");
            System.out.println(" A: Check Inventory");
            System.out.println(" B: Buy Product");
            System.out.println(" C: Quit");
            System.out.print("Input: ");
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("a")) {
                System.out.println("\nStocked Products:");
                for (String key : item.keySet()) {
                    System.out.println("- " + key + "\n" + item.get(key) + "\n");

                }

                System.out.print("Do you want to continue? [Y/N] ");
                String yesOrNo = scanner.nextLine();
                if (yesOrNo.equalsIgnoreCase("N")) break;

            } else if (userInput.equalsIgnoreCase("b")) {
                System.out.println("\nProducts to choose from: \n" + item.keySet() + "\n");
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
                System.out.println("Thank you for your purchase!");
                System.out.println("===================");

            } else if (userInput.equalsIgnoreCase("c")) {
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