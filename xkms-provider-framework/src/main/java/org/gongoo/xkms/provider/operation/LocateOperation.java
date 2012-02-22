package org.gongoo.xkms.provider.operation;

import javax.xml.ws.WebServiceContext;

import org.w3._2002._03.xkms.LocateRequestType;
import org.w3._2002._03.xkms.LocateResultType;

public interface LocateOperation {

	LocateResultType locate(LocateRequestType request, WebServiceContext context);
	
}
