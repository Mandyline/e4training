
package com.ausy.rental.ui.views;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.di.extensions.Preference;
import org.eclipse.e4.ui.services.EMenuService;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;

import com.ausy.rental.ui.RentalUIConstants;
import com.opcoach.training.rental.RentalAgency;

public class AgencePart implements RentalUIConstants {

	private TreeViewer tv;
	@PostConstruct
	public void createContent(Composite parent, RentalAgency a, IEclipseContext ctx, ESelectionService esel,
			EMenuService men) {
		tv = new TreeViewer(parent);
		// RentalProvider p=new RentalProvider();
		// (MApplication a a.getContext())
		RentalProvider p = ContextInjectionFactory.make(RentalProvider.class, ctx);
		tv.setContentProvider(p);
		tv.setLabelProvider(p);
		Collection<RentalAgency> lstAgences = new ArrayList<>();
		lstAgences.add(a);
		tv.setInput(lstAgences);
		tv.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				// TODO Auto-generated method stub
				IStructuredSelection sel = (IStructuredSelection) event.getSelection();
				esel.setSelection(sel.size() == 1 ? sel.getFirstElement() : sel.toArray());
			}
		});

		men.registerContextMenu(tv.getControl(), "com.ausy.rental.eap.popupmenu.0");
		tv.expandAll();
	}

	@Inject
	@Optional
	public void rafraichir(@Preference(value = PREF_CUSTOMER_COLOR) String cColor,
			@Preference(value = PREF_RENTAL_OBJECT_COLOR) String roColor,
			@Preference(value = PREF_RENTAL_COLOR) String rColor) {
		if(tv!=null)
		{
			tv.refresh();
		}
	}

}