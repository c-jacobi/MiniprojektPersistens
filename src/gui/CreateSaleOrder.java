package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class CreateSaleOrder extends JFrame{
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					CreateSaleOrder frame = new CreateSaleOrder();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
