package com.example.borrowing.service;

import com.example.borrowing.dao.BorrowingRepository;
import com.example.borrowing.dto.Borrowing;
import com.example.borrowing.dto.ResponseTemplateVo;
import com.example.borrowing.dto.User;
import com.example.borrowing.exception.BorrowingNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

//@Service
public class BorrowingService {

//    @Autowired
//    BorrowingRepository borrowingRepo;
//
//    @Autowired
//    private RestTemplate restTemplate;

//    public ResponseTemplateVo getBorrowingWithUser(Long borrowingId) {
//        ResponseTemplateVo vo = new ResponseTemplateVo();
//        Optional<Borrowing> borrowing = borrowingRepo.findById(borrowingId);
//        if(borrowing.isPresent()){
//            Borrowing borrowing1 = borrowingRepo.findByBorrowingId(borrowingId);
//
//            User user = restTemplate.getForObject("http://USER-SERVICE/users/" + borrowing1.getUserId(), User.class );
//
//            vo.setBorrowing(borrowing1);
//            vo.setUser(user);
//
//            return vo;
//        } else {
//            throw new BorrowingNotFoundException("No borrowing with id: "+borrowingId);
//        }
//
//    }

}
