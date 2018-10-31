package com.bilin.springmongo.service.user.impl;

import org.springframework.stereotype.Service;

import com.bilin.springmongo.model.WbOrder;
import com.bilin.springmongo.service.common.impl.CommonServiceImpl;
import com.bilin.springmongo.service.user.IWbOrderService;

@Service
public class WbOrderServiceImpl extends CommonServiceImpl<WbOrder, String> implements IWbOrderService{

}
