/**
 * QuizALOGBackend.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package session;

public interface QuizALOGBackend extends java.rmi.Remote {
    public session.Answer[] loadQustionAnswers(int id) throws java.rmi.RemoteException;
    public session.Question[] loadQustions(int nbr) throws java.rmi.RemoteException;
    public java.lang.String testWebService(java.lang.String arg0) throws java.rmi.RemoteException;
}
