package org.gongoo.xkms.provider.operation;

import javax.xml.ws.WebServiceContext;

import org.w3._2002._03.xkms.CompoundRequestType;
import org.w3._2002._03.xkms.CompoundResultType;

public interface CompoundOperation {

	CompoundResultType compound(CompoundRequestType request, WebServiceContext context);
	
}
