package rs.np.storage_manager_common.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import org.apache.commons.lang3.StringEscapeUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import rs.np.storage_manager_common.domain.utility.JSONPurifier;


public class ReceiverJSON {
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
	
	
	public String receiveObject() throws Exception {
		String obj = in.readLine();
		System.out.println("JSON received!");
		String jsonFixed = JSONPurifier.removeQuotesAndUnescape(obj);
		System.out.println(jsonFixed);
		
		return jsonFixed;
	}


}
