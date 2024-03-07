package com.clan;

import java.util.List;

import org.json.JSONObject;

import com.user.User;

public class Clan {
	String clanID;
	String clanName;
	User admin;
	List<User> coAdmins;
	List<User> clanMembers;
	List<User> joinRequests;
	
	public Clan(String clanID) {
		this.clanID = clanID;
	}
	
	public JSONObject toJSON() throws Exception{
		try {
			JSONObject clanData = new JSONObject();
			clanData.put("clanID", clanID);
			clanData.put("clanName", clanName);
			clanData.put("admin", admin.getMailID());
			clanData.put("coAdmins", coAdmins);
			clanData.put("clanMembers", clanMembers);
			clanData.put("joinRequests", joinRequests);

			return clanData;
		} catch (Exception e) {
			throw new Exception("Can't fetch clan Details");
		}

	}

	public String getClanID(){
		return clanID;
	}

	public void setClanID(String clanID){
		this.clanID = clanID;
	}

	public String getClanName(){
		return clanName;
	}

	public void setClanName(String clanName){
		this.clanName = clanName;
	}

	public User getAdmin(){
		return admin;
	}

	public void setAdmin(User admin){
		this.admin = admin;
	}

	public List<User> getCoAdmins(){
		return coAdmins;
	}

	public void setCoAdmins(List<User> coadmins){
		this.coAdmins = coAdmins;
	}
	
	public List<User> getClanMembers(){
		return clanMembers;
	}

	public void setClanMembers(List<User> clanMembers){
		this.clanMembers = clanMembers;
	} 

	public List<User> getJoinRequests(){
		return joinRequests;
	}

	public void setJoinRequests(List<User> joinRequests){
		this.joinRequests = joinRequests;
	}
	
}
