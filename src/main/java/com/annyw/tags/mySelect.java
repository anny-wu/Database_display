package com.annyw.tags;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class mySelect extends  SimpleTagSupport {
    //id of the selected
    private String id;
    //values to select from
    Map<String,Integer> values;
    
    //Value to start with
    private int start;
    
    //value to end with
    private int end;
    
    //option selected
    private int select;
    
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public Map<String, Integer> getValues() {
        return values;
    }
    
    public void setValues(Map<String, Integer> values) {
        this.values = values;
    }
    
    public int getStart() {
        return start;
    }
    
    public void setStart(int start) {
        this.start = start;
    }
    
    public int getEnd() {
        return end;
    }
    
    public void setEnd(int end) {
        this.end = end;
    }
    
    public int getSelect() {
        return select;
    }
    
    public void setSelect(int select) {
        this.select = select;
    }
    
    public void doTag()throws IOException {
        
        Writer out = getJspContext().getOut();
        StringBuffer sb = new StringBuffer();
        sb.append("<select id='" + id + "'>");
        for (int i = start; i <= end; i++) {
            if (i == select) {
                sb.append("<option value='" + i + "' selected='selected'>");
            }
            else {
                sb.append("<option value='" + i + "'>");
            }
            sb.append(i + "</option>");
        }
        sb.append("</select>");
        out.write(sb.toString());
    }
}
