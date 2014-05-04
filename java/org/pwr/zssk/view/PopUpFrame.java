package org.pwr.zssk.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PopUpFrame extends JFrame{
	
	JLabel label;
	
	public void setText(String str)
	{
		label.setText(str);
		pack();
	}
	public PopUpFrame() {
		super();

		setBounds(0, 0, 100, 50);
		setSize(100, 50);
		JPanel contentPane = new JPanel();
		label= new JLabel();
		contentPane.add(label);
		setUndecorated(true);
		setResizable(false);
		setEnabled(false);
		setFocusable(false);
		setContentPane(contentPane);
		pack();
		
		setVisible(true);
	}
	
	
}
