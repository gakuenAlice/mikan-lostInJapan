using UnityEngine;
using System.Collections;

[System.Serializable]
public class Profile{

	public string profileName;
	public int money;
	public float musicVol;
	public float effectsVol;

	public Profile(){

		profileName = "";
		money = 200;
		musicVol = 5.0f;
		effectsVol = 5.0f;

	}
}
