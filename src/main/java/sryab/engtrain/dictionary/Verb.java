package sryab.engtrain.dictionary;

public class Verb extends ForeignWord {
	
	private ConnectToVerbForms connectToVerbForm;

	public ConnectToVerbForms getConnectToVerb() {
		return connectToVerbForm;
	}

	public void setConnectToVerb(ConnectToVerbForms connectToVerbForm) {
		this.connectToVerbForm = connectToVerbForm;
	}
}
