import java.util.ArrayList;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		ArrayList<ServerThread> serverThreads = new ArrayList<ServerThread>();
		try (ServerSocket serverSocket = new ServerSocket(1234)){
			while(true) {
				Socket socket = serverSocket.accept();
				ServerThread serverThread = new ServerThread(socket, serverThreads);
				serverThreads.add(serverThread);
				serverThread.start();
			}	
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}

}
