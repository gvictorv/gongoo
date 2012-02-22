package org.gongoo.xkms.provider.operation;

import javax.xml.ws.WebServiceContext;

import org.w3._2002._03.xkms.PendingRequestType;
import org.w3._2002._03.xkms.ResultType;

public interface PendingOperation {

	ResultType pending(PendingRequestType request, WebServiceContext context);
	
}
