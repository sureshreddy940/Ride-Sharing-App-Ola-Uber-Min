import java.util.Scanner;
class InvalidRideTypeException extends Exception {
    public InvalidRideTypeException(String message) {
        super(message);
    }
}
abstract class Ride {
    private String driverName;
    private String vehicleNumber;
    protected double distance;

    public Ride(String driverName, String vehicleNumber, double distance) {
        this.driverName = driverName;
        this.vehicleNumber = vehicleNumber;
        this.distance = distance;
    }

    public String getDriverName() {
        return driverName;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public double getDistance() {
        return distance;
    }

    public abstract double calculateFare();
}

class BikeRide extends Ride {
    public BikeRide(String driverName, String vehicleNumber, double distance) {
        super(driverName, vehicleNumber, distance);
    }

    @Override
    public double calculateFare() {
        return distance * 10;
    }
}

class CarRide extends Ride {
    public CarRide(String driverName, String vehicleNumber, double distance) {
        super(driverName, vehicleNumber, distance);
    }

    @Override
    public double calculateFare() {
        return distance * 20;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            String rideType = sc.nextLine().trim().toLowerCase();
            double distance = Double.parseDouble(sc.nextLine().trim());

            if (distance <= 0) {
                System.out.println("Distance must be greater than 0.");
                return;
            }

            Ride ride;
            switch (rideType) {
                case "bike":
                    ride = new BikeRide("Rajesh Kumar", "GJ01AB1234", distance);
                    break;
                case "car":
                    ride = new CarRide("Priya Mehta", "GJ01XY5678", distance);
                    break;
                default:
                    throw new InvalidRideTypeException("Invalid ride type: " + rideType);
            }

            System.out.println("Driver: " + ride.getDriverName());
            System.out.println("Vehicle No: " + ride.getVehicleNumber());
            System.out.println("Distance: " + ride.getDistance() + " km");
            System.out.println("Fare: ₹" + ride.calculateFare());

        } catch (InvalidRideTypeException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid distance input.");
        } finally {
            sc.close();
        }
    }
}
