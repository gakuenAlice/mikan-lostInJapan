using UnityEngine;
using System.Collections;

public class LoadLevelController : MonoBehaviour {

    public int level = 0;

    public void loadLevel()
    {
        Application.LoadLevel(level);
    }
}
