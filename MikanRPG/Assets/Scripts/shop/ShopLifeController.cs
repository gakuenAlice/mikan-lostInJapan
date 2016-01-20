using UnityEngine;
using System.Collections;
using UnityEngine.UI;

public class ShopLifeController : MonoBehaviour {


	public int score;
	private Text text; 

	// Use this for initialization
	void Start () {

		text = GetComponent<Text> ();

		if (score < 0) {
			score = 1;
		}
		text.text = "" + score;
	}
	
	// Update is called once per frame
	void Update () {
	
	}

	public void reduceScore(){
		score--;
		text.text = "" + score;
	}

	public int getScore(){
		return score;
	}


}
