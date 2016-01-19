package pt.fccn.arquivo.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import pt.fccn.arquivo.pages.IndexPage;
import pt.fccn.arquivo.pages.OpenSearchPage;
import pt.fccn.arquivo.pages.SearchPage;
import pt.fccn.saw.selenium.WebDriverTestBase;



public class TestSearchOneTermOpenSearch extends WebDriverTestBase{
	@Test
	 public void testSearchOneTermOpenSearch() {
	 System.out.print("Running TestSearchOneTermOpenSearch. \n");
	 String term = "fccn";
     IndexPage index = new IndexPage(driver);
     OpenSearchPage searchResults = index.opensarch(term);
  
     try {
		assertTrue("The search did not return results",searchResults.existsResults());
		assertTrue("The search result from opensearch and search are not coherent",searchResults.inspectCoherence(titleOfFirstResult));
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 }
}


