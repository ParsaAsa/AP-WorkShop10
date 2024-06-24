import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		try(Socket socket = new Socket("localhost",1234)){
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter output = new PrintWriter(socket.getOutputStream(),true);
			Scanner sc = new Scanner(System.in);
			String userInput;
			String response;
			String userName = "noName";
			ClientThread clientThread = new ClientThread(socket);
			clientThread.start();
			do {
				if (userName.equals("noName")) {
					System.out.println("Enter your name: ");
					userInput = sc.nextLine();
					userName = userInput;
				}
				else {
					String message = (userName+": ");
					userInput = sc.nextLine();
					output.println(message+userInput);
				}
			}
			while(!userInput.equals("exit"));
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}

}
