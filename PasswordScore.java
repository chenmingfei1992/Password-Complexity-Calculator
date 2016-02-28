//******************************************************************//
// AUTHOR: Mingfei Chen
// Email: mchen28@wustl.edu
// School: Washington University in Saint Louis
// Purpose: calculate the score and complexity of password.
//******************************************************************//



import java.util.regex.Matcher ;
import java.util.regex.Pattern ;


public class PasswordScore {

	public static void main(String[] args) {
	
		String[] password = { "perficient", "cardinals", "1234567890", "Cardinal$15", "CArdinals", "p@s$w0r#",
				"jA(kBauer", "jellyfish", "Jelly", "thisisareallylongpasswordthatshouldgenerateascoreof100","Example123"};
		
		for(int i=0;i<password.length;i++)
		{
			System.out.println(password[i]+": "+calculatePasswordScore(password[i]));
		}
		
	}

	public static int calculatePasswordScore( String inputPassword )
	{   
		// throw exception when input a null String
		if(inputPassword==null)
			 throw new IllegalArgumentException( "Error! password is Null! " ) ;
	    
		// Removes all leading and trailing white-space characters 
		String password = inputPassword.trim() ; 
		
		// throw exception when input password is blank
		if(password.isEmpty()) 
			 throw new IllegalArgumentException( "Error! password can not be Blank ! " ) ;
		
		//check letters those are neither 'a-z' nor 'A-z'
		Pattern patternLettersOnly = Pattern.compile( "[^a-zA-Z]");
		//check letters those are not a number like '0-9'
		Pattern patternNumbersOnly  = Pattern.compile( "[^0-9]");
		//check letters those are numbers like '0-9'
		Pattern patternNumbers  = Pattern.compile( "[0-9]");
		//check upper case letters like 'A-Z'
		Pattern patternUpperCase = Pattern.compile( "[A-Z]");
		//check lower case letters like 'a-z'
		Pattern patternLowerCase = Pattern.compile( "[a-z]");
		//check symbols those are neither letters nor numbers
		Pattern patternSymbols = Pattern.compile( "[^0-9a-zA-Z]");
		
		// length of the password
		int len = password.length(); 
		
		//Additions
		int lengthScore = len * 4;
		int upperCaseScore = (len - regexCompare( password,patternUpperCase ) )* 2;
		int lowerCaseScore = (len - regexCompare( password,patternLowerCase ) )* 2;
		int numbersScore = regexCompare( password,patternNumbers ) * 4;
		int symbolsScore = regexCompare( password,patternSymbols ) * 6;
		
		//Deductions
		int lettersOnlyScore =  regexCompare( password, patternLettersOnly)==0 ? len : 0;
		int numbersOnlyScore =  regexCompare( password, patternNumbersOnly)==0 ? len : 0;
		int consecutiveScore = countConsecutive( password ) *2;
		
		//Calculate total score by combining all the components
		int totalScore = lengthScore + upperCaseScore + lowerCaseScore + numbersScore + symbolsScore
				          - lettersOnlyScore - numbersOnlyScore -consecutiveScore ; 
		
		// maximum score is 100 and minimum score is 0
		if(totalScore > 100) return 100;
		else if(totalScore < 0 )  return 0;
		else return totalScore ; 
		
		
	}
	
	
	// help function to count how many characters in the input password match the desired pattern
	public static int regexCompare( String password, Pattern pattern ) {

		int num = 0;
		Matcher m = pattern.matcher(password) ;
		while ( m.find() ) {
			// count all the occurrences matching the pattern
			for ( int i = 0; i <= m.groupCount() ; i++ )  
				{ num++; } 
			 
		}

		return num ;
	}
	
	// help function to count how many consecutive letters or numbers are in the input password 
	public static int countConsecutive( String password) {

		int num = 0 ;
		// count all the occurrences in which the neighborhood characters are identical
		for ( int i = 0; i < password.length() - 1; i++ ) {
			if ( password.charAt(i) == password.charAt( i + 1 ) ) num++;
		}
		return num;
	}
	
	


}