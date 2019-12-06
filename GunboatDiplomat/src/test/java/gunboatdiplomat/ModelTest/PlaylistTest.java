package gunboatdiplomat.ModelTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import gunboatdiplomat.model.Playlist;
import gunboatdiplomat.model.VidSeg;

public class PlaylistTest {

	Playlist p1 = new Playlist("Test1");
	Playlist p2 = new Playlist("Test1");
	Playlist p3 = new Playlist("Test2");
	
	VidSeg v1 = new VidSeg("1", "Aaron", "hello", 0, 0, "");
	VidSeg v2 = new VidSeg("2", "Ashwin", "bye", 1, 0, "");
	VidSeg v3 = new VidSeg("3", "Shannon", "huh", 1, 1, "");
	VidSeg v4 = new VidSeg("4", "Nicole", "ok", 0, 1, "");
	
	
	@Test
	public void testEqualsPlaylist() {
		//this tests the override equals method in Playlist
		assertEquals(p1, p2);
		assertNotEquals(p1, p3);
		
		p1.addVideoSegment(v1);
		p1.addVideoSegment(v2);
		p1.addVideoSegment(v3);
		
		p2.addVideoSegment(v1);
		p2.addVideoSegment(v2);
		p2.addVideoSegment(v3);
		
		assertEquals(p1, p2);
		assertNotEquals(p1, p3);
		
		p3.addVideoSegment(v1);
		
		assertNotEquals(p1, p3);
		
		p3.addVideoSegment(v2);
		p3.addVideoSegment(v4);
		
		assertNotEquals(p1, p3);
		
		
	}
}
