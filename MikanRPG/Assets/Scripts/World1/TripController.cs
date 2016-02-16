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
        text.text = "Pay \n¥7,000";
    }

    public void Close()
    {
        Debug.Log("Closing");
        anim.SetBool("open", false);

    }

    public void Pay()
    {
        
        if(PlayGlobalVariables.money >= 7000){
                Application.LoadLevel(7);
        }
        else if(PlayGlobalVariables.experience >= 7000){
                Application.LoadLevel(7);
        }
        else
        {
            text.text = "You don't have enough money ";
        }
    }

    public void Pay2()
    {
        if (PlayGlobalVariables.money >= 7000)
        {
            Application.LoadLevel(19);
        }
        else
        {
            text.text = "You don't have enough money ";
        }
    }


}
