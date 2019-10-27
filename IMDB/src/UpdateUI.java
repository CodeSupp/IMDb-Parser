import java.awt.Window;
import java.beans.PropertyChangeEvent;

import javax.swing.SwingUtilities;


/*
 *  UpdateUI represents the 'Controller'.
 */

public class UpdateUI {
	
	
	private StartScreen startScreen;
	private Parser parser;
	
	public UpdateUI(StartScreen startScreen, Parser parser)
	{
		this.startScreen = startScreen;
		this.parser = parser;
		initListeners();
	}

	
	private void initListeners()
	{
		startScreen.getStartButton().addActionListener(e -> start());
		startScreen.getContinueButton().addActionListener(e -> continueTo());
		parser.addPropertyChangeListener(this::propertyChange);
	}


	private void start() {
	   
		startScreen.getStartButton().setEnabled(false);
		parser.execute();
	}
	
	private void propertyChange(PropertyChangeEvent e) {
		
		if("progress".equals(e.getPropertyName()))
		{
		    startScreen.updateBar((Integer) e.getNewValue());
		    
		    if(parser.getProgress() == 100)
		    {
		    	startScreen.getStartButton().setVisible(false);
		    	startScreen.getContinueButton().setVisible(true);
		    }
		}
	}
	
	
	/*
	 *  When moving from StartSceen ----> MoviesScreen
	 *  we closing the StartScreen.
	 */
	
	private void continueTo()
	{
		Window w = SwingUtilities.getWindowAncestor(startScreen.getPanel());
		w.dispose();
		new MoviesScreen(parser.getListButtonImages());
	}
}
