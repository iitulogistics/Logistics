//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.26 at 11:04:56 AM ALMT 
//


package soap.logistic.country;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="countryNameKk" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="countryNameRu" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="countryNameEn" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "id",
    "countryNameKk",
    "countryNameRu",
    "countryNameEn"
})
@XmlRootElement(name = "updateCountryRequest")
public class UpdateCountryRequest {

    protected long id;
    @XmlElement(required = true)
    protected String countryNameKk;
    @XmlElement(required = true)
    protected String countryNameRu;
    @XmlElement(required = true)
    protected String countryNameEn;

    /**
     * Gets the value of the id property.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Gets the value of the countryNameKk property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountryNameKk() {
        return countryNameKk;
    }

    /**
     * Sets the value of the countryNameKk property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountryNameKk(String value) {
        this.countryNameKk = value;
    }

    /**
     * Gets the value of the countryNameRu property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountryNameRu() {
        return countryNameRu;
    }

    /**
     * Sets the value of the countryNameRu property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountryNameRu(String value) {
        this.countryNameRu = value;
    }

    /**
     * Gets the value of the countryNameEn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountryNameEn() {
        return countryNameEn;
    }

    /**
     * Sets the value of the countryNameEn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountryNameEn(String value) {
        this.countryNameEn = value;
    }

}
