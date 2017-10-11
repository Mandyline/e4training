package com.ausy.rental.ui.views;

import java.util.Collection;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.RentalAgency;

public class RentalProvider extends LabelProvider implements ITreeContentProvider {

	public RentalProvider() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof Collection<?>)
			return ((Collection<?>) inputElement).toArray();
		return null;
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		Object[] result = null;
		// TODO Auto-generated method stub
		if (parentElement instanceof RentalAgency) {
			result = ((RentalAgency) parentElement).getCustomers().toArray();
		}
		return result;
	}

	@Override
	public Object getParent(Object element) {
		if(element instanceof RentalAgency)
		{
			return ((Customer)element).getParentAgency();
		}
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String getText(Object element) {
		if (element instanceof RentalAgency)
			return ((RentalAgency) element).getName();
		if(element instanceof Customer)
			return ((Customer) element).getDisplayName();
		// TODO Auto-generated method stub
		return super.getText(element);
	}
	
	@Override
	public Image getImage(Object element) {
		// TODO Auto-generated method stub
		return super.getImage(element);
	}
}
