package org.gongoo.xkms.provider.operation;

import javax.xml.ws.WebServiceContext;

import org.w3._2002._03.xkms.ReissueRequestType;
import org.w3._2002._03.xkms.ReissueResultType;

public interface ReissueOperation {

	ReissueResultType reissue(ReissueRequestType request, WebServiceContext context);
	
}
