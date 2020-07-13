package presentation.ejb;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ScoresTable extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ScoresTable(Object[][] data) {
		//headers for the table
        String[] columns = new String[] {
            "Pseudo", "Email", "Score"
        };
        this.setSize(400,225);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		//create table with data
        JTable table = new JTable(data, columns){
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {                
                    return false;               
            };
        };
        
        //add the table to the frame
        this.add(new JScrollPane(table));
         
        this.setTitle("Scores");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
        //this.pack();
        WindowAdapter closeingListenern = new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				exit();
			}
		};
		addWindowListener(closeingListenern);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}
	private void exit() {
		this.dispose();
	}
}
