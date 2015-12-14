
package com.liferay.docs.guestbook.portlet.searchcontainer;

import com.liferay.docs.guestbook.model.Entry;
import com.liferay.docs.guestbook.service.EntryLocalServiceUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchContextFactory;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class EntrySearchContainer extends SearchContainer<Entry> {

	private final Log logger = LogFactoryUtil.getLog(SearchContainer.class);
	private final List<Entry> entries;

	public EntrySearchContainer(HttpServletRequest request, String keywords) {

		this.entries = setEntries(request, keywords);
	}

	@Override
	public List<Entry> getResults() {
		return entries;

	}

	@Override
	public int getTotal() {

		return entries.size();
	}

	private List<Entry> setEntries(HttpServletRequest request, String keywords) {

		List<Entry> entries = Collections.emptyList();

		SearchContext searchContext = SearchContextFactory.getInstance(request);

		searchContext.setKeywords(keywords);
		searchContext.setAttribute("paginationType", "more");
		searchContext.setStart(0);
		searchContext.setEnd(10);

		Indexer indexer = IndexerRegistryUtil.getIndexer(Entry.class);

		try {
			Hits hits = indexer.search(searchContext);
			Document[] documents = hits.getDocs();

			entries = new ArrayList<Entry>(documents.length);

			for (int i = 0; i < documents.length; i++) {
				Document document = documents[i];

				long entryId =
					GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK));

				Entry entry = null;

				entry = EntryLocalServiceUtil.getEntry(entryId);

				entries.add(entry);
			}
		}
		catch (PortalException | SystemException e) {
			logger.error(e.getLocalizedMessage());
		}
		return entries;
	}
}
