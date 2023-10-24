import java.io.*;
// Add code to:
// 1. Ensure deserialized objects are of the expected type
// 2. Ensure deserialized objects are not malicious
public class SecureSerialization implements Serializable {
    public static void main(String[] args) throws Exception {
        
        SecureSerialization obj = new SecureSerialization();
        obj.executeCommand(args[0]); 

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bos);
        out.writeObject(obj);

        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
        SecureSerialization newObj = (SecureSerialization) in.readObject();

        newObj.executeCommand(args[0]);
    }

    public void executeCommand(String command) throws Exception {
        Runtime.getRuntime().exec(command);
    }

}