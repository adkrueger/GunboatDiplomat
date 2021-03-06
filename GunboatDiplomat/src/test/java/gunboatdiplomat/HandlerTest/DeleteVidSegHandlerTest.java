package gunboatdiplomat.HandlerTest;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;

import com.amazonaws.util.IOUtils;

import gunboatdiplomat.DeleteVidSegHandler;
import gunboatdiplomat.LambdaTest;
import gunboatdiplomat.UploadVidSegHandler;
import gunboatdiplomat.http.DeleteVidSegRequest;
import gunboatdiplomat.http.DeleteVidSegResponse;
import gunboatdiplomat.http.UploadVidSegRequest;
import gunboatdiplomat.http.UploadVidSegResponse;

public class DeleteVidSegHandlerTest extends LambdaTest {

    @Test
    public void testDeleteVidSegHandler() throws IOException {
    	// upload a video segment
		InputStream inputStream = UploadVidSegHandlerTest.class.getResourceAsStream("Segment1.ogg");
        String testFile = new String(java.util.Base64.getEncoder().encode(IOUtils.toByteArray(inputStream)));;
        UploadVidSegRequest req = new UploadVidSegRequest("testingDeleteVidSeg", "Leonard McCoy", "Death by natural causes.", 1, 0, testFile);
        UploadVidSegResponse resp = new UploadVidSegHandler().handleRequest(req, createContext("upload"));
        Assert.assertEquals("testingDeleteVidSeg", resp.response);

        // now delete
        DeleteVidSegRequest dvsr = new DeleteVidSegRequest("testingDeleteVidSeg");
        DeleteVidSegResponse deleteResponse = new DeleteVidSegHandler().handleRequest(dvsr, createContext("delete"));
        System.out.println(deleteResponse.id);
        Assert.assertEquals("testingDeleteVidSeg", deleteResponse.id);

        // try again and this should fail
        deleteResponse = new DeleteVidSegHandler().handleRequest(dvsr, createContext("delete"));
        Assert.assertEquals(422, deleteResponse.statusCode);
    }

}
