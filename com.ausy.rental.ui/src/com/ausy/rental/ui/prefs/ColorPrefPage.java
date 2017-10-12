package com.ausy.rental.ui.prefs;

import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;

import com.ausy.rental.ui.RentalUIConstants;

public class ColorPrefPage extends FieldEditorPreferencePage implements RentalUIConstants {

	public ColorPrefPage() {
		// TODO Auto-generated constructor stub
		super(GRID);
	}


	@Override
	protected void createFieldEditors() {
		// TODO Auto-generated method stub
		addField(new ColorFieldEditor(PREF_CUSTOMER_COLOR, "Couleur Customer", getFieldEditorParent()));
		addField(new ColorFieldEditor(PREF_RENTAL_COLOR, "Couleur Rental", getFieldEditorParent()));
		addField(new ColorFieldEditor(PREF_RENTAL_OBJECT_COLOR, "Couleur Rental Object", getFieldEditorParent()));
	}

}
