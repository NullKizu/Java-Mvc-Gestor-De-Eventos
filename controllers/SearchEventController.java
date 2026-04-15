package controllers;

import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import core.Controller;
import models.SchedulerIO;
import views.SearchEventView;

public class SearchEventController extends Controller
{
	private SearchEventView searchEventView;
	private JTable table;

	@Override
	public void run()
	{
		table = new JTable(getDataColumns(), getNameColumns());
		searchEventView = new SearchEventView(this, table);
	}

	public void searchEvents(String description, String date, String email)
	{
		try {
			SchedulerIO schedulerIO = new SchedulerIO();
			schedulerIO.attach(searchEventView);
			Vector<Vector<Object>> allEvents = schedulerIO.getEvents();
			Vector<Vector<Object>> filteredEvents = new Vector<>();

			for (Vector<Object> event : allEvents) {
				String eventDate = (String) event.get(0);
				String eventDesc = (String) event.get(1);
				String eventEmail = (String) event.get(3);

				boolean matches = true;
				if (!description.isEmpty() && !eventDesc.toLowerCase().contains(description.toLowerCase())) {
					matches = false;
				}
				if (!date.isEmpty() && !eventDate.contains(date)) {
					matches = false;
				}
				if (!email.isEmpty() && !eventEmail.toLowerCase().contains(email.toLowerCase())) {
					matches = false;
				}

				if (matches) {
					filteredEvents.add(event);
				}
			}

			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setRowCount(0);
			for (Vector<Object> event : filteredEvents) {
				model.addRow(event);
			}
		} catch (Exception ex) { }
	}

	public SearchEventView getView()
	{
		return searchEventView;
	}

	public Vector<String> getNameColumns()
	{
		Vector<String> nameColumns = new Vector<String>();

		nameColumns.add("Fecha");
		nameColumns.add("Descripción");
		nameColumns.add("Frecuencia");
		nameColumns.add("Correo electrónico");
		nameColumns.add("Alarma");

		return nameColumns;
	}

	public Vector<Vector<Object>> getDataColumns() 
	{
		return new Vector<Vector<Object>>();
	}
}