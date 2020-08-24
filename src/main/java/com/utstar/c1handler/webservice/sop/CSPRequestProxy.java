package com.utstar.c1handler.webservice.sop;

public class CSPRequestProxy implements com.utstar.c1handler.webservice.sop.CSPRequest {
  private String _endpoint = null;
  private com.utstar.c1handler.webservice.sop.CSPRequest cSPRequest = null;
  
  public CSPRequestProxy() {
    _initCSPRequestProxy();
  }
  
  public CSPRequestProxy(String endpoint) {
    _endpoint = endpoint;
    _initCSPRequestProxy();
  }
  
  private void _initCSPRequestProxy() {
    try {
      cSPRequest = (new com.utstar.c1handler.webservice.sop.CSPRequestServiceLocator()).getctms();
      if (cSPRequest != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)cSPRequest)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)cSPRequest)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (cSPRequest != null)
      ((javax.xml.rpc.Stub)cSPRequest)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.utstar.c1handler.webservice.sop.CSPRequest getCSPRequest() {
    if (cSPRequest == null)
      _initCSPRequestProxy();
    return cSPRequest;
  }
  
  public com.utstar.c1handler.webservice.sop.CSPResult execCmd(String CSPID, String LSPID, String correlateID, String cmdFileURL) throws java.rmi.RemoteException{
    if (cSPRequest == null)
      _initCSPRequestProxy();
    return cSPRequest.execCmd(CSPID, LSPID, correlateID, cmdFileURL);
  }
  
  public com.utstar.c1handler.webservice.sop.CSPResult resultNotify(String CSPID, String LSPID, String correlateID, int cmdResult, String resultFileURL) throws java.rmi.RemoteException{
    if (cSPRequest == null)
      _initCSPRequestProxy();
    return cSPRequest.resultNotify(CSPID, LSPID, correlateID, cmdResult, resultFileURL);
  }
  
  
}