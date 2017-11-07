/*Copyright (c) 2016-2017 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.newdeletequerytestproject.controller;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.TypeMismatchException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.data.model.AggregationInfo;
import com.wavemaker.runtime.file.model.Downloadable;
import com.wavemaker.runtime.util.WMMultipartUtils;
import com.wavemaker.runtime.util.WMRuntimeUtils;
import com.wavemaker.tools.api.core.annotations.WMAccessVisibility;
import com.wavemaker.tools.api.core.models.AccessSpecifier;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import com.newdeletequerytestproject.IdTable1;
import com.newdeletequerytestproject.IdTable1Id;
import com.newdeletequerytestproject.service.IdTable1Service;


/**
 * Controller object for domain model class IdTable1.
 * @see IdTable1
 */
@RestController("sampleAug17Test.IdTable1Controller")
@Api(value = "IdTable1Controller", description = "Exposes APIs to work with IdTable1 resource.")
@RequestMapping("/sampleAug17Test/IdTable1")
public class IdTable1Controller {

    private static final Logger LOGGER = LoggerFactory.getLogger(IdTable1Controller.class);

    @Autowired
	@Qualifier("sampleAug17Test.IdTable1Service")
	private IdTable1Service idTable1Service;

	@ApiOperation(value = "Creates a new IdTable1 instance.")
@RequestMapping(method = RequestMethod.POST, consumes = "multipart/form-data")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
public IdTable1 createIdTable1(@RequestPart("wm_data_json") IdTable1 idTable1, @RequestPart(value = "column2", required = false) MultipartFile _column2) {
		LOGGER.debug("Create IdTable1 with information: {}" , idTable1);

    idTable1.setColumn2(WMMultipartUtils.toByteArray(_column2));
		idTable1 = idTable1Service.create(idTable1);
		LOGGER.debug("Created IdTable1 with information: {}" , idTable1);

	    return idTable1;
	}

@ApiOperation(value = "Returns the IdTable1 instance associated with the given composite-id.")
    @RequestMapping(value = "/composite-id", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public IdTable1 getIdTable1(@RequestParam("id") String id,@RequestParam("column3") Long column3,@RequestParam("column2") byte[] column2,@RequestParam("column4") Integer column4,@RequestParam("column5") String column5) throws EntityNotFoundException {

        IdTable1Id idtable1Id = new IdTable1Id();
        idtable1Id.setId(id);
        idtable1Id.setColumn3(column3);
        idtable1Id.setColumn2(column2);
        idtable1Id.setColumn4(column4);
        idtable1Id.setColumn5(column5);

        LOGGER.debug("Getting IdTable1 with id: {}" , idtable1Id);
        IdTable1 idTable1 = idTable1Service.getById(idtable1Id);
        LOGGER.debug("IdTable1 details with id: {}" , idTable1);

        return idTable1;
    }

    @ApiOperation(value = "Retrieves content for the given BLOB field in IdTable1 instance associated with the given composite-id.")
    @RequestMapping(value = "/composite-id/content/{fieldName}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public void getIdTable1BLOBContent(@RequestParam("id") String id,@RequestParam("column3") Long column3,@RequestParam("column2") byte[] column2,@RequestParam("column4") Integer column4,@RequestParam("column5") String column5, @PathVariable("fieldName") String fieldName, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws EntityNotFoundException {

        LOGGER.debug("Retrieves content for the given BLOB field {} in IdTable1 instance" , fieldName);

        if(!WMRuntimeUtils.isLob(IdTable1.class, fieldName)) {
            throw new TypeMismatchException("Given field " + fieldName +  " is not a valid BLOB type");
        }

        IdTable1Id idtable1Id = new IdTable1Id();
        idtable1Id.setId(id);
        idtable1Id.setColumn3(column3);
        idtable1Id.setColumn2(column2);
        idtable1Id.setColumn4(column4);
        idtable1Id.setColumn5(column5);

        IdTable1 idTable1 = idTable1Service.getById(idtable1Id);
        WMMultipartUtils.buildHttpResponseForBlob(idTable1, fieldName, httpServletRequest, httpServletResponse);
    }



    @ApiOperation(value = "Updates the IdTable1 instance associated with the given composite-id.")
    @RequestMapping(value = "/composite-id", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public IdTable1 editIdTable1(@RequestParam("id") String id,@RequestParam("column3") Long column3,@RequestParam("column2") byte[] column2,@RequestParam("column4") Integer column4,@RequestParam("column5") String column5, @RequestBody IdTable1 idTable1) throws EntityNotFoundException {

        idTable1.setId(id);
        idTable1.setColumn3(column3);
        idTable1.setColumn2(column2);
        idTable1.setColumn4(column4);
        idTable1.setColumn5(column5);

        LOGGER.debug("IdTable1 details with id is updated with: {}" , idTable1);

        return idTable1Service.update(idTable1);
    }

    @ApiOperation(value = "Updates the IdTable1 instance associated with the given composite-id.This API should be used when IdTable1 instance fields that require multipart data.")
    @RequestMapping(value = "/composite-id", method = RequestMethod.POST, consumes = "multipart/form-data")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public IdTable1 editIdTable1(@RequestParam("id") String id,@RequestParam("column3") Long column3,@RequestParam("column2") byte[] column2,@RequestParam("column4") Integer column4,@RequestParam("column5") String column5, MultipartHttpServletRequest multipartHttpServletRequest) throws EntityNotFoundException {
        return this.editIdTable1AndMultiparts(id, column3, column2, column4, column5, multipartHttpServletRequest);
    }

    @ApiOperation(value = "Updates the IdTable1 instance associated with the given composite-id.")
    @RequestMapping(value = "/composite-id", method = RequestMethod.PUT,  consumes = "multipart/form-data")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public IdTable1 editIdTable1AndMultiparts(@RequestParam("id") String id,@RequestParam("column3") Long column3,@RequestParam("column2") byte[] column2,@RequestParam("column4") Integer column4,@RequestParam("column5") String column5, MultipartHttpServletRequest multipartHttpServletRequest) throws EntityNotFoundException { 

        IdTable1Id idtable1Id = new IdTable1Id();
        idtable1Id.setId(id);
        idtable1Id.setColumn3(column3);
        idtable1Id.setColumn2(column2);
        idtable1Id.setColumn4(column4);
        idtable1Id.setColumn5(column5);

        IdTable1 newIdTable1 = WMMultipartUtils.toObject(multipartHttpServletRequest, IdTable1.class, "sampleAug17Test");
        IdTable1 oldIdTable1 = idTable1Service.getById(idtable1Id);

        WMMultipartUtils.updateLobsContent(oldIdTable1, newIdTable1);

        newIdTable1.setId(id);
        newIdTable1.setColumn3(column3);
        newIdTable1.setColumn2(column2);
        newIdTable1.setColumn4(column4);
        newIdTable1.setColumn5(column5);

        LOGGER.debug("IdTable1 details with id is updated with: {}" , newIdTable1);

        return idTable1Service.update(newIdTable1);
    }


    @ApiOperation(value = "Deletes the IdTable1 instance associated with the given composite-id.")
    @RequestMapping(value = "/composite-id", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteIdTable1(@RequestParam("id") String id,@RequestParam("column3") Long column3,@RequestParam("column2") byte[] column2,@RequestParam("column4") Integer column4,@RequestParam("column5") String column5) throws EntityNotFoundException {

        IdTable1Id idtable1Id = new IdTable1Id();
        idtable1Id.setId(id);
        idtable1Id.setColumn3(column3);
        idtable1Id.setColumn2(column2);
        idtable1Id.setColumn4(column4);
        idtable1Id.setColumn5(column5);

        LOGGER.debug("Deleting IdTable1 with id: {}" , idtable1Id);
        IdTable1 idTable1 = idTable1Service.delete(idtable1Id);

        return idTable1 != null;
    }


    @RequestMapping(value = "/column4/{column4}", method = RequestMethod.GET)
    @ApiOperation(value = "Returns the matching IdTable1 with given unique key values.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public IdTable1 getByColumn4(@PathVariable("column4") Integer column4) {
        LOGGER.debug("Getting IdTable1 with uniques key Column4");
        return idTable1Service.getByColumn4(column4);
    }

    @RequestMapping(value = "/column4-column3", method = RequestMethod.GET)
    @ApiOperation(value = "Returns the matching IdTable1 with given unique key values.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public IdTable1 getByColumn4AndColumn3(@RequestParam("column4") Integer column4, @RequestParam("column3") long column3) {
        LOGGER.debug("Getting IdTable1 with uniques key Column4AndColumn3");
        return idTable1Service.getByColumn4AndColumn3(column4, column3);
    }

    /**
     * @deprecated Use {@link #findIdTable1s(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of IdTable1 instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<IdTable1> searchIdTable1sByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering IdTable1s list");
        return idTable1Service.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of IdTable1 instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<IdTable1> findIdTable1s(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering IdTable1s list");
        return idTable1Service.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of IdTable1 instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @RequestMapping(value="/filter", method = RequestMethod.POST, consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<IdTable1> filterIdTable1s(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering IdTable1s list");
        return idTable1Service.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
    @RequestMapping(value = "/export/{exportType}", method = {RequestMethod.GET,  RequestMethod.POST}, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportIdTable1s(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return idTable1Service.export(exportType, query, pageable);
    }

	@ApiOperation(value = "Returns the total count of IdTable1 instances matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
	@RequestMapping(value = "/count", method = {RequestMethod.GET, RequestMethod.POST})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Long countIdTable1s( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting IdTable1s");
		return idTable1Service.count(query);
	}

    @ApiOperation(value = "Returns aggregated result with given aggregation info")
	@RequestMapping(value = "/aggregations", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Page<Map<String, Object>> getIdTable1AggregatedValues(@RequestBody AggregationInfo aggregationInfo, Pageable pageable) {
        LOGGER.debug("Fetching aggregated results for {}", aggregationInfo);
        return idTable1Service.getAggregatedValues(aggregationInfo, pageable);
    }


    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service IdTable1Service instance
	 */
	protected void setIdTable1Service(IdTable1Service service) {
		this.idTable1Service = service;
	}

}

