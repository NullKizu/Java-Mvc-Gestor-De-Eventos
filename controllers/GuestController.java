package controllers;

import javax.swing.JOptionPane;

import core.Controller;
import models.Guest;
import models.SchedulerIO;
import views.RegisterGuestView;

public class GuestController extends Controller
{
	private RegisterGuestView registerGuestView;

	@Override
	public void run()
	{
		registerGuestView = new RegisterGuestView(this);
	}

	public void addGuest(Guest guest)
	{
		try {
			SchedulerIO schedulerIO = new SchedulerIO();
			schedulerIO.attach(registerGuestView);
			schedulerIO.saveGuest(guest);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR", e.getMessage(), JOptionPane.ERROR_MESSAGE);
		}
	}

	public RegisterGuestView getView()
	{
		return registerGuestView;
	}
}