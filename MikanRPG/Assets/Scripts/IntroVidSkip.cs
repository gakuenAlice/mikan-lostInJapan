using UnityEngine;
using UnityEngine.UI;
using System.Collections;

public class IntroVidSkip : MonoBehaviour {

	public Button skipBtn;

	// Use this for initialization
	void Start () {

		skipBtn = skipBtn.GetComponent<Button> ();
	
	}
	
	public void SkipPress(){
	
		Application.LoadLevel (2);
	
	}
}
