using UnityEngine;
using UnityEngine.UI;
using UnityEditor.Events;
using System.Collections;
using System.Collections.Generic;
using System.IO;

public class MainMenu: MonoBehaviour {

    public static MainMenu instance;

    private Animator anim;

	// Use this for initialization
	void Start () {
        anim = GetComponent<Animator>();
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

	

}
