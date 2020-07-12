/**
 * Player.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package wsclient;

import java.util.Arrays;

public class Player  implements java.io.Serializable {
    private java.lang.String email;

    private wsclient.Game[] games;

    private int id;

    private java.lang.String name;

    private java.lang.String pseudo;

    public Player() {
    }

    public Player(
           java.lang.String email,
           wsclient.Game[] games,
           int id,
           java.lang.String name,
           java.lang.String pseudo) {
           this.email = email;
           this.games = games;
           this.id = id;
           this.name = name;
           this.pseudo = pseudo;
    }


    /**
     * Gets the email value for this Player.
     * 
     * @return email
     */
    public java.lang.String getEmail() {
        return email;
    }


    /**
     * Sets the email value for this Player.
     * 
     * @param email
     */
    public void setEmail(java.lang.String email) {
        this.email = email;
    }


    /**
     * Gets the games value for this Player.
     * 
     * @return games
     */
    public wsclient.Game[] getGames() {
        return games;
    }


    /**
     * Sets the games value for this Player.
     * 
     * @param games
     */
    public void setGames(wsclient.Game[] games) {
        this.games = games;
    }

    public wsclient.Game getGames(int i) {
        return this.games[i];
    }

    public void setGames(int i, wsclient.Game _value) {
        this.games[i] = _value;
    }


    /**
     * Gets the id value for this Player.
     * 
     * @return id
     */
    public int getId() {
        return id;
    }


    /**
     * Sets the id value for this Player.
     * 
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Gets the name value for this Player.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this Player.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the pseudo value for this Player.
     * 
     * @return pseudo
     */
    public java.lang.String getPseudo() {
        return pseudo;
    }


    /**
     * Sets the pseudo value for this Player.
     * 
     * @param pseudo
     */
    public void setPseudo(java.lang.String pseudo) {
        this.pseudo = pseudo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Player)) return false;
        Player other = (Player) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.email==null && other.getEmail()==null) || 
             (this.email!=null &&
              this.email.equals(other.getEmail()))) &&
            ((this.games==null && other.getGames()==null) || 
             (this.games!=null &&
              java.util.Arrays.equals(this.games, other.getGames()))) &&
            this.id == other.getId() &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.pseudo==null && other.getPseudo()==null) || 
             (this.pseudo!=null &&
              this.pseudo.equals(other.getPseudo())));
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
        if (getEmail() != null) {
            _hashCode += getEmail().hashCode();
        }
        if (getGames() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getGames());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getGames(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        _hashCode += getId();
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getPseudo() != null) {
            _hashCode += getPseudo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Player.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://session/", "player"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("email");
        elemField.setXmlName(new javax.xml.namespace.QName("", "email"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("games");
        elemField.setXmlName(new javax.xml.namespace.QName("", "games"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://session/", "game"));
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
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("", "name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pseudo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pseudo"));
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
		return "Player [email=" + email + ", games=" + Arrays.toString(games) + ", id=" + id + ", name=" + name
				+ ", pseudo=" + pseudo + "]";
	}

}
