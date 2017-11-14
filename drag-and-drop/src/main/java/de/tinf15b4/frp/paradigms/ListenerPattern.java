package de.tinf15b4.frp.paradigms;

import java.awt.Point;
import java.util.Optional;
import java.util.UUID;

import de.tinf15b4.frp.api.Document;
import de.tinf15b4.frp.api.Document.Entry;
import de.tinf15b4.frp.api.Element;
import de.tinf15b4.frp.api.MouseEvent;
import de.tinf15b4.frp.api.Paradigm;

public class ListenerPattern implements Paradigm {

	private final DocumentListener documentListener;
	private Document document;

	private UUID toDragId;
	private Element toDrag;
	private Point lastPoint;

	public ListenerPattern(Document initDoc, DocumentListener documentListener) {
		this.document = initDoc;
		this.documentListener = documentListener;
	}

	@Override
	public void mouseEvent(MouseEvent mouseEvent) {
		switch (mouseEvent.type()) {
		case DOWN:
			Optional<? extends Entry> entry = document.atPoint(mouseEvent.point());
			entry.ifPresent(e -> {
				toDrag = e.element();
				toDragId = e.id();
				lastPoint = mouseEvent.point();
			});
			break;
		case UP:
			toDrag = null;
			toDragId = null;
			lastPoint = null;
			break;
		case MOVE:
			if (toDrag != null && toDragId != null) {
				Point translation = new Point(mouseEvent.point().x - lastPoint.x, mouseEvent.point().y - lastPoint.y);
				lastPoint = mouseEvent.point();
				Element dragged = toDrag.translate(translation);
				document = document.insert(toDragId, dragged);
				toDrag = dragged;
				documentListener.documentUpdated(document);
				break;
			}
		}
	}

	@Override
	public void dispose() {
	}
}
