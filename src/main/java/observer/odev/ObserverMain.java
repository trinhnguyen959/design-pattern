package observer.odev;

import observer.odev.notifiers.EmailNotifiers;
import observer.odev.notifiers.FacebookNotifiers;
import observer.odev.notifiers.PhoneNotifiers;
import observer.odev.notifiers.YoutubeNotifiers;

public class ObserverMain {
	public static void main(String[] args) {
		VideoData videoData = new VideoData();
		new EmailNotifiers(videoData);
		new PhoneNotifiers(videoData);
		YoutubeNotifiers youtubeNotifiers = new YoutubeNotifiers(videoData);

		videoData.setTitle("Observer design pattern");
		videoData.detachObserver(youtubeNotifiers);
		System.out.println("----------------------");
		videoData.setDescription("Dev video");

		new FacebookNotifiers(videoData);
		System.out.println("----------------------");
		videoData.setFileName("Good video");
	}
}
