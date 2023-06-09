package rs.np.storage_manager_common.connection.abstraction.JSONImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import com.google.gson.Gson;

import rs.np.storage_manager_common.connection.abstraction.Receiver;

public class ReceiverJSON implements Receiver{
	@SuppressWarnings("unused")
	private Socket socket;
	private BufferedReader in;
	
	public ReceiverJSON(Socket socket) {
		this.socket = socket;
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			System.out.println("JSON input stream not initialized correctly.");
            System.out.println(e);
		}
	}
	@Override
	public<T> T receiveObject(Class<T> obj) throws Exception {
		String jsonRequest = in.readLine();
		System.out.println("JSON received!");
		System.out.println(jsonRequest);
		
		Gson gson = new Gson();
		
		return gson.fromJson(jsonRequest, obj);
	}


}
