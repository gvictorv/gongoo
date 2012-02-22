

package org.gongoo.xkms.provider;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.ws.Provider;
import javax.xml.ws.Service;
import javax.xml.ws.ServiceMode;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.apache.cxf.binding.soap.SoapFault;
import org.apache.cxf.binding.soap.SoapVersion;
import org.apache.cxf.jaxb.JAXBContextCache;
import org.apache.cxf.jaxb.JAXBContextCache.CachedContextAndSchemas;
import org.gongoo.xkms.provider.operation.CompoundOperation;
import org.gongoo.xkms.provider.operation.LocateOperation;
import org.gongoo.xkms.provider.operation.PendingOperation;
import org.gongoo.xkms.provider.operation.RecoverOperation;
import org.gongoo.xkms.provider.operation.RegisterOperation;
import org.gongoo.xkms.provider.operation.ReissueOperation;
import org.gongoo.xkms.provider.operation.RevokeOperation;
import org.gongoo.xkms.provider.operation.StatusOperation;
import org.gongoo.xkms.provider.operation.ValidateOperation;
import org.w3._2002._03.xkms.CompoundRequestType;
import org.w3._2002._03.xkms.LocateRequestType;
import org.w3._2002._03.xkms.ObjectFactory;
import org.w3._2002._03.xkms.PendingRequestType;
import org.w3._2002._03.xkms.RecoverRequestType;
import org.w3._2002._03.xkms.RegisterRequestType;
import org.w3._2002._03.xkms.ReissueRequestType;
import org.w3._2002._03.xkms.RevokeRequestType;
import org.w3._2002._03.xkms.StatusRequestType;
import org.w3._2002._03.xkms.ValidateRequestType;

@ServiceMode(value = Service.Mode.PAYLOAD)
public class XMLKeyManagementServiceProvider implements Provider<Source> {

    private static final String XKMS_NAMESPACE = "http://www.w3.org/2002/03/xkms#wsdl";
    private static final String XKMS_REQUESTTYPE_COMPOUND = XKMS_NAMESPACE
            + "/Compound";
    private static final String XKMS_REQUESTTYPE_LOCATE = XKMS_NAMESPACE
            + "/Locate";
    private static final String XKMS_REQUESTTYPE_PENDING = XKMS_NAMESPACE
            + "/Pending";
    private static final String XKMS_REQUESTTYPE_RECOVER = XKMS_NAMESPACE
            + "/Recover";
    private static final String XKMS_REQUESTTYPE_REGISTER = XKMS_NAMESPACE
            + "/Register";
    private static final String XKMS_REQUESTTYPE_REISSUE = XKMS_NAMESPACE
            + "/Reissue";
    private static final String XKMS_REQUESTTYPE_REVOKE = XKMS_NAMESPACE
    		+ "/Revoke";
    private static final String XKMS_REQUESTTYPE_STATUS = XKMS_NAMESPACE
    		+ "/Status";
    private static final String XKMS_REQUESTTYPE_VALIDATE = XKMS_NAMESPACE
    		+ "/Validate";
    
    private static final Map<String, Method> OPERATION_METHODS = new HashMap<String, Method>();
    static {
        try {
            Method m = CompoundOperation.class.getDeclaredMethod("compund", 
                                                              CompoundRequestType.class, 
                                                              WebServiceContext.class);
            OPERATION_METHODS.put(XKMS_REQUESTTYPE_COMPOUND, m);
            
            m = LocateOperation.class.getDeclaredMethod("locate", 
                                                       LocateRequestType.class, 
                                                       WebServiceContext.class);
            OPERATION_METHODS.put(XKMS_REQUESTTYPE_LOCATE, m);
            
            m = PendingOperation.class.getDeclaredMethod("pending", 
                                                       PendingRequestType.class, 
                                                       WebServiceContext.class);
            OPERATION_METHODS.put(XKMS_REQUESTTYPE_PENDING, m);
            
            m = RecoverOperation.class.getDeclaredMethod("recover", 
                                                       RecoverRequestType.class, 
                                                       WebServiceContext.class);
            OPERATION_METHODS.put(XKMS_REQUESTTYPE_RECOVER, m);
            
            m = RegisterOperation.class.getDeclaredMethod("register", 
                                                       RegisterRequestType.class, 
                                                       WebServiceContext.class);
            OPERATION_METHODS.put(XKMS_REQUESTTYPE_REGISTER, m);
            
            m = ReissueOperation.class.getDeclaredMethod("reissue", 
                                                       ReissueRequestType.class, 
                                                       WebServiceContext.class);
            OPERATION_METHODS.put(XKMS_REQUESTTYPE_REISSUE, m);
            
            m = ReissueOperation.class.getDeclaredMethod("revoke", 
                    RevokeRequestType.class, 
                    WebServiceContext.class);
            OPERATION_METHODS.put(XKMS_REQUESTTYPE_REVOKE, m);

            m = ReissueOperation.class.getDeclaredMethod("status", 
                    StatusRequestType.class, 
                    WebServiceContext.class);
            OPERATION_METHODS.put(XKMS_REQUESTTYPE_STATUS, m);

            m = ReissueOperation.class.getDeclaredMethod("validate", 
                    ValidateRequestType.class, 
                    WebServiceContext.class);
            OPERATION_METHODS.put(XKMS_REQUESTTYPE_VALIDATE, m);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    

    
    protected JAXBContext jaxbContext;
    protected Set<Class<?>> jaxbContextClasses;
    
    private CompoundOperation compoundOperation;
    private LocateOperation locateOperation;
    private PendingOperation pendingOperation;
    private RecoverOperation recoverOperation;
    private RegisterOperation registerOperation;
    private ReissueOperation reissueOperation;
    private RevokeOperation revokeOperation;
    private StatusOperation statusOperation;
    private ValidateOperation validateOperation;
    
    private Map<String, Object> operationMap = new HashMap<String, Object>();

    @Resource
    private WebServiceContext context;

    public XMLKeyManagementServiceProvider() throws Exception {
        Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(ObjectFactory.class);
                
        CachedContextAndSchemas cache = 
            JAXBContextCache.getCachedContextAndSchemas(classes, null, null, null, false);
        jaxbContext = cache.getContext();
        jaxbContextClasses = cache.getClasses();
    }
    
    
    
	public void setCompoundOperation(CompoundOperation compoundOperation) {
		this.compoundOperation = compoundOperation;
		operationMap.put(XKMS_REQUESTTYPE_COMPOUND, compoundOperation);
	}

	public void setLocateOperation(LocateOperation locateOperation) {
		this.locateOperation = locateOperation;
		operationMap.put(XKMS_REQUESTTYPE_LOCATE, locateOperation);
	}

	public void setPendingOperation(PendingOperation pendingOperation) {
		this.pendingOperation = pendingOperation;
		operationMap.put(XKMS_REQUESTTYPE_PENDING, pendingOperation);
	}

	public void setRecoverOperation(RecoverOperation recoverOperation) {
		this.recoverOperation = recoverOperation;
		operationMap.put(XKMS_REQUESTTYPE_RECOVER, recoverOperation);
	}

	public void setRegisterOperation(RegisterOperation registerOperation) {
		this.registerOperation = registerOperation;
		operationMap.put(XKMS_REQUESTTYPE_REGISTER, registerOperation);
	}

	public void setReissueOperation(ReissueOperation reissueOperation) {
		this.reissueOperation = reissueOperation;
		operationMap.put(XKMS_REQUESTTYPE_REISSUE, reissueOperation);
	}


	public void setRevokeOperation(RevokeOperation revokeOperation) {
		this.revokeOperation = revokeOperation;
		operationMap.put(XKMS_REQUESTTYPE_REVOKE, revokeOperation);
	}


	public void setStatusOperation(StatusOperation statusOperation) {
		this.statusOperation = statusOperation;
		operationMap.put(XKMS_REQUESTTYPE_STATUS, statusOperation);
	}
	
	public void setValidateOperation(ValidateOperation validateOperation) {
		this.validateOperation = validateOperation;
		operationMap.put(XKMS_REQUESTTYPE_VALIDATE, validateOperation);
	}

    public Source invoke(Source request) {
        Source response = null;
        try {
			Object obj = convertToJAXBObject(request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return response;
    }
    
    private SoapFault createSOAPFault(Throwable ex) {
        String faultString = "Internal XKMS error";
        QName faultCode = null;
        
        if (ex != null) {
            faultString = ex.getMessage();
        }
        
        MessageContext messageContext = context.getMessageContext();
        SoapVersion soapVersion = (SoapVersion)messageContext.get(SoapVersion.class.getName());
        SoapFault fault;
        if (soapVersion.getVersion() == 1.1 && faultCode != null) {
            fault = new SoapFault(faultString, faultCode);
        } else {
            fault = new SoapFault(faultString, soapVersion.getSender());
            if (soapVersion.getVersion() != 1.1 && faultCode != null) {
                fault.setSubCode(faultCode);
            }
        }
        return fault;
    }

    private Object convertToJAXBObject(Source source) throws Exception {
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        JAXBElement<?> jaxbElement = (JAXBElement<?>) unmarshaller
                .unmarshal(source);
        return jaxbElement.getValue();
    }

 
}
