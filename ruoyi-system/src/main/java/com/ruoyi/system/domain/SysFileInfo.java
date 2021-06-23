package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 文件信息对象 sys_file_info
 * 
 * @author wuzh
 * @date 2021-06-04
 */
public class SysFileInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long fileId;

    /** 平台类型 */
    @Excel(name = "平台类型")
    private String platType;

    /** 文件类型 */
    @Excel(name = "文件类型")
    private String fileType;

    /** 文件名 */
    @Excel(name = "文件名")
    private String fileName;

    /** 文件后缀 */
    @Excel(name = "文件后缀")
    private String suffix;

    /** 本地路径 */
    @Excel(name = "本地路径")
    private String filePath;

    /** 客户端访问路径 */
    @Excel(name = "客户端访问路径")
    private String visitUrl;

    /** 文件大小，单位bit */
    @Excel(name = "文件大小，单位bit")
    private Long size;

    /** 文件大小，带单位 */
    @Excel(name = "文件大小，带单位")
    private String resultSize;

    /** 文件描述 */
    @Excel(name = "文件描述")
    private String description;

    /** 查看次数 */
    @Excel(name = "查看次数")
    private Long checkTimes;

    /** 下载次数 */
    @Excel(name = "下载次数")
    private Long downloadTimes;

    /** 文件标签 */
    @Excel(name = "文件标签")
    private String tag;

    /** （全局权限）文件是否可以下载，0不可以，1可以 */
    @Excel(name = "", readConverterExp = "全=局权限")
    private Long isDownloadable;

    /** （全局权限）文件夹是否允许上传（需要判断文件是否是文件夹），0不可以，1可以 */
    @Excel(name = "", readConverterExp = "全=局权限")
    private Long isUploadable;

    /** （全局权限）文件是否可见，0不可以，1可以 */
    @Excel(name = "", readConverterExp = "全=局权限")
    private Long isVisible;

    /** （全局权限）文件是否可以删除，0不可以，1可以 */
    @Excel(name = "", readConverterExp = "全=局权限")
    private Long isDeletable;

    /** （全局权限）文件是否可以更新，0不可以，1可以 */
    @Excel(name = "", readConverterExp = "全=局权限")
    private Long isUpdatable;

    /** 是否删除  1：已删除  0：正常 */
    private String delFlag;

    public void setFileId(Long fileId) 
    {
        this.fileId = fileId;
    }

    public Long getFileId() 
    {
        return fileId;
    }
    public void setPlatType(String platType) 
    {
        this.platType = platType;
    }

    public String getPlatType() 
    {
        return platType;
    }
    public void setFileType(String fileType) 
    {
        this.fileType = fileType;
    }

    public String getFileType() 
    {
        return fileType;
    }
    public void setFileName(String fileName) 
    {
        this.fileName = fileName;
    }

    public String getFileName() 
    {
        return fileName;
    }
    public void setSuffix(String suffix) 
    {
        this.suffix = suffix;
    }

    public String getSuffix() 
    {
        return suffix;
    }
    public void setFilePath(String filePath) 
    {
        this.filePath = filePath;
    }

    public String getFilePath() 
    {
        return filePath;
    }
    public void setVisitUrl(String visitUrl) 
    {
        this.visitUrl = visitUrl;
    }

    public String getVisitUrl() 
    {
        return visitUrl;
    }
    public void setSize(Long size) 
    {
        this.size = size;
    }

    public Long getSize() 
    {
        return size;
    }
    public void setResultSize(String resultSize) 
    {
        this.resultSize = resultSize;
    }

    public String getResultSize() 
    {
        return resultSize;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setCheckTimes(Long checkTimes) 
    {
        this.checkTimes = checkTimes;
    }

    public Long getCheckTimes() 
    {
        return checkTimes;
    }
    public void setDownloadTimes(Long downloadTimes) 
    {
        this.downloadTimes = downloadTimes;
    }

    public Long getDownloadTimes() 
    {
        return downloadTimes;
    }
    public void setTag(String tag) 
    {
        this.tag = tag;
    }

    public String getTag() 
    {
        return tag;
    }
    public void setIsDownloadable(Long isDownloadable) 
    {
        this.isDownloadable = isDownloadable;
    }

    public Long getIsDownloadable() 
    {
        return isDownloadable;
    }
    public void setIsUploadable(Long isUploadable) 
    {
        this.isUploadable = isUploadable;
    }

    public Long getIsUploadable() 
    {
        return isUploadable;
    }
    public void setIsVisible(Long isVisible) 
    {
        this.isVisible = isVisible;
    }

    public Long getIsVisible() 
    {
        return isVisible;
    }
    public void setIsDeletable(Long isDeletable) 
    {
        this.isDeletable = isDeletable;
    }

    public Long getIsDeletable() 
    {
        return isDeletable;
    }
    public void setIsUpdatable(Long isUpdatable) 
    {
        this.isUpdatable = isUpdatable;
    }

    public Long getIsUpdatable() 
    {
        return isUpdatable;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("fileId", getFileId())
            .append("platType", getPlatType())
            .append("fileType", getFileType())
            .append("fileName", getFileName())
            .append("suffix", getSuffix())
            .append("filePath", getFilePath())
            .append("visitUrl", getVisitUrl())
            .append("size", getSize())
            .append("resultSize", getResultSize())
            .append("description", getDescription())
            .append("checkTimes", getCheckTimes())
            .append("downloadTimes", getDownloadTimes())
            .append("tag", getTag())
            .append("isDownloadable", getIsDownloadable())
            .append("isUploadable", getIsUploadable())
            .append("isVisible", getIsVisible())
            .append("isDeletable", getIsDeletable())
            .append("isUpdatable", getIsUpdatable())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
