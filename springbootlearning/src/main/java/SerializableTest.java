import demo.models.Car;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializableTest {

  public static void main(String args[]) {
    try {
      Car carObject = new Car();
      carObject.setName("Nissan");
      carObject.setModel("Altima");
      carObject.setPrice(300000L);
      String filename = "file.ser";

      // Serialization
      // Saving of object in a file
      FileOutputStream file1 = new FileOutputStream(filename);
      ObjectOutputStream out = new ObjectOutputStream(file1);

      // Method for serialization of object
      out.writeObject(carObject);
      out.close();
      file1.close();

      System.out.println("Object has been serialized");

      Car object1 = null;

      // Deserialization
      // Reading the object from a file
      FileInputStream file = new FileInputStream(filename);
      ObjectInputStream in = new ObjectInputStream(file);
in.readByte();
      // Method for deserialization of object
      object1 = (Car) in.readObject();
      in.close();
      file.close();

      System.out.println("Object has been deserialized ");
      System.out.println("a = " + object1.getModel());
      System.out.println("b = " + object1.getPrice());
      object1.setPrice(5000000000000000000l);
      System.out.println("deserialze price :: " + object1.getPrice() + ",  serialized price :: " + carObject.getPrice());
    } catch (Exception e) {
    }
  }
}