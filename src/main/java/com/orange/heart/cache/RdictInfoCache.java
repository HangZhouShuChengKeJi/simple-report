package com.orange.heart.cache;

import com.orange.heart.dao.RDictInfoDAO;
import com.orange.heart.dao.RReportDAO;
import com.orange.heart.dao.RReportSqlDAO;
import com.orange.heart.entity.RDictInfo;
import com.orange.heart.entity.RDictInfoExample;
import com.orange.heart.entity.RReport;
import com.orange.heart.entity.RReportExample;
import com.orange.heart.entity.RReportSql;
import com.orange.heart.entity.RReportSqlExample;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 字典集
 *
 * @author allen
 */
@Service("rdictInfoCache")
public class RdictInfoCache implements InitializingBean {

    @Resource
    private RDictInfoDAO rDictInfoDAO;

    private static Map<String, List<RDictInfo>> dictInfoListMap;

    private static Map<String, Map<String, String>> dictInfoMap;


    @Override
    public void afterPropertiesSet() throws Exception {
        refresh();
    }

    public void refresh() {
        List<RDictInfo> rDictInfoList = rDictInfoDAO.selectByExample(new RDictInfoExample());
        dictInfoListMap = new HashMap<String, List<RDictInfo>>();
        dictInfoMap = new HashMap<>();

        List<String> keyList = new ArrayList<String>();
        if (CollectionUtils.isNotEmpty(rDictInfoList)) {
            for (RDictInfo rDictInfo : rDictInfoList) {
                if (dictInfoListMap.containsKey(rDictInfo.getDictType())) {
                    dictInfoListMap.get(rDictInfo.getDictType()).add(rDictInfo);
                } else {
                    List<RDictInfo> tempList = new ArrayList();
                    tempList.add(rDictInfo);
                    dictInfoListMap.put(rDictInfo.getDictType(), tempList);
                }

                if (dictInfoMap.containsKey(rDictInfo.getDictType())) {
                    dictInfoMap.get(rDictInfo.getDictType()).put(rDictInfo.getDictCode(), rDictInfo.getDictValue());
                } else {
                    Map<String, String> tempMap = new HashMap<>();
                    tempMap.put(rDictInfo.getDictCode(), rDictInfo.getDictValue());
                    dictInfoMap.put(rDictInfo.getDictType(), tempMap);
                }
            }
        }
    }

    /**
     *
     * @return
     */
    public Map<String, List<RDictInfo>> getDictTypeListMap() {
        return dictInfoListMap;
    }

    /**
     * @return
     */
    public Map<String, Map<String, String>> getDictTypeMap() {
        return dictInfoMap;
    }

}
