package com.whl.common.lambda.functional;

import com.whl.common.lambda.model.Person;

public interface PersonFactory<P extends Person> {

    P create(String firstName, String lastName);
}
