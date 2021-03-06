
package com.ausy.rental.ui.views;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;

public class RentalPart {

	private Label rentedObjectLabel;
	private Label lblSaisieDateAu;
	private Label lblSaisieDateDu;
	private Label lblSaisieLoueA;

	@Inject
	public RentalPart(Composite parent, RentalAgency a) {
		parent.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		parent.setLayout(new GridLayout(1, false));

		Group infoGroup = new Group(parent, SWT.NONE);
		GridData gd_infoGroup = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
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
		new Label(infoGroup, SWT.NONE);

		Group infoGroupDate = new Group(parent, SWT.NONE);
		infoGroupDate.setLayout(new GridLayout(2, false));
		infoGroupDate.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		infoGroupDate.setText("Dates de location");

		Label lblDateDu = new Label(infoGroupDate, SWT.NONE);
		lblDateDu.setText("du :");

		lblSaisieDateDu = new Label(infoGroupDate, SWT.NONE);
		lblSaisieDateDu.setText("New Label");

		Label lblDateAu = new Label(infoGroupDate, SWT.NONE);
		lblDateAu.setText("au :");

		lblSaisieDateAu = new Label(infoGroupDate, SWT.NONE);
		lblSaisieDateAu.setText("New Label");
		// code quand agency pas en paramètre
		// setRental(RentalCoreActivator.getAgency().getRentals().get(0));
		setRental(a.getRentals().get(0));
	}

	@Focus
	public void onFocus() {

	}

	public void setRental(Rental r) {
		if (r != null) {
			rentedObjectLabel.setText(r.getRentedObject().getName());
			lblSaisieDateDu.setText(r.getStartDate().toString());
			lblSaisieDateAu.setText(r.getEndDate().toString());
			lblSaisieLoueA.setText(r.getCustomer().getDisplayName());
		}
	}

	@Inject
	@Optional
	public void listenToSel(@Named(IServiceConstants.ACTIVE_SELECTION) Rental r) {
		// if(r!=null) on l'enlève car remplace de postcontruct par constructeur
		// {
		setRental(r);
		// }
	}
}