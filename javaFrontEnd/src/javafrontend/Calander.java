/*
* Authors Brandon Foss, Calvin Brewer
*/
package javafrontend;


import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.client.util.DateTime;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;


public class Calander {
public void createEvent(Calendar cal) throws IOException {       

Event event = new Event();
event.setSummary("Event name here");
event.setLocation("event place here");
//event.getDescription();

Date startDate = new Date();
Date endDate = new Date(startDate.getTime() + 3600000);
DateTime start = new DateTime(startDate, TimeZone.getTimeZone("UTC"));
event.setStart(new EventDateTime().setDateTime(start));
DateTime end = new DateTime(endDate, TimeZone.getTimeZone("UTC"));
event.setEnd(new EventDateTime().setDateTime(end));
Event createdEvent = cal.events().insert("primary", event).execute();
System.out.println("Created event id: " + createdEvent.getId());
}
}
