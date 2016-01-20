using UnityEngine;
using System.Collections;
using UnityEngine.UI;

public class EndingDialogContoller : MonoBehaviour {

	public static EndingDialogContoller instance;

	public Text mainText;
	public Text money;


	void Awake(){
		if (instance == null) {
			instance = this;
			DontDestroyOnLoad (gameObject);
		} else {
			Destroy(this);
		}
		
	}


	public void gameOver(int score){
		if (score <= 0) {
			mainText.text = "Game Over. Please practice more";
			score = 0;
		} else if (score <= 3) {
			mainText.text = "You could do better. Practice more";
		} else if (score <= 5) {
			mainText.text = "Not bad. A little practice can help";
		} else if (score == 6) {
			mainText.text = "Almost perfect. Great job";
		} else {
			mainText.text = "Perfect!";
		}

		int earned = score * 100;

		money.text = "" + earned;

		stopGame ();
		GetComponent<Animator> ().SetBool ("active", true);

	}

	private void stopGame(){
		RestaurantGlobals.stop = true;
	}
}
