using UnityEngine;
using UnityEngine.UI;
using System.Collections;

public class MainMenu: MonoBehaviour {

	public Canvas optionsMenu;
	public Canvas exitMenu;
	public Button playBtn;
	public Button exitBtn;
	public Button optionBtn;

	// Use this for initialization
	void Start () {

		optionsMenu = optionsMenu.GetComponent<Canvas> ();
		playBtn = playBtn.GetComponent<Button> ();
		exitBtn = exitBtn.GetComponent<Button> ();
		optionBtn = optionBtn.GetComponent<Button> ();
		optionsMenu.enabled = false;

	}
	
	public void ExitPress(){
	
		exitMenu.enabled = true;
		playBtn.enabled = false;
		optionBtn.enabled = false;
		exitBtn.enabled = false;
	
	}

	public void OptionsPress(){
	
		optionsMenu.enabled = true;
		playBtn.enabled = false;
		optionBtn.enabled = false;
		exitBtn.enabled = false;

	}

	public void OkOptions(){
		
		optionsMenu.enabled = false;
		playBtn.enabled = true;
		optionBtn.enabled = true;
		exitBtn.enabled = true;
		
	}

	public void NoPress(){
	
		exitMenu.enabled = false;
		playBtn.enabled = true;
		optionBtn.enabled = true;
		exitBtn.enabled = true;
		
	}

	public void StartPlay(){
	
		Application.LoadLevel (3);

	
	}

	public void ExitGame(){
	
		Application.Quit ();	
	
	}
}
