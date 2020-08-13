package design_patterns.active_type.observe;

import java.util.ArrayList;
import java.util.List;

public abstract class Writer {


    List<Reader> readerList;

    public Writer() {
        this.readerList = new ArrayList<>();
    }

    public void addFans(Reader reader) {
        readerList.add(reader);
    }

    public void addContent(String s) {
        notifyAllReader(s);
    };

    protected void notifyAllReader(String s) {
     readerList.forEach(reader -> {
         reader.getContent(s);
     });
    }




}
