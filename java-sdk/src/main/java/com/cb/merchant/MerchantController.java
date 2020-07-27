package com.cb.merchant;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cb.dto.BigOrderDTO;
import com.cb.dto.CountDTO;
import com.cb.dto.CustomerByRegionDTO;
import com.cb.dto.ProductRatingDTO;
import com.cb.dto.PurchaseOrderDTO;
import com.cb.dto.SalesDTO;

@RestController
public class MerchantController {

    @Autowired
    private MerchantService merchantService;

    @RequestMapping("/getPurchaseOrder")
    public ResponseEntity<?> getPurchaseOrder() {
        List<PurchaseOrderDTO> result = merchantService.getPurchaseOrder();
        return ResponseEntity.ok(result);
    }

    @RequestMapping("/getCustomersByRegion")
    public ResponseEntity<?> getCustomersByRegion() {
        List<CustomerByRegionDTO> result = merchantService.getCustomersByRegion();
        return ResponseEntity.ok(result);
    }

    @RequestMapping("/getActiveCustomersForDateRange")
    public ResponseEntity<?> getActiveCustomersForDateRange(
            @RequestParam String fromDate, @RequestParam String toDate) {
        List<CountDTO> result = merchantService.getActiveCustomersForDateRange(fromDate, toDate);
        return ResponseEntity.ok(result);
    }

    @RequestMapping("/getLowRatedProducts")
    public ResponseEntity<?> getLowRatedProducts() {
        List<ProductRatingDTO> result = merchantService.getLowRatedProducts();
        return ResponseEntity.ok(result);
    }

    @RequestMapping("/getMonthWiseSales")
    public ResponseEntity<?> getMonthWiseSales() {
        List<SalesDTO> result = merchantService.getMonthWiseSales();
        return ResponseEntity.ok(result);
    }

    @RequestMapping("/getBigTicketOrdersForMonth")
    public ResponseEntity<?> getBigTicketOrdersForMonth(@RequestParam int month, @RequestParam int year) {
        List<BigOrderDTO> result = merchantService.getBigTicketOrdersForMonth(month, year);
        return ResponseEntity.ok(result);
    }

}
