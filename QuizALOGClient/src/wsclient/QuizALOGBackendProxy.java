package wsclient;

public class QuizALOGBackendProxy implements wsclient.QuizALOGBackend {
  private String _endpoint = null;
  private wsclient.QuizALOGBackend quizALOGBackend = null;
  
  public QuizALOGBackendProxy() {
    _initQuizALOGBackendProxy();
  }
  
  public QuizALOGBackendProxy(String endpoint) {
    _endpoint = endpoint;
    _initQuizALOGBackendProxy();
  }
  
  private void _initQuizALOGBackendProxy() {
    try {
      quizALOGBackend = (new wsclient.QuizALOGBackendServiceLocator()).getQuizALOGBackendPort();
      if (quizALOGBackend != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)quizALOGBackend)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)quizALOGBackend)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (quizALOGBackend != null)
      ((javax.xml.rpc.Stub)quizALOGBackend)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public wsclient.QuizALOGBackend getQuizALOGBackend() {
    if (quizALOGBackend == null)
      _initQuizALOGBackendProxy();
    return quizALOGBackend;
  }
  
  public void addPlayer(wsclient.Player arg0) throws java.rmi.RemoteException{
    if (quizALOGBackend == null)
      _initQuizALOGBackendProxy();
    quizALOGBackend.addPlayer(arg0);
  }
  
  public wsclient.Question[] loadQustions(int nbr) throws java.rmi.RemoteException{
    if (quizALOGBackend == null)
      _initQuizALOGBackendProxy();
    return quizALOGBackend.loadQustions(nbr);
  }
  
  public int playerExist(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException{
    if (quizALOGBackend == null)
      _initQuizALOGBackendProxy();
    return quizALOGBackend.playerExist(arg0, arg1);
  }
  
  public java.lang.String testWebService(java.lang.String arg0) throws java.rmi.RemoteException{
    if (quizALOGBackend == null)
      _initQuizALOGBackendProxy();
    return quizALOGBackend.testWebService(arg0);
  }
  
  public void saveGame(wsclient.Game arg0, wsclient.GameQuestion[] arg1) throws java.rmi.RemoteException{
    if (quizALOGBackend == null)
      _initQuizALOGBackendProxy();
    quizALOGBackend.saveGame(arg0, arg1);
  }
  
  public wsclient.Answer[] loadQustionAnswers(int id) throws java.rmi.RemoteException{
    if (quizALOGBackend == null)
      _initQuizALOGBackendProxy();
    return quizALOGBackend.loadQustionAnswers(id);
  }
  
  
}