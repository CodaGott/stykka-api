package com.stykkapi.stykka.services;

import com.stykkapi.stykka.dtos.ChangeBuyerPasswordDTO;
import com.stykkapi.stykka.dtos.RegisterBuyerDTO;
import com.stykkapi.stykka.exceptions.EmailExistsException;
import com.stykkapi.stykka.exceptions.InvalidPasswordException;
import com.stykkapi.stykka.models.Buyer;
import com.stykkapi.stykka.repositories.BuyerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class BuyerServiceImplTest {

    @Autowired
    private BuyerRepository buyerDb;

    @Autowired
    private BuyerService buyerService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void shouldThrowAnExceptionIfBuyerEmailAlreadyExists(){
        RegisterBuyerDTO newBuyer = new RegisterBuyerDTO();
        newBuyer.setFirstName("Jane");
        newBuyer.setLastName("Jones");
        newBuyer.setEmail("jones11@gmail.com");
        newBuyer.setPassword("jones112");
        assertThrows(EmailExistsException.class, () -> buyerService.registerBuyer(newBuyer));
    }

    @Test
    void shouldCreateABuyer(){

        RegisterBuyerDTO newBuyer = new RegisterBuyerDTO();
        newBuyer.setFirstName("Janey");
        newBuyer.setLastName("Joneses");
        newBuyer.setEmail("jones112111@gmail.com");
        newBuyer.setPassword("jones112");

        try{
            Buyer buyer2 = buyerService.registerBuyer(newBuyer);
        }catch(EmailExistsException e){
            e.getLocalizedMessage();
        }
    }

    @Test
    void shouldReturnAllBuyersInDatabase(){
        assertEquals(4, buyerService.getAllBuyers().stream().count());
    }

    @Test
    void shouldThrowAnExceptionIfBuyerDoesNotExist(){
       assertThrows(NoSuchElementException.class, () -> buyerService.getOneBuyer( "hjdjjjdjdj"));
    }

    @Test
    void shouldAllowBuyerChangePassword()  {
        ChangeBuyerPasswordDTO passwordDTO = new ChangeBuyerPasswordDTO();
        Optional<Buyer> foundBuyer = buyerDb.findByBuyerId("606a08fb4d247442f43de36c");
        passwordDTO.setPassword("234561");
        passwordDTO.setNewPassword("");
        try{
            buyerService.changePassword(passwordDTO,"606a08fb4d247442f43de36c");
        }catch(InvalidPasswordException e){
            System.out.println(e.getLocalizedMessage());;
        }
        assertEquals("234561", foundBuyer.get().getBuyerPassword());
    }


    @Test
    void shouldUpdateBuyerDetailIfBuyerExists() {

//        FrontEnd
        Buyer buyer = new Buyer();
        buyer.setBuyerFirstName("Good");
        buyer.setBuyerLastName("Bad");
        buyer.setBuyerEmail("Good@Bad.com");
//        Service
        buyerService.updateBuyer(buyer, "606a033d7031b77f0064910f");

//        Assert
        assertEquals("Good", buyer.getBuyerFirstName());
    }

    @Test
    void shouldAllowBuyerAddAddress(){}

    @Test
    void shouldAllowBuyerEditExistingAddress(){}

    @Test
    void shouldDeleteABuyer(){
        Optional<Buyer> buyerToDelete = buyerDb.findByBuyerId("60675f15ad083a1db89a6771");
        try{
            buyerService.deleteBuyer(buyerToDelete.get().getBuyerId());
        }catch(NoSuchElementException e){
            System.out.println(e.getLocalizedMessage());
        }
        assertEquals(2, buyerDb.count());
    }
}