package org.solution.movieShop;

import java.util.List;

public class PrintFormatter implements Formatter{
    @Override
    public String format(String header, List<Info> showList) {
        StringBuilder sb = new StringBuilder();
        sb.append(header);
        sb.append("\n");
        for (Info info:showList) {
            sb.append(info.getLabel());
            sb.append(" : ");
            sb.append(info.getValue());
            sb.append("\n");
        }
        return sb.toString();
    }
}
