package models;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import core.Model;
import core.View;


/**
 * Responsible for reading / writing events saved.
 */
public class SchedulerIO implements Model
{
	//-----------------------------------------------------------------------
	//		Attributes
	//-----------------------------------------------------------------------
	private static final String DIRECTORY = ".";
	private static final String FILE = "events.txt";
	private static final String FILE_GUESTS = "guests.txt";
	private List<View> views = new ArrayList<>();
	private String notice;

	
	//-----------------------------------------------------------------------
	//		Methods
	//-----------------------------------------------------------------------
	@Override
	public void attach(View view) 
	{
		views.add(view);
	}

	@Override
	public void detach(View view) 
	{
		views.remove(view);
	}

	@Override
	public void notifyViews() 
	{
		for (View v : views) {
			v.update(this, notice);
		}
	}
	
	/**
	 * Saves a {@link SchedulerEvent} in disk in {@link #DIRECTORY}.{@link #FILE}.
	 * 
	 * @param event {@link SchedulerEvent Event} to be saved
	 * @throws Exception If it can't save the event
	 */
	public void saveEvent(SchedulerEvent event) throws Exception 
	{
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(DIRECTORY, FILE), true));
			writer.write(event.toString(), 0, event.toString().length());
			writer.newLine();
			writer.close();
		} catch (FileNotFoundException fnfe) {
			notice = "File not found"; 
			notifyViews();
		} catch (Exception ex) {
			notice = "Error while writing the file";
			notifyViews();
		}
	}

	/**
	 * Reads a {@link SchedulerEvent} saved in disk with name {@link #FILE}.
	 * @return List of lists (matrix) of the events
	 * @throws Exception If it can't read event file
	 */
	public Vector<Vector<Object>> getEvents() throws Exception 
	{
		Vector<Vector<Object>> response = new Vector<Vector<Object>>();

		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(DIRECTORY, FILE)));
			String line = reader.readLine();
			
			while (line != null) {
				Vector<Object> eventInfo = new Vector<Object>();
				String[] tokens = line.split(";");

				eventInfo.add(tokens[0]);
				eventInfo.add(tokens[1]);
				eventInfo.add(Frequency.valueOf(tokens[2]));
				eventInfo.add(tokens[3]);
				eventInfo.add(tokens[4].equals("1") ? "ON" : "OFF");

				response.add(eventInfo);
				line = reader.readLine();
			}

			reader.close();
		} catch (FileNotFoundException fnfe) {
			notice = "File not found";
			notifyViews();
		} catch (Exception ex) {
			notice = "There was a problem reading the event file";
			notifyViews();
		}
		
		return response;
	}
	
	public void saveGuest(Guest guest) throws Exception 
	{
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(DIRECTORY, FILE_GUESTS), true));
			writer.write(guest.toString(), 0, guest.toString().length());
			writer.newLine();
			writer.close();
		} catch (FileNotFoundException fnfe) {
			notice = "File not found"; 
			notifyViews();
		} catch (Exception ex) {
			notice = "Error while writing the file";
			notifyViews();
		}
	}

	public Vector<Vector<Object>> getGuests() throws Exception 
	{
		Vector<Vector<Object>> response = new Vector<Vector<Object>>();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(DIRECTORY, FILE_GUESTS)));
			String line = reader.readLine();
			
			while (line != null) {
				Vector<Object> guestInfo = new Vector<Object>();
				String[] tokens = line.split(";");

				guestInfo.add(tokens[0]);
				guestInfo.add(tokens[1]);
				guestInfo.add(tokens[2]);
				guestInfo.add(tokens[3]);
				guestInfo.add(tokens[4]);
				guestInfo.add(tokens[5].equals("1") ? "Sí" : "No");

				response.add(guestInfo);
				line = reader.readLine();
			}

			reader.close();
		} catch (FileNotFoundException fnfe) {
			notice = "File not found";
			notifyViews();
		} catch (Exception ex) {
			notice = "There was a problem reading the guest file";
			notifyViews();
		}
		
		return response;
	}
}