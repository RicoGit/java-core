package com.company.java8;

/**
 * Project: Shamrock Web Portal.
 * User: Constantine Solovev
 * Date: 20.12.14
 * Time: 15:34
 */

public interface PersonFactory {

    Person create (String firstName, String lastName);
}
