package views;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controllers.SearchEventController;
import core.Model;
import core.View;

@SuppressWarnings("serial")
public class SearchEventView extends JPanel implements View
{
	private SearchEventController searchEventController;
	private JTable table;
	private JTextField tf_description;
	private JTextField tf_date;
	private JTextField tf_email;

	public SearchEventView(SearchEventController searchEventController, JTable table)
	{
		this.searchEventController = searchEventController;
		this.table = table;

		make_frame();
		make_field_description();
		make_field_date();
		make_field_email();
		make_btn_search();
		make_table();
	}

	@Override
	public void update(Model model, Object data) 
	{
		if (data != null) {
			String notice = (String) data;
		}
	}

	private void make_frame() { setLayout(null); }

	private void make_field_description()
	{
		JLabel lbl_description = new JLabel("Descripción");
		lbl_description.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_description.setBounds(75, 21, 100, 14);
		add(lbl_description);

		tf_description = new JTextField();
		tf_description.setBounds(225, 18, 150, 20);
		add(tf_description);
		tf_description.setColumns(10);
	}

	private void make_field_date()
	{
		JLabel lbl_date = new JLabel("Fecha");
		lbl_date.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_date.setBounds(75, 45, 100, 14);
		add(lbl_date);

		tf_date = new JTextField();
		tf_date.setBounds(225, 42, 150, 20);
		add(tf_date);
		tf_date.setColumns(10);
	}

	private void make_field_email()
	{
		JLabel lbl_email = new JLabel("Correo electrónico");
		lbl_email.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_email.setBounds(75, 69, 120, 14);
		add(lbl_email);

		tf_email = new JTextField();
		tf_email.setBounds(225, 66, 150, 20);
		add(tf_email);
		tf_email.setColumns(10);
	}

	private void make_btn_search()
	{
		JButton btn_search = new JButton("Buscar");
		btn_search.setBounds(400, 42, 89, 23);
		add(btn_search);

		btn_search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String description = tf_description.getText();
				String date = tf_date.getText();
				String email = tf_email.getText();
				searchEventController.searchEvents(description, date, email);
			}
		});
	}

	private void make_table()
	{
		table.getColumnModel().getColumn(0).setPreferredWidth(80);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(80);
		table.getColumnModel().getColumn(3).setPreferredWidth(150);
		table.getColumnModel().getColumn(4).setPreferredWidth(70);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 120, 600, 200);
		add(scrollPane, BorderLayout.CENTER);
	}
}