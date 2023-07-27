package main;

public class ArrayMain {
	public static void main(String[] args) {
		String[][] strArray = new String[2][3];
		strArray[0][0] = "0";
		strArray[0][1] = "0";
		strArray[0][2] = "0";
		strArray[1][0] = "1";
		strArray[1][1] = "1";
		strArray[1][2] = "1";
		
		for(int i=0;i<strArray.length;i++) {
			for(int j=0;j<strArray[i].length;j++) {
				System.out.print(strArray[i][j] + " ");
			}
			System.out.println();
		}
	}
}
