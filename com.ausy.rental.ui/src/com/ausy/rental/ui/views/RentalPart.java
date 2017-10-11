
package com.ausy.rental.ui.views;

import javax.annotation.PostConstruct;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

import com.ausy.rental.core.RentalCoreActivator;
import com.opcoach.training.rental.Rental;
import org.eclipse.wb.swt.SWTResourceManager;

public class RentalPart {

	private Label rentedObjectLabel;
	private Label lblSaisieDateAu;
	private Label lblSaisieDateDu;
	private Label lblSaisieLoueA;

	@PostConstruct
	public void createContent(Composite parent) {
		parent.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		parent.setLayout(new GridLayout(1, false));

		Group infoGroup = new Group(parent, SWT.NONE);
		GridData gd_infoGroup = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_infoGroup.widthHint = 257;
		infoGroup.setLayoutData(gd_infoGroup);
		infoGroup.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		infoGroup.setText("Information");
		infoGroup.setLayout(new GridLayout(4, false));

		rentedObjectLabel = new Label(infoGroup, SWT.BORDER);
		GridData gd = new GridData();
		gd.horizontalAlignment = SWT.FILL;
		rentedObjectLabel.setLayoutData(gd);

		Label lblLoueA = new Label(infoGroup, SWT.NONE);
		lblLoueA.setText("Loué à");

		lblSaisieLoueA = new Label(infoGroup, SWT.NONE);
		lblSaisieLoueA.setText("New Label");

		Group infoGroupDate = new Group(parent, SWT.NONE);
		infoGroupDate.setLayout(new GridLayout(2, false));
		infoGroupDate.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		infoGroupDate.setText("Dates de location");

		Label lblDateDu = new Label(infoGroupDate, SWT.NONE);
		lblDateDu.setText("du :");

		lblSaisieDateDu = new Label(infoGroupDate, SWT.NONE);
		lblSaisieDateDu.setText("New Label");

		Label lblDateAu = new Label(infoGroupDate, SWT.NONE);
		lblDateAu.setText("au :");

		lblSaisieDateAu = new Label(infoGroupDate, SWT.NONE);
		lblSaisieDateAu.setText("New Label");
		setRental(RentalCoreActivator.getAgency().getRentals().get(0));
	}

	@Focus
	public void onFocus() {

	}

	public void setRental(Rental r) {
		rentedObjectLabel.setText(r.getRentedObject().getName());
		lblSaisieDateDu.setText(r.getStartDate().toString());
		lblSaisieDateAu.setText(r.getEndDate().toString());
		lblSaisieLoueA.setText(r.getCustomer().getDisplayName());
	}
}