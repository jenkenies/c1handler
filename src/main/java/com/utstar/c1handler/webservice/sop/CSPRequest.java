/**
 * CSPRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.utstar.c1handler.webservice.sop;

public interface CSPRequest extends java.rmi.Remote {
    public com.utstar.c1handler.webservice.sop.CSPResult execCmd(String CSPID, String LSPID, String correlateID, String cmdFileURL) throws java.rmi.RemoteException;
    public com.utstar.c1handler.webservice.sop.CSPResult resultNotify(String CSPID, String LSPID, String correlateID, int cmdResult, String resultFileURL) throws java.rmi.RemoteException;
}
