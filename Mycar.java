

public class Mycar {

    public static void main(String[] args){

        Car car = new Car("toilet");

        car.accelerate();
        car.accelerate();
        car.brake();

        car.displayStatus();
    }


}


class Car {

    int speed = 0;
    String brand;

        public Car (String brand) {
            this.brand = brand;

        }

        void accelerate() {
            speed += 10;
        }
        void brake() {
            speed -= 5;
        }

        void displayStatus () {
            System.out.println("Car: " + brand);
            System.out.println("Speed: " + speed);
        }

}