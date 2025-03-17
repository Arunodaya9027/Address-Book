package com.example.addressbook.interfaces;

import com.example.addressbook.dto.AddressBookDTO;

import java.util.List;

/**
 * Interface for Address Book Service.
 * Provides methods to manage address book data.
 */
public interface IAddressBookService {
    List<AddressBookDTO> getMyAddressBookData();
    AddressBookDTO getAddressBookDataById(long id);
    AddressBookDTO createAddressBookData(AddressBookDTO empPayrollDTO);
    boolean updateAddressBookData(long id, AddressBookDTO updatedAddressBookDTO);
    void deleteAddressBookData(long id);
    List<AddressBookDTO> getAllAddressBookData();
}