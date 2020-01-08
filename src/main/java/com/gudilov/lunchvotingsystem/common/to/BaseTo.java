package com.gudilov.lunchvotingsystem.common.to;

import com.gudilov.lunchvotingsystem.common.model.HasId;

public abstract class BaseTo implements HasId {
    protected Integer id;

    public BaseTo() {
    }

    public BaseTo(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseTo)) return false;

        BaseTo baseTo = (BaseTo) o;

        return getId() != null ? getId().equals(baseTo.getId()) : baseTo.getId() == null;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}
