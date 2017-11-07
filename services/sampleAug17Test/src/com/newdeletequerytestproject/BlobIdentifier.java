/*Copyright (c) 2016-2017 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.newdeletequerytestproject;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

/**
 * BlobIdentifier generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`BlobIdentifier`")
public class BlobIdentifier implements Serializable {

    private Integer id;
    @JsonProperty(access = Access.READ_ONLY)
    private byte[] column2;
    private String column3;

    @Id
    @Column(name = "`ID`", nullable = false, scale = 0, precision = 10)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "`COLUMN2`", nullable = true)
    public byte[] getColumn2() {
        return this.column2;
    }

    public void setColumn2(byte[] column2) {
        this.column2 = column2;
    }

    @Column(name = "`COLUMN3`", nullable = true, length = 255)
    public String getColumn3() {
        return this.column3;
    }

    public void setColumn3(String column3) {
        this.column3 = column3;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BlobIdentifier)) return false;
        final BlobIdentifier blobIdentifier = (BlobIdentifier) o;
        return Objects.equals(getId(), blobIdentifier.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
