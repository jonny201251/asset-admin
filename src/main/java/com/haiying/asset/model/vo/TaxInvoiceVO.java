package com.haiying.asset.model.vo;

import com.haiying.asset.model.entity.TaxInvoice;
import lombok.Data;

import java.util.List;

@Data
public class TaxInvoiceVO {
    private List<TaxInvoice> list;
}
