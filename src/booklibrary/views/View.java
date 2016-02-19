package booklibrary.views;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

import booklibrary.model.BookTo;
import booklibrary.provider.BookProvider;
import booklibrary.rest.BookRestService;

public class View extends ViewPart {
	
	public static final String ID = "BookLibrary.view";
	private TableViewer viewer;
	private Table table;
	private BookRestService bookRestService = new BookRestService();
	private List<BookTo> bookToList = new ArrayList<>();
	
	public View() {
		
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(3, false));

		Label searchText = new Label(parent, SWT.NONE);
		searchText.setText("Search:");
		Text titlePrefix = new Text(parent, SWT.BORDER);
		titlePrefix.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		Button searchButton = new Button(parent, SWT.NONE);
		searchButton.setText("Search");
		Button addButton = new Button(parent, SWT.NONE);
		addButton.setText("Add");
		Button deleteButton = new Button(parent, SWT.NONE);
		deleteButton.setText("Delete");

		/*btnAddBook.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				NewBookDialog dialog = new NewBookDialog(
						PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
				dialog.open();
				updateBooksList("");
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});*/
		
		// TODO : Mozna wyszukiwanie bez RESTa, na liscie
		searchButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					bookToList = bookRestService.sendGET(titlePrefix.getText());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				viewer.setInput(bookToList);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});
		
		deleteButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				IStructuredSelection selection = viewer.getStructuredSelection();
				BookTo book = (BookTo) selection.getFirstElement();
				if(book != null){
					try {
						bookRestService.sendDELETE(book.getId());
						bookToList = bookRestService.sendGET("");
						viewer.setInput(bookToList);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		try {
			createViewer(parent);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private void createViewer(Composite parent) throws IOException {
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		createColumns(parent, viewer);
		table = viewer.getTable();
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 3, 1));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		// TODO: Poczytaj o tym filtrowaniu
		/*viewer.addFilter(new ViewerFilter() {
			
			@Override
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				// TODO Auto-generated method stub
				return false;
			}
		});*/

		viewer.setContentProvider(new ArrayContentProvider());
		viewer.setInput(BookProvider.INSTANCE.getBooks());
		getSite().setSelectionProvider(viewer);

		GridData gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		gridData.horizontalSpan = 3;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		viewer.getControl().setLayoutData(gridData);
	}

	public TableViewer getViewer() {
		return viewer;
	}

	private void createColumns(final Composite parent, final TableViewer viewer) {
		String[] titles = { "Id", "Title" };
		int[] bounds = { 50, 185 };

		TableViewerColumn col = createTableViewerColumn(titles[0], bounds[0], 0);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				BookTo book = (BookTo) element;
				String id = String.valueOf(book.getId());
				return id;
			}
		});

		col = createTableViewerColumn(titles[1], bounds[1], 1);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				BookTo book = (BookTo) element;
				return book.getTitle();
			}
		});

	}

	private TableViewerColumn createTableViewerColumn(String title, int bound, final int colNumber) {
		final TableViewerColumn viewerColumn = new TableViewerColumn(viewer, SWT.NONE);
		final TableColumn column = viewerColumn.getColumn();
		column.setText(title);
		column.setWidth(bound);
		column.setResizable(true);
		column.setMoveable(true);
		return viewerColumn;
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}