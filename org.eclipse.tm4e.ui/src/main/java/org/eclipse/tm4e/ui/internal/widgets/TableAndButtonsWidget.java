/**
 *  Copyright (c) 2015-2017 Angelo ZERR.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *
 *  Contributors:
 *  Angelo Zerr <angelo.zerr@gmail.com> - initial API and implementation
 */
package org.eclipse.tm4e.ui.internal.widgets;

import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.tm4e.ui.internal.TMUIMessages;

/**
 * Widget which display a table on the left and buttons on the right.
 *
 */
public class TableAndButtonsWidget extends Composite {

	private TableViewer viewer;
	private Button newButton;
	private Button removeButton;

	public TableAndButtonsWidget(Composite parent, int style, String title) {
		super(parent, style);
		GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		layout.marginLeft = 0;
		layout.marginRight = 0;
		super.setLayout(layout);
		createUI(title, this);
	}

	private void createUI(String title, Composite ancestor) {
		Composite parent = new Composite(ancestor, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		layout.marginLeft = 0;
		layout.marginRight = 0;
		parent.setLayout(layout);
		parent.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		// Title
		createTitle(title, parent);

		// Table
		createTable(parent);

		// Buttons
		Composite buttonsComposite = new Composite(parent, SWT.NONE);
		layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		layout.marginLeft = 0;
		layout.marginRight = 0;
		buttonsComposite.setLayout(layout);
		buttonsComposite.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING | GridData.FILL_VERTICAL));
		createButtons(buttonsComposite);
	}

	private void createTitle(String title, Composite ancestor) {
		if (title == null) {
			return;
		}
		Label label = new Label(ancestor, SWT.NONE);
		label.setText(title);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 2;
		label.setLayoutData(data);
	}

	private void createTable(Composite parent) {
		Table table = new Table(parent, SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.V_SCROLL);
		table.setHeaderVisible(false);
		table.setLinesVisible(false);

		viewer = new TableViewer(table);
		table.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
	}

	private void createButtons(Composite parent) {
		newButton = new Button(parent, SWT.PUSH);
		newButton.setText(TMUIMessages.Button_new);
		newButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		newButton.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event e) {
				// add();
			}
		});
		newButton.setEnabled(false);

		removeButton = new Button(parent, SWT.PUSH);
		removeButton.setText(TMUIMessages.Button_remove);
		removeButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		removeButton.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event e) {

			}
		});
		removeButton.setEnabled(false);
	}

	public void setInput(Object input) {
		viewer.setInput(input);
	}

	public TableViewer getViewer() {
		return viewer;
	}

	public void setLabelProvider(IBaseLabelProvider labelProvider) {
		viewer.setLabelProvider(labelProvider);
	}

	public void setContentProvider(IContentProvider provider) {
		viewer.setContentProvider(provider);
	}

	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		viewer.addSelectionChangedListener(listener);
	}

	public void setSelection(IStructuredSelection selection) {
		viewer.setSelection(selection);
	}

	public IStructuredSelection getSelection() {
		return viewer.getStructuredSelection();
	}
}
