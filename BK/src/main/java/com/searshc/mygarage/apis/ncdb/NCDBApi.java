package com.searshc.mygarage.apis.ncdb;

import java.util.List;

import com.searshc.mygarage.entities.Order;

public interface NCDBApi {

    List<Order> getCarTransactionHistory(final String familyIdNumber, final String tangibleId);
}
