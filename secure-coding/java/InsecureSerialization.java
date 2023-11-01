import java.io.*;

public class InsecureSerialization implements Serializable {

    private static final long serialVersionUID = 1L;
    private String command;
    public String getCommand() {
        return command;
    }
    public void setCommand(String command) {
        this.command = command;
    }

    boolean isWindows = System.getProperty("os.name")
                            .toLowerCase()
                            .startsWith("windows");
    public static void main(String[] args) throws Exception {
        // Create a new object
        InsecureSerialization obj = new InsecureSerialization();
        obj.setCommand(args[0]); // Set the command to execute
        obj.executeCommand(); // Execute a command

        // Serialize the object to a byte array
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bos);
        out.writeObject(obj);
        out.close();
        System.out.println("Object serialized");
        System.out.println(bos.toByteArray());

        // Deserialize the object from the byte array
        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray())); // If serialized object is modified here, then the risk of impacting the real behaviour is critical....
        InsecureSerialization newObj = (InsecureSerialization) in.readObject();

        // The command is executed again when the object is deserialized
        newObj.executeCommand();
        System.out.println("Object deserialized");
        System.out.println(newObj.getCommand());
        if ( obj == newObj ) {
            System.out.println("Same object");
        } else {
            System.out.println("Different object");
        }
    }

    public void executeCommand() throws Exception {
        if (isWindows) {
            Runtime.getRuntime()
              .exec(String.format("cmd.exe /c %s", this.command));
        } else {
            Runtime.getRuntime()
              .exec(String.format("/bin/sh -c %s", this.command));
        }
    }

}