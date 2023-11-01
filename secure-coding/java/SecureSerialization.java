import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
// Added code to:
// 1. Ensure deserialized objects are of the expected type
// 2. Ensure deserialized objects only run allowed commands
public class SecureSerialization implements Serializable {

    private String command;
    private static final List<String> allowList = Arrays.asList("calc.exe");
    public String getCommand() {
        return command;
    }
    public void setCommand(String command) {
        this.command = command;
    }
    public static void main(String[] args) throws Exception {
        // 1. Original object runs the input from command line
        SecureSerialization obj = new SecureSerialization();
        obj.setCommand(args[0]);
        System.out.println("Command to run:"+obj.getCommand());
        obj.executeCommand();

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bos);
        out.writeObject(obj);
        out.close();

        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
        SecureSerialization newObj = (SecureSerialization) in.readObject();
        newObj.setCommand("calc.exe");
        System.out.println("Command to run:"+newObj.getCommand());
        newObj.executeCommand();
    }

    public void executeCommand() throws Exception {

        if (SecureSerialization.allowList.contains(this.command)) {
            Runtime.getRuntime().exec(this.command);
        }
        else {
            throw new Exception("Command not allowed: "+this.command);
        }
        
    }

}