package org.gongoo.xkms.provider.operation;

import javax.xml.ws.WebServiceContext;

import org.w3._2002._03.xkms.ValidateRequestType;
import org.w3._2002._03.xkms.ValidateResultType;

public interface ValidateOperation {

	ValidateResultType validate(ValidateRequestType request, WebServiceContext context);
	
}
