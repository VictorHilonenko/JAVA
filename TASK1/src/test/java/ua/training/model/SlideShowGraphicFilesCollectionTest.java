package ua.training.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import ua.training.model.entities.FileExtensionsVector;
import ua.training.model.entities.GraphicFile;
import ua.training.model.entities.RasterGraphicFile;
import ua.training.model.entities.VectorGraphicFile;

public class SlideShowGraphicFilesCollectionTest {

	@Test
	public void testSlideShowGraphicFilesCollectionBasedOnList() {
		ArrayList<GraphicFile> availableGraphicFilesList = new ArrayList<GraphicFile>();
		
		VectorGraphicFile graphicFile1 = new VectorGraphicFile();
		graphicFile1.setPathToFile("D:\\Documents\\Java\\Tasks\\TASK1\\graphicFiles\\1.svg");
		graphicFile1.setFileSize(100001);
		graphicFile1.setName("1");
		graphicFile1.setLastModified(1575160669900L);
		graphicFile1.setFileExtension(FileExtensionsVector.SVG);
		graphicFile1.addTag("signs");
		
		availableGraphicFilesList.add(graphicFile1);
		
		RasterGraphicFile graphicFile2 = new RasterGraphicFile();
		graphicFile2.setPathToFile("D:\\Documents\\Java\\Tasks\\TASK1\\graphicFiles\\2.jpg");
		graphicFile2.setFileSize(100002);
		graphicFile2.setName("2");
		graphicFile2.setLastModified(1575160669901L);
		graphicFile2.addTag("humor");
		graphicFile2.addTag("road");
		
		availableGraphicFilesList.add(graphicFile2);
		
		RasterGraphicFile graphicFile3 = new RasterGraphicFile();
		graphicFile3.setPathToFile("D:\\Documents\\Java\\Tasks\\TASK1\\graphicFiles\\3.jpg");
		graphicFile3.setFileSize(100003);
		graphicFile3.setName("3");
		graphicFile3.setLastModified(1575160669902L);
		graphicFile3.setHeightPixels(100);
		graphicFile3.setWidthPixels(100);
		graphicFile3.addTag("signs");
		
		availableGraphicFilesList.add(graphicFile3);
		
		SlideShowGraphicFilesCollection slideShowGraphicFilesCollection = new SlideShowGraphicFilesCollection(availableGraphicFilesList);
		
		assertTrue(slideShowGraphicFilesCollection.getGraphicFilesList().size() == 3); //that means that Constructor builds that list)
		
	}
	
	@Test
	public void testSlideShowFilesTotalSize() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testSlideShowGraphicFilesCollectionBasedOnFolder() {
		fail("Not yet implemented");
	}

	@Test
	public void testFilterGraphicFilesBySize() {
		fail("Not yet implemented");
	}

	@Test
	public void testFilterGraphicFilesByLastModified() {
		fail("Not yet implemented");
	}

	@Test
	public void testFilterGraphicFilesByTAGs() {
		fail("Not yet implemented");
	}

	@Test
	public void testSortGraphicFilesBySize() {
		fail("Not yet implemented");
	}

	@Test
	public void testSortGraphicFilesByLastModified() {
		fail("Not yet implemented");
	}

	@Test
	public void testSortGraphicFilesByTAGs() {
		fail("Not yet implemented");
	}

}
