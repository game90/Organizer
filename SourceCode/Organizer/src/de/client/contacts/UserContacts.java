package de.client.contacts;

import java.util.ArrayList;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

public class UserContacts {
	
	private ArrayList<Contact> userContacts = new ArrayList<>();
	
	
	public UserContacts(JSONObject allUserContacts) {
		try{
			
		//Get the head (feed)
		JSONObject result = allUserContacts.getJSONObject("atom:feed");
		
		//Get all contact-entries (people)
	    JSONArray AllPeople = result.getJSONArray("atom:entry");
	    
	    for(int i=0; i<AllPeople.length();i++)
	    {
	    	Contact con=new Contact();
	    	
	    	JSONObject OneEntry= (JSONObject) AllPeople.get(i);
	    
	    	//Set the name for one contact
	    	if(!OneEntry.isNull("gd:name"))
	    	{
	    		JSONObject name=OneEntry.getJSONObject("gd:name");
		    	if(!name.isNull("gd:givenName"))
		    	{
			    	con.setGivenName(name.getString("gd:givenName"));
		    	}
		    	if(!name.isNull("gd:familyName"))
		    	{
			    	con.setFamilyName(name.getString("gd:familyName"));
		    	}
	    	}
	    	
	    	//set the email for one contact. A contact can has more than one mail
	    	if(!OneEntry.isNull("gd:email"))
	    	{
	    		
	    		//has the user only one email?
	    		if(OneEntry.get("gd:email").getClass().toString().equals("class org.codehaus.jettison.json.JSONObject"))
	    		{
		    		JSONObject email = OneEntry.getJSONObject("gd:email");
		    		if(!email.isNull("address"))
		    		{
		    			con.setEmail(email.getString("address"));
		    		}
	    		}else
	    		{
	    			JSONArray email = OneEntry.getJSONArray("gd:email");
	    			//If a user has more than one email, only the first one is shown
	    			JSONObject firstEmail = email.getJSONObject(0);
	    			con.setEmail(firstEmail.getString("address"));
	    		}
	    	}
	    	
	    	
	    	if(!OneEntry.isNull("gd:phoneNumber"))
	    	{
	    		
	    		//has the user only one phonenumber??
	    		if(OneEntry.get("gd:phoneNumber").getClass().toString().equals("class org.codehaus.jettison.json.JSONObject"))
	    		{
		    		JSONObject phone = OneEntry.getJSONObject("gd:phoneNumber");
		    		if(!phone.isNull("content"))
		    		{
		    			con.setPhone(phone.getString("content"));
		    		}
	    		}else
	    		{
	    			JSONArray phone = OneEntry.getJSONArray("gd:phoneNumber");
	    			//If a user has more than one email, only the first one is shown
	    			JSONObject firstPhone = phone.getJSONObject(0);
	    			con.setPhone(firstPhone.getString("content"));
	    		}
	    	}
	    	userContacts.add(con);
	    	
	    	
	    }
	   
	    
	   
	    
		}catch(Exception e){e.printStackTrace();}
	}


	public ArrayList<Contact> getUserContacts() {
		return userContacts;
	}
	
	

	public void setContactName()
	{
		
	}
	

}
