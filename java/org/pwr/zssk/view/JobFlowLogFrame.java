package org.pwr.zssk.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;

public class JobFlowLogFrame extends JFrame {

	private JPanel contentPane;
	private JTextPane textPane;
	private final JScrollPane scrollPane = new JScrollPane();

	public void setLog(String str)
	{
		textPane.setText(str);
	}
	/**
	 * Create the frame.
	 */
	public JobFlowLogFrame() {

		setBounds(100, 100, 600, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setPreferredSize(new Dimension(400,500));
		setContentPane(contentPane);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
pack();
		
		setVisible(true);
	}

}
