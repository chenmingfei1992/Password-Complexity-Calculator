//******************************************************************//
// AUTHOR: Mingfei Chen
// Email: mchen28@wustl.edu
// School: Washington University in Saint Louis
// Purpose: Testing cases for PasswordScore class
//******************************************************************//


import static org.junit.Assert.*;


import org.junit.Test;

public class PasswordScoreTest {

	@Test
	public void test() {
		String[] password = { "perficient", "cardinals", "1234567890", "Cardinal$15", "CArdinals", "p@s$w0r#",
				"jA(kBauer", "jellyfish", "Jelly", "thisisareallylongpasswordthatshouldgenerateascoreof100"};
		
		int[] correctScore = { 50, 45 , 100, 86, 45, 78, 62, 43, 23,100 } ;
		for (int i = 0; i < password.length; i++) {
			int scoreTest = PasswordScore.calculatePasswordScore(password[i]);
			if (scoreTest != correctScore[i]) {
				System.out.println("calculate password score error when password is:  " + password[i]);
				System.out.println("Expected: " + correctScore[i] + "     Actually: "+ scoreTest);
			}
			assertEquals(correctScore[i], scoreTest);
			
		}
	}

}
