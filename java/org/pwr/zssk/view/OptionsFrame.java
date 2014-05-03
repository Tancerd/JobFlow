package org.pwr.zssk.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.AbstractAction;
import javax.swing.AbstractListModel;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.pwr.zssk.dataaccess.DataStore;
import org.pwr.zssk.dataaccess.Rule;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class OptionsFrame extends JDialog {

	private JPanel contentPane;
	private JFormattedTextField  textField;
	private JFormattedTextField  textField_1;
	private JTable opoznieniaTable;
	private JTable table_1;
	private JTable table;
	private DataStore dataStore;
	private JList ruleList;
	private JComboBox ruleComboBox;
	private JComboBox jobComboBox;

	/**
	 * Launch the application.
	 */
	

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
		
		textField_1 = new JFormattedTextField();
		textField_1.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				try
				{
					dataStore.setMachineNumber(Integer.parseInt(textField_1.getText()));
					updateRuleGUI();
					updateMacierzPrzezbrojen();
					updateKolejnoscGUI(0);
					updateKolejnoscJobComboBox();

				}
				catch ( Exception e)
				{
					
				}
			}
		});
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.anchor = GridBagConstraints.WEST;
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 0;
		panel.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		textField_1.setValue(new Integer(2));
		
		JLabel lblLiczbaJobw = new JLabel("Liczba Job\u00F3w");
		GridBagConstraints gbc_lblLiczbaJobw = new GridBagConstraints();
		gbc_lblLiczbaJobw.insets = new Insets(0, 0, 0, 5);
		gbc_lblLiczbaJobw.gridx = 0;
		gbc_lblLiczbaJobw.gridy = 1;
		panel.add(lblLiczbaJobw, gbc_lblLiczbaJobw);
		
		textField = new JFormattedTextField ();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.anchor = GridBagConstraints.WEST;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		panel.add(textField, gbc_textField);
		textField.setColumns(10);
		textField.setValue(new Integer(2));
		textField.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				try
				{
					
					dataStore.setJobNumber(Integer.parseInt(textField.getText()));
					updateOpoznienieGUI();
					updateMacierzPrzezbrojen();
					updateKolejnoscJobComboBox();
					updateKolejnoscGUI(0);
					
				}
				catch ( Exception e)
				{
					
				}
				
			}
		});
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Op\u00F3\u017Anienie", null, panel_1, null);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		opoznieniaTable = new JTable();
		
		AbstractAction action = new AbstractAction()
		{
			@Override
		    public void actionPerformed(ActionEvent e)
		    {
				
		        TableCellListener tcl = (TableCellListener)e.getSource();
		        System.out.println("Row   : " + tcl.getRow());
		        System.out.println("Column: " + tcl.getColumn());
		        System.out.println("Old   : " + tcl.getOldValue());
		        System.out.println("New   : " + tcl.getNewValue());
		        Integer[] tempArr=dataStore.getArriveTimes();
		        try
		        {
		        tempArr[tcl.getColumn()]=(Integer) Integer.parseInt((String) tcl.getNewValue());
		        }
		        	catch(Exception arg)
		        	{
		       
		        	}
		    }

			
		};

		TableCellListener tcl = new TableCellListener(opoznieniaTable, action);
		
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
		jobComboBox = new JComboBox();
		
		jobComboBox.addItemListener(new ItemListener() {
	    	public void itemStateChanged(ItemEvent arg0) {
	    		updateKolejnoscGUI(jobComboBox.getSelectedIndex());
	    	}
	    });
	
		jobComboBox.setModel(new DefaultComboBoxModel(new String[] {}));
		panel_2.add(jobComboBox);
		
		
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
		
		AbstractAction action3 = new AbstractAction()
		{
			@Override
		    public void actionPerformed(ActionEvent e)
		    {
		        TableCellListener tcl = (TableCellListener)e.getSource();
		        System.out.println("Row   : " + tcl.getRow());
		        System.out.println("Column: " + tcl.getColumn());
		        System.out.println("Old   : " + tcl.getOldValue());
		        System.out.println("New   : " + tcl.getNewValue());
		        String[][] tempArr=dataStore.getOrders();
		        try
		        {
		        	String temp= tcl.getNewValue().toString();
		        	int tempKtoryJob=jobComboBox.getSelectedIndex();
		        	String oldValue=tempArr[tempKtoryJob][tcl.getColumn()-1];
		        	String[] oldValueArr=oldValue.split(" ");
		        	String newValue="0 0";
		        	if(tcl.getRow()==0)
		        	{
		        		newValue=tcl.getNewValue().toString()+" "+oldValueArr[1];
		        	}
		        	else if(tcl.getRow()==1)
		        	{
		        		newValue=oldValueArr[0]+" "+ tcl.getNewValue().toString();
		        	}
		        	tempArr[tempKtoryJob][tcl.getColumn()-1]=newValue;
		        	dataStore.setOrders(tempArr);
		        }
		        	catch(Exception arg)
		        	{
		        		System.out.println(arg);
		        	}
		    }

			
		};

		TableCellListener tcl3 = new TableCellListener(table, action3);
		
		
		panel_2.setLayout(new GridLayout(2, 0, 0, 0));
		panel_2.add(table);
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Regu\u0142y priorytet\u00F3w", null, panel_4, null);
		panel_4.setLayout(new GridLayout(0, 2, 0, 0));
		
		 ruleList = new JList();
		 ruleList.addListSelectionListener(new ListSelectionListener() {
		 	public void valueChanged(ListSelectionEvent arg0) {
		 		Rule rarr[]=dataStore.getRules();
		 	   JList list = (JList) arg0.getSource();
		 		ruleComboBox.setSelectedItem(rarr[ list.getSelectedIndex()]);
		 	}
		 });
		ruleList.setModel(new AbstractListModel() {
			String[] values = new String[0];
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		ruleList.setSelectedIndex(0);
		panel_4.add(ruleList);
		
	    ruleComboBox = new JComboBox();
	    ruleComboBox.addItemListener(new ItemListener() {
	    	public void itemStateChanged(ItemEvent arg0) {
	    		Rule[] oldRules=dataStore.getRules();
	    		if(ruleList.getSelectedIndex()!=-1)
	    		{
	    			int i=ruleList.getSelectedIndex();
	    			oldRules[ruleList.getSelectedIndex()]=(Rule) ruleComboBox.getSelectedItem();
	    		}
	    		dataStore.setRules(oldRules);
	    	}
	    });
		ruleComboBox.setModel(new DefaultComboBoxModel(new Rule[] {Rule.FIFO, Rule.LIFO, Rule.EDD, Rule.LPT, Rule.LWR, Rule.SPT}));
		panel_4.add(ruleComboBox);
		
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
		
		AbstractAction action2 = new AbstractAction()
		{
			@Override
		    public void actionPerformed(ActionEvent e)
		    {
		        TableCellListener tcl = (TableCellListener)e.getSource();
		        System.out.println("Row   : " + tcl.getRow());
		        System.out.println("Column: " + tcl.getColumn());
		        System.out.println("Old   : " + tcl.getOldValue());
		        System.out.println("New   : " + tcl.getNewValue());
		        Integer[][] tempArr=dataStore.getPrepareTimes();
		        try
		        {
		        tempArr[tcl.getRow()][tcl.getColumn()]=(Integer) Integer.parseInt((String) tcl.getNewValue());
		        }
		        	catch(Exception arg)
		        	{
		       
		        	}
		    }

			
		};

		TableCellListener tcl2 = new TableCellListener(table_1, action2);
		
		
		panel_3.add(table_1);
		
	}

	public void setDataStore(DataStore tdataStore) {
		// TODO Auto-generated method stub
		dataStore=tdataStore;
		updateRuleGUI();
	}

	public void setGUI()
	{
		textField_1.setText(dataStore.getMachineNumber()+"");
		textField.setText(dataStore.getJobNumber()+"");
	}
	public void updateRuleGUI()
	{
		Rule[] trule=new Rule[dataStore.getMachineNumber()];
		Rule[] oldRule=dataStore.getRules();
		int m=0;
		for(;m<dataStore.getRules().length && m<dataStore.getMachineNumber();m++)
		{
			trule[m]=oldRule[m];
		}
		for(;m<dataStore.getMachineNumber();m++)
		{
			trule[m]=Rule.EDD;
		}
		dataStore.setRules(trule);
		final String[] tvalues= new String[dataStore.getMachineNumber()];
		for(int n=0;n<tvalues.length;n++)
			tvalues[n]="Machine "+(n+1);
		
		ruleList.setModel(new AbstractListModel() {
		
			String[] values=tvalues;
			
	
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
	}

	public void updateMacierzPrzezbrojen()
	{
		String [][] tempOpoznieniaArray=new String[dataStore.getMachineNumber()][dataStore.getJobNumber()];
		Integer[][] oldPrzezbrojenia= dataStore.getPrepareTimes();
		Integer[][] newPrzezbrojenia= new Integer[dataStore.getMachineNumber()][dataStore.getJobNumber()];

		for(int n=0;n<dataStore.getMachineNumber();n++)
			for(int m=0;m<dataStore.getJobNumber();m++)
			{
				newPrzezbrojenia[n][m]=0;
				tempOpoznieniaArray[n][m]=0+"";
			}
		for(int n=0;n<oldPrzezbrojenia.length && n<dataStore.getMachineNumber();n++)
			for(int m=0;m<oldPrzezbrojenia[n].length && m<dataStore.getJobNumber();m++)
			{
				newPrzezbrojenia[n][m]=oldPrzezbrojenia[n][m];
				tempOpoznieniaArray[n][m]=oldPrzezbrojenia[n][m]+"";
			}
		
		
		
		dataStore.setPrepareTimes(newPrzezbrojenia);
		
		table_1.setModel(new DefaultTableModel(
				tempOpoznieniaArray,
				tempOpoznieniaArray.length>0?tempOpoznieniaArray[0]:null
			) {
			@Override  
			public Class getColumnClass(int columnIndex) { 
			        return Integer.class;  
			}
		
			});
	}
	public void updateKolejnoscJobComboBox()
	{
		String[] tempArr= new String[dataStore.getJobNumber()];
		
		for(int n=0;n<dataStore.getJobNumber();n++)
		{
			tempArr[n]="Job "+n;
		}
		jobComboBox.setModel(new DefaultComboBoxModel(tempArr));
	}
	public void updateOpoznienieGUI()
	{
		String [][] tempOpoznieniaArray=new String[2][dataStore.getJobNumber()];
		Integer[] oldOpoznienia= dataStore.getArriveTimes();
		Integer[] newOpoznienia= new Integer[dataStore.getJobNumber()];
		int m=0;
		for(;m<oldOpoznienia.length && m<dataStore.getJobNumber();m++)
		{
			tempOpoznieniaArray[0][m]="Job "+ (m+1);
			tempOpoznieniaArray[1][m]=oldOpoznienia[m].toString();
			newOpoznienia[m]=oldOpoznienia[m];
		}
		for(;m<dataStore.getJobNumber();m++)
		{
			tempOpoznieniaArray[0][m]="Job "+(m+1);
			tempOpoznieniaArray[1][m]=0+"";
			newOpoznienia[m]=0;
		}
		
		
		dataStore.setArriveTimes(newOpoznienia);
		opoznieniaTable.setModel(new DefaultTableModel(
				tempOpoznieniaArray,
				tempOpoznieniaArray[0]
			) {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;
				@Override  
				public Class getColumnClass(int columnIndex) { 
				        return Integer.class;  
				}
				
				boolean[] rowEditables = new boolean[] {
						false, true
					};
				@Override  
					public boolean isCellEditable(int row, int column) {
						return rowEditables[row];
					}
			});
		
	}
	
	
	
	public void updateKolejnoscGUI(int ktoryJob)
	{
		String [][] tempKolejnoscArray=new String[2][dataStore.getMachineNumber()+1];
		String [][] oldKolejnosc= dataStore.getOrders();
		String [][] newKolejnosc= new String[dataStore.getJobNumber()][dataStore.getMachineNumber()];

		
		tempKolejnoscArray[0][0]="Kolejnosc Maszyn";
		tempKolejnoscArray[1][0]="Kolejnosc Czasow";
		int m=0;
		
		for(int n=0;n<dataStore.getJobNumber();n++)
			for(int h=0;h<dataStore.getMachineNumber();h++)
			{
				newKolejnosc[n][h]=0+" "+0;
				if(n<oldKolejnosc.length && h<oldKolejnosc[n].length)
					newKolejnosc[n][h]=oldKolejnosc[n][h];
			}
			if(ktoryJob<oldKolejnosc.length)
		for(;m<oldKolejnosc[ktoryJob].length && m<dataStore.getMachineNumber();m++)
		{
			String[] tempiczek=oldKolejnosc[ktoryJob][m].split(" ");
			if(tempiczek.length==2)
			{
				tempKolejnoscArray[0][m+1]=tempiczek[0];
				tempKolejnoscArray[1][m+1]=tempiczek[1];
			}
			else
			{
				tempKolejnoscArray[0][m+1]=0+"";
				tempKolejnoscArray[1][m+1]=0+"";
			}
			
		}
		for(;m<dataStore.getMachineNumber();m++)
		{
			tempKolejnoscArray[0][m+1]=0+"";
			tempKolejnoscArray[1][m+1]=0+"";
			
		}
		
		
		dataStore.setOrders(newKolejnosc);
		table.setModel(new DefaultTableModel(
				tempKolejnoscArray,
				tempKolejnoscArray[0]
			) {
				/**
				 * 
				 */
			@Override  
			public Class getColumnClass(int columnIndex) {
				 if (columnIndex == 0)      
			            return String.class;  
			        else return Integer.class;  
			}
				private static final long serialVersionUID = 1L;
				
				boolean[] columnEditables = new boolean[] {
						false, true
					};
					public boolean isCellEditable(int row, int column) {
						return column<columnEditables.length?columnEditables[column]:true;
					}
			});
		
	}
}
