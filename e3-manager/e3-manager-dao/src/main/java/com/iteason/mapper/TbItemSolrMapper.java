package com.iteason.mapper;

import java.util.List;

import com.iteason.pojo.TbItemSolrResult;

public interface TbItemSolrMapper {
	List<TbItemSolrResult> selectTbItemSolrResultList();
	TbItemSolrResult selectTbItemSolrResult(Long id);
}
