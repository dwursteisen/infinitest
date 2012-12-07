package org.infinitest.intellij.idea;


import com.intellij.openapi.module.*;
import com.intellij.openapi.roots.*;
import com.intellij.openapi.vfs.*;
import org.junit.*;
import org.junit.runner.*;
import org.mockito.*;
import org.mockito.runners.*;

import java.io.*;
import java.util.*;

import static org.hamcrest.core.IsEqual.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class WhenBuildingIdeaModuleClassPath {

    public static final String PATH_A = "a";
    public static final String PATH_B = "b";

    @Mock
    private CompilerModuleExtension compilerModuleExtensionMock;

    @Mock
    private Module module;

    @Spy
    private IdeaModuleSettings ideaModuleSettingsSpy = new IdeaModuleSettings(module);

    @Test
    public void shouldIncludeAllCompilationClassesToClasspathElementsList() {
        doReturn(new VirtualFile[]{mockedVirtualFileWith(PATH_A), mockedVirtualFileWith(PATH_B)}).when(compilerModuleExtensionMock).getOutputRoots(true);
        doReturn(compilerModuleExtensionMock).when(ideaModuleSettingsSpy).compilerModuleExtensionInstance();

        final List<File> classPathElementsList = ideaModuleSettingsSpy.listClasspathElements();

        verify(ideaModuleSettingsSpy, times(1)).compilerModuleExtensionInstance();
        verify(compilerModuleExtensionMock, times(1)).getOutputRoots(true);
        assertThat(classPathElementsList.get(0).getPath(), equalTo(PATH_A));
        assertThat(classPathElementsList.get(1).getPath(), equalTo(PATH_B));
        assertThat(classPathElementsList.size(), equalTo(2));
    }

    private OrderEntry orderEntryMock() {
        return mock(OrderEntry.class);
    }

    private VirtualFile mockedVirtualFileWith(final String path) {
        final VirtualFile virtualFile = mock(VirtualFile.class);
        doReturn(path).when(virtualFile).getPath();

        return virtualFile;
    }
}
