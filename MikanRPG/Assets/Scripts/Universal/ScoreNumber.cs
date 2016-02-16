using UnityEngine;
using UnityEngine.UI;
using System.Collections;

public class ScoreNumber : MonoBehaviour {

    public static ScoreNumber instance;

    private Text text;
	// Use this for initialization
	void Start () {
        text = GetComponent<Text>();
        update();
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

    public void update()
    {
        text.text = PlayGlobalVariables.money + "";
    }





}
