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
import com.wavemaker.runtime.file.model.DownloadResponse;
import com.wavemaker.runtime.file.model.Downloadable;
import com.wavemaker.runtime.util.WMMultipartUtils;
import com.wavemaker.runtime.util.WMRuntimeUtils;
import com.wavemaker.tools.api.core.annotations.WMAccessVisibility;
import com.wavemaker.tools.api.core.models.AccessSpecifier;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import com.newdeletequerytestproject.BlobIdentifier;
import com.newdeletequerytestproject.service.BlobIdentifierService;


/**
 * Controller object for domain model class BlobIdentifier.
 * @see BlobIdentifier
 */
@RestController("sampleAug17Test.BlobIdentifierController")
@Api(value = "BlobIdentifierController", description = "Exposes APIs to work with BlobIdentifier resource.")
@RequestMapping("/sampleAug17Test/BlobIdentifier")
public class BlobIdentifierController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BlobIdentifierController.class);

    @Autowired
	@Qualifier("sampleAug17Test.BlobIdentifierService")
	private BlobIdentifierService blobIdentifierService;

	@ApiOperation(value = "Creates a new BlobIdentifier instance.")
@RequestMapping(method = RequestMethod.POST, consumes = "multipart/form-data")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
public BlobIdentifier createBlobIdentifier(@RequestPart("wm_data_json") BlobIdentifier blobIdentifier, @RequestPart(value = "column2", required = false) MultipartFile _column2) {
		LOGGER.debug("Create BlobIdentifier with information: {}" , blobIdentifier);

    blobIdentifier.setColumn2(WMMultipartUtils.toByteArray(_column2));
		blobIdentifier = blobIdentifierService.create(blobIdentifier);
		LOGGER.debug("Created BlobIdentifier with information: {}" , blobIdentifier);

	    return blobIdentifier;
	}

    @ApiOperation(value = "Returns the BlobIdentifier instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public BlobIdentifier getBlobIdentifier(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Getting BlobIdentifier with id: {}" , id);

        BlobIdentifier foundBlobIdentifier = blobIdentifierService.getById(id);
        LOGGER.debug("BlobIdentifier details with id: {}" , foundBlobIdentifier);

        return foundBlobIdentifier;
    }

    @ApiOperation(value = "Retrieves content for the given BLOB field in BlobIdentifier instance" )
    @RequestMapping(value = "/{id}/content/{fieldName}", method = RequestMethod.GET, produces="application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public DownloadResponse getBlobIdentifierBLOBContent(@PathVariable("id") Integer id, @PathVariable("fieldName") String fieldName, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestParam(value="download", defaultValue = "false") boolean download) {

        LOGGER.debug("Retrieves content for the given BLOB field {} in BlobIdentifier instance" , fieldName);

        if(!WMRuntimeUtils.isLob(BlobIdentifier.class, fieldName)) {
            throw new TypeMismatchException("Given field " + fieldName + " is not a valid BLOB type");
        }
        BlobIdentifier blobIdentifier = blobIdentifierService.getById(id);

        return WMMultipartUtils.buildDownloadResponseForBlob(blobIdentifier, fieldName, httpServletRequest, download);
    }

    @ApiOperation(value = "Updates the BlobIdentifier instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public BlobIdentifier editBlobIdentifier(@PathVariable("id") Integer id, @RequestBody BlobIdentifier blobIdentifier) throws EntityNotFoundException {
        LOGGER.debug("Editing BlobIdentifier with id: {}" , blobIdentifier.getId());

        blobIdentifier.setId(id);
        blobIdentifier = blobIdentifierService.update(blobIdentifier);
        LOGGER.debug("BlobIdentifier details with id: {}" , blobIdentifier);

        return blobIdentifier;
    }

    @ApiOperation(value = "Updates the BlobIdentifier instance associated with the given id.This API should be used when BlobIdentifier instance fields that require multipart data.") 
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public BlobIdentifier editBlobIdentifier(@PathVariable("id") Integer id, MultipartHttpServletRequest multipartHttpServletRequest) throws EntityNotFoundException {
        BlobIdentifier newBlobIdentifier = WMMultipartUtils.toObject(multipartHttpServletRequest, BlobIdentifier.class, "sampleAug17Test");
        newBlobIdentifier.setId(id);

        BlobIdentifier oldBlobIdentifier = blobIdentifierService.getById(id);
        WMMultipartUtils.updateLobsContent(oldBlobIdentifier, newBlobIdentifier);
        LOGGER.debug("Updating BlobIdentifier with information: {}" , newBlobIdentifier);

        return blobIdentifierService.update(newBlobIdentifier);
    }

    @ApiOperation(value = "Deletes the BlobIdentifier instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteBlobIdentifier(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Deleting BlobIdentifier with id: {}" , id);

        BlobIdentifier deletedBlobIdentifier = blobIdentifierService.delete(id);

        return deletedBlobIdentifier != null;
    }

    /**
     * @deprecated Use {@link #findBlobIdentifiers(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of BlobIdentifier instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<BlobIdentifier> searchBlobIdentifiersByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering BlobIdentifiers list");
        return blobIdentifierService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of BlobIdentifier instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<BlobIdentifier> findBlobIdentifiers(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering BlobIdentifiers list");
        return blobIdentifierService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of BlobIdentifier instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @RequestMapping(value="/filter", method = RequestMethod.POST, consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<BlobIdentifier> filterBlobIdentifiers(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering BlobIdentifiers list");
        return blobIdentifierService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
    @RequestMapping(value = "/export/{exportType}", method = {RequestMethod.GET,  RequestMethod.POST}, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportBlobIdentifiers(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return blobIdentifierService.export(exportType, query, pageable);
    }

	@ApiOperation(value = "Returns the total count of BlobIdentifier instances matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
	@RequestMapping(value = "/count", method = {RequestMethod.GET, RequestMethod.POST})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Long countBlobIdentifiers( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting BlobIdentifiers");
		return blobIdentifierService.count(query);
	}

    @ApiOperation(value = "Returns aggregated result with given aggregation info")
	@RequestMapping(value = "/aggregations", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Page<Map<String, Object>> getBlobIdentifierAggregatedValues(@RequestBody AggregationInfo aggregationInfo, Pageable pageable) {
        LOGGER.debug("Fetching aggregated results for {}", aggregationInfo);
        return blobIdentifierService.getAggregatedValues(aggregationInfo, pageable);
    }


    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service BlobIdentifierService instance
	 */
	protected void setBlobIdentifierService(BlobIdentifierService service) {
		this.blobIdentifierService = service;
	}

}

