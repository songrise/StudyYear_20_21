import java.io.*;
import java.net.*;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = null;
        Socket cs = null;
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            ss = new ServerSocket(3333);
        } catch (IOException e) {
            System.out.println("Could not listen on port:3333");
            System.exit(1);
        }
        try {
            cs = ss.accept();
        } catch (IOException e) {
            System.out.println("Accept failed:3333");
            System.exit(1);
        }
        out = new PrintWriter(cs.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(cs.getInputStream()));
        String clientInput;
        while (true) {
            clientInput = in.readLine();
            if (clientInput == null)
                break;
            out.println(clientInput);
        }
        out.close();
        in.close();
        ss.close();
        cs.close();
    }
}