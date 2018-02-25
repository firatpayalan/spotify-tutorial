package com.firat.client.ui;

import com.firat.client.model.TypeModel;
import com.firat.client.ui.SearchPage;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@SpringUI
public class VaadinUI extends UI {


	private final SearchPage searchPage;
	final TextField filter;
	private final ComboBox<TypeModel> typeCombobox;

	private final Button addNewBtn;

	@Autowired
	public VaadinUI(SearchPage searchPage) {
		this.filter = new TextField("Search query keywords and optional field filters and operators");
		this.searchPage = searchPage;
		this.addNewBtn = new Button("Search", FontAwesome.SEARCH);
		this.typeCombobox = new ComboBox<>("Search results include hits from all the specified item types.");

		List<TypeModel> types = new ArrayList<>();
		types.add(new TypeModel(1,"album"));
		this.typeCombobox.setItems(types);
		this.typeCombobox.setItemCaptionGenerator(TypeModel::getName);
		//default value.
		this.typeCombobox.setValue(new TypeModel(1,"album"));
		addNewBtn.addClickListener(e -> retrieveResults(filter.getValue(),typeCombobox.getValue().getName()));
	}

	@Override
	protected void init(VaadinRequest request)
	{
		VerticalLayout actions=new VerticalLayout(filter, typeCombobox,addNewBtn);
		VerticalLayout mainLayout=new VerticalLayout(actions,searchPage);
		setContent(mainLayout);
	}

	private void retrieveResults(String q,String type)
	{
		searchPage.retrieveResult(q,type);
	}

}
