package booklibrary.views;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.part.ViewPart;

import booklibrary.model.BookTo;

public class AuthorList extends ViewPart {
	
	private TableViewer authorsTableViewer;
	private Table authorsTable;
	private BookTo book;

	public AuthorList() {

	}
	
	public BookTo getBook() {
		return book;
	}

	@Override
	public void createPartControl(Composite parent) {		
		parent.setLayout(new GridLayout(2, false));
		book = new BookTo();
		
		Label label = new Label(parent, SWT.NONE);
		label.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		label.setAlignment(SWT.CENTER);
		label.setText("Author list:");
		
	}
	
	/*// create the columns for the table
	  private void createColumns(final Composite parent, final TableViewer viewer) {
	    String[] titles = { "First name", "Last name", "Gender", "Married" };
	    int[] bounds = { 100, 100, 100, 100 };

	    // first column is for the first name
	    TableViewerColumn col = createTableViewerColumn(titles[0], bounds[0], 0);
	    col.setLabelProvider(new ColumnLabelProvider() {
	      @Override
	      public String getText(Object element) {
	        Person p = (Person) element;
	        return p.getFirstName();
	      }
	    });

	    // second column is for the last name
	    col = createTableViewerColumn(titles[1], bounds[1], 1);
	    col.setLabelProvider(new ColumnLabelProvider() {
	      @Override
	      public String getText(Object element) {
	        Person p = (Person) element;
	        return p.getLastName();
	      }
	    });

	    // now the gender
	    col = createTableViewerColumn(titles[2], bounds[2], 2);
	    col.setLabelProvider(new ColumnLabelProvider() {
	      @Override
	      public String getText(Object element) {
	        Person p = (Person) element;
	        return p.getGender();
	      }
	    });

	    // now the status married
	    col = createTableViewerColumn(titles[3], bounds[3], 3);
	    col.setLabelProvider(new ColumnLabelProvider() {
	      @Override
	      public String getText(Object element) {
	        return null;
	      }

	      @Override
	      public Image getImage(Object element) {
	        if (((Person) element).isMarried()) {
	          return CHECKED;
	        } else {
	          return UNCHECKED;
	        }
	      }
	    });

	  }

	  private TableViewerColumn createTableViewerColumn(String title, int bound, final int colNumber) {
	    final TableViewerColumn viewerColumn = new TableViewerColumn(viewer,
	        SWT.NONE);
	    final TableColumn column = viewerColumn.getColumn();
	    column.setText(title);
	    column.setWidth(bound);
	    column.setResizable(true);
	    column.setMoveable(true);
	    return viewerColumn;
	  }*/

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}

}
