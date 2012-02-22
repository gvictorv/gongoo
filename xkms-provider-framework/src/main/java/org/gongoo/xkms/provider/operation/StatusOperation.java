package org.gongoo.xkms.provider.operation;

import javax.xml.ws.WebServiceContext;

import org.w3._2002._03.xkms.StatusRequestType;
import org.w3._2002._03.xkms.StatusResultType;

public interface StatusOperation {

	StatusResultType status(StatusRequestType request, WebServiceContext context);
	
}
