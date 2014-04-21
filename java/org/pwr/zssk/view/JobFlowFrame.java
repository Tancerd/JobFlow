package org.pwr.zssk.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JobFlowFrame extends JFrame {
	OptionsFrame optionsFrame ;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JobFlowFrame frame = new JobFlowFrame();
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
	public JobFlowFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setEnabled(false);
		splitPane.setOneTouchExpandable(true);
		splitPane.setContinuousLayout(true);
		contentPane.add(splitPane);
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setRightComponent(scrollPane);
		
		JPanel panel_1 = new JPanel();
		scrollPane.setViewportView(panel_1);
		panel_1.setBackground(Color.WHITE);
		
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setOneTouchExpandable(true);
		splitPane_1.setEnabled(false);
		splitPane.setLeftComponent(splitPane_1);
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		
		JPanel panel_2 = new JPanel();
		splitPane_1.setLeftComponent(panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{121, 0};
		gbl_panel_2.rowHeights = new int[]{23, 23, 23, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JButton newSimButton = new JButton("Nowa symulacja");
		GridBagConstraints gbc_newSimButton = new GridBagConstraints();
		gbc_newSimButton.weightx = 1.0;
		gbc_newSimButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_newSimButton.insets = new Insets(0, 0, 5, 0);
		gbc_newSimButton.gridx = 0;
		gbc_newSimButton.gridy = 0;
		panel_2.add(newSimButton, gbc_newSimButton);
		newSimButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JButton loadSimButton = new JButton("Wczytaj symulacj\u0119");
		GridBagConstraints gbc_loadSimButton = new GridBagConstraints();
		gbc_loadSimButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_loadSimButton.weightx = 1.0;
		gbc_loadSimButton.anchor = GridBagConstraints.WEST;
		gbc_loadSimButton.insets = new Insets(0, 0, 5, 0);
		gbc_loadSimButton.gridx = 0;
		gbc_loadSimButton.gridy = 1;
		panel_2.add(loadSimButton, gbc_loadSimButton);
		
		JButton btnZapiszRaportPdf = new JButton("Zapisz raport PDF");
		GridBagConstraints gbc_btnZapiszRaportPdf = new GridBagConstraints();
		gbc_btnZapiszRaportPdf.weightx = 1.0;
		gbc_btnZapiszRaportPdf.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnZapiszRaportPdf.gridx = 0;
		gbc_btnZapiszRaportPdf.gridy = 2;
		panel_2.add(btnZapiszRaportPdf, gbc_btnZapiszRaportPdf);
		
		JPanel panel = new JPanel();
		splitPane_1.setRightComponent(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{121, 0};
		gbl_panel.rowHeights = new int[]{23, 0};
		gbl_panel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JButton btnOpcje = new JButton("Opcje");
		btnOpcje.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});
		btnOpcje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(optionsFrame==null || !optionsFrame.isShowing())
				{
				 optionsFrame = new OptionsFrame();
				optionsFrame.setVisible(true);
				}
			}
		});
		GridBagConstraints gbc_btnOpcje = new GridBagConstraints();
		gbc_btnOpcje.weightx = 1.0;
		gbc_btnOpcje.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnOpcje.gridx = 0;
		gbc_btnOpcje.gridy = 0;
		panel.add(btnOpcje, gbc_btnOpcje);
	}

}
