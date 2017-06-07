package com.dl.commonslidebanner;

/**
 * Created by zhouwei on 17/5/26.
 */

public interface DLHolderCreator<VH extends DLViewHolder> {
    /**
     * 创建ViewHolder
     * @return
     */
    public VH createViewHolder();
}
