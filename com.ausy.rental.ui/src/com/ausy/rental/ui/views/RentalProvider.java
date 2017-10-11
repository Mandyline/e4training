package com.ausy.rental.ui.views;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import com.ausy.rental.ui.RentalUIConstants;
import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalObject;


public class RentalProvider extends LabelProvider implements ITreeContentProvider, IColorProvider, RentalUIConstants {

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
		/*Object[] result = null;
		// TODO Auto-generated method stub
		if (parentElement instanceof RentalAgency) {
			result = ((RentalAgency) parentElement).getCustomers().toArray();
		}*/
		if(parentElement instanceof RentalAgency)
		{
			RentalAgency a=(RentalAgency) parentElement;
			return new Node[] {new Node(Node.CUSTOMERS,a), new Node(Node.LOCATIONS,a), new Node(Node.OBJETS_À_LOUER,a)};
		}
		else if(parentElement instanceof Node)
		{
			return ((Node) parentElement).getChildren();
		}
		return null;
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
		else if(element instanceof Customer)
			return ((Customer) element).getDisplayName();
		else if(element instanceof RentalObject)
			return ((RentalObject) element).getName();
		else if(element instanceof Rental)
			return ((Rental) element).toString();
		// TODO Auto-generated method stub
		return super.getText(element);
	}
	
	@Inject @Named(RENTAL_UI_IMG_REGISTRY)
	private ImageRegistry registry;
	
	@Override
	public Image getImage(Object element) {
		// TODO Auto-generated method stub
		if (element instanceof RentalAgency)
			return registry.get(IMG_AGENCY);
		else if(element instanceof Customer)
		{
			return registry.get(IMG_CUSTOMER);
		}
		else if(element instanceof RentalObject)
			return registry.get(IMG_RENTAL_OBJECT);
		else if(element instanceof Rental)
			return registry.get(IMG_RENTAL);
		return null;
	}
	
	class Node{
		
		public static final String OBJETS_À_LOUER = "Objets à louer";
		public static final String LOCATIONS = "Locations";
		public static final String CUSTOMERS = "Customers";
		private String label;
		private RentalAgency a;
		
		public Node(String label, RentalAgency a) {
			super();
			this.label = label;
			this.a = a;
		}
		
		public Object[] getChildren()
		{
			if(label==CUSTOMERS)
				return a.getCustomers().toArray();
			else if(label==LOCATIONS)
				return a.getRentals().toArray();
			else if(label==OBJETS_À_LOUER)
				return a.getObjectsToRent().toArray();
			return null;
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return label;
		}
		
	}

	@Override
	public Color getForeground(Object element) {
		// TODO Auto-generated method stub
		if (element instanceof RentalAgency)
			return Display.getCurrent().getSystemColor(SWT.COLOR_BLUE);
		else if(element instanceof Customer)
			return Display.getCurrent().getSystemColor(SWT.COLOR_RED);
		else if(element instanceof RentalObject)
			return Display.getCurrent().getSystemColor(SWT.COLOR_GREEN);
		else if(element instanceof Rental)
			return Display.getCurrent().getSystemColor(SWT.COLOR_MAGENTA);
		
			
		return null;
	}

	@Override
	public Color getBackground(Object element) {
		// TODO Auto-generated method stub
		return null;
	}
}
