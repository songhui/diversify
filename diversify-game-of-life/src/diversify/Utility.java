package diversify;

import java.util.ArrayList;
import java.util.List;

public class Utility {
	public static int[][] get2DArray(int x, int y){
		return new int[x][y];
	}
	
	public static int[][] get2DArrayFromList(ArrayList<ArrayList<Integer>> list){
		int[][] res = new int[list.size()][list.get(0).size()];
		for(int i = 0; i < list.size(); i++)
			for(int j = 0; j < list.get(i).size(); j++)
				res[i][j]=list.get(i).get(j);
		return res;
	}
}
