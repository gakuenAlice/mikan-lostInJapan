using UnityEngine;
using System.Collections;
using System;

public class PlayGlobalVariables : MonoBehaviour {
    public static int money = 16000;
    public static int experience = 20000;

    public static void addMoney(int n)
    {
        money += n;
        experience += n;

        try {
            Game.current.currentProfile.money = money;
            Game.current.currentProfile.exp = experience;
            SaveLoad.Save();
        }catch(NullReferenceException ex)
        {

        }
    }

    public static void reduceMoney(int n)
    {
        money -= n;

        try
        {
            Game.current.currentProfile.money = money;
            Game.current.currentProfile.exp = experience;
            SaveLoad.Save();
        }
        catch (NullReferenceException ex)
        {

        } 
    }

    public static bool hasEnoughExperience(int exp)
    {
        bool ret = false;

        if(experience >= exp)
        {
            ret = true;
        }

        return ret;
    }



}
