/**
 * Copyright (C) 2011 Simao Fontes <simao.fontes@fccn.pt>
 * Copyright (C) 2011 SAW Group - FCCN <saw@asa.fccn.pt>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package pt.fccn.arquivo.pages;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author Simao Fontes
 *
 */
public class HighlightsPage {
    private final WebDriver driver;
    
    private static final String titleTextEN = "Highlighted archive pages - Arquivo.pt";
    
    
    private static final String titleTextPT = "Páginas arquivadas em destaque - Arquivo.pt";
    private static final String titleSaramago = "José Saramago";
    private static final String titleOjogo ="Soccer player Luís Figo nominated as the 2001 FIFA World Player of the Year";
    private static final String titleClix = "UEFA Euro 2004";
    private static final String titleExpo = "EXPO '98";
    private static final String titlePublico = "Público newspaper";
    private static final String titleSapo = "Sapo";
    private static final String titleTim = "Tim Berners-Lee";
    private static final String titlePresidenciais ="portuguese presidentials of 2001";
    private static final String h1Title= "Highlighted archive pages";
    
    public HighlightsPage(WebDriver driver) {
        this.driver = driver;
        // Check that we're on the right page.
        String pageTitle= driver.getTitle();
        //pageTitle=pageTitle.replace("", ")");
//        System.out.print("\nTitulo: "+driver.getTitle()+"\n"+"URL: "+driver.getCurrentUrl()+"\n");
//        System.out.print("\nTituloEN: "+titleTextEN+"\n");
        if (!(pageTitle.contentEquals(titleTextEN) || (pageTitle.contentEquals(titleTextPT)))){
            throw new IllegalStateException("This is not the " + this.getClass().getName()+ " page\n Title of current page: " + pageTitle);
        }
    }
    
    /**
     * Verify if page has correct text
     * @return true if page contains the expected text
     */
    public boolean isPageCorrect() {
    	
        return (h1Title.compareTo(driver.findElement(By.cssSelector("h1")).getText()) == 0);
    }
    
    /**
     * Run through the links of highlights
     * @return true if all links from 
     */
    public boolean goThroughHighlights()  {
    	
        List <WebElement> listOfHighlights = driver.findElements(By.id("boxes"));
        for (WebElement element : listOfHighlights) {
        	element.click();
        	
        }
        return true;
    }
    
    
    /**
     * @return true if all of links are not broken
     */
    public boolean checkLinkHighligths(){
    	List<WebElement> linkList= driver.findElements(By.tagName("a"));
   	 int statuscode=0;
   	String title=null;
       for(int i=0 ; i<linkList.size() ; i++)
       {
       	if(linkList.get(i).getAttribute("href") != null)
       	  {
       		if (linkList.get(i).getAttribute("href").contains("/wayback/wayback")){
				statuscode=getResponseCode(linkList.get(i).getAttribute("href"));
				if (statuscode!= 200){
					throw new IllegalStateException("This link "+ linkList.get(i).getAttribute("href") + " is broken." );
				}
       		}
       	  }	
       }
       return true;
    }
    
    /**
     * @return true if all of the links are correct
     */
    public boolean checkHighligthsPageLinks(){
    	List<WebElement> linkList= driver.findElements(By.tagName("a"));
    	String title=null;	
       for(int i=0 ; i<linkList.size() ; i++)
       {
       	if(linkList.get(i).getAttribute("href") != null)
       	  {
       		if (linkList.get(i).getAttribute("href").contains("/wayback/wayback")){
       	 		title=linkList.get(i).getAttribute("title").trim().toLowerCase();
       	 		if (!inspectTitlesMatched(title)){
       	 		throw new IllegalStateException("This link "+ linkList.get(i).getAttribute("href") + " is wrong." );
       	 		}
				
       		}
       	  }	
       }
       return true;
    }
    /**
     * titlepage: current driven title
     * @return true if matches any title
     */
    public boolean inspectTitlesMatched(String titlepage){
    	if (titlePresidenciais.trim().toLowerCase().equals(titlepage)){
    		return true;
    	}
		    
		if (titleClix.trim().toLowerCase().equals(titlepage)){
			return true;
		}
		
		if (titleExpo.trim().toLowerCase().equals(titlepage)){
			return true;
		}
		
		if (titleOjogo.trim().toLowerCase().equals(titlepage)){
			return true;
		}
		if (titlePublico.trim().toLowerCase().equals(titlepage)){
			return true;
		}
		
		if (titleSaramago.trim().toLowerCase().equals(titlepage)){
			return true;
		}
		
		if (titleSapo.trim().toLowerCase().equals(titlepage)){
			return true;
		}
		
		if (titleTim.trim().toLowerCase().equals(titlepage)){
			return true;
		}
		else
			return false;
    	
    	
    }
    
    /**
     * @param urlString
     * @return the statuscode from the page
     */
    public int getResponseCode(String urlString) {         
        URL u=null;
        HttpURLConnection huc=null;
		try {
			u = new URL(urlString);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        try {
			huc = (HttpURLConnection) u.openConnection();
			huc.setRequestMethod("GET");  
	        huc.connect();  
	        return huc.getResponseCode();  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;  
        
  } 
}
