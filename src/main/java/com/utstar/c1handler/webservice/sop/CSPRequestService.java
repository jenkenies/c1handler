/**
 * CSPRequestService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.utstar.c1handler.webservice.sop;

public interface CSPRequestService extends javax.xml.rpc.Service {
    public String getctmsAddress();

    public com.utstar.c1handler.webservice.sop.CSPRequest getctms() throws javax.xml.rpc.ServiceException;

    public com.utstar.c1handler.webservice.sop.CSPRequest getctms(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
