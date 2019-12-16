package ua.training.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import ua.training.model.entities.VectorGraphicFile;
import ua.training.model.entities.FileExtensionsRaster;
import ua.training.model.entities.FileExtensionsVector;
import ua.training.model.entities.GraphicFile;
import ua.training.model.entities.RasterGraphicFile;

/**
 * this is a main Model class of MVC pattern SlideShowGraphicFilesCollection is
 * a collection, that represents list of GraphicFiles which can be
 * RasterGraphicFile or VectorGraphicFile
 */
public class SlideShowGraphicFilesCollection {
	private ArrayList<GraphicFile> graphicFilesList;

	/**
	 * this constructor creates graphicFilesList based on given list @param
	 * newGraphicFilesList
	 */
	public SlideShowGraphicFilesCollection(ArrayList<GraphicFile> newGraphicFilesList) {
		graphicFilesList = new ArrayList<GraphicFile>(newGraphicFilesList);
	}

	/**
	 * this constructor creates graphicFilesList based on @param pathToFolder
	 * contents
	 */
	public SlideShowGraphicFilesCollection(String pathToFolder) {
		graphicFilesList = new ArrayList<GraphicFile>();

		if ("".equals(pathToFolder)) {
			return;
		}

		File myFolder = new File(pathToFolder);
		if (!myFolder.exists()) {
			// TODO throw exception in future releases
			return;
		}

		File[] files = myFolder.listFiles();

		if (files.length == 0) {
			return;
		}

		for (File file : files) {
			String fileExtensionUpperCase = GraphicFile.getFileExtension(file.getAbsolutePath()).toUpperCase();

			if (FileExtensionsVector.getValueOf(fileExtensionUpperCase) != null) {
				graphicFilesList.add(new VectorGraphicFile(file));
			} else if (FileExtensionsRaster.getValueOf(fileExtensionUpperCase) != null) {
				graphicFilesList.add(new RasterGraphicFile(file));
			}
		}
	}

	/**
	 * getter for graphicFilesList
	 */
	public ArrayList<GraphicFile> getGraphicFilesList() {
		return graphicFilesList;
	}

	/**
	 * this method returns total files size of current
	 * SlideShowGraphicFilesCollection
	 */
	public long slideShowFilesTotalSize() {
		return graphicFilesList.stream()
				.mapToLong(GraphicFile::getFileSize)
				.sum();
	}

	/**
	 * this method filters current SlideShowGraphicFilesCollection by the range
	 * of @param minSize and @param maxSize and returns a new
	 * SlideShowGraphicFilesCollection
	 */
	public SlideShowGraphicFilesCollection filterGraphicFilesBySize(long minSize, long maxSize) {
		if ((minSize < 0) || (maxSize < 0)) {
			// TODO maybe throw exception "invalid parameters" in future releases
			return null;
		}
		
/*		
		ArrayList<GraphicFile> newGraphicFilesList = (ArrayList<GraphicFile>) graphicFilesList.stream()
				.filter((gf) -> {
					if ((minSize > 0) && (maxSize == 0)) {
						if (gf.getFileSize() >= minSize) {
							return true;
						} else {
							return false;
						}
					}
				}).sorted()
*/
		
		
		ArrayList<GraphicFile> newGraphicFilesList = new ArrayList<GraphicFile>;
		
		if ((minSize == 0) && (maxSize == 0)) {
			newGraphicFilesList = graphicFilesList;
		} else {
			newGraphicFilesList = new ArrayList<GraphicFile>();
			for (GraphicFile graphicFile : graphicFilesList) {
				long fileSize = graphicFile.getFileSize();
				if ((minSize > 0) && (maxSize == 0)) {
					if (fileSize >= minSize) {
						newGraphicFilesList.add(graphicFile);
					}
				} else if ((minSize == 0) && (maxSize > 0)) {
					if (fileSize <= maxSize) {
						newGraphicFilesList.add(graphicFile);
					}
				} else {
					if ((minSize <= fileSize) && (fileSize <= maxSize)) {
						newGraphicFilesList.add(graphicFile);
					}
				}
			}
		}

		return new SlideShowGraphicFilesCollection(newGraphicFilesList);
	}

	/**
	 * this method filters current SlideShowGraphicFilesCollection by the range
	 * of @param minDateModified and @param maxDateModified and returns a new
	 * SlideShowGraphicFilesCollection
	 */
	public SlideShowGraphicFilesCollection filterGraphicFilesByLastModified(long minDateModified,
			long maxDateModified) {
		if ((minDateModified < 0) || (maxDateModified < 0)) {
			// TODO maybe throw exception "invalid parameters" in future releases
			return null;
		}

		ArrayList<GraphicFile> newGraphicFilesList;
		if ((minDateModified == 0) && (maxDateModified == 0)) {
			newGraphicFilesList = graphicFilesList;
		} else {
			newGraphicFilesList = new ArrayList<GraphicFile>();
			for (GraphicFile graphicFile : graphicFilesList) {
				long fileLastModified = graphicFile.getLastModified();
				if ((minDateModified > 0) && (maxDateModified == 0)) {
					if (fileLastModified >= minDateModified) {
						newGraphicFilesList.add(graphicFile);
					}
				} else if ((minDateModified == 0) && (maxDateModified > 0)) {
					if (fileLastModified <= maxDateModified) {
						newGraphicFilesList.add(graphicFile);
					}
				} else {
					if ((minDateModified <= fileLastModified) && (fileLastModified <= maxDateModified)) {
						newGraphicFilesList.add(graphicFile);
					}
				}
			}
		}

		return new SlideShowGraphicFilesCollection(newGraphicFilesList);
	}

	/**
	 * this method filters current SlideShowGraphicFilesCollection by @param tags
	 * and returns a new SlideShowGraphicFilesCollection
	 */
	public SlideShowGraphicFilesCollection filterGraphicFilesByTAGs(String... tags) {
		ArrayList<GraphicFile> newGraphicFilesList = new ArrayList<GraphicFile>();

		if (tags.length > 0) {
			for (GraphicFile graphicFile : graphicFilesList) {
				for (String tag : tags) {
					if (graphicFile.hasTag(tag)) {
						newGraphicFilesList.add(graphicFile);
						break;
					}
				}
			}
		}

		return new SlideShowGraphicFilesCollection(newGraphicFilesList);
	}

	/**
	 * sorts current graphicFilesList in Ascending order by file size
	 */
	public void sortGraphicFilesBySize() {
		graphicFilesList = (ArrayList<GraphicFile>) graphicFilesList
		.stream()
		.sorted(Comparator.comparingLong(GraphicFile::getFileSize))
		.collect(Collectors.toList());
	}

	/**
	 * sorts current graphicFilesList in Ascending order by last modified datetime
	 */
	public void sortGraphicFilesByLastModified() {
		graphicFilesList = (ArrayList<GraphicFile>) graphicFilesList
		.stream()
		.sorted(Comparator.comparingLong(GraphicFile::getLastModified))
		.collect(Collectors.toList());
	}

	/**
	 * sorts current graphicFilesList in Ascending order by first TAG
	 */
	public void sortGraphicFilesByTAGs() {
		graphicFilesList = (ArrayList<GraphicFile>) graphicFilesList
		.stream()
		.sorted(Comparator.comparing(GraphicFile::getFirstTag))
		.collect(Collectors.toList());
	}
	
}
