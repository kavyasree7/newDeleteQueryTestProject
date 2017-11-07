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
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

/**
 * IdTable1 generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`ID_TABLE1`", uniqueConstraints = {
        @UniqueConstraint(name = "`UK_ID_TABLE1_COLUMN4_COLUMN3`", columnNames = {"`COLUMN4`", "`COLUMN3`"}),
        @UniqueConstraint(name = "`UK_ID_TABLE1_COLUMN4`", columnNames = {"`COLUMN4`"})})
@IdClass(IdTable1Id.class)
public class IdTable1 implements Serializable {

    private String id;
    private Long column3;
    @JsonProperty(access = Access.READ_ONLY)
    private byte[] column2;
    private Integer column4;
    private String column5;

    @Id
    @Column(name = "`ID`", nullable = true, length = 255)
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Id
    @Column(name = "`COLUMN3`", nullable = false, scale = 0, precision = 19)
    public Long getColumn3() {
        return this.column3;
    }

    public void setColumn3(Long column3) {
        this.column3 = column3;
    }

    @Id
    @Column(name = "`COLUMN2`", nullable = true)
    public byte[] getColumn2() {
        return this.column2;
    }

    public void setColumn2(byte[] column2) {
        this.column2 = column2;
    }

    @Id
    @Column(name = "`COLUMN4`", nullable = true, scale = 0, precision = 10)
    public Integer getColumn4() {
        return this.column4;
    }

    public void setColumn4(Integer column4) {
        this.column4 = column4;
    }

    @Id
    @Column(name = "`COLUMN5`", nullable = true, length = 255)
    public String getColumn5() {
        return this.column5;
    }

    public void setColumn5(String column5) {
        this.column5 = column5;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IdTable1)) return false;
        final IdTable1 idTable1 = (IdTable1) o;
        return Objects.equals(getId(), idTable1.getId()) &&
                Objects.equals(getColumn3(), idTable1.getColumn3()) &&
                Objects.equals(getColumn2(), idTable1.getColumn2()) &&
                Objects.equals(getColumn4(), idTable1.getColumn4()) &&
                Objects.equals(getColumn5(), idTable1.getColumn5());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),
                getColumn3(),
                getColumn2(),
                getColumn4(),
                getColumn5());
    }
}
