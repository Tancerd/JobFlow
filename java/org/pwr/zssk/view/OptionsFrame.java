package org.pwr.zssk.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class OptionsFrame extends JDialog {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTable opoznieniaTable;
	private JTable table_1;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OptionsFrame frame = new OptionsFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public OptionsFrame() {
		
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Og\u00F3lne", null, panel, null);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {100, 150};
		gbl_panel.rowHeights = new int[]{20, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblLiczbaMaszyn = new JLabel("Liczba maszyn");
		GridBagConstraints gbc_lblLiczbaMaszyn = new GridBagConstraints();
		gbc_lblLiczbaMaszyn.insets = new Insets(0, 0, 5, 5);
		gbc_lblLiczbaMaszyn.gridx = 0;
		gbc_lblLiczbaMaszyn.gridy = 0;
		panel.add(lblLiczbaMaszyn, gbc_lblLiczbaMaszyn);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.anchor = GridBagConstraints.WEST;
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 0;
		panel.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JLabel lblLiczbaJobw = new JLabel("Liczba Job\u00F3w");
		GridBagConstraints gbc_lblLiczbaJobw = new GridBagConstraints();
		gbc_lblLiczbaJobw.insets = new Insets(0, 0, 0, 5);
		gbc_lblLiczbaJobw.gridx = 0;
		gbc_lblLiczbaJobw.gridy = 1;
		panel.add(lblLiczbaJobw, gbc_lblLiczbaJobw);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.anchor = GridBagConstraints.WEST;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		panel.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Op\u00F3\u017Anienie", null, panel_1, null);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		opoznieniaTable = new JTable();
		opoznieniaTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
			},
			new String[] {
				"ID", "Op\u00F3\u017Anienie"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		opoznieniaTable.setCellSelectionEnabled(true);
		opoznieniaTable.setColumnSelectionAllowed(true);
		opoznieniaTable.setFillsViewportHeight(true);
		panel_1.add(opoznieniaTable);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Kolejno\u015B\u0107", null, panel_2, null);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Kolejno\u015B\u0107 maszyn"},
				{"Kolejno\u015B\u0107 czasu"},
			},
			new String[] {
				"New column"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(88);
		table.getColumnModel().getColumn(0).setMinWidth(23);
		panel_2.setLayout(new BorderLayout(0, 0));
		panel_2.add(table);
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Regu\u0142y priorytet\u00F3w", null, panel_4, null);
		panel_4.setLayout(new GridLayout(0, 2, 0, 0));
		
		JList list = new JList();
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"cxz", "cx", "cxz", "c", "zxc", "zx", "czx"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		panel_4.add(list);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"FIFO", "LIFO", "SHITO"}));
		panel_4.add(comboBox);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Macierz przezbroje\u0144", null, panel_3, null);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		table_1 = new JTable();
		table_1.setColumnSelectionAllowed(true);
		table_1.setFillsViewportHeight(true);
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
			},
			new String[] {
				"New column", "New column"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		panel_3.add(table_1);
	}

}
