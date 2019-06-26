//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.26 at 11:04:56 AM ALMT 
//


package soap.logistic.district;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for district complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="district">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="districtNameKk" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="districtNameRu" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="districtNameEn" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="regionId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="cityId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "district", propOrder = {
    "id",
    "districtNameKk",
    "districtNameRu",
    "districtNameEn",
    "regionId",
    "cityId"
})
public class District {

    protected long id;
    @XmlElement(required = true)
    protected String districtNameKk;
    @XmlElement(required = true)
    protected String districtNameRu;
    @XmlElement(required = true)
    protected String districtNameEn;
    protected long regionId;
    protected long cityId;

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
     * Gets the value of the districtNameKk property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDistrictNameKk() {
        return districtNameKk;
    }

    /**
     * Sets the value of the districtNameKk property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDistrictNameKk(String value) {
        this.districtNameKk = value;
    }

    /**
     * Gets the value of the districtNameRu property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDistrictNameRu() {
        return districtNameRu;
    }

    /**
     * Sets the value of the districtNameRu property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDistrictNameRu(String value) {
        this.districtNameRu = value;
    }

    /**
     * Gets the value of the districtNameEn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDistrictNameEn() {
        return districtNameEn;
    }

    /**
     * Sets the value of the districtNameEn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDistrictNameEn(String value) {
        this.districtNameEn = value;
    }

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
     * Gets the value of the cityId property.
     * 
     */
    public long getCityId() {
        return cityId;
    }

    /**
     * Sets the value of the cityId property.
     * 
     */
    public void setCityId(long value) {
        this.cityId = value;
    }

}
