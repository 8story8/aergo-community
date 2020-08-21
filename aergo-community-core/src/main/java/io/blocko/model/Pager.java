package io.blocko.model;

import java.util.List;

import org.springframework.data.domain.Page;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Pager<T> {

	public static final int SIZE = 10;

	private int totalPages;

	private int currentPage;

	private int startPage;

	private int endPage;

	private int prevBlockPage;

	private int nextBlockPage;

	private List<T> contents;

	private Pager(int currentPage, int startPage, int endPage, int prevBlockPage, int nextBlockPage, int totalPages,
			List<T> contents) {
		this.currentPage = currentPage;
		this.startPage = startPage;
		this.endPage = endPage;
		this.prevBlockPage = prevBlockPage;
		this.nextBlockPage = nextBlockPage;
		this.totalPages = totalPages;
		this.contents = contents;
	}

	public static <T, G> Pager<T> of(List<T> contents, Page<G> pages) {
		final int totalPages = pages.getTotalPages() == 0 ? 1 : pages.getTotalPages();
		final int currentPage = pages.getPageable().getPageNumber() + 1 > totalPages ? totalPages
				: pages.getPageable().getPageNumber() + 1;
		final int startPage = ((currentPage - 1) / SIZE) * SIZE + 1;
		final int endPage = startPage + SIZE - 1 > totalPages ? totalPages : startPage + SIZE - 1;
		final int prevBlockPage = startPage - SIZE < 0 ? 1 : startPage - SIZE;
		final int nextBlockPage = endPage + 1 < totalPages ? endPage + 1 : totalPages;
		return new Pager<>(currentPage, startPage, endPage, prevBlockPage, nextBlockPage, totalPages, contents);
	}
}
