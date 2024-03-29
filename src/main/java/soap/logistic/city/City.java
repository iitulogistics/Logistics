//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.09.12 at 06:51:03 PM ALMT 
//


package soap.logistic.city;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for city complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="city">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="regionId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="countryId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="cityNameKk" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cityNameRu" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cityNameEn" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "city", propOrder = {
    "regionId",
    "countryId",
    "id",
    "cityNameKk",
    "cityNameRu",
    "cityNameEn"
})
public class City {

    protected long regionId;
    protected long countryId;
    protected long id;
    @XmlElement(required = true)
    protected String cityNameKk;
    @XmlElement(required = true)
    protected String cityNameRu;
    @XmlElement(required = true)
    protected String cityNameEn;

    /**
     * Gets the value of the regionId property.
     * 
     */
    public long getRegionId() {
        return regionId;
    }

    /**
     * Sets the value of the regionId property.
     * 
     */
    public void setRegionId(long value) {
        this.regionId = value;
    }

    /**
     * Gets the value of the countryId property.
     * 
     */
    public long getCountryId() {
        return countryId;
    }

    /**
     * Sets the value of the countryId property.
     * 
     */
    public void setCountryId(long value) {
        this.countryId = value;
    }

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
     * Gets the value of the cityNameKk property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCityNameKk() {
        return cityNameKk;
    }

    /**
     * Sets the value of the cityNameKk property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCityNameKk(String value) {
        this.cityNameKk = value;
    }

    /**
     * Gets the value of the cityNameRu property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCityNameRu() {
        return cityNameRu;
    }

    /**
     * Sets the value of the cityNameRu property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCityNameRu(String value) {
        this.cityNameRu = value;
    }

    /**
     * Gets the value of the cityNameEn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCityNameEn() {
        return cityNameEn;
    }

    /**
     * Sets the value of the cityNameEn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCityNameEn(String value) {
        this.cityNameEn = value;
    }

}
