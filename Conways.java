import java.util.*;
import java.io.*;


/**
Program to demonstrate conway's game of life
This program takes input from a file named "life.txt"
and prints the output on the console window. 
The the number of generations of life and the file name can be given as command line arguements
@author : Sahitya Pavurala
//ID : 0490373
//HW 02
*/
class Conways
{
	/** originalMatrix is a member variable which is a 2d array of type char[][]*/
	private char[][] originalMatrix;
	/** rows is a member variable of type int which takes the number of rows from the input file*/
	private int rows;
	/** columns is a member variable of type int which takes the number of columns from the input file*/
	private int columns;
	
	/** fileProcess(String givenName) processes the data from the input file given and throws FileNotFoundException if the file is not found	
	 @param		givenName			name of the file given as a command line arguement
	 @return	originalMatrix		it is the matrix which is constructed from the original file data		    
	 */
	char[][] fileProcess(String givenName) throws FileNotFoundException
	{	String fileName = givenName;
		Scanner in = new Scanner(new File(fileName));
		rows = in.nextInt();
		columns = in.nextInt();
		in.nextLine();
		this.originalMatrix = new char[rows][columns];
		char[] duplicate = new char[columns];
		for(int  j = 0; j < rows;j++)
		{		
			String line = null;
			if(in.hasNextLine())
				line = in.nextLine(); 
				for (int i =0 ;i < columns ;i++)
				{
					if (line != null && i < line.length() && line.charAt(i) == '*')
						duplicate[i] ='*'; 
					else
						duplicate[i] ='-';
				}
			this.originalMatrix[j] = Arrays.copyOf(duplicate,duplicate.length);
		}
		return originalMatrix;
	}
	
	/** nextGenerations(char[][] currenMatrix) constructs a matrix of the next generation with the current matrix 
	 @param		currentMatrix		    this is the matrix of the current generation  
	 */
	void nextGenerations(char[][] currentMatrix)
	{	
		//newMatrix is a copy of the nextMatrix and is used as a parent matrix for checking and altering the current matrix
		char[][] newMatrix = new char[rows][columns]; 
		for(int i = 0; i < rows; i++)
			for(int j = 0; j < columns; j++)
				newMatrix[i][j] = currentMatrix[i][j];
		for(int i = 0;i < rows ; i++)
		{
			for(int j = 0; j < columns ; j++)
			{	int neighbours = 0 ;
				for(int k =i-1; k < i+2 ;k++)
				{
					for(int l=j-1; l < j+2 ; l++)
					{
						if(k < 0 || k >= rows || l < 0 || l >= columns){continue;}
						else if( k == i && l == j ){continue;}
						else if ( newMatrix[k][l] == '*')
							neighbours  += 1;
					}
				}
				//checks if the cell lives in the next generation
				if (newMatrix[i][j] == '*' &&( neighbours == 2 || neighbours == 3))
					currentMatrix[i][j] = '*';
				//checks if the cell is a birth cell	
				else if (newMatrix[i][j] == '-' && neighbours == 3) 
				    currentMatrix[i][j] = '*';
				else
					currentMatrix[i][j] = '-';
			}
		}	
	}
	
	
	/** displayNextGenerations(char[][] generations) diaplays the contents of the matrix of the generation passed as parameter
	 @param		genrations		    this is the matrix wich is to be printed on the output window
	 */
	void displayNextGenerations(char[][] generations)
	{
		for(int  j = 0; j < rows;j++)
		{
			System.out.println(generations[j]);
		}
	}
	
	/** The main method throws the FileNotFoundException if the file is not found
		and the number of generations is given as the first command line arguement
		and the file name is given as the second command line arguement
	 */
	public static void main(String args[]) throws FileNotFoundException
	{
		String givenName = "life.txt" ;
		int numberOfGenerations =10;
		if(args.length == 1 )
		{
			numberOfGenerations = Integer.parseInt(args[0]);
			givenName = "life.txt" ;
		}
		else if(args.length == 2 )
		{
			numberOfGenerations =  Integer.parseInt(args[0]);
			givenName = args[1];
			
		}
		Conways ob = new Conways();
		char[][] currentMatrix = ob.fileProcess(givenName);
		ob.displayNextGenerations(currentMatrix);
		//A delimiting line to separate each generation matrix
		System.out.println("===================="+0);
		for(int i =0; i < numberOfGenerations ; i++ )
		{
			ob.nextGenerations(currentMatrix);
			ob.displayNextGenerations(currentMatrix);
			System.out.println("===================="+(i+1));
		}
	}	
} 
