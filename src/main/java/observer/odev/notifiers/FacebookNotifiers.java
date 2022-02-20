package observer.odev.notifiers;

import observer.odev.Observer;
import observer.odev.Subject;
import observer.odev.VideoData;

public class FacebookNotifiers extends Observer {
	public FacebookNotifiers(Subject subject) {
		this.subject = subject;
		this.subject.attackObserver(this);
	}

	@Override
	protected void notify(Subject subject, Object o) {
		if (subject instanceof VideoData videoData) {
			System.out.println(String.format("Notify all subscribers via Facebook with new data "
							+ "\nName: %s, \nDescription: %s, \nFileName: %s",
					videoData.getTitle(), videoData.getDescription(), videoData.getFileName()));
		}
	}
}
