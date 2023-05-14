package rs.np.storage_manager_common.connection.abstraction.JSONImpl;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import rs.np.storage_manager_common.connection.abstraction.Response;

public class ResponseJSON implements Response{
	private String response;
	@SerializedName(value = "exception_content")
	private String exMessage;
	
	public ResponseJSON() {
	}
	
	public ResponseJSON(String response, String exMessage) {
		super();
		this.response = response;
		this.exMessage = exMessage;
	}
	@Override
	public <T> List<T> getResponse(Class<T> className, boolean isList) {
		Gson gson = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd").create();
		return Arrays.asList(gson.fromJson(response, (Type) Array.newInstance(className, 0).getClass()));
	}
	@Override
	public <T> T getResponse(Class<T> className) {
		Gson gson = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd").create();
		return gson.fromJson(response, className);
	}
	@Override
	public <T>void setResponse(T response, Class<T> className) {
		if(response == null)
			return;
		this.response = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd").create().toJson(response, className);
	}
	@Override
	public String getExMessage() {
		return exMessage;
	}
	@Override
	public void setExMessage(String exMessage) {
		this.exMessage = exMessage;
	}

	@Override
	public String toString() {
		return "ResponseJSON [response=" + response + ", exMessage=" + exMessage + "]";
	}
	@Override
	public <T> void setResponse(List<T> response, Class<T[]> class1) {
		if(response == null) 
			return;
		this.response = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd").create().toJson(response.toArray(), class1);
	}
	
}