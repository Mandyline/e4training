
package com.ausy.rental.ui.views;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;

import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;

import com.opcoach.training.rental.RentalAgency;

public class AgencePart {

	@PostConstruct
	public void createContent(Composite parent, RentalAgency a, IEclipseContext ctx) {
		TreeViewer tv= new TreeViewer(parent);
		//RentalProvider p=new RentalProvider();
		//(MApplication a a.getContext())
		RentalProvider p=ContextInjectionFactory.make(RentalProvider.class, ctx );
		tv.setContentProvider(p);
		tv.setLabelProvider(p);
		Collection<RentalAgency> lstAgences=new ArrayList<>();
		lstAgences.add(a);
		tv.setInput(lstAgences);
		tv.expandAll();
	}


}