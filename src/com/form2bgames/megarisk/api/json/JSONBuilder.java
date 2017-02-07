package com.form2bgames.megarisk.api.json;

import java.util.Map.Entry;
import java.util.Set;

import com.afollestad.json.Ason;
import com.form2bgames.megarisk.api.DataSet;

public class JSONBuilder {
	String[] path=new String[64];
	public JSONBuilder(){
		path[0]=DATA_STRING; // base data layer for JSON packet
	}

	public String getJSON(String packetType,DataSet ds){
		
		Ason ason=new Ason();
		ason.put(PACKET_STRING, packetType);
		putDataSet(ason,ds);
		
		return ason.toString();
	}

	public String getJSON(String packetType,DataSet ds,String userID){
		
		Ason ason=new Ason();
		ason.put(PACKET_STRING, packetType);
		ason.put(USER_STRING, userID);
		putDataSet(ason,ds);
		
		return ason.toString();
	}
	
	private void putDataSet(Ason as,DataSet ds){
		
		path[counter++]=ds.getKey(); // sets current path to key, then increments for next key
		if((!(ds.getDataPairs()==null))&&(!ds.getDataPairs().isEmpty())){
			String currPath=getKeyString(path);
			Set<Entry<String, Object>> s=ds.getDataPairs().entrySet();
			for(Entry<String,Object> ent:s){
				as.put(currPath+"."+ent.getKey(), ent.getValue());
			}
		}
		if((!(ds.getNestedDataSet()==null))&&(!ds.getNestedDataSet().isEmpty())){
			for(DataSet dn:ds.getNestedDataSet()){
				putDataSet(as,dn);
			}
		}
		path[--counter]=null; // decrements counter to this key, then sets to null
	}
	private String getKeyString(String[] path){
		String s="",k="";
		int level=0;
		
		while((k=path[level])!=null){
			if(level!=0){
				s+=".";
			}
			s+=k;
			level++;
		}
		return s;
	}
	private int counter=1; // counts how many datasets we are in so the proper JSON structure can be made
	public static final String DATA_STRING="data",PACKET_STRING="packetType",USER_STRING="user";
}
