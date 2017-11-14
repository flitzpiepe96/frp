package de.tinf15b4.frp.paradigms;

import de.tinf15b4.frp.api.Document;
import de.tinf15b4.frp.api.MouseEvent;
import de.tinf15b4.frp.api.Paradigm;

public class ListenerPattern implements Paradigm {

    private final DocumentListener documentListener;
    private Document document;

    public ListenerPattern(Document initDoc,  DocumentListener documentListener) {
        this.document = initDoc;
        this.documentListener = documentListener;
    }

    public void mouseEvent(MouseEvent mouseEvent) {
    }

    public void dispose() {}
}
