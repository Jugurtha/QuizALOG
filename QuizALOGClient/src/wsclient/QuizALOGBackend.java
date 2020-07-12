/**
 * QuizALOGBackend.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package wsclient;

public interface QuizALOGBackend extends java.rmi.Remote {
    public void addPlayer(wsclient.Player arg0) throws java.rmi.RemoteException;
    public wsclient.Question[] loadQustions(int nbr) throws java.rmi.RemoteException;
    public int playerExist(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException;
    public java.lang.String testWebService(java.lang.String arg0) throws java.rmi.RemoteException;
    public void saveGame(wsclient.Game arg0, wsclient.GameQuestion[] arg1) throws java.rmi.RemoteException;
    public wsclient.Answer[] loadQustionAnswers(int id) throws java.rmi.RemoteException;
}
