
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

class CardParser {

	private String urlString;
	private ArrayList<HearthstoneCard> theMinions;
	
	public CardParser(String urlString)
	{
		//initial fields
		this.urlString = urlString;
		theMinions = new ArrayList<HearthstoneCard>();

		URLReader hearthstoneURLReader = new URLReader (this.urlString);
		Object obj = JSONValue.parse(hearthstoneURLReader.getTheURLContents());
		
	    if(obj instanceof JSONArray)
	    {
	    	JSONArray array = (JSONArray)obj;
	    	
		    for(int i = 0; i < array.size(); i++)
		    {
		    	JSONObject cardData = (JSONObject)array.get(i);
		    	if(cardData.containsKey("cost") && cardData.containsKey("name"))
		    	{
		    		if(cardData.containsKey("type") && cardData.get("type").equals("MINION"))
		    		{
		    			//I am only here is this is a minion card!!!
		    			System.out.println(cardData.keySet().toString());
		    			String name = (String)cardData.get("name");
		    			int cost = Integer.parseInt(cardData.get("cost").toString());
		    			int attack = Integer.parseInt(cardData.get("attack").toString());
		    			int health = Integer.parseInt(cardData.get("health").toString());
		    			HearthstoneCard tmp =new HearthstoneCard (name, cost, attack, health);
		    			theMinions.add(tmp);
		    		}
		    	}
		    	
		    }
	    }
	}
	
	public void showMinions()
	{
		for(int i = 0; i < this.theMinions.size(); i++)
		{
			this.theMinions.get(i).display();
		}
	}
	
	/*********************below code is code for HW10 JUNGCHAN LEE*********************************/
	
	public void sort()
	{
		for (int i=0; i < theMinions.size(); i++)
		{
			for (int j = i+1; j < theMinions.size(); j++)
			{
				HearthstoneCard tmp1 = this.theMinions.get(i);
				HearthstoneCard tmp2 = this.theMinions.get(j);
				
				if(tmp1.getAttack() > tmp2.getAttack())
				{
					HearthstoneCard tmp = this.theMinions.get(i);
					theMinions.set(i, theMinions.get(j));
					theMinions.set(j, tmp);
				}
			}
		}
		System.out.println("sorting is done!");
	}
	
	
	
	
	public void binarySearch_HomeworkCode(int key)
	{
		int left=0;
		int right = theMinions.size()-1;
		int mid ;
		
		while (left <= right)
		{
			mid = (left + right)/2;
			
			HearthstoneCard tmp = this.theMinions.get(mid);
			
			if(key==tmp.getAttack())
			{
				System.out.format("**************\nThe card first found which has attack of %d is %s\n**************", key, tmp.getName());
				break;
			}
			if(key<tmp.getAttack())
			{
				right = mid-1;
			}
			else
			{
				left = mid+1;
			}
			
		}
		
	}
	
}
