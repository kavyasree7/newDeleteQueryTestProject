/*Copyright (c) 2016-2017 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.newdeletequerytestproject.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.wavemaker.runtime.data.dao.WMGenericDao;
import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.data.model.AggregationInfo;
import com.wavemaker.runtime.file.model.Downloadable;

import com.newdeletequerytestproject.Table10;


/**
 * ServiceImpl object for domain model class Table10.
 *
 * @see Table10
 */
@Service("kavyadb.Table10Service")
@Validated
public class Table10ServiceImpl implements Table10Service {

    private static final Logger LOGGER = LoggerFactory.getLogger(Table10ServiceImpl.class);


    @Autowired
    @Qualifier("kavyadb.Table10Dao")
    private WMGenericDao<Table10, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<Table10, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "kavyadbTransactionManager")
    @Override
	public Table10 create(Table10 table10) {
        LOGGER.debug("Creating a new Table10 with information: {}", table10);
        Table10 table10Created = this.wmGenericDao.create(table10);
        return table10Created;
    }

	@Transactional(readOnly = true, value = "kavyadbTransactionManager")
	@Override
	public Table10 getById(Integer table10Id) throws EntityNotFoundException {
        LOGGER.debug("Finding Table10 by id: {}", table10Id);
        Table10 table10 = this.wmGenericDao.findById(table10Id);
        if (table10 == null){
            LOGGER.debug("No Table10 found with id: {}", table10Id);
            throw new EntityNotFoundException(String.valueOf(table10Id));
        }
        return table10;
    }

    @Transactional(readOnly = true, value = "kavyadbTransactionManager")
	@Override
	public Table10 findById(Integer table10Id) {
        LOGGER.debug("Finding Table10 by id: {}", table10Id);
        return this.wmGenericDao.findById(table10Id);
    }


	@Transactional(rollbackFor = EntityNotFoundException.class, value = "kavyadbTransactionManager")
	@Override
	public Table10 update(Table10 table10) throws EntityNotFoundException {
        LOGGER.debug("Updating Table10 with information: {}", table10);
        this.wmGenericDao.update(table10);

        Integer table10Id = table10.getId();

        return this.wmGenericDao.findById(table10Id);
    }

    @Transactional(value = "kavyadbTransactionManager")
	@Override
	public Table10 delete(Integer table10Id) throws EntityNotFoundException {
        LOGGER.debug("Deleting Table10 with id: {}", table10Id);
        Table10 deleted = this.wmGenericDao.findById(table10Id);
        if (deleted == null) {
            LOGGER.debug("No Table10 found with id: {}", table10Id);
            throw new EntityNotFoundException(String.valueOf(table10Id));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "kavyadbTransactionManager")
	@Override
	public Page<Table10> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Table10s");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "kavyadbTransactionManager")
    @Override
    public Page<Table10> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Table10s");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "kavyadbTransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service kavyadb for table Table10 to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

	@Transactional(readOnly = true, value = "kavyadbTransactionManager")
	@Override
	public long count(String query) {
        return this.wmGenericDao.count(query);
    }

    @Transactional(readOnly = true, value = "kavyadbTransactionManager")
	@Override
    public Page<Map<String, Object>> getAggregatedValues(AggregationInfo aggregationInfo, Pageable pageable) {
        return this.wmGenericDao.getAggregatedValues(aggregationInfo, pageable);
    }



}

