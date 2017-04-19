//Import packages
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import javax.swing.event.*;
import javax.swing.table.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.*;
import java.util.*;

public class CalPanel extends JPanel implements ChangeListener {
	private JLabel lblMonth;
	private JButton ButtonPrev, ButtonNext, createButton, quitButton, colButton;
	private JTable tblCalendar;
	private DefaultTableModel mtblCalendar; // Table model
	private JScrollPane stblCalendar; // The scrollpane
	private JPanel panelCalendar;
	private int rYear, rMonth, rDay;
	private BackBone bb;
	public static Color c = Color.WHITE;

	public CalPanel(BackBone bb) {
		// Look and feel
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		} catch (UnsupportedLookAndFeelException e) {
		}

		this.bb = bb;

		setSize(300, 300);
		setLayout(new BorderLayout());
		// Create controls
		lblMonth = new JLabel("January");
		

		// cmbYear = new JComboBox();
		ButtonPrev = new JButton("<");
		ButtonNext = new JButton(">");
		createButton = new JButton("CREATE");
		quitButton = new JButton("QUIT");
		colButton = new JButton("CHANGE COLOR");

		JPanel ctrlPan = new JPanel();
		ctrlPan.setOpaque(true);
		// ctrlPan.getRootPane().setBackground(col);
		ctrlPan.add(createButton);
		ctrlPan.add(quitButton);
		ctrlPan.add(colButton);
		createButton.setBounds(0, 0, 20, 20);
		colButton.setBounds(0, 0, 20, 20);
		mtblCalendar = new DefaultTableModel() {
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};

		tblCalendar = new JTable(mtblCalendar);
		stblCalendar = new JScrollPane(tblCalendar);
		panelCalendar = new JPanel(null);
		panelCalendar.setOpaque(true);
		// model.changeColor(c,panelCalendar);
		tblCalendar.setShowGrid(false);
		stblCalendar.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
		//makes the actionListeners
		ButtonPrev.addActionListener(new ButtonPrev_Action());
		ButtonNext.addActionListener(new ButtonNext_Action());
		createButton.addActionListener(new create_Action());
		quitButton.addActionListener(new quit_Action());
		colButton.addActionListener(new col_Action());
		// add mouse listener
		tblCalendar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = tblCalendar.rowAtPoint(e.getPoint());
				int col = tblCalendar.columnAtPoint(e.getPoint());
				bb.setDate((int) tblCalendar.getValueAt(row, col));
			}
		});

		// Add buttons to theGUI
		add(ctrlPan, BorderLayout.NORTH);
		add(panelCalendar, BorderLayout.CENTER);
		panelCalendar.add(lblMonth);
		panelCalendar.add(ButtonPrev);
		panelCalendar.add(ButtonNext);
		panelCalendar.add(stblCalendar);
		// panelCalendar.add(colButton);

		// Set bounds
		panelCalendar.setBounds(0, 0, 500, 335);

		lblMonth.setBounds(10, 25, 20, 25);

		ButtonPrev.setBounds(150, 25, 50, 25);
		ButtonNext.setBounds(200, 25, 50, 25);
		stblCalendar.setBounds(10, 50, 250, 250);

		// Get real month and year
		rDay = bb.getRealTime().get(GregorianCalendar.DAY_OF_MONTH); // Get day
		rMonth = bb.getRealTime().get(GregorianCalendar.MONTH); // Get month
		rYear = bb.getRealTime().get(GregorianCalendar.YEAR); // Get year

		// Add headers
		String[] headers = { "S", "M", "T", "W", "T", "F", "S" }; // All headers
		for (int i = 0; i < 7; i++) {
			mtblCalendar.addColumn(headers[i]);
		}

		tblCalendar.getParent().setBackground(tblCalendar.getBackground()); // Set
																			// background

		// No resize/reorder
		tblCalendar.getTableHeader().setResizingAllowed(false);
		tblCalendar.getTableHeader().setReorderingAllowed(false);

		// Single cell selection
		tblCalendar.setColumnSelectionAllowed(true);
		tblCalendar.setRowSelectionAllowed(true);
		tblCalendar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Set row/column count
		tblCalendar.setRowHeight(38);
		mtblCalendar.setColumnCount(7);
		mtblCalendar.setRowCount(6);

		// Refresh calendar
		refreshCalendar(rMonth, rYear); // Refresh calendar
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		// setBackground(Color.BLUE);
	}

	class col_Action implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JTextField color = new JTextField("Select the new Color");
			JPanel panel = new JPanel(new BorderLayout());
			JButton Button1 = new JButton("Green button");
			JButton Button2 = new JButton("Yellow button");
			JButton Button3 = new JButton("Blue button");
			JButton Button4 = new JButton("EXIT");

			// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			// frame.setVisible(true);
			// frame.setSize(400, 200);
			// frame.add(panel,BorderLayout.CENTER);

			JButton yButton = new JButton("Yellow");

			JButton bButton = new JButton("Blue");

			JButton rButton = new JButton("Red");

			// add buttons to panel

			panel.add(yButton);

			panel.add(bButton);

			panel.add(rButton);

			// create button actions

			ColorAction yellowAction = new ColorAction(Color.yellow);

			ColorAction blueAction = new ColorAction(Color.blue);

			ColorAction redAction = new ColorAction(Color.red);

			panel.add(color, BorderLayout.NORTH);
			panel.add(yButton, BorderLayout.WEST);
			panel.add(bButton, BorderLayout.CENTER);
			panel.add(rButton, BorderLayout.EAST);
			panel.add(Button4, BorderLayout.SOUTH);

			// Button1.addActionListener(this);
			// Button2.addActionListener(this);
			// Button3.addActionListener(this);
			// Button4.addActionListener(this);

			Object[] options = { "1-Green", "2-Yellow", "3-Blue","4 -Dark Gray", "5 -Exit" };

			int col = JOptionPane.showOptionDialog(null, null, "CHANGE COLOR", JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.PLAIN_MESSAGE, null, options, options[4]);

			// panel.setVisible(true);

			// if (rc==-1) {
			// System.out.println("Dialog closed without clicking a button.");
			// }
			// else {
			// System.out.println(options[rc] + " was clicked");
			// }
			if (col == 0) {
				bb.changeColor(Color.GREEN, panelCalendar);
				// c = Color.GREEN;
			} else if (col == 1) {
				bb.changeColor(Color.YELLOW, panelCalendar);
				// c = Color.YELLOW;
			} else if (col == 2) {
				bb.changeColor(Color.BLUE, panelCalendar);
				// c = Color.BLUE;

			} 
			else if(col ==3){
				bb.changeColor(Color.DARK_GRAY, panelCalendar);
			}else if (col == 4) {
				try {
					bb.quit();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}

	}

	public void refreshCalendar(int month, int year) {
		// Variables
		String[] months = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
				"October", "November", "December" };
		int nod, som; // Number Of Days, Start Of Month

		// Allow/disallow buttons
		ButtonPrev.setEnabled(true);
		ButtonNext.setEnabled(true);

		lblMonth.setText(months[month] + "  " + String.valueOf(year)); // Refresh
																		// the
																		// month
																		// label
																		// (at
																		// the
																		// top)
		lblMonth.setBounds(10, 25, 180, 25);
		// Clear table
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				mtblCalendar.setValueAt(null, i, j);
			}
		}

		// Get first day of month and number of days
		GregorianCalendar cal = new GregorianCalendar(year, month, 1);
		nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		som = cal.get(GregorianCalendar.DAY_OF_WEEK);

		// Draw calendar
		for (int i = 1; i <= nod; i++) {
			int row = new Integer((i + som - 2) / 7);
			int column = (i + som - 2) % 7;
			mtblCalendar.setValueAt(i, row, column);
		}

		// Apply renderers
		tblCalendar.setDefaultRenderer(tblCalendar.getColumnClass(0), new tblCalendarRenderer());
	}

	/**
	 * serves as view
	 * 
	 * @param e
	 */
	@Override
	public void stateChanged(ChangeEvent e) {
		refreshCalendar(bb.getPrintCal().get(GregorianCalendar.MONTH), bb.getPrintCal().get(GregorianCalendar.YEAR));
	}

	/**
	 * table renderer
	 */
	class tblCalendarRenderer extends DefaultTableCellRenderer {
		public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused,
				int row, int column) {
			super.getTableCellRendererComponent(table, value, selected, focused, row, column);
			setHorizontalAlignment(JLabel.CENTER);
			setBackground(new Color(200, 200, 200));

			if (value != null) {
				if (Integer.parseInt(value.toString()) == rDay
						&& bb.getPrintCal().get(GregorianCalendar.MONTH) == rMonth
						&& bb.getPrintCal().get(GregorianCalendar.YEAR) == rYear) { // Today
					setBackground(Color.GRAY);
					setFont(getFont().deriveFont(Font.BOLD));
				}
			}
			if (value != null) {
				if (Integer.parseInt(value.toString()) == bb.getPrintCal().get(GregorianCalendar.DAY_OF_MONTH)) {
					setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
				}
			}
			setForeground(Color.black);
			return this;
		}
	}

	/*
	 * Action listener: serves as controller
	 */
	class ButtonPrev_Action implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			bb.preDay();
		}
	}

	class ButtonNext_Action implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			bb.nextDay();
		}
	}

	class create_Action implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// create the input dialog
			JTextField eventTitle = new JTextField("Untitled event");
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			JTextField date = new JTextField(df.format(bb.getPrintCal().getTime()));
			JTextField startTime = new JTextField("HH:mm (In 24h Format)");
			JLabel label = new JLabel("TO");
			JTextField endTime = new JTextField("HH:mm (Put NA if not apply)");

			JPanel panel = new JPanel(new BorderLayout());
			panel.add(eventTitle, BorderLayout.NORTH);
			panel.add(date, BorderLayout.CENTER);

			JPanel southPanel = new JPanel(new GridLayout(1, 3));
			southPanel.add(startTime);
			southPanel.add(label);
			label.setHorizontalAlignment(JLabel.CENTER);
			southPanel.add(endTime);
			panel.add(southPanel, BorderLayout.SOUTH);

			int result = JOptionPane.showConfirmDialog(null, panel, "CREATE EVENT", JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.PLAIN_MESSAGE);

			if (result == JOptionPane.OK_OPTION) {
				String title = eventTitle.getText();
				String dateStr = date.getText();
				String startStr = startTime.getText();
				String endStr = endTime.getText();
				bb.createEvent(title, dateStr, startStr, endStr);
			} else {
				System.out.println("Cancelled");
			}

		}

	}

	class quit_Action implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				bb.quit();
			} catch (Exception expt) {
				System.out.println("Load Failed!");
			}
		}
	}

	private class ColorAction implements ActionListener

	{

		public ColorAction(Color c)

		{

			backgroundColor = c;

		}

		public void actionPerformed(ActionEvent event)

		{

			panelCalendar.setBackground(backgroundColor);

			// repaint();

		}

		private Color backgroundColor;

	}

}