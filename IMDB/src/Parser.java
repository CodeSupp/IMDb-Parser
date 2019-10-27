
import java.awt.Dimension;
import java.io.IOException;
import java.net.*;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingWorker;
import java.util.*;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Parser extends SwingWorker<Void, URL>{
    
	//URL ---> Most popular movies based on IMDb
	private final String URL = "https://www.imdb.com/chart/moviemeter?ref_=nv_mv_mpm";
    
	private List<JButton> buttonImages;
	private int totalMovies = 0;
	
	public Parser()
	{
	     //Initialization
		 totalMovies = 0;
		 buttonImages = new ArrayList<>();
	}
	
	/* 
	 *  doInBackground does 2 things.
	 *  First we connect to 'IMDb most popular movies'.
	 *  Second because images are too small, we need to take the redirected URL and get the bigger image.
	 */
	
	@Override
	protected Void doInBackground() {
		
		List<String> redirectedURLs;
	     
		try {
			
			//Here we connect to URL=MostPopularMovies and for each image we getting 'a' which has 'href'.
			Document document = Jsoup.connect(URL).get();
			Elements elements = document.select("tbody.lister-list").select("tr").select("td.posterColumn").select("a");
			
			//Getting the redirectedURLs 
			redirectedURLs = elements.stream()
			                    .map(element -> element.attr("abs:href"))
			                    .collect(Collectors.toList());
			
			//We open a parallelStream for speed purposes.
			//This is were we getting the bigger image(if exists) and then we publish it.
			redirectedURLs.parallelStream()
			              .forEach(redURL -> {
			            	  
			            	    try {
			            	    	
			            	    	Document doc = Jsoup.connect(redURL).get();
			            	    	Element element = doc.select("div.poster").select("a img").first();
			            	    	
			            	    	if(element != null)
			            	    	{
			            	    		publish(new URL(element.attr("src")));
			            	    	}
			            	    	
			            	    	setProgress(totalMovies+1);
			            	    	totalMovies++;
			            	    	
			            	    } catch(IOException e) {
			            	    	
			            	        e.printStackTrace();
			            	    }
	  
			              });
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	//Constructing the images.
	@Override
	protected void process(List<URL> chunks)
	{
		for(URL url : chunks)
		{
			Icon icon = new ImageIcon(url);
			JButton button = new JButton(icon);
			button.setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
			buttonImages.add(button);
			
		}
		 
	}
	
	
	public List<JButton> getListButtonImages() {
		return buttonImages;
	}
	
}
