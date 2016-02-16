using UnityEngine;
using UnityEngine.EventSystems;
using System.Collections;
using System;

public class ScoreBoard : MonoBehaviour, IPointerClickHandler, IPointerExitHandler, IPointerEnterHandler
{


    private Animator anim;

    void Start()
    {
        anim = GetComponent<Animator>();
    }

    public void OnPointerClick(PointerEventData eventData)
    {
        anim.SetBool("open", true);
        RestaurantGlobals.stop = true;
    }

    public void OnPointerEnter(PointerEventData eventData)
    {

        anim.SetBool("hover", true);
    }

    public void OnPointerExit(PointerEventData eventData)
    {
        anim.SetBool("hover", false);
    }

    public void Open(bool isOpen)
    {
        anim.SetBool("open", isOpen);
        RestaurantGlobals.stop = false;
    }
}
