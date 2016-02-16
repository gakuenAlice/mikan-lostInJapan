using UnityEngine;
using UnityEngine.UI;


public class MainMenu: MonoBehaviour {

    public static MainMenu instance;

    public Text profileName;

    private int profileNumber;
    private Animator anim;

	// Use this for initialization
	void Start () {
        anim = GetComponent<Animator>();
        profileNumber = 0;
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


    public void Open(bool isOpen)
    {
        anim.SetBool("open", isOpen);
    }

    public void setProfileName(string profileName, int profileNum)
    {
        this.profileName.text = profileName;
        this.profileNumber = profileNum;
    }


    public int getProfileNumber()
    {
        return this.profileNumber;
    }

    public void exitGame()
    {
        Application.Quit();
    }
	

}
