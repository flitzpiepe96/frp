package de.tinf15b4.frp.swidgets;

import nz.sodium.Cell;
import nz.sodium.CellSink;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Optional;
import java.util.Vector;

public class SComboBox<E> extends JComboBox<E> {

	private static final long serialVersionUID = 2418303177835109572L;

	public SComboBox() {
		selectedItem = mkSelectedItem(this);
	}

	public SComboBox(ComboBoxModel<E> aModel) {
		super(aModel);
		selectedItem = mkSelectedItem(this);
	}

	public SComboBox(E[] items) {
		super(items);
		selectedItem = mkSelectedItem(this);
	}

	public SComboBox(Vector<E> items) {
		super(items);
		selectedItem = mkSelectedItem(this);
	}

	@SuppressWarnings("unchecked")
	private static <E> Cell<Optional<E>> mkSelectedItem(JComboBox<E> box) {
		E sel = (E) box.getSelectedItem();
		CellSink<Optional<E>> selectedItem = new CellSink<Optional<E>>(
				sel == null ? Optional.<E>empty() : Optional.of(sel));
		box.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED)
					selectedItem.send(Optional.of((E) e.getItem()));
			}
		});
		return selectedItem;
	}

	public final Cell<Optional<E>> selectedItem;
}
