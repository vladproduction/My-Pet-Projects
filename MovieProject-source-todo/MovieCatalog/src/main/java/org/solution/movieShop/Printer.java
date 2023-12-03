package org.solution.movieShop;

import java.util.List;

public abstract class Printer {

    public String print(Formatter formatter){
        return formatter.format(getHeader(),getShowInfo());
    }

    protected abstract List<Info> getShowInfo();

    protected abstract String getHeader();

}
