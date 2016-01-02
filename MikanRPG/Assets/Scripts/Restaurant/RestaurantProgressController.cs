using UnityEngine;
using UnityEngine.UI;
using System.Collections;

public class RestaurantProgressController : MonoBehaviour {

	public int numberOfTimes;

	private Slider timeBar;

	
	// Use this for initialization
	void Start () {
		timeBar = GetComponent<Slider> ();
		timeBar.value = 0;
	}
	
	// Update is called once per frame
	void Update () {

		if (numberOfTimes > 0) {

			timeBar.value += Time.deltaTime;

			if(timeBar.value >= timeBar.maxValue){
				numberOfTimes--;
				RestaurantGlobals.incrementDay();
				timeBar.value = 0;
			}
		
		}
	}
}
