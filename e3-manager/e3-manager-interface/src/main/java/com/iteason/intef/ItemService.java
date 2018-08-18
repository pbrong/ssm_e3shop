package com.iteason.intef;

import java.util.List;

import com.iteason.pojo.EasyUIDatagridResult;
import com.iteason.pojo.EsayUIZtreeNode;
import com.iteason.pojo.TbItem;
import com.iteason.pojo.TbItemDesc;

public interface ItemService {

	EasyUIDatagridResult findItemList(int page, int rows);

	List<EsayUIZtreeNode> findCatZtree(Long parentId);

	void saveItem(TbItem item, String desc);

	void delete(String[] idList);

	void instock(String ids);

	void reshelf(String ids);

	TbItemDesc desc(String id);

}
