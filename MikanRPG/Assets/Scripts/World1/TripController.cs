using UnityEngine;
using UnityEngine.UI;
using System.Collections;

public class TripController : MonoBehaviour {

    private Animator anim;
    public Text text;
    // Use this for initialization
	void Start () {
        anim = GetComponent<Animator>();
	}

    public void Open()
    {
        anim.SetBool("open", true);
        text.text = "Pay\n¥ 15,000";
    }

    public void Close()
    {
        Debug.Log("Closing");
        anim.SetBool("open", false);

    }

    public void Pay()
    {
        if(PlayGlobalVariables.firstLevelFinished == true)
        {
            Application.LoadLevel(6);
        }
        else if(PlayGlobalVariables.money >= 15000){
            Application.LoadLevel(6);
        }
        else
        {
            text.text = "You don't have enough money ";
        }
    }


}
