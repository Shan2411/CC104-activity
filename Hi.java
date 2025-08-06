import java.util.Scanner;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

//check an item, overall inventory,

/* 

Mga pre try nyo to i run pero letter b lng na option pa lng gumagana eh

*/


public class Hi {

    public static void main(String[] args) {

        while (true) {
            
            Scanner scanner = new Scanner(System.in);
            Inventory inventory = new Inventory("Expiry date: Jun 24 2090","Manufacture Date: 2025", "Production Date: 2020", "Barcode: 112039", "Product Desc: Bread", "Product Size: Size varies", "Quantity: 1200pcs, ", "Status: Fresh");
            Inventory inventory2 = new Inventory("Expiry date: 2030","Manufacture Date: 2025", "Production Date: 2021", "Barcode: 091824" ,"Product Desc: Milk", "Product Size: 1L", "Quantity: 800pcs", "Status: Fresh");

            List<Inventory> li = new ArrayList<Inventory>();
            List<Inventory> milkDesc = new ArrayList<Inventory>();

            HashMap<String, List<Inventory>> item = new HashMap<>();

            li.add(inventory);
            milkDesc.add(inventory2);

            item.put("Bread", li);
            item.put("bread", li);
            item.put("Milk", milkDesc);
            item.put("milk", milkDesc);

            System.out.println("\nQuick Stock Inventory Checker \n------------------------------------------\n");
            System.out.println("Please select the letter of your desired option:");
            System.out.println("\n A: Check Inventory \n B: Check a product's detail\n C: Quit\n");

            System.out.print("Input: ");
            String userInput = scanner.nextLine();
            //IF CONDITIONS START    

            if (userInput.equalsIgnoreCase("b")) {
                
                System.out.print("Insert the name of the Item: ");
                String search = scanner.nextLine();

                System.out.println("\nProduct Information of item " + search);
                System.out.print(item.get(search)); 
                System.out.print("\n\nDo you want to continue? [Y/N] ");
                String yesOrNo = scanner.nextLine();

                if (yesOrNo.equalsIgnoreCase("N")){break;}
                
            }

            else if (userInput.equalsIgnoreCase("a")){ 
                System.out.println("Stocked Products:\n");
                
                

            }

            else if (userInput.equalsIgnoreCase("c")){ 
                break; 
            }

            else {System.out.println("Input invalid");}

            //System.out.print("\nInput the name of the item: ");
        }
    }
}


class Inventory {

String exp;
String mnf;
String prod;
String barcode;
String prodDesc;
String prodSize;
String quantity;
String status;
    
    public Inventory (String exp, String mnf, String prod, String barcode, String prodDesc, String prodSize, String quantity, String status) {

        this.exp = exp;
        this.mnf = mnf;
        this.prod = prod;
        this.barcode = barcode;
        this.prodDesc = prodDesc;
        this.prodSize = prodSize;
        this.quantity = quantity;
        this.status = status;
    }

    @Override
    public String toString() {

        //return exp + mnf + "]\n[" + prod + barcode + "]\n[" + prodDesc + prodSize + "]\n[" + quantity + status;
        //return exp + "]\n[" + mnf + "]\n[" + prod + "]\n[" + barcode + "]\n[" + prodDesc + "]\n[" + prodSize + "]\n[" + quantity + "]\n[" + status;
        return "----------------------]\n " + exp + "\n " + mnf + "\n " + prod + "\n " + barcode + "\n " + prodDesc + "\n " + prodSize + "\n " + quantity + "\n " + status + "\n[----------------------";    
    }
    

}

