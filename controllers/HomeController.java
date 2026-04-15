package controllers;

import core.Controller;
import views.EventListView;
import views.HomeView;
import views.NewEventView;
import views.RegisterGuestView;
import views.RemoveEventView;


public class HomeController extends Controller
{
	private HomeView homeView;
	private EventListController eventListController = new EventListController();
	private RemoveEventController removeEventController = new RemoveEventController();
	private NewEventController newEventController = new NewEventController(eventListController, removeEventController);
	private GuestController guestController = new GuestController();


	@Override
	public void run()
	{
		eventListController.run();
		newEventController.run();
		removeEventController.run();
		guestController.run();
		removeEventController.setEventListController(eventListController);

		homeView = new HomeView(this, mainFrame);
		addView("HomeView", homeView);

		mainFrame.setVisible(true);
	}


	public EventListView getEventListView()
	{
		return eventListController.getView();
	}

	public NewEventView getNewEventView()
	{
		return newEventController.getView();
	}

	public RemoveEventView getRemoveEventView()
	{
		return removeEventController.getView();
	}

	public RegisterGuestView getRegisterGuestView()
	{
		return guestController.getView();
	}
}
