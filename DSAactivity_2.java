import java.util.*;

// List is used for keys and hashmap is used to store multiple values maybe we can only use hashmap
// How can i use linked list with this
//Library system where i can put, remove, borrow books?

public class DSAactivity_2 {
    
   public static void main(String[] args) {
        HashMap<String, Values> hashmap = new HashMap<>();
        hashmap.put("book1", new Values("Harry Potta"));
   }

}

class Values {

   String name = "";

   Values(String name){

   }

}