
using UnityEngine;
using UnityEngine.UI;

public class ItemCardController : MonoBehaviour {

	public ItemController []items;
	public Text quantity;

	private string answerName;
	private int answerNum;


	public Text textName;
	public Text textNum;

	public ShopCustomerNumController customerNum;
	public ShopLifeController lifeNum;

	private Animator anim;
	private bool available;
	private float delay;

	// Use this for initialization
	void Start () {
		anim = GetComponent<Animator> ();
		delay = 1;
		anim.SetBool ("isAvailable", false);
		available = false;
	}
	
	// Update is called once per frame
	void Update () {

		if (available == false) {

			delay -= Time.deltaTime;
			if(delay < 0){
				generateQuestion();
				delay = 2;
				available = true;
				anim.SetBool ("isAvailable", true);
				anim.SetBool ("hasAnswered", false);
			}
		}
	}



	public void answer(){

		if (available == true) {
			if (ItemSelectedController.getSelectedItem ().getText () == answerName && answerNum == int.Parse (quantity.text)) {
				ShopSounds1.instance.playSound("win");

			} else {
				ShopSounds1.instance.playSound("fail");
				lifeNum.reduceScore();
			}


			customerNum.reduceCustomers();
	
			anim.SetBool ("isAvailable", false);
			anim.SetBool ("hasAnswered", true);
			available = false;
		}
	}

	public void generateQuestion(){
		answerName = randomItem ();
		answerNum = Random.Range (1, 10);
		textName.text = JapaneseTranslator.translate (answerName);
		textNum.text = "" + JapaneseTranslator.number (answerNum);


	}

	string randomItem(){
		int num = Random.Range (0, items.Length -1);

		return items [num].getText();
	}




}
