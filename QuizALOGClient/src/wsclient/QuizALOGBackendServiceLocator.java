/**
 * QuizALOGBackendServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package wsclient;

public class QuizALOGBackendServiceLocator extends org.apache.axis.client.Service implements wsclient.QuizALOGBackendService {

    public QuizALOGBackendServiceLocator() {
    }


    public QuizALOGBackendServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public QuizALOGBackendServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for QuizALOGBackendPort
    private java.lang.String QuizALOGBackendPort_address = "http://LENOVO-8:8080/QuizALOGBackendService/QuizALOGBackend";

    public java.lang.String getQuizALOGBackendPortAddress() {
        return QuizALOGBackendPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String QuizALOGBackendPortWSDDServiceName = "QuizALOGBackendPort";

    public java.lang.String getQuizALOGBackendPortWSDDServiceName() {
        return QuizALOGBackendPortWSDDServiceName;
    }

    public void setQuizALOGBackendPortWSDDServiceName(java.lang.String name) {
        QuizALOGBackendPortWSDDServiceName = name;
    }

    public wsclient.QuizALOGBackend getQuizALOGBackendPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(QuizALOGBackendPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getQuizALOGBackendPort(endpoint);
    }

    public wsclient.QuizALOGBackend getQuizALOGBackendPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            wsclient.QuizALOGBackendPortBindingStub _stub = new wsclient.QuizALOGBackendPortBindingStub(portAddress, this);
            _stub.setPortName(getQuizALOGBackendPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setQuizALOGBackendPortEndpointAddress(java.lang.String address) {
        QuizALOGBackendPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (wsclient.QuizALOGBackend.class.isAssignableFrom(serviceEndpointInterface)) {
                wsclient.QuizALOGBackendPortBindingStub _stub = new wsclient.QuizALOGBackendPortBindingStub(new java.net.URL(QuizALOGBackendPort_address), this);
                _stub.setPortName(getQuizALOGBackendPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("QuizALOGBackendPort".equals(inputPortName)) {
            return getQuizALOGBackendPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://session/", "QuizALOGBackendService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://session/", "QuizALOGBackendPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("QuizALOGBackendPort".equals(portName)) {
            setQuizALOGBackendPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
