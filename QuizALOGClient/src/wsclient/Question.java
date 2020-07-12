/**
 * Question.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package wsclient;

import java.util.Arrays;

public class Question  implements java.io.Serializable {
    private wsclient.Answer[] answers;

    private int id;

    private int idCorrectAnswer;

    private int score;

    private java.lang.String text;

    public Question() {
    }

    public Question(
    		wsclient.Answer[] answers,
           int id,
           int idCorrectAnswer,
           int score,
           java.lang.String text) {
           this.answers = answers;
           this.id = id;
           this.idCorrectAnswer = idCorrectAnswer;
           this.score = score;
           this.text = text;
    }


    /**
     * Gets the answers value for this Question.
     * 
     * @return answers
     */
    public wsclient.Answer[] getAnswers() {
        return answers;
    }


    /**
     * Sets the answers value for this Question.
     * 
     * @param answers
     */
    public void setAnswers(wsclient.Answer[] answers) {
        this.answers = answers;
    }

    public wsclient.Answer getAnswers(int i) {
        return this.answers[i];
    }

    public void setAnswers(int i, wsclient.Answer _value) {
        this.answers[i] = _value;
    }


    /**
     * Gets the id value for this Question.
     * 
     * @return id
     */
    public int getId() {
        return id;
    }


    /**
     * Sets the id value for this Question.
     * 
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Gets the idCorrectAnswer value for this Question.
     * 
     * @return idCorrectAnswer
     */
    public int getIdCorrectAnswer() {
        return idCorrectAnswer;
    }


    /**
     * Sets the idCorrectAnswer value for this Question.
     * 
     * @param idCorrectAnswer
     */
    public void setIdCorrectAnswer(int idCorrectAnswer) {
        this.idCorrectAnswer = idCorrectAnswer;
    }


    /**
     * Gets the score value for this Question.
     * 
     * @return score
     */
    public int getScore() {
        return score;
    }


    /**
     * Sets the score value for this Question.
     * 
     * @param score
     */
    public void setScore(int score) {
        this.score = score;
    }


    /**
     * Gets the text value for this Question.
     * 
     * @return text
     */
    public java.lang.String getText() {
        return text;
    }


    /**
     * Sets the text value for this Question.
     * 
     * @param text
     */
    public void setText(java.lang.String text) {
        this.text = text;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Question)) return false;
        Question other = (Question) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.answers==null && other.getAnswers()==null) || 
             (this.answers!=null &&
              java.util.Arrays.equals(this.answers, other.getAnswers()))) &&
            this.id == other.getId() &&
            this.idCorrectAnswer == other.getIdCorrectAnswer() &&
            this.score == other.getScore() &&
            ((this.text==null && other.getText()==null) || 
             (this.text!=null &&
              this.text.equals(other.getText())));
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
        if (getAnswers() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAnswers());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAnswers(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        _hashCode += getId();
        _hashCode += getIdCorrectAnswer();
        _hashCode += getScore();
        if (getText() != null) {
            _hashCode += getText().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Question.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://session/", "question"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("answers");
        elemField.setXmlName(new javax.xml.namespace.QName("", "answers"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://session/", "answer"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idCorrectAnswer");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idCorrectAnswer"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("score");
        elemField.setXmlName(new javax.xml.namespace.QName("", "score"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("text");
        elemField.setXmlName(new javax.xml.namespace.QName("", "text"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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

	@Override
	public String toString() {
		return "Question [answers=" + Arrays.toString(answers) + ", id=" + id + ", idCorrectAnswer=" + idCorrectAnswer
				+ ", score=" + score + ", text=" + text + "]";
	}

}
