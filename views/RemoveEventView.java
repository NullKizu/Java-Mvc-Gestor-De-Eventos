package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controllers.RemoveEventController;
import core.Controller;
import core.Model;
import core.View;


@SuppressWarnings("serial")
public class RemoveEventView extends JPanel implements View
{
	private RemoveEventController removeEventController;
	private JTable table;


	public RemoveEventView(RemoveEventController removeEventController)
	{
		this.removeEventController = removeEventController;

		make_frame();
	}


	@Override
	public void update(Model model, Object data)
	{ }

	private void make_frame()
	{
		setLayout(new BorderLayout());

		String[] columns = {"", "Fecha", "Descripción", "Frecuencia", "Correo electrónico", "Alarma"};
		DefaultTableModel model = new DefaultTableModel(columns, 0) {
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				if (columnIndex == 0) {
					return Boolean.class;
				}
				return String.class;
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				return column == 0;
			}
		};

		table = new JTable(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.getColumnModel().getColumn(3).setPreferredWidth(80);
		table.getColumnModel().getColumn(4).setPreferredWidth(150);
		table.getColumnModel().getColumn(5).setPreferredWidth(70);

		removeEventController.loadEvents(table);

		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel(new FlowLayout());

		JButton btnSelectAll = new JButton("Seleccionar Todos");
		JButton btnRemove = new JButton("Eliminar");
		JButton btnCancel = new JButton("Cancelar");

		btnSelectAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removeEventController.selectAll(table);
			}
		});

		btnRemove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removeEventController.removeSelected(table);
			}
		});

		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.loadView("HomeView");
			}
		});

		buttonPanel.add(btnSelectAll);
		buttonPanel.add(btnRemove);
		buttonPanel.add(btnCancel);

		add(buttonPanel, BorderLayout.SOUTH);
	}

	public JTable getTable()
	{
		return table;
	}
}
