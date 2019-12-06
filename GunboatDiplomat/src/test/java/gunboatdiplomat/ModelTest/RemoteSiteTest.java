package gunboatdiplomat.ModelTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import gunboatdiplomat.model.RemoteSite;

public class RemoteSiteTest {

	RemoteSite r1 = new RemoteSite();
	
	@Test
	public void testSetUrl() {
		r1.setUrl("hi");
		assertEquals("hi", r1.getUrl());
	}
	
}
