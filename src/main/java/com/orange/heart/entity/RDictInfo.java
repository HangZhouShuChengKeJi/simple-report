package com.orange.heart.entity;

import java.io.Serializable;

/**
 * 
 * 
 * @table r_dict_info
 * @author MyBatis Generator
 * @version 1.0.0, 2019-02-19 16:50:33
 */
public class RDictInfo implements Serializable {
    /**
     * 
     * 
     * @column r_dict_info.id
     */
    private Integer id;

    /**
     * 字典类型
     * 
     * @column r_dict_info.dict_type
     */
    private String dictType;

    /**
     * 字典代码
     * 
     * @column r_dict_info.dict_code
     */
    private String dictCode;

    /**
     * 字典值
     * 
     * @column r_dict_info.dict_value
     */
    private String dictValue;

    private static final long serialVersionUID = 1L;

    /**
     * @column r_dict_info.id
     * 
     * @return 
     */
    public Integer getId() {
        return id;
    }

    /**
     * @column r_dict_info.id
     * 
     * @param id 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @column r_dict_info.dict_type
     * 
     * @return 字典类型
     */
    public String getDictType() {
        return dictType;
    }

    /**
     * @column r_dict_info.dict_type
     * 
     * @param dictType 字典类型
     */
    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    /**
     * @column r_dict_info.dict_code
     * 
     * @return 字典代码
     */
    public String getDictCode() {
        return dictCode;
    }

    /**
     * @column r_dict_info.dict_code
     * 
     * @param dictCode 字典代码
     */
    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    /**
     * @column r_dict_info.dict_value
     * 
     * @return 字典值
     */
    public String getDictValue() {
        return dictValue;
    }

    /**
     * @column r_dict_info.dict_value
     * 
     * @param dictValue 字典值
     */
    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }
}