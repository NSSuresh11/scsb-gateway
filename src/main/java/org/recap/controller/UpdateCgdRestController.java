package org.recap.controller;

import lombok.extern.slf4j.Slf4j;
import org.recap.ScsbCommonConstants;
import org.recap.ScsbConstants;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Created by rajeshbabuk on 3/1/17.
 */
@Slf4j
@RestController
@RequestMapping("/updateCgdService")
public class UpdateCgdRestController extends AbstractController {



    /**
     * This method will call scsb-solr-client microservice to update CGD for an item in scsb database and scsb solr.
     *
     * @param itemBarcode                   the item barcode
     * @param owningInstitution             the owning institution
     * @param oldCollectionGroupDesignation the old collection group designation
     * @param newCollectionGroupDesignation the new collection group designation
     * @param cgdChangeNotes                the cgd change notes
     * @return the string
     */
    @GetMapping(value="/updateCgd")
    public String updateCgdForItem(@RequestParam String itemBarcode, @RequestParam String owningInstitution, @RequestParam String oldCollectionGroupDesignation, @RequestParam String newCollectionGroupDesignation, @RequestParam String cgdChangeNotes, @RequestParam String userName) {
        String statusResponse = null;
        try {
            HttpEntity requestEntity = new HttpEntity<>(getRestHeaderService().getHttpHeaders());

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(getScsbSolrClientUrl() + ScsbConstants.URL_UPDATE_CGD)
                    .queryParam(ScsbCommonConstants.CGD_UPDATE_ITEM_BARCODE, itemBarcode)
                    .queryParam(ScsbConstants.OWNING_INSTITUTION, owningInstitution)
                    .queryParam(ScsbCommonConstants.OLD_CGD, oldCollectionGroupDesignation)
                    .queryParam(ScsbCommonConstants.NEW_CGD, newCollectionGroupDesignation)
                    .queryParam(ScsbCommonConstants.CGD_CHANGE_NOTES, cgdChangeNotes)
                    .queryParam(ScsbCommonConstants.USER_NAME, userName);

            ResponseEntity<String> responseEntity = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, requestEntity, String.class);
            statusResponse = responseEntity.getBody();
        } catch (Exception e) {
            log.error(ScsbCommonConstants.LOG_ERROR,e);
            statusResponse = ScsbCommonConstants.FAILURE + "-" + e.getMessage();
        }
        return statusResponse;
    }
}
