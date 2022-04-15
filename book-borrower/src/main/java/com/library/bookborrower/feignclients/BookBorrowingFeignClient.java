package com.library.bookborrower.feignclients;

import com.library.bookborrower.dto.Borrowing;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("BORROWING-SERVICE")
public interface BookBorrowingFeignClient {
    @GetMapping("borrowings/{borrowingId}")
    Borrowing getBorrowingById(@PathVariable("borrowingId") long borrowingId);
}
