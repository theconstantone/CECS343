import java.awt.*;
import java.io.IOException;
import javax.swing.JFrame;


public class MainCal {
	static JFrame mainFrame;
	/**
	 * sets the frame up 
	 * @param args
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public static void main(String[] args) throws ClassNotFoundException, IOException{
		BackBone model = new BackBone();
		CalPanel myPanel = new CalPanel(model);
		SchedulePanel dayPanel = new SchedulePanel(model);
		model.attach(myPanel);
		model.attach(dayPanel);
		mainFrame = new JFrame ("Calendar"); //Create frame
		mainFrame.setSize(620, 400); //Set size to 400x400 pixels
		mainFrame.setLayout(new BorderLayout());
		mainFrame.add(myPanel, BorderLayout.WEST);
		mainFrame.add(dayPanel, BorderLayout.EAST);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Close when X is clicked
		mainFrame.setResizable(false);
		mainFrame.setVisible(true);
	}
}
