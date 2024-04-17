package org.recap.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.recap.ScsbCommonConstants;
import org.recap.model.transfer.ItemDestination;
import org.recap.model.transfer.ItemSource;
import org.recap.model.transfer.ItemTransferRequest;
import org.recap.model.transfer.ItemTransferResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Created by Anitha on 13/6/20.
 */
@ExtendWith(MockitoExtension.class)
public class ItemTransferResponseUT {

    @Test
    public void itemTransferResponse(){
        ItemTransferResponse itemTransferResponse = new ItemTransferResponse(ScsbCommonConstants.SUCCESS,getItemTransferRequest());
        assertEquals(ScsbCommonConstants.SUCCESS,itemTransferResponse.getMessage());
        assertNotNull(new ItemTransferResponse());
        assertNotNull(itemTransferResponse.getItemTransferRequest());
    }

    private ItemTransferRequest getItemTransferRequest() {
        ItemTransferRequest itemTransferRequest = new ItemTransferRequest();
        itemTransferRequest.setSource(getItemSource());
        itemTransferRequest.setDestination(getItemDestination());
        return itemTransferRequest;
    }

    private ItemDestination getItemDestination() {
        ItemDestination itemDestination = new ItemDestination();
        itemDestination.setOwningInstitutionItemId("CUL");
        return itemDestination;
    }

    private ItemSource getItemSource() {
        ItemSource itemSource = new ItemSource();
        itemSource.setOwningInstitutionItemId("PUL");
        return itemSource;
    }
}
