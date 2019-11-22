package gunboatdiplomat.HandlerTest;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import gunboatdiplomat.LambdaTest;
import gunboatdiplomat.UploadVidSegHandler;
import gunboatdiplomat.http.UploadVidSegRequest;
import gunboatdiplomat.http.UploadVidSegResponse;

public class UploadVidSegHandlerTest extends LambdaTest {

	void testInputSuccess(String input) throws IOException {
		UploadVidSegHandler handler = new UploadVidSegHandler();
		UploadVidSegRequest request = new Gson().fromJson(input, UploadVidSegRequest.class);
		
		UploadVidSegResponse response = handler.handleRequest(request, createContext("upload"));
		Assert.assertEquals(200, response.httpCode);
	}
	
	void testInputFailure(String input, int failureCode) throws IOException {
		UploadVidSegHandler handler = new UploadVidSegHandler();
		UploadVidSegRequest request = new Gson().fromJson(input, UploadVidSegRequest.class);
		
		UploadVidSegResponse response = handler.handleRequest(request, createContext("upload"));
		Assert.assertEquals(failureCode, response.httpCode);
	}
	
}
