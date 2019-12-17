package ua.training.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import ua.training.model.entities.VectorGraphicFile;
import ua.training.model.entities.FileExtensionsRaster;
import ua.training.model.entities.FileExtensionsVector;
import ua.training.model.entities.IGraphicFile;
import ua.training.model.entities.RasterGraphicFile;

import lombok.*;

@Getter
@Setter

/**
 * this is a main Model class of MVC pattern SlideShowGraphicFilesCollection is
 * a collection, that represents list of GraphicFiles which can be
 * RasterGraphicFile or VectorGraphicFile
 */
public class SlideShowCollection {
	private ArrayList<IGraphicFile> graphicFilesList;

	/**
	 * this constructor creates graphicFilesList based on given list @param
	 * newGraphicFilesList
	 */
	public SlideShowCollection(ArrayList<IGraphicFile> newGraphicFilesList) {
		graphicFilesList = new ArrayList<IGraphicFile>(newGraphicFilesList);
	}

	/**
	 * this constructor creates graphicFilesList based on @param pathToFolder
	 * contents
	 */
	public SlideShowCollection(String pathToFolder) {
		graphicFilesList = new ArrayList<IGraphicFile>();

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
			String fileExtensionUpperCase = IGraphicFile.getFileExtension(file.getAbsolutePath()).toUpperCase();

			if (FileExtensionsVector.getValueOf(fileExtensionUpperCase) != null) {
				graphicFilesList.add(new VectorGraphicFile(file));
			} else if (FileExtensionsRaster.getValueOf(fileExtensionUpperCase) != null) {
				graphicFilesList.add(new RasterGraphicFile(file));
			}
		}
	}
	
	/**
	 * this method returns total files size of current
	 * SlideShowGraphicFilesCollection
	 */
	public long slideShowFilesTotalSize() {
		return graphicFilesList.stream()
				.mapToLong(IGraphicFile::fileSize)
				.sum();
	}

	/**
	 * this method filters current SlideShowGraphicFilesCollection by the range
	 * of @param minSize and @param maxSize and returns a new
	 * SlideShowGraphicFilesCollection
	 */
	public SlideShowCollection filterBySize(long minSize, long maxSize) {
		if ((minSize < 0) || (maxSize < 0)) {
			throw new IllegalArgumentException();
		}
		
		ArrayList<IGraphicFile> newGraphicFilesList;
		
		if ((minSize == 0) && (maxSize == 0)) {
			newGraphicFilesList = graphicFilesList;
		} else {
			newGraphicFilesList = (ArrayList<IGraphicFile>) graphicFilesList.stream()
				.filter((iGF) -> {
					long fileSize = iGF.fileSize();
					if ((minSize > 0) && (maxSize == 0)) {
						if (fileSize >= minSize) {
							return true;
						}
					} else if ((minSize == 0) && (maxSize > 0)) {
						if (fileSize <= maxSize) {
							return true;
						}
					} else {
						if ((minSize <= fileSize) && (fileSize <= maxSize)) {
							return true;
						}
					}
					return false;
				})
				.collect(Collectors.toList());
		}

		return new SlideShowCollection(newGraphicFilesList);
	}

	/**
	 * this method filters current SlideShowGraphicFilesCollection by the range
	 * of @param minDateModified and @param maxDateModified and returns a new
	 * SlideShowGraphicFilesCollection
	 */
	public SlideShowCollection filterByLastModified(long minDateModified, long maxDateModified) {
		if ((minDateModified < 0) || (maxDateModified < 0)) {
			throw new IllegalArgumentException();
		}

		ArrayList<IGraphicFile> newGraphicFilesList;
		if ((minDateModified == 0) && (maxDateModified == 0)) {
			newGraphicFilesList = graphicFilesList;
		} else {
			newGraphicFilesList = (ArrayList<IGraphicFile>) graphicFilesList.stream()
				.filter((iGF) -> {
					long fileLastModified = iGF.lastModified();
					if ((minDateModified > 0) && (maxDateModified == 0)) {
						if (fileLastModified >= minDateModified) {
							return true;
						}
					} else if ((minDateModified == 0) && (maxDateModified > 0)) {
						if (fileLastModified <= maxDateModified) {
							return true;
						}
					} else {
						if ((minDateModified <= fileLastModified) && (fileLastModified <= maxDateModified)) {
							return true;
						}
					}
					return false;
				})
				.collect(Collectors.toList());
		}

		return new SlideShowCollection(newGraphicFilesList);
	}

	/**
	 * this method filters current SlideShowGraphicFilesCollection by @param tags
	 * and returns a new SlideShowGraphicFilesCollection
	 */
	public SlideShowCollection filterByTAGs(String... tags) {
		ArrayList<IGraphicFile> newGraphicFilesList = new ArrayList<IGraphicFile>();

		//TODO looks a bit tricky how to filter it with streams, leave so for a while:
		if (tags.length > 0) {
			for (IGraphicFile iGF : graphicFilesList) {
				for (String tag : tags) {
					if (iGF.hasTag(tag)) {
						newGraphicFilesList.add(iGF);
						break;
					}
				}
			}
		}

		return new SlideShowCollection(newGraphicFilesList);
	}

	/**
	 * sorts current graphicFilesList in Ascending order by file size
	 */
	public void sortBySize() {
		graphicFilesList = (ArrayList<IGraphicFile>) graphicFilesList.stream()
		.sorted(Comparator.comparingLong(IGraphicFile::fileSize))
		.collect(Collectors.toList());
	}

	/**
	 * sorts current graphicFilesList in Ascending order by last modified datetime
	 */
	public void sortByLastModified() {
		graphicFilesList = (ArrayList<IGraphicFile>) graphicFilesList.stream()
		.sorted(Comparator.comparingLong(IGraphicFile::lastModified))
		.collect(Collectors.toList());
	}

	/**
	 * sorts current graphicFilesList in Ascending order by first TAG
	 */
	public void sortByTAGs() {
		graphicFilesList = (ArrayList<IGraphicFile>) graphicFilesList.stream()
		.sorted(Comparator.comparing(IGraphicFile::firstTag))
		.collect(Collectors.toList());
	}
	
}
