package com.example.db.tables;

public class Words {
	private int _id;
	private String _word;
	private String _fingerprint;

	public Words() {
		
	}
	
	public Words(int id, String word, String fingerprint) {
		this._id = id;
		this._word = word;
		this._fingerprint = fingerprint;
	}
	
	public Words(String word, String fingerprint) {
		this._word = word;
		this._fingerprint = fingerprint;
	}
	
	public void setID(int id) {
		this._id = id;
	}
	
	public int getID() {
		return this._id;
	}
	
	public void setWord(String word) {
		this._word = word;
	}
	
	public String getWord() {
		return this._word;
	}
	
	public void setFingerPrint(String fingerprint) {
		this._fingerprint = fingerprint;
	}
	
	public String getFingerPrint() {
		return this._fingerprint;
	}
}
