package com.devol.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class UnknownException extends Exception implements IsSerializable {
	private static final long serialVersionUID = -3716210112460968735L;
	public UnknownException(){
    }
    public UnknownException(String message) {
        super(message);
    }
}