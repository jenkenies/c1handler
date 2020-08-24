/**
 * CtmsSoapBindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.utstar.c1handler.webservice.sop;

public class CtmsSoapBindingSkeleton implements com.utstar.c1handler.webservice.sop.CSPRequest, org.apache.axis.wsdl.Skeleton {
    private com.utstar.c1handler.webservice.sop.CSPRequest impl;
    private static java.util.Map _myOperations = new java.util.Hashtable();
    private static java.util.Collection _myOperationsList = new java.util.ArrayList();

    /**
    * Returns List of OperationDesc objects with this name
    */
    public static java.util.List getOperationDescByName(String methodName) {
        return (java.util.List)_myOperations.get(methodName);
    }

    /**
    * Returns Collection of OperationDescs
    */
    public static java.util.Collection getOperationDescs() {
        return _myOperationsList;
    }

    static {
        org.apache.axis.description.OperationDesc _oper;
        org.apache.axis.description.FaultDesc _fault;
        org.apache.axis.description.ParameterDesc [] _params;
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "CSPID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false),
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "LSPID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false),
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "CorrelateID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false),
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "CmdFileURL"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false),
        };
        _oper = new org.apache.axis.description.OperationDesc("execCmd", _params, new javax.xml.namespace.QName("", "ExecCmdReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("urn:ctms", "CSPResult"));
        _oper.setElementQName(new javax.xml.namespace.QName("urn:ctms", "ExecCmd"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("execCmd") == null) {
            _myOperations.put("execCmd", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("execCmd")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "CSPID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false),
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "LSPID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false),
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "CorrelateID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false),
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "CmdResult"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ResultFileURL"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false),
        };
        _oper = new org.apache.axis.description.OperationDesc("resultNotify", _params, new javax.xml.namespace.QName("", "ResultNotifyReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("urn:ctms", "CSPResult"));
        _oper.setElementQName(new javax.xml.namespace.QName("urn:ctms", "ResultNotify"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("resultNotify") == null) {
            _myOperations.put("resultNotify", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("resultNotify")).add(_oper);
    }

    public CtmsSoapBindingSkeleton() {
        this.impl = new com.utstar.c1handler.webservice.sop.CtmsSoapBindingImpl();
    }

    public CtmsSoapBindingSkeleton(com.utstar.c1handler.webservice.sop.CSPRequest impl) {
        this.impl = impl;
    }
    public com.utstar.c1handler.webservice.sop.CSPResult execCmd(String CSPID, String LSPID, String correlateID, String cmdFileURL) throws java.rmi.RemoteException
    {
        com.utstar.c1handler.webservice.sop.CSPResult ret = impl.execCmd(CSPID, LSPID, correlateID, cmdFileURL);
        return ret;
    }

    public com.utstar.c1handler.webservice.sop.CSPResult resultNotify(String CSPID, String LSPID, String correlateID, int cmdResult, String resultFileURL) throws java.rmi.RemoteException
    {
        com.utstar.c1handler.webservice.sop.CSPResult ret = impl.resultNotify(CSPID, LSPID, correlateID, cmdResult, resultFileURL);
        return ret;
    }

}
