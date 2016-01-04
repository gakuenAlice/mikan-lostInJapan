using UnityEngine;
using UnityEngine.UI;
using System.Collections;
using System.Collections.Generic;
using System.Runtime.Serialization.Formatters.Binary;
using System.IO;

public static class SaveLoad{

	public static ProfileList list= new ProfileList();
	public static string fileName = "savedGames.gd";

	public static void Save(){
	
		list.savedGames.Add (Game.current);
		BinaryFormatter bf = new BinaryFormatter ();
		FileStream file = File.Create (Application.persistentDataPath + "/" + fileName);
		bf.Serialize (file, SaveLoad.list);
		file.Close ();

		Debug.Log ("save" + Application.persistentDataPath);
	
	}

	public static void Load(){
		
			BinaryFormatter bf = new BinaryFormatter ();
			FileStream file = File.Open (Application.persistentDataPath + "/" + fileName, FileMode.Open);
			SaveLoad.list = (ProfileList)bf.Deserialize (file);
			file.Close ();
			Debug.Log ("Loaded");
	
	}

}
