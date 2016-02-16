using UnityEngine;
using System.Collections.Generic;

public class ProfileListController : MonoBehaviour {

    public PlayerNameItem[] listPlayer;

    private int lastIndx;
    private int lastSelected = 0;

    void Start ()
    {
        Game.current = null;
        lastIndx = -1;
       
        foreach(PlayerNameItem item in listPlayer)
        {
            item.gameObject.SetActive(false);
        }

        loadPlayers();
    }

    public void addPlayerIntro()
    {
        string playerName = InputPlayerNameIntro.instance.getText();

        if (string.IsNullOrEmpty(playerName))
        {
            ErrorMessageController.instance.setMessage("Please input a name");
            ErrorMessageController.instance.open(true);
        }
        else
        {
            if (validateName(playerName))
            {
                if (lastIndx >= -1 && lastIndx < listPlayer.Length - 1 && validateName(playerName))
                {
                    Game newGame = new Game();
                    Profile prof = new Profile();
                    prof.profileName = playerName;
                    newGame.currentProfile = prof;
                   

                    ++lastIndx;
                    listPlayer[lastIndx].gameObject.SetActive(true);
                    listPlayer[lastIndx].setName(playerName);

                    if (lastIndx == 0)
                    {
                        lastSelected = 0;
                        IntroCanvasController.instance.open(false);
                        MainMenu.instance.setProfileName(playerName, 0);
                        MainMenu.instance.Open(true);
                    }

                    Game.current = newGame;
                    SaveLoad.AddSavedGame(newGame);

                }
            }
            else
            {
                ErrorMessageController.instance.setMessage("Name already taken");
                ErrorMessageController.instance.open(true);
            }

        }
    }

    public void addPlayer()
    {
        string playerName = InputPlayerNameController.instance.getText();

        if (string.IsNullOrEmpty(playerName))
        {
            ErrorMessageController.instance.setMessage("Please input a name");
            ErrorMessageController.instance.open(true);
        }
        else
        {
            if (validateName(playerName))
            {
                if (lastIndx >= -1 && lastIndx < listPlayer.Length - 1 && validateName(playerName))
                {
                    Game newGame = new Game();
                    Profile prof = new Profile();
                    prof.profileName = playerName;
                    newGame.currentProfile = prof;
                    Game.current = newGame;
                    SaveLoad.AddSavedGame(newGame);

                    ++lastIndx;
                    listPlayer[lastIndx].gameObject.SetActive(true);
                    listPlayer[lastIndx].setName(playerName);

                    if (lastIndx == 0)
                    {
                        updateSelected(listPlayer[lastIndx].getName());
                        listPlayer[lastIndx].selected(true);
                    }


                }
                else
                {
                    ErrorMessageController.instance.setMessage("Too many users");
                    ErrorMessageController.instance.open(true);
                }
            }
            else
            {
                ErrorMessageController.instance.setMessage("Name already taken");
                ErrorMessageController.instance.open(true);
            }

        }

    }

    public void deletePlayer(int indx)
    {
        if(indx <= lastIndx && indx >= 0 ) 
        {
            Game.current = null;
            SaveLoad.DeleteGame(listPlayer[indx].getName());
            for (int i = indx; i < lastIndx; ++i)
            {
                listPlayer[i].setName(listPlayer[i + 1].getName());
            }

            if(lastSelected == indx && indx > 0)
            {
                listPlayer[lastSelected].selected(false);
                lastSelected = indx - 1;
                listPlayer[lastSelected].selected(true);
                updateSelected(listPlayer[lastSelected].getName());
            }

            listPlayer[lastIndx].gameObject.SetActive(false);
            lastIndx--;

            if(lastIndx == -1)
            {
                updateSelected(null);
            }
            
        }
    }

    public void selectPlayer(int indx)
    { 

        if (indx >= 0 && indx <= lastIndx)
        {
            listPlayer[lastSelected].selected(false);
            listPlayer[indx].selected(true);
            lastSelected = indx;

            updateSelected(listPlayer[indx].getName());

        }

    }

    public void updateSelectedPlayer()
    {
        if(lastSelected >= 0 && lastSelected <= lastIndx)
        {
            listPlayer[lastSelected].selected(true);
            updateSelected(listPlayer[lastSelected].getName());
            MainMenu.instance.setProfileName(listPlayer[lastSelected].getName(), lastSelected);
        }
    }

    


    public void loadPlayers()
    {
        SaveLoad.Load();
        List<Game> list = SaveLoad.list.savedGames;

        for(int i = 0; i < list.Count && i < listPlayer.Length; i++) {
            listPlayer[i].gameObject.SetActive(true);
            listPlayer[i].setName(list[i].currentProfile.profileName);
            Debug.Log(listPlayer[i].getName());
        }


        if (list.Count > 0)
        {
            lastIndx = list.Count - 1;
            MainMenu.instance.setProfileName(listPlayer[0].getName(), 0);
            MainMenu.instance.Open(true);
            Game.current = SaveLoad.list.getProfile(listPlayer[0].getName());
        }
        else
        {
            IntroCanvasController.instance.open(true);
        }

        

    }

    public bool playerExist(string x)
    {
        bool ret = false;


        for(int i = 0; i < lastIndx; ++i)
        {
            if (listPlayer[i].getName().Equals(x))
            {
                Debug.Log(listPlayer[i].getName() + "compared with " + x);
                ret = true;
                break;
            }
        }

        return ret;
    }

    public bool validateName(string x)
    {
        bool ret = false;

        if(x.Length > 0)
        {
            ret = !playerExist(x);
        }

        return ret;
    }

    public void updateSelected(string s)
    {
        if (string.IsNullOrEmpty(s) == false)
        {
            Game.current = SaveLoad.list.getProfile(s);
            ProfileDetailsController.instance.setName(s);
        }
        else
        {
            Game.current = null;
            ProfileDetailsController.instance.setInvalid();
        }
    }



}
