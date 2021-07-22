package com.edytagodniak.releasemanager.system;

import com.edytagodniak.releasemanager.api.ServiceDeployInformation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SystemServiceTest {

    @Mock
    SystemFinder systemFinder;

    @Mock
    SystemHandler systemHandler;

    @InjectMocks
    SystemService systemService;

    @Test
    void addDeployInformation_creates_new_service_version_if_no_service_version_exists() {
        //given
        when(systemFinder.findLatestSystemVersion()).thenReturn(Optional.empty());
        when(systemHandler.addNewSystemVersion(any())).thenReturn(1);

        //when
        int systemVersion = systemService.addDeployInformation(new ServiceDeployInformation("serviceA", 1));

        //then
        verify(systemFinder).findLatestSystemVersion();
        verify(systemHandler).addNewSystemVersion(any());
        assertEquals(1, systemVersion);
    }

}