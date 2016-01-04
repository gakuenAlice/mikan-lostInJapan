using UnityEngine;
using UnityEngine.UI;
using System.Collections;
using System.IO;

public class MainMenu: MonoBehaviour {

	public Canvas optionsMenu;
	public Canvas exitMenu;
	public Canvas profileMenu;	
	public Canvas profileNamePrompt;
	public Button profileBtn;
	public Button playBtn;
	public Button exitBtn;
	public Button optionBtn;
	public Button tstBtn;

	// Use this for initialization
	void Start () {

		optionsMenu = optionsMenu.GetComponent<Canvas> ();
		profileNamePrompt = profileNamePrompt.GetComponent<Canvas>();
		profileMenu = profileMenu.GetComponent<Canvas> ();
		profileBtn = profileBtn.GetComponent<Button> ();
		playBtn = playBtn.GetComponent<Button> ();
		exitBtn = exitBtn.GetComponent<Button> ();
		optionBtn = optionBtn.GetComponent<Button> ();
		//tstBtn = tstBtn.GetComponent<Button> ();
		optionsMenu.enabled = false;
		profileMenu.enabled = false;


		if (File.Exists (Application.persistentDataPath + "/" + SaveLoad.fileName)) {
		
			SaveLoad.Load ();
			profileNamePrompt.enabled = false;
			profileBtn.GetComponentInChildren<Text>().text = SaveLoad.list.latestGame;
		
		} else {

			profileNamePrompt.enabled = true;
		
		}

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

	public void ProfilePress(){
	
		profileMenu.enabled = true;
	
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
	
		Application.LoadLevel (1);
	
	}

	public void ExitGame(){
	
		Application.Quit ();	
	
	}

	public void btnTest(){
		Debug.Log ("Button test");
	}
	
}
