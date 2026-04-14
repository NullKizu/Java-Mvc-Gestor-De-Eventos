package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import core.Controller;
import models.SchedulerIO;
import views.RemoveEventView;


public class RemoveEventController extends Controller
{
	private RemoveEventView removeEventView;
	private EventListController eventListController;


	public void setEventListController(EventListController eventListController)
	{
		this.eventListController = eventListController;
	}

	@Override
	public void run()
	{
		removeEventView = new RemoveEventView(this);
	}

	public void loadEvents(JTable table)
	{
		try {
			SchedulerIO schedulerIO = new SchedulerIO();
			schedulerIO.attach(removeEventView);
			Vector<Vector<Object>> events = schedulerIO.getEvents();
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setRowCount(0);
			if (events != null) {
				for (Vector<Object> event : events) {
					model.addRow(new Object[]{false, event.get(0), event.get(1), event.get(2), event.get(3), event.get(4)});
				}
			}
		} catch (Exception ex) { }
	}

	public void refreshTable()
	{
		if (removeEventView != null && removeEventView.getTable() != null) {
			loadEvents(removeEventView.getTable());
		}
	}

	public void selectAll(JTable table)
	{
		for (int i = 0; i < table.getRowCount(); i++) {
			table.setValueAt(true, i, 0);
		}
	}

	public void removeSelected(JTable table)
	{
		List<Integer> rowsToRemove = new ArrayList<>();
		for (int i = 0; i < table.getRowCount(); i++) {
			Boolean selected = (Boolean) table.getValueAt(i, 0);
			if (selected != null && selected) {
				rowsToRemove.add(i);
			}
		}

		if (rowsToRemove.isEmpty()) {
			return;
		}

		try {
			SchedulerIO schedulerIO = new SchedulerIO();
			schedulerIO.attach(removeEventView);
			Vector<Vector<Object>> events = schedulerIO.getEvents();

			for (int i = rowsToRemove.size() - 1; i >= 0; i--) {
				events.remove(rowsToRemove.get(i).intValue());
			}

			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(".", "events.txt")));
			for (Vector<Object> event : events) {
				String line = event.get(0) + ";" + event.get(1) + ";" + event.get(2) + ";" + event.get(3) + ";" + (event.get(4).equals("ON") ? "1" : "0");
				writer.write(line);
				writer.newLine();
			}
			writer.close();

			DefaultTableModel model = (DefaultTableModel) table.getModel();
			for (int i = rowsToRemove.size() - 1; i >= 0; i--) {
				model.removeRow(rowsToRemove.get(i).intValue());
			}

			if (eventListController != null) {
				eventListController.refreshTable();
			}
		} catch (Exception ex) { }
	}

	public RemoveEventView getView()
	{
		return removeEventView;
	}
}
