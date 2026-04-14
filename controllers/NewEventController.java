package controllers;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import core.Controller;
import models.SchedulerEvent;
import models.SchedulerIO;
import views.EventListView;
import views.NewEventView;


public class NewEventController extends Controller
{
	private NewEventView newEventView;
	private EventListController eventListController;
	private RemoveEventController removeEventController;


	public NewEventController(EventListController eventListController, RemoveEventController removeEventController)
	{
		this.eventListController = eventListController;
		this.removeEventController = removeEventController;

	}


	@Override
	public void run()
	{
		newEventView = new NewEventView(this);
	}

	public void addEvent(SchedulerEvent event)
	{
		Object[] metadata = new Object[5];
		metadata[0] = event.getDate();
		metadata[1] = event.getEventDesc();
		metadata[2] = event.getFrequency();
		metadata[3] = event.getFwdEmail();
		metadata[4] = event.getAlarm() ? "ON" : "OFF";

		try {
			SchedulerIO schedulerIO = new SchedulerIO();
			schedulerIO.attach(newEventView);
			schedulerIO.saveEvent(event);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR", e.getMessage(), JOptionPane.ERROR_MESSAGE);
		}


		eventListController.addNewRow(metadata);

		if (removeEventController.getView() != null) {
			removeEventController.refreshTable();
		}
	}


	public NewEventView getView()
	{
		return newEventView;
	}
}
