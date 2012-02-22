package org.gongoo.xkms.provider.operation;

import javax.xml.ws.WebServiceContext;

import org.w3._2002._03.xkms.RecoverRequestType;
import org.w3._2002._03.xkms.RecoverResultType;

public interface RecoverOperation {

	RecoverResultType recover(RecoverRequestType request, WebServiceContext context);
	
}
