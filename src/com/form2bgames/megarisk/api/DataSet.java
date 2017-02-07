package com.form2bgames.megarisk.api;

import java.util.ArrayList;
import java.util.HashMap;

public class DataSet{
	private String key;
	private ArrayList<DataSet> ds;
	private HashMap<String,Object> dataPairs;
	public DataSet(String key,ArrayList<DataSet> ds,HashMap<String,Object> dataPairs){
		this.key=key;
		this.ds=ds;
		this.dataPairs=dataPairs;
	}
	public ArrayList<DataSet> getNestedDataSet(){
		return ds;
	}
	public HashMap<String,Object> getDataPairs(){
		return dataPairs;
	}
	public String getKey(){
		return key;
	}
}
