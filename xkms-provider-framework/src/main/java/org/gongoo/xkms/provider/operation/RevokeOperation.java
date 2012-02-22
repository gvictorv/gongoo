package org.gongoo.xkms.provider.operation;

import javax.xml.ws.WebServiceContext;

import org.w3._2002._03.xkms.RevokeRequestType;
import org.w3._2002._03.xkms.RevokeResultType;

public interface RevokeOperation {

	RevokeResultType revoke(RevokeRequestType request, WebServiceContext context);
	
}
