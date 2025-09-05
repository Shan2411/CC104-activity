import java.util.*;

public class DSAactivity {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, Inventory> item = new HashMap<>();
        HashMap<String, String> barcodeToName = new HashMap<>();
        List<CartItem> cart = new ArrayList<>();
        double cartTotal = 0.0;

        // Inventory setup
        item.put("corned beef", new Inventory(200, "Expiry Date: March 5, 2028", "Manufacture Date: August 12, 2025", "Production Date: August 10, 2025", "480100", "(Corned Beef) Premium corned beef with natural beef strands.", "380g can", "Fresh", 41.0));
        item.put("century tuna", new Inventory(90, "Expiry Date: May 1, 2028", "Manufacture Date: March 10, 2025", "Production Date: March 8, 2025", "480166", "(Century Tuna) Healthy tuna flakes in vegetable oil.", "180g can", "Fresh", 26.0));
        item.put("young town sardines", new Inventory(100, "Expiry date: August 15, 2027", "Manufacture Date: February 10, 2025", "Production Date: February 5, 2025", "480651", "(Youngs Town Sardines) Premium sardines in rich tomato sauce.", "155g can", "Fresh", 29.90));
        item.put("luncheon meat", new Inventory(330, "Expiry Date: September 18, 2026", "Manufacture Date: January 5, 2025", "Production Date: January 1, 2025", "489700", "(Luncheon Meat) Chinese-style pork luncheon meat.", "397g can", "Fresh", 33.25));
        item.put("ligo sardines", new Inventory(20, "Expiry Date: July 30, 2027", "Manufacture Date: April 18, 2025", "Production Date: April 15, 2025", "480014", "(Mega Sardines) Spicy sardines perfect with rice.", "155g can", "Fresh", 21.0));

        for (Map.Entry<String, Inventory> entry : item.entrySet()) {
            barcodeToName.put(entry.getValue().barcode, entry.getKey());
        }

        while (true) {
            System.out.println("\nInventory Checker and Ordering System\n------------------------------------------");
            System.out.println("A: Check Inventory");
            System.out.println("B: Add to Cart");
            System.out.println("C: View Cart / Checkout");
            System.out.println("D: Quit");
            System.out.print("Input: ");
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("a")) {
                System.out.println("\nStocked Products:");
                for (Map.Entry<String, Inventory> entry : item.entrySet()) {
                    System.out.println("- " + entry.getKey() + "\n" + entry.getValue() + "\n");
                }

            } else if (userInput.equalsIgnoreCase("b")) {
                System.out.println("\nAvailable Product Barcodes:");
                for (Inventory inv : item.values()) {
                    System.out.println(inv.barcode + " - " + inv.prodDesc + " (Stock: " + inv.stock + ")");
                }

                System.out.print("Enter Product ID (Barcode) to add to cart: ");
                String barcode = scanner.nextLine();

                if (!barcodeToName.containsKey(barcode)) {
                    System.out.println("Product ID not found.");
                    continue;
                }

                String productName = barcodeToName.get(barcode);
                Inventory selectedItem = item.get(productName);

                System.out.println("Selected: " + selectedItem.prodDesc + " - P" + selectedItem.price + " each");
                System.out.print("Enter quantity: ");
                String qtyInput = scanner.nextLine();
                int qty;

                try {
                    qty = Integer.parseInt(qtyInput);
                    if (qty <= 0) {
                        System.out.println("Invalid");
                        continue;
                    }
                    if (qty > selectedItem.stock) {
                        System.out.println("Quantity exceeded stock. Available: " + selectedItem.stock);
                        continue;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid");
                    continue;
                }

                selectedItem.stock -= qty;
                cart.add(new CartItem(productName, selectedItem.mnf, selectedItem.exp, selectedItem.price, qty));
                double subtotal = selectedItem.price * qty;
                cartTotal += subtotal;
                System.out.println("Added to cart: " + productName + " x" + qty);
                System.out.printf("Cart Total: P%.2f\n", cartTotal);

            } else if (userInput.equalsIgnoreCase("c")) {
                if (cart.isEmpty()) {
                    System.out.println("Cart is empty.");
                    continue;
                }

                while (true) {
                    System.out.println("\n===== CART =====");
                    double grandTotal = 0;
                    for (int i = 0; i < cart.size(); i++) {
                        CartItem c = cart.get(i);
                        double total = c.price * c.quantity;
                        grandTotal += total;
                        System.out.println((i + 1) + ". Name: " + c.name + "\n   Exp: " + c.exp + "\n   Mnf: " + c.mnf + "\n   Quantity: " + c.quantity + " | Price: P" + c.price + " | Subtotal: P" + total + "\n");
                    }
                    System.out.printf("Total to Pay: P%.2f\n", grandTotal);

                    System.out.print("\n[Y] Checkout | [R] Remove Item | [N] Go Back: ");
                    String decision = scanner.nextLine();

                    if (decision.equalsIgnoreCase("n")) {
                        break;

                    } else if (decision.equalsIgnoreCase("r")) {
                        System.out.print("Enter item number to remove (0 to cancel): ");
                        String removeInput = scanner.nextLine();
                        int removeIndex;
                        try {
                            removeIndex = Integer.parseInt(removeInput);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid selection.");
                            continue;
                        }

                        if (removeIndex == 0) {
                            continue;
                        }
                        if (removeIndex < 1 || removeIndex > cart.size()) {
                            System.out.println("Invalid selection.");
                        } else {
                            CartItem removed = cart.remove(removeIndex - 1);
                            Inventory returned = item.get(removed.name);
                            returned.stock += removed.quantity;
                            cartTotal -= removed.price * removed.quantity;
                            System.out.println("Removed: " + removed.name + " x" + removed.quantity);
                        }

                    } else if (decision.equalsIgnoreCase("y")) {
                        System.out.print("Enter cash: P");
                        String cashInput = scanner.nextLine();
                        double cash;
                        try {
                            cash = Double.parseDouble(cashInput);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid cash input.");
                            continue;
                        }

                        if (cash < grandTotal) {
                            System.out.println("Insufficient cash.");
                            continue;
                        }

                        double change = cash - grandTotal;
                        System.out.println("\n===== RECEIPT =====");
                        for (CartItem c : cart) {
                            System.out.println(" x" + c.quantity + " - " + c.name + " = P" + (c.price * c.quantity));
                        }
                        System.out.println("Total: P" + grandTotal);
                        System.out.println("Cash: P" + cash);
                        System.out.println("Change: P" + change);
                        System.out.println("===================");

                        cart.clear();
                        cartTotal = 0;
                        break;

                    } else {
                        System.out.println("Invalid input.");
                    }
                }

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
    int stock;
    String status;
    double price;

    public Inventory(int stock, String exp, String mnf, String prod, String barcode, String prodDesc, String prodSize, String status, double price) {
        this.stock = stock;
        this.exp = exp;
        this.mnf = mnf;
        this.prod = prod;
        this.barcode = barcode;
        this.prodDesc = prodDesc;
        this.prodSize = prodSize;
        this.status = status;
        this.price = price;
    }

    @Override
    public String toString() {
        return "----------------------\n Stock: " + stock + "\n " + exp + "\n " + mnf + "\n " + prod + "\n Barcode: " + barcode + "\n Description: " + prodDesc + "\n Size: " + prodSize + "\n Status: " + status + "\n Price: P" + price + "\n----------------------";
    }
}

class CartItem {
    String name;
    String exp;
    String mnf;
    double price;
    int quantity;

    public CartItem(String name, String exp, String mnf, double price, int quantity) {
        this.name = name;
        this.exp = exp;
        this.mnf = mnf;
        this.price = price;
        this.quantity = quantity;
    }
}