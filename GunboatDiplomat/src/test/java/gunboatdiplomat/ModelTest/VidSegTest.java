package gunboatdiplomat.ModelTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import gunboatdiplomat.model.VidSeg;

public class VidSegTest {

	VidSeg v1 = new VidSeg("1", "Aaron", "hello", 0, 0, "");
	VidSeg v2 = new VidSeg("2", "Ashwin", "bye", 1, 0, "");
	VidSeg v3 = new VidSeg("3", "Shannon", "huh", 1, 1, "");
	VidSeg v4 = new VidSeg("4", "Nicole", "ok", 0, 1, "");

	VidSeg v5 = new VidSeg("3", "Shannon", "huh", 1, 1, "");

	@Test
	public void testEqualsVidSeg() {
		//this tests the override equals method in VidSeg
		assertEquals(v3, v5);
		assertNotEquals(v1, v2);

		VidSeg v6 = new VidSeg("3", "Nicole", "huh", 1, 1, "");
		assertNotEquals(v3, v6);


		VidSeg v7 = new VidSeg("3", "Shannon", "bye", 1, 1, "");
		assertNotEquals(v3, v7);

		VidSeg v8 = new VidSeg("3", "Shannon", "huh", 0, 1, "");
		assertNotEquals(v3, v8);

		VidSeg v9 = new VidSeg("3", "Shannon", "huh", 1, 0, "");
		assertNotEquals(v3, v9);
	}
	
	@Test
	public void testSetContentsVidSeg() {
		v1.setContents("hi");
		assertEquals("hi", v1.base64EncodedContents);
	}
}
