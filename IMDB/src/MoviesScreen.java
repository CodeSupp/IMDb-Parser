import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.net.URL;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/*
 *  MoviesScreen contains the images(posters) from movies.
 */

public class MoviesScreen extends JPanel{
	
	private JFrame frame;
	private JScrollPane scrollPane;
	private JPanel panel;
	
	public MoviesScreen(List<JButton> buttonImages)
	{
		
		setLayout(new BorderLayout());
        setPreferredSize(getPreferredSize());
        
        panel = new JPanel(new GridLayout(0, 7));
        panel.setBackground(Color.BLACK);

     
        for(JButton button : buttonImages)
        	panel.add(button);
        
        scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        
        add(scrollPane, BorderLayout.CENTER);
		 
		frame = new JFrame("Most Popular Movies - IMDb");
		 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
	    frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(1300, 700);
	}

}
