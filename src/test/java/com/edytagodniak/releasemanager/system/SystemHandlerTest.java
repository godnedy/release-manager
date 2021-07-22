package com.edytagodniak.releasemanager.system;

import com.edytagodniak.releasemanager.latestversion.LatestVersionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SystemHandlerTest {

    private static final Integer CURRENT_SYSTEM_VERSION = 10;


    @Mock
    SystemVersionRepository systemVersionRepository;

    @Mock
    LatestVersionService latestVersionService;

    @InjectMocks
    SystemHandler systemHandler;

    @Test
    void addNewSystemVersion_saves_system_version_and_increments_latest_version() {

        //given
        Map<String ,ServiceVersion> serviceVersions = new HashMap<>();
        ServiceVersion newServiceVersion = new ServiceVersion("serviceA", 1);
        serviceVersions.put("serviceA", newServiceVersion);

        when(latestVersionService.findLatestSystemVersion()).thenReturn(Optional.of(CURRENT_SYSTEM_VERSION));

        //when
        systemHandler.addNewSystemVersion(serviceVersions);

        //then
        SystemVersion expectedSystemVersion = new SystemVersion(CURRENT_SYSTEM_VERSION + 1, serviceVersions);

        verify(systemVersionRepository).save(expectedSystemVersion);
        verify(latestVersionService).updateLatestVersion(CURRENT_SYSTEM_VERSION + 1);
    }
}