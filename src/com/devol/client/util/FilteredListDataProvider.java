package com.devol.client.util;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.Range;

public class FilteredListDataProvider<T> extends ListDataProvider<T> {

    private String filterString;

    public final IFilter<T> filter;
    
    public List<T> resulted;

    public FilteredListDataProvider(IFilter<T> filter) {
        this.filter = filter;
    }


    public String getFilter() {
        return filterString;
    }

    public void setFilter(String filter) {
        this.filterString = filter;
        refresh();
    }

    public void resetFilter() {
        filterString = null;
        refresh();
    }

    public boolean hasFilter() {
        return !(filterString==null || "".equals(filterString));
    }

    @Override
    protected void updateRowData(HasData display, int start, List values) {
        if (!hasFilter() || filter == null) { // we don't need to filter, so call base class
            super.updateRowData(display, start, values);
        } else {
            int end = start + values.size();
            Range range = display.getVisibleRange();
            int curStart = range.getStart();
            int curLength = range.getLength();
            int curEnd = curStart + curLength;
            if (start == curStart || (curStart < end && curEnd > start)) {
                int realStart = curStart < start ? start : curStart;
                int realEnd = curEnd > end ? end : curEnd;
                int realLength = realEnd - realStart;
                resulted = new ArrayList<T>(realLength);
                for (int i = realStart - start; i < realStart - start + realLength; i++) {
                    if (filter.isValid((T) values.get(i), getFilter())) {
                        resulted.add((T) values.get(i));
                    }
                }
                display.setRowData(realStart, resulted);
                display.setRowCount(resulted.size());                                
            }
        }
    }
}
