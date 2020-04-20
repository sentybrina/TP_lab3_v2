import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;

public class Connection {

    private final Socket socket;
    private final BufferedReader reader;
    private final PrintWriter writer;

    public Connection(Socket socket) throws IOException {
        this.socket = socket;
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(socket.getOutputStream());
        writer.flush();
    }

    public <T> T  readObject(Class<T> classOfT) throws IOException {
        String str;
        do{
            str = reader.readLine();
        }while (str == null || str.isEmpty());
        return new Gson().fromJson(str, classOfT);
    }

    public void send(Object o){
        writer.println(new Gson().toJson(o));
        writer.print("\n");
        writer.flush();
    }

    public void disconnect(){
        close(reader);
        close(writer);
        close(socket);
    }

    private void close(Closeable c){
        try{
            c.close();
        } catch (IOException e) {
            System.err.println(e.getClass() + ": " + e.getMessage());
        }
    }
}
