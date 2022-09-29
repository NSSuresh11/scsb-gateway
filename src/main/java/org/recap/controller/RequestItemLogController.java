package org.recap.controller;

import org.recap.entity.ItemRequestReceivedInformationEntity;
import org.recap.model.request.RequestLogReportRequest;
import org.recap.service.RequestItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Dinakar N created on 26/09/22
 */
@RestController
@RequestMapping("/request-log")
public class RequestItemLogController {
    @Autowired
    private RequestItemService requestItemService;

    @PostMapping(path = "/reports", consumes = "application/json",produces = "application/json")
    public ResponseEntity<RequestLogReportRequest> getRequestLogReports(@RequestBody RequestLogReportRequest requestLogReportRequest ){
        RequestLogReportRequest  requestLogReportResponse = requestItemService.getRequests(requestLogReportRequest);
        return new ResponseEntity<>(requestLogReportResponse, HttpStatus.OK);
    }

    @PostMapping(path = "/submit", consumes = "application/json",produces = "application/json")
    public ResponseEntity<RequestLogReportRequest> getRequestLogSubmitReports(@RequestBody RequestLogReportRequest requestLogReportRequest ){
        RequestLogReportRequest  requestLogReportResponse = null;
        try {
            requestLogReportResponse = requestItemService.submitRequests(requestLogReportRequest);
        } catch (Exception e) {
            requestLogReportResponse = new RequestLogReportRequest();
            requestLogReportResponse.setStatus("Resubmit failed");
        }
        return new ResponseEntity<>(requestLogReportResponse, HttpStatus.OK);
    }
}
