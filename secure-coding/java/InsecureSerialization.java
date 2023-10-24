import java.io.*;

public class InsecureSerialization implements Serializable {
    public static void main(String[] args) throws Exception {
        // Create a new object
        InsecureSerialization obj = new InsecureSerialization();
        obj.executeCommand(args[0]); // Execute a command

        // Serialize the object to a byte array
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bos);
        out.writeObject(obj);

        // Deserialize the object from the byte array
        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
        InsecureSerialization newObj = (InsecureSerialization) in.readObject();

        // The command is executed again when the object is deserialized
        newObj.executeCommand(args[0]);
    }

    public void executeCommand(String command) throws Exception {
        Runtime.getRuntime().exec(command);
    }

}