
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;



/* 
 *  StartScreen just have a progress bar, 2 buttons and an image.
 *  StartButton: Starts the parser
 *  ContinueButton: Shows up when the download is completed and moves 'User' to MoviesScreen.
 */


public class StartScreen extends JPanel{
	
	
	private JProgressBar progressBar;
	private JButton startButton;
	private JButton continueButton;
	private JFrame startFrame;
	
    private BufferedImage bi;
	
	public StartScreen() {
		
		setPreferredSize(getPreferredSize());
		buildImage();
		
        //ProgressBar
		progressBar = new JProgressBar(0, 100);
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		
		//Buttons
		startButton = new JButton("Start");
		startButton.setFocusPainted(false);
		
		continueButton = new JButton("Continue");
		continueButton.setFocusPainted(false);
		continueButton.setVisible(false);
		
	    //Adding components
		add(progressBar);
		add(startButton);
		add(continueButton);
		
		//Setting the frame
		startFrame = new JFrame();
		startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		startFrame.add(this);
		startFrame.setResizable(false);
		startFrame.pack();
		startFrame.setLocationRelativeTo(null);
		
		startFrame.setVisible(true);
	}
	
	private void buildImage() {
		
		try {
			BufferedImage image = ImageIO.read(new File("background.jpg"));
			int w = image.getWidth();
			int h = image.getHeight();
			bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
			Graphics g = bi.getGraphics();
			g.drawImage(image, 0, 0, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(1300, 700);
	}
	
	public JButton getStartButton()
	{
		return startButton;
	}
	
	public void updateBar(int value) {
		progressBar.setValue(value);
	}
   
	public JProgressBar getProgressBar() {
		return progressBar;
	}
	
	public JButton getContinueButton() {
		return continueButton;
	}
	
	public JPanel getPanel() {
		return this;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		g.drawImage(bi, 0, 0, null);
		
	}
   
}
