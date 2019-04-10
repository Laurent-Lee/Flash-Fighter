import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class Launch extends JFrame implements ActionListener {

	private JPanel contentPane;
	private final Action action = new SwingAction();
	private int playerNum;
	private ArrKeyListener keyboard;
	private JButton mainMenuPlayButton;
	private JButton mainMenuQuitButton;
	private JSlider mainMenuSlider;
	private JLabel mainMenuPlayerLabel;
	private JPanel mainPanel;

	Timer t;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Launch frame = new Launch();
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
	public Launch() {
		t = new Timer(5, this);
		t.start();
		playerNum = 1;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize((int) DD.SCREENWIDTH, (int) DD.SCREENHEIGHT);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(null);
		
		
		mainMenuPlayButton = new JButton("Play");
		mainMenuPlayButton.setForeground(Color.BLACK);
		mainMenuPlayButton.setBackground(Color.ORANGE);
		mainMenuPlayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				keyboard = new ArrKeyListener(playerNum);
				getContentPane().add(keyboard);
				mainPanel.removeAll();
				mainPanel.repaint();
				mainPanel.revalidate();

			}
		});
		mainMenuPlayButton.setBounds(665, 332, 155, 155);
		mainPanel.add(mainMenuPlayButton);

		mainMenuSlider = new JSlider(JSlider.HORIZONTAL, 1, 4, 1);
		mainMenuSlider.setBounds(644, 528, 200, 26);
		mainMenuSlider.setMajorTickSpacing(1);
		mainMenuSlider.setPaintTicks(true);
		mainPanel.add(mainMenuSlider);

		mainMenuPlayerLabel = new JLabel("Number of Players: " + playerNum);
		mainMenuPlayerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mainMenuPlayerLabel.setBounds(644, 554, 200, 26);
		mainPanel.add(mainMenuPlayerLabel);

		mainMenuSlider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				playerNum = mainMenuSlider.getValue();
				mainMenuPlayerLabel.setText("Number of Players: " + playerNum);
			}

		});

		mainMenuQuitButton = new JButton("Quit Game");
		mainMenuQuitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mainMenuQuitButton.setForeground(Color.WHITE);
		mainMenuQuitButton.setBackground(Color.RED);
		mainMenuQuitButton.setBounds(1797, 0, 123, 60);
		mainPanel.add(mainMenuQuitButton);

		setUndecorated(true);
		setVisible(true);

	}

	public void actionPerformed(ActionEvent e) {
		if (keyboard != null) {
			if (keyboard.isBackToMenu()) {
				keyboard = null;
				getContentPane().removeAll();
				mainPanel.removeAll();

				mainPanel.add(mainMenuPlayButton);
				mainPanel.add(mainMenuSlider);
				mainPanel.add(mainMenuPlayerLabel);
				mainPanel.add(mainMenuQuitButton);

				getContentPane().add(mainPanel, BorderLayout.CENTER);

				mainPanel.repaint();
				mainPanel.revalidate();
			}
		}
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
		}
	}
}
