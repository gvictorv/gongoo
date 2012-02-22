package org.gongoo.xkms.provider.operation;

import javax.xml.ws.WebServiceContext;

import org.w3._2002._03.xkms.RegisterRequestType;
import org.w3._2002._03.xkms.RegisterResultType;

public interface RegisterOperation {

	RegisterResultType register(RegisterRequestType request, WebServiceContext context);
}
