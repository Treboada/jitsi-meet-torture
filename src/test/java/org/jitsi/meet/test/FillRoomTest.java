package org.jitsi.meet.test;

import org.jitsi.meet.test.base.JitsiMeetUrl;
import org.jitsi.meet.test.base.Participant;
import org.jitsi.meet.test.util.TestUtils;
import org.jitsi.meet.test.web.WebTestBase;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FillRoomTest 
	extends WebTestBase 
{
	public static String TEST_PROP_BASE = "room_capacity_test";
    public static String ROOM_NAME_PROP = TEST_PROP_BASE + ".room_name";

	String roomName;
	int roomCapacity;
		
	@Override
	public boolean skipTestByDefault() {

		return true;
	}

	@Override
	protected void setupClass() {

		super.setupClass();
		
		roomName = System.getProperty(ROOM_NAME_PROP, "test");
		roomCapacity = 2;
	}

	@Test
	public void FillConference() {

		// room name
		JitsiMeetUrl meet = super.getJitsiMeetUrl();
		meet.setRoomName(roomName);

		int participant_count = 3;
		
		try {
			
			for (int p = 0; p < participant_count; p++)
			{
				joinParticipant(meet);
			}
			
			Thread.sleep(60 * 1000);
			
			// check limit of participants by room
			//if (checkRoomLimit(participant.getDriver())) return;

		} catch (TimeoutException | InterruptedException ex) {

			// server not answering on time
			Assert.fail("Timeout fail after " + 0 + " opened sessions.");
			
		} finally {
			
			// dispose sessions
			//participants.getAll().forEach(Participant::close);
		}
	}
	
	private void joinParticipant(JitsiMeetUrl meet)
	{
		// create participant
		//String name = "CreatedParticipant_" + (++participant_counter);0
		Participant<?> participant = participants.createParticipant(TEST_PROP_BASE);
		//participant.setDisplayName(name);
		
		// join room
		participant.joinConference(meet);
	}
	
	private boolean checkRoomLimit(WebDriver driver)
	{
//		TestUtils.waitForElementByXPath(driver,
//	            "//span[@data-i18n='dialog.maxUsersLimitReached']", 5);
		
		return true;
	}
}
