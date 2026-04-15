package views;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import controllers.GuestController;
import core.Model;
import core.View;
import models.Guest;

@SuppressWarnings("serial")
public class RegisterGuestView extends JPanel implements View
{
	private GuestController guestController;	
	private JTextField tf_nombre;
	private JTextField tf_numeroCelular;
	private JRadioButton rbtn_masculino;
	private JRadioButton rbtn_femenino;
	private JComboBox<Integer> cbx_dia;
	private JComboBox<String> cbx_mes;
	private JComboBox<Integer> cbx_anio;
	private JTextField tf_direccion;
	private JCheckBox cbx_aceptaTerminos;
	
	public RegisterGuestView(GuestController guestController) 
	{
		this.guestController = guestController;
		
		make_frame();
		make_field_nombre();
		make_field_numeroCelular();
		make_field_genero();
		make_field_fechaNacimiento();
		make_field_direccion();
		make_field_aceptaTerminos();
		make_btn_save();
		make_btn_clean();
		setPreferredSize(new java.awt.Dimension(600, 300));
	}

	@Override
	public void update(Model model, Object data) 
	{
		if (data != null) {
			String notice = (String) data;
			JOptionPane.showMessageDialog(null, notice);
		}
	}
	
	private void cleanFields() 
	{
		tf_nombre.setText("");
		tf_numeroCelular.setText("");
		rbtn_masculino.setSelected(true);
		cbx_dia.setSelectedIndex(0);
		cbx_mes.setSelectedIndex(0);
		cbx_anio.setSelectedIndex(0);
		tf_direccion.setText("");
		cbx_aceptaTerminos.setSelected(false);
	}
	
	private void make_frame() { setLayout(null); }
	
	private void make_field_nombre()
	{
		JLabel lbl_nombre = new JLabel("Nombre");
		lbl_nombre.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_nombre.setBounds(29, 29, 134, 14);
		add(lbl_nombre);

		tf_nombre = new JTextField();
		tf_nombre.setBounds(169, 26, 196, 20);
		add(tf_nombre);
		tf_nombre.setColumns(10);
	}
	
	private void make_field_numeroCelular()
	{
		JLabel lbl_numeroCelular = new JLabel("Número celular");
		lbl_numeroCelular.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_numeroCelular.setBounds(29, 71, 130, 14);
		add(lbl_numeroCelular);

		tf_numeroCelular = new JTextField();
		tf_numeroCelular.setBounds(169, 68, 196, 20);
		add(tf_numeroCelular);
		tf_numeroCelular.setColumns(10);
	}
	
	private void make_field_genero()
	{
		final ButtonGroup btng_genero = new ButtonGroup();

		JLabel lbl_genero = new JLabel("Género");
		lbl_genero.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_genero.setBounds(29, 119, 78, 14);
		add(lbl_genero);

		rbtn_masculino = new JRadioButton("Masculino");
		btng_genero.add(rbtn_masculino);
		rbtn_masculino.setSelected(true);
		rbtn_masculino.setBounds(169, 115, 85, 23);
		add(rbtn_masculino);

		rbtn_femenino = new JRadioButton("Femenino");
		btng_genero.add(rbtn_femenino);
		rbtn_femenino.setBounds(269, 115, 85, 23);
		add(rbtn_femenino);
	}
	
	private void make_field_fechaNacimiento()
	{
		JLabel lbl_fechaNacimiento = new JLabel("Fecha de nacimiento");
		lbl_fechaNacimiento.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_fechaNacimiento.setBounds(29, 164, 130, 14);
		add(lbl_fechaNacimiento);

		cbx_dia = new JComboBox<>();
		for (int i = 1; i <= 31; i++) {
			cbx_dia.addItem(i);
		}
		cbx_dia.setBounds(169, 160, 50, 20);
		add(cbx_dia);

		cbx_mes = new JComboBox<>();
		String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
		for (String mes : meses) {
			cbx_mes.addItem(mes);
		}
		cbx_mes.setBounds(229, 160, 80, 20);
		add(cbx_mes);

		cbx_anio = new JComboBox<>();
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		for (int i = currentYear; i >= 1900; i--) {
			cbx_anio.addItem(i);
		}
		cbx_anio.setBounds(319, 160, 60, 20);
		add(cbx_anio);
	}
	
	private void make_field_direccion()
	{
		JLabel lbl_direccion = new JLabel("Dirección");
		lbl_direccion.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_direccion.setBounds(29, 207, 78, 14);
		add(lbl_direccion);

		tf_direccion = new JTextField();
		tf_direccion.setBounds(169, 204, 196, 20);
		add(tf_direccion);
		tf_direccion.setColumns(10);
	}
	
	private void make_field_aceptaTerminos()
	{
		cbx_aceptaTerminos = new JCheckBox("Aceptar Terminos");
		cbx_aceptaTerminos.setBounds(29, 247, 150, 23);
		add(cbx_aceptaTerminos);
	}
	
	private void make_btn_save()
	{
		JButton btn_save = new JButton("Guardar");
		btn_save.setBounds(190, 247, 89, 23);
		add(btn_save);

		btn_save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Guest guest = new Guest();

				guest.setNombre(tf_nombre.getText());
				guest.setNumeroCelular(tf_numeroCelular.getText());
				guest.setGenero(rbtn_masculino.isSelected() ? "Masculino" : "Femenino");
				
				int dia = (Integer) cbx_dia.getSelectedItem();
				int mes = cbx_mes.getSelectedIndex() + 1;
				int anio = (Integer) cbx_anio.getSelectedItem();
				Calendar cal = Calendar.getInstance();
				cal.set(anio, mes - 1, dia);
				guest.setFechaNacimiento(cal.getTime());
				
				guest.setDireccion(tf_direccion.getText());
				guest.setAceptaTerminos(cbx_aceptaTerminos.isSelected());

				guestController.addGuest(guest);
				cleanFields();
			}
		});
	}
	
	private void make_btn_clean()
	{
		JButton btn_clean = new JButton("Limpiar");
		btn_clean.setBounds(310, 247, 89, 23);
		add(btn_clean);

		btn_clean.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cleanFields();
			}
		});
	}
}