package ua.training.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import ua.training.controller.DefaultConfigSettings;
import ua.training.model.entities.FileExtensionsVector;
import ua.training.model.entities.IGraphicFile;
import ua.training.model.entities.RasterGraphicFile;
import ua.training.model.entities.VectorGraphicFile;

public class SlideShowCollectionTest {
	
	private ArrayList<IGraphicFile> createAnExampleListOfFiles() {
		ArrayList<IGraphicFile> availableGraphicFilesList = new ArrayList<IGraphicFile>();
		
		VectorGraphicFile graphicFile1 = new VectorGraphicFile();
		graphicFile1.setPathToFile("D:\\Documents\\Java\\Tasks\\TASK1\\graphicFiles\\1.svg");
		graphicFile1.setFileSize(100003);
		graphicFile1.setName("1");
		graphicFile1.setLastModified(1575160669901L);
		graphicFile1.setFileExtension(FileExtensionsVector.SVG);
		graphicFile1.addTag("signs");
		
		availableGraphicFilesList.add(graphicFile1);
		
		RasterGraphicFile graphicFile2 = new RasterGraphicFile();
		graphicFile2.setPathToFile("D:\\Documents\\Java\\Tasks\\TASK1\\graphicFiles\\2.jpg");
		graphicFile2.setFileSize(100002);
		graphicFile2.setName("2");
		graphicFile2.setLastModified(1575160669801L);
		graphicFile2.addTag("humor");
		graphicFile2.addTag("road");
		
		availableGraphicFilesList.add(graphicFile2);
		
		RasterGraphicFile graphicFile3 = new RasterGraphicFile();
		graphicFile3.setPathToFile("D:\\Documents\\Java\\Tasks\\TASK1\\graphicFiles\\3.jpg");
		graphicFile3.setFileSize(100001);
		graphicFile3.setName("3");
		graphicFile3.setLastModified(1575160669952L);
		graphicFile3.setHeightPixels(100);
		graphicFile3.setWidthPixels(100);
		graphicFile3.addTag("signs");
		
		availableGraphicFilesList.add(graphicFile3);
		
		return availableGraphicFilesList;
	} 
	
	
	@Test
	public void testSlideShowGraphicFilesCollectionBasedOnList() {
		ArrayList<IGraphicFile> availableGraphicFilesList = createAnExampleListOfFiles();
		
		SlideShowCollection slideShow = new SlideShowCollection(availableGraphicFilesList);
		
		assertTrue(slideShow.getGraphicFilesList().size() == 3); //that means that Constructor builds that list)
	}
	
	@Test
	public void testSlideShowFilesTotalSize() {
		ArrayList<IGraphicFile> availableGraphicFilesList = createAnExampleListOfFiles();
		
		SlideShowCollection slideShow = new SlideShowCollection(availableGraphicFilesList);
		
		assertTrue(slideShow.slideShowFilesTotalSize() == 300006L);
	}
	
	@Test
	//important note:
	//this test is written only for TDD and is actual only in certain case on certain computer
	//so after this Constructor is done and checked, it is recommended to disable this test 
	public void testSlideShowGraphicFilesCollectionBasedOnFolder() {
		SlideShowCollection slideShow = new SlideShowCollection(DefaultConfigSettings.PATH_TO_FOLDER_WITH_GRAPHIC_FILES);
		
		assertTrue(slideShow.slideShowFilesTotalSize() == 478864L);
	}

	@Test
	public void testFilterGraphicFilesBySize() {
		ArrayList<IGraphicFile> availableGraphicFilesList = createAnExampleListOfFiles();
		
		SlideShowCollection slideShow = new SlideShowCollection(availableGraphicFilesList);
		
		SlideShowCollection filteredSlideShow = slideShow.filterBySize(100002L, 100002L);
		
		assertEquals("incorrect filtering by size!", 100002L, filteredSlideShow.slideShowFilesTotalSize());
	}

	@Test
	public void testFilterGraphicFilesByLastModified() {
		ArrayList<IGraphicFile> availableGraphicFilesList = createAnExampleListOfFiles();
		
		SlideShowCollection slideShow = new SlideShowCollection(availableGraphicFilesList);
		
		SlideShowCollection filteredSlideShow = slideShow.filterByLastModified(1575160669850L, 1575160669950L);
		
		assertEquals("incorrect filtering by last modified!", 1, filteredSlideShow.getGraphicFilesList().size());
	}

	@Test
	public void testFilterGraphicFilesByTAGs() {
		ArrayList<IGraphicFile> availableGraphicFilesList = createAnExampleListOfFiles();
		
		SlideShowCollection slideShow = new SlideShowCollection(availableGraphicFilesList);
		
		SlideShowCollection filteredSlideShow = slideShow.filterByTAGs("signs");
		
		assertEquals("incorrect filtering by tag!", 2, filteredSlideShow.getGraphicFilesList().size());
	}

	@Test
	public void testSortGraphicFilesBySize() {
		ArrayList<IGraphicFile> availableGraphicFilesList = createAnExampleListOfFiles();
		
		SlideShowCollection slideShow = new SlideShowCollection(availableGraphicFilesList);
		slideShow.sortBySize();
		
		IGraphicFile firstGraphicFile = slideShow.getGraphicFilesList().get(0);
		
		assertEquals("incorrect sorting by size!", "3", firstGraphicFile.name());
	}

	@Test
	public void testSortGraphicFilesByLastModified() {
		ArrayList<IGraphicFile> availableGraphicFilesList = createAnExampleListOfFiles();
		
		SlideShowCollection slideShow = new SlideShowCollection(availableGraphicFilesList);
		slideShow.sortByLastModified();
		
		IGraphicFile firstGraphicFile = slideShow.getGraphicFilesList().get(0);
		
		assertEquals("incorrect sorting by size!", "2", firstGraphicFile.name());
	}

	@Test
	public void testSortGraphicFilesByTAGs() {
		ArrayList<IGraphicFile> availableGraphicFilesList = createAnExampleListOfFiles();
		
		SlideShowCollection slideShow = new SlideShowCollection(availableGraphicFilesList);
		slideShow.sortByTAGs();
		
		IGraphicFile firstGraphicFile = slideShow.getGraphicFilesList().get(0);
		
		assertEquals("incorrect sorting by size!", "2", firstGraphicFile.name());
	}

}
