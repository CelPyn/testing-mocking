package com.axxes.testing.mocking.unittesting;


import com.axxes.testing.mocking.unittesting.exception.BeerAlreadyExistsException;
import com.axxes.testing.mocking.unittesting.exception.BeerNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

class BeerServiceTest {

    private final Database database = mock(Database.class);
    private final BeerService beerService = new BeerService(database);

}
