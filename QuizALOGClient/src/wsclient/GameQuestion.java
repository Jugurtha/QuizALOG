/**
 * GameQuestion.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package wsclient;

public class GameQuestion  implements java.io.Serializable {
    private wsclient.Answer answer;

    private wsclient.Game game;

    private int id;

    private int idQuestion;

    public GameQuestion() {
    }

    public GameQuestion(
           wsclient.Answer answer,
           wsclient.Game game,
           int id,
           int idQuestion) {
           this.answer = answer;
           this.game = game;
           this.id = id;
           this.idQuestion = idQuestion;
    }


    /**
     * Gets the answer value for this GameQuestion.
     * 
     * @return answer
     */
    public wsclient.Answer getAnswer() {
        return answer;
    }


    /**
     * Sets the answer value for this GameQuestion.
     * 
     * @param answer
     */
    public void setAnswer(wsclient.Answer answer) {
        this.answer = answer;
    }


    /**
     * Gets the game value for this GameQuestion.
     * 
     * @return game
     */
    public wsclient.Game getGame() {
        return game;
    }


    /**
     * Sets the game value for this GameQuestion.
     * 
     * @param game
     */
    public void setGame(wsclient.Game game) {
        this.game = game;
    }


    /**
     * Gets the id value for this GameQuestion.
     * 
     * @return id
     */
    public int getId() {
        return id;
    }


    /**
     * Sets the id value for this GameQuestion.
     * 
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Gets the idQuestion value for this GameQuestion.
     * 
     * @return idQuestion
     */
    public int getIdQuestion() {
        return idQuestion;
    }


    /**
     * Sets the idQuestion value for this GameQuestion.
     * 
     * @param idQuestion
     */
    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GameQuestion)) return false;
        GameQuestion other = (GameQuestion) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.answer==null && other.getAnswer()==null) || 
             (this.answer!=null &&
              this.answer.equals(other.getAnswer()))) &&
            ((this.game==null && other.getGame()==null) || 
             (this.game!=null &&
              this.game.equals(other.getGame()))) &&
            this.id == other.getId() &&
            this.idQuestion == other.getIdQuestion();
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getAnswer() != null) {
            _hashCode += getAnswer().hashCode();
        }
        if (getGame() != null) {
            _hashCode += getGame().hashCode();
        }
        _hashCode += getId();
        _hashCode += getIdQuestion();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GameQuestion.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://session/", "gameQuestion"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("answer");
        elemField.setXmlName(new javax.xml.namespace.QName("", "answer"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://session/", "answer"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("game");
        elemField.setXmlName(new javax.xml.namespace.QName("", "game"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://session/", "game"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idQuestion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idQuestion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
