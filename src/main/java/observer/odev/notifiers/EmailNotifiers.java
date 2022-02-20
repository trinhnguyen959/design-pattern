package observer.odev.notifiers;

import observer.odev.Observer;
import observer.odev.Subject;
import observer.odev.VideoData;

public class EmailNotifiers extends Observer {
	public EmailNotifiers(Subject subject) {
		this.subject = subject;
		this.subject.attackObserver(this);
	}

	@Override
	protected void notify(Subject subject, Object o) {
		if (subject instanceof VideoData videoData) {
			System.out.println(String.format("Notify all subscribers via Email with new data "
							+ "\nName: %s, \nDescription: %s, \nFileName: %s",
					videoData.getTitle(), videoData.getDescription(), videoData.getFileName()));
		}
	}
}
