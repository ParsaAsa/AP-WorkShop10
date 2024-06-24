import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread extends Thread{
	private Socket socket;
	private ArrayList<ServerThread> serverThreads;
	private PrintWriter output;
	
	public ServerThread(Socket socket, ArrayList<ServerThread> serverThreads) {
		this.socket = socket;
		this.serverThreads = serverThreads;
	}
	
	@Override
	public void run() {
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			output = new PrintWriter(socket.getOutputStream(),true);
			
			while(true) {
				String outputString = input.readLine();
				if (outputString.equals("exit")){
					break;
				}
				sendToClients(outputString);
				System.out.println("Server recieved: "+outputString);
				
			}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	private void sendToClients(String outputString) {
		for (ServerThread sT : serverThreads) {
			sT.output.println(outputString);
		}
	}
}
