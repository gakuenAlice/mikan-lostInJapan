using UnityEngine;
using UnityEngine.UI;
using System.Collections;

public class ProfileDetailsController : MonoBehaviour {

    public static ProfileDetailsController instance;


    private Text profileName;
    // Use this for initialization
	void Start () {
        profileName = GetComponent<Text>();    
	}

    void Awake()
    {
        if (instance == null)
        {
            instance = this;
            DontDestroyOnLoad(gameObject);
        }
        else {
            Destroy(this);
        }

    }

    public void setName(string s)
    {
        profileName.text = s;
    }

    public void setInvalid()
    {
        profileName.text = "Please select a player.";
    }


	
	
}
