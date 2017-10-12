package com.ausy.rental.ui.views;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.StringConverter;
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

	@Inject @Named(RENTAL_UI_PREF_STORE)
	private IPreferenceStore pStore;
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
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((a == null) ? 0 : a.hashCode());
			result = prime * result + ((label == null) ? 0 : label.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (a == null) {
				if (other.a != null)
					return false;
			} else if (!a.equals(other.a))
				return false;
			if (label == null) {
				if (other.label != null)
					return false;
			} else if (!label.equals(other.label))
				return false;
			return true;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return label;
		}

		private RentalProvider getOuterType() {
			return RentalProvider.this;
		}
		
	}

	@Override
	public Color getForeground(Object element) {
		// TODO Auto-generated method stub
		if (element instanceof RentalAgency)
			return Display.getCurrent().getSystemColor(SWT.COLOR_BLUE);
		else if(element instanceof Customer)
			return getAColor(pStore.getString(PREF_CUSTOMER_COLOR));
		else if(element instanceof RentalObject)
			return getAColor(pStore.getString(PREF_RENTAL_OBJECT_COLOR));
		else if(element instanceof Rental)
			return getAColor(pStore.getString(PREF_RENTAL_COLOR));
		
			
		return null;
	}

	@Override
	public Color getBackground(Object element) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * A private methode to get a color in from a rgb key
	 * @param rgbKey the rgb value as String. For instance : "10,20,30"
	 * @return
	 */
	private Color getAColor(String rgbKey)
	{
		//je créé une couleur à partir d'une clef
		
		//Use the global color reguistry (from jface)
		ColorRegistry colorRegistry=JFaceResources.getColorRegistry();
		
		//test if a color exists for this key
		Color col=colorRegistry.get(rgbKey);
		if(col==null&&!rgbKey.isEmpty())
		{
			colorRegistry.put(rgbKey, StringConverter.asRGB(rgbKey));
			col=colorRegistry.get(rgbKey);
		}
		return col;
	}
}
