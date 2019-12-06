package ua.training.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import ua.training.ConfigSettings;
import ua.training.model.entities.FileExtensionsVector;
import ua.training.model.entities.GraphicFile;
import ua.training.model.entities.RasterGraphicFile;
import ua.training.model.entities.VectorGraphicFile;

public class SlideShowGraphicFilesCollectionTest {
	
	private ArrayList<GraphicFile> createAnExampleListOfFiles() {
		ArrayList<GraphicFile> availableGraphicFilesList = new ArrayList<GraphicFile>();
		
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
		ArrayList<GraphicFile> availableGraphicFilesList = createAnExampleListOfFiles();
		
		SlideShowGraphicFilesCollection slideShowGraphicFilesCollection = new SlideShowGraphicFilesCollection(availableGraphicFilesList);
		
		assertTrue(slideShowGraphicFilesCollection.getGraphicFilesList().size() == 3); //that means that Constructor builds that list)
	}
	
	@Test
	public void testSlideShowFilesTotalSize() {
		ArrayList<GraphicFile> availableGraphicFilesList = createAnExampleListOfFiles();
		
		SlideShowGraphicFilesCollection slideShowGraphicFilesCollection = new SlideShowGraphicFilesCollection(availableGraphicFilesList);
		
		assertTrue(slideShowGraphicFilesCollection.slideShowFilesTotalSize() == 300006L);
	}
	
	@Test
	//important note:
	//this test is written only for TDD and is actual only in certain case on certain computer
	//so after this Constructor is done and checked, it is recommended to disable this test 
	public void testSlideShowGraphicFilesCollectionBasedOnFolder() {
		SlideShowGraphicFilesCollection slideShowGraphicFilesCollection = new SlideShowGraphicFilesCollection(ConfigSettings.PATH_TO_FOLDER_WITH_GRAPHIC_FILES);
		
		assertTrue(slideShowGraphicFilesCollection.slideShowFilesTotalSize() == 478864L);
	}

	@Test
	public void testFilterGraphicFilesBySize() {
		ArrayList<GraphicFile> availableGraphicFilesList = createAnExampleListOfFiles();
		
		SlideShowGraphicFilesCollection slideShowGraphicFilesCollection = new SlideShowGraphicFilesCollection(availableGraphicFilesList);
		
		SlideShowGraphicFilesCollection filteredSlideShowGraphicFilesCollection = slideShowGraphicFilesCollection.filterGraphicFilesBySize(100002L, 100002L);
		
		assertEquals("incorrect filtering by size!", 100002L, filteredSlideShowGraphicFilesCollection.slideShowFilesTotalSize());
	}

	@Test
	public void testFilterGraphicFilesByLastModified() {
		ArrayList<GraphicFile> availableGraphicFilesList = createAnExampleListOfFiles();
		
		SlideShowGraphicFilesCollection slideShowGraphicFilesCollection = new SlideShowGraphicFilesCollection(availableGraphicFilesList);
		
		SlideShowGraphicFilesCollection filteredSlideShowGraphicFilesCollection = slideShowGraphicFilesCollection.filterGraphicFilesByLastModified(1575160669850L, 1575160669950L);
		
		assertEquals("incorrect filtering by last modified!", 1, filteredSlideShowGraphicFilesCollection.getGraphicFilesList().size());
	}

	@Test
	public void testFilterGraphicFilesByTAGs() {
		ArrayList<GraphicFile> availableGraphicFilesList = createAnExampleListOfFiles();
		
		SlideShowGraphicFilesCollection slideShowGraphicFilesCollection = new SlideShowGraphicFilesCollection(availableGraphicFilesList);
		
		SlideShowGraphicFilesCollection filteredSlideShowGraphicFilesCollection = slideShowGraphicFilesCollection.filterGraphicFilesByTAGs("signs");
		
		assertEquals("incorrect filtering by tag!", 2, filteredSlideShowGraphicFilesCollection.getGraphicFilesList().size());
	}

	@Test
	public void testSortGraphicFilesBySize() {
		ArrayList<GraphicFile> availableGraphicFilesList = createAnExampleListOfFiles();
		
		SlideShowGraphicFilesCollection slideShowGraphicFilesCollection = new SlideShowGraphicFilesCollection(availableGraphicFilesList);
		slideShowGraphicFilesCollection.sortGraphicFilesBySize();
		
		GraphicFile firstGraphicFile = slideShowGraphicFilesCollection.getGraphicFilesList().get(0);
		
		assertEquals("incorrect sorting by size!", "3", firstGraphicFile.getName());
	}

	@Test
	public void testSortGraphicFilesByLastModified() {
		ArrayList<GraphicFile> availableGraphicFilesList = createAnExampleListOfFiles();
		
		SlideShowGraphicFilesCollection slideShowGraphicFilesCollection = new SlideShowGraphicFilesCollection(availableGraphicFilesList);
		slideShowGraphicFilesCollection.sortGraphicFilesByLastModified();
		
		GraphicFile firstGraphicFile = slideShowGraphicFilesCollection.getGraphicFilesList().get(0);
		
		
		assertEquals("incorrect sorting by size!", "2", firstGraphicFile.getName());
	}

	@Test
	public void testSortGraphicFilesByTAGs() {
		ArrayList<GraphicFile> availableGraphicFilesList = createAnExampleListOfFiles();
		
		SlideShowGraphicFilesCollection slideShowGraphicFilesCollection = new SlideShowGraphicFilesCollection(availableGraphicFilesList);
		slideShowGraphicFilesCollection.sortGraphicFilesByTAGs();
		
		GraphicFile firstGraphicFile = slideShowGraphicFilesCollection.getGraphicFilesList().get(0);
		
		
		assertEquals("incorrect sorting by size!", "2", firstGraphicFile.getName());
	}

}
