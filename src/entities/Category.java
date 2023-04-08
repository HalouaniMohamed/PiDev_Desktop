/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author ALPHA
 */
public class Category {

    private Integer id;
    private String categoryName;
    private String description;
    private Date createAt;
    private Date updatedAt;
//    private List<Product> productList;

    public Category() {
    }

    public Category(Integer id) {
        this.id = id;
    }
// constructor for categories with empty description

    public Category(Integer id, String categoryName, Date createdAt, Date updatedAt) {
        this.id = id;
        this.description = "";
        this.categoryName = categoryName;
        this.createAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Category(Integer id, String categoryName, String description, Date createdAt, Date updatedAt) {
        this.id = id;
        this.categoryName = categoryName;
        this.description = description;
        this.createAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Category)) {
            return false;
        }
        Category other = (Category) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Category{" + "id=" + id + ", categoryName=" + categoryName + ", description=" + description + ", createAt=" + createAt + ", updatedAt=" + updatedAt + '}';
    }

}
