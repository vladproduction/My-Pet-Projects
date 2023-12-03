package org.solution.movieShop;

import java.util.List;

public class HTMLFormatter implements Formatter{
    @Override
    public String format(String header, List<Info> infoList) {
        StringBuilder sb = new StringBuilder();
        sb.append("<table>");
        sb.append("<th>");
        sb.append("Age_Limit");
        sb.append("</th>");
        sb.append("<th>");
        sb.append(header);
        sb.append("</th>");
        for (Info element:infoList) {
            sb.append("<tr><td>");
            sb.append(element.getLabel());
            sb.append("</td><td>");
            sb.append(element.getValue());
            sb.append("</td></tr>");
        }
        return sb.toString();
    }
}
