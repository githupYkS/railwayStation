package com.gx.railwaystation.po;

import java.io.Serializable;

public class SysSite implements Serializable {
    /**
     * 站点ID
     */
    private Integer siteId;

    /**
     * 父级id(0表示是根全部)
     */
    private Integer parentId;

    /**
     * 站点名称
     */
    private String siteName;

    /**
     * 站点排序
     */
    private Integer siteSort;

    private static final long serialVersionUID = 1L;

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public Integer getSiteSort() {
        return siteSort;
    }

    public void setSiteSort(Integer siteSort) {
        this.siteSort = siteSort;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SysSite other = (SysSite) that;
        return (this.getSiteId() == null ? other.getSiteId() == null : this.getSiteId().equals(other.getSiteId()))
            && (this.getParentId() == null ? other.getParentId() == null : this.getParentId().equals(other.getParentId()))
            && (this.getSiteName() == null ? other.getSiteName() == null : this.getSiteName().equals(other.getSiteName()))
            && (this.getSiteSort() == null ? other.getSiteSort() == null : this.getSiteSort().equals(other.getSiteSort()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSiteId() == null) ? 0 : getSiteId().hashCode());
        result = prime * result + ((getParentId() == null) ? 0 : getParentId().hashCode());
        result = prime * result + ((getSiteName() == null) ? 0 : getSiteName().hashCode());
        result = prime * result + ((getSiteSort() == null) ? 0 : getSiteSort().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", siteId=").append(siteId);
        sb.append(", parentId=").append(parentId);
        sb.append(", siteName=").append(siteName);
        sb.append(", siteSort=").append(siteSort);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}