package com.bilin.designMode.create.factory;

public class SendSmsFactory implements Provider {

	@Override
	public Sender produce() {
		// TODO Auto-generated method stub
		 return new SmsSender();
	}

}
