using UnityEngine;
using System.Collections;

public class RestaurantGlobals : MonoBehaviour {

	private static int Day = 1;
	private static int score = 7;

	public static int getDay(){
		return Day;
	}

	public static void incrementDay(){
		Day++;
	}

	public static void setDay(int num){
		Day = num;
	}

	public static void setScore(){
		score = 7;
	}

	public static void reduceScore(){
		score--;
	}

	public static int getScore(){
		return score;
	}


}
