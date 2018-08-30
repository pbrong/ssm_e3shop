package com.iteason.intef;

import com.iteason.pojo.TbItemDesc;
import com.iteason.pojo.TbItemSolrResult;

public interface ItemDetailService {

	TbItemSolrResult selectItemDetail(Long itemId);

	TbItemDesc selectItemDesc(Long itemId);

}
